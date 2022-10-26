package com.invoiceapplication.invoiceapplication.dao;

import lombok.Data;

@Data
public class InvoiceItems {
    private double amount;
    private String taxCode;
    private String country;
}
