package com.example.mpds.repository;

import com.example.mpds.entity.InvoiceEntity;
import com.example.mpds.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<InvoiceEntity,Long> {
    InvoiceEntity findOneById(int id);


}
