package com.example.fintrax.di

import com.example.fintrax.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt module for use case dependencies
 * Provides all use cases for the application
 */
@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    
    // Transaction Use Cases
    @Provides
    @Singleton
    fun provideAddTransactionUseCase(
        repository: com.example.fintrax.domain.repository.TransactionRepository
    ): AddTransactionUseCase {
        return AddTransactionUseCase(repository)
    }
    
    @Provides
    @Singleton
    fun provideGetAllTransactionsUseCase(
        repository: com.example.fintrax.domain.repository.TransactionRepository
    ): GetAllTransactionsUseCase {
        return GetAllTransactionsUseCase(repository)
    }
    
    @Provides
    @Singleton
    fun provideDeleteTransactionUseCase(
        repository: com.example.fintrax.domain.repository.TransactionRepository
    ): DeleteTransactionUseCase {
        return DeleteTransactionUseCase(repository)
    }
    
    @Provides
    @Singleton
    fun provideUpdateTransactionUseCase(
        repository: com.example.fintrax.domain.repository.TransactionRepository
    ): UpdateTransactionUseCase {
        return UpdateTransactionUseCase(repository)
    }
    
    // Financial Use Cases
    @Provides
    @Singleton
    fun provideAddMonthlyExpenseUseCase(
        repository: com.example.fintrax.domain.repository.FinancialRepository
    ): AddMonthlyExpenseUseCase {
        return AddMonthlyExpenseUseCase(repository)
    }
    
    @Provides
    @Singleton
    fun provideGetCurrentBalanceUseCase(
        repository: com.example.fintrax.domain.repository.FinancialRepository
    ): GetCurrentBalanceUseCase {
        return GetCurrentBalanceUseCase(repository)
    }
    
    @Provides
    @Singleton
    fun provideGetFinancialSummaryUseCase(
        repository: com.example.fintrax.domain.repository.FinancialRepository
    ): GetFinancialSummaryUseCase {
        return GetFinancialSummaryUseCase(repository)
    }
    
    @Provides
    @Singleton
    fun provideGetMonthlyExpensesUseCase(
        repository: com.example.fintrax.domain.repository.FinancialRepository
    ): GetMonthlyExpensesUseCase {
        return GetMonthlyExpensesUseCase(repository)
    }
    
    // Analytics Use Cases
    @Provides
    @Singleton
    fun provideGetExpenseChartUseCase(
        repository: com.example.fintrax.domain.repository.AnalyticsRepository
    ): GetExpenseChartUseCase {
        return GetExpenseChartUseCase(repository)
    }
    
    @Provides
    @Singleton
    fun provideGetTrendDataUseCase(
        repository: com.example.fintrax.domain.repository.AnalyticsRepository
    ): GetTrendDataUseCase {
        return GetTrendDataUseCase(repository)
    }
    
    @Provides
    @Singleton
    fun provideGetCategoryBreakdownUseCase(
        repository: com.example.fintrax.domain.repository.AnalyticsRepository
    ): GetCategoryBreakdownUseCase {
        return GetCategoryBreakdownUseCase(repository)
    }
}
