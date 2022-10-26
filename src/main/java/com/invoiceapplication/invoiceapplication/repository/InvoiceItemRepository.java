package com.invoiceapplication.invoiceapplication.repository;

import com.invoiceapplication.invoiceapplication.resources.InvoiceItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceItemRepository extends JpaRepository<InvoiceItem, Integer> {
  @Query(
      value = "SELECT country, tax_code, amount, net_computed_amount, invoice_id, id from invoice_item where invoice_id = :invoice_id", nativeQuery = true)
  List<InvoiceItem> findAllByInvoiceNumber(@Param("invoice_id") Long invoiceNumber);
}
