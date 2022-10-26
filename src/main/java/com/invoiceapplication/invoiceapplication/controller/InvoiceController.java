package com.invoiceapplication.invoiceapplication.controller;

import com.invoiceapplication.invoiceapplication.dao.ComputedInvoice;
import com.invoiceapplication.invoiceapplication.dao.Invoice;
import com.invoiceapplication.invoiceapplication.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {

    @Autowired
    InvoiceService invoiceService;

    /**
     * API to accept invoice and compute tax and total net amount
     * @param invoice data
     * @return message
     */
    @PostMapping
    public String addInvoiceDetails(@RequestBody Invoice invoice){
        return invoiceService.createInvoice(invoice);
    }

    /**
     * API to fetch all invoices
     * @return List of ComputedInvoice
     */
    @GetMapping
    public List<ComputedInvoice> getAllInvoices(){
        return invoiceService.findAllInvoices();
    }
}
