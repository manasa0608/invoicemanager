package com.invoiceapplication.invoiceapplication.service;

import com.invoiceapplication.invoiceapplication.dao.ComputedInvoice;
import com.invoiceapplication.invoiceapplication.dao.Invoice;
import com.invoiceapplication.invoiceapplication.dao.InvoiceItems;
import com.invoiceapplication.invoiceapplication.helpers.InvoiceHelper;
import com.invoiceapplication.invoiceapplication.repository.InvoiceHeaderRepository;
import com.invoiceapplication.invoiceapplication.repository.InvoiceItemRepository;
import com.invoiceapplication.invoiceapplication.resources.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class InvoiceService {

  @Autowired InvoiceItemRepository invoiceItemRepository;

  @Autowired InvoiceHeaderRepository invoiceHeaderRepository;

  @Autowired InvoiceHelper invoiceHelper;

  @Autowired TaxService taxService;

  private static final Logger logger = LoggerFactory.getLogger(InvoiceService.class);

  public String createInvoice(Invoice invoice) {
    try {
      logger.info("Received invoice, computing tax");
      Double netTaxComputed = 0d;

      for (InvoiceItems individualInvoiceItem : invoice.getItems()) {
        logger.info("Calculating tax as per individual items");
        Double itemComputedAmount = taxService.computeTax(individualInvoiceItem);
        invoiceHelper.addInvoiceItemEntry(
            invoice.getInvoiceNumber(), individualInvoiceItem, itemComputedAmount);
        netTaxComputed += itemComputedAmount;
      }

      invoiceHelper.createInvoiceEntry(invoice, netTaxComputed);
      return "Successfully created an invoice with tax computations";

    } catch (Exception exception) {
      logger.error(
          "Exception occurred while creating the invoice. Details are - {}",
          exception.getMessage());
      throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Unable to create invoice");
    }
  }

  public List<ComputedInvoice> findAllInvoices() {
    try {

      List<ComputedInvoice> computedInvoiceList = new ArrayList<>();

      for (InvoiceHeader individualInvoice : invoiceHeaderRepository.findAll()) {
        List<InvoiceItem> invoiceItemList = invoiceItemRepository.findAllByInvoiceNumber(individualInvoice.getInvoiceNumber());



        computedInvoiceList.add(invoiceHelper.buildComputedInvoice(individualInvoice,invoiceItemList));
      }
      return computedInvoiceList;
    } catch (Exception exception) {
      logger.error(
          "Exception occurred while retrieving the invoices. Details are - {}",
          exception.getMessage());
      throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Failed to fetch invoices data");
    }
  }
}
