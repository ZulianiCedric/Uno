package Partie.TestPartie;

import Carte.Carte;
import Carte.CarteSimple;
import Exceptions.*;
import Joueur.Joueur;
import Partie.Partie;

public class JeuTestCarteSimplePourUno {
	public void TestUno() throws Exception {
		System.out.println("Debut Test Uno");
        Joueur Alice = new Joueur("Alice");
        Joueur Bob = new Joueur("Bob");
        Joueur Charles = new Joueur("Charles");
        Partie.getInstance().ajouterJoueur(Alice);
        Partie.getInstance().ajouterJoueur(Bob);
        Partie.getInstance().ajouterJoueur(Charles);
        Carte deuxv = new CarteSimple("2","Vert");
        Carte huitv = new CarteSimple("8","Vert");
        String s ="jeux_test/JeuTestCarteSimplePourTestUno.csv";
        Partie.getInstance().initialiserPartie(2,s);


        //Test Uno au bon moment
        if(Alice.getNombreCarteMain()!=2)
            throw new MauvaisNombreCarteException("Alice n'a pas 2 cartes");

        Alice.poserCarte(Alice.getMain().get(0));
        Alice.Uno();
        Alice.FinTour();

        if(Alice.getNombreCarteMain()!=1)
            throw new MauvaisNombreCarteException("Alice n'a pas 1 cartes");

        if(!Partie.getInstance().getCarteTas(deuxv)) //2vert
            throw new MauvaiseCarteException("La carte au sommet du tas n'est pas le 2 Vert");

        if(Partie.getInstance().getJoueurActuel()!="Bob")
            throw new MauvaisJoueurException("Le joueur actuel n'est pas pas Bob");

        Partie.FinPartie();
        Alice.getMain().clear(); Bob.getMain().clear(); Charles.getMain().clear();

        //Test Uno oublié
        Partie.getInstance().ajouterJoueur(Alice);
        Partie.getInstance().ajouterJoueur(Bob);
        Partie.getInstance().ajouterJoueur(Charles);
        Partie.getInstance().initialiserPartie(2,s);

        Alice.poserCarte(Alice.getMain().get(0));
        try {
            Alice.FinTour();
        } catch (UnoException e) {
            Alice.punition();
            if(Alice.getNombreCarteMain()!=4)
        	    throw new MauvaisNombreCarteException("Alice n'a pas 4 cartes");

            if(!Partie.getInstance().getCarteTas(huitv))
        	    throw new MauvaiseCarteException("La carte au sommet n'est pas le 8 vert");

            if(Partie.getInstance().getJoueurActuel()!="Bob")
        	    throw new MauvaisJoueurException("C'est pas bob");
        }
        catch (Exception e){
            throw new JeuxUnoException("Exception non voulu");
        }

        Partie.FinPartie();
        Alice.getMain().clear(); Bob.getMain().clear(); Charles.getMain().clear();

        //Test Uno hors de son tour

        Partie.getInstance().ajouterJoueur(Alice);
        Partie.getInstance().ajouterJoueur(Bob);
        Partie.getInstance().ajouterJoueur(Charles);
        Partie.getInstance().initialiserPartie(2,s);

        if(Partie.getInstance().getJoueurActuel()!="Alice")
            throw new MauvaisJoueurException("Le joueur actuel n'est pas Alice");
        else
            try {
                Bob.Uno();
            } catch (UnoException e) {
                Bob.punition();
            
                if(Bob.getNombreCarteMain()!=4)
                throw new MauvaisNombreCarteException("Bob n'a pas 4 carte");
    
                if(Partie.getInstance().getJoueurActuel()!="Alice")
                throw new MauvaisJoueurException("Alice n'est pas le joueur courant");
    
                if(!Partie.getInstance().getCarteTas(huitv))
                throw new MauvaiseCarteException("La carte au sommet n'est pas le 8 vert");
            }
            catch (Exception e){
                throw new JeuxUnoException("Exception non voulu");
            }
        System.out.println("Fin Test Uno");
        Partie.FinPartie();
    }
}
