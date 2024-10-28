# Projet de Gestion des Compétitions de Pigeons Voyageurs

## Contexte du Projet

Ce projet est développé dans le cadre d’une compétition annuelle organisée par la **Fédération Marocaine des Pigeons Voyageurs**. Chaque année, des éleveurs de pigeons participent à plusieurs compétitions, organisées en trois étapes: vitesse, demi-fond, et fond (selon les conditions climatiques). Ce système vise à centraliser et automatiser la gestion des compétitions, de l’enregistrement des éleveurs et de leurs pigeons à l’affichage des résultats finaux.

## Fonctionnalités

### Enregistrement et Authentification des Éleveurs
- Les éleveurs peuvent s’enregistrer en créant un compte et en fournissant un nom de colombier unique, un nom d’utilisateur, un mot de passe, et les coordonnées GPS de leur colombier.
- Les éleveurs peuvent s’authentifier pour accéder à leur compte et gérer leurs pigeons.

### Gestion des Pigeons
- Chaque éleveur peut ajouter des pigeons pour la saison active, chaque pigeon étant caractérisé par un **numéro de bague unique**, **sexe**, **âge**, **couleur**, et éventuellement une **image**.

### Création et Gestion des Compétitions
- L’organisateur peut créer des compétitions en renseignant le **nom de la course**, les **coordonnées GPS du point de lâcher**, la **date et heure de départ**, et la **distance prévisionnelle**.
- Les pigeons participants peuvent être ajoutés à chaque compétition en utilisant leur numéro de bague.

### Calcul des Résultats
1. **Collecte des Données** : Upload des données collectées après chaque compétition, incluant l’heure d’arrivée et le numéro de bague des pigeons.
2. **Calcul de la Distance** : Utilisation de la formule de Haversine pour calculer la distance entre le point de lâcher et le colombier de chaque éleveur.
3. **Calcul du Temps de Vol** : Différence entre l’heure de lâcher et l’heure d’arrivée.
4. **Calcul de la Vitesse** : Vitesse calculée en m/min.
5. **Classement** : Classement des pigeons en fonction de leur vitesse, de la plus rapide à la plus lente.
6. **Points de Performance** : Attribution de points selon le classement et le nombre de pigeons admis, avec cumul des points pour chaque colombier.

### Affichage et Export des Résultats
- Les résultats sont affichés pour chaque course dans un tableau, avec les informations suivantes : classement, colombier, numéro de bague, heure d’arrivée, distance, vitesse, et points.
- Calcul automatique du classement général, disponible pour consultation et export en format PDF.

## Exigences Techniques

- **Backend** : Spring Boot pour le développement de l’API.
- **Base de Données** : MongoDB pour stocker les données des éleveurs, pigeons, compétitions, et résultats.
- **Architecture** : Application organisée en couches (controller, service, repository).
- **Validation** : Validation des données en entrée pour garantir la fiabilité du système.
- **Gestion des Exceptions** : Gestion centralisée des exceptions pour un traitement homogène des erreurs.
- **Tests Unitaires** : Tests unitaires obligatoires pour assurer la qualité et la robustesse du code.
- **Configuration** : Fichier de configuration en format YAML pour une meilleure lisibilité et organisation des configurations.

## Installation

### Prérequis
- Java 21
- MongoDB
- Maven


