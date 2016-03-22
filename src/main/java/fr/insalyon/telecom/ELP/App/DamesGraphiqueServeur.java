package fr.insalyon.telecom.ELP.App; 

import fr.insalyon.telecom.ELP.Noyau.Couleur;
import fr.insalyon.telecom.ELP.Noyau.EnsembleDePions;

import fr.insalyon.telecom.ELP.Sortie.VueGraphique;

import fr.insalyon.telecom.ELP.Entree.LecteurDeDeplacementDeVue;
import fr.insalyon.telecom.ELP.Entree.LecteurDeDeplacementDeFlux;
import fr.insalyon.telecom.ELP.Entree.Joueur;
import fr.insalyon.telecom.ELP.Entree.JoueurTransmetteur;
import fr.insalyon.telecom.ELP.Entree.Arbitre;

import java.net.ServerSocket;
import java.net.Socket;

import java.io.IOException;
 
public class DamesGraphiqueServeur {
	
	public static void main(String[] args) throws IOException {
		
		EnsembleDePions configuration = new EnsembleDePions();
		Arbitre arbitre = new Arbitre();
		ServerSocket connection = new ServerSocket(8080);
		Socket socket = connection.accept();
		LecteurDeDeplacementDeVue lecteur = new LecteurDeDeplacementDeVue(configuration);
		Joueur joueurBlanc = new Joueur(Couleur.blanc(), configuration, arbitre, 
								        new LecteurDeDeplacementDeFlux(socket.getInputStream()));
		Joueur joueurNoir  = new JoueurTransmetteur(Couleur.noir(), configuration, arbitre, lecteur, 
												    socket.getOutputStream());
		VueGraphique vue = new VueGraphique(lecteur, "DamesServeur: noir");
		
		configuration.addObserver(vue);
		configuration.init();
				
		Thread threadVue = new Thread(vue);
		Thread threadJoueurBlanc = new Thread(joueurBlanc);
		Thread threadJoueurNoir = new Thread(joueurNoir);
		
		threadVue.start();
		threadJoueurBlanc.start();
		threadJoueurNoir.start();
	}
}
