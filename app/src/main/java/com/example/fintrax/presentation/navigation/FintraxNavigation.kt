package com.example.fintrax.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fintrax.presentation.screens.dashboard.DashboardScreen
import com.example.fintrax.presentation.screens.transactions.TransactionsScreen
import com.example.fintrax.presentation.screens.income.IncomeScreen
import com.example.fintrax.presentation.screens.expenses.ExpensesScreen
import com.example.fintrax.presentation.screens.reports.ReportsScreen
import com.example.fintrax.presentation.screens.settings.SettingsScreen

/**
 * Main navigation setup for Fintrax app
 * Configures navigation graph and screen routing
 */
@Composable
fun FintraxNavigation(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Dashboard.route
    ) {
        // Main screens
        composable(Screen.Dashboard.route) {
            DashboardScreen(
                onNavigateToTransactions = { 
                    navController.navigate(Screen.Transactions.route) 
                },
                onNavigateToIncome = { 
                    navController.navigate(Screen.Income.route) 
                },
                onNavigateToExpenses = { 
                    navController.navigate(Screen.Expenses.route) 
                },
                onNavigateToReports = { 
                    navController.navigate(Screen.Reports.route) 
                },
                onNavigateToSettings = { 
                    navController.navigate(Screen.Settings.route) 
                }
            )
        }
        
        composable(Screen.Transactions.route) {
            TransactionsScreen(
                onNavigateBack = { navController.popBackStack() },
                onNavigateToAddTransaction = { 
                    navController.navigate(Screen.AddTransaction.route) 
                },
                onNavigateToTransactionDetail = { transactionId ->
                    navController.navigate(Screen.TransactionDetail.createRoute(transactionId))
                }
            )
        }
        
        composable(Screen.Income.route) {
            IncomeScreen(
                onNavigateBack = { navController.popBackStack() },
                onNavigateToAddIncome = { 
                    navController.navigate(Screen.AddTransaction.route) 
                }
            )
        }
        
        composable(Screen.Expenses.route) {
            ExpensesScreen(
                onNavigateBack = { navController.popBackStack() },
                onNavigateToAddExpense = { 
                    navController.navigate(Screen.AddTransaction.route) 
                },
                onNavigateToMonthlyExpenses = { 
                    navController.navigate(Screen.MonthlyExpenses.route) 
                }
            )
        }
        
        composable(Screen.Reports.route) {
            ReportsScreen(
                onNavigateBack = { navController.popBackStack() },
                onNavigateToExpenseReport = { 
                    navController.navigate(Screen.ExpenseReport.route) 
                },
                onNavigateToIncomeReport = { 
                    navController.navigate(Screen.IncomeReport.route) 
                },
                onNavigateToBalanceReport = { 
                    navController.navigate(Screen.BalanceReport.route) 
                },
                onNavigateToCategoryReport = { 
                    navController.navigate(Screen.CategoryReport.route) 
                }
            )
        }
        
        composable(Screen.Settings.route) {
            SettingsScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }
        
        // Detail screens will be added here
        // composable(Screen.TransactionDetail.route) { ... }
        // composable(Screen.AddTransaction.route) { ... }
        // etc.
    }
}
