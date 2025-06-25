package com.jogli.bootcamp.java8.collectors;

import java.util.*;
import java.util.stream.*;

/**
 * Ejemplos de Collectors en Java 8
 * 
 * Los collectors permiten recolectar elementos de streams en diferentes
 * tipos de colecciones y realizar operaciones de agregación.
 */
public class CollectorExamples {
    
    public static void ejecutarEjemplos() {
        ejemplo1_RecoleccionBasica();
        ejemplo2_Agrupacion();
        ejemplo3_Particionado();
        ejemplo4_OperacionesDeAgregacion();
        ejemplo5_CollectorsPersonalizados();
    }
    
    /**
     * Ejemplo 1: Recolección básica
     * Demuestra los collectors más comunes
     */
    public static void ejemplo1_RecoleccionBasica() {
        System.out.println("1. RECOLECCIÓN BÁSICA");
        System.out.println("---------------------");
        
        List<String> palabras = Arrays.asList("casa", "coche", "árbol", "libro", "sol");
        
        // Recolectar en List
        List<String> lista = palabras.stream()
            .filter(p -> p.length() > 3)
            .collect(Collectors.toList());
        System.out.println("Lista filtrada: " + lista);
        
        // Recolectar en Set (elimina duplicados)
        Set<String> conjunto = palabras.stream()
            .map(String::toUpperCase)
            .collect(Collectors.toSet());
        System.out.println("Conjunto en mayúsculas: " + conjunto);
        
        // Recolectar en ArrayList específico
        ArrayList<String> arrayList = palabras.stream()
            .collect(Collectors.toCollection(ArrayList::new));
        System.out.println("ArrayList: " + arrayList);
        
        // Recolectar en LinkedList
        LinkedList<String> linkedList = palabras.stream()
            .collect(Collectors.toCollection(LinkedList::new));
        System.out.println("LinkedList: " + linkedList);
        
        // Recolectar en Map
        Map<String, Integer> mapa = palabras.stream()
            .collect(Collectors.toMap(
                palabra -> palabra,
                String::length
            ));
        System.out.println("Mapa palabra -> longitud: " + mapa);
        
        // Recolectar en Map con manejo de duplicados
        List<String> palabrasConDuplicados = Arrays.asList("casa", "coche", "casa", "árbol");
        Map<String, Integer> mapaConDuplicados = palabrasConDuplicados.stream()
            .collect(Collectors.toMap(
                palabra -> palabra,
                String::length,
                (existente, nuevo) -> existente // mantener el existente
            ));
        System.out.println("Mapa con duplicados manejados: " + mapaConDuplicados);
        System.out.println();
    }
    
    /**
     * Ejemplo 2: Agrupación
     * Demuestra groupingBy para agrupar elementos
     */
    public static void ejemplo2_Agrupacion() {
        System.out.println("2. AGRUPACIÓN");
        System.out.println("-------------");
        
        List<String> palabras = Arrays.asList("casa", "coche", "árbol", "libro", "sol", "mar");
        
        // Agrupar por longitud
        Map<Integer, List<String>> porLongitud = palabras.stream()
            .collect(Collectors.groupingBy(String::length));
        System.out.println("Agrupadas por longitud: " + porLongitud);
        
        // Agrupar por primera letra
        Map<String, List<String>> porPrimeraLetra = palabras.stream()
            .collect(Collectors.groupingBy(palabra -> 
                palabra.substring(0, 1).toUpperCase()));
        System.out.println("Agrupadas por primera letra: " + porPrimeraLetra);
        
        // Agrupar y contar
        Map<Integer, Long> conteoPorLongitud = palabras.stream()
            .collect(Collectors.groupingBy(
                String::length,
                Collectors.counting()
            ));
        System.out.println("Conteo por longitud: " + conteoPorLongitud);
        
        // Agrupar y transformar
        Map<Integer, List<String>> mayusculasPorLongitud = palabras.stream()
            .collect(Collectors.groupingBy(
                String::length,
                Collectors.mapping(
                    String::toUpperCase,
                    Collectors.toList()
                )
            ));
        System.out.println("Mayúsculas por longitud: " + mayusculasPorLongitud);
        
        // Agrupar con múltiples criterios
        Map<String, Map<Integer, List<String>>> porLetraYLongitud = palabras.stream()
            .collect(Collectors.groupingBy(
                palabra -> palabra.substring(0, 1).toUpperCase(),
                Collectors.groupingBy(String::length)
            ));
        System.out.println("Por letra y longitud: " + porLetraYLongitud);
        
        // Agrupar en TreeMap (ordenado)
        TreeMap<Integer, List<String>> ordenadoPorLongitud = palabras.stream()
            .collect(Collectors.groupingBy(
                String::length,
                TreeMap::new,
                Collectors.toList()
            ));
        System.out.println("Ordenado por longitud: " + ordenadoPorLongitud);
        System.out.println();
    }
    
    /**
     * Ejemplo 3: Particionado
     * Demuestra partitioningBy para dividir en dos grupos
     */
    public static void ejemplo3_Particionado() {
        System.out.println("3. PARTICIONADO");
        System.out.println("---------------");
        
        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        // Particionar por pares/impares
        Map<Boolean, List<Integer>> paresEImpares = numeros.stream()
            .collect(Collectors.partitioningBy(n -> n % 2 == 0));
        System.out.println("Pares e impares: " + paresEImpares);
        System.out.println("Pares: " + paresEImpares.get(true));
        System.out.println("Impares: " + paresEImpares.get(false));
        
        // Particionar y contar
        Map<Boolean, Long> conteoParesImpares = numeros.stream()
            .collect(Collectors.partitioningBy(
                n -> n % 2 == 0,
                Collectors.counting()
            ));
        System.out.println("Conteo pares/impares: " + conteoParesImpares);
        
        // Particionar y sumar
        Map<Boolean, Integer> sumaParesImpares = numeros.stream()
            .collect(Collectors.partitioningBy(
                n -> n % 2 == 0,
                Collectors.summingInt(Integer::intValue)
            ));
        System.out.println("Suma pares/impares: " + sumaParesImpares);
        
        // Particionar strings por longitud
        List<String> palabras = Arrays.asList("casa", "coche", "árbol", "libro", "sol");
        Map<Boolean, List<String>> cortasYLargas = palabras.stream()
            .collect(Collectors.partitioningBy(palabra -> palabra.length() > 4));
        System.out.println("Cortas y largas: " + cortasYLargas);
        
        // Particionar y transformar
        Map<Boolean, List<String>> mayusculasCortasYLargas = palabras.stream()
            .collect(Collectors.partitioningBy(
                palabra -> palabra.length() > 4,
                Collectors.mapping(
                    String::toUpperCase,
                    Collectors.toList()
                )
            ));
        System.out.println("Mayúsculas cortas y largas: " + mayusculasCortasYLargas);
        System.out.println();
    }
    
    /**
     * Ejemplo 4: Operaciones de agregación
     * Demuestra collectors para operaciones matemáticas
     */
    public static void ejemplo4_OperacionesDeAgregacion() {
        System.out.println("4. OPERACIONES DE AGREGACIÓN");
        System.out.println("----------------------------");
        
        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        // Contar elementos
        Long cantidad = numeros.stream()
            .collect(Collectors.counting());
        System.out.println("Cantidad total: " + cantidad);
        
        // Sumar elementos
        Integer suma = numeros.stream()
            .collect(Collectors.summingInt(Integer::intValue));
        System.out.println("Suma total: " + suma);
        
        // Promedio
        Double promedio = numeros.stream()
            .collect(Collectors.averagingInt(Integer::intValue));
        System.out.println("Promedio: " + promedio);
        
        // Estadísticas completas
        IntSummaryStatistics estadisticas = numeros.stream()
            .collect(Collectors.summarizingInt(Integer::intValue));
        System.out.println("Estadísticas: " + estadisticas);
        System.out.println("  - Conteo: " + estadisticas.getCount());
        System.out.println("  - Suma: " + estadisticas.getSum());
        System.out.println("  - Promedio: " + estadisticas.getAverage());
        System.out.println("  - Mínimo: " + estadisticas.getMin());
        System.out.println("  - Máximo: " + estadisticas.getMax());
        
        // Máximo y mínimo
        Optional<Integer> maximo = numeros.stream()
            .collect(Collectors.maxBy(Integer::compareTo));
        Optional<Integer> minimo = numeros.stream()
            .collect(Collectors.minBy(Integer::compareTo));
        
        maximo.ifPresent(max -> System.out.println("Máximo: " + max));
        minimo.ifPresent(min -> System.out.println("Mínimo: " + min));
        
        // Reducción personalizada
        Integer producto = numeros.stream()
            .collect(Collectors.reducing(1, (a, b) -> a * b));
        System.out.println("Producto: " + producto);
        
        // Reducción con Optional
        Optional<Integer> sumaOpcional = numeros.stream()
            .collect(Collectors.reducing(Integer::sum));
        sumaOpcional.ifPresent(s -> System.out.println("Suma con reducing: " + s));
        
        // Agrupar y aplicar estadísticas
        List<String> palabras = Arrays.asList("casa", "coche", "árbol", "libro", "sol");
        Map<Integer, IntSummaryStatistics> estadisticasPorLongitud = palabras.stream()
            .collect(Collectors.groupingBy(
                String::length,
                Collectors.summarizingInt(String::length)
            ));
        System.out.println("Estadísticas por longitud: " + estadisticasPorLongitud);
        System.out.println();
    }
    
    /**
     * Ejemplo 5: Collectors personalizados
     * Demuestra cómo crear collectors personalizados
     */
    public static void ejemplo5_CollectorsPersonalizados() {
        System.out.println("5. COLLECTORS PERSONALIZADOS");
        System.out.println("---------------------------");
        
        List<String> palabras = Arrays.asList("casa", "coche", "árbol", "libro", "sol");
        
        // Unir strings con separador
        String resultado = palabras.stream()
            .collect(Collectors.joining(" - "));
        System.out.println("Palabras unidas: " + resultado);
        
        // Unir con prefijo y sufijo
        String resultadoConPrefijo = palabras.stream()
            .collect(Collectors.joining(", ", "[", "]"));
        System.out.println("Con prefijo y sufijo: " + resultadoConPrefijo);
        
        // Collector personalizado para concatenar con formato
        String formatoPersonalizado = palabras.stream()
            .collect(Collectors.collectingAndThen(
                Collectors.joining(" | "),
                str -> ">>> " + str + " <<<"
            ));
        System.out.println("Formato personalizado: " + formatoPersonalizado);
        
        // Collector para obtener el primer y último elemento
        List<String> primerYUltimo = palabras.stream()
            .collect(Collectors.collectingAndThen(
                Collectors.toList(),
                lista -> {
                    if (lista.size() >= 2) {
                        return Arrays.asList(lista.get(0), lista.get(lista.size() - 1));
                    }
                    return lista;
                }
            ));
        System.out.println("Primer y último: " + primerYUltimo);
        
        // Collector para obtener elementos únicos ordenados
        List<String> unicosOrdenados = palabras.stream()
            .collect(Collectors.collectingAndThen(
                Collectors.toSet(),
                set -> set.stream().sorted().collect(Collectors.toList())
            ));
        System.out.println("Únicos ordenados: " + unicosOrdenados);
        
        // Collector personalizado para estadísticas de strings
        EstadisticasString estadisticas = palabras.stream()
            .collect(Collectors.collectingAndThen(
                Collectors.toList(),
                lista -> {
                    int totalCaracteres = lista.stream().mapToInt(String::length).sum();
                    double promedio = lista.stream().mapToDouble(String::length).average().orElse(0.0);
                    String masLarga = lista.stream().max(Comparator.comparing(String::length)).orElse("");
                    String masCorta = lista.stream().min(Comparator.comparing(String::length)).orElse("");
                    
                    return new EstadisticasString(totalCaracteres, promedio, masLarga, masCorta);
                }
            ));
        System.out.println("Estadísticas personalizadas: " + estadisticas);
        
        // Collector para agrupar y transformar
        Map<String, String> agrupacionPersonalizada = palabras.stream()
            .collect(Collectors.groupingBy(
                palabra -> palabra.length() > 4 ? "LARGAS" : "CORTAS",
                Collectors.collectingAndThen(
                    Collectors.toList(),
                    lista -> String.join(" + ", lista)
                )
            ));
        System.out.println("Agrupación personalizada: " + agrupacionPersonalizada);
        System.out.println();
    }
    
    // Clase auxiliar para estadísticas personalizadas
    static class EstadisticasString {
        private int totalCaracteres;
        private double promedio;
        private String masLarga;
        private String masCorta;
        
        public EstadisticasString(int totalCaracteres, double promedio, String masLarga, String masCorta) {
            this.totalCaracteres = totalCaracteres;
            this.promedio = promedio;
            this.masLarga = masLarga;
            this.masCorta = masCorta;
        }
        
        @Override
        public String toString() {
            return String.format("Total: %d, Promedio: %.2f, Más larga: '%s', Más corta: '%s'",
                totalCaracteres, promedio, masLarga, masCorta);
        }
    }
} 