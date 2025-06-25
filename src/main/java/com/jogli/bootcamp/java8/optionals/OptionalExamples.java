package com.jogli.bootcamp.java8.optionals;

import java.util.*;
import java.util.function.*;

/**
 * Ejemplos de Optional en Java 8
 * 
 * Optional es un contenedor que puede contener un valor o estar vacío,
 * ayudando a evitar NullPointerException y hacer el código más expresivo.
 */
public class OptionalExamples {
    
    public static void ejecutarEjemplos() {
        ejemplo1_CreacionYBasicos();
        ejemplo2_MetodosDeAcceso();
        ejemplo3_TransformacionYFiltrado();
        ejemplo4_CombinacionDeOptionals();
        ejemplo5_CasosDeUsoPracticos();
    }
    
    /**
     * Ejemplo 1: Creación y métodos básicos
     * Demuestra cómo crear y usar Optional
     */
    public static void ejemplo1_CreacionYBasicos() {
        System.out.println("1. CREACIÓN Y MÉTODOS BÁSICOS");
        System.out.println("-----------------------------");
        
        // Crear Optional con valor
        Optional<String> optionalConValor = Optional.of("Hola mundo");
        System.out.println("Optional con valor: " + optionalConValor);
        
        // Crear Optional vacío
        Optional<String> optionalVacio = Optional.empty();
        System.out.println("Optional vacío: " + optionalVacio);
        
        // Crear Optional que puede ser null
        String valorPosiblementeNull = null;
        Optional<String> optionalDeNull = Optional.ofNullable(valorPosiblementeNull);
        System.out.println("Optional de null: " + optionalDeNull);
        
        // Verificar si tiene valor
        System.out.println("¿Tiene valor 'Hola mundo'? " + optionalConValor.isPresent());
        System.out.println("¿Está vacío? " + optionalVacio.isEmpty());
        
        // Obtener valor (puede lanzar NoSuchElementException)
        try {
            String valor = optionalConValor.get();
            System.out.println("Valor obtenido: " + valor);
        } catch (Exception e) {
            System.out.println("Error al obtener valor: " + e.getMessage());
        }
        System.out.println();
    }
    
    /**
     * Ejemplo 2: Métodos de acceso seguros
     * Demuestra métodos que evitan excepciones
     */
    public static void ejemplo2_MetodosDeAcceso() {
        System.out.println("2. MÉTODOS DE ACCESO SEGUROS");
        System.out.println("---------------------------");
        
        Optional<String> optionalConValor = Optional.of("Texto de ejemplo");
        Optional<String> optionalVacio = Optional.empty();
        
        // orElse - valor por defecto si está vacío
        String resultado1 = optionalConValor.orElse("Valor por defecto");
        String resultado2 = optionalVacio.orElse("Valor por defecto");
        System.out.println("Con valor: " + resultado1);
        System.out.println("Sin valor: " + resultado2);
        
        // orElseGet - valor por defecto calculado (lazy)
        String resultado3 = optionalVacio.orElseGet(() -> {
            System.out.println("Calculando valor por defecto...");
            return "Valor calculado";
        });
        System.out.println("Valor calculado: " + resultado3);
        
        // orElseThrow - lanzar excepción si está vacío
        try {
            String valor = optionalVacio.orElseThrow(() -> 
                new RuntimeException("No hay valor disponible"));
        } catch (RuntimeException e) {
            System.out.println("Excepción capturada: " + e.getMessage());
        }
        
        // ifPresent - ejecutar acción si tiene valor
        optionalConValor.ifPresent(valor -> 
            System.out.println("Procesando valor: " + valor.toUpperCase()));
        
        optionalVacio.ifPresent(valor -> 
            System.out.println("Esto no se ejecuta"));
        
        // ifPresentOrElse - ejecutar acción diferente si está vacío
        optionalConValor.ifPresentOrElse(
            valor -> System.out.println("Valor encontrado: " + valor),
            () -> System.out.println("No hay valor")
        );
        
        optionalVacio.ifPresentOrElse(
            valor -> System.out.println("Valor encontrado: " + valor),
            () -> System.out.println("No hay valor")
        );
        System.out.println();
    }
    
    /**
     * Ejemplo 3: Transformación y filtrado
     * Demuestra map(), flatMap(), filter()
     */
    public static void ejemplo3_TransformacionYFiltrado() {
        System.out.println("3. TRANSFORMACIÓN Y FILTRADO");
        System.out.println("---------------------------");
        
        Optional<String> optionalTexto = Optional.of("  hola mundo  ");
        Optional<String> optionalVacio = Optional.empty();
        
        // map - transformar valor si existe
        Optional<String> mayusculas = optionalTexto.map(String::toUpperCase);
        Optional<String> mayusculasVacio = optionalVacio.map(String::toUpperCase);
        
        System.out.println("Texto original: " + optionalTexto.get());
        System.out.println("En mayúsculas: " + mayusculas.get());
        System.out.println("Vacio transformado: " + mayusculasVacio);
        
        // map con transformación compleja
        Optional<Integer> longitud = optionalTexto.map(String::length);
        System.out.println("Longitud: " + longitud.get());
        
        // flatMap - transformar a otro Optional
        Optional<String> sinEspacios = optionalTexto.flatMap(texto -> 
            Optional.of(texto.trim()));
        System.out.println("Sin espacios: " + sinEspacios.get());
        
        // filter - filtrar por condición
        Optional<String> textoLargo = optionalTexto.filter(texto -> texto.length() > 10);
        Optional<String> textoCorto = optionalTexto.filter(texto -> texto.length() < 20);
        
        System.out.println("¿Es largo? " + textoLargo.isPresent());
        System.out.println("¿Es corto? " + textoCorto.isPresent());
        
        // Cadena de operaciones
        Optional<String> resultado = optionalTexto
            .map(String::trim)
            .filter(texto -> !texto.isEmpty())
            .map(String::toUpperCase);
        
        System.out.println("Resultado final: " + resultado.get());
        
        // Con Optional vacío
        Optional<String> resultadoVacio = optionalVacio
            .map(String::trim)
            .filter(texto -> !texto.isEmpty())
            .map(String::toUpperCase);
        
        System.out.println("Resultado con vacío: " + resultadoVacio);
        System.out.println();
    }
    
    /**
     * Ejemplo 4: Combinación de Optionals
     * Demuestra cómo combinar múltiples Optional
     */
    public static void ejemplo4_CombinacionDeOptionals() {
        System.out.println("4. COMBINACIÓN DE OPTIONALS");
        System.out.println("--------------------------");
        
        Optional<String> nombre = Optional.of("Juan");
        Optional<String> apellido = Optional.of("Pérez");
        Optional<String> email = Optional.empty();
        
        // Combinar con map
        Optional<String> nombreCompleto = nombre.flatMap(n -> 
            apellido.map(a -> n + " " + a));
        System.out.println("Nombre completo: " + nombreCompleto.get());
        
        // Combinar múltiples Optional
        Optional<String> informacion = nombre.flatMap(n -> 
            apellido.flatMap(a -> 
                email.map(e -> n + " " + a + " (" + e + ")")
                    .or(() -> Optional.of(n + " " + a + " (sin email)"))
            ));
        System.out.println("Información completa: " + informacion.get());
        
        // Usar or() para alternativas
        Optional<String> emailAlternativo = email.or(() -> 
            Optional.of("juan.perez@default.com"));
        System.out.println("Email (con alternativo): " + emailAlternativo.get());
        
        // Combinar con orElse
        String resultado = nombre
            .flatMap(n -> apellido.map(a -> n + " " + a))
            .orElse("Nombre desconocido");
        System.out.println("Resultado final: " + resultado);
        
        // Ejemplo con números
        Optional<Integer> numero1 = Optional.of(10);
        Optional<Integer> numero2 = Optional.of(5);
        Optional<Integer> numero3 = Optional.empty();
        
        Optional<Integer> suma = numero1.flatMap(n1 -> 
            numero2.map(n2 -> n1 + n2));
        System.out.println("Suma: " + suma.get());
        
        Optional<Integer> sumaConVacio = numero1.flatMap(n1 -> 
            numero3.map(n3 -> n1 + n3));
        System.out.println("Suma con vacío: " + sumaConVacio);
        System.out.println();
    }
    
    /**
     * Ejemplo 5: Casos de uso prácticos
     * Demuestra aplicaciones reales de Optional
     */
    public static void ejemplo5_CasosDeUsoPracticos() {
        System.out.println("5. CASOS DE USO PRÁCTICOS");
        System.out.println("------------------------");
        
        // Simular búsqueda en base de datos
        Optional<Usuario> usuario = buscarUsuario("juan");
        Optional<Usuario> usuarioNoEncontrado = buscarUsuario("inexistente");
        
        // Procesar usuario encontrado
        usuario.ifPresent(u -> {
            System.out.println("Usuario encontrado: " + u.getNombre());
            System.out.println("Email: " + u.getEmail().orElse("Sin email"));
        });
        
        // Procesar usuario no encontrado
        usuarioNoEncontrado.ifPresentOrElse(
            u -> System.out.println("Usuario: " + u.getNombre()),
            () -> System.out.println("Usuario no encontrado")
        );
        
        // Obtener información segura
        String nombreUsuario = usuario
            .map(Usuario::getNombre)
            .orElse("Usuario desconocido");
        System.out.println("Nombre para mostrar: " + nombreUsuario);
        
        // Validar y procesar
        Optional<String> emailValido = usuario
            .flatMap(Usuario::getEmail)
            .filter(email -> email.contains("@"))
            .map(String::toLowerCase);
        
        emailValido.ifPresent(email -> 
            System.out.println("Email válido: " + email));
        
        // Simular configuración
        Optional<Configuracion> config = cargarConfiguracion();
        
        int puerto = config
            .flatMap(Configuracion::getPuerto)
            .orElse(8080);
        System.out.println("Puerto del servidor: " + puerto);
        
        String host = config
            .flatMap(Configuracion::getHost)
            .orElse("localhost");
        System.out.println("Host del servidor: " + host);
        
        // Procesar lista de Optional
        List<Optional<String>> opcionales = Arrays.asList(
            Optional.of("valor1"),
            Optional.empty(),
            Optional.of("valor3")
        );
        
        List<String> valores = opcionales.stream()
            .filter(Optional::isPresent)
            .map(Optional::get)
            .toList();
        System.out.println("Valores no vacíos: " + valores);
        System.out.println();
    }
    
    // Métodos auxiliares para simular casos reales
    private static Optional<Usuario> buscarUsuario(String id) {
        if ("juan".equals(id)) {
            return Optional.of(new Usuario("Juan Pérez", Optional.of("juan@email.com")));
        }
        return Optional.empty();
    }
    
    private static Optional<Configuracion> cargarConfiguracion() {
        return Optional.of(new Configuracion(
            Optional.of("servidor.com"),
            Optional.of(9090)
        ));
    }
    
    // Clases auxiliares
    static class Usuario {
        private String nombre;
        private Optional<String> email;
        
        public Usuario(String nombre, Optional<String> email) {
            this.nombre = nombre;
            this.email = email;
        }
        
        public String getNombre() { return nombre; }
        public Optional<String> getEmail() { return email; }
    }
    
    static class Configuracion {
        private Optional<String> host;
        private Optional<Integer> puerto;
        
        public Configuracion(Optional<String> host, Optional<Integer> puerto) {
            this.host = host;
            this.puerto = puerto;
        }
        
        public Optional<String> getHost() { return host; }
        public Optional<Integer> getPuerto() { return puerto; }
    }
} 