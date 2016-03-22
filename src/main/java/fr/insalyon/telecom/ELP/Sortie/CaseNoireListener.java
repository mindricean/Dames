package fr.insalyon.telecom.ELP.Sortie;

import fr.insalyon.telecom.ELP.Noyau.Position;

import java.awt.event.MouseListener; 
import java.awt.event.MouseEvent; 

public class CaseNoireListener implements MouseListener {
	
	/** la position de la case*/
	private Position pos;
	/** la vue associe au listener*/
	private VueGraphique vue;
	
	/**
	 * Un constructeur de la CaseNoireListener. 
	 */
	public CaseNoireListener(Position position, VueGraphique vue) {
		pos = position;
		this.vue = vue;
	}
	
    @Override 
    public void mouseClicked(MouseEvent e) {
		vue.ajouterPosition(pos);
    }
    
    @Override 
    public void mouseEntered(MouseEvent e) {}
    
    @Override 
    public void mouseExited(MouseEvent e) {}
    
    @Override 
    public void mousePressed(MouseEvent e) {}
    
    @Override 
    public void mouseReleased(MouseEvent e) {}
}
