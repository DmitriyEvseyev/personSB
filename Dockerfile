# Используем официальный образ Java
FROM openjdk:22-ea-20-jdk-slim-bullseye

# Устанавливаем рабочую директорию
WORKDIR /app

# Копируем файл jar вашего Spring Boot приложения в контейнер
COPY target/personSpringBoot-0.0.1-SNAPSHOT.jar /app/personSpringBoot.jar

# Открываем порт, на котором будет работать приложение
EXPOSE 8080

# Команда для запуска приложения
ENTRYPOINT ["java", "-jar", "personSpringBoot.jar"]