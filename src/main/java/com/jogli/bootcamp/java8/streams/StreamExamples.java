package com.jogli.bootcamp.java8.streams;

import java.util.*;
import java.util.stream.*;

/**
 * Ejemplos de Streams en Java 8
 * 
 * Los streams permiten procesar colecciones de datos de manera funcional,
 * con operaciones intermedias (lazy) y terminales (eager).
 */
public class StreamExamples {
    
    public static void ejecutarEjemplos() {
        ejemplo1_OperacionesBasicas();
        ejemplo2_FiltradoYTransformacion();
        ejemplo3_OrdenamientoYLimitacion();
        ejemplo4_OperacionesMatematicas();
        ejemplo5_StreamsParalelos();
    }
    
    /**
     * Ejemplo 1: Operaciones básicas de streams
     * Demuestra la creación y operaciones básicas
     */
    public static void ejemplo1_OperacionesBasicas() {
        System.out.println("1. OPERACIONES BÁSICAS");
        System.out.println("----------------------");
        
        List<String> palabras = Arrays.asList("casa", "coche", "árbol", "libro", "sol");
        
        // Crear stream desde lista
        Stream<String> stream = palabras.stream();
        
        // forEach - operación terminal
        System.out.println("Palabras originales:");
        stream.forEach(palabra -> System.out.println("  - " + palabra));
        
        // count - contar elementos
        long cantidad = palabras.stream().count();
        System.out.println("Cantidad de palabras: " + cantidad);
        
        // distinct - eliminar duplicados
        List<String> palabrasConDuplicados = Arrays.asList("casa", "coche", "casa", "árbol", "coche");
        List<String> sinDuplicados = palabrasConDuplicados.stream()
            .distinct()
            .collect(Collectors.toList());
        System.out.println("Sin duplicados: " + sinDuplicados);
        
        // anyMatch, allMatch, noneMatch
        boolean hayPalabraLarga = palabras.stream().anyMatch(p -> p.length() > 4);
        boolean todasCortas = palabras.stream().allMatch(p -> p.length() <= 4);
        boolean ningunaVacia = palabras.stream().noneMatch(String::isEmpty);
        
        System.out.println("¿Hay palabra larga? " + hayPalabraLarga);
        System.out.println("¿Todas son cortas? " + todasCortas);
        System.out.println("¿Ninguna está vacía? " + ningunaVacia);
        System.out.println();
    }
    
    /**
     * Ejemplo 2: Filtrado y transformación
     * Demuestra filter() y map()
     */
    public static void ejemplo2_FiltradoYTransformacion() {
        System.out.println("2. FILTRADO Y TRANSFORMACIÓN");
        System.out.println("----------------------------");
        
        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        // Filtrar números pares
        List<Integer> pares = numeros.stream()
            .filter(n -> n % 2 == 0)
            .collect(Collectors.toList());
        System.out.println("Números pares: " + pares);
        
        // Transformar números (cuadrados)
        List<Integer> cuadrados = numeros.stream()
            .map(n -> n * n)
            .collect(Collectors.toList());
        System.out.println("Cuadrados: " + cuadrados);
        
        // Filtrar y transformar en cadena
        List<String> paresComoString = numeros.stream()
            .filter(n -> n % 2 == 0)
            .map(n -> "Par: " + n)
            .collect(Collectors.toList());
        System.out.println("Pares como string: " + paresComoString);
        
        // flatMap - aplanar streams anidados
        List<List<String>> listasAnidadas = Arrays.asList(
            Arrays.asList("a", "b"),
            Arrays.asList("c", "d", "e"),
            Arrays.asList("f")
        );
        
        List<String> aplanado = listasAnidadas.stream()
            .flatMap(List::stream)
            .collect(Collectors.toList());
        System.out.println("Lista aplanada: " + aplanado);
        
        // mapToInt, mapToDouble
        IntStream intStream = numeros.stream().mapToInt(Integer::intValue);
        DoubleStream doubleStream = numeros.stream().mapToDouble(Integer::doubleValue);
        
        System.out.println("Suma de enteros: " + intStream.sum());
        System.out.println("Promedio de doubles: " + doubleStream.average().orElse(0.0));
        System.out.println();
    }
    
    /**
     * Ejemplo 3: Ordenamiento y limitación
     * Demuestra sorted(), limit(), skip()
     */
    public static void ejemplo3_OrdenamientoYLimitacion() {
        System.out.println("3. ORDENAMIENTO Y LIMITACIÓN");
        System.out.println("---------------------------");
        
        List<String> nombres = Arrays.asList("Carlos", "Ana", "Beatriz", "David", "Elena");
        
        // Ordenamiento natural
        List<String> ordenados = nombres.stream()
            .sorted()
            .collect(Collectors.toList());
        System.out.println("Ordenados alfabéticamente: " + ordenados);
        
        // Ordenamiento por longitud
        List<String> ordenadosPorLongitud = nombres.stream()
            .sorted(Comparator.comparing(String::length))
            .collect(Collectors.toList());
        System.out.println("Ordenados por longitud: " + ordenadosPorLongitud);
        
        // Ordenamiento descendente
        List<String> ordenadosDesc = nombres.stream()
            .sorted(Comparator.reverseOrder())
            .collect(Collectors.toList());
        System.out.println("Ordenados descendente: " + ordenadosDesc);
        
        // limit - tomar solo los primeros elementos
        List<String> primeros3 = nombres.stream()
            .sorted()
            .limit(3)
            .collect(Collectors.toList());
        System.out.println("Primeros 3 ordenados: " + primeros3);
        
        // skip - saltar elementos
        List<String> saltando2 = nombres.stream()
            .sorted()
            .skip(2)
            .collect(Collectors.toList());
        System.out.println("Saltando los primeros 2: " + saltando2);
        
        // Combinar limit y skip
        List<String> delMedio = nombres.stream()
            .sorted()
            .skip(1)
            .limit(2)
            .collect(Collectors.toList());
        System.out.println("Del medio (skip 1, limit 2): " + delMedio);
        System.out.println();
    }
    
    /**
     * Ejemplo 4: Operaciones matemáticas
     * Demuestra operaciones de agregación
     */
    public static void ejemplo4_OperacionesMatematicas() {
        System.out.println("4. OPERACIONES MATEMÁTICAS");
        System.out.println("-------------------------");
        
        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        // Suma
        int suma = numeros.stream()
            .mapToInt(Integer::intValue)
            .sum();
        System.out.println("Suma total: " + suma);
        
        // Promedio
        OptionalDouble promedio = numeros.stream()
            .mapToInt(Integer::intValue)
            .average();
        promedio.ifPresent(p -> System.out.println("Promedio: " + p));
        
        // Máximo y mínimo
        OptionalInt maximo = numeros.stream()
            .mapToInt(Integer::intValue)
            .max();
        OptionalInt minimo = numeros.stream()
            .mapToInt(Integer::intValue)
            .min();
        
        maximo.ifPresent(max -> System.out.println("Máximo: " + max));
        minimo.ifPresent(min -> System.out.println("Mínimo: " + min));
        
        // reduce - operación personalizada
        int producto = numeros.stream()
            .reduce(1, (a, b) -> a * b);
        System.out.println("Producto: " + producto);
        
        // reduce con Optional
        Optional<Integer> sumaOpcional = numeros.stream()
            .reduce(Integer::sum);
        sumaOpcional.ifPresent(s -> System.out.println("Suma con reduce: " + s));
        
        // Operaciones con filtros
        int sumaPares = numeros.stream()
            .filter(n -> n % 2 == 0)
            .mapToInt(Integer::intValue)
            .sum();
        System.out.println("Suma de pares: " + sumaPares);
        
        double promedioPares = numeros.stream()
            .filter(n -> n % 2 == 0)
            .mapToDouble(Integer::doubleValue)
            .average()
            .orElse(0.0);
        System.out.println("Promedio de pares: " + promedioPares);
        System.out.println();
    }
    
    /**
     * Ejemplo 5: Streams paralelos
     * Demuestra el procesamiento paralelo
     */
    public static void ejemplo5_StreamsParalelos() {
        System.out.println("5. STREAMS PARALELOS");
        System.out.println("-------------------");
        
        // Crear lista grande para demostrar paralelismo
        List<Integer> numerosGrandes = IntStream.range(1, 1000001)
            .boxed()
            .collect(Collectors.toList());
        
        // Stream secuencial
        long inicioSecuencial = System.currentTimeMillis();
        long sumaSecuencial = numerosGrandes.stream()
            .mapToLong(Integer::longValue)
            .sum();
        long finSecuencial = System.currentTimeMillis();
        
        // Stream paralelo
        long inicioParalelo = System.currentTimeMillis();
        long sumaParalela = numerosGrandes.parallelStream()
            .mapToLong(Integer::longValue)
            .sum();
        long finParalelo = System.currentTimeMillis();
        
        System.out.println("Suma secuencial: " + sumaSecuencial);
        System.out.println("Tiempo secuencial: " + (finSecuencial - inicioSecuencial) + " ms");
        System.out.println("Suma paralela: " + sumaParalela);
        System.out.println("Tiempo paralelo: " + (finParalelo - inicioParalelo) + " ms");
        
        // Procesamiento paralelo con filtros
        List<Integer> numerosPares = numerosGrandes.parallelStream()
            .filter(n -> n % 2 == 0)
            .limit(10)
            .collect(Collectors.toList());
        System.out.println("Primeros 10 pares (paralelo): " + numerosPares);
        
        // Ordenamiento paralelo
        List<Integer> ordenadosParalelo = numerosGrandes.parallelStream()
            .limit(1000)
            .sorted()
            .collect(Collectors.toList());
        System.out.println("Primeros 1000 ordenados (paralelo): " + ordenadosParalelo.size() + " elementos");
        System.out.println();
    }
} 