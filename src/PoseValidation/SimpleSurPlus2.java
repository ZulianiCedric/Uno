package PoseValidation;

import Carte.Carte;
import Carte.CarteSimple;
import Carte.CartePlus2;

/**
 * <b>SimpleSurPlus2 est la classe representant une pose valide</b>
 */
public class SimpleSurPlus2 extends Valide{

    public SimpleSurPlus2(Valide suivant) {
        super(suivant);
    }

    @Override
    public boolean Test(Carte carte, Carte carteTas) throws Exception {
        CarteSimple carte1 = (CarteSimple) carte;
        CartePlus2 carte2 = (CartePlus2) carteTas;
        return carte1.getCouleur().equals(carte2.getCouleur());
    }

    @Override
    public boolean saitTester(Carte carte, Carte carteTas) {
        if(carte instanceof CarteSimple && carteTas instanceof CartePlus2)
        {
            return true;
        }
        return false;
    }
}
