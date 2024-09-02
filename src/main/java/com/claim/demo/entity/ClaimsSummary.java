package com.claim.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "claims_summaries")
public class ClaimsSummary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "report_generated")
    private Date reportGenerated;

    @Column(name = "number_of_claims")
    private Integer numberOfClaims;

    @Column(name = "total_amount")
    private Double totalAmount;

}


