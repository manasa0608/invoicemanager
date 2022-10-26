package com.invoiceapplication.invoiceapplication.dao;

import lombok.Data;
import java.util.List;

@Data
public class Invoice {
  private long invoiceNumber;
  private double totalAmount;
  private String externalSystem;
  private List<InvoiceItems> items;
}
