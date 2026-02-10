package com.example.fintrax.data.mapper

import com.example.fintrax.domain.model.Transaction
import com.example.fintrax.data.local.entity.TransactionEntity
import java.util.Date

/**
 * Mapper for converting between Transaction domain models and TransactionEntity database models
 */
class TransactionMapper {
    
    fun toEntity(transaction: Transaction): TransactionEntity {
        return TransactionEntity(
            id = transaction.id,
            amount = transaction.amount,
            description = transaction.description,
            category = transaction.category,
            type = transaction.type,
            date = transaction.date.time,
            isRecurring = transaction.isRecurring,
            recurringPeriod = transaction.recurringPeriod
        )
    }
    
    fun toDomain(entity: TransactionEntity): Transaction {
        return Transaction(
            id = entity.id,
            amount = entity.amount,
            description = entity.description,
            category = entity.category,
            type = entity.type,
            date = Date(entity.date),
            isRecurring = entity.isRecurring,
            recurringPeriod = entity.recurringPeriod
        )
    }
    
    fun toEntityList(transactions: List<Transaction>): List<TransactionEntity> {
        return transactions.map { toEntity(it) }
    }
    
    fun toDomainList(entities: List<TransactionEntity>): List<Transaction> {
        return entities.map { toDomain(it) }
    }
}
