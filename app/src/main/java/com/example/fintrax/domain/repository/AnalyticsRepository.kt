package com.example.fintrax.domain.repository

import com.example.fintrax.domain.model.ChartData
import com.example.fintrax.domain.model.TrendData
import com.example.fintrax.domain.model.CategoryBreakdown
import com.example.fintrax.domain.model.TransactionCategory
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for analytics and reporting operations
 * Handles chart data, trends, and category breakdowns
 */
interface AnalyticsRepository {
    // Chart Data
    suspend fun getExpenseByCategoryChart(period: String): List<ChartData>
    suspend fun getIncomeByCategoryChart(period: String): List<ChartData>
    suspend fun getMonthlyTrendChart(months: Int): List<TrendData>
    
    // Category Analysis
    suspend fun getCategoryBreakdown(period: String): List<CategoryBreakdown>
    suspend fun getTopCategories(limit: Int): List<CategoryBreakdown>
    
    // Trend Analysis
    fun getIncomeTrend(period: String): Flow<List<TrendData>>
    fun getExpenseTrend(period: String): Flow<List<TrendData>>
    suspend fun getYearOverYearComparison(): Map<String, Float>
}
