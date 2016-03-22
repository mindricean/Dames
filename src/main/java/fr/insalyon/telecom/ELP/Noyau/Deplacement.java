package fr.insalyon.telecom.ELP.Noyau; 

import java.util.ArrayList;  

/**
 * Classe representant le deplacement d'un pion sous forme d'une 
 * liste de positions
 */
public class Deplacement extends ArrayList<Position> {

    /**
     * Constructeur par defaut
     */
    public Deplacement() {
		super();
    }

    /**
     * Constructeur pour un deplacement simple impliquant 
     * deux positions
     * @param depart position de depart
     * @param arrivee position d'arrivee
     */
    public Deplacement(Position depart, Position arrivee) {
		super();
		add(depart); 
		add(arrivee); 
    }

    /**
     * @return chaine representant la liste des positions impliquees
     */
    @Override
    public String toString() {
		String res = "";  
		for (Position p: this) {
			res += p + " "; 
		}
		return res; 
    }
}
