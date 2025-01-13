# Mon Application Spring Boot

## Dépendances

1. **Spring Web**  
   Permet de créer des applications web (incluant REST) en simplifiant la configuration de Tomcat et des endpoints HTTP.

2. **Spring Data JPA**  
   Simplifie l’utilisation de JPA/Hibernate pour interagir avec la base de données.

3. **Hibernate**  
   Implémentation de JPA, permettant le mapping Objet/Relationnel (ORM) pour gérer la persistance des entités en base.

4. **H2**  
   Base de données embarquée, facile à configurer pour les développements ou tests.  
   Les données sont volatiles (en mémoire) et peuvent être persistées dans un fichier si on le souhaite.

5. **Thymeleaf**  
   Moteur de templates pour générer du HTML dynamique côté serveur.

6. **DevTools**  
   Aide au développement en redémarrant l’application automatiquement après chaque modification du code.

Etape17:

Avez-vous remarqué une différence après le redémarrage ?

Réponse : Oui, une table ADDRESS a été créée automatiquement dans la base.

Etape18:

Pourquoi cette table est-elle apparue ?

Réponse : Grâce à la dépendance Hibernate (et Spring Data JPA), Spring a détecté l’entité @Entity Address et a automatiquement créé la table correspondante.

Etape19:

Voyez-vous le contenu de data.sql ?

Réponse : Oui, les deux adresses apparaissent bien dans la table ADDRESS.

Etape22:

À quoi sert l’annotation @Autowired ?

Réponse : Elle permet à Spring d’injecter automatiquement une instance de AddressRepository (créée par Spring Data) dans la variable addressRepository afin de pouvoir l’utiliser pour accéder aux données.

