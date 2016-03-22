package fr.insalyon.telecom.ELP.Noyau; 

import java.util.Collection; 
import java.util.Map; 
import java.util.HashMap; 
import java.util.Observable;
import java.util.ArrayList;

/**
 * Classe modelisant un ensemble de pions, 
 * c'est-a-dire une configuration de pions sur le 
 * damier. 
 */
public class EnsembleDePions extends Observable {

    /** tableau associant pion et position */
    private Map<Position, Pion> mesPions 
	= new HashMap<Position, Pion>();

    //-------------------- initialisation ---------------------------
    /**
     * Methode qui positionne les pions d'une couleur donnee. 
     * @param uneCouleur couleur des pions a positionner
     * @param unFluxDePositions chaine traduisant une liste des positions
     */
    private void init(Couleur uneCouleur, String unFluxDePositions) {
		String[] chaines = unFluxDePositions.split(" "); //separation par espace
		for (String chaine: chaines) {
			Position position = new Position(chaine);
			ajouterPion( new Pion(uneCouleur), position ); 
		}
    }

    /**
     * Methode qui positionne tous les pions.
     * @param pionsNoirs liste des positions des pions noirs
     * @param pionsBlancs liste des positions des pions blancs
     */
    public void init(String pionsNoirs, String pionsBlancs) {
		init(Couleur.noir(), pionsNoirs); 
		init(Couleur.blanc(), pionsBlancs); 
		reafficher();
    }

    /**
     * Methode qui positionne tous les pions dans la configuration
     * initiale. 
     */
    public void init() {
		init("1,0 3,0 5,0 7,0 9,0 0,1 2,1 4,1 6,1 8,1 1,2 3,2 5,2 7,2 9,2 0,3 2,3 4,3 6,3 8,3", 
			 "1,6 3,6 5,6 7,6 9,6 0,7 2,7 4,7 6,7 8,7 1,8 3,8 5,8 7,8 9,8 0,9 2,9 4,9 6,9 8,9"); 
    }


    //-------------------- services ---------------------------

    /**
     * Methode permettant d'obtenir le pion situe a une position donnee
     * @param unePosition position
     * @return null si aucun pion ne s'y trouve, le pion sinon
     */
    public Pion obtenirPion(Position unePosition) {
		return mesPions.get(unePosition); 	
    }

    /**
     * Methode calculant le nombre de pions d'une couleur donnee
     * @return le nombre de pions
     */
    public int nombrePions(Couleur uneCouleur) {
		Collection<Pion> pions = mesPions.values();
		int nb = 0;
		for (Pion p: pions) {
			if (p.aMemeCouleur(uneCouleur))
			nb++; 
		}
		return nb; 
    }

    /**
     * Methode indiquant si une couleur n'est plus représentée, 
     * c'est-a-dire que la partie est terminee
     * @return vrai si la partie est terminee, faux sinon
     */
    public boolean estTermine(Couleur couleurJoueur) {
		if (nombrePions(Couleur.noir()) == 0) {
			System.out.println("Le joueur blanc a gagné");
			return true;
		}
		if (nombrePions(Couleur.blanc()) == 0) {
			System.out.println("Le joueur noir a gagné");
			return true;
		}
		if (deplacementMax(couleurJoueur) <= 1) {
			if (Couleur.noir().equals(couleurJoueur)) {
				System.out.println("Le joueur blanc a gagné");
			} else {
				System.out.println("Le joueur noir a gagné");
			}
			return true;
		}
		return false;
    }


    /**
     * Methode indiquant si un deplacement donne est valide ou non. 
     * @param unDeplacement deplacement a tester 
     * @param couleurJoueur couleur du joueur realisant le deplacement
     * @return vrai si valide, faux sinon
     */
    private boolean estValide(Deplacement unDeplacement, Couleur couleurJoueur) {
		boolean estOK = true; 

		//toutes les positions
		Position[] positions = unDeplacement.toArray(new Position[0]);

		//on teste s'il y a au moins deux positions
		estOK = estOK && (positions.length >= 2); 

		if (estOK) {
	
			//on teste si toutes les positions sont valides
			for (int i = 0; ( (i < positions.length)&&(estOK) ); i++) {
				estOK = estOK && (positions[i].estValide()); 
			}
			if(!estOK) return false;

			//position de depart
			Position depart = positions[0];

			//on teste s'il y a un pion de la couleur du joueur
			Pion aDeplacer = obtenirPion(depart); 
			estOK = estOK && (aDeplacer != null); 
			estOK = estOK && (aDeplacer.aMemeCouleur(couleurJoueur)); 
			
			//on teste que les cases sont libres		
			for (int i = 1; ( (i < positions.length)&&(estOK) ); i++) {
				estOK = estOK && (obtenirPion(positions[i]) == null); 
			}
			if(!estOK) return false;
			
			ArrayList<Position> pionsSuppr = new ArrayList<Position>();
			ArrayList<Pion> piecesSuppr = new ArrayList<Pion>();
			for (int i = 1; ( (i < positions.length)&&(estOK) ); i++) {
				estOK = estOK && Position.estDiagonale(positions[i-1], positions[i]);
				if(!estOK) break;
				
				Collection<Position> c = Position.diagonale(positions[i-1], positions[i]); 
				if (c.size() == 1) {
					if(!(aDeplacer instanceof Dame)) {
						for (Position pos: c) {
							Pion aSupprimer = obtenirPion(pos); 
							estOK = estOK && ( (aSupprimer != null) && (!aSupprimer.aMemeCouleur(couleurJoueur)) );
							if (estOK) {
								pionsSuppr.add(pos);
								piecesSuppr.add(aSupprimer);
								supprimerPion(pos);
							}
						}
					} else {
						int nbPionsSupprimes = 0;
						for (Position pos: c) {
							Pion aSupprimer = obtenirPion(pos); 
							if(aSupprimer != null) {
								estOK = estOK && (!aSupprimer.aMemeCouleur(couleurJoueur));
							}
							if (estOK && (aSupprimer != null)) {
								nbPionsSupprimes++;
								pionsSuppr.add(pos);
								piecesSuppr.add(aSupprimer);
								supprimerPion(pos);
							}
						}
						if(nbPionsSupprimes == 0) {
							estOK = estOK && (positions.length == 2);
						} else {
							estOK = estOK && (nbPionsSupprimes == 1);
						}
					}
				}
				if (c.size() > 1) {
					//c'est valide que pour une dame
					if(!(aDeplacer instanceof Dame)) {
						estOK = false;
					} else {
						//est une dame
						int nbPionsSupprimes = 0;
						for (Position pos: c) {
							Pion aSupprimer = obtenirPion(pos); 
							if(aSupprimer != null) {
								estOK = estOK && (!aSupprimer.aMemeCouleur(couleurJoueur));
							}
							if (estOK && (aSupprimer != null)) {
								nbPionsSupprimes++;
								pionsSuppr.add(pos);
								piecesSuppr.add(aSupprimer);
								supprimerPion(pos);
							}
						}
						if(nbPionsSupprimes == 0) {
							estOK = estOK && (positions.length == 2);
						} else {
							estOK = estOK && (nbPionsSupprimes == 1);
						}
					}
				}
				if (c.size() == 0) {
					estOK = estOK && (positions.length == 2);
					if(!(aDeplacer instanceof Dame)) {
						if (couleurJoueur.equals(Couleur.noir())) {
							estOK = estOK && (positions[i].j() - positions[i-1].j() > 0);
						} else {
							estOK = estOK && (positions[i].j() - positions[i-1].j() < 0);
						}
					}
				}
			}
			for (int i=0; i<pionsSuppr.size(); i++) {
				ajouterPion(piecesSuppr.get(i), pionsSuppr.get(i));
			}
		}

		return estOK; 
    }

    /**
     * Methode qui realise un deplacement donne, ce qui modifie la 
     * configuration des pions.  
     * @param unDeplacement deplacement a realiser 
     * @param couleurJoueur couleur du joueur realisant le deplacement
     * @return vrai si le deplacement est valide, faux sinon
     */
    public boolean realiser(Deplacement unDeplacement, Couleur couleurJoueur) {
	
		if (estValide(unDeplacement, couleurJoueur) && 
		!(!estPrise(unDeplacement, couleurJoueur) && existePrise(couleurJoueur)) ) { //prise obligatoire

			//toutes les positions
			Position[] positions = unDeplacement.toArray(new Position[0]); 
			//position de depart
			Position posDep = positions[0]; 

			//pion de la position de posDep
			Pion pionDep = obtenirPion(posDep);
			//pionDep = mesPions.get(posDep); 

			//supprimer le pion de la position de depart
			supprimerPion(positions[0]); 
	
			//ajouter le pion a la position d'arrivee
			ajouterPion(pionDep, positions[positions.length-1]); 

			//traiter les positions intermediaires
			for (int i = 1; i < positions.length; i++) {
				Collection<Position> c = Position.diagonale(positions[i-1], positions[i]); 
				if (c.size() >= 1) {
					for (Position pos: c) {
						Pion aSupprimer = obtenirPion(pos); 
						if ( (aSupprimer != null) && (!aSupprimer.aMemeCouleur(couleurJoueur)) ) {
							supprimerPion(pos);  
						}
					}
				}
			}
			
			//promotion en dame
			for(int i = 0; i < Constantes.N; i++) {
				Position pos = new Position(i, 0);
				if(pos.estValide()) {
					Pion pion = obtenirPion(pos);
					if(pion != null && pion.aMemeCouleur(Couleur.blanc())) {
						supprimerPion(pos);
						ajouterPion(new Dame(Couleur.blanc()), pos);
					}
				}
				pos = new Position(i, Constantes.N - 1);
				if(pos.estValide()) {
					Pion pion = obtenirPion(pos);
					if(pion != null && pion.aMemeCouleur(Couleur.noir())) {
						supprimerPion(pos);
						ajouterPion(new Dame(Couleur.noir()), pos);
					}
				}
			}
			reafficher();
			return true; 
		} else {
			System.err.println("Deplacement invalide");
			return false; 
		}
	   
    }
    
    /**
     * Methode qui calcule le deplacement le plus long.
     * Utilisee pour lire un deplacement complet du lecteur.
     * @param couleurJoueur la couleur du joueur courant
     * @return la taille maximale du deplacement existant
     */
    public int deplacementMax(Couleur couleurJoueur) {
		int max = 0;
		Deplacement deplacementCourant;
		for(int i = 0; i < Constantes.N; i++) {
			for(int j = 0; j < Constantes.N; j++) {
				Position pos = new Position(i, j);
				if((obtenirPion(pos) != null) && obtenirPion(pos).aMemeCouleur(couleurJoueur)) {
					deplacementCourant = new Deplacement();
					deplacementCourant.add(pos);
					int maxCourant = backtrack(deplacementCourant, couleurJoueur);
					if(maxCourant > max) {
						max = maxCourant;
					}
				}
			}
		}
		System.out.println("DeplMax: " + max);
		return max;
	}
	
	

    //--------------------- Operations internes ----------------------------
	/**
	 * Methode qui indique si un deplacement correspond a une prise
	 * PRECONDITION: on suppose que le deplacement est valide
	 * @param unDeplacement le deplacement a etudier
	 * @param couleurJoeur la couleur du joueur courant
	 * @return boolean qui indique si le deplacement est une prise
	 */
	private boolean estPrise(Deplacement unDeplacement, Couleur couleurJoueur) {
		Position[] positions = unDeplacement.toArray(new Position[0]); 
		if(positions.length > 2) {
			return true;
		}
		Collection<Position> c = Position.diagonale(positions[0], positions[1]); 
		if (c.size() >= 1) {
			Pion aDeplacer = obtenirPion(positions[0]);
			if(!(aDeplacer instanceof Dame)) {
				return true;
			} else {
				for (Position pos: c) {
					if(obtenirPion(pos) != null) {
						return true;
					}
				}
				return false;
			}
		}
		return false;
	}
	
	/**
	 * Methode qui indique s'il existe une prise pour le joueur courant
	 * @param couleurJoueur la couleur du joueur courant
	 * @return la possibilite ou non d'une prise
	 */
	private boolean existePrise(Couleur couleurJoueur) {
		if (deplacementMax(couleurJoueur) > 2) {
			return true;
		}
		Deplacement deplacementCourant;
		for(int i = 0; i < Constantes.N; i++) {
			for(int j = 0; j < Constantes.N; j++) {
				Position pos = new Position(i, j);
				if((obtenirPion(pos) != null) && obtenirPion(pos).aMemeCouleur(couleurJoueur)) {
					deplacementCourant = new Deplacement();
					deplacementCourant.add(pos);
					for(int ii = 0; ii < Constantes.N; ii++) {
						for(int jj = 0; jj < Constantes.N; jj++) {
							deplacementCourant.add(new Position(ii, jj));
							if(estValide(deplacementCourant, couleurJoueur) && estPrise(deplacementCourant, couleurJoueur)) {
								return true;
							} else {
								deplacementCourant.remove(1);
							}
						}
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * Methode qui realise un backtracking pour determiner la longueur du deplacement maximal.
	 * @param depl le deplacement courant
	 * @param couleurJoueur la couleur du joueur courant
	 * @return la taille du deplacement maximal pour un pion donne.
	 */
	private int backtrack(Deplacement depl, Couleur couleurJoueur) {
		int maxBack = depl.size();
		for(int i = 0; i < Constantes.N; i++) {
			for(int j = 0; j < Constantes.N; j++) {
				depl.add(new Position(i,j));
				if(estValide(depl, couleurJoueur)) {
					int val = backtrack(depl, couleurJoueur);
					if(val > maxBack) {
						maxBack = val;
					}
				}
				depl.remove(depl.size() - 1);
			}
		}
		return maxBack;
	}
	
	/**
	 * Methode qui permet de reafficher la vue avec la nouvelle configuration.
	 */
	private void reafficher() {
		this.setChanged();
		this.notifyObservers();
	}

    /**
     * Methode qui supprime le pion situe a une position donnee
     * @param unePosition position du pion a supprimer
     */
    private void supprimerPion(Position unePosition) {
		mesPions.remove(unePosition); 
    }

    /**
     * Methode qui ajoute un pion donne a une position donnee
     * @param unPion pion a ajouter 
     * @param unePosition position du pion a ajouter
     */
    private void ajouterPion(Pion unPion, Position unePosition) {
		mesPions.put(unePosition, unPion); 
    }


}
