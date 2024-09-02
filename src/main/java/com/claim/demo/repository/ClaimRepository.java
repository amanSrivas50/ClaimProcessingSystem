package com.claim.demo.repository;

import com.claim.demo.entity.Claim;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ClaimRepository extends JpaRepository<Claim, Long> {
    List<Claim> findByUserId(Long userId);

	Claim save(Claim claim);
	
    List<Claim> findClaimsByStatusAndLastUpdatedBefore(String status, Date lastUpdated);

}