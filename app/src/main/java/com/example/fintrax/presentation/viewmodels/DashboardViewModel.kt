package com.example.fintrax.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fintrax.domain.model.Balance
import com.example.fintrax.domain.model.FinancialSummary
import com.example.fintrax.domain.model.BalancePeriod
import com.example.fintrax.domain.model.MonthlyExpense
import com.example.fintrax.domain.usecase.GetCurrentBalanceUseCase
import com.example.fintrax.domain.usecase.GetFinancialSummaryUseCase
import com.example.fintrax.domain.usecase.GetMonthlyExpensesUseCase
import com.example.fintrax.domain.usecase.AddMonthlyExpenseUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel for dashboard and financial operations
 * Manages balance, summary, and monthly expenses state
 */
class DashboardViewModel(
    private val getCurrentBalanceUseCase: GetCurrentBalanceUseCase,
    private val getFinancialSummaryUseCase: GetFinancialSummaryUseCase,
    private val getMonthlyExpensesUseCase: GetMonthlyExpensesUseCase,
    private val addMonthlyExpenseUseCase: AddMonthlyExpenseUseCase
) : ViewModel() {
    
    private val _balance = MutableStateFlow<Balance?>(null)
    val balance: StateFlow<Balance?> = _balance.asStateFlow()
    
    private val _financialSummary = MutableStateFlow<FinancialSummary?>(null)
    val financialSummary: StateFlow<FinancialSummary?> = _financialSummary.asStateFlow()
    
    private val _monthlyExpenses = MutableStateFlow<List<MonthlyExpense>>(emptyList())
    val monthlyExpenses: StateFlow<List<MonthlyExpense>> = _monthlyExpenses.asStateFlow()
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()
    
    init {
        loadDashboardData()
    }
    
    private fun loadDashboardData() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                // Load current balance
                _balance.value = getCurrentBalanceUseCase()
                
                // Load financial summary for current month
                _financialSummary.value = getFinancialSummaryUseCase(BalancePeriod.MONTHLY)
                
                _isLoading.value = false
            } catch (e: Exception) {
                _errorMessage.value = "Error loading dashboard data: ${e.message}"
                _isLoading.value = false
            }
        }
        
        // Load monthly expenses separately as it's a Flow
        viewModelScope.launch {
            try {
                getMonthlyExpensesUseCase().collect { expenses ->
                    _monthlyExpenses.value = expenses
                }
            } catch (e: Exception) {
                _errorMessage.value = "Error loading monthly expenses: ${e.message}"
            }
        }
    }
    
    fun refreshData() {
        loadDashboardData()
    }
    
    fun addMonthlyExpense(expense: MonthlyExpense) {
        viewModelScope.launch {
            try {
                addMonthlyExpenseUseCase(expense)
                // Monthly expenses will be updated automatically via flow
            } catch (e: Exception) {
                _errorMessage.value = "Error adding monthly expense: ${e.message}"
            }
        }
    }
    
    fun clearError() {
        _errorMessage.value = null
    }
}
