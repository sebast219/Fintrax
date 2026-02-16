package com.example.fintrax.presentation.screens.reports

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fintrax.presentation.viewmodels.AnalyticsViewModel

/**
 * Reports Screen - Shows financial analytics and reports
 * Charts, trends, and category breakdowns
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReportsScreen(
    onNavigateBack: () -> Unit,
    onNavigateToExpenseReport: () -> Unit,
    onNavigateToIncomeReport: () -> Unit,
    onNavigateToBalanceReport: () -> Unit,
    onNavigateToCategoryReport: () -> Unit,
    viewModel: AnalyticsViewModel = hiltViewModel()
) {
    val expenseChartData by viewModel.expenseChartData.collectAsState()
    val trendData by viewModel.trendData.collectAsState()
    val categoryBreakdown by viewModel.categoryBreakdown.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()
    
    LaunchedEffect(Unit) {
        viewModel.refreshAllData()
    }
    
    LaunchedEffect(errorMessage) {
        errorMessage?.let {
            // Handle error display
            viewModel.clearError()
        }
    }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Reports") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Text("←")
                    }
                }
            )
        }
    ) { paddingValues ->
        if (isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    ReportOverviewCard()
                }
                
                item {
                    Text(
                        text = "Quick Reports",
                        style = MaterialTheme.typography.headlineSmall
                    )
                }
                
                item {
                    QuickReportsGrid(
                        onNavigateToExpenseReport = onNavigateToExpenseReport,
                        onNavigateToIncomeReport = onNavigateToIncomeReport,
                        onNavigateToBalanceReport = onNavigateToBalanceReport,
                        onNavigateToCategoryReport = onNavigateToCategoryReport
                    )
                }
                
                item {
                    Text(
                        text = "Expense by Category",
                        style = MaterialTheme.typography.headlineSmall
                    )
                }
                
                item {
                    ExpenseChartPreview(chartData = expenseChartData)
                }
                
                item {
                    Text(
                        text = "Monthly Trend",
                        style = MaterialTheme.typography.headlineSmall
                    )
                }
                
                item {
                    TrendChartPreview(trendData = trendData)
                }
                
                item {
                    Text(
                        text = "Category Breakdown",
                        style = MaterialTheme.typography.headlineSmall
                    )
                }
                
                item {
                    CategoryBreakdownPreview(breakdown = categoryBreakdown)
                }
            }
        }
    }
}

@Composable
private fun ReportOverviewCard() {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Financial Reports",
                style = MaterialTheme.typography.headlineSmall
            )
            Text(
                text = "View detailed analytics, trends, and insights about your financial data",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
private fun QuickReportsGrid(
    onNavigateToExpenseReport: () -> Unit,
    onNavigateToIncomeReport: () -> Unit,
    onNavigateToBalanceReport: () -> Unit,
    onNavigateToCategoryReport: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Card(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                onClick = onNavigateToExpenseReport
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("💸")
                    Text("Expense Report")
                }
            }
            
            Card(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                onClick = onNavigateToIncomeReport
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("💰")
                    Text("Income Report")
                }
            }
        }
        
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Card(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                onClick = onNavigateToBalanceReport
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("⚖️")
                    Text("Balance Report")
                }
            }
            
            Card(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                onClick = onNavigateToCategoryReport
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("📊")
                    Text("Category Report")
                }
            }
        }
    }
}

@Composable
private fun ExpenseChartPreview(chartData: List<com.example.fintrax.domain.model.ChartData>) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Expense Distribution",
                style = MaterialTheme.typography.titleMedium
            )
            // Chart implementation will go here
            Text(
                text = "Chart will be implemented here",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Composable
private fun TrendChartPreview(trendData: List<com.example.fintrax.domain.model.TrendData>) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Income vs Expenses Trend",
                style = MaterialTheme.typography.titleMedium
            )
            // Chart implementation will go here
            Text(
                text = "Trend chart will be implemented here",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Composable
private fun CategoryBreakdownPreview(breakdown: List<com.example.fintrax.domain.model.CategoryBreakdown>) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Top Categories",
                style = MaterialTheme.typography.titleMedium
            )
            // Category breakdown implementation will go here
            Text(
                text = "Category breakdown will be implemented here",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}
