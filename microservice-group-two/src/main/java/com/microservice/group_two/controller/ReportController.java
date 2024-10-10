package com.microservice.group_two.controller;

import com.microservice.group_two.dto.response.AccountReportDto;
import com.microservice.group_two.service.report.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reports")
public class ReportController {

    private final ReportService reportService;

    @GetMapping
    public ResponseEntity<List<AccountReportDto>> getAccountReport(@Param("clientName") String clientName,
                                                                   @Param("startDate") LocalDate startDate,
                                                                   @Param("endDate") LocalDate endDate) {
        return ResponseEntity.ok(reportService.getAccountReportByClientAndDateRange(clientName, startDate, endDate));
    }
}
