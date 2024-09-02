package com.claim.demo.dto;

import lombok.Data;

@Data
public class ClaimReportDTO {
    private String claimStatus;
    private Long totalClaims;
    private Double totalClaimAmount;

    public ClaimReportDTO() {}

    public ClaimReportDTO(String claimStatus, Long totalClaims, Double totalClaimAmount) {
        this.claimStatus = claimStatus;
        this.totalClaims = totalClaims;
        this.totalClaimAmount = totalClaimAmount;
    }

}
