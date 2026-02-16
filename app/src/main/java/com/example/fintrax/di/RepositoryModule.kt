package com.example.fintrax.di

import com.example.fintrax.domain.repository.TransactionRepository
import com.example.fintrax.domain.repository.FinancialRepository
import com.example.fintrax.domain.repository.AnalyticsRepository
import com.example.fintrax.data.repository.TransactionRepositoryImpl
import com.example.fintrax.data.repository.FinancialRepositoryImpl
import com.example.fintrax.data.repository.AnalyticsRepositoryImpl
import com.example.fintrax.data.mapper.TransactionMapper
import com.example.fintrax.data.mapper.FinancialMapper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt module for repository bindings
 * Binds interfaces to their implementations
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    
    @Binds
    @Singleton
    abstract fun bindTransactionRepository(
        transactionRepositoryImpl: TransactionRepositoryImpl
    ): TransactionRepository
    
    @Binds
    @Singleton
    abstract fun bindFinancialRepository(
        financialRepositoryImpl: FinancialRepositoryImpl
    ): FinancialRepository
    
    @Binds
    @Singleton
    abstract fun bindAnalyticsRepository(
        analyticsRepositoryImpl: AnalyticsRepositoryImpl
    ): AnalyticsRepository
}

/**
 * Hilt module for mapper bindings
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class MapperModule {
    
    @Binds
    abstract fun bindTransactionMapper(
        transactionMapper: TransactionMapper
    ): TransactionMapper
    
    @Binds
    abstract fun bindFinancialMapper(
        financialMapper: FinancialMapper
    ): FinancialMapper
}
