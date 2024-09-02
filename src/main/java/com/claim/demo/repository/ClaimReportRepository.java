package com.claim.demo.repository;

import com.claim.demo.entity.ClaimReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ClaimReportRepository extends JpaRepository<ClaimReport, Long> {
    List<ClaimReport> findByClaimStatus(String claimStatus);

    List<ClaimReport> findByReportDateBetween(Date startDate, Date endDate);
}

