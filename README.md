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


## Utilisation de Bootstrap dans Nos Templates

### 1. Ajout de Bootstrap via CDN
Nous avons inclus Bootstrap dans nos fichiers HTML en utilisant le CDN (Content Delivery Network) officiel. Concrètement, dans la section `<head>` de chaque fichier HTML, nous avons ajouté :

```html
<!-- Bootstrap CSS -->
<link 
    rel="stylesheet" 
    href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
/>
```

Ce lien récupère directement les styles de Bootstrap depuis un CDN de confiance, ce qui nous permet de bénéficier de la dernière version stable de Bootstrap sans avoir besoin de stocker localement les fichiers CSS.

### 2. Ajout de jQuery et de Bootstrap JS
Les fonctionnalités JavaScript de Bootstrap (comme les modales, les dropdowns et les barres de navigation repliables) nécessitent jQuery ainsi que le fichier JavaScript de Bootstrap. Nous les avons donc ajoutés juste avant la balise de fermeture `</body>` :

```html
<!-- jQuery -->
<script 
    src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
></script>

<!-- Bootstrap JS + Popper (inclus dans Bootstrap 4.5.2) -->
<script 
    src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"
></script>
```

### 3. Utilisation des Composants Bootstrap
Dès que la balise `<link>` (pour le CSS) et les balises `<script>` (pour le JS) sont incluses, vous pouvez utiliser directement les classes et composants fournis par Bootstrap dans votre HTML. Par exemple, pour créer une barre de navigation responsive :

```html
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  ...
</nav>
```

Vous pouvez également employer d’autres classes telles que `.container`, `.card`, `.btn`, `.row`, et `.col` pour bâtir une mise en page structurée et responsive à travers vos pages.

### 4. Styles Personnalisés
En plus des styles par défaut de Bootstrap, nous avons ajouté nos propres règles CSS soit dans un bloc `<style>` directement dans nos fichiers HTML, soit dans un fichier `.css` séparé :

```html
<style>
  body {
      background-color: #f8fafc;
      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  }
  /* Ajoutez vos styles personnalisés ici */
</style>
```

Cette approche nous permet de profiter de tout l’écosystème de design de Bootstrap tout en conservant la flexibilité nécessaire pour adapter ou remplacer certains éléments afin qu’ils correspondent à l’identité visuelle de notre projet.

---

Voilà ! Avec cette configuration, vous disposez d’un environnement Bootstrap pleinement fonctionnel pour créer des pages web responsives et adaptées aux mobiles.