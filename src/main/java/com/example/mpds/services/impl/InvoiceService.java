package com.example.mpds.services.impl;

import com.example.mpds.dto.InvoiceDTO;
import com.example.mpds.dto.UserDTO;
import com.example.mpds.entity.InvoiceEntity;
import com.example.mpds.entity.UserEntity;
import com.example.mpds.mapper.InvoiceMapper;
import com.example.mpds.mapper.UserMapper;
import com.example.mpds.model.UserInvoiceResult;
import com.example.mpds.repository.InvoiceRepository;
import com.example.mpds.services.IInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InvoiceService implements IInvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private InvoiceMapper invoiceMapper;
    @Autowired
    private UserService userService;

    public List<InvoiceDTO> findAll(){
        List<InvoiceDTO> result=new ArrayList<>();
        List<InvoiceEntity> list1=invoiceRepository.findAll();
        for(InvoiceEntity item: list1){
            result.add(invoiceMapper.toDTO(item));

        }
        return result;
    }
    public UserInvoiceResult findAllByUserId(long userId, Pageable pageable){
        List<InvoiceDTO> result=new ArrayList<>();
        Page<InvoiceEntity> lst=invoiceRepository.findByUserId(userId,pageable);
        List<InvoiceDTO> dtoList = lst.stream()
                .map(invoiceMapper::toDTO)
                .toList();

        return new UserInvoiceResult(lst.getTotalElements(), dtoList);
    }



    //return DTO de update sau khi save ??
    public InvoiceEntity save(InvoiceDTO dto){
        InvoiceEntity entity=new InvoiceEntity();
        //se doi lai sang lay name tu dto
        String userName= "thien";
        UserEntity user=userService.findUser(userName);
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
}
