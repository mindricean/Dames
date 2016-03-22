package fr.insalyon.telecom.ELP.App; 

import fr.insalyon.telecom.ELP.Noyau.Couleur;
import fr.insalyon.telecom.ELP.Noyau.EnsembleDePions;

import fr.insalyon.telecom.ELP.Sortie.VueShell;

import fr.insalyon.telecom.ELP.Entree.LecteurDeDeplacementDeFlux;
import fr.insalyon.telecom.ELP.Entree.Joueur;
import fr.insalyon.telecom.ELP.Entree.Arbitre;
 
public class DamesShell {
	
	public static void main(String[] args) {
		
		EnsembleDePions configuration = new EnsembleDePions();
		Arbitre arbitre = new Arbitre();
		Joueur joueurBlanc = new Joueur(Couleur.blanc(), configuration, arbitre, new LecteurDeDeplacementDeFlux(System.in));
		Joueur joueurNoir  = new Joueur(Couleur.noir(),  configuration, arbitre, new LecteurDeDeplacementDeFlux(System.in));
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
