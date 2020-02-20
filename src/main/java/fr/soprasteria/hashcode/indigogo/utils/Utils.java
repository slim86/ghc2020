package fr.soprasteria.hashcode.indigogo.utils;

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
                if (pLibraire.getListeLivres().get(j).getScore() > pLibraire.getListeLivres().get(j + 1).getScore()) {
                    temp = pLibraire.getListeLivres().get(j + 1);
                    pLibraire.getListeLivres().set(j + 1, pLibraire.getListeLivres().get(j));
                    pLibraire.getListeLivres().set(j + 1, temp);
                }
        }

        return pLibraire;
    }

    /**
     * 
     * @param T
     * @return
     */
    static int[] tri_a_bulles(int T[]) {
        int temp;
        for (int i = T.length - 1; i >= 1; i--) {
            for (int j = 0; j < i; j++)
                if (T[j] > T[j + 1]) {
                    temp = T[j + 1];
                    T[j + 1] = T[j];
                    T[j] = temp;
                }
        }
        return T;
    }

}
