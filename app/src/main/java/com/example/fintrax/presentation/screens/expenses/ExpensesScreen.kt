package com.example.fintrax.presentation.screens.expenses

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fintrax.presentation.viewmodels.TransactionViewModel
import com.example.fintrax.presentation.viewmodels.DashboardViewModel

/**
 * Expenses Screen - Shows expense transactions and monthly expenses
 * Comprehensive expense management view
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpensesScreen(
    onNavigateBack: () -> Unit,
    onNavigateToAddExpense: () -> Unit,
    onNavigateToMonthlyExpenses: () -> Unit,
    transactionViewModel: TransactionViewModel = hiltViewModel(),
    dashboardViewModel: DashboardViewModel = hiltViewModel()
) {
    val transactions by transactionViewModel.transactions.collectAsState()
    val isLoading by transactionViewModel.isLoading.collectAsState()
    val errorMessage by transactionViewModel.errorMessage.collectAsState()
    val monthlyExpenses by dashboardViewModel.monthlyExpenses.collectAsState()
    
    val expenseTransactions = remember(transactions) {
        transactions.filter { it.type == com.example.fintrax.domain.model.TransactionType.EXPENSE }
    }
    
    LaunchedEffect(errorMessage) {
        errorMessage?.let {
            // Handle error display
            transactionViewModel.clearError()
        }
    }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Expenses") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Text("←")
                    }
                },
                actions = {
                    IconButton(onClick = onNavigateToMonthlyExpenses) {
                        Text("📅")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onNavigateToAddExpense
            ) {
                Text("+")
            }
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
                    ExpenseSummaryCard(expenseTransactions, monthlyExpenses)
                }
                
                item {
                    Text(
                        text = "Recent Expenses",
                        style = MaterialTheme.typography.headlineSmall
                    )
                }
                
                items(expenseTransactions.take(10)) { transaction ->
                    ExpenseTransactionItem(transaction = transaction)
                }
                
                item {
                    Text(
                        text = "Monthly Recurring",
                        style = MaterialTheme.typography.headlineSmall
                    )
                }
                
                items(monthlyExpenses.take(5)) { expense ->
                    MonthlyExpenseItem(expense = expense)
                }
            }
        }
    }
}

@Composable
private fun ExpenseSummaryCard(
    expenseTransactions: List<com.example.fintrax.domain.model.Transaction>,
    monthlyExpenses: List<com.example.fintrax.domain.model.MonthlyExpense>
) {
    val totalExpenses = remember(expenseTransactions) {
        expenseTransactions.sumOf { it.amount.toDouble() }
    }
    
    val totalMonthlyExpenses = remember(monthlyExpenses) {
        monthlyExpenses.filter { it.isActive }.sumOf { it.amount.toDouble() }
    }
    
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Expense Summary",
                style = MaterialTheme.typography.titleMedium
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text("This Month")
                    Text(
                        text = "$$totalExpenses",
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.error
                    )
                }
                Column(
                    horizontalAlignment = Alignment.End
                ) {
                    Text("Recurring")
                    Text(
                        text = "$$totalMonthlyExpenses",
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
    }
}

@Composable
private fun ExpenseTransactionItem(transaction: com.example.fintrax.domain.model.Transaction) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = transaction.description,
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = transaction.category.name,
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    text = transaction.date.toString(),
                    style = MaterialTheme.typography.bodySmall
                )
            }
            
            Column(
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = "$${transaction.amount}",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.error
                )
                Text(
                    text = "EXPENSE",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@Composable
private fun MonthlyExpenseItem(expense: com.example.fintrax.domain.model.MonthlyExpense) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = expense.description,
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = "${expense.category.name} • Due: ${expense.dueDate}",
                    style = MaterialTheme.typography.bodySmall
                )
            }
            
            Column(
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = "$${expense.amount}",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.error
                )
                Text(
                    text = if (expense.isActive) "ACTIVE" else "INACTIVE",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}
