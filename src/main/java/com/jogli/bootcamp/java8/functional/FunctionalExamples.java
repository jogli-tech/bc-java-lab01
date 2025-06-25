package com.jogli.bootcamp.java8.functional;

import java.util.*;
import java.util.function.*;

/**
 * Ejemplos de Interfaces Funcionales en Java 8
 */
public class FunctionalExamples {
    
    public static void ejecutarEjemplos() {
        System.out.println("1. INTERFACES FUNCIONALES BÁSICAS");
        System.out.println("-------------------------------");
        
        // Predicate<T> - evalúa una condición
        Predicate<String> esVacio = String::isEmpty;
        Predicate<String> esLargo = str -> str.length() > 5;
        
        String texto = "hola mundo";
        System.out.println("Texto: " + texto);
        System.out.println("¿Está vacío? " + esVacio.test(texto));
        System.out.println("¿Es largo? " + esLargo.test(texto));
        
        // Function<T, R> - transforma un valor
        Function<String, Integer> longitud = String::length;
        Function<String, String> mayusculas = String::toUpperCase;
        
        System.out.println("Longitud: " + longitud.apply(texto));
        System.out.println("En mayúsculas: " + mayusculas.apply(texto));
        
        // Consumer<T> - consume un valor (no retorna nada)
        Consumer<String> imprimir = System.out::println;
        System.out.print("Impresión: ");
        imprimir.accept(texto);
        
        // Supplier<T> - provee un valor
        Supplier<String> saludo = () -> "¡Hola desde supplier!";
        System.out.println("Saludo: " + saludo.get());
        System.out.println();
    }
}
