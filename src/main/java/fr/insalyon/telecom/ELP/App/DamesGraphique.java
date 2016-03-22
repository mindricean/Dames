package fr.insalyon.telecom.ELP.App; 

import fr.insalyon.telecom.ELP.Noyau.Couleur;
import fr.insalyon.telecom.ELP.Noyau.EnsembleDePions;

import fr.insalyon.telecom.ELP.Sortie.VueGraphique;


import fr.insalyon.telecom.ELP.Entree.LecteurDeDeplacementDeVue;
import fr.insalyon.telecom.ELP.Entree.Joueur;
import fr.insalyon.telecom.ELP.Entree.Arbitre;

public class DamesGraphique {
	
	public static void main(String[] args) {
		
		EnsembleDePions configuration = new EnsembleDePions();
		Arbitre arbitre = new Arbitre();
		LecteurDeDeplacementDeVue lecteur = new LecteurDeDeplacementDeVue(configuration);
		Joueur joueurBlanc = new Joueur(Couleur.blanc(), configuration, arbitre, lecteur);
		Joueur joueurNoir  = new Joueur(Couleur.noir(),  configuration, arbitre, lecteur);
		VueGraphique vue = new VueGraphique(lecteur, "Dames:Meme host");
		
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

