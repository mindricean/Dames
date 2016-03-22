package fr.insalyon.telecom.ELP.Noyau; 

/**
 * Classe modelisant une dame
 */
public class Dame extends Pion {

    /** 
     * Constructeur
     * @param couleur couleur de la dame
     */
    public Dame(Couleur uneCouleur) {
		super(uneCouleur); 
    }

    /**
     * @return chaine de caractere representant la dame
     */
    @Override 
    public String toString() {
		return "[Dame "+maCouleur.toString()+"]"; 
    }
}
