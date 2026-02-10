# Fintrax - Sistema de Gestión Financiera Personal

<div align="center">

![Logo de Fintrax](https://via.placeholder.com/150x150/6366F1/FFFFFF?text=FINTRAX)

**Aplicación Android moderna para gestión financiera personal construida con Clean Architecture y Jetpack Compose**

[![Android](https://img.shields.io/badge/Plataforma-Android-green.svg)](https://developer.android.com)
[![Kotlin](https://img.shields.io/badge/Lenguaje-Kotlin-blue.svg)](https://kotlinlang.org)
[![Compose](https://img.shields.io/badge/UI-Jetpack%20Compose-purple.svg)](https://developer.android.com/jetpack/compose)
[![Architecture](https://img.shields.io/badge/Arquitectura-Clean%20Architecture-orange.svg)](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)

</div>

## 📋 Índice

- [Acerca del Proyecto](#-acerca-del-proyecto)
- [Características](#-características)
- [Arquitectura](#-arquitectura)
- [Stack Tecnológico](#-stack-tecnológico)
- [Estructura del Proyecto](#-estructura-del-proyecto)
- [Instalación](#-instalación)
- [Uso](#-uso)
- [Pruebas](#-pruebas)
- [Contribuciones](#-contribuciones)
- [Licencia](#-licencia)
- [Roadmap](#-roadmap)

## 🎯 Acerca del Proyecto

Fintrax es una aplicación integral de gestión financiera personal diseñada para ayudar a los usuarios a registrar sus ingresos, gastos y objetivos financieros. La aplicación proporciona interfaces intuitivas para gestionar transacciones diarias, monitorear gastos mensuales y generar informes financieros detallados.

### Beneficios Clave

- **Seguimiento Financiero Simplificado**: Interfaz fácil de usar para registrar y categorizar transacciones
- **Análisis Inteligente**: Insights potenciados por IA y análisis de patrones de gasto
- **Gestión de Presupuestos**: Establecer y monitorear presupuestos mensuales con notificaciones en tiempo real
- **Informes Integrales**: Generar informes detallados para mejores decisiones financieras
- **Seguridad de Datos**: Almacenamiento local con capacidades opcionales de respaldo en la nube

## ✨ Características

### Características Principales
- ✅ **Gestión de Transacciones**: Agregar, editar y eliminar transacciones de ingresos/gastos
- ✅ **Gastos Mensuales**: Monitorear gastos mensuales recurrentes con recordatorios automáticos
- ✅ **Seguimiento de Balance**: Actualizaciones de balance en tiempo real e historial de datos
- ✅ **Gestión de Categorías**: Organizar transacciones por categorías personalizables
- ✅ **Informes Financieros**: Generar informes detallados con gráficos y tendencias

### Características Avanzadas
- 🚧 **Análisis de Gastos**: Análisis de patrones de gasto potenciado por IA
- 🚧 **Planificación de Presupuestos**: Establecer y monitorear límites de presupuesto
- 🚧 **Establecimiento de Metas**: Definir y monitorear objetivos financieros
- 🚧 **Exportación de Datos**: Exportar datos financieros en múltiples formatos (CSV, PDF)
- 🚧 **Sincronización en la Nube**: Respaldo opcional y sincronización en la nube
- 🚧 **Soporte Multi-divisa**: Soporte para múltiples monedas

### Características de UI/UX
- 🎨 **Material Design 3**: UI moderna y adaptativa siguiendo las guías de Material Design 3
- 🌙 **Modo Oscuro**: Cambio automático de tema claro/oscuro
- 📱 **Diseño Responsivo**: Optimizado para varios tamaños y orientaciones de pantalla
- ♿ **Accesibilidad**: Soporte completo de accesibilidad siguiendo las guías de Android

## 🏗️ Arquitectura

Fintrax sigue los principios de **Clean Architecture** con clara separación de responsabilidades:

```
┌─────────────────────────────────────────────────────────────┐
│                    CAPA DE PRESENTACIÓN                     │
│  ┌─────────────┐  ┌─────────────┐  ┌─────────────────────┐  │
│  │   Pantallas │  │ ViewModels  │  │    Navegación       │  │
│  │  (Compose)  │  │   (MVVM)    │  │   (Compose Nav)     │  │
│  └─────────────┘  └─────────────┘  └─────────────────────┘  │
└─────────────────────────────────────────────────────────────┘
                              │
                              ▼
┌─────────────────────────────────────────────────────────────┐
│                       CAPA DE DOMINIO                       │
│  ┌─────────────┐  ┌─────────────┐  ┌─────────────────────┐  │
│  │   Modelos   │  │  Casos de   │  │   Repositorios      │  │
│  │ (Entidades) │  │   Uso       │  │   (Interfaces)      │  │
│  └─────────────┘  └─────────────┘  └─────────────────────┘  │
└─────────────────────────────────────────────────────────────┘
                              │
                              ▼
┌─────────────────────────────────────────────────────────────┐
│                        CAPA DE DATOS                        │
│  ┌─────────────┐  ┌─────────────┐  ┌─────────────────────┐  │
│  │ Repositorio │  │   BD Local  │  │   APIs Remotas      │  │
│  │ (Impl)       │  │   (Room)    │  │  (Futuro)           │  │
│  └─────────────┘  └─────────────┘  └─────────────────────┘  │
└─────────────────────────────────────────────────────────────┘
```

### Principios Arquitectónicos Clave

1. **Separación de Responsabilidades**: Cada capa tiene responsabilidades específicas
2. **Inversión de Dependencias**: Módulos de alto nivel no dependen de módulos de bajo nivel
3. **Responsabilidad Única**: Cada clase tiene una razón para cambiar
4. **Testabilidad**: La arquitectura permite pruebas comprehensivas
5. **Escalabilidad**: Estructura modular soporta crecimiento futuro

## 🛠️ Stack Tecnológico

### Tecnologías Principales
- **Lenguaje**: [Kotlin](https://kotlinlang.org/) - Desarrollo Android moderno y conciso
- **Framework UI**: [Jetpack Compose](https://developer.android.com/jetpack/compose) - Toolkit de UI declarativo
- **Arquitectura**: [MVVM](https://developer.android.com/jetpack/guide) + Clean Architecture
- **Inyección de Dependencias**: [Hilt](https://dagger.dev/hilt/) - Framework DI en tiempo de compilación

### Librerías Android
- **Navegación**: [Navigation Compose](https://developer.android.com/jetpack/compose/navigation) - Navegación entre pantallas
- **ViewModel**: [Lifecycle ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Gestión de estado UI
- **Room**: [Room Database](https://developer.android.com/training/data-storage/room) - Base de datos local
- **Material 3**: [Material Design 3](https://m3.material.io/) - Sistema de diseño

### Pruebas y Calidad
- **Pruebas Unitarias**: [JUnit](https://junit.org/) + [Mockk](https://mockk.io/)
- **Pruebas UI**: [Compose Testing](https://developer.android.com/jetpack/compose/testing)
- **Análisis Estático**: [Detekt](https://detekt.dev/) + [Ktlint](https://ktlint.github.io/)

## 📁 Estructura del Proyecto

```
Fintrax/
├── app/
│   └── src/
│       ├── main/
│       │   ├── java/com/example/fintrax/
│       │   │   ├── data/                    # Capa de Datos
│       │   │   │   ├── local/              # Almacenamiento local (Room)
│       │   │   │   │   ├── database/       # Configuración BD
│       │   │   │   │   ├── entity/         # Entidades BD
│       │   │   │   │   └── dao/            # Objetos acceso datos
│       │   │   │   ├── remote/             # Fuentes datos remotos
│       │   │   │   ├── repository/         # Implementaciones repositorios
│       │   │   │   └── mapper/             # Utilidades mapeo datos
│       │   │   ├── domain/                 # Capa de Dominio
│       │   │   │   ├── model/              # Modelos de negocio
│       │   │   │   ├── repository/         # Interfaces repositorios
│       │   │   │   ├── usecase/            # Casos de uso lógica negocio
│       │   │   │   └── utils/              # Utilidades dominio
│       │   │   ├── presentation/           # Capa de Presentación
│       │   │   │   ├── screens/            # Pantallas UI
│       │   │   │   │   ├── dashboard/      # Panel principal
│       │   │   │   │   ├── transactions/   # Gestión transacciones
│       │   │   │   │   ├── income/         # Seguimiento ingresos
│       │   │   │   │   ├── expenses/       # Gestión gastos
│       │   │   │   │   ├── reports/        # Informes financieros
│       │   │   │   │   └── settings/       # Configuración app
│       │   │   │   ├── components/         # Componentes UI reutilizables
│       │   │   │   ├── viewmodels/         # ViewModels (MVVM)
│       │   │   │   ├── navigation/         # Configuración navegación
│       │   │   │   └── theme/              # Tema y estilos UI
│       │   │   └── di/                     # Módulos inyección dependencias
│       │   ├── res/                        # Recursos Android
│       │   └── AndroidManifest.xml
│       ├── test/                           # Pruebas unitarias
│       └── androidTest/                     # Pruebas instrumentadas
├── gradle/                                 # Configuración Gradle
├── docs/                                   # Documentación
└── README.md
```

## 🚀 Installation

### Prerequisites
- **Android Studio**: Arctic Fox or later
- **Android SDK**: API 26 (Android 8.0) minimum
- **Kotlin**: 2.0.21 or later
- **Gradle**: 8.0 or later

### Setup Instructions

1. **Clone the repository**
   ```bash
   git clone https://github.com/your-username/fintrax.git
   cd fintrax
   ```

2. **Open in Android Studio**
   - Open Android Studio
   - Select "Open an existing project"
   - Navigate to the cloned repository directory

3. **Sync Gradle**
   - Android Studio will automatically prompt to sync the project
   - Wait for Gradle sync to complete

4. **Run the application**
   - Select an emulator or physical device
   - Click the "Run" button (▶️) or press `Ctrl+R`

### Build Variants

- **debug**: Development build with debugging enabled
- **release**: Production build with optimizations

## 📖 Usage

### Getting Started

1. **Launch the App**: Open Fintrax on your Android device
2. **Dashboard**: View your financial overview on the main dashboard
3. **Add Transactions**: Start recording income and expenses
4. **Set Monthly Expenses**: Configure recurring monthly expenses
5. **View Reports**: Analyze your financial patterns

### Main Features

#### Dashboard
- View current balance and monthly summary
- Quick access to all main features
- Recent transactions overview
- Monthly expenses preview

#### Transaction Management
- Add new income/expense transactions
- Categorize transactions automatically
- Edit or delete existing transactions
- Search and filter transactions

#### Reports & Analytics
- Visual charts for expense breakdown
- Income vs expense trends
- Category-wise spending analysis
- Export reports in various formats

#### Settings
- Configure currency and date formats
- Manage data backup and export
- Customize app appearance
- Privacy and security settings



### Development Workflow

1. **Fork the repository**
2. **Create a feature branch**
   ```bash
   git checkout -b feature/your-feature-name
   ```
3. **Make your changes**
4. **Add tests for new functionality**
5. **Run all tests**
   ```bash
   ./gradlew test connectedAndroidTest
   ```
6. **Commit your changes**
   ```bash
   git commit -m "feat: add your feature description"
   ```
7. **Push to your fork**
   ```bash
   git push origin feature/your-feature-name
   ```
8. **Create a Pull Request**

### Code Style

- Follow [Kotlin coding conventions](https://kotlinlang.org/docs/coding-conventions.html)
- Use [Detekt](https://detekt.dev/) for static analysis
- Write meaningful commit messages following [Conventional Commits](https://conventionalcommits.org/)
- Keep pull requests focused and well-documented
</div>
