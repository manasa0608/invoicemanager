package com.invoiceapplication.invoiceapplication.dao;

import com.invoiceapplication.invoiceapplication.resources.InvoiceHeader;
import com.invoiceapplication.invoiceapplication.resources.InvoiceItem;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ComputedInvoice {
  InvoiceHeader invoiceHeader;
  List<InvoiceItem> invoiceItems;
}
