package com.example.fintrax.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fintrax.domain.model.ChartData
import com.example.fintrax.domain.model.TrendData
import com.example.fintrax.domain.model.CategoryBreakdown
import com.example.fintrax.domain.usecase.GetExpenseChartUseCase
import com.example.fintrax.domain.usecase.GetTrendDataUseCase
import com.example.fintrax.domain.usecase.GetCategoryBreakdownUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel for analytics and reporting
 * Manages chart data, trends, and category breakdowns
 */
class AnalyticsViewModel(
    private val getExpenseChartUseCase: GetExpenseChartUseCase,
    private val getTrendDataUseCase: GetTrendDataUseCase,
    private val getCategoryBreakdownUseCase: GetCategoryBreakdownUseCase
) : ViewModel() {
    
    private val _expenseChartData = MutableStateFlow<List<ChartData>>(emptyList())
    val expenseChartData: StateFlow<List<ChartData>> = _expenseChartData.asStateFlow()
    
    private val _trendData = MutableStateFlow<List<TrendData>>(emptyList())
    val trendData: StateFlow<List<TrendData>> = _trendData.asStateFlow()
    
    private val _categoryBreakdown = MutableStateFlow<List<CategoryBreakdown>>(emptyList())
    val categoryBreakdown: StateFlow<List<CategoryBreakdown>> = _categoryBreakdown.asStateFlow()
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()
    
    fun loadExpenseChartData(period: String = "monthly") {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                _expenseChartData.value = getExpenseChartUseCase(period)
                _isLoading.value = false
            } catch (e: Exception) {
                _errorMessage.value = "Error loading expense chart data: ${e.message}"
                _isLoading.value = false
            }
        }
    }
    
    fun loadTrendData(months: Int = 6) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                _trendData.value = getTrendDataUseCase(months)
                _isLoading.value = false
            } catch (e: Exception) {
                _errorMessage.value = "Error loading trend data: ${e.message}"
                _isLoading.value = false
            }
        }
    }
    
    fun loadCategoryBreakdown(period: String = "monthly") {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                _categoryBreakdown.value = getCategoryBreakdownUseCase(period)
                _isLoading.value = false
            } catch (e: Exception) {
                _errorMessage.value = "Error loading category breakdown: ${e.message}"
                _isLoading.value = false
            }
        }
    }
    
    fun refreshAllData(period: String = "monthly", months: Int = 6) {
        loadExpenseChartData(period)
        loadTrendData(months)
        loadCategoryBreakdown(period)
    }
    
    fun clearError() {
        _errorMessage.value = null
    }
}
