package Partie.TestPartie;

import Carte.Carte;
import Carte.CartePasserTour;
import Carte.CarteSimple;
import Exceptions.*;
import Joueur.Joueur;
import Partie.Partie;

public class JeuTestCartePasser {
	public void TestPasser() throws Exception {
		System.out.println("Debut Test Passer");
        Joueur Alice = new Joueur("Alice");
        Joueur Bob = new Joueur("Bob");
        Joueur Charles = new Joueur("Charles");

        Carte sixv = new CarteSimple("6","Vert");
        Carte unbleu = new CarteSimple("1","Bleu");
        Carte PasseR = new CartePasserTour("Rouge");
        Carte PasseV = new CartePasserTour("Vert");

        String s ="jeux_test/JeuTestCartePasser.csv";

        Partie.getInstance().ajouterJoueur(Alice);
        Partie.getInstance().ajouterJoueur(Bob);
        Partie.getInstance().ajouterJoueur(Charles);
        Partie.getInstance().initialiserPartie(3,s);

        //Test de coups legaux avec des cartes Passer ton tour

        if(Partie.getInstance().getJoueurActuel()!="Alice")
        	throw new MauvaisJoueurException("Alice n'est pas le joueur courant");

        Alice.poserCarte(Alice.getMain().get(0));
        Alice.FinTour();
        
        if(Partie.getInstance().getJoueurActuel()!="Charles")
        	throw new MauvaisJoueurException("C'est pas Charles");
        if(!Partie.getInstance().getCarteTas(PasseR))
            throw new MauvaiseCarteException("La carte du tas n'est pas le Passer ton tour Rouge");

        Charles.poserCarte(Charles.getMain().get(1));
        Charles.FinTour();

        if(Partie.getInstance().getJoueurActuel()!="Bob")
            throw new MauvaisJoueurException("C'est pas Bob");
        if(!Partie.getInstance().getCarteTas(PasseV))
            throw new MauvaiseCarteException("La carte du tas n'est pas le Passer ton tour Vert");

        Bob.poserCarte(Bob.getMain().get(1));
        Bob.FinTour();

        if(Partie.getInstance().getJoueurActuel()!="Charles")
                throw new MauvaisJoueurException("C'est pas Charles");
        if(!Partie.getInstance().getCarteTas(sixv))
                throw new MauvaiseCarteException("La carte du tas n'est pas le Six Vert");

        Partie.FinPartie();
        Alice.getMain().clear(); Bob.getMain().clear(); Charles.getMain().clear();

        //Test d'une carte simple illégale sur un Passe ton Tour
        Partie.getInstance().ajouterJoueur(Alice);
        Partie.getInstance().ajouterJoueur(Bob);
        Partie.getInstance().ajouterJoueur(Charles);
        Partie.getInstance().initialiserPartie(3,s);



        if(Partie.getInstance().getJoueurActuel()!="Alice")
                throw new MauvaisJoueurException("Alice n'est pas le joueur courant");

        Alice.poserCarte(Alice.getMain().get(0));
        Alice.FinTour();

        if(Partie.getInstance().getJoueurActuel()!="Charles")
                throw new MauvaisJoueurException("C'est pas Charles");
        if(!Partie.getInstance().getCarteTas(PasseR))
                throw new MauvaiseCarteException("La carte du tas n'est pas le Passer ton tour Rouge");

        try{
                Charles.poserCarte(unbleu);
                Charles.FinTour();
        }
        catch(CoupIllegaux e){
                if(Charles.getNombreCarteMain()!=3)
                        throw new MauvaisNombreCarteException("Charles n'a pas trois cartes");
        }
        catch(Exception e){
                throw new JeuxUnoException("Exception non attendue");
        }

        Partie.FinPartie();
        Alice.getMain().clear(); Bob.getMain().clear(); Charles.getMain().clear();

        //Test d'un Passe ton Tour illegal sur une carte simple

        Partie.getInstance().ajouterJoueur(Alice);
        Partie.getInstance().ajouterJoueur(Bob);
        Partie.getInstance().ajouterJoueur(Charles);
        Partie.getInstance().initialiserPartie(3,s);

        if(Partie.getInstance().getJoueurActuel()!="Alice")
                throw new MauvaisJoueurException("Alice n'est pas le joueur courant");

        Alice.poserCarte(Alice.getMain().get(1));
        Alice.FinTour();

        Bob.poserCarte(Bob.getMain().get(2));
        Bob.FinTour();

        if(Partie.getInstance().getJoueurActuel()!="Charles")
                throw new MauvaisJoueurException("C'est pas Charles");
        if(Charles.getNombreCarteMain()!=3)
                throw new MauvaisNombreCarteException("Charles n'as pas 3 cartes");

        try{
                Charles.poserCarte(PasseV);
                Charles.FinTour();
        }
        catch(CoupIllegaux e){
                if(Charles.getNombreCarteMain()!=3)
                        throw new MauvaisNombreCarteException("Charles n'a pas trois cartes");
        }
        catch(Exception e){
                throw new JeuxUnoException("Exception non attendue");
        }

        Partie.FinPartie();
        System.out.println("Fin Test Passer");
	}
}
