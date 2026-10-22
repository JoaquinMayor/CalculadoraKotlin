# Calculadora Kotlin

Aplicación Android de calculadora básica desarrollada en Kotlin como **práctica de curso**. El objetivo es ejercitar la construcción de interfaces con XML (en particular `GridLayout` y estilos reutilizables), el uso de **View Binding** y la separación de la lógica de cálculo en una clase propia.

## Tecnologías

- **Lenguaje:** Kotlin (1.9.24)
- **Plataforma:** Android (`compileSdk` 35, `minSdk` 26, `targetSdk` 35)
- **Build:** Gradle con Kotlin DSL (`build.gradle.kts`) y catálogo de versiones (`gradle/libs.versions.toml`), Android Gradle Plugin 8.9.0
- **Librerías:** AndroidX Core KTX, AppCompat, Material Components, Activity, ConstraintLayout
- **View Binding** habilitado (`buildFeatures { viewBinding = true }`)
- Java 11 como target de compilación

## Estructura del proyecto

```
app/src/main/
├── AndroidManifest.xml                 # Declara MainActivity como actividad de inicio
├── java/com/example/calculadorakotlin/
│   ├── MainActivity.kt                 # UI: gestiona los clics de los botones y evalúa la expresión
│   └── Calculadora.kt                  # Lógica: acumulador con suma, resta, multiplicación, división y reinicio
└── res/
    ├── layout/activity_main.xml        # Teclado de la calculadora construido con GridLayout
    ├── layout/rotation.xml             # Layout de prueba (vertical) para practicar orientación
    ├── layout-land/rotation.xml        # Versión horizontal (landscape) del layout de prueba
    └── values/style_calcu.xml          # Estilos de botones (base, naranja y azul) con herencia de estilos
```

## Conceptos aplicados

- **Clases en Kotlin:** `Calculadora` encapsula un total privado (`Float`) y expone métodos para operar sobre él.
- **View Binding:** acceso a las vistas mediante `ActivityMainBinding` en lugar de `findViewById`.
- **Recorrido de vistas hijas:** se itera sobre los hijos del `GridLayout` y se asigna un único `OnClickListener` a cada `TextView`, diferenciando la acción por su `android:tag`.
- **Expresión `when`** para decidir la acción según el tag del botón y el operador.
- **Colecciones mutables** (`mutableListOf`) para separar números y operadores al evaluar la expresión.
- **Estilos y herencia de estilos** en XML (`AppTheme.BotonCalculadora` → `BotonNaranja` / `BotonAzul`).
- **Layouts para distintas orientaciones:** según los comentarios del código, se practica la creación de una variante *landscape* (`layout-land`) desde el modo diseño de Android Studio ("Create Landscape Variation").
- **Edge-to-edge** con `enableEdgeToEdge()` y manejo de *window insets*.

## Funcionalidades

- Teclado numérico (0–9) y punto decimal.
- Operaciones: suma (`+`), resta (`-`), multiplicación (`X`) y división (`÷`).
- Botón `=` que evalúa la expresión escrita en pantalla de izquierda a derecha (sin precedencia de operadores) y muestra el resultado.
- Tras un resultado, se puede seguir operando sobre el total acumulado.
- Botón `C` para reiniciar la calculadora.
- Botón de borrar el último carácter.

## Cómo ejecutarlo

### Requisitos

- Android Studio (versión compatible con AGP 8.9)
- JDK 11 o superior
- Android SDK 35
- Emulador o dispositivo con Android 8.0 (API 26) o superior

### Desde Android Studio

1. Abrir la carpeta del proyecto en Android Studio.
2. Esperar la sincronización de Gradle.
3. Ejecutar la configuración `app` sobre un emulador o dispositivo.

### Desde la línea de comandos

```bash
# Compilar el APK de depuración
./gradlew assembleDebug

# Instalar en un dispositivo/emulador conectado
./gradlew installDebug
```

En Windows usar `gradlew.bat` en lugar de `./gradlew`.
