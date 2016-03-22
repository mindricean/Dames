package fr.insalyon.telecom.ELP.Entree; 

import fr.insalyon.telecom.ELP.Noyau.Position; 
import fr.insalyon.telecom.ELP.Noyau.Deplacement;  
import fr.insalyon.telecom.ELP.Noyau.EnsembleDePions;
import fr.insalyon.telecom.ELP.Noyau.Couleur;

/**
 * Classe modelisant un lecteur de deplacement
 * a partir d'un vue 
 */
public class LecteurDeDeplacementDeVue implements LecteurDeDeplacement {

    /** deplacement */
    private Deplacement monDeplacement = new Deplacement(); 
    /** boolean indiquant si le deplacement est complet */
    private boolean estComplet = false;
    /** la couleur associe a ce lecteur*/
    private Couleur couleurJoueur;
    /** la configuration associe a ce lecteur*/
    private EnsembleDePions config;
    
    /** 
     * Methode indiquant qu'un deplacement complet 
     * a ete lu
     * @return vrai si complet, faux sinon
     */
    @Override
    public boolean estComplet() {
		return estComplet;
    }
    
    /**
     * Constructeur du lecteur
     */
    public LecteurDeDeplacementDeVue(EnsembleDePions config) {
		this.config = config;
	}
	
	/**
     * Methode qui permet d'initialiser la culeur du joueur associe a
     * ce lecteur.
     */
	public void couleur(Couleur couleurJoueur) {
		this.couleurJoueur = couleurJoueur;
	}
	
    /**
     * Retourne le deplacement lu, 
     * puis remet a zero le deplacement
     * @return deplacement lu
     */
    @Override
    public Deplacement obtenir() {
		Deplacement res = monDeplacement; 
		estComplet = false; 
		monDeplacement = new Deplacement(); 
		System.out.println();
		return res; 
    }
    
    /**
     * Methode qui ajoute une position dans la liste de deplacements
     * du lecteur.
     */
    public void ajouterPosition(Position pos) {
		monDeplacement.add(pos);
		System.out.print(pos + " ");
		estComplet = (monDeplacement.size() == config.deplacementMax(couleurJoueur));
	}
}
