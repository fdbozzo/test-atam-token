# Pedir token a partir de un servicio de autenticación

## Tecnologías usadas:
- OpenAPI 3.0
- Java 17
- Spring Boot 3.3
- Docker

## Compilación del proyecto con tests
```bash
mvn clean install
```

## Ejecución del proyecto
```bash
java -jar target/test-atam-token-0.0.3-SNAPSHOT.jar
```

## Visualización de Swagger para probar y ver detalles del servicio
```url
http://localhost:8081
```

## Creación de imagen docker
```bash
docker build -t fdbozzo/test-atam-token:latest .
```

## Ejecución de los servicios dockerizados (desde directorio raiz)
```bash
docker compose up
```

## Detención de los servicios dockerizados (desde directorio raiz)
```bash
docker compose down
```
