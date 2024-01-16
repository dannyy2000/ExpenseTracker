package com.example.expensetracker.controllers;

import com.example.expensetracker.data.dto.request.CreateBudgetRequest;
import com.example.expensetracker.data.dto.request.EditBudgetRequest;
import com.example.expensetracker.general.ApiResponse;
import com.example.expensetracker.services.interfaces.BudgetService;
import jakarta.annotation.security.PermitAll;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/budget")
public class BudgetController extends Controller{

    private final BudgetService budgetService;

    public BudgetController(HttpServletResponse response, BudgetService budgetService) {
        super(response);
        this.budgetService = budgetService;
    }

    @PostMapping("/create")
    @PermitAll
    public ApiResponse<?> createBudget(@Valid @RequestBody CreateBudgetRequest createBudgetRequest){
        return responseWithUpdatedHttpStatus(budgetService.createBudget(createBudgetRequest));
    }

    @PutMapping("/edit")
    @PermitAll
    public ApiResponse<?> editBudget(@RequestParam Long id, @Valid @RequestBody EditBudgetRequest editBudgetRequest){
        return responseWithUpdatedHttpStatus(budgetService.editBudget(id, editBudgetRequest));
    }

    @DeleteMapping("/delete")
    @PermitAll
    public void deleteBudget(@RequestParam Long id){
        budgetService.deleteBudget(id);
    }
}
