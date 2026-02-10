package com.example.fintrax.presentation.screens.income

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

/**
 * Income Screen - Shows list of income transactions
 * Focused view for income management
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IncomeScreen(
    onNavigateBack: () -> Unit,
    onNavigateToAddIncome: () -> Unit,
    viewModel: TransactionViewModel = hiltViewModel()
) {
    val transactions by viewModel.transactions.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()
    
    val incomeTransactions = remember(transactions) {
        transactions.filter { it.type == com.example.fintrax.domain.model.TransactionType.INCOME }
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
                title = { Text("Income") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Text("←")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onNavigateToAddIncome
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
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {
                // Summary Card
                IncomeSummaryCard(incomeTransactions)
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Income List
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(incomeTransactions) { transaction ->
                        IncomeTransactionItem(transaction = transaction)
                    }
                }
            }
        }
    }
}

@Composable
private fun IncomeSummaryCard(incomeTransactions: List<com.example.fintrax.domain.model.Transaction>) {
    val totalIncome = remember(incomeTransactions) {
        incomeTransactions.sumOf { it.amount.toDouble() }
    }
    
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Total Income",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "$$totalIncome",
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = "${incomeTransactions.size} transactions",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Composable
private fun IncomeTransactionItem(transaction: com.example.fintrax.domain.model.Transaction) {
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
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "INCOME",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}
