package com.example.fintrax.presentation.screens.settings

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Settings Screen - App configuration and preferences
 * User settings, data management, and app preferences
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    onNavigateBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Settings") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Text("←")
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                ProfileSection()
            }
            
            item {
                PreferencesSection()
            }
            
            item {
                DataManagementSection()
            }
            
            item {
                AboutSection()
            }
        }
    }
}

@Composable
private fun ProfileSection() {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Profile",
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Profile picture placeholder
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .padding(8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text("👤")
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(
                        text = "User Name",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = "user@example.com",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}

@Composable
private fun PreferencesSection() {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Preferences",
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.height(8.dp))
            
            // Settings items will go here
            SettingsItem(
                title = "Currency",
                subtitle = "USD ($)",
                onClick = { /* Handle currency change */ }
            )
            
            SettingsItem(
                title = "Date Format",
                subtitle = "MM/DD/YYYY",
                onClick = { /* Handle date format change */ }
            )
            
            SettingsItem(
                title = "Theme",
                subtitle = "System Default",
                onClick = { /* Handle theme change */ }
            )
            
            SettingsItem(
                title = "Notifications",
                subtitle = "Enabled",
                onClick = { /* Handle notifications */ }
            )
        }
    }
}

@Composable
private fun DataManagementSection() {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Data Management",
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.height(8.dp))
            
            SettingsItem(
                title = "Export Data",
                subtitle = "Export all financial data",
                onClick = { /* Handle export */ }
            )
            
            SettingsItem(
                title = "Import Data",
                subtitle = "Import from backup",
                onClick = { /* Handle import */ }
            )
            
            SettingsItem(
                title = "Clear Cache",
                subtitle = "Clear temporary data",
                onClick = { /* Handle cache clear */ }
            )
            
            SettingsItem(
                title = "Reset App",
                subtitle = "Reset all data",
                onClick = { /* Handle reset */ },
                isDestructive = true
            )
        }
    }
}

@Composable
private fun AboutSection() {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "About",
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = "Fintrax",
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = "Version 1.0.0",
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = "Personal Finance Management App",
                style = MaterialTheme.typography.bodyMedium
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            SettingsItem(
                title = "Privacy Policy",
                subtitle = "View privacy policy",
                onClick = { /* Open privacy policy */ }
            )
            
            SettingsItem(
                title = "Terms of Service",
                subtitle = "View terms of service",
                onClick = { /* Open terms */ }
            )
        }
    }
}

@Composable
private fun SettingsItem(
    title: String,
    subtitle: String,
    onClick: () -> Unit,
    isDestructive: Boolean = false
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
                color = if (isDestructive) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodySmall
            )
        }
        
        IconButton(onClick = onClick) {
            Text("→")
        }
    }
}
