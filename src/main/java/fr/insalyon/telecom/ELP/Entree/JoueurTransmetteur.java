package fr.insalyon.telecom.ELP.Entree; 

import fr.insalyon.telecom.ELP.Noyau.Couleur; 
import fr.insalyon.telecom.ELP.Noyau.EnsembleDePions; 
import fr.insalyon.telecom.ELP.Noyau.Deplacement; 

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * Classe modelisant un joueur transmetteur
 */
public class JoueurTransmetteur extends Joueur {
  
	protected PrintWriter out;

    /** 
     *  Constructeur
     * @param uneCouleur couleur du joueur
     * @param uneConfiguration configuration des pions
     * @param unArbitre arbitre
     * @param unLecteur lecteur
     * @param output flux de sortie
     */
    public JoueurTransmetteur(Couleur uneCouleur, EnsembleDePions uneConfiguration, Arbitre unArbitre, 
							  LecteurDeDeplacement unLecteur, OutputStream output) {
		super(uneCouleur, uneConfiguration, unArbitre, unLecteur);
		out = new PrintWriter(new OutputStreamWriter( output ), true );
    }

    /**
     * Methode dans laquelle le joueur realise un nouveau deplacement et l'envoye dans le socket.
     * @return vrai, si le deplacement, valide, est realise, faux sinon
     */
    @Override
    protected boolean jouer() {
		Deplacement monDeplacement = monLecteur.obtenir();
		if (maConfiguration.realiser(monDeplacement, maCouleur)) {
			out.println(monDeplacement);
			return true;
		} else {
			return false;
		}     
    }

}
