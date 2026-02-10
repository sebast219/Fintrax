package com.example.fintrax.domain.model

import java.math.BigDecimal

/**
 * Modelo de dominio que representa datos para visualización gráfica financiera.
 * 
 * Esta clase encapsula la estructura de datos necesaria para generar gráficos
 * de análisis financiero, proporcionando una abstracción limpia entre la
 * lógica de negocio y la capa de presentación visual.
 * 
 * @param label Etiqueta descriptiva para el elemento del gráfico
 * @param value Valor numérico del elemento (monto monetario)
 * @param percentage Porcentaje representativo respecto al total
 * @param color Código de color hexadecimal para visualización
 * 
 * @author Fintrax Development Team
 * @version 1.0
 * @since 1.0
 */
data class ChartData(
    val label: String,
    val value: BigDecimal,
    val percentage: Float,
    val color: String
)

/**
 * Modelo de dominio que representa tendencias temporales de ingresos y egresos.
 * 
 * Esta estructura facilita el análisis de patrones financieros a lo largo
 * del tiempo, permitiendo identificar tendencias y realizar proyecciones
 * financieras basadas en datos históricos.
 * 
 * @param period Período temporal de agregación (ej: "Enero 2024")
 * @param income Monto total de ingresos en el período
 * @param expenses Monto total de egresos en el período
 * @param net Balance neto calculado para el período
 * 
 * @author Fintrax Development Team
 * @version 1.0
 * @since 1.0
 */
data class TrendData(
    val period: String,
    val income: BigDecimal,
    val expenses: BigDecimal,
    val net: BigDecimal
)

/**
 * Modelo de dominio que representa el desglose analítico por categorías.
 * 
 * Proporciona una vista agregada del comportamiento financiero segmentado
 * por categorías taxonómicas, facilitando el análisis de patrones de gasto
 * y la optimización presupuestaria.
 * 
 * @param category Categoría de transacción analizada
 * @param totalAmount Monto total acumulado para la categoría
 * @param transactionCount Número total de transacciones en la categoría
 * @param percentage Porcentaje representativo respecto al total general
 * 
 * @author Fintrax Development Team
 * @version 1.0
 * @since 1.0
 */
data class CategoryBreakdown(
    val category: TransactionCategory,
    val totalAmount: BigDecimal,
    val transactionCount: Int,
    val percentage: Float
)
