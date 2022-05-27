# BattleTournaments

[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://openjdk.org/)
[![PaperMC](https://img.shields.io/badge/Paper-1.18.2-blue.svg)](https://papermc.io/)
[![MongoDB](https://img.shields.io/badge/MongoDB-4.6-green.svg)](https://www.mongodb.com/)
[![Gradle](https://img.shields.io/badge/Gradle-8.10-teal.svg)](https://gradle.org/)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

Plugin PaperMC (Minecraft 1.18.2+) pour la gestion de tournois et d'arènes de duel, optimisé pour limiter l'impact sur le thread principal du serveur.

---

## Sommaire

- [Presentation](#presentation)
- [Architecture](#architecture)
- [Fonctionnalites](#fonctionnalites)
- [Organisation du projet](#organisation-du-projet)
- [Prerequis](#prerequis)
- [Compilation et installation](#compilation-et-installation)
- [Configuration](#configuration)
- [Commandes](#commandes)
- [Licence](#licence)

---

## Presentation

Sur un serveur Minecraft accueillant du jeu competitif, les acces base de donnees et le traitement synchrone des evenements peuvent vite saturer la boucle de tick (TPS drops).

BattleTournaments repond a cette contrainte :
- Traitement asynchrone des taches lourdes et des evenements joueurs.
- Persistance MongoDB sans couche ORM superflue, via les codecs BSON natifs.
- Routage modulaire des commandes base sur des annotations Java, supprimant les longues chaines de conditions dans l'executeur Bukkit.

---

## Architecture

Le plugin s'articule autour de quatre composants principaux :

- **Noyau (`BattleTournaments`)** : point d'entree du plugin, cycle de vie (`onEnable`, `onDisable`) et initialisation des gestionnaires.
- **Moteur de taches (`TaskManager`)** : ordonnance l'execution asynchrone via le scheduler Paper, isolant le calcul metier du thread principal.
- **Acces donnees (`MongoManager`)** : gere le pool de connexions MongoDB et la serialisation automatique des entites BSON.
- **Routage des commandes (`CommandManager`)** : enregistre et route les sous-commandes declarees par annotations (`@Name`, `@Permission`, `@UsableBy`), avec gestion native de l'autocompletion selon les permissions.

---

## Fonctionnalites

- **Taches asynchrones** :
  - `BattleTask` : tache runnable asynchrone standard.
  - `BattleEventTask<T>` : passerelle generique pour consommer un evenement Paper de maniere non-bloquante.
- **Commandes declaratives** :
  - Declaration des permissions et restrictions d'execution (joueur, console ou les deux) directement sur les classes.
  - Filtrage de la tabulation selon les permissions effectives du joueur.
- **Modeles metier et persistance** :
  - `AbstractManager<T>` fournissant les operations de base sur les collections MongoDB.
  - Gestion des profils de joueurs (`BattlePlayer`), des tournois (`Tournament`) et des etats d'arenes (`ArenaState` : `READY`, `IN_GAME`, `ENDED`).
- **Build standardise** :
  - Cible Java 17 stricte (`options.release = 17`).

---

## Organisation du projet

Le code source est decoupe par responsabilite dans `src/main/java/fr/romitou/battletournaments/` :

- `commands/` : framework de commandes, annotations et sous-commandes implementees.
- `database/` : client MongoDB et configuration du registre de codecs.
- `elements/` : gestionnaires et modeles metier (`arenas`, `battlePlayers`, `tournaments`).
- `listeners/` : ecouteurs d'evenements Bukkit interceptant les actions de jeu.
- `tasks/` : primitives pour l'execution de taches asynchrones.

---

## Prerequis

- JDK 17 ou superieur
- Instance MongoDB disponible (locale ou distante)
- Serveur compatible PaperMC 1.18.2+

---

## Compilation et installation

### 1. Recuperer le depot
```bash
git clone https://github.com/Romitou/DuelTournaments.git
cd DuelTournaments
```

### 2. Compiler
```bash
chmod +x gradlew
./gradlew clean build
```

Le binaire compile se trouve dans `build/libs/BattleTournaments-1.0.0.jar`.

### 3. Deploiement
1. Copier le fichier `.jar` dans le dossier `plugins/` du serveur.
2. Demarrer le serveur pour generer le fichier de configuration par defaut.
3. Renseigner l'URI MongoDB dans `plugins/BattleTournaments/config.yml`.

---

## Configuration

Fichier `src/main/resources/config.yml` :

```yaml
mongodb:
  uri: "mongodb://localhost:27017"
  database: "battleTournaments"
```

---

## Commandes

| Commande | Syntaxe | Permission | Contexte | Description |
| :--- | :--- | :--- | :--- | :--- |
| `/battletournaments` | `/bt <commande>` | `battletournaments.use` | Tous | Racine des commandes du plugin |
| `version` | `/bt version` | `battletournaments.version` | Console / Joueur | Affiche la version en cours |

---

## Licence

Ce projet est sous licence [MIT](LICENSE).
