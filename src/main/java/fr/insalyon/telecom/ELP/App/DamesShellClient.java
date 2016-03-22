package fr.insalyon.telecom.ELP.App; 

import fr.insalyon.telecom.ELP.Noyau.Couleur;
import fr.insalyon.telecom.ELP.Noyau.EnsembleDePions;

import fr.insalyon.telecom.ELP.Sortie.VueShell;

import fr.insalyon.telecom.ELP.Entree.LecteurDeDeplacement;
import fr.insalyon.telecom.ELP.Entree.LecteurDeDeplacementDeFlux;
import fr.insalyon.telecom.ELP.Entree.Joueur;
import fr.insalyon.telecom.ELP.Entree.JoueurTransmetteur;
import fr.insalyon.telecom.ELP.Entree.Arbitre;

import java.net.Socket;

import java.io.IOException;
 
public class DamesShellClient {
	
	public static void main(String[] args) throws IOException {
		
		EnsembleDePions configuration = new EnsembleDePions();
		Arbitre arbitre = new Arbitre();
		Socket socket = new Socket("localhost", 8080);
		Joueur joueurBlanc = new JoueurTransmetteur(Couleur.blanc(), configuration, arbitre, 
													new LecteurDeDeplacementDeFlux(System.in), socket.getOutputStream());
		Joueur joueurNoir  = new Joueur(Couleur.noir(), configuration, arbitre, 
									    new LecteurDeDeplacementDeFlux(socket.getInputStream()));
		
		VueShell vue = new VueShell();
		
		configuration.addObserver(vue);
		configuration.init();
				
		Thread threadVue = new Thread(vue);
		Thread threadJoueurBlanc = new Thread(joueurBlanc);
		Thread threadJoueurNoir  = new Thread(joueurNoir);
		
		threadVue.start();
		threadJoueurBlanc.start();
		threadJoueurNoir.start();
	}
}

