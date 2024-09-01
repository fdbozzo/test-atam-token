# Pedir token a partir de un servicio de autenticación

## Compilación del proyecto con tests
```bash
mvn clean install
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
