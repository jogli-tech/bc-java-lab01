package com.jogli.bootcamp.java8;

import com.jogli.bootcamp.java8.lambdas.LambdaExamples;
import com.jogli.bootcamp.java8.streams.StreamExamples;

/**
 * Clase principal que ejecuta todos los ejemplos de Java 8
 * 
 * Esta clase demuestra las características más importantes de Java 8:
 * - Lambdas y expresiones funcionales
 * - Streams y operaciones de flujo
 * - Optionals para manejo de valores nulos
 * - Collectors para agrupación y transformación
 * - Interfaces funcionales
 */
public class Main {
    
    public static void main(String[] args) {
        System.out.println("=== EJEMPLOS DE CARACTERÍSTICAS DE JAVA 8 ===\n");
        
        // Ejecutar ejemplos de lambdas
        System.out.println("🔸 EJEMPLOS DE LAMBDAS");
        System.out.println("=======================");
        LambdaExamples.ejecutarEjemplos();
        System.out.println();
        
        // Ejecutar ejemplos de streams
        System.out.println("🔸 EJEMPLOS DE STREAMS");
        System.out.println("=======================");
        StreamExamples.ejecutarEjemplos();
        System.out.println();
    
    }
} 