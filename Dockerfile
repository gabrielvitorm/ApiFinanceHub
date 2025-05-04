# ← Dockerfile (na raiz do seu projeto)
# multi‐stage build para empacotar o JAR via Maven e depois rodar
FROM eclipse-temurin:17-jdk-alpine AS builder

WORKDIR /app
COPY . .
RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app
# copia apenas o .jar final gerado
COPY --from=builder /app/target/*.jar app.jar

# expõe a porta (aqui apenas para documentação; o Render usará $PORT)
EXPOSE 8080

# faz o Spring Boot escutar na porta definida por $PORT
CMD ["sh", "-c", "java -Dserver.port=$PORT -jar app.jar"]
