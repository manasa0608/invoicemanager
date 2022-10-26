package com.invoiceapplication.invoiceapplication.resources;

import javax.persistence.*;

@Table(name = "TAX")
@Entity
public class Tax {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "country")
    private String country;

    @Column(name = "tax_code")
    private String taxCode;

    @Column(name = "tax_percentage")
    private Integer taxPercentage;
}
