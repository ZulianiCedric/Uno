package PoseValidation;

import Carte.Carte;
import Carte.CartePasserTour;
import Carte.CartePlus2;

/**
 * <b>Plus2surPasser est la classe representant une pose valide</b>
 */
public class Plus2SurPasser extends Valide{
    public Plus2SurPasser(Valide suivant) {
        super(suivant);
    }

    @Override
    public boolean Test(Carte carte, Carte carteTas) throws Exception {
        CartePlus2 carte1 = (CartePlus2) carteTas;
        CartePasserTour carte2 = (CartePasserTour) carte;
        return (carte1.getCouleur().equals(carte2.getCouleur()));
    }

    @Override
    public boolean saitTester(Carte carte, Carte carteTas) {
        if(carte instanceof CartePlus2 && carteTas instanceof CartePasserTour)
        {
            return true;
        }
        return false;
    }
}
