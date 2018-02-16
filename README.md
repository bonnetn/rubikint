Computer science class project - Rubik's cube solver
====================================================

## Presentation

The goal of this project was to make a rubik's cube helper. 

You show your current cube configuration to the webcam, the program detects the 
colors using computer vision library *OpenCV*.

It then finds the movements to apply to solve the cube.

Finally it displays in 3D, using OpenGL, steps to solve the cube.


Projet informatique - Solveur de Rubik's Cube
=============================================

## Introduction
L'objectif de ce projet est de faire un assistant pour résoudre un Rubik's Cube en Java. Concrètement, il devrait prendre en entrée une configuration de Rubik's Cube et sortir les différents mouvements pour arriver à un cube résolu.
 
Pour l'entrée nous pourrons tout d'abord lire un fichier décrivant les couleurs, puis passer à un système capable de lire les couleurs d'un Rubik's Cube avec une webcam.

Pour la sortie, nous allons implémenter une représentation en 3D de la résolution du Rubik's Cube.



## Comment représenter le Rubik's Cube ?
### 1/ Représentation en mémoire 
Il a plusieurs façons de représenter le Rubik's Cube. Une première est de considérer chaque facette qui est susceptible de bouger. On assigne un numéro unique à chaque position des facettes et on applique des permutations sur cette tableau de taille 6x8=48. La correspondance indice/position est décrite dans l'image *rubiks.png*.

![Image associant les indices aux positions](https://raw.githubusercontent.com/Roxasispoor/Rubik-int/master/rubiks.png)

> Note: Une face est constituée  de 3x3 facettes

### 2/ Représentation des mouvements
Pour représenter les mouvements on utilise la notation de Singmaster.
Ainsi nous avons U,D,L,R,F,B pour la rotation *antihoraire* des faces Up Down Left Right Front Back, et U',D'... pour une rotation *horaire*.

> Note: Dans le code on remplace le ' par un i pour inversed. Ainsi U' devient Ui


### 3/ Installation des bibliothèques

Liste des bibliothèques à ajouter au projet pour le livrable 2 (13/03/2017) :
    
    gluegen-rt.jar 
    jogl-all.jar 
    gluegen-rt_native_OS.jar (à choisir selon son OS)
    jogl-all_native_OS.jar (même chose ici)


