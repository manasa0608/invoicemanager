package com.invoiceapplication.invoiceapplication.resources;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.aspectj.apache.bcel.classfile.Unknown;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "invoice_item")
@Entity
public class InvoiceItem {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  @JsonIgnore
  private int id;

  @Column(name = "invoice_id", nullable = false)
  private Integer invoiceId;

  @Column(name = "country")
  private String country;

  @Column(name = "tax_code")
  private String taxCode;

  @Column(name = "amount")
  private Double amount;

  @Column(name = "net_computed_amount")
  private Double netComputedAmount;
}
