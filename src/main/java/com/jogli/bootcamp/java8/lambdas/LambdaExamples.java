package com.jogli.bootcamp.java8.lambdas;

import java.util.*;
import java.util.function.*;

/**
 * Ejemplos de Lambdas en Java 8
 * 
 * Las lambdas son expresiones que implementan interfaces funcionales
 * de manera concisa y legible.
 */
public class LambdaExamples {
    
    public static void ejecutarEjemplos() {
        ejemplo1_LambdasBasicas();
        ejemplo2_LambdasConParametros();
        ejemplo3_LambdasEnColecciones();
        ejemplo4_MethodReferences();
        ejemplo5_InterfacesFuncionales();
    }
    
    /**
     * Ejemplo 1: Lambdas básicas
     * Demuestra la sintaxis básica de las lambdas
     */
    public static void ejemplo1_LambdasBasicas() {
        System.out.println("1. LAMBDAS BÁSICAS");
        System.out.println("-------------------");
        
        // Lambda sin parámetros
        Runnable runnable = () -> System.out.println("Hola desde lambda!");
        runnable.run();
        
        // Lambda con un parámetro
        Consumer<String> consumer = (String mensaje) -> System.out.println("Mensaje: " + mensaje);
        consumer.accept("¡Hola mundo!");
        
        // Lambda con inferencia de tipos
        Consumer<String> consumer2 = mensaje -> System.out.println("Mensaje inferido: " + mensaje);
        consumer2.accept("¡Tipos inferidos!");
        
        // Lambda con múltiples parámetros
        BiFunction<Integer, Integer, Integer> suma = (a, b) -> a + b;
        System.out.println("Suma: " + suma.apply(5, 3));
        
        // Lambda con bloque de código
        BiFunction<Integer, Integer, Integer> multiplicacion = (a, b) -> {
            int resultado = a * b;
            System.out.println("Multiplicando " + a + " * " + b + " = " + resultado);
            return resultado;
        };
        multiplicacion.apply(4, 6);
        System.out.println();
    }
    
    /**
     * Ejemplo 2: Lambdas con parámetros
     * Demuestra diferentes formas de usar parámetros en lambdas
     */
    public static void ejemplo2_LambdasConParametros() {
        System.out.println("2. LAMBDAS CON PARÁMETROS");
        System.out.println("-------------------------");
        
        // Predicate: evalúa una condición
        Predicate<String> esVacio = str -> str.isEmpty();
        Predicate<String> esLargo = str -> str.length() > 5;
        
        List<String> palabras = Arrays.asList("", "hola", "programación", "java", "lambda");
        
        System.out.println("Palabras originales: " + palabras);
        System.out.println("¿Está vacía 'hola'? " + esVacio.test("hola"));
        System.out.println("¿Es largo 'programación'? " + esLargo.test("programación"));
        
        // Combinar predicates
        Predicate<String> noVacioYLargo = esVacio.negate().and(esLargo);
        List<String> filtradas = new ArrayList<>();
        for (String palabra : palabras) {
            if (noVacioYLargo.test(palabra)) {
                filtradas.add(palabra);
            }
        }
        System.out.println("Palabras no vacías y largas: " + filtradas);
        
        // Function: transforma un valor
        Function<String, Integer> longitud = String::length;
        Function<String, String> mayusculas = String::toUpperCase;
        
        String texto = "hola mundo";
        System.out.println("Texto original: " + texto);
        System.out.println("Longitud: " + longitud.apply(texto));
        System.out.println("En mayúsculas: " + mayusculas.apply(texto));
        System.out.println();
    }
    
    /**
     * Ejemplo 3: Lambdas en colecciones
     * Demuestra el uso de lambdas con listas y mapas
     */
    public static void ejemplo3_LambdasEnColecciones() {
        System.out.println("3. LAMBDAS EN COLECCIONES");
        System.out.println("-------------------------");
        
        List<String> nombres = Arrays.asList("Ana", "Carlos", "Beatriz", "David");
        
        // forEach con lambda
        System.out.println("Nombres originales:");
        nombres.forEach(nombre -> System.out.println("  - " + nombre));
        
        // removeIf con lambda
        List<String> nombresCopia = new ArrayList<>(nombres);
        nombresCopia.removeIf(nombre -> nombre.startsWith("A"));
        System.out.println("Nombres sin 'A': " + nombresCopia);
        
        // replaceAll con lambda
        List<String> nombresModificados = new ArrayList<>(nombres);
        nombresModificados.replaceAll(String::toLowerCase);
        System.out.println("Nombres en minúsculas: " + nombresModificados);
        
        // Map con lambdas
        Map<String, Integer> edades = new HashMap<>();
        edades.put("Ana", 25);
        edades.put("Carlos", 30);
        edades.put("Beatriz", 22);
        
        // forEach en map
        System.out.println("Edades:");
        edades.forEach((nombre, edad) -> 
            System.out.println("  " + nombre + " tiene " + edad + " años"));
        
        // computeIfAbsent
        edades.computeIfAbsent("David", nombre -> 28);
        System.out.println("Edades después de agregar David: " + edades);
        System.out.println();
    }
    
    /**
     * Ejemplo 4: Method References
     * Demuestra las referencias a métodos como forma concisa de lambdas
     */
    public static void ejemplo4_MethodReferences() {
        System.out.println("4. METHOD REFERENCES");
        System.out.println("--------------------");
        
        List<String> palabras = Arrays.asList("casa", "coche", "árbol", "libro");
        
        // Referencia a método estático
        palabras.forEach(System.out::println);
        
        // Referencia a método de instancia
        List<String> mayusculas = new ArrayList<>();
        palabras.forEach(mayusculas::add);
        System.out.println("Lista en mayúsculas: " + mayusculas);
        
        // Referencia a constructor
        List<String> palabrasList = palabras.stream()
            .map(String::new)
            .collect(java.util.stream.Collectors.toList());
        System.out.println("Nuevas instancias: " + palabrasList);
        
        // Referencia a método de instancia de clase arbitraria
        List<Integer> longitudes = palabras.stream()
            .map(String::length)
            .collect(java.util.stream.Collectors.toList());
        System.out.println("Longitudes: " + longitudes);
        
        // Referencia a método estático personalizado
        List<String> palabrasFormateadas = palabras.stream()
            .map(LambdaExamples::formatearPalabra)
            .collect(java.util.stream.Collectors.toList());
        System.out.println("Palabras formateadas: " + palabrasFormateadas);
        System.out.println();
    }
    
    /**
     * Ejemplo 5: Interfaces funcionales personalizadas
     * Demuestra cómo crear y usar interfaces funcionales propias
     */
    public static void ejemplo5_InterfacesFuncionales() {
        System.out.println("5. INTERFACES FUNCIONALES PERSONALIZADAS");
        System.out.println("----------------------------------------");
        
        // Interfaz funcional personalizada
        Calculadora suma = (a, b) -> a + b;
        Calculadora multiplicacion = (a, b) -> a * b;
        Calculadora potencia = (a, b) -> (int) Math.pow(a, b);
        
        System.out.println("5 + 3 = " + suma.calcular(5, 3));
        System.out.println("4 * 6 = " + multiplicacion.calcular(4, 6));
        System.out.println("2 ^ 8 = " + potencia.calcular(2, 8));
        
        // Interfaz funcional con método por defecto
        Validador<String> validadorLargo = str -> str.length() > 5;
        Validador<String> validadorVocal = str -> str.matches(".*[aeiou].*");
        
        String texto = "programación";
        System.out.println("Texto: " + texto);
        System.out.println("¿Es largo? " + validadorLargo.validar(texto));
        System.out.println("¿Tiene vocales? " + validadorVocal.validar(texto));
        System.out.println("¿Es válido? " + validadorLargo.and(validadorVocal).validar(texto));
        System.out.println();
    }
    
    // Método auxiliar para method references
    public static String formatearPalabra(String palabra) {
        return "[" + palabra.toUpperCase() + "]";
    }
    
    // Interfaz funcional personalizada
    @FunctionalInterface
    interface Calculadora {
        int calcular(int a, int b);
    }
    
    // Interfaz funcional con método por defecto
    @FunctionalInterface
    interface Validador<T> {
        boolean validar(T t);
        
        default Validador<T> and(Validador<T> otro) {
            return t -> this.validar(t) && otro.validar(t);
        }
    }
} 