package com.example.mpds.repository;

import com.example.mpds.entity.InvoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<InvoiceEntity,Long> {
    InvoiceEntity findOneById(int id);
    @Query("select i from InvoiceEntity i where  i.user.id=?1")
    List<InvoiceEntity> findByUser(long id);


}
