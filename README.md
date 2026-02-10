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

## 🚀 Instalación

### Prerrequisitos
- **Android Studio**: Arctic Fox o superior
- **Android SDK**: API 26 (Android 8.0) mínimo
- **Kotlin**: 2.0.21 o superior
- **Gradle**: 8.0 o superior

### Instrucciones de Configuración

1. **Clonar el repositorio**
   ```bash
   git clone https://github.com/your-username/fintrax.git
   cd fintrax
   ```

2. **Abrir en Android Studio**
   - Abrir Android Studio
   - Seleccionar "Open an existing project"
   - Navegar al directorio del repositorio clonado

3. **Sincronizar Gradle**
   - Android Studio solicitará automáticamente sincronizar el proyecto
   - Esperar a que se complete la sincronización de Gradle

4. **Ejecutar la aplicación**
   - Seleccionar un emulador o dispositivo físico
   - Hacer clic en el botón "Run" o presionar `Ctrl+R`

### Variantes de Build

- **debug**: Build de desarrollo con depuración habilitada
- **release**: Build de producción con optimizaciones

## 📖 Uso

### Primeros Pasos

1. **Iniciar la App**: Abrir Fintrax en tu dispositivo Android
2. **Panel Principal**: Ver tu resumen financiero en el panel principal
3. **Agregar Transacciones**: Comenzar a registrar ingresos y gastos
4. **Configurar Gastos Mensuales**: Configurar gastos mensuales recurrentes
5. **Ver Informes**: Analizar tus patrones financieros

### Características Principales

#### Panel Principal
- Ver balance actual y resumen mensual
- Acceso rápido a todas las características principales
- Vista general de transacciones recientes
- Vista previa de gastos mensuales

#### Gestión de Transacciones
- Agregar nuevas transacciones de ingresos/gastos
- Categorizar transacciones automáticamente
- Editar o eliminar transacciones existentes
- Buscar y filtrar transacciones

#### Informes y Análisis
- Gráficos visuales para desglose de gastos
- Tendencias de ingresos vs gastos
- Análisis de gastos por categoría
- Exportar informes en varios formatos

#### Configuración
- Configurar formatos de moneda y fecha
- Gestionar respaldo y exportación de datos
- Personalizar apariencia de la app
- Configuración de privacidad y seguridad

### Flujo de Desarrollo

1. **Bifurcar el repositorio**
2. **Crear una rama de característica**
   ```bash
   git checkout -b feature/your-feature-name
   ```
3. **Realizar tus cambios**
4. **Agregar pruebas para nueva funcionalidad**
5. **Ejecutar todas las pruebas**
   ```bash
   ./gradlew test connectedAndroidTest
   ```
6. **Confirmar tus cambios**
   ```bash
   git commit -m "feat: add your feature description"
   ```
7. **Enviar a tu bifurcación**
   ```bash
   git push origin feature/your-feature-name
   ```
8. **Crear un Pull Request**

<div align="center">

[⭐ Dar estrella a este repo](https://github.com/your-username/fintrax) | [🐛 Reportar problemas](https://github.com/your-username/fintrax/issues) | [💡 Sugerir características](https://github.com/your-username/fintrax/discussions)

</div>
</div>
