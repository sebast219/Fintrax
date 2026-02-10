package com.example.fintrax.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fintrax.domain.model.Transaction
import com.example.fintrax.domain.usecase.GetAllTransactionsUseCase
import com.example.fintrax.domain.usecase.AddTransactionUseCase
import com.example.fintrax.domain.usecase.DeleteTransactionUseCase
import com.example.fintrax.domain.usecase.UpdateTransactionUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel for transaction operations
 * Manages transaction state and business logic
 */
class TransactionViewModel(
    private val getAllTransactionsUseCase: GetAllTransactionsUseCase,
    private val addTransactionUseCase: AddTransactionUseCase,
    private val deleteTransactionUseCase: DeleteTransactionUseCase,
    private val updateTransactionUseCase: UpdateTransactionUseCase
) : ViewModel() {
    
    private val _transactions = MutableStateFlow<List<Transaction>>(emptyList())
    val transactions: StateFlow<List<Transaction>> = _transactions.asStateFlow()
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()
    
    init {
        loadTransactions()
    }
    
    private fun loadTransactions() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                getAllTransactionsUseCase().collect { transactionList ->
                    _transactions.value = transactionList
                    _isLoading.value = false
                }
            } catch (e: Exception) {
                _errorMessage.value = "Error loading transactions: ${e.message}"
                _isLoading.value = false
            }
        }
    }
    
    fun addTransaction(transaction: Transaction) {
        viewModelScope.launch {
            try {
                addTransactionUseCase(transaction)
                // Transactions will be updated automatically via flow
            } catch (e: Exception) {
                _errorMessage.value = "Error adding transaction: ${e.message}"
            }
        }
    }
    
    fun deleteTransaction(transactionId: String) {
        viewModelScope.launch {
            try {
                deleteTransactionUseCase(transactionId)
                // Transactions will be updated automatically via flow
            } catch (e: Exception) {
                _errorMessage.value = "Error deleting transaction: ${e.message}"
            }
        }
    }
    
    fun updateTransaction(transaction: Transaction) {
        viewModelScope.launch {
            try {
                updateTransactionUseCase(transaction)
                // Transactions will be updated automatically via flow
            } catch (e: Exception) {
                _errorMessage.value = "Error updating transaction: ${e.message}"
            }
        }
    }
    
    fun clearError() {
        _errorMessage.value = null
    }
}
