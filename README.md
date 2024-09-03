# tp-rest-pokemon-groupe-1
# Spring Boot Pokémon Battle Game


## Description
Cette application est un jeu de combat entre Pokémon.  
Les utilisateurs peuvent choisir leurs Pokémon et les faire combattre dans un environnement simulé.


# Création de l'application
L'application a été initialisée en utilisant Spring Initializr. 
Un outil permettant de générer rapidement un projet Spring Boot avec les dépendances nécessaires.


# Dépendances sélectionnées :
Spring Web
Spring Data JPA
PostgreSQL Driver
H2 Database (pour les tests)
Spring Boot DevTools (pour le développement)


# Connexion a une base postgreSql

Ouvrir pgAdmin et creer une base de donnée pokemons
dans src/main/source configurer application.properties
spring.application.name=spring-boot-pokemon
spring.datasource.url=jdbc:postgresql://127.0.0.1:numéro_de_port/pokemons
spring.datasource.username=postgres
spring.datasource.password= mot de passe si vous en avez mis un
spring.jpa.generate-ddl=true

# Lancer l'application

mvn spring-boot:run

