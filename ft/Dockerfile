FROM eclipse-temurin:21-jdk

COPY build/libs/pokedex-ft.jar ft.jar

ENTRYPOINT ["java", "-jar", "ft.jar", "--glue", "com.dayamani.pokedex.steps", "classpath:features"]
