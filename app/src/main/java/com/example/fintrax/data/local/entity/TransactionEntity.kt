package com.example.fintrax.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.fintrax.domain.model.TransactionCategory
import com.example.fintrax.domain.model.TransactionType
import com.example.fintrax.domain.model.RecurringPeriod
import java.math.BigDecimal
import java.util.Date

/**
 * Entidad de Room que representa una transacción en la base de datos local.
 * 
 * Esta clase actúa como un mapeo objeto-relacional (ORM) entre el modelo
 * de dominio Transaction y la tabla de base de datos SQLite. Implementa
 * el patrón Data Mapper para la conversión entre entidades de dominio
 * y entidades de persistencia.
 * 
 * La anotación @Entity especifica que esta clase es una tabla de base
 * de datos Room, con nombre personalizado "transactions" para evitar
 * conflictos con palabras reservadas de SQL.
 * 
 * @author Fintrax Development Team
 * @version 1.0
 * @since 1.0
 */
@Entity(tableName = "transactions")
data class TransactionEntity(
    
    /**
     * Identificador único primario de la transacción.
     * 
     * Utiliza UUID como clave primaria para garantizar unicidad global
     * y facilitar la sincronización con sistemas externos en el futuro.
     */
    @PrimaryKey
    val id: String,
    
    /**
     * Monto monetario de la transacción.
     * 
     * Almacenado como BigDecimal para mantener precisión decimal en
     * operaciones financieras críticas. Room requiere TypeConverters
     * para persistir este tipo de dato.
     */
    val amount: BigDecimal,
    
    /**
     * Descripción narrativa de la transacción.
     * 
     * Campo de texto libre para facilitar la identificación y búsqueda
     * de transacciones por parte del usuario final.
     */
    val description: String,
    
    /**
     * Categorización taxonómica de la transacción.
     * 
     * Enum almacenado como string mediante TypeConverter, permitiendo
     * consultas agrupadas por categoría y análisis de patrones.
     */
    val category: TransactionCategory,
    
    /**
     * Clasificación binaria de la transacción.
     * 
     * Determina si la transacción representa un ingreso o egreso,
     * fundamental para cálculos de balance y análisis financiero.
     */
    val type: TransactionType,
    
    /**
     * Timestamp de ocurrencia de la transacción.
     * 
     * Almacenado como Long (milisegundos desde epoch) para optimizar
     * consultas por rangos de fechas y ordenamiento cronológico.
     */
    val date: Long, // Almacenado como timestamp
    
    /**
     * Indicador de periodicidad de la transacción.
     * 
     * Booleano que determina si la transacción es recurrente,
     * habilitando funcionalidades de automatización financiera.
     */
    val isRecurring: Boolean = false,
    
    /**
     * Período de recurrencia para transacciones periódicas.
     * 
     * Nullable ya que solo aplica cuando isRecurring es true.
     * Almacenado como enum mediante TypeConverter.
     */
    val recurringPeriod: RecurringPeriod? = null,
    
    /**
     * Timestamp de creación del registro.
     * 
     * Campo de auditoría para seguimiento de creación y
     * sincronización de datos. Valor por defecto: timestamp actual.
     */
    val created_at: Long = System.currentTimeMillis(),
    
    /**
     * Timestamp de última actualización del registro.
     * 
     * Campo de auditoría para seguimiento de modificaciones y
     * detección de cambios para sincronización incremental.
     */
    val updated_at: Long = System.currentTimeMillis()
)
