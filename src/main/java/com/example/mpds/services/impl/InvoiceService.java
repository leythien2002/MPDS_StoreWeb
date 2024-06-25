package com.example.mpds.services.impl;

import com.example.mpds.dto.InvoiceDTO;
import com.example.mpds.dto.ProductDTO;
import com.example.mpds.dto.UserDTO;
import com.example.mpds.entity.CategoryEntity;
import com.example.mpds.entity.InvoiceEntity;
import com.example.mpds.entity.ProductEntity;
import com.example.mpds.entity.UserEntity;
import com.example.mpds.mapper.InvoiceMapper;
import com.example.mpds.mapper.UserMapper;
import com.example.mpds.model.TotalStatusInvoice;
import com.example.mpds.model.UserInvoiceResult;
import com.example.mpds.repository.InvoiceRepository;
import com.example.mpds.repository.InvoiceSpecification;
import com.example.mpds.services.IInvoiceService;
import jakarta.servlet.http.HttpSession;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.time.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class InvoiceService implements IInvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private InvoiceMapper invoiceMapper;
    @Autowired
    private UserService userService;

    public List<InvoiceDTO> findAll() throws ParseException {
        List<InvoiceDTO> result=new ArrayList<>();
        List<InvoiceEntity> list1=invoiceRepository.findAll();
        for(InvoiceEntity item: list1){
            result.add(invoiceMapper.toDTO(item));

        }
        return result;
    }
    public List<InvoiceEntity> findInvoices(String status, Date startDate, Date endDate) {
        Specification<InvoiceEntity> spec = Specification.where(InvoiceSpecification.hasStatus(status))
                .and(InvoiceSpecification.createDateBetween(startDate, endDate));
        return invoiceRepository.findAll(spec);
    }
    public TotalStatusInvoice getTotalStatusInvoice(Date startDate, Date endDate){
        Integer pending=findInvoices("Pending", startDate, endDate).size();
        Integer paid=findInvoices("Paid", startDate, endDate).size();
        Integer cancelled=findInvoices("Cancelled", startDate, endDate).size();
        Integer delivered=findInvoices("delivered", startDate, endDate).size();

        return new TotalStatusInvoice(pending,paid,cancelled, delivered );

    }
    public Integer getInvoicesForJulyAndAugust(int year, Month month) {
        LocalDate startLocalDate = LocalDate.of(year, month, 1);
        YearMonth yearMonthObject = YearMonth.of(year, month);
        int lastDay = yearMonthObject.lengthOfMonth();
        LocalDate endLocalDate = LocalDate.of(year, month, lastDay);

        LocalDateTime startDateTime = startLocalDate.atStartOfDay();
        LocalDateTime endDateTime = endLocalDate.atTime(23, 59, 59, 999_999_999);

        Date startDate = Date.from(startDateTime.atZone(ZoneId.systemDefault()).toInstant());
        Date endDate = Date.from(endDateTime.atZone(ZoneId.systemDefault()).toInstant());

        return (int) invoiceRepository.findInvoicesBetweenDates(startDate, endDate).stream().mapToDouble(InvoiceEntity::getTotalMoney).sum();
    }
    public Integer totalInvoice(Date startDate, Date endDate){
        Specification<InvoiceEntity> spec = Specification.where(InvoiceSpecification.createDateBetween(startDate, endDate));
        return invoiceRepository.findAll(spec).size();
    }
    public Integer totalRevenue(Date startDate, Date endDate){
        Specification<InvoiceEntity> spec = Specification.where(InvoiceSpecification.createDateBetween(startDate, endDate));
        List<InvoiceEntity> listInvoice=invoiceRepository.findAll(spec);
        double sum = 0;
        for ( InvoiceEntity invoice : listInvoice){
            if(invoice.getStatus().equals("Paid")) sum += invoice.getTotalMoney();
        }
//        return (int) listInvoice.stream().mapToDouble(InvoiceEntity::getTotalMoney).sum();
        return (int) sum;
    }
    @SneakyThrows
    public UserInvoiceResult findAllByUserId(long userId, Pageable pageable){
        List<InvoiceDTO> result=new ArrayList<>();
        Page<InvoiceEntity> lst=invoiceRepository.findByUserId(userId,pageable);
        List<InvoiceDTO> dtoList = lst.stream()
                .map(invoiceMapper::toDTO)
                .toList();

        return new UserInvoiceResult(lst.getTotalElements(), dtoList);
    }

    //return DTO de update sau khi save ??
    public InvoiceEntity save(InvoiceDTO dto, int userId){
        InvoiceEntity entity=new InvoiceEntity();
        //se doi lai sang lay name tu dto
        UserEntity user=userService.findUserById((long)userId);
        //
        if(dto.getId()!=0){
           Optional<InvoiceEntity> tmp=invoiceRepository.findById(Long.valueOf(dto.getId()));
           InvoiceEntity oldInfor=new InvoiceEntity();
            if(tmp.isPresent()){
                oldInfor=tmp.get();
            }
//            entity= invoiceMapper.toEntity(dto,oldInfor);
        }
        else{
            entity=invoiceMapper.toEntity(dto,user);
        }
        entity=invoiceRepository.save(entity);
//        return invoiceMapper.toDTO(entity);
        return entity;
    }
    public InvoiceEntity findInvoice(int id){
        InvoiceEntity invoice=invoiceRepository.findOneById(id);
        return invoice;
    }

    public void updateInvoice(InvoiceDTO invoiceDTO, HttpSession session)
    {
        String userName= (String) session.getAttribute("username");
        InvoiceEntity existingInvoice = invoiceRepository.findById((long) invoiceDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("Invoice not found"));
        existingInvoice.setStatus(invoiceDTO.getStatus());
        existingInvoice.setEmail(invoiceDTO.getEmail());
        existingInvoice.setPhone(invoiceDTO.getPhone());
        existingInvoice.setAddress(invoiceDTO.getAddress());
        existingInvoice.setTotalMoney(invoiceDTO.getTotalMoney());
        existingInvoice.setPaymentMethod(invoiceDTO.getPaymentMethod());
        existingInvoice.setUpdatedBy(userName);
        invoiceRepository.save(existingInvoice);
    }


}
