# 📚 Sistema Biblioteca Virtual



Un sistema de gestión de biblioteca desarrollado en Java que permite realizar préstamos y devoluciones de libros utilizando el patrón arquitectónico **Modelo-Vista-Controlador (MVC)** y principios de **Programación Orientada a Objetos**.



## 🏗️ Arquitectura del Sistema

El proyecto sigue **MVC** para separar responsabilidades:

- **Modelo (Model)**: Entidades y casos de uso (lógica de negocio)
- **Vista (View)**: Interacción por consola con el usuario
- **Controlador (Controller)**: Orquesta el flujo entre modelo y vista





## 📁 Estructura del Proyecto



```
src/
├── App.java                       # Punto de entrada
├── controller/                    # Controladores (flujo de la aplicación)
│   └── BibliotecaController.java
├── models/                        # Entidades del dominio
│   ├── book/
│   │   └── Book.java
│   └── user/
│       └── User.java
├── useCases/                      # Lógica de negocio por casos de uso
│   ├── BookUseCases/
│   │   └── BookUseCase.java
│   └── UserUseCases/
│       └── UserUseCase.java
└── view/                          # Interfaz de usuario por consola
    └── BibliotecaView.java
```



## 🎯 Funcionalidades



### ✅ Características Principales



- **Autenticación de usuario** con sistema de intentos limitados

- **Gestión de préstamos** de libros por categorías

- **Gestión de devoluciones** con validación

- **Consulta de préstamos activos**

- **Consulta de libros disponibles** por categoría

- **Organización por categorías**: Ciencia ficción, Juveniles, Infantiles



### 📖 Catálogo de Libros



#### 🚀 Ciencia Ficción

- Dune

- Neuromante

- Fundación



#### 👥 Juveniles

- Bajo la misma estrella

- El corredor del laberinto

- Divergente



#### 🧸 Infantiles

- El principito

- Donde viven los monstruos

- Harry Potter y la piedra filosofal



## 🔐 Credenciales de Acceso



Para acceder al sistema utiliza las siguientes credenciales:



- **Usuario**: `Andrea.Benitez`

- **Contraseña**: `1234`



> ⚠️ **Nota**: El sistema permite máximo 3 intentos de inicio de sesión.



## 🚀 Instalación y Ejecución



### Requisitos Previos



- Java JDK 8 o superior

- Un IDE de Java (IntelliJ IDEA, Eclipse, VS Code, etc.) o compilador de línea de comandos



### Pasos para ejecutar



1. **Clonar o descargar** el proyecto

2. **Compilar** todos los archivos Java:

```bash

  javac -d out src/model/*.java src/view/*.java src/controller/*.java src/App.java

  ```

3. **Ejecutar** la aplicación:

  ```bash

  java -cp out App
```



### Ejecución desde IDE



1. Importar el proyecto en tu IDE favorito

2. Asegurarte de que la estructura de carpetas sea correcta

3. Ejecutar la clase `App.java`



## 📋 Menú Principal



Una vez autenticado, el sistema presenta las siguientes opciones:



```

1. Realizar un préstamo de libro

2. Realizar una devolución de libro  

3. Consultar préstamos activos

4. Consultar libros disponibles

5. Salir

```



## 🔧 Detalles Técnicos



### Clases Principales



#### 📦 Modelo (model/)



- **`Book.java`**:

&nbsp; - Representa un libro con título, categoría y estado de préstamo

&nbsp; - Métodos: getters, setters, toString()



- **`User.java`**:

&nbsp; - Maneja las credenciales del usuario

&nbsp; - Método de validación: `validarCredenciales()`



- **`BibliotecaService.java`**:

&nbsp; - Contiene toda la lógica de negocio

&nbsp; - Métodos principales: `prestarLibro()`, `devolverLibro()`, `autenticarUsuario()`



#### 👁️ Vista (vista/)



- **`BibliotecaView.java`**:

&nbsp; - Maneja toda la interacción con el usuario

&nbsp; - Métodos para mostrar menús, solicitar datos y mostrar mensajes



#### 🎮 Controlador (controlador/)



- **`BibliotecaController.java`**:

&nbsp; - Coordina entre modelo y vista

&nbsp; - Controla el flujo de la aplicación

&nbsp; - Maneja la autenticación y el menú principal



## 🎮 Guía de Uso



### 1. Inicio de Sesión

- Ejecutar la aplicación

- Ingresar credenciales válidas

- Máximo 3 intentos permitidos



### 2. Realizar Préstamo

- Seleccionar opción 1 del menú principal

- Elegir categoría de libro

- Seleccionar libro específico

- El sistema validará disponibilidad



### 3. Realizar Devolución

- Seleccionar opción 2 del menú principal

- Elegir categoría del libro a devolver

- Seleccionar libro específico

- El sistema validará que el libro esté prestado



### 4. Consultar Préstamos Activos

- Seleccionar opción 3

- Ver lista de todos los libros prestados actualmente



### 5. Consultar Libros Disponibles

- Seleccionar opción 4

- Elegir categoría

- Ver disponibilidad de libros en esa categoría



## ✨ Características del Diseño



### Principios de POO Aplicados



- **Encapsulación**: Atributos privados con métodos de acceso

- **Abstracción**: Separación clara de responsabilidades

- **Modularidad**: Clases especializadas para cada función



### Ventajas del Patrón MVC



- **Mantenibilidad**: Fácil modificar cualquier capa sin afectar las otras

- **Escalabilidad**: Simple agregar nuevas funcionalidades

- **Reutilización**: Componentes reutilizables

- **Testeo**: Cada capa puede ser probada independientemente




## 👨‍💻 Desarrollo



Este proyecto fue desarrollado como una demostración de:



- Conversión de código funcional a orientado a objetos

- Implementación del patrón arquitectónico MVC

- Aplicación de principios de diseño de software

- Buenas prácticas de programación en Java



---



