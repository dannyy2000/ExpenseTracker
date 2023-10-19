package com.example.expensetracker.services.interfaces;

import com.example.expensetracker.data.dto.request.IncomeRequest;
import com.example.expensetracker.general.ApiResponse;
import org.apache.tomcat.websocket.AuthenticationException;

public interface IncomeService {

    ApiResponse<?> addIncome(IncomeRequest incomeRequest) ;

    ApiResponse<?> editIncome(IncomeRequest incomeRequest);

    void deleteIncome(Long id);
}
