Parcial Desarrollo de Software - Mutantes
Este proyecto esta desarrollado en Java Spring Boot para detectar mutantes a partir de secuencias de ADN. La API evalúa si un humano es mutante o no, en función de patrones repetitivos en su secuencia de ADN, como también, proporciona estadísticas de las evaluaciones realizadas.

Descripción del Proyecto
Magneto desea identificar la mayor cantidad de mutantes posible para ayudar en su lucha contra los X-Men. Para lograr esto, esta API recibe secuencias de ADN y determina si una persona es mutante o no.

Estructura de la API
La API está divida mediante una arquitectura en capas de controladores, repositorios y servicios. Organizados en paquetes de controladores, servicios, repositorios, entidades y DTOs. La lógica para detectar mutantes se basa en encontrar múltiples secuencias de cuatro letras consecutivas (A, T, C, G) idénticas en cualquier dirección: horizontal, vertical o diagonal.

Requisitos
Java 17+
Spring Boot
Gradle
Base de datos H2
Herramientas de pruebas como JUnit y JMeter
URL: /mutant

Método: POST

Descripción: Evalúa si una persona es mutante a partir de una secuencia de ADN.

Formato de la solicitud:

json
Copiar código
{
    "dna": ["ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"]
}
Respuesta:

HTTP 200 OK si la persona es mutante.
HTTP 403 Forbidden si la persona no es mutante.
Estadísticas
URL: /stats

Método: GET

Descripción: Devuelve estadísticas de las secuencias de ADN evaluadas, mostrando la cantidad de mutantes y humanos analizados y el ratio entre ellos.

Formato de la respuesta:

json
Copiar código
{
    "count_mutant_dna": 40,
    "count_human_dna": 100,
    "ratio": 0.4
}

Estructura del Proyecto
controllers: Controladores para los endpoints /mutant y /stats.

DnaController: Controlador para el endpoint de detección de mutantes.
StatsController: Controlador para el endpoint de estadísticas.
dto: Clases para el intercambio de datos entre cliente y servidor.

DnaRequest: Clase que representa la solicitud de secuencia de ADN.
DnaResponse: Clase que representa la respuesta de la detección de mutante.
StatsResponse: Clase que representa la respuesta del servicio de estadísticas.
entities: Clases que representan las entidades del modelo de datos.

Dna: Entidad que representa una secuencia de ADN y su clasificación como mutante o humano.
repositories: Interfaces para la interacción con la base de datos.

DnaRepository: Repositorio para almacenar y recuperar secuencias de ADN.
services: Clases que contienen la lógica de negocio.

DnaService: Servicio para analizar la secuencia de ADN y detectar mutantes.
StatsService: Servicio para generar estadísticas de las secuencias de ADN evaluadas.
validators: Validadores personalizados para asegurar la validez de las secuencias de ADN.

DnaValidator: Validador para verificar que la secuencia de ADN solo contiene caracteres válidos (A, T, C, G).
Instalación
Clona el repositorio.

bash
Copiar código
git clone <URL_DEL_REPOSITORIO>
cd ProyectoMutantes
Configura el entorno. Asegúrate de tener Java 17 y Gradle instalados.

Ejecuta la aplicación.

bash
Copiar código
./gradlew bootRun
La aplicación estará disponible en http://localhost:8080.

Ejecución de Pruebas
JUnit: Para realizar pruebas unitarias y de integración.
JMeter: Para realizar pruebas de carga y estrés.

Ejecuta las pruebas con:
bash
Copiar código
./gradlew test
Consideraciones Adicionales
Cobertura de pruebas: El proyecto requiere al menos un 80% de cobertura de código.
Manejo de concurrencia: La aplicación está diseñada para soportar altas tasas de tráfico (entre 100 y 1,000 solicitudes por segundo).
Base de Datos
La aplicación utiliza una base de datos en memoria H2 para almacenar las secuencias de ADN evaluadas, permitiendo solo un registro por secuencia de ADN para evitar duplicados.







