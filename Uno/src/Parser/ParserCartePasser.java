package Parser;

import Carte.Carte;
import Carte.CartePasserTour;

public class ParserCartePasser extends Parser{
    
    public ParserCartePasser(Parser suivant){
        super(suivant);
    }

    @Override
    public void parser(String ligne) throws Exception {
        String[] nom = ligne.split(";");
        if(saitParser(ligne)){
            Carte Passer = new CartePasserTour(nom[1]);
            Partie.Partie.getInstance().getPioche().add(Passer);
        }else
            throw new Exception("Pas une carte passer");
    }

    @Override
    public boolean saitParser(String ligne) {
        String[] nom = ligne.split(";");
        if(nom[0].matches("CartePasser"))return true;
        return false;
    }

}
