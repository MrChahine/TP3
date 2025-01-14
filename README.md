# Mon Application Spring Boot

## Description

Cette application permet de saisir une adresse et d'obtenir les prévisions météorologiques pour ce lieu. Elle utilise l'API ETALAB pour géocoder l'adresse et l'API MeteoConcept pour récupérer les informations météorologiques.

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

## Étapes et Questions-Réponses

### Étape 17

**Question :** Avez-vous remarqué une différence après le redémarrage ?

**Réponse :** Oui, une table `ADDRESS` a été créée automatiquement dans la base.

### Étape 18

**Question :** Pourquoi cette table est-elle apparue ?

**Réponse :** Grâce à la dépendance Hibernate (et Spring Data JPA), Spring a détecté l’entité `@Entity Address` et a automatiquement créé la table correspondante.

### Étape 19

**Question :** Voyez-vous le contenu de `data.sql` ?

**Réponse :** Oui, les deux adresses apparaissent bien dans la table `ADDRESS`.

### Étape 22

**Question :** À quoi sert l’annotation `@Autowired` ?

**Réponse :** Elle permet à Spring d’injecter automatiquement une instance de `AddressRepository` (créée par Spring Data) dans la variable `addressRepository` afin de pouvoir l’utiliser pour accéder aux données.

## Étape 6 : Documentation de l'API MeteoConcept

### 1. Faut-il une clé API pour appeler MeteoConcept ?

Oui, une clé API (token) est nécessaire pour accéder aux services de l'API MeteoConcept. Cette clé permet d'authentifier vos requêtes et de suivre votre utilisation de l'API. Vous pouvez obtenir cette clé en vous inscrivant sur le site de [MeteoConcept](https://www.meteo-concept.com/).

### 2. Quelle URL appeler ?

L'URL de base pour l'API MeteoConcept est la suivante :

https://api.meteo-concept.com/api/


Pour obtenir les prévisions météorologiques quotidiennes, l'URL complète à utiliser est :

https://api.meteo-concept.com/api/forecast/daily


### 3. Quelle méthode HTTP utiliser ?

L'API MeteoConcept utilise principalement la méthode **GET** pour récupérer des données météorologiques.

### 4. Comment passer les paramètres d'appels ?

Les paramètres sont passés via des **paramètres de requête** (query parameters) dans l'URL. Pour l'endpoint des prévisions météorologiques quotidiennes, les principaux paramètres requis sont :

- **token** : Votre clé API personnelle.
- **latlng** : Les coordonnées GPS sous la forme `latitude,longitude`.

**Exemple d'URL complète avec paramètres :**

https://api.meteo-concept.com/api/forecast/daily?token=VOTRE_TOKEN_METEOCONCEPT&latlng=48.114997,0.194543


**Détails des paramètres :**

- **token** : Remplacez `VOTRE_TOKEN_METEOCONCEPT` par votre clé API obtenue lors de votre inscription.
- **latlng** : Fournissez les coordonnées GPS du lieu pour lequel vous souhaitez obtenir les prévisions. Par exemple, `48.114997,0.194543` correspond à une localisation spécifique.

### 5. Où est l'information dont j'ai besoin dans la réponse :

#### a. Pour afficher la température du lieu visé par les coordonnées GPS

Les informations sur la température se trouvent dans les champs `tmin` (température minimale) et `tmax` (température maximale) de la première entrée de la liste `forecast`.

**Exemple de réponse JSON simplifiée :**

```json
{
  "forecast": [
    {
      "tmin": 5.0,
      "tmax": 15.0,
      "wind10m": 10,
      "gust10m": 20,
      "weather": 0,
      "probarain": 10
    }
  ]
}
```

**Accès aux données :**

Température minimale : forecast[0].tmin
Température maximale : forecast[0].tmax
b. Pour afficher la prévision de météo du lieu visé par les coordonnées GPS
La description des conditions météorologiques est disponible dans le champ weather de la première entrée de la liste forecast. Ce champ contient un code numérique que vous devez mapper à une description textuelle.

Exemple de réponse JSON simplifiée :

```json
{
  "forecast": [
    {
      "weather": 0
    }
  ]
}
````

**Mapping des codes météo :**

0 : Ensoleillé

1 : Peu nuageux

2 : Partiellement nuageux

3 : Nuageux

4 : Très nuageux

40 : Pluie légère

41 : Pluie modérée

11 : Orage

21 : Neige légère
