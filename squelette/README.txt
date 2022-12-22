Nom : - LIA Brizoua Bi Ouiva Jean Aime
      - Latifou Falola Firdaousse

Les implémentations faites dans notre projet sont :

** Les personnages du jeu (Isaac inflige aussi des dégâts au corps à corps)

** Les objets que peut ramasser Isaac:
   - les équipements (Dans le jeu nous avons considérés les objets BloodOfTheMartyr et Inferieur3 comme des équipements). L'objet inférieur à trois est représenté par
   l'icone coeur dans la première salle monstre et une fois ramassé cet objet nous donne 2 vies soit un coeur plein. On observe cela sur le nombre de coeur qui augmente à l'écran.

   l'objet BloodOfTheMartyr est également représenté dans la première salle monstre avec une couronne et permet d'augmenter les caractéristiques d'Isaac, une fois cet objet est ramassé

   NB : Toutes les fonctionnalités pour le ramassage des objets sont développés dans la classe Héro. Nous avons notamment les méthodes : ramasserObjet, ramasserEquipement

** Les Obstacles (Porte fermée, mur, rocher, pique)

    Nous avons également mis à place des obstacles dans le jeu. Les obstacles représentés dans le jeu sont des rochers et des piques.

    Nous avons mis en place la fonctionnalité qui empêche le personnage de passer à travers les obstacles. Un obstacle qui ne peut être traversé dans le jeu implémente
    l'interface Stop, ce qui nous permet à travers l'instruction java InstanceOf, de vérifier si notre objet est une instance de stop pour empêcher le personnage de se déplacer.

** La fonction de triche (un petit souci avec la touche 'o', qui au lieu d'augmenter de 10 le nombre de pièces, le fait plusieurs fois lors de l'appui
      	sur la touche, ce qui est dû à la boucle qui relance le jeu, car cela se fait pendant un temps très petit.)

      	Toutes les fonctionnalités de tricherie ont été développées. Nous avons également ajouté la fonctionnalité qui permet de diminuer la vitesse d'Isaac en appuyant
      	la touche 'm'.

** Les salles

    Nous avons également implémenté la liaison des salles entre elle par les portes, en donnant la possibilité au Hero de revenir dans les salles précédentes.

    Dans la classe Room, nous avons ajouté un attribut carreau qui est un tableau à deux dimensions. Cet attribut nous permet de savoir pour une position d'abscisse x et d'ordonnée y,
    l'élément qui se trouve à cette position dans la salle. C'est ainsi que nous arrivons à ramasser les objets, tuer les monstres. (Voir fonction drawRoom() de la classe Room ligne 126)

** Les armes (Que les larmes pour cette version)

    Nous avons ajouté au personnage une liste d'arme, en prenant par défaut la première arme de la liste. Ceci a été fait pour prendre en compte dans une évolution futur le fait qu'un
    personnage peut avoir plusieurs armes.

** La fonction de tir : tue en fonction de la portée (si le monstre est à la distance correspondante)

** restriction des zones de déplacement (les personnages du jeu ne peuvent passer à travers les murs)

** Fin après la mort du joueur :
    Lorsque le joueur n'a plus de point de vie (un affichage dans la console indique : Game Over)

** Fin après la mort du BOSS :
    Lorsque le joueur n'a plus de point de vie (un affichage dans la console indique : Game Over)

** Corps à corps entre Isaac et les monstres:
    Nous avons mis en la place cette fonctionnalité (Voir methode rencontreMonstre, de la classe Hero )