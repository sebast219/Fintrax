package com.example.fintrax.data.repository

import com.example.fintrax.domain.model.Transaction
import com.example.fintrax.domain.model.TransactionCategory
import com.example.fintrax.domain.model.TransactionType
import com.example.fintrax.domain.repository.TransactionRepository
import com.example.fintrax.data.local.dao.TransactionDao
import com.example.fintrax.data.mapper.TransactionMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Implementation of TransactionRepository
 * Bridges domain layer with data sources
 */
class TransactionRepositoryImpl(
    private val transactionDao: TransactionDao,
    private val mapper: TransactionMapper
) : TransactionRepository {
    
    override suspend fun insertTransaction(transaction: Transaction) {
        val entity = mapper.toEntity(transaction)
        transactionDao.insertTransaction(entity)
    }
    
    override suspend fun updateTransaction(transaction: Transaction) {
        val entity = mapper.toEntity(transaction)
        transactionDao.updateTransaction(entity)
    }
    
    override suspend fun deleteTransaction(transactionId: String) {
        transactionDao.deleteTransactionById(transactionId)
    }
    
    override suspend fun getTransactionById(transactionId: String): Transaction? {
        val entity = transactionDao.getTransactionById(transactionId)
        return entity?.let { mapper.toDomain(it) }
    }
    
    override fun getAllTransactions(): Flow<List<Transaction>> {
        return transactionDao.getAllTransactions().map { entities ->
            entities.map { mapper.toDomain(it) }
        }
    }
    
    override fun getTransactionsByType(type: TransactionType): Flow<List<Transaction>> {
        return transactionDao.getTransactionsByType(type).map { entities ->
            entities.map { mapper.toDomain(it) }
        }
    }
    
    override fun getTransactionsByCategory(category: TransactionCategory): Flow<List<Transaction>> {
        return transactionDao.getTransactionsByCategory(category).map { entities ->
            entities.map { mapper.toDomain(it) }
        }
    }
    
    override fun getTransactionsByDateRange(startDate: Long, endDate: Long): Flow<List<Transaction>> {
        return transactionDao.getTransactionsByDateRange(startDate, endDate).map { entities ->
            entities.map { mapper.toDomain(it) }
        }
    }
    
    override suspend fun getTransactionCount(): Int {
        return transactionDao.getTransactionCount()
    }
}
