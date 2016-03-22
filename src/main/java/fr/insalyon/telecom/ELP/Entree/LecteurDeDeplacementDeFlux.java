package fr.insalyon.telecom.ELP.Entree; 

import fr.insalyon.telecom.ELP.Noyau.Position; 
import fr.insalyon.telecom.ELP.Noyau.Deplacement;  
import fr.insalyon.telecom.ELP.Noyau.Couleur;  

import java.io.InputStream; 
import java.io.InputStreamReader;
import java.io.BufferedReader;

/**
 * Classe modelisant un lecteur de deplacement
 * a partir d'un flux d'entree 
 */
public class LecteurDeDeplacementDeFlux implements LecteurDeDeplacement {

    /** flux d'entree */
    private BufferedReader monFlux; 
    /** deplacement */
    private Deplacement monDeplacement = null; 
    /** boolean indiquant si le deplacement est complet */
    private boolean estComplet = false; 
	
	/** la couleur associa ce lecteur */
	private Couleur couleurJoueur;
	
	/** Methode qui initialise la couleur du lecteur */
	public void couleur(Couleur couleurJoueur) {
		this.couleurJoueur = couleurJoueur;
	}

    /**
     * Constructeur 
     * @param unFlux flux d'entree
     */
    public LecteurDeDeplacementDeFlux(InputStream unFlux) {
		monFlux = new BufferedReader( new InputStreamReader(unFlux) );
    }

    /**
     * Lit un deplacement a partir du flux, puis renvoie vrai
     * @return vrai
     */
    @Override
    public boolean estComplet() {
		try {
			//un deplacement est une suite de positions separees 
			//par des espaces
			String ligne = monFlux.readLine(); 
			String[] chaines = ligne.split(" ");
			monDeplacement = new Deplacement(); 
			for (String chaine: chaines) {
				Position position = new Position(chaine);
				monDeplacement.add(position);
			}
			estComplet = true; 
			return estComplet;
		} catch (Exception e) {
			estComplet = false; 
			return estComplet; 
		}
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
		monDeplacement = null; 
		return res; 
    }
}
