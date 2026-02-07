# Sistema de Gestión de Productos y Usuarios

Aplicación web desarrollada como práctica experimental de la asignatura Aplicaciones Web. Implementa el patrón MVC utilizando Spring Boot, JSF, PrimeFaces y Hibernate para gestionar productos y usuarios.

## Tecnologías

- Spring Boot
- Jakarta Server Faces (JSF)
- PrimeFaces 12
- Hibernate (JPA)
- PostgreSQL
- Maven

## Requisitos

- Java 17+
- Maven 3.6+
- PostgreSQL 12+

## Instalación

1. Clonar el repositorio
2. Crear la base de datos en PostgreSQL:
   CREATE DATABASE Productos;
3. Configurar credenciales en application.properties
4. Ejecutar: mvn clean install
5. Ejecutar: mvn spring-boot:run
6. Acceder a: http://localhost:8080

## Estructura

- model/ - Entidades JPA (Producto, Usuario)
- repository/ - Repositorios Spring Data JPA
- service/ - Lógica de negocio
- controller/ - Managed Beans JSF
- resources/META-INF/resources/ - Vistas XHTML

## Funcionalidades

Gestión completa CRUD para productos y usuarios con interfaz basada en componentes PrimeFaces.

## Configuración

Editar src/main/resources/application.properties con tus credenciales de PostgreSQL.

## Autores

- Calderón Saltos Joseph Alexander
- Castro Coello Mario Sebastián
- Zambrano Yong Ángel Daniel

Práctica Experimental Unidad V - Aplicaciones Web
Universidad Técnica Estatal de Quevedo
