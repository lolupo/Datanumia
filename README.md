# Yatzy Game

## Description
Ce projet est une implémentation du jeu Yatzy en utilisant Java et la bibliothèque picocli pour le traitement des arguments en ligne de commande.

## Prérequis
- Java 17 ou une version supérieure doit être installé sur votre machine.
- Maven doit être installé pour construire le projet.

## Instructions d'exécution
1. Avant de lancer le jeu, assurez-vous d'avoir construit le projet en exécutant les commandes suivantes depuis la racine du projet :

   ```shell
   mvn clean package
    ```
    
Cela va générer le fichier JAR exécutable yatzy-refactoring-kata-1.0.0.jar dans le répertoire target.

Trouvez le fichier JAR picocli-4.7.4.jar dans votre répertoire Maven local (~/.m2/repository/info/picocli/picocli/4.7.4/). Copiez ce fichier JAR dans le même répertoire que le fichier JAR yatzy-refactoring-kata-1.0.0.jar.

Ouvrez un terminal et naviguez jusqu'au répertoire contenant les fichiers JAR.

Utilisez la commande suivante pour lancer le jeu :
  
   ```shell
   java -cp "picocli-4.7.4.jar:yatzy-refactoring-kata-1.0.0.jar" com.yatzy.Game [options]
   ```
Remplacez [options] par les options spécifiques que vous souhaitez utiliser pour exécuter le jeu. Par exemple, pour spécifier le nombre de relances (re-rolls), utilisez l'option --reRolls suivie du nombre de relances :

   ```shell
   java -cp "picocli-4.7.4.jar:yatzy-refactoring-kata-1.0.0.jar" com.yatzy.Game --reRolls 2
   ```
Notez que vous devrez remplacer com.yatzy.Game par le chemin d'accès approprié à votre classe Game contenant la méthode main().

Le jeu Yatzy se lancera avec les options spécifiées et vous pourrez interagir avec l'interface en ligne de commande pour jouer au jeu.

Exemple d'options disponibles :

--reRolls <nombre> : spécifie le nombre de relances autorisées pour chaque tour.
...
Suivez les instructions affichées à l'écran pour jouer au jeu Yatzy.
   