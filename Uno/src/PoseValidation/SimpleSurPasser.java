package PoseValidation;

import Carte.Carte;
import Carte.CartePasserTour;
import Carte.CarteSimple;

/**
 * <b>SimpleSurPasser est la classe representant une pose valide</b>
 */
public class SimpleSurPasser extends Valide{


    public SimpleSurPasser(Valide suivant) {
        super(suivant);
    }

    @Override
    public boolean Test(Carte carte, Carte carteTas) throws Exception {
        CarteSimple carte1 = (CarteSimple) carte;
        CartePasserTour carte2 = (CartePasserTour) carteTas;
        return carte1.getCouleur().equals(carte2.getCouleur());

    }

    @Override
    public boolean saitTester(Carte carte, Carte carteTas) {
        if(carte instanceof CarteSimple && carteTas instanceof CartePasserTour)
        {
            return true;
        }
        return false;
    }
}