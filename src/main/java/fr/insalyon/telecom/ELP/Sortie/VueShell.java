package fr.insalyon.telecom.ELP.Sortie; 

import fr.insalyon.telecom.ELP.Noyau.Constantes; 
import fr.insalyon.telecom.ELP.Noyau.Couleur;
import fr.insalyon.telecom.ELP.Noyau.Pion; 
import fr.insalyon.telecom.ELP.Noyau.Position; 
import fr.insalyon.telecom.ELP.Noyau.EnsembleDePions; 

import java.util.Observer;
import java.util.Observable;

/**
 * Classe modelisant la vue sur la sortie standard. 
 * Cette classe observe le modele et l'affiche a 
 * l'ecran apres chaque modification.
 */
public class VueShell implements Runnable, Observer {

    /**
     * Methode affichant sur la sortie standard la
     * representation textuelle du damier: 
     * O pour une case blanche, 
     * X pour une case noir inoccupee, 
     * N pour un pion noir,
     * B pour un pion blanc. 
     */
    private void affichage(EnsembleDePions e) {
    	System.out.println("  0 1 2 3 4 5 6 7 8 9 "); 
    	for (int j = 0; j < Constantes.N; j++) {
    	    System.out.print(j + " "); 
    	    for (int i = 0; i < Constantes.N; i++) {
				Position pos = new Position(i,j); 
				if ( pos.estValide() ) {
					Pion pion = e.obtenirPion(pos);
					if(pion != null) {
						if(pion.aMemeCouleur(Couleur.noir())) {
							System.out.print("N ");
						} else {
							System.out.print("B ");
						}
					} else {
						System.out.print("X ");
					}
				} else {
					System.out.print("O "); 
				} 
    	    }
    	    System.out.println(); 
    	}
    	System.out.println(); 
    }
    
    /**
     * Methode executee au demarrage d'un thread.  
     */
    @Override
    public void run() {
		while (true) {
			//attente d'un appel de methode
		}
    }
    
    /**
     * Methode qui met a jour l'affichage du damier.
     */
    @Override
    public void update(Observable e, Object arg) {
		affichage((EnsembleDePions)e);
	} 
}
