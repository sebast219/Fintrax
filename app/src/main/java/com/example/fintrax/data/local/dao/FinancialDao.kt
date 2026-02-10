package com.example.fintrax.data.local.dao

import androidx.room.*
import com.example.fintrax.data.local.entity.MonthlyExpenseEntity
import com.example.fintrax.data.local.entity.BalanceEntity
import kotlinx.coroutines.flow.Flow

/**
 * DAO for financial operations (monthly expenses, balance)
 */
@Dao
interface FinancialDao {
    
    // Monthly Expenses
    @Query("SELECT * FROM monthly_expenses ORDER BY dueDate ASC")
    fun getAllMonthlyExpenses(): Flow<List<MonthlyExpenseEntity>>
    
    @Query("SELECT * FROM monthly_expenses WHERE isActive = 1 ORDER BY dueDate ASC")
    fun getActiveMonthlyExpenses(): Flow<List<MonthlyExpenseEntity>>
    
    @Query("SELECT * FROM monthly_expenses WHERE id = :id")
    suspend fun getMonthlyExpenseById(id: String): MonthlyExpenseEntity?
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMonthlyExpense(expense: MonthlyExpenseEntity)
    
    @Update
    suspend fun updateMonthlyExpense(expense: MonthlyExpenseEntity)
    
    @Delete
    suspend fun deleteMonthlyExpense(expense: MonthlyExpenseEntity)
    
    @Query("DELETE FROM monthly_expenses WHERE id = :id")
    suspend fun deleteMonthlyExpenseById(id: String)
    
    // Balance History
    @Query("SELECT * FROM balance_history ORDER BY date DESC")
    fun getBalanceHistory(): Flow<List<BalanceEntity>>
    
    @Query("SELECT * FROM balance_history WHERE period = :period ORDER BY date DESC")
    fun getBalanceHistoryByPeriod(period: String): Flow<List<BalanceEntity>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBalance(balance: BalanceEntity)
    
    @Query("SELECT * FROM balance_history ORDER BY date DESC LIMIT 1")
    suspend fun getLatestBalance(): BalanceEntity?
}
