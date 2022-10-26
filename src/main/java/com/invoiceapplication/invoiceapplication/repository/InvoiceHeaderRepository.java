package com.invoiceapplication.invoiceapplication.repository;

import com.invoiceapplication.invoiceapplication.resources.InvoiceHeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceHeaderRepository extends JpaRepository<InvoiceHeader, Long> {
}
