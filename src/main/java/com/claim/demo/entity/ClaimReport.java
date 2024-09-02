package com.claim.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "claim_reports")
public class ClaimReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "status")
    private String claimStatus;

    @Column(name = "total_claims")
    private Long totalClaims;

    @Column(name = "total_amount")
    private Double totalClaimAmount;

    @Column(name = "report_date")
    private Date reportDate;

}

