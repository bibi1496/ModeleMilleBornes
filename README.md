**Les plus du projet :**

- Une classe _**CouleurTextes**_ a été ajouté pour gérer les couleurs dans le terminale.
Cependant le rendu des couleurs peut varier d'un terminale à l'autre.

- Le dossier **Test** en dehors du dossier **src** contient des tests. On utilise la version junit-4.13.2.
Disponible à ce lien : https://search.maven.org/search?q=g:junit%20AND%20a:junit
  
- **BotFacile** : joue exclusivement avec les bornes ne se protège pas.
- **BotDifficile** : Se protège avec des parades si nécessaire et des bottes s'il en a, sinon il joue les bornes pour avancer.
- **Sauvegarde** possible seulement une à la fois et chargement si possible si le joueur veut à chaque chargement du programme.

- Ajout de la methode **getJoueur()** dans la class Jeu. Cela permet au bot de récupérer plus facilement la liste.

- Commentaire dans la class **Main**.
- La class **bot** contient les methods essentiels pour les autres bot. Elle est abstract pour ne pas pouvoir l'instancier et respecter une certaine logique dans notre programme.