package Partie.TestPartie;

import Carte.*;
import Exceptions.*;
import Joueur.Joueur;
import Partie.Partie;

public class JeuTestCarteSimple {

    public void TestSimple() throws Exception {
        System.out.println("Debut TestCarteSimple");
        Joueur Alice = new Joueur("Alice");
        Joueur Bob = new Joueur("Bob");
        Joueur Charles = new Joueur("Charles");
        Partie.getInstance().ajouterJoueur(Alice);
        Partie.getInstance().ajouterJoueur(Bob);
        Partie.getInstance().ajouterJoueur(Charles);
        Carte deuxv = new CarteSimple("2","Vert");
        Carte sixj = new CarteSimple("6","Jaune");
        Carte unr = new CarteSimple("1","Rouge");
        String s ="jeux_test/JeuTestCarteSimple.csv";
        Partie.getInstance().initialiserPartie(3,s);
        //Init la partie donc le tas et la pioche, les joueurs peut etre independamment de la partie,car il s'ajoute un par un
        if(Partie.getInstance().getJoueurActuel()!="Alice")
            throw new MauvaisJoueurException("Le joueur courant n'est pas alice");

        if(Alice.getNombreCarteMain()!=3)
            throw new MauvaisNombreCarteException("Alice n'a pas 3 cartes");

        Alice.poserCarte(Alice.getMain().get(0)); 

        if(Alice.getMain().size()!=2)
            throw new MauvaisNombreCarteException("Alice n'a pas 2 cartes");
        
        if(!Alice.PresenceCarteDansMain(sixj) && !Alice.PresenceCarteDansMain(unr)) 
            throw new MauvaiseCarteException("Alice ne possede pas le 6 Jaune et le 1 Rouge");

        if(!Partie.getInstance().getCarteTas(deuxv)) //2vert
            throw new MauvaiseCarteException("La carte au sommet du tas n'est pas le 2 Vert");
        
        if(Partie.getInstance().getNombreTas()!=2)
            throw new MauvaisNombreCarteException("Mauvais nombre de carte du tas");

        Alice.FinTour();

        if(Partie.getInstance().getJoueurActuel()!="Bob")
            throw new MauvaisJoueurException("Le joueur actuel n'est pas pas Bob");


        Carte quatrej = new CarteSimple("4","Jaune");
        Carte neufr = new CarteSimple("9","Rouge");
        Carte deuxb = new CarteSimple("2","Bleu");

        if(Bob.getNombreCarteMain()!=3)
            throw new MauvaisNombreCarteException("Bob n'a pas 3 cartes");

        Bob.poserCarte(Bob.getMain().get(0)); //Bob joue 2 bleu

        if(Bob.getMain().size()!=2)
            throw new MauvaisNombreCarteException("Bob n'a pas 2 cartes");

        if(!Bob.PresenceCarteDansMain(quatrej) && !Bob.PresenceCarteDansMain(neufr)) //Verif Bob possede 6j 1r
            throw new MauvaiseCarteException("Bob ne possede pas le 4 Jaune et le 9 Rouge");

        if(!Partie.getInstance().getCarteTas(deuxb)) //2bleu
            throw new MauvaiseCarteException("La carte au sommet du tas n'est pas le 2 Bleu");

        if(Partie.getInstance().getNombreTas()!=3)
            throw new MauvaisNombreCarteException("Mauvais nombre de carte du tas");

        Bob.FinTour();
        
        if(Partie.getInstance().getJoueurActuel()!="Charles")
            throw new MauvaisJoueurException("Le joueur actuel n'est pas Charles");

        System.out.println("Fin TestCarteSimple");

        Partie.FinPartie();
    }
}
