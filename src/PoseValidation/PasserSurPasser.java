package PoseValidation;

import Carte.Carte;
import Carte.CartePasserTour;

/**
 * <b>PasserSurPasser est la classe representant une pose valide</b>
 */
public class PasserSurPasser extends Valide{

    public PasserSurPasser(Valide suivant) {
        super(suivant);
    }

    @Override
    public boolean Test(Carte carte, Carte carteTas) throws Exception {
        CartePasserTour carte1 = (CartePasserTour) carteTas;
        CartePasserTour carte2 = (CartePasserTour) carte;
        return (carte1.getCouleur().equals(carte2.getCouleur()) || !carte1.getCouleur().equals(carte2.getCouleur()));
    }

    @Override
    public boolean saitTester(Carte carte, Carte carteTas) {
        if(carte instanceof CartePasserTour && carteTas instanceof CartePasserTour)
        {
            return true;
        }
        return false;
    }
}