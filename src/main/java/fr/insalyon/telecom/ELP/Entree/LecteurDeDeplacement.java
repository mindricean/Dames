package fr.insalyon.telecom.ELP.Entree; 

import fr.insalyon.telecom.ELP.Noyau.Deplacement; 
import fr.insalyon.telecom.ELP.Noyau.Couleur; 

/**
 * Interface listant les methodes 
 * que possedent les objets capable
 * de lire des deplacements
 */
public interface LecteurDeDeplacement {

    /** 
     * Methode indiquant qu'un deplacement complet 
     * a ete lu
     * @return vrai si complet, faux sinon
     */
    public boolean estComplet(); 

    /**
     * Accede au deplacement lu, puis 
     * remet a zero le deplacement de 
     * sorte que estComplet() renvoie 
     * faux immediatement apres l'appel 
     * @return le deplacement
     */
    public Deplacement obtenir(); 
    
    /**
     * Methode qui permet d'initialiser la culeur du joueur associe a
     * ce lecteur.
     */
    public void couleur(Couleur couleurJoueur);

}
