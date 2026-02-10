package com.example.fintrax.domain.repository

import com.example.fintrax.domain.model.Transaction
import com.example.fintrax.domain.model.TransactionCategory
import com.example.fintrax.domain.model.TransactionType
import kotlinx.coroutines.flow.Flow

/**
 * Interfaz de repositorio para operaciones de gestión de transacciones financieras.
 * 
 * Este contrato define la especificación abstracta para todas las operaciones
 * de acceso y manipulación de datos de transacciones, siguiendo el principio
 * de inversión de dependencias de Clean Architecture. Las implementaciones
 * concretas se encuentran en la capa de datos.
 * 
 * La interfaz utiliza Kotlin Flow para proporcionar streams reactivos
 * que permiten la observación de cambios en tiempo real de los datos,
 * facilitando una UI responsiva y actualizada automáticamente.
 * 
 * @author Fintrax Development Team
 * @version 1.0
 * @since 1.0
 */
interface TransactionRepository {
    
    /**
     * Inserta una nueva transacción en el sistema de persistencia.
     * 
     * @param transaction Instancia de Transaction a persistir
     * @throws IllegalArgumentException si la transacción es inválida
     * @throws DataAccessException si ocurre un error de persistencia
     */
    suspend fun insertTransaction(transaction: Transaction)
    
    /**
     * Actualiza los datos de una transacción existente.
     * 
     * @param transaction Instancia de Transaction con datos actualizados
     * @throws IllegalArgumentException si la transacción no existe o es inválida
     * @throws DataAccessException si ocurre un error de actualización
     */
    suspend fun updateTransaction(transaction: Transaction)
    
    /**
     * Elimina una transacción del sistema mediante su identificador único.
     * 
     * @param transactionId Identificador UUID de la transacción a eliminar
     * @throws IllegalArgumentException si el ID es nulo o vacío
     * @throws DataAccessException si ocurre un error durante la eliminación
     */
    suspend fun deleteTransaction(transactionId: String)
    
    /**
     * Recupera una transacción específica mediante su identificador único.
     * 
     * @param transactionId Identificador UUID de la transacción buscada
     * @return Instancia de Transaction si existe, null en caso contrario
     * @throws IllegalArgumentException si el ID es nulo o vacío
     */
    suspend fun getTransactionById(transactionId: String): Transaction?
    
    /**
     * Obtiene un stream reactivo de todas las transacciones del sistema.
     * 
     * @return Flow que emite listas actualizadas de Transaction
     * @throws DataAccessException si ocurre un error de consulta
     */
    fun getAllTransactions(): Flow<List<Transaction>>
    
    /**
     * Filtra transacciones por tipo (ingreso/egreso) mediante stream reactivo.
     * 
     * @param type Tipo de transacción a filtrar (INCOME/EXPENSE)
     * @return Flow que emite listas filtradas de Transaction
     * @throws IllegalArgumentException si el tipo es nulo
     * @throws DataAccessException si ocurre un error de consulta
     */
    fun getTransactionsByType(type: TransactionType): Flow<List<Transaction>>
    
    /**
     * Filtra transacciones por categoría taxonómica mediante stream reactivo.
     * 
     * @param category Categoría de transacción a filtrar
     * @return Flow que emite listas filtradas de Transaction
     * @throws IllegalArgumentException si la categoría es nula
     * @throws DataAccessException si ocurre un error de consulta
     */
    fun getTransactionsByCategory(category: TransactionCategory): Flow<List<Transaction>>
    
    /**
     * Recupera transacciones dentro de un rango de fechas específico.
     * 
     * @param startDate Timestamp de inicio del rango en milisegundos
     * @param endDate Timestamp de fin del rango en milisegundos
     * @return Flow que emite transacciones dentro del rango temporal
     * @throws IllegalArgumentException si las fechas son inválidas
     * @throws DataAccessException si ocurre un error de consulta
     */
    fun getTransactionsByDateRange(startDate: Long, endDate: Long): Flow<List<Transaction>>
    
    /**
     * Obtiene el conteo total de transacciones almacenadas en el sistema.
     * 
     * @return Número entero representando el total de transacciones
     * @throws DataAccessException si ocurre un error durante el conteo
     */
    suspend fun getTransactionCount(): Int
}
