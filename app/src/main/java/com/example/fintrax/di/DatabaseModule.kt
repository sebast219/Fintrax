package com.example.fintrax.di

import android.content.Context
import androidx.room.Room
import com.example.fintrax.data.local.database.FintraxDatabase
import com.example.fintrax.data.local.dao.TransactionDao
import com.example.fintrax.data.local.dao.FinancialDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt module for database dependencies
 * Provides database instance and DAOs
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    
    @Provides
    @Singleton
    fun provideFintraxDatabase(
        @ApplicationContext context: Context
    ): FintraxDatabase {
        return Room.databaseBuilder(
            context,
            FintraxDatabase::class.java,
            "fintrax_database"
        ).build()
    }
    
    @Provides
    fun provideTransactionDao(database: FintraxDatabase): TransactionDao {
        return database.transactionDao()
    }
    
    @Provides
    fun provideFinancialDao(database: FintraxDatabase): FinancialDao {
        return database.financialDao()
    }
}
