package produit;

public class Poisson extends Produit {
	private String date;
	
	public Poisson(String date) {
		super("poisson", Unit.PIECE);
		this.date = date;
	}
	
	@Override
	public String decrireProduit() {
		return getNom() + " pêchés " + date;
	}
	
	@Override
	public int calculerPrix(int prix) {
		return prix;
	}

}
