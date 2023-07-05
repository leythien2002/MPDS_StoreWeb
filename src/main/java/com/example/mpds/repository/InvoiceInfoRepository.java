package com.example.mpds.repository;



import com.example.mpds.entity.InvoiceInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceInfoRepository extends JpaRepository<InvoiceInfoEntity,Long> {
}
