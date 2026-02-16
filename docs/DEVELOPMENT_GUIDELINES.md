# Fintrax Development Guidelines

## 📋 Introduction

This document provides comprehensive development guidelines for the Fintrax project, ensuring consistency, quality, and maintainability across all code contributions.

---

## 🎯 Coding Standards

### Kotlin Conventions
Follow official [Kotlin Coding Conventions](https://kotlinlang.org/docs/coding-conventions.html):

```kotlin
// ✅ Good
class TransactionRepository @Inject constructor(
    private val transactionDao: TransactionDao,
    private val mapper: TransactionMapper
) : TransactionRepository {
    
    override suspend fun insertTransaction(transaction: Transaction) {
        val entity = mapper.toEntity(transaction)
        transactionDao.insertTransaction(entity)
    }
}

// ❌ Bad
class transactionRepository {
    fun insertTransaction(transaction: Transaction) {
        // implementation
    }
}
```

### Naming Conventions

#### Classes and Objects
- **Classes**: PascalCase (e.g., `TransactionRepository`)
- **Objects**: PascalCase (e.g., `DatabaseModule`)
- **Interfaces**: PascalCase with descriptive names (e.g., `TransactionRepository`)

#### Functions and Variables
- **Functions**: camelCase (e.g., `insertTransaction`)
- **Variables**: camelCase (e.g., `transactionList`)
- **Constants**: UPPER_SNAKE_CASE (e.g., `DEFAULT_PAGE_SIZE`)

#### Packages
- **Structure**: Lowercase with dots (e.g., `com.example.fintrax.data.local`)
- **Naming**: Descriptive and concise

---

## 🏗️ Architecture Guidelines

### Layer Separation

#### Domain Layer Rules
```kotlin
// ✅ Good - Pure domain logic
data class Transaction(
    val id: String,
    val amount: BigDecimal,
    val description: String,
    val category: TransactionCategory,
    val type: TransactionType,
    val date: Date
)

// ❌ Bad - Framework dependencies in domain
data class Transaction(
    val id: String,
    val amount: BigDecimal,
    @SerializedName("amount") // No framework annotations
    val description: String
)
```

#### Data Layer Rules
```kotlin
// ✅ Good - Repository implementation
class TransactionRepositoryImpl @Inject constructor(
    private val transactionDao: TransactionDao,
    private val mapper: TransactionMapper
) : TransactionRepository {
    
    override fun getAllTransactions(): Flow<List<Transaction>> {
        return transactionDao.getAllTransactions()
            .map { entities -> entities.map { mapper.toDomain(it) } }
    }
}

// ❌ Bad - Business logic in data layer
class TransactionRepositoryImpl {
    fun getAllTransactions(): Flow<List<Transaction>> {
        // Don't calculate financial summaries here
        return flow { /* implementation */ }
    }
}
```

#### Presentation Layer Rules
```kotlin
// ✅ Good - ViewModel with use cases
@HiltViewModel
class TransactionViewModel @Inject constructor(
    private val getAllTransactionsUseCase: GetAllTransactionsUseCase,
    private val addTransactionUseCase: AddTransactionUseCase
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(TransactionUiState())
    val uiState: StateFlow<TransactionUiState> = _uiState.asStateFlow()
    
    fun addTransaction(transaction: Transaction) {
        viewModelScope.launch {
            addTransactionUseCase(transaction)
        }
    }
}

// ❌ Bad - Direct repository access in ViewModel
class TransactionViewModel {
    fun addTransaction(transaction: Transaction) {
        // Don't access repository directly
        repository.insertTransaction(transaction)
    }
}
```

---

## 🧪 Testing Guidelines

### Unit Tests

#### Structure
```kotlin
class AddTransactionUseCaseTest {
    
    private lateinit var repository: TransactionRepository
    private lateinit var addTransactionUseCase: AddTransactionUseCase
    
    @Before
    fun setup() {
        repository = mockk()
        addTransactionUseCase = AddTransactionUseCase(repository)
    }
    
    @Test
    fun `should add transaction when valid`() = runTest {
        // Given
        val transaction = createTestTransaction()
        coEvery { repository.insertTransaction(transaction) } returns Unit
        
        // When
        addTransactionUseCase(transaction)
        
        // Then
        coVerify { repository.insertTransaction(transaction) }
    }
    
    @Test
    fun `should throw exception when repository fails`() = runTest {
        // Given
        val transaction = createTestTransaction()
        coEvery { repository.insertTransaction(transaction) } throws IOException()
        
        // When & Then
        assertThrows<IOException> {
            addTransactionUseCase(transaction)
        }
    }
    
    private fun createTestTransaction() = Transaction(
        id = "1",
        amount = BigDecimal("100.00"),
        description = "Test Transaction",
        category = TransactionCategory.FOOD,
        type = TransactionType.EXPENSE,
        date = Date()
    )
}
```

#### Test Naming
- Use descriptive names with backticks
- Follow "should [behavior] when [condition]" pattern
- Include test setup and expectations

### UI Tests

#### Compose Testing
```kotlin
@Test
fun `transaction item should display transaction details`() {
    composeTestRule.setContent {
        TransactionItem(
            transaction = testTransaction,
            onClick = { }
        )
    }
    
    composeTestRule
        .onNodeWithText(testTransaction.description)
        .assertIsDisplayed()
    
    composeTestRule
        .onNodeWithText("$${testTransaction.amount}")
        .assertIsDisplayed()
}
```

---

## 📱 UI/UX Guidelines

### Compose Best Practices

#### Component Structure
```kotlin
@Composable
fun TransactionItem(
    transaction: Transaction,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            TransactionHeader(transaction)
            Spacer(modifier = Modifier.height(8.dp))
            TransactionDetails(transaction)
        }
    }
}

@Composable
private fun TransactionHeader(transaction: Transaction) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = transaction.description,
            style = MaterialTheme.typography.bodyLarge
        )
        AmountText(
            amount = transaction.amount,
            type = transaction.type
        )
    }
}
```

#### State Management
```kotlin
@Composable
fun TransactionScreen(
    viewModel: TransactionViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    
    when (uiState) {
        is TransactionUiState.Loading -> LoadingIndicator()
        is TransactionUiState.Success -> TransactionContent(
            transactions = uiState.transactions,
            onTransactionClick = viewModel::onTransactionClick
        )
        is TransactionUiState.Error -> ErrorMessage(
            message = uiState.message,
            onDismiss = viewModel::clearError
        )
    }
}
```

### Material Design 3

#### Theme Usage
```kotlin
@Composable
fun FintraxTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) 
            else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
```

---

## 🔧 Dependency Management

### Hilt Usage

#### Module Structure
```kotlin
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    
    @Provides
    @Singleton
    fun provideFintraxDatabase(
        @ApplicationContext context: Context
    ): FintraxDatabase {
        return Room.databaseBuilder(
            context,
            FintraxDatabase::class.java,
            "fintrax_database"
        ).build()
    }
    
    @Provides
    fun provideTransactionDao(database: FintraxDatabase): TransactionDao {
        return database.transactionDao()
    }
}
```

#### Constructor Injection
```kotlin
@HiltViewModel
class TransactionViewModel @Inject constructor(
    private val getAllTransactionsUseCase: GetAllTransactionsUseCase,
    private val addTransactionUseCase: AddTransactionUseCase,
    private val deleteTransactionUseCase: DeleteTransactionUseCase
) : ViewModel() {
    // Implementation
}
```

---

## 📊 Performance Guidelines

### Database Optimization

#### Efficient Queries
```kotlin
@Dao
interface TransactionDao {
    
    @Query("SELECT * FROM transactions WHERE date BETWEEN :startDate AND :endDate ORDER BY date DESC")
    fun getTransactionsByDateRange(startDate: Long, endDate: Long): Flow<List<TransactionEntity>>
    
    @Query("SELECT category, SUM(amount) as total FROM transactions WHERE type = :type GROUP BY category")
    suspend fun getExpensesByCategory(type: TransactionType): List<CategoryTotal>
}
```

#### Pagination
```kotlin
@Query("SELECT * FROM transactions ORDER BY date DESC LIMIT :limit OFFSET :offset")
suspend fun getTransactionsPaged(limit: Int, offset: Int): List<TransactionEntity>
```

### UI Performance

#### Lazy Loading
```kotlin
@Composable
fun TransactionList(
    transactions: List<Transaction>,
    onTransactionClick: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(
            items = transactions,
            key = { it.id }
        ) { transaction ->
            TransactionItem(
                transaction = transaction,
                onClick = { onTransactionClick(transaction.id) }
            )
        }
    }
}
```

---

## 🔒 Security Guidelines

### Data Protection

#### Input Validation
```kotlin
class TransactionValidator {
    fun validateTransaction(transaction: Transaction): ValidationResult {
        return when {
            transaction.amount <= BigDecimal.ZERO -> {
                ValidationResult.Error("Amount must be positive")
            }
            transaction.description.isBlank() -> {
                ValidationResult.Error("Description cannot be empty")
            }
            transaction.description.length > 255 -> {
                ValidationResult.Error("Description too long")
            }
            else -> ValidationResult.Success
        }
    }
}
```

#### Secure Storage
```kotlin
@Module
@InstallIn(SingletonComponent::class)
object SecurityModule {
    
    @Provides
    @Singleton
    fun provideEncryptedSharedPreferences(
        @ApplicationContext context: Context
    ): SharedPreferences {
        return EncryptedSharedPreferences.create(
            "fintrax_secure_prefs",
            MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC),
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }
}
```

---

## 📝 Documentation Guidelines

### Code Documentation

#### Class Documentation
```kotlin
/**
 * Repository interface for transaction operations.
 * 
 * This interface defines the contract for accessing and managing transaction data.
 * Implementations should handle data persistence, caching, and synchronization.
 * 
 * @see TransactionRepositoryImpl for Room database implementation
 * 
 * @throws IOException when data access fails
 * @throws IllegalArgumentException when invalid data is provided
 */
interface TransactionRepository {
    /**
     * Inserts a new transaction into the data store.
     * 
     * @param transaction The transaction to insert
     * @throws IOException if the transaction cannot be inserted
     */
    suspend fun insertTransaction(transaction: Transaction)
}
```

#### Function Documentation
```kotlin
/**
 * Calculates the total expenses for a given category within a date range.
     * 
     * @param category The transaction category to filter by
     * @param startDate The start date of the period (inclusive)
     * @param endDate The end date of the period (inclusive)
     * @return The total amount as BigDecimal
     * @throws IllegalArgumentException if startDate is after endDate
     */
suspend fun getTotalExpensesByCategory(
    category: TransactionCategory,
    startDate: Date,
    endDate: Date
): BigDecimal
```

---

## 🔄 Git Workflow

### Branch Strategy

#### Branch Naming
- `feature/feature-name` - New features
- `bugfix/bug-description` - Bug fixes
- `hotfix/urgent-fix` - Critical fixes
- `refactor/component-name` - Refactoring

#### Commit Messages
```
feat: add transaction validation
fix: resolve crash on empty transaction list
docs: update API documentation
refactor: simplify repository implementation
test: add unit tests for use cases
```

### Pull Request Guidelines

#### PR Template
```markdown
## Description
Brief description of changes

## Type of Change
- [ ] Bug fix
- [ ] New feature
- [ ] Breaking change
- [ ] Documentation update

## Testing
- [ ] Unit tests added/updated
- [ ] UI tests added/updated
- [ ] Manual testing completed

## Checklist
- [ ] Code follows project guidelines
- [ ] Self-review completed
- [ ] Documentation updated
- [ ] Tests passing
```

---

## 🚀 Deployment Guidelines

### Build Configuration

#### Release Build
```kotlin
android {
    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
        }
    }
}
```

#### Version Management
```kotlin
android {
    defaultConfig {
        versionCode = 1
        versionName = "1.0.0"
    }
}
```

---

## 📊 Monitoring and Analytics

### Performance Monitoring

#### Firebase Performance
```kotlin
class TransactionRepositoryImpl @Inject constructor(
    private val transactionDao: TransactionDao,
    private val performance: FirebasePerformance
) : TransactionRepository {
    
    override suspend fun insertTransaction(transaction: Transaction) {
        val trace = performance.newTrace("insert_transaction")
        trace.start()
        
        try {
            val entity = mapper.toEntity(transaction)
            transactionDao.insertTransaction(entity)
        } finally {
            trace.stop()
        }
    }
}
```

### Error Tracking

#### Crashlytics Integration
```kotlin
class TransactionViewModel @Inject constructor(
    private val addTransactionUseCase: AddTransactionUseCase
) : ViewModel() {
    
    fun addTransaction(transaction: Transaction) {
        viewModelScope.launch {
            try {
                addTransactionUseCase(transaction)
            } catch (e: Exception) {
                Firebase.crashlytics.recordException(e)
                _uiState.value = TransactionUiState.Error(e.message ?: "Unknown error")
            }
        }
    }
}
```

---

These guidelines ensure consistent, high-quality code development across the Fintrax project. Regular updates and reviews of these guidelines will maintain code quality and developer productivity.
