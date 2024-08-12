# Usar la imagen base de OpenJDK 17
FROM openjdk:17-jdk-slim

# Crear y utilizar un volumen temporal para mejorar el rendimiento
VOLUME /tmp

# Establecer el directorio de trabajo en el contenedor
WORKDIR /app

# Copiar el archivo JAR generado al contenedor
# Asegúrate de que la ruta del archivo JAR sea correcta
COPY build/libs/cliente-0.0.1-SNAPSHOT.jar app.jar

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
