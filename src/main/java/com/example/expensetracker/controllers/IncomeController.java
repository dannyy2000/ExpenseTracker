package com.example.expensetracker.controllers;

import com.example.expensetracker.data.dto.request.IncomeRequest;
import com.example.expensetracker.general.ApiResponse;
import com.example.expensetracker.services.interfaces.IncomeService;
import jakarta.annotation.security.PermitAll;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/income")
public class IncomeController extends Controller{

    private final IncomeService incomeService;


    public IncomeController(HttpServletResponse response, IncomeService incomeService) {
        super(response);
        this.incomeService = incomeService;
    }

    @PostMapping("/add")
    @PermitAll
    public ApiResponse<?> addIncome(@Valid @RequestBody IncomeRequest incomeRequest)  {
        return responseWithUpdatedHttpStatus(incomeService.addIncome(incomeRequest));
    }
}
