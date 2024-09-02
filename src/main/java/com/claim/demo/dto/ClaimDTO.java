package com.claim.demo.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ClaimDTO {
    private Long claimId;
    private Long userId;
    private String emailId;
    private Date claimDate;
    private Double claimAmount;
    private String claimType;
    private String claimStatus;
    private Date lastUpdated;


    public ClaimDTO() {}

    public ClaimDTO(Long claimId, Long userId, Date claimDate, Double claimAmount, String claimType, String claimStatus, Date lastUpdated) {
        this.claimId = claimId;
        this.userId = userId;
        this.claimDate = claimDate;
        this.claimAmount = claimAmount;
        this.claimType = claimType;
        this.claimStatus = claimStatus;
        this.lastUpdated = lastUpdated;
    }


}
