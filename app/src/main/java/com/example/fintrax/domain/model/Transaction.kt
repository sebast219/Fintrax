package com.example.fintrax.domain.model

import java.math.BigDecimal
import java.util.Date

/**
 * Modelo de dominio que representa una transacción financiera.
 * 
 * Esta clase es una entidad de negocio pura sin dependencias de framework alguno,
 * siguiendo los principios de Clean Architecture. Representa el concepto central
 * del dominio financiero y encapsula todos los atributos necesarios para definir
 * una transacción monetaria dentro del sistema.
 * 
 * @param id Identificador único universal de la transacción (UUID)
 * @param amount Monto monetario de la transacción utilizando BigDecimal para precisión decimal
 * @param description Descripción narrativa de la transacción para fines de registro y consulta
 * @param category Categorización taxonómica de la transacción según su naturaleza económica
 * @param type Clasificación binaria de la transacción como ingreso o egreso
 * @param date Timestamp de ocurrencia de la transacción en tiempo UTC
 * @param isRecurring Indicador booleano de periodicidad de la transacción
 * @param recurringPeriod Periodicidad temporal en caso de ser una transacción recurrente
 * 
 * @author Fintrax Development Team
 * @version 1.0
 * @since 1.0
 */
data class Transaction(
    val id: String,
    val amount: BigDecimal,
    val description: String,
    val category: TransactionCategory,
    val type: TransactionType,
    val date: Date,
    val isRecurring: Boolean = false,
    val recurringPeriod: RecurringPeriod? = null
)

/**
 * Enumeración que representa las categorías taxonómicas de transacciones financieras.
 * 
 * Esta enumeración define una clasificación estandarizada para organizar las transacciones
 * según su naturaleza económica, facilitando el análisis de patrones de gasto y
 * la generación de informes financieros agregados.
 * 
 * @author Fintrax Development Team
 * @version 1.0
 * @since 1.0
 */
enum class TransactionCategory {
    /**
     * Gastos relacionados con vivienda y alojamiento
     */
    HOUSING,
    
    /**
     * Gastos asociados con transporte y movilidad
     */
    TRANSPORTATION,
    
    /**
     * Gastos en alimentos y comestibles
     */
    FOOD,
    
    /**
     * Gastos en servicios públicos y utilities
     */
    UTILITIES,
    
    /**
     * Gastos en servicios médicos y salud
     */
    HEALTHCARE,
    
    /**
     * Gastos en entretenimiento y ocio
     */
    ENTERTAINMENT,
    
    /**
     * Transacciones de ahorro e inversión
     */
    SAVINGS,
    
    /**
     * Categoría especial para ingresos
     */
    INCOME,
    
    /**
     * Categoría por defecto para transacciones no clasificadas
     */
    OTHER
}

/**
 * Enumeración que representa la naturaleza fundamental de una transacción financiera.
 * 
 * Define la clasificación binaria esencial que determina el impacto de la transacción
 * sobre el balance financiero: incrementa (ingreso) o decrementa (egreso) el patrimonio.
 * 
 * @author Fintrax Development Team
 * @version 1.0
 * @since 1.0
 */
enum class TransactionType {
    /**
     * Transacción que incrementa el patrimonio neto
     */
    INCOME,
    
    /**
     * Transacción que decrementa el patrimonio neto
     */
    EXPENSE
}

/**
 * Enumeración que define los períodos de recurrencia para transacciones periódicas.
 * 
 * Establece los intervalos temporales estandarizados para la automatización
 * de transacciones recurrentes, permitiendo la predecibilidad y planificación
 * financiera a largo plazo.
 * 
 * @author Fintrax Development Team
 * @version 1.0
 * @since 1.0
 */
enum class RecurringPeriod {
    /**
     * Período de recurrencia diaria (24 horas)
     */
    DAILY,
    
    /**
     * Período de recurrencia semanal (7 días)
     */
    WEEKLY,
    
    /**
     * Período de recurrencia mensual (aproximadamente 30 días)
     */
    MONTHLY,
    
    /**
     * Período de recurrencia anual (365 días)
     */
    YEARLY
}
