package com.invoiceapplication.invoiceapplication;

import com.invoiceapplication.invoiceapplication.dao.ComputedInvoice;
import com.invoiceapplication.invoiceapplication.repository.InvoiceHeaderRepository;
import com.invoiceapplication.invoiceapplication.repository.InvoiceItemRepository;
import com.invoiceapplication.invoiceapplication.resources.InvoiceHeader;
import com.invoiceapplication.invoiceapplication.resources.InvoiceItem;
import com.invoiceapplication.invoiceapplication.service.InvoiceService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
class InvoiceapplicationApplicationTests {

  @Autowired private InvoiceService invoiceService;

  @MockBean private InvoiceItemRepository invoiceItemRepository;

  @MockBean private InvoiceHeaderRepository invoiceHeaderRepository;

  @Test
  public void findAllInvoicesTest() {
    when(invoiceItemRepository.findAllByInvoiceNumber(10001L))
        .thenReturn(
            Stream.of(
                    new InvoiceItem(1, 10001, "India", "Default", 200.00, 260.00),
                    new InvoiceItem(1, 10001, "Germany", "Default", 100.00, 140.00))
                .collect(Collectors.toList()));

    when(invoiceHeaderRepository.findAll())
        .thenReturn(
            Stream.of(new InvoiceHeader(10001L, 300.00, "System1", 400.00))
                .collect(Collectors.toList()));

    List<ComputedInvoice> computedInvoiceList = invoiceService.findAllInvoices();

    Assertions.assertEquals(computedInvoiceList.get(0).getInvoiceHeader().getNetAmount(), 400);
    Assertions.assertEquals(1, computedInvoiceList.size());
  }
}
