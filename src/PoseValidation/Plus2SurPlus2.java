package PoseValidation;

import Carte.Carte;
import Carte.CartePlus2;

/**
 * <b>Plus2SurPlus2 est la classe representant une pose valide</b>
 */
public class Plus2SurPlus2 extends Valide{
    public Plus2SurPlus2(Valide suivant) {
        super(suivant);
    }

    @Override
    public boolean Test(Carte carte, Carte carteTas) throws Exception {
        CartePlus2 carte1 = (CartePlus2) carteTas;
        CartePlus2 carte2 = (CartePlus2) carte;
        return (carte1.getCouleur().equals(carte2.getCouleur()) || !carte1.getCouleur().equals(carte2.getCouleur()));
    }

    @Override
    public boolean saitTester(Carte carte, Carte carteTas) {
        if(carte instanceof CartePlus2 && carteTas instanceof CartePlus2)
        {
            return true;
        }
        return false;
    }
}
