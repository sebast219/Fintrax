package com.example.fintrax.data.local.database

import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.fintrax.data.local.dao.TransactionDao
import com.example.fintrax.data.local.dao.FinancialDao
import com.example.fintrax.data.local.entity.TransactionEntity
import com.example.fintrax.data.local.entity.MonthlyExpenseEntity
import com.example.fintrax.data.local.entity.BalanceEntity

/**
 * Room database for Fintrax application
 * Defines database configuration and entities
 */
@Database(
    entities = [
        TransactionEntity::class,
        MonthlyExpenseEntity::class,
        BalanceEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class FintraxDatabase : RoomDatabase() {
    
    abstract fun transactionDao(): TransactionDao
    abstract fun financialDao(): FinancialDao
    
    /**
     * Callback for database creation and opening
     */
    abstract class AppDatabaseCallback : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            // Pre-populate database with initial data if needed
        }
        
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            // Handle database opening logic
        }
    }
}

/**
 * Type converters for Room database
 * Handles conversion of complex types to database-compatible formats
 */
class Converters {
    // Converters for enums, BigDecimal, etc. will be implemented here
}
