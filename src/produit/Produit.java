package produit;

public abstract class Produit implements IProduit {
	private String nom;
	protected Unit unite;
	
	protected Produit(String nom, Unit unite) {
		this.nom = nom;
		this.unite = unite;
	}
	
	@Override
	public String getNom() {
		return nom;
	}
	
	@Override
	public abstract int calculerPrix(int prix);
}
