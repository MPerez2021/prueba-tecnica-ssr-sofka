package com.microservice.group_two.service.report;

import com.microservice.group_two.dto.response.AccountReportDto;
import com.microservice.group_two.entity.Account;

import java.time.LocalDate;
import java.util.List;

public interface ReportService {

    List<AccountReportDto> getAccountReportByClientAndDateRange(String clientName, LocalDate startDate, LocalDate endDate);
}
