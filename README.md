# Ejemplos de Características de Java 8

Este proyecto contiene ejemplos prácticos organizados por características que demuestran las funcionalidades más importantes de Java 8: **lambdas**, **streams**, **optionals**, **collectors** e **interfaces funcionales**.

## 🚀 Características Demostradas

### 📦 **Estructura del Proyecto**
El proyecto está organizado en packages que separan las diferentes características de Java 8:

- `com.jogli.bootcamp.java8.lambdas` - Ejemplos de expresiones lambda
- `com.jogli.bootcamp.java8.streams` - Ejemplos de streams y operaciones
- `com.jogli.bootcamp.java8.optionals` - Ejemplos de Optional para manejo de nulos
- `com.jogli.bootcamp.java8.collectors` - Ejemplos de collectors para agregación
- `com.jogli.bootcamp.java8.functional` - Ejemplos de interfaces funcionales

### 1. **Lambdas**
- Sintaxis básica de lambdas
- Lambdas con parámetros
- Method references
- Interfaces funcionales personalizadas
- Composición de lambdas

### 2. **Streams**
- Operaciones básicas (filter, map, forEach)
- Filtrado y transformación
- Ordenamiento y limitación (sorted, limit, skip)
- Operaciones matemáticas (sum, average, max, min)
- Streams paralelos

### 3. **Optionals**
- Creación y métodos básicos
- Métodos de acceso seguros (orElse, ifPresent)
- Transformación y filtrado (map, flatMap, filter)
- Combinación de Optionals
- Casos de uso prácticos

### 4. **Collectors**
- Recolección básica (toList, toSet, toMap)
- Agrupación (groupingBy)
- Particionado (partitioningBy)
- Operaciones de agregación (counting, summingInt)
- Collectors personalizados

### 5. **Interfaces Funcionales**
- Interfaces básicas (Predicate, Function, Consumer, Supplier)
- Interfaces personalizadas
- Composición de funciones
- Interfaces avanzadas (UnaryOperator, BinaryOperator)
- Casos de uso prácticos

## 📋 Requisitos

- Java 8 o superior
- Gradle 7.6 o superior (incluido con Gradle Wrapper)

## 🛠️ Instalación y Ejecución

### Compilar el proyecto:
```bash
./gradlew build
```

### Ejecutar los ejemplos:
```bash
./gradlew run
```

### O usar la tarea personalizada:
```bash
./gradlew runExamples
```

### Ejecutar directamente con Java:
```bash
# Compilar
./gradlew compileJava

# Ejecutar
java -cp build/classes/java/main com.jogli.bootcamp.java8.Main
```

## 📚 Conceptos Clave de Java 8

### **Lambdas (Expresiones Lambda)**
```java
// Sintaxis básica: (parámetros) -> { cuerpo }
nombres.stream()
    .filter(nombre -> nombre.startsWith("A"))
    .forEach(nombre -> System.out.println(nombre));
```

### **Method References**
```java
// Referencia a método estático
.map(String::toUpperCase)

// Referencia a método de instancia
.sorted(Comparator.comparing(Persona::getNombre))
```

### **Streams**
```java
// Operaciones intermedias (lazy)
.filter()
.map()
.sorted()
.limit()

// Operaciones terminales (eager)
.collect()
.forEach()
.sum()
.count()
```

### **Collectors**
```java
// Recolectar en lista
.collect(Collectors.toList())

// Agrupar elementos
.collect(Collectors.groupingBy(String::length))

// Contar elementos
.collect(Collectors.counting())

// Unir strings
.collect(Collectors.joining(" - "))
```

## 🎯 Beneficios de Java 8

1. **Código más legible**: Las lambdas hacen el código más expresivo
2. **Menos verboso**: Elimina la necesidad de clases anónimas
3. **Programación funcional**: Permite un estilo de programación más funcional
4. **Mejor rendimiento**: Los streams pueden ser paralelos
5. **Menos errores**: Reduce el código boilerplate

## 📖 Ejemplos de Salida

Al ejecutar el programa, verás ejemplos como:

```
=== EJEMPLOS DE CARACTERÍSTICAS DE JAVA 8 ===

🔸 EJEMPLOS DE LAMBDAS
=======================
1. LAMBDAS BÁSICAS
-------------------
Hola desde lambda!
Mensaje: ¡Hola mundo!
Suma: 8
...

🔸 EJEMPLOS DE STREAMS
=======================
1. OPERACIONES BÁSICAS
----------------------
Palabras originales:
  - casa
  - coche
...

🔸 EJEMPLOS DE OPTIONALS
=========================
1. CREACIÓN Y MÉTODOS BÁSICOS
-----------------------------
Optional con valor: Optional[Hola mundo]
...

🔸 EJEMPLOS DE COLLECTORS
==========================
1. RECOLECCIÓN BÁSICA
---------------------
Lista filtrada: [casa, coche, árbol, libro]
...

🔸 EJEMPLOS DE INTERFACES FUNCIONALES
=====================================
1. INTERFACES FUNCIONALES BÁSICAS
-------------------------------
Texto: hola mundo
¿Está vacío? false
...
```

## 🔧 Personalización

Puedes modificar los ejemplos en cualquiera de los packages para experimentar con diferentes casos de uso:

- `src/main/java/com/jogli/bootcamp/java8/lambdas/LambdaExamples.java`
- `src/main/java/com/jogli/bootcamp/java8/streams/StreamExamples.java`
- `src/main/java/com/jogli/bootcamp/java8/optionals/OptionalExamples.java`
- `src/main/java/com/jogli/bootcamp/java8/collectors/CollectorExamples.java`
- `src/main/java/com/jogli/bootcamp/java8/functional/FunctionalExamples.java`

## 📝 Notas Adicionales

- Los streams son **lazy**: las operaciones intermedias no se ejecutan hasta que se llama a una operación terminal
- Los streams pueden ser **secuenciales** o **paralelos** usando `parallelStream()`
- Las lambdas pueden capturar variables del ámbito externo (efectivamente finales)
- Los method references son una forma más concisa de escribir lambdas simples