package com.example.fintrax.data.mapper

import com.example.fintrax.domain.model.MonthlyExpense
import com.example.fintrax.domain.model.Balance
import com.example.fintrax.data.local.entity.MonthlyExpenseEntity
import com.example.fintrax.data.local.entity.BalanceEntity
import java.util.Date

/**
 * Mapper for financial entities (MonthlyExpense, Balance)
 */
class FinancialMapper {
    
    // Monthly Expense mapping
    fun toMonthlyExpenseEntity(expense: MonthlyExpense): MonthlyExpenseEntity {
        return MonthlyExpenseEntity(
            id = expense.id,
            amount = expense.amount,
            description = expense.description,
            category = expense.category,
            dueDate = expense.dueDate,
            isActive = expense.isActive,
            createdDate = expense.createdDate.time,
            lastPaidDate = expense.lastPaidDate?.time
        )
    }
    
    fun toMonthlyExpenseDomain(entity: MonthlyExpenseEntity): MonthlyExpense {
        return MonthlyExpense(
            id = entity.id,
            amount = entity.amount,
            description = entity.description,
            category = entity.category,
            dueDate = entity.dueDate,
            isActive = entity.isActive,
            createdDate = Date(entity.createdDate),
            lastPaidDate = entity.lastPaidDate?.let { Date(it) }
        )
    }
    
    // Balance mapping
    fun toBalanceEntity(balance: Balance): BalanceEntity {
        return BalanceEntity(
            id = java.util.UUID.randomUUID().toString(),
            totalIncome = balance.totalIncome,
            totalExpenses = balance.totalExpenses,
            netBalance = balance.netBalance,
            period = balance.period.name,
            date = balance.date.time
        )
    }
    
    fun toBalanceDomain(entity: BalanceEntity): Balance {
        return Balance(
            totalIncome = entity.totalIncome,
            totalExpenses = entity.totalExpenses,
            netBalance = entity.netBalance,
            period = com.example.fintrax.domain.model.BalancePeriod.valueOf(entity.period),
            date = Date(entity.date)
        )
    }
}
