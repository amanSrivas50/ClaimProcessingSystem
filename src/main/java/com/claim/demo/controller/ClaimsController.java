package com.claim.demo.controller;

import com.claim.demo.dto.ClaimDTO;
import com.claim.demo.entity.Claim;
import com.claim.demo.service.ClaimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing claims.
 * Provides endpoints for creating, retrieving, updating, and deleting claims.
 */
@RestController
@RequestMapping("/claims")
public class ClaimsController {
    @Autowired
    private ClaimService claimService;

    /**
     * Submits a new claim.
     * @param claim the claim entity to be submitted
     * @return the submitted claim data with HTTP status 201
     */
    @PostMapping
    public ResponseEntity<ClaimDTO> submitClaim(@RequestBody Claim claim) {
        ClaimDTO submittedClaim = claimService.submitClaim(claim);
        return new ResponseEntity<>(submittedClaim, HttpStatus.CREATED);
    }

    /**
     * Retrieves a claim by its ID.
     * @param claimId the ID of the claim
     * @return the claim data if found, with HTTP status 200
     */
    @GetMapping("/{claimId}")
    public ResponseEntity<ClaimDTO> getClaimById(@PathVariable Long claimId) {
        ClaimDTO claim = claimService.convertToDTO(claimService.findClaimById(claimId));
        return ResponseEntity.ok(claim);
    }

    /**
     * Updates an existing claim.
     * @param claimId the ID of the claim to update
     * @param updatedClaim the updated claim data
     * @return the updated claim data with HTTP status 200
     */
    @PutMapping("/{claimId}")
    public ResponseEntity<ClaimDTO> updateClaim(@PathVariable Long claimId, @RequestBody ClaimDTO updatedClaim) {
        ClaimDTO claim = claimService.updateClaim(claimId, updatedClaim);
        return ResponseEntity.ok(claim);
    }

    /**
     * Deletes a claim by its ID.
     * @param claimId the ID of the claim to be deleted
     * @return HTTP status 204 (No Content) if the deletion was successful
     */
    @DeleteMapping("/{claimId}")
    public ResponseEntity<Void> deleteClaim(@PathVariable Long claimId) {
        claimService.deleteClaim(claimId);
        return ResponseEntity.noContent().build();
    }

    /**
     * Retrieves all claims associated with a specific user ID.
     * @param userId the ID of the user whose claims to retrieve
     * @return a list of claims with HTTP status 200
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ClaimDTO>> getClaimsByUserId(@PathVariable Long userId) {
        List<ClaimDTO> claims = claimService.findClaimsByUserId(userId);
        return ResponseEntity.ok(claims);
    }

}
