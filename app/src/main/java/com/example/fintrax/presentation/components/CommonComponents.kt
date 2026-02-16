package com.example.fintrax.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.math.BigDecimal

/**
 * Reusable UI components for the Fintrax app
 * Common components used across multiple screens
 */

@Composable
fun AmountInputField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String = "Amount",
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = modifier,
        placeholder = { Text("0.00") }
    )
}

@Composable
fun DescriptionInputField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String = "Description",
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = modifier,
        placeholder = { Text("Enter description...") }
    )
}

@Composable
fun CategoryDropdown(
    selectedCategory: com.example.fintrax.domain.model.TransactionCategory,
    onCategorySelected: (com.example.fintrax.domain.model.TransactionCategory) -> Unit,
    categories: List<com.example.fintrax.domain.model.TransactionCategory>,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = modifier
    ) {
        OutlinedTextField(
            value = selectedCategory.name,
            onValueChange = { },
            readOnly = true,
            label = { Text("Category") },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            modifier = Modifier.menuAnchor()
        )
        
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            categories.forEach { category ->
                DropdownMenuItem(
                    text = { Text(category.name) },
                    onClick = {
                        onCategorySelected(category)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Composable
fun TransactionTypeSelector(
    selectedType: com.example.fintrax.domain.model.TransactionType,
    onTypeSelected: (com.example.fintrax.domain.model.TransactionType) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        FilterChip(
            selected = selectedType == com.example.fintrax.domain.model.TransactionType.INCOME,
            onClick = { onTypeSelected(com.example.fintrax.domain.model.TransactionType.INCOME) },
            label = { Text("Income") }
        )
        
        FilterChip(
            selected = selectedType == com.example.fintrax.domain.model.TransactionType.EXPENSE,
            onClick = { onTypeSelected(com.example.fintrax.domain.model.TransactionType.EXPENSE) },
            label = { Text("Expense") }
        )
    }
}

@Composable
fun LoadingIndicator(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorMessage(
    message: String,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.errorContainer
        )
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = message,
                color = MaterialTheme.colorScheme.onErrorContainer,
                modifier = Modifier.weight(1f)
            )
            
            IconButton(onClick = onDismiss) {
                Text(
                    text = "×",
                    color = MaterialTheme.colorScheme.onErrorContainer
                )
            }
        }
    }
}

@Composable
fun EmptyState(
    title: String,
    description: String,
    actionText: String? = null,
    onActionClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineSmall
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Text(
            text = description,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        
        actionText?.let { action ->
            onActionClick?.let { click ->
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = click) {
                    Text(action)
                }
            }
        }
    }
}

@Composable
fun CurrencyText(
    amount: BigDecimal,
    modifier: Modifier = Modifier
) {
    Text(
        text = "$$amount",
        style = MaterialTheme.typography.bodyLarge,
        modifier = modifier
    )
}

@Composable
fun DateSelector(
    selectedDate: Long,
    onDateSelected: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    // Date picker implementation will go here
    OutlinedTextField(
        value = java.util.Date(selectedDate).toString(),
        onValueChange = { },
        readOnly = true,
        label = { Text("Date") },
        modifier = modifier,
        trailingIcon = {
            IconButton(onClick = { /* Open date picker */ }) {
                Text("📅")
            }
        }
    )
}
