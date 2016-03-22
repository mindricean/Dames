package fr.insalyon.telecom.ELP.Noyau; 

import java.util.ArrayList; 
import java.util.Collection;  

/**
 * Classe modelisant une position sur un damier 
 * (du jeu de dames international). Elle est 
 * representee par un couple de coordonnees 
 * entieres. 
 * 
 */
public final class Position {

    /** abscisse */
    private final int i;
    /** ordonnee */
    private final int j;

    /**
     * Constructeur
     * @param i abscisse de la position
     * @param j ordonnee de la position
     */
    public Position(int i, int j) {
		this.i = i; 
		this.j = j; 
    }

    /**
     * Constructeur 
     * @param chaine chaine contenant la 
     * representation textuelle de la position 
     * (les coordonnees sont separees par une virgule)
     */
    public Position(String chaine) {
		String[] indices = chaine.split(","); 
		this.i = Integer.parseInt(indices[0]); 
		this.j = Integer.parseInt(indices[1]); 
    }

    /**
     * Acces en lecture seule a l'abscisse
     * @return i
     */
    public int i() {
    	return i; 
    }

    /**
     * Acces en lecture seule a l'ordonnee
     * @return j
     */
    public int j() {
    	return j; 
    }

    /**
     * Indique si la position est impaire
     * (les coordonnees ne sont ni toutes deux paires
     * ni toutes deux impaires) 
     * @return vrai si impaire, faux sinon
     */
    public boolean estImpaire() {
		return (((i+j)%2) == 1); 
    }

    /**
     * Indique si la position se trouve dans les bornes
     * du damier.  
     * @return vrai si dedans, faux sinon
     */
    public boolean estDedans() {
		return ( (i >= 0)&&(i < Constantes.N)&&(j >= 0)&&(j < Constantes.N) ); 
    }

    /**
     * Indique si la position est valide  
     * (c'est-a-dire est dedans et est impaire, 
     * ce qui correspond a une case noire du damier)
     * @return vrai si valide, faux sinon
     */    
    public boolean estValide() {
		return ( estDedans() && estImpaire() ); 
    }

    /**
     * Methode comparant la position courante avec une autre. 
     * NB. on ne considere que les positions du damier, de sorte
     * que toutes les autres positions (invalides) sont considérées
     * egales. 
     * @param obj une autre position
     * @return vrai si les deux positions dans les bornes du damier
     * ont meme coordonnees, ou si les deux positions sont hors des 
     * bornes du damier, faux sinon
     */
    @Override 
    public boolean equals(Object obj) {
		if (obj == null)
			return false; 
		else {
			Position autrePosition = (Position) obj; 
			if (estDedans()) {
				return ( (i == autrePosition.i)&&(j == autrePosition.j) ); 
			} else {
				return (!autrePosition.estDedans());  
			}
		}
    }

    /**
     * Methode retournant le hash code de la position
     * @return hash code  
     * NB. on ne considere que les positions du damier, de sorte
     * que toutes les autres positions sont considérées
     * égales avec le meme hash code. Deux positions egales ont
     * meme hash code. 
     */
    @Override 
    public int hashCode() {
		if (estDedans()) 
			return i*Constantes.N + j; 
		else
			return Constantes.N*Constantes.N; 
    }

    /**
     * Methode renvoyant la representation textuelle de la position
     * @return les coordonnees separees par une virgule
     */
    @Override
    public String toString() {
		return i+","+j; 
    }

    /**
     * Methode renvoyant la liste des positions intermediaires entre 
     * deux positions valides se trouvant sur une meme diagonale. 
     * @param a premiere position
     * @param b seconde position
     * @return liste des positions intermediaires (sans a, ni b); 
     */
    public static ArrayList<Position> diagonale(Position a, Position b) {
		//liste
		ArrayList<Position> liste = new ArrayList<Position>();
		//si les positions sont valides et distinctes
		if (a.estValide() && b.estValide() && (!a.equals(b))) {
			//coordonnees de a
			int i = a.i; 
			int j = a.j; 
			//coordonnees du vecteur allant de a vers b
			int di = (b.i - i);   
			int dj = (b.j - j); 
			//si les deux positions sont en diagonale
			if (Math.abs(di) == Math.abs(dj)) {
				//normalisation
				di /= Math.abs(di);
				dj /= Math.abs(dj); 
				while ( (i != b.i) && (j !=  b.j) ) {
					//avancer
					i += di; 
					j += dj; 
					//ajouter la position
					liste.add(new Position(i,j));
				}
				//supprimer la derniere position egale a b
				liste.remove(liste.size()-1);
			}
		}
		return liste; 
    }
    
    /**
     * Methode renvoyant si deux positions se trouvent sur une diagonale. 
     * @param a premiere position
     * @param b seconde position
     * @return boolean qui correspond au reponse
     */
	public static boolean estDiagonale(Position a, Position b) {
		//si les positions sont valides et distinctes
		if (a.estValide() && b.estValide() && (!a.equals(b))) {
			//coordonnees de a
			int i = a.i; 
			int j = a.j; 
			//coordonnees du vecteur allant de a vers b
			int di = (b.i - i);   
			int dj = (b.j - j); 
			//si les deux positions sont en diagonale
			return (Math.abs(di) == Math.abs(dj));
		} else {
			return false;
		}
	}
    
}
