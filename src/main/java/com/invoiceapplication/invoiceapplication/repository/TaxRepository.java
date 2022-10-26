package com.invoiceapplication.invoiceapplication.repository;

import com.invoiceapplication.invoiceapplication.resources.Tax;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxRepository extends JpaRepository<Tax, Integer> {

    @Query(value = "SELECT tax_percentage from tax where country =:country  and tax_code =:tax_code", nativeQuery = true)
    Integer findTaxPercentage(@Param("country") String country, @Param("tax_code") String taxCode);
}
