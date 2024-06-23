package com.example.mpds.repository;

import com.example.mpds.entity.InvoiceEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<InvoiceEntity,Long>, JpaSpecificationExecutor<InvoiceEntity> {
    InvoiceEntity findOneById(int id);
    @Query("select i from InvoiceEntity i where  i.user.id=?1")
    List<InvoiceEntity> findByUser(long id);
    Page<InvoiceEntity> findByUserId(long id, Pageable pageable);

//    @Query("SELECT i FROM InvoiceEntity i WHERE i.status=?1 and i.createDate BETWEEN ?2 AND ?3")
//    List<InvoiceEntity> findAllByStatus(String status,Date startDate, Date endDate);

    @Query("SELECT i FROM InvoiceEntity i WHERE i.createDate BETWEEN ?1 AND ?2")
    List<InvoiceEntity> findInvoicesBetweenDates(Date startDate, Date endDate);



}
