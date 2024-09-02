package com.claim.demo.controller;

import com.claim.demo.dto.ClaimReportDTO;
import com.claim.demo.dto.ClaimsSummaryDTO;
import com.claim.demo.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller for handling report related operations.
 * This controller provides endpoints for accessing reports about claims' statuses and summaries.
 */
@RestController
@RequestMapping("/reports")
public class ReportsController {
    @Autowired
    private ReportService reportService;

    /**
     * Endpoint to retrieve a report of claims grouped by their status.
     * This can be useful for dashboard or monitoring purposes to see the distribution of claims' statuses.
     * 
     * @return ResponseEntity containing a list of ClaimReportDTO with detailed claims status report.
     */
    @GetMapping("/claims/status")
    public ResponseEntity<List<ClaimReportDTO>> getClaimsReportByStatus() {
        List<ClaimReportDTO> reports = reportService.generateReportByStatus();
        return ResponseEntity.ok(reports);
    }

    /**
     * Endpoint to get a summary of claims.
     * This summary might include aggregated data like total claims, total approved, total rejected, etc.
     * 
     * @return ResponseEntity containing a ClaimsSummaryDTO with summary details of all claims.
     */
    @GetMapping("/claims/summary")
    public ResponseEntity<ClaimsSummaryDTO> getClaimsSummary() {
        ClaimsSummaryDTO summary = reportService.generateClaimsSummary();
        return ResponseEntity.ok(summary);
    }
}
