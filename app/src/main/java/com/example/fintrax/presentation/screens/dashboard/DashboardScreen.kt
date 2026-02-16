package com.example.fintrax.presentation.screens.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fintrax.domain.model.Balance
import com.example.fintrax.domain.model.FinancialSummary
import com.example.fintrax.presentation.viewmodels.DashboardViewModel

/**
 * Pantalla principal del Dashboard - Vista panorámica financiera del usuario.
 * 
 * Esta Composable function representa la pantalla principal de la aplicación,
 * proporcionando una vista consolidada de la situación financiera actual
 * incluyendo balance, resumen mensual y acciones rápidas de navegación.
 * 
 * Implementa el patrón MVVM donde la pantalla observa el estado del ViewModel
 * mediante StateFlows, actualizándose automáticamente cuando los datos cambian.
 * Utiliza Jetpack Compose para una UI declarativa y reactiva.
 * 
 * @param onNavigateToTransactions Callback para navegación a pantalla de transacciones
 * @param onNavigateToIncome Callback para navegación a pantalla de ingresos
 * @param onNavigateToExpenses Callback para navegación a pantalla de gastos
 * @param onNavigateToReports Callback para navegación a pantalla de informes
 * @param onNavigateToSettings Callback para navegación a pantalla de configuración
 * @param viewModel ViewModel inyectado con Hilt para gestión de estado y lógica
 * 
 * @author Fintrax Development Team
 * @version 1.0
 * @since 1.0
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    onNavigateToTransactions: () -> Unit,
    onNavigateToIncome: () -> Unit,
    onNavigateToExpenses: () -> Unit,
    onNavigateToReports: () -> Unit,
    onNavigateToSettings: () -> Unit,
    viewModel: DashboardViewModel = hiltViewModel()
) {
    // Observación reactiva del estado del ViewModel
    val balance by viewModel.balance.collectAsState()
    val financialSummary by viewModel.financialSummary.collectAsState()
    val monthlyExpenses by viewModel.monthlyExpenses.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()
    
    // Efecto colateral para manejo de errores
    LaunchedEffect(errorMessage) {
        errorMessage?.let {
            // Manejar display de errores (ej: Snackbar)
            viewModel.clearError()
        }
    }
    
    // Estructura principal de la pantalla usando Scaffold
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Fintrax") },
                actions = {
                    IconButton(onClick = onNavigateToSettings) {
                        // Ícono de configuración
                        Text("⚙️")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* Add quick action */ }
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
                    BalanceCard(balance = balance)
                }
                
                item {
                    FinancialSummaryCard(summary = financialSummary)
                }
                
                item {
                    QuickActionsSection(
                        onNavigateToTransactions = onNavigateToTransactions,
                        onNavigateToIncome = onNavigateToIncome,
                        onNavigateToExpenses = onNavigateToExpenses,
                        onNavigateToReports = onNavigateToReports
                    )
                }
                
                item {
                    Text(
                        text = "Monthly Expenses",
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
private fun BalanceCard(balance: Balance?) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Current Balance",
                style = MaterialTheme.typography.titleMedium
            )
            balance?.let {
                Text(
                    text = "$${it.netBalance}",
                    style = MaterialTheme.typography.headlineLarge
                )
            } ?: Text("Loading...")
        }
    }
}

@Composable
private fun FinancialSummaryCard(summary: FinancialSummary?) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Monthly Summary",
                style = MaterialTheme.typography.titleMedium
            )
            summary?.let {
                Text("Income: $${it.totalIncome}")
                Text("Expenses: $${it.totalExpenses}")
                Text("Net: $${it.netBalance}")
            } ?: Text("Loading...")
        }
    }
}

@Composable
private fun QuickActionsSection(
    onNavigateToTransactions: () -> Unit,
    onNavigateToIncome: () -> Unit,
    onNavigateToExpenses: () -> Unit,
    onNavigateToReports: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(onClick = onNavigateToTransactions) {
            Text("Transactions")
        }
        Button(onClick = onNavigateToIncome) {
            Text("Income")
        }
        Button(onClick = onNavigateToExpenses) {
            Text("Expenses")
        }
        Button(onClick = onNavigateToReports) {
            Text("Reports")
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
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(expense.description)
                Text(expense.category.name)
            }
            Text("$${expense.amount}")
        }
    }
}
