package fr.soprasteria.hashcode.indigogo.utils;

import java.util.List;

import fr.soprasteria.hashcode.indigogo.model.Librairie;
import fr.soprasteria.hashcode.indigogo.model.Livre;

public class Utils {

    /**
     * Classe les livres dans les librairies
     * 
     * @param pLibraire
     * @return
     */
    public Librairie triLivreParScore(Librairie pLibraire) {
        Livre temp;
        for (int i = pLibraire.getListeLivres().size() - 1; i >= 1; i--) {
            for (int j = 0; j < i; j++)
                if (pLibraire.getListeLivres().get(j).getScore() < pLibraire.getListeLivres().get(j + 1).getScore()) {
                    temp = pLibraire.getListeLivres().get(j + 1);
                    pLibraire.getListeLivres().set(j + 1, pLibraire.getListeLivres().get(j));
                    pLibraire.getListeLivres().set(j + 1, temp);
                }
        }

        return pLibraire;
    }

    /**
     * retourne la librairie avec le meilleur score
     * @return
     */
    public Librairie calculerOuverture(List<Librairie> pLibrairiesFermees, int nbJoursRestants) {

        Librairie pLibrairieBestScore = new Librairie();
        int score = 0;
        int somme = 0;

        if (null != pLibrairiesFermees && !pLibrairiesFermees.isEmpty()
                && pLibrairiesFermees.size()>1) {
            for (Librairie pLibrairieEnCours : pLibrairiesFermees) {
                int LivreATraiter = pLibrairieEnCours.getCapaciteTraitement()
                        * (nbJoursRestants - pLibrairieEnCours.getNbJoursOuverture());
                somme = 0;
                for (int i = 0; i<LivreATraiter-1; i++) {
                    somme =somme +  pLibrairieEnCours.getListeLivres().get(i).getScore();
                }
                if (somme>score) {
                    pLibrairieBestScore = pLibrairieEnCours;
                }
            }
        } else if (null != pLibrairiesFermees && !pLibrairiesFermees.isEmpty()
                && pLibrairiesFermees.size()==1) {
            if (pLibrairiesFermees.get(0).getNbJoursOuverture()<nbJoursRestants) {
                pLibrairieBestScore = pLibrairiesFermees.get(0);
            }
        }
        return pLibrairieBestScore;
    }

}
