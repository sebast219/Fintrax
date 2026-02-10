package com.example.fintrax.domain.repository

import com.example.fintrax.domain.model.MonthlyExpense
import com.example.fintrax.domain.model.Balance
import com.example.fintrax.domain.model.FinancialSummary
import com.example.fintrax.domain.model.BalancePeriod
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for financial operations
 * Handles balance, monthly expenses, and financial summaries
 */
interface FinancialRepository {
    // Monthly Expenses
    suspend fun insertMonthlyExpense(expense: MonthlyExpense)
    suspend fun updateMonthlyExpense(expense: MonthlyExpense)
    suspend fun deleteMonthlyExpense(expenseId: String)
    suspend fun getMonthlyExpenseById(expenseId: String): MonthlyExpense?
    fun getAllMonthlyExpenses(): Flow<List<MonthlyExpense>>
    fun getActiveMonthlyExpenses(): Flow<List<MonthlyExpense>>
    
    // Balance Operations
    suspend fun getCurrentBalance(): Balance
    fun getBalanceHistory(period: BalancePeriod): Flow<List<Balance>>
    suspend fun updateBalance(balance: Balance)
    
    // Financial Summary
    suspend fun getFinancialSummary(period: BalancePeriod): FinancialSummary
    suspend fun getMonthlyAverage(): java.math.BigDecimal
}
