package com.invoiceapplication.invoiceapplication.helpers;

import com.invoiceapplication.invoiceapplication.dao.ComputedInvoice;
import com.invoiceapplication.invoiceapplication.dao.Invoice;
import com.invoiceapplication.invoiceapplication.dao.InvoiceItems;
import com.invoiceapplication.invoiceapplication.repository.InvoiceHeaderRepository;
import com.invoiceapplication.invoiceapplication.repository.InvoiceItemRepository;
import com.invoiceapplication.invoiceapplication.resources.InvoiceHeader;
import com.invoiceapplication.invoiceapplication.resources.InvoiceItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceHelper {

  @Autowired InvoiceItemRepository invoiceItemRepository;

  @Autowired InvoiceHeaderRepository invoiceHeaderRepository;

  public void createInvoiceEntry(Invoice invoice, Double netTaxComputed) {
    InvoiceHeader invoiceHeader =
        InvoiceHeader.builder()
            .invoiceNumber(invoice.getInvoiceNumber())
            .externalSystem(invoice.getExternalSystem())
            .netAmount(netTaxComputed + invoice.getTotalAmount())
            .totalAmount(invoice.getTotalAmount())
            .build();
    invoiceHeaderRepository.saveAndFlush(invoiceHeader);
  }

  public void addInvoiceItemEntry(
      long invoiceNumber, InvoiceItems invoiceItem, Double itemComputedAmount) {
    InvoiceItem invoiceItemEntry =
        InvoiceItem.builder()
            .invoiceId((int) invoiceNumber)
            .amount(invoiceItem.getAmount())
            .country(invoiceItem.getCountry())
            .taxCode(invoiceItem.getTaxCode())
            .netComputedAmount(itemComputedAmount + invoiceItem.getAmount())
            .build();
    invoiceItemRepository.save(invoiceItemEntry);
  }

  public ComputedInvoice buildComputedInvoice(InvoiceHeader individualInvoice, List<InvoiceItem> invoiceItemList) {
    return ComputedInvoice.builder()
            .invoiceHeader(individualInvoice)
            .invoiceItems(invoiceItemList)
            .build();
  }
}
