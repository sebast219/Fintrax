package com.example.fintrax.domain.model

import java.math.BigDecimal
import java.util.Date

/**
 * Modelo de dominio que representa un gasto mensual recurrente.
 * 
 * Esta entidad encapsula la lógica de negocio para gastos que ocurren
 * periódicamente cada mes, permitiendo la planificación financiera y
 * el seguimiento de obligaciones financieras predecibles.
 * 
 * @param id Identificador único universal del gasto mensual
 * @param amount Monto monetario fijo del gasto recurrente
 * @param description Descripción detallada del gasto para identificación
 * @param category Categorización taxonómica del gasto según su naturaleza
 * @param dueDate Día del mes en que se debe realizar el pago (1-31)
 * @param isActive Estado de activación del gasto para control de periodicidad
 * @param createdDate Fecha de creación del registro del gasto mensual
 * @param lastPaidDate Fecha del último pago realizado (nullable)
 * 
 * @author Fintrax Development Team
 * @version 1.0
 * @since 1.0
 */
data class MonthlyExpense(
    val id: String,
    val amount: BigDecimal,
    val description: String,
    val category: TransactionCategory,
    val dueDate: Int, // Día del mes (1-31)
    val isActive: Boolean = true,
    val createdDate: Date,
    val lastPaidDate: Date? = null
)

/**
 * Modelo de dominio que representa el estado financiero consolidado.
 * 
 * Esta clase encapsula el cálculo y representación del balance financiero
 * en un período específico, proporcionando una vista agregada de la
 * situación económica del usuario.
 * 
 * @param totalIncome Sumatoria total de ingresos en el período
 * @param totalExpenses Sumatoria total de egresos en el período
 * @param netBalance Balance neto calculado (ingresos - egresos)
 * @param period Período temporal de agregación del balance
 * @param date Fecha de cálculo o actualización del balance
 * 
 * @author Fintrax Development Team
 * @version 1.0
 * @since 1.0
 */
data class Balance(
    val totalIncome: BigDecimal,
    val totalExpenses: BigDecimal,
    val netBalance: BigDecimal,
    val period: BalancePeriod,
    val date: Date
)

/**
 * Enumeración que define los períodos de agregación para balances financieros.
 * 
 * Establece los intervalos temporales estándar para el cálculo y análisis
 * de balances financieros, permitiendo comparaciones temporales y
 * tendencias históricas.
 * 
 * @author Fintrax Development Team
 * @version 1.0
 * @since 1.0
 */
enum class BalancePeriod {
    /**
     * Período de agregación diaria
     */
    DAILY,
    
    /**
     * Período de agregación semanal
     */
    WEEKLY,
    
    /**
     * Período de agregación mensual
     */
    MONTHLY,
    
    /**
     * Período de agregación anual
     */
    YEARLY
}

/**
 * Modelo de dominio que representa un resumen financiero comprehensivo.
 * 
 * Esta clase proporciona una vista analítica agregada de la situación financiera,
 * incluyendo métricas clave y transacciones significativas para facilitar
 * la toma de decisiones financieras informadas.
 * 
 * @param totalIncome Sumatoria total de ingresos en el período analizado
 * @param totalExpenses Sumatoria total de egresos en el período analizado
 * @param netBalance Balance neto resultante del período
 * @param monthlyAverage Promedio mensual calculado para normalización temporal
 * @param biggestExpense Transacción de mayor monto egreso (nullable)
 * @param biggestIncome Transacción de mayor monto ingreso (nullable)
 * @param period Período temporal de análisis del resumen
 * 
 * @author Fintrax Development Team
 * @version 1.0
 * @since 1.0
 */
data class FinancialSummary(
    val totalIncome: BigDecimal,
    val totalExpenses: BigDecimal,
    val netBalance: BigDecimal,
    val monthlyAverage: BigDecimal,
    val biggestExpense: Transaction?,
    val biggestIncome: Transaction?,
    val period: BalancePeriod
)
