package com.example.fintrax.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.fintrax.domain.model.TransactionCategory
import java.math.BigDecimal
import java.util.Date

/**
 * Room entity representing monthly expenses
 */
@Entity(tableName = "monthly_expenses")
data class MonthlyExpenseEntity(
    @PrimaryKey
    val id: String,
    val amount: BigDecimal,
    val description: String,
    val category: TransactionCategory,
    val dueDate: Int, // Day of month (1-31)
    val isActive: Boolean = true,
    val createdDate: Long,
    val lastPaidDate: Long? = null,
    val created_at: Long = System.currentTimeMillis(),
    val updated_at: Long = System.currentTimeMillis()
)

/**
 * Room entity representing balance history
 */
@Entity(tableName = "balance_history")
data class BalanceEntity(
    @PrimaryKey
    val id: String,
    val totalIncome: BigDecimal,
    val totalExpenses: BigDecimal,
    val netBalance: BigDecimal,
    val period: String,
    val date: Long,
    val created_at: Long = System.currentTimeMillis()
)
