package fr.insalyon.telecom.ELP.Sortie;

import fr.insalyon.telecom.ELP.Noyau.Pion;
import fr.insalyon.telecom.ELP.Noyau.Dame;
import fr.insalyon.telecom.ELP.Noyau.Couleur;

import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Color;

public class CaseNoire extends JPanel {
	
	/** le pion associe a cette case noire*/
	private Pion pion;
	
	/**
	 * Un constructeur qui prend en parametres le pion qui se trouve
	 * dans la case respective.
	 */ 
	public CaseNoire(Pion p) {
		super();
		pion = p;
		this.setBackground(Color.GRAY);
	}
	
	/**
	 * Methode qui permet de dessiner la case noire avec le pion ou
	 * la dame.
	 */ 
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(pion != null) {
			if(pion.aMemeCouleur(Couleur.noir())) {
				g.setColor(Color.BLACK);
			} else {
				g.setColor(Color.WHITE);
			}
			g.fillOval(0, 0, this.getSize().width, this.getSize().height);
			if(pion instanceof Dame) {
				g.setColor(Color.RED);
				g.fillOval(this.getSize().width/4, this.getSize().height/4, this.getSize().width/2, this.getSize().height/2);
			}
		}
	}
} 
