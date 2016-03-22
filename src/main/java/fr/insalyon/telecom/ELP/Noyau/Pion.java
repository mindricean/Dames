package fr.insalyon.telecom.ELP.Noyau; 

/**
 * Classe modelisant un pion
 */
public class Pion {

    /** couleur du pion */
    protected final Couleur maCouleur; 

    /** 
     * Constructeur
     * @param couleur couleur du pion
     */
    public Pion(Couleur uneCouleur) {
		maCouleur = uneCouleur; 
    }

    /**
     * Compare la couleur du pion courant avec une autre couleur
     * @param uneCouleur 
     * @return vrai si les deux couleurs sont identiques, faux sinon
     */
    public boolean aMemeCouleur(Couleur uneCouleur) {
		return (maCouleur.equals(uneCouleur)); 
    }

    /**
     * @return chaine de caractere representant le pion
     */
    @Override 
    public String toString() {
		return "[Pion "+maCouleur.toString()+"]"; 
    }
}
