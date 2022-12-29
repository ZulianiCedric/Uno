package Parser;

import Carte.Carte;
import Carte.CartePlus2;

public class ParserCartePlus2 extends Parser{
    
    public ParserCartePlus2(Parser suivant){
        super(suivant);
    }

    @Override
    public void parser(String ligne) throws Exception {
        String[] nom = ligne.split(";");
        if(saitParser(ligne)){
            Carte Plus2 = new CartePlus2(nom[1]);
            Partie.Partie.getInstance().getPioche().add(Plus2);
        }else
            throw new Exception("Pas une carte simple");
    }

    @Override
    public boolean saitParser(String ligne) {
        String[] nom = ligne.split(";");
        if(nom[0].matches("CartePlus2"))return true;
        return false;
    }

}
