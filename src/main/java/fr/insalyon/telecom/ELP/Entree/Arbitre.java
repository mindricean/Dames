package fr.insalyon.telecom.ELP.Entree; 

/**
 * Classe modelisant un arbitre synchronisant les actions
 * des 2 joueurs
 */
public class Arbitre {

    /**
     * Methode avec laquelle un joueur laisse la main, 
     * son thread reveille celui du joueur adverse, 
     * puis se met en attente. 
     */
    public synchronized void laisseLaMain() {
		try {

			notify(); //reveille le joueur adverse
			wait();   //attend que le joueur adverse joue

		} catch (InterruptedException e) {
			System.err.println("ERR: interruption impromptue");
		}
    } 
}
