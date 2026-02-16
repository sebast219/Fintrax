package com.example.fintrax.domain.usecase

import com.example.fintrax.domain.model.ChartData
import com.example.fintrax.domain.model.TrendData
import com.example.fintrax.domain.model.CategoryBreakdown
import com.example.fintrax.domain.repository.AnalyticsRepository

/**
 * Use case for getting expense chart data
 */
class GetExpenseChartUseCase(
    private val repository: AnalyticsRepository
) {
    suspend operator fun invoke(period: String): List<ChartData> {
        return repository.getExpenseByCategoryChart(period)
    }
}

/**
 * Use case for getting trend data
 */
class GetTrendDataUseCase(
    private val repository: AnalyticsRepository
) {
    suspend operator fun invoke(months: Int): List<TrendData> {
        return repository.getMonthlyTrendChart(months)
    }
}

/**
 * Use case for getting category breakdown
 */
class GetCategoryBreakdownUseCase(
    private val repository: AnalyticsRepository
) {
    suspend operator fun invoke(period: String): List<CategoryBreakdown> {
        return repository.getCategoryBreakdown(period)
    }
}
