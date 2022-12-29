package PoseValidation;

import Carte.Carte;
import Carte.CartePasserTour;
import Carte.CarteSimple;

/**
 * <b>PasserSurSimple est la classe representant une pose valide</b>
 */
public class PasserSurSimple extends Valide{


    public PasserSurSimple(Valide suivant) {
        super(suivant);
    }

    @Override
    public boolean Test(Carte carte, Carte carteTas) throws Exception {
        CarteSimple carte1 = (CarteSimple) carteTas;
        CartePasserTour carte2 = (CartePasserTour) carte;
        return carte1.getCouleur().equals(carte2.getCouleur());
    }

    @Override
    public boolean saitTester(Carte carte, Carte carteTas) {
        if(carte instanceof CartePasserTour && carteTas instanceof CarteSimple)
        {
            return true;
        }
        return false;
    }
}