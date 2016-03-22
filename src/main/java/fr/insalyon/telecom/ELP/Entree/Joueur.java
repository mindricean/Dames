package fr.insalyon.telecom.ELP.Entree; 

import fr.insalyon.telecom.ELP.Noyau.Couleur; 
import fr.insalyon.telecom.ELP.Noyau.EnsembleDePions; 

/**
 * Classe modelisant un joueur
 */
public class Joueur implements Runnable {

    /** couleur du joueur */
    protected Couleur maCouleur;
    /** configuration des pions sur le damier */
    protected EnsembleDePions maConfiguration; 
    /** arbitre permettant de synchroniser les joueurs */
    protected Arbitre monArbitre; 
    /** lecteur capable de renvoyer le deplacement
	que le joueur souhaite effectuer */
    protected LecteurDeDeplacement monLecteur;  

    /** 
     *  Constructeur
     * @param uneCouleur couleur du joueur
     * @param uneConfiguration configuration des pions
     * @param unArbitre arbitre
     * @param unLecteur lecteur
     */
    public Joueur(Couleur uneCouleur, EnsembleDePions uneConfiguration, Arbitre unArbitre, LecteurDeDeplacement unLecteur) {
		maCouleur = uneCouleur; 
		maConfiguration = uneConfiguration;
		monArbitre = unArbitre; 
		monLecteur = unLecteur; 
    }

    /**
     * Methode dans laquelle le joueur realise un nouveau deplacement.
     * @return vrai, si le deplacement, valide, est realise, faux sinon
     */
    protected boolean jouer() {
		return (maConfiguration.realiser(monLecteur.obtenir(), maCouleur));	     
    }

    /**
     * Methode indiquant si un joueur a une couleur donnee
     * @param uneCouleur toute couleur
     * @return vrai si les couleurs sont identiques, faux sinon
     */
    public boolean aMemeCouleur(Couleur uneCouleur) {
		return (maCouleur.equals(uneCouleur)); 
    }

    /**
     * Representation textuelle du joueur
     * @return nom de la classe, suivi de la couleur du joueur
     */
    @Override
    public String toString() {
		return getClass().getSimpleName() + maCouleur; 
    }

    /**
     * Methode executee lors du demarrage du thread
     */
    @Override
    public void run() {
	
		if (aMemeCouleur(Couleur.noir())) {
			monArbitre.laisseLaMain(); //attend le tour des blancs, qui commencent
		}

		while (!maConfiguration.estTermine(maCouleur)) {

			System.out.println(toString() + "..."); 
			monLecteur.couleur(maCouleur);
			monLecteur.obtenir(); //vider le buffer de deplacement
			while (!monLecteur.estComplet()) {
				//attendre que le deplacement soit lu
				try {
					Thread.sleep(1); 
				} catch (InterruptedException e) {
				}
			}
			if (jouer()) {
				monArbitre.laisseLaMain(); 
			} 
		}
    }
}
