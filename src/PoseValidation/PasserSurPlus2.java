package PoseValidation;

import Carte.Carte;
import Carte.CartePasserTour;
import Carte.CartePlus2;

/**
 * <b>PasserSurPlus2 est la classe representant une pose valide</b>
 */
public class PasserSurPlus2 extends Valide{
    public PasserSurPlus2(Valide suivant) {
        super(suivant);
    }

    @Override
    public boolean Test(Carte carte, Carte carteTas) throws Exception {
        CartePasserTour carte1 = (CartePasserTour) carteTas;
        CartePlus2 carte2 = (CartePlus2) carte;
        return (carte1.getCouleur().equals(carte2.getCouleur()));
    }

    @Override
    public boolean saitTester(Carte carte, Carte carteTas) {
        if(carte instanceof CartePasserTour && carteTas instanceof CartePlus2)
        {
            return true;
        }
        return false;
    }
}
