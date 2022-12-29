package Parser;

import Carte.Carte;
import Carte.CarteSimple;

public class ParserCarteSimple extends Parser{
    
    public ParserCarteSimple(Parser suivant){
        super(suivant);
    }

    @Override
    public void parser(String ligne) throws Exception {
        String[] nom = ligne.split(";");
        if(saitParser(ligne)){
            Carte Simple = new CarteSimple(nom[2],nom[1]);
            Partie.Partie.getInstance().getPioche().add(Simple);
        }else
            throw new Exception("Pas une carte simple");
    }

    @Override
    public boolean saitParser(String ligne) {
        String[] nom = ligne.split(";");
        if(nom[0].matches("CarteSimple"))return true;
        return false;
    }

}
