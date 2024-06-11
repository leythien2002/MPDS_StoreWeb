package com.example.mpds.repository;



import com.example.mpds.entity.InvoiceInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceInfoRepository extends JpaRepository<InvoiceInfoEntity,Long> {
    @Query("select t from InvoiceInfoEntity t where t.invoice.id=?1")
    List<InvoiceInfoEntity> findByInvoiceId(long id);
}
