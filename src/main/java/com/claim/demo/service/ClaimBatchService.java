package com.claim.demo.service;

import com.claim.demo.entity.Claim;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClaimBatchService {

    @Autowired
    private ClaimService claimService;

    @Scheduled(cron = "0 0 * * * *")
    public void processPendingClaims() {
        List<Claim> claims = claimService.findClaimsNeedingUpdate();
        for (Claim claim : claims) {
            try {
                String newStatus = "Processed";
                claimService.updateClaimStatus(claim.getClaimId(), newStatus, claim.getEmailId());
            } catch (Exception e) {
                System.out.println("Error processing claim ID: " + claim.getClaimId() + " with error: " + e.getMessage());
            }
        }
    }
}

