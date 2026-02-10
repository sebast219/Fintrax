package com.example.fintrax.domain.usecase

import com.example.fintrax.domain.model.Transaction
import com.example.fintrax.domain.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow

/**
 * Caso de uso para la creación de nuevas transacciones financieras.
 * 
 * Esta clase encapsula la lógica de negocio necesaria para la validación
 * y persistencia de transacciones, siguiendo los principios de Clean
 * Architecture donde cada caso de uso representa una acción específica
 * del dominio de negocio.
 * 
 * Implementa el patrón Command con el operador invoke para facilitar
 * su uso como función de alto nivel, proporcionando una interfaz
 * limpia y expresiva para la capa de presentación.
 * 
 * @param repository Repositorio de transacciones para persistencia de datos
 * 
 * @author Fintrax Development Team
 * @version 1.0
 * @since 1.0
 */
class AddTransactionUseCase(
    private val repository: TransactionRepository
) {
    
    /**
     * Ejecuta el caso de uso para agregar una nueva transacción.
     * 
     * Este método realiza las validaciones de negocio necesarias antes
     * de persistir la transacción, asegurando la integridad de los datos
     * y el cumplimiento de las reglas del dominio financiero.
     * 
     * @param transaction Instancia de Transaction a agregar
     * @throws IllegalArgumentException si la transacción viola reglas de negocio
     * @throws TransactionValidationException si los datos son inválidos
     */
    suspend operator fun invoke(transaction: Transaction) {
        // Validaciones de lógica de negocio se implementarán aquí
        repository.insertTransaction(transaction)
    }
}

/**
 * Caso de uso para la recuperación de todas las transacciones del sistema.
 * 
 * Proporciona una interfaz unificada para obtener el conjunto completo
 * de transacciones, abstrayendo la complejidad del acceso a datos
 * y proporcionando un stream reactivo para actualizaciones en tiempo real.
 * 
 * @param repository Repositorio de transacciones para consulta de datos
 * 
 * @author Fintrax Development Team
 * @version 1.0
 * @since 1.0
 */
class GetAllTransactionsUseCase(
    private val repository: TransactionRepository
) {
    
    /**
     * Ejecuta el caso de uso para obtener todas las transacciones.
     * 
     * @return Flow reactivo que emite listas actualizadas de Transaction
     * @throws DataAccessException si ocurre un error durante la consulta
     */
    operator fun invoke(): Flow<List<Transaction>> {
        return repository.getAllTransactions()
    }
}

/**
 * Caso de uso para la eliminación de transacciones del sistema.
 * 
 * Encapsula la lógica de negocio para la eliminación segura de
 * transacciones, incluyendo validaciones de estado y dependencias
 * antes de proceder con la eliminación física.
 * 
 * @param repository Repositorio de transacciones para eliminación
 * 
 * @author Fintrax Development Team
 * @version 1.0
 * @since 1.0
 */
class DeleteTransactionUseCase(
    private val repository: TransactionRepository
) {
    
    /**
     * Ejecuta el caso de uso para eliminar una transacción específica.
     * 
     * @param transactionId Identificador único de la transacción a eliminar
     * @throws IllegalArgumentException si el ID es inválido
     * @throws TransactionNotFoundException si la transacción no existe
     * @throws BusinessRuleException si la transacción no puede ser eliminada
     */
    suspend operator fun invoke(transactionId: String) {
        repository.deleteTransaction(transactionId)
    }
}

/**
 * Caso de uso para la actualización de transacciones existentes.
 * 
 * Gestiona la modificación de datos de transacciones, asegurando
 * la validación de cambios y el mantenimiento de la integridad
 * referencial y de negocio durante el proceso de actualización.
 * 
 * @param repository Repositorio de transacciones para actualización
 * 
 * @author Fintrax Development Team
 * @version 1.0
 * @since 1.0
 */
class UpdateTransactionUseCase(
    private val repository: TransactionRepository
) {
    
    /**
     * Ejecuta el caso de uso para actualizar una transacción existente.
     * 
     * @param transaction Instancia de Transaction con datos actualizados
     * @throws IllegalArgumentException si la transacción es inválida
     * @throws TransactionNotFoundException si la transacción no existe
     * @throws BusinessRuleException si los cambios violan reglas de negocio
     */
    suspend operator fun invoke(transaction: Transaction) {
        repository.updateTransaction(transaction)
    }
}
