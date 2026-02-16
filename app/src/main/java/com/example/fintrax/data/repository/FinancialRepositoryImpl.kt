package com.example.fintrax.data.repository

import com.example.fintrax.domain.model.MonthlyExpense
import com.example.fintrax.domain.model.Balance
import com.example.fintrax.domain.model.FinancialSummary
import com.example.fintrax.domain.model.BalancePeriod
import com.example.fintrax.domain.repository.FinancialRepository
import com.example.fintrax.data.local.dao.FinancialDao
import com.example.fintrax.data.mapper.FinancialMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Implementation of FinancialRepository
 */
class FinancialRepositoryImpl(
    private val financialDao: FinancialDao,
    private val mapper: FinancialMapper
) : FinancialRepository {
    
    override suspend fun insertMonthlyExpense(expense: MonthlyExpense) {
        val entity = mapper.toMonthlyExpenseEntity(expense)
        financialDao.insertMonthlyExpense(entity)
    }
    
    override suspend fun updateMonthlyExpense(expense: MonthlyExpense) {
        val entity = mapper.toMonthlyExpenseEntity(expense)
        financialDao.updateMonthlyExpense(entity)
    }
    
    override suspend fun deleteMonthlyExpense(expenseId: String) {
        financialDao.deleteMonthlyExpenseById(expenseId)
    }
    
    override suspend fun getMonthlyExpenseById(expenseId: String): MonthlyExpense? {
        val entity = financialDao.getMonthlyExpenseById(expenseId)
        return entity?.let { mapper.toMonthlyExpenseDomain(it) }
    }
    
    override fun getAllMonthlyExpenses(): Flow<List<MonthlyExpense>> {
        return financialDao.getAllMonthlyExpenses().map { entities ->
            entities.map { mapper.toMonthlyExpenseDomain(it) }
        }
    }
    
    override fun getActiveMonthlyExpenses(): Flow<List<MonthlyExpense>> {
        return financialDao.getActiveMonthlyExpenses().map { entities ->
            entities.map { mapper.toMonthlyExpenseDomain(it) }
        }
    }
    
    override suspend fun getCurrentBalance(): Balance {
        val entity = financialDao.getLatestBalance()
        return entity?.let { mapper.toBalanceDomain(it) } ?: createDefaultBalance()
    }
    
    override fun getBalanceHistory(period: BalancePeriod): Flow<List<Balance>> {
        return financialDao.getBalanceHistoryByPeriod(period.name).map { entities ->
            entities.map { mapper.toBalanceDomain(it) }
        }
    }
    
    override suspend fun updateBalance(balance: Balance) {
        val entity = mapper.toBalanceEntity(balance)
        financialDao.insertBalance(entity)
    }
    
    override suspend fun getFinancialSummary(period: BalancePeriod): FinancialSummary {
        // Implementation will calculate summary from transactions and balance
        return createDefaultSummary(period)
    }
    
    private fun createDefaultBalance(): Balance {
        // Default balance implementation
        TODO("Implement default balance creation")
    }
    
    private fun createDefaultSummary(period: BalancePeriod): FinancialSummary {
        // Default summary implementation
        TODO("Implement default summary creation")
    }
}
