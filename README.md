Projet informatique - Solveur de Rubik's Cube
=============================================

##Introduction
L'objectif de ce projet est de faire un assistant pour résoudre un Rubik's Cube en Java. Concrètement, il devrait prendre en entrée une configuration de Rubik's Cube et sortir les différents mouvements pour arriver à un cube résolu.
 
Pour l'entrée nous pourrons tout d'abord lire un fichier décrivant les couleurs, puis passer à un système capable de lire les couleurs d'un Rubik's Cube avec une webcam.

Pour la sortie, nous allons implémenter une représentation en 3D de la résolution du Rubik's Cube.



## Comment représenter le Rubik's Cube ?
###1/ Représentation en mémoire 
Il a plusieurs façons de représenter le Rubik's Cube. Une première est de considérer chaque facette qui est susceptible de bouger. On assigne un numéro unique à chaque position des facettes et on applique des permutations sur cette tableau de taille 6x8=48. La correspondance indice/position est décrite dans l'image *rubiks.png*.

![Image associant les indices aux positions](https://raw.githubusercontent.com/Roxasispoor/Rubik-int/master/rubiks.png)

> Note: Une face est constituée  de 3x3 facettes

###2/ Représentation des mouvements
Pour représenter les mouvements on utilise la notation de Singmaster.
Ainsi nous avons U,D,L,R,F,B pour la rotation *antihoraire* des faces Up Down Left Right Front Back, et U',D'... pour une rotation *horaire*.

> Note: Dans le code on remplace le ' par un i pour inversed. Ainsi U' devient Ui


