package com.invoiceapplication.invoiceapplication.resources;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "invoice_header")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class InvoiceHeader {

    @Id
    @Column(name = "invoice_number")
    private Long invoiceNumber;

    @Column(name = "total_amount")
    private double totalAmount;

    @Column(name = "external_system")
    private String externalSystem;

    @Column(name = "net_amount")
    private double netAmount;

}
