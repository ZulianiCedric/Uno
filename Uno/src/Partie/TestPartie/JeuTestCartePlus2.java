package Partie.TestPartie;

import Carte.Carte;
import Carte.CartePlus2;
import Carte.CarteSimple;
import Exceptions.MauvaisJoueurException;
import Exceptions.MauvaisNombreCarteException;
import Joueur.Joueur;
import Partie.Partie;

public class JeuTestCartePlus2 {
    public void TestPlus2() throws Exception {
        System.out.println("Debut Test Plus2");

        //Test Coup legal sans cumul
        Joueur Alice = new Joueur("Alice");
        Joueur Bob = new Joueur("Bob");
        Joueur Charles = new Joueur("Charles");

        Carte sixv = new CarteSimple("6","Vert");
        Carte unbleu = new CarteSimple("1","Bleu");
        Carte PlusV = new CartePlus2("Vert");

        String s ="jeux_test/JeuTestCartePlus2.csv";

        Partie.getInstance().ajouterJoueur(Alice);
        Partie.getInstance().ajouterJoueur(Bob);
        Partie.getInstance().ajouterJoueur(Charles);
        Partie.getInstance().initialiserPartie(3,s);

        if(Partie.getInstance().getJoueurActuel()!="Alice")
            throw new MauvaisJoueurException("Alice n'est pas le joueur courant");

        Alice.poserCarte(Alice.getMain().get(0));
        Alice.FinTour();

        if(Partie.getInstance().getJoueurActuel()!="Bob")
            throw new MauvaisJoueurException("Bob n'est pas le joueur courant");
        if(Bob.getNombreCarteMain()!=3)
            throw new MauvaisNombreCarteException("Bob n'as pas 3 cartes");

        Bob.Encaisser();

        if(Bob.getNombreCarteMain()!=5)
            throw new MauvaisNombreCarteException("Bob n'as pas 5 cartes");

        if(Partie.getInstance().getJoueurActuel()!="Charles")
            throw new MauvaisJoueurException("Charles n'est pas le joueur courant");

        Charles.poserCarte(Charles.getMain().get(2));
        Charles.FinTour();

        if(Charles.getNombreCarteMain()!=2)
            throw new MauvaisNombreCarteException("Charles n'as pas 2 cartes");

        Partie.FinPartie();
        Alice.getMain().clear(); Bob.getMain().clear(); Charles.getMain().clear();

        //Test Plus2 avec cumul

        Partie.getInstance().ajouterJoueur(Alice);
        Partie.getInstance().ajouterJoueur(Bob);
        Partie.getInstance().ajouterJoueur(Charles);
        Partie.getInstance().initialiserPartie(3,s);

        if(Partie.getInstance().getJoueurActuel()!="Alice")
            throw new MauvaisJoueurException("Alice n'est pas le joueur courant");
        Alice.piocher();
        Alice.FinTour();

        if(Partie.getInstance().getJoueurActuel()!="Bob")
            throw new MauvaisJoueurException("Bob n'est pas le joueur courant");
        Bob.piocher();
        Bob.FinTour();

        if(Partie.getInstance().getJoueurActuel()!="Charles")
            throw new MauvaisJoueurException("Charles n'est pas le joueur courant");
        Charles.poserCarte(Charles.getMain().get(1));
        Charles.FinTour();

        if(Partie.getInstance().getJoueurActuel()!="Alice")
            throw new MauvaisJoueurException("Alice n'est pas le joueur courant");
        Alice.poserCarte(Alice.getMain().get(0));
        Alice.FinTour();

        if(Partie.getInstance().getJoueurActuel()!="Bob")
            throw new MauvaisJoueurException("Bob n'est pas le joueur courant");
        if(Bob.getNombreCarteMain()!=4)
            throw new MauvaisNombreCarteException("Bob n'as pas 4 cartes");
        Bob.Encaisser();
        if(Bob.getNombreCarteMain()!=8)
            throw new MauvaisNombreCarteException("Bob n'as pas 8 cartes");

        if(Partie.getInstance().getJoueurActuel()!="Charles")
            throw new MauvaisJoueurException("Charles n'est pas le joueur courant");
        System.out.println("Fin Test Plus2");
    }
}
