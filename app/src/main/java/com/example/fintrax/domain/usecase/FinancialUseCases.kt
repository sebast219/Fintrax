package com.example.fintrax.domain.usecase

import com.example.fintrax.domain.model.MonthlyExpense
import com.example.fintrax.domain.model.Balance
import com.example.fintrax.domain.model.FinancialSummary
import com.example.fintrax.domain.model.BalancePeriod
import com.example.fintrax.domain.repository.FinancialRepository
import kotlinx.coroutines.flow.Flow

/**
 * Use case for managing monthly expenses
 */
class AddMonthlyExpenseUseCase(
    private val repository: FinancialRepository
) {
    suspend operator fun invoke(expense: MonthlyExpense) {
        repository.insertMonthlyExpense(expense)
    }
}

/**
 * Use case for getting current balance
 */
class GetCurrentBalanceUseCase(
    private val repository: FinancialRepository
) {
    suspend operator fun invoke(): Balance {
        return repository.getCurrentBalance()
    }
}

/**
 * Use case for getting financial summary
 */
class GetFinancialSummaryUseCase(
    private val repository: FinancialRepository
) {
    suspend operator fun invoke(period: BalancePeriod): FinancialSummary {
        return repository.getFinancialSummary(period)
    }
}

/**
 * Use case for managing monthly expenses
 */
class GetMonthlyExpensesUseCase(
    private val repository: FinancialRepository
) {
    operator fun invoke(): Flow<List<MonthlyExpense>> {
        return repository.getActiveMonthlyExpenses()
    }
}
