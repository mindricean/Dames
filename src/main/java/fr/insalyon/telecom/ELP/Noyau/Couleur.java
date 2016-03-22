package fr.insalyon.telecom.ELP.Noyau; 
/**
 * Classe modelisant la couleur des pions
 * ou des joueurs: blanc ou noir. 
 */
public final class Couleur {

    /** boolean vrai si noir */
    private final boolean estNoir; 

    /**
     * Constructeur 
     * @param estNoir vrai si noir
     */
    private Couleur(boolean estNoir) {
		this.estNoir = estNoir; 
    }

    /**
     * Methode usine qui fabrique une couleur noire
     * @return couleur noire
     */
    public static Couleur noir() {
		return new Couleur(true); 
    }

    /**
     * Methode usine qui fabrique une couleur blanche
     * @return couleur blanche
     */
    public static Couleur blanc() {
		return new Couleur(false); 
    }

    /**
     * Methode comparant la couleur courante avec une autre
     * @param obj une autre couleur
     * @return vrai si les couleurs sont identiques, faux sinon
     */
    @Override 
    public boolean equals(Object obj) {
		if (obj == null)
			return false; 
		else {
			Couleur autreCouleur = (Couleur) obj; 
			return ( estNoir == autreCouleur.estNoir ); 
		}
    }

    /**
     * Methode renvoyant la representation textuelle de la couleur
     * @return "N" si noir, "B" si blanc
     */
    @Override
    public String toString() {
		String res = (estNoir)?"N":"B"; 
		return res; 
    }
}
