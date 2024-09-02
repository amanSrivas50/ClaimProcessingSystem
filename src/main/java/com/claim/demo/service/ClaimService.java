package com.claim.demo.service;

import com.claim.demo.dto.ClaimDTO;
import com.claim.demo.entity.Claim;
import com.claim.demo.repository.ClaimRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClaimService {

	@Autowired
	private ClaimRepository claimRepository;

	private static final Logger logger = LogManager.getLogger(ClaimService.class);
	
	public void updateClaimStatus(Long claimId, String newStatus, String userEmail) {
		


		try {
			Claim claim = claimRepository.findById(claimId).orElseThrow(() -> new RuntimeException("Claim not found"));
			String oldStatus = claim.getClaimStatus();
			claim.setClaimStatus(newStatus);
			claimRepository.save(claim);

			logger.debug("Claim status updated successfully. Claim ID: {}, Old Status: {}, New Status: {}", claimId,
					oldStatus, newStatus);

		} catch (Exception e) {
			logger.error("Error updating claim status. Claim ID: {}, Error: {}", claimId, e.getMessage(), e);
			throw e;
		}
	}
	


	@Transactional
	public ClaimDTO submitClaim(Claim claim) {
		claim.setClaimStatus("Submitted");
		claim.setClaimDate(new Date());
		return convertToDTO(claimRepository.save(claim));
	}

	@Transactional(readOnly = true)
	public Claim findClaimById(Long claimId) {
		Optional<Claim> claim = claimRepository.findById(claimId);
		return claim.get();
	}

	@Transactional
	public ClaimDTO updateClaim(Long claimId, ClaimDTO updatedClaim) {
		Claim claim = findClaimById(claimId);
		claim.setClaimAmount(updatedClaim.getClaimAmount());
		claim.setClaimType(updatedClaim.getClaimType());
		claim.setClaimStatus(updatedClaim.getClaimStatus());
		claim.setLastUpdated(new Date());
		return convertToDTO(claimRepository.save(claim));
	}

	@Transactional
	public void deleteClaim(Long claimId) {
		Claim claim = findClaimById(claimId);
		claimRepository.delete(claim);
	}

	@Transactional(readOnly = true)
	public List<ClaimDTO> findClaimsByUserId(Long userId) {
		List<Claim> claims = claimRepository.findByUserId(userId);
		return claims.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	public ClaimDTO convertToDTO(Claim claim) {
		return new ClaimDTO(claim.getClaimId(), claim.getUser() != null ? claim.getUser().getUserId() : null,
				claim.getClaimDate(), claim.getClaimAmount(), claim.getClaimType(), claim.getClaimStatus(),
				claim.getLastUpdated());
	}

	public List<Claim> findClaimsNeedingUpdate() {
		// Fetch claims based on specific criteria, e.g., status is 'Pending' and last
		// updated > 24 hours ago
		return claimRepository.findClaimsByStatusAndLastUpdatedBefore("Pending",
				new Date(System.currentTimeMillis() - 86400000));
	}


}
