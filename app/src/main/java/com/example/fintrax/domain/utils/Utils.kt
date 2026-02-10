package com.example.fintrax.domain.utils

import java.math.BigDecimal
import java.text.NumberFormat
import java.util.*

/**
 * Utility class for formatting currency values
 */
object CurrencyFormatter {
    fun formatCurrency(amount: BigDecimal, locale: Locale = Locale.getDefault()): String {
        val formatter = NumberFormat.getCurrencyInstance(locale)
        return formatter.format(amount)
    }
    
    fun formatDecimal(amount: BigDecimal): String {
        return amount.setScale(2, BigDecimal.ROUND_HALF_UP).toString()
    }
}

/**
 * Utility class for date operations
 */
object DateUtils {
    // Date formatting and parsing utilities will be implemented here
    fun getCurrentTimestamp(): Long = System.currentTimeMillis()
    
    fun getStartOfMonth(timestamp: Long): Long {
        // Implementation will go here
        return timestamp
    }
    
    fun getEndOfMonth(timestamp: Long): Long {
        // Implementation will go here
        return timestamp
    }
}

/**
 * Utility class for validation
 */
object ValidationUtils {
    fun isValidAmount(amount: BigDecimal): Boolean {
        return amount > BigDecimal.ZERO
    }
    
    fun isValidDescription(description: String): Boolean {
        return description.isNotBlank() && description.length <= 255
    }
}
