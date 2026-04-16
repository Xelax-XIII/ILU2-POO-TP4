package produit;

import personnages.Gaulois;

public class Sanglier extends Produit {
	private int poid;
	private Gaulois chasseur;
	
	public Sanglier(int poid, Gaulois chasseur) {
		super("sanglier", Unit.G);
		this.poid = poid;
		this.chasseur = chasseur;
	}
	
	@Override
	public String decrireProduit() {
		return getNom() + " de " + poid + " " + unite + " chasssé par " + chasseur.getNom();
	}
	
	@Override
	public int calculerPrix(int prix) {
		return (poid * prix) / 1000;
	}
}
