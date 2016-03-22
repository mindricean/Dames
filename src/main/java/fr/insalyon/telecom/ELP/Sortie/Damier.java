package fr.insalyon.telecom.ELP.Sortie;

import fr.insalyon.telecom.ELP.Noyau.Constantes; 
import fr.insalyon.telecom.ELP.Noyau.Couleur;
import fr.insalyon.telecom.ELP.Noyau.Pion;  
import fr.insalyon.telecom.ELP.Noyau.Position; 
import fr.insalyon.telecom.ELP.Noyau.EnsembleDePions; 

import javax.swing.JPanel;

import java.awt.GridLayout;
import java.awt.Dimension;

public class Damier extends JPanel {
	
	/** la vue associe au damier*/
	private VueGraphique vue;
	
	/**
	 * Un constructeur du Damier qui prend en parametres le vue associe
	 */
	public Damier(VueGraphique vue) {
		super(new GridLayout(Constantes.N, Constantes.N));
		this.setPreferredSize(new Dimension(500, 500));
		this.vue = vue;
	}
	
	/**
	 * Methode qui dessine le damier avec tous les pions de la configuration.
	 */
	public void desiner(EnsembleDePions e) {
		this.removeAll();
		for(int j = 0; j < Constantes.N; j++) {
    	    for (int i = 0; i < Constantes.N; i++) {
				Position pos = new Position(i,j); 
				if ( pos.estValide() ) {
					Pion pion = e.obtenirPion(pos);
					CaseNoire caseN = new CaseNoire(pion);
					caseN.addMouseListener(new CaseNoireListener(pos, vue));
					this.add(caseN);
				} else {
					this.add(new CaseBlanche());
				} 
    	    } 
    	}
    	this.revalidate();
    	this.repaint();
	}
	
} 
