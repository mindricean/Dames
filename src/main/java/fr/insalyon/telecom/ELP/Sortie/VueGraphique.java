package fr.insalyon.telecom.ELP.Sortie; 

import fr.insalyon.telecom.ELP.Noyau.EnsembleDePions;
import fr.insalyon.telecom.ELP.Noyau.Position;

import fr.insalyon.telecom.ELP.Entree.LecteurDeDeplacementDeVue;

import java.util.Observer;
import java.util.Observable;

import javax.swing.JFrame;

/**
 * Classe modelisant la vue sur la sortie standard. 
 * Cette classe observe le modele et l'affiche a 
 * l'ecran apres chaque modification.
 */
public class VueGraphique implements Runnable, Observer {
	
	/** le damier associe*/
	private Damier damier = new Damier(this);
	/** le lecteur de vue*/
	private LecteurDeDeplacementDeVue lecteur;
    /** le string correspondant au nom de la fenetre*/
    private String nom;
    
    /**
     * Un constructeur du VueGraphique qui prend en parametres le lecteur
     * de deplacement de vue.
     */
    public VueGraphique(LecteurDeDeplacementDeVue lecteur, String nom) {
		this.lecteur = lecteur;
		this.nom = nom;
	}
    
    /**
     * Methode executee au demarrage d'un thread.  
     */
    @Override
    public void run() {
		JFrame f = new JFrame(nom);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setContentPane(damier);
		f.pack();
		f.setVisible(true);
		
		while (true) {
			//attente d'un appel de methode
		}
    }
    
    /**
     * Methode qui permet de mettre a jour le vue.
     */
    @Override
    public void update(Observable e, Object arg) {
		affichage((EnsembleDePions)e);
	}
	
	/**
	 * Methode qui permet d'ajouter une position dans la liste de deplacements
	 * du lecteur.
	 */
	public void ajouterPosition(Position pos) {
		lecteur.ajouterPosition(pos);
	}
	
	/**
	 * Methode privee qui permet de redesinner le damier.
	 */
	private void affichage(EnsembleDePions e) {
    	damier.desiner(e);
    }
}

