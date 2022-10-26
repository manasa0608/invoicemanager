package com.invoiceapplication.invoiceapplication.service;

import com.invoiceapplication.invoiceapplication.repository.TaxRepository;
import com.invoiceapplication.invoiceapplication.dao.InvoiceItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class TaxService {

  private static final Logger logger = LoggerFactory.getLogger(TaxService.class);

  @Autowired TaxRepository taxRepository;

  public Double computeTax(InvoiceItems invoiceItems) {

    Integer taxPercentage =
        taxRepository.findTaxPercentage(invoiceItems.getCountry(), invoiceItems.getTaxCode());

    if (Objects.isNull(taxPercentage)) {
      logger.error("Tax percentage is found to be null");
    }
    logger.info(
        "Tax percentage for country {} with tax code {} is {}",
        invoiceItems.getCountry(),
        invoiceItems.getTaxCode(),
        taxPercentage);
    return (invoiceItems.getAmount() * taxPercentage) / 100;
  }
}
