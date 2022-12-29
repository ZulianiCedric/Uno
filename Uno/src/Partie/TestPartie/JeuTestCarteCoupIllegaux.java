package Partie.TestPartie;

import Carte.Carte;
import Carte.CarteSimple;
import Exceptions.*;
import Joueur.Joueur;
import Partie.Partie;

public class JeuTestCarteCoupIllegaux {
    public void TestCarteSimpleCoupIllegaux() throws Exception {
        System.out.println("Debut TestCarteSimpleCoupIllegal");

        Joueur Alice = new Joueur("Alice");
        Joueur Bob = new Joueur("Bob");
        Joueur Charles = new Joueur("Charles");
        Partie.getInstance().ajouterJoueur(Alice);
        Partie.getInstance().ajouterJoueur(Bob);
        Partie.getInstance().ajouterJoueur(Charles);
        Carte deuxv = new CarteSimple("2","Vert");
        Carte sixj = new CarteSimple("6","Jaune");
        Carte unr = new CarteSimple("1","Rouge");
        Carte deuxb = new CarteSimple("2","Bleu");
        String s ="jeux_test/JeuTestCarteSimple.csv";
        Partie.getInstance().initialiserPartie(3,s);
        
        //Test d'une carte illégale

        try {
            Alice.poserCarte(Alice.getMain().get(1)); //Alice joue 6 jaune 
        } catch (CoupIllegaux e) {
            if(!Alice.PresenceCarteDansMain(sixj) && !Alice.PresenceCarteDansMain(unr)
                    && !Alice.PresenceCarteDansMain(deuxv) && Alice.getNombreCarteMain() !=3)
                throw new MauvaiseCarteException("Alice ne possede pas le 6 Jaune et le 1 Rouge et " +
                        "le 2 vert ou elle n'a pas 3 cartes");
        }
        catch (Exception e) {
            throw new JeuxUnoException("Exception autre que CoupIllegaux");
        }

        Partie.FinPartie();
        Alice.getMain().clear(); Bob.getMain().clear(); Charles.getMain().clear();

        //Test d'un joueur qui pose deux cartes légales de suite

        Partie.getInstance().ajouterJoueur(Alice);
        Partie.getInstance().ajouterJoueur(Bob);
        Partie.getInstance().ajouterJoueur(Charles);
        Partie.getInstance().initialiserPartie(3,s);

        Alice.poserCarte(Alice.getMain().get(0)); //Alice joue 2 vert
        Alice.FinTour();
        Bob.poserCarte(Bob.getMain().get(0)); //Bob joue 2 bleu
        Bob.FinTour();
        
        Charles.poserCarte(Charles.getMain().get(0)); //Charles joue 2 bleu
        if(Charles.getMain().size()!=2)
            throw new MauvaisNombreCarteException("Charles n'a pas 2 cartes");

        try {
            Charles.poserCarte(Charles.getMain().get(0)); //Charles joue 7 bleu
        } catch (CoupIllegaux e) {
            if(!Charles.PresenceCarteDansMain(deuxb) && Charles.getMain().size()!=2)
                throw new MauvaiseCarteException("Charles n'a pas le 2 bleu et il n'a pas 2 cartes");
        }
        catch (Exception e) {
            throw new JeuxUnoException("Exception autre que CoupIllegaux");
        }

        Partie.FinPartie();
        Alice.getMain().clear(); Bob.getMain().clear(); Charles.getMain().clear();

        //Test d'un joueur qui finit son tour sans poser de carte
        Partie.getInstance().ajouterJoueur(Alice);
        Partie.getInstance().ajouterJoueur(Bob);
        Partie.getInstance().ajouterJoueur(Charles);
        Partie.getInstance().initialiserPartie(3,s);

        try {
            Alice.FinTour();
        } catch (CoupIllegaux e) {
            if(Alice.getMain().size()!=3)
                throw new JeuxUnoException("Le joueur actuel "+Partie.getInstance().getJoueurActuel()+" n'as pas jouer");
        }
        catch (Exception e) {
            throw new JeuxUnoException("Exception autre que CoupIllegaux");
        }

        Partie.FinPartie();
        Alice.getMain().clear(); Bob.getMain().clear(); Charles.getMain().clear();

        //Test d'un joueur qui joue puis pioche

        Partie.getInstance().ajouterJoueur(Alice);
        Partie.getInstance().ajouterJoueur(Bob);
        Partie.getInstance().ajouterJoueur(Charles);
        Partie.getInstance().initialiserPartie(3,s);

        Alice.poserCarte(Alice.getMain().get(0)); //Alice joue 2 vert
        try {
            Alice.piocher();
        }
        catch (CoupIllegaux e) {
            if(Alice.getMain().size()!=2)
                throw new MauvaisNombreCarteException("Le joueur "+Partie.getInstance().getJoueurActuel()+" n'as pas 2 cartes");
            if(!Partie.getInstance().getCartePioche(sixj))
                throw new MauvaiseCarteException("La carte de la pioche n'est pas le 6 Jaune");
        }
        catch (Exception e) {
            throw new JeuxUnoException("Exception autre que CoupIllegaux");
        }
        Partie.FinPartie();
        Alice.getMain().clear(); Bob.getMain().clear(); Charles.getMain().clear();
        System.out.println("Fin TestCarteSimpleCoupIllegal");
    }

    public void TestPunition() throws Exception{
        System.out.println("Debut TestCarteSimplePunition");

        Joueur Alice = new Joueur("Alice");
        Joueur Bob = new Joueur("Bob");
        Joueur Charles = new Joueur("Charles");
        Partie.getInstance().ajouterJoueur(Alice);
        Partie.getInstance().ajouterJoueur(Bob);
        Partie.getInstance().ajouterJoueur(Charles);

        Carte deuxv = new CarteSimple("2","Vert");
        Carte sixj = new CarteSimple("6","Jaune");
        Carte unr = new CarteSimple("1","Rouge");
        Carte quatrer = new CarteSimple("4","Rouge");
        Carte deuxb = new CarteSimple("2","Bleu");
        String s ="jeux_test/JeuTestCarteSimple.csv";
        Partie.getInstance().initialiserPartie(3,s);


        //Punition pour joueur courant

        if(Partie.getInstance().getJoueurActuel()!="Alice")
            throw new MauvaisJoueurException("Le joueur actuel n'est pas Alice");
        else{
            try {
                Alice.poserCarte(Alice.getMain().get(1)); //Alice joue 6 jaune
            } catch (CoupIllegaux e) {
                Alice.punition();
                if(!Partie.getInstance().getJoueurActuel().equals("Bob"))
                    throw new CoupIllegaux("Le joueur actuel n'est pas Bob");
                if(Alice.getNombreCarteMain()!=5 && !(Alice.PresenceCarteDansMain(sixj) && Alice.PresenceCarteDansMain(quatrer)))
                    throw new CoupIllegaux("Alice n'a pas 5 cartes");
            }
            catch (Exception e) {
                throw new JeuxUnoException("Exception autre que CoupIllegaux");
            }
        }
        Partie.FinPartie();
        Alice.getMain().clear(); Bob.getMain().clear(); Charles.getMain().clear();

        //Punition pour joueur lorsque ce n'est pas sont tour

        Partie.getInstance().ajouterJoueur(Alice);
        Partie.getInstance().ajouterJoueur(Bob);
        Partie.getInstance().ajouterJoueur(Charles);

        Partie.getInstance().initialiserPartie(3,s);

        if(Partie.getInstance().getJoueurActuel()!="Alice")
            throw new MauvaisJoueurException("Le joueur actuel n'est pas Alice");
        try{
            Bob.piocher();
        }
        catch (CoupIllegaux e){
            Bob.punition();
            if(Partie.getInstance().getJoueurActuel()!="Alice")
                throw new MauvaisJoueurException("Le joueur actuel n'est pas Alice");
            if(Bob.getNombreCarteMain()!=5 && !Bob.PresenceCarteDansMain(sixj) && !Bob.PresenceCarteDansMain(quatrer))
                throw new MauvaiseCarteException("Bob n'as pas 5 cartes et/ou n'as pas le 6 Jaune et le 4 Rouge");
            if(!Partie.getInstance().getCartePioche(deuxv))
                throw new MauvaiseCarteException("La carte de la pioche n'est pas le 2 Vert");
        }
        catch (Exception e) {
            throw new JeuxUnoException("Exception autre que CoupIllegaux");
        }

        Partie.FinPartie();
        Alice.getMain().clear(); Bob.getMain().clear(); Charles.getMain().clear();
    }
        
}
