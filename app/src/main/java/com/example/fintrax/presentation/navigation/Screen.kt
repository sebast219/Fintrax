package com.example.fintrax.presentation.navigation

/**
 * Navigation destinations for the Fintrax app
 * Defines all screens and their routes
 */
sealed class Screen(val route: String) {
    
    // Main screens
    object Dashboard : Screen("dashboard")
    object Transactions : Screen("transactions")
    object Income : Screen("income")
    object Expenses : Screen("expenses")
    object Reports : Screen("reports")
    object Settings : Screen("settings")
    
    // Detail screens
    object TransactionDetail : Screen("transaction_detail/{transactionId}") {
        fun createRoute(transactionId: String) = "transaction_detail/$transactionId"
    }
    
    object AddTransaction : Screen("add_transaction")
    object EditTransaction : Screen("edit_transaction/{transactionId}") {
        fun createRoute(transactionId: String) = "edit_transaction/$transactionId"
    }
    
    object MonthlyExpenses : Screen("monthly_expenses")
    object AddMonthlyExpense : Screen("add_monthly_expense")
    object EditMonthlyExpense : Screen("edit_monthly_expense/{expenseId}") {
        fun createRoute(expenseId: String) = "edit_monthly_expense/$expenseId"
    }
    
    // Report screens
    object ExpenseReport : Screen("expense_report")
    object IncomeReport : Screen("income_report")
    object BalanceReport : Screen("balance_report")
    object CategoryReport : Screen("category_report")
}

/**
 * Navigation arguments
 */
object NavArgs {
    const val TRANSACTION_ID = "transactionId"
    const val EXPENSE_ID = "expenseId"
}
