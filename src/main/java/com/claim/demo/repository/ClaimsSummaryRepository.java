package com.claim.demo.repository;

import com.claim.demo.entity.ClaimsSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ClaimsSummaryRepository extends JpaRepository<ClaimsSummary, Long> {
    ClaimsSummary findTopByOrderByReportGeneratedDesc();

    List<ClaimsSummary> findByReportGeneratedBetween(Date start, Date end);
}
