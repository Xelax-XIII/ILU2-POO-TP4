package scenarioTest;

import personnages.Gaulois;
import produit.Poisson;
import produit.Sanglier;
import villagegaulois.Etal;
import villagegaulois.IEtal;

public class ScenarioTest {

	public static void acheterProduit(IEtal[] marche, String produit, int quantiteSouhaitee) {
		int quantiteRestante = quantiteSouhaitee;
		for (int i = 0; i < marche.length && quantiteRestante != 0; i++) {
			IEtal etal = marche[i];
			int quantiteDisponible = etal.contientProduit(produit, quantiteRestante);
			if (quantiteDisponible != 0) {
				int prix = etal.acheterProduit(quantiteDisponible);
				String chaineProduit = accorderNomProduit(produit, quantiteDisponible);
				System.out.println("A l'étal n° " + (i + 1) + ", j'achete " + quantiteDisponible + " " + chaineProduit
						+ " et je paye " + prix + " sous.");
				quantiteRestante -= quantiteDisponible;
			}
		}
		String chaineProduit = accorderNomProduit(produit, quantiteSouhaitee);
		System.out.println("Je voulais " + quantiteSouhaitee + " " + chaineProduit + ", j'en ai acheté "
				+ (quantiteSouhaitee - quantiteRestante) + ".");
	}

	private static String accorderNomProduit(String produit, int quantiteSouhaitee) {
		String chaineProduit = produit;
		if (quantiteSouhaitee > 1) {
			chaineProduit = produit + "s";
		}
		return chaineProduit;
	}

	public static void main(String[] args) {
		Gaulois ordralfabetix = new Gaulois("Ordralfabétix", 9);
		Gaulois obelix = new Gaulois("Obélix", 20);
		Gaulois asterix = new Gaulois("Astérix", 6);
		
		Sanglier sanglier1 = new Sanglier(2000, obelix); 
		Sanglier sanglier2 = new Sanglier(1500, obelix);
		Sanglier sanglier3 = new Sanglier(1000, asterix);
		Sanglier sanglier4 = new Sanglier(500, asterix);
		
		Sanglier[] sangliersObelix = { sanglier1, sanglier2 };
		Sanglier[] sangliersAsterix = { sanglier3, sanglier4 };
		
		Poisson poisson1 = new Poisson("lundi");
		Poisson[] poissons = { poisson1 };

		IEtal[] marche = new IEtal[3];
		Etal<Sanglier> etalSanglierAsterix = new Etal<>();
		Etal<Sanglier> etalSanglierObelix = new Etal<>();
		Etal<Poisson> etalPoisson = new Etal<>();

		marche[0] = etalSanglierAsterix;
		marche[1] = etalSanglierObelix;
		marche[2] = etalPoisson;

		etalSanglierAsterix.installerVendeur(asterix, sangliersAsterix, 10);
		etalSanglierObelix.installerVendeur(obelix, sangliersObelix, 8);
		etalPoisson.installerVendeur(ordralfabetix, poissons, 7);

		System.out.println("--- Marché avant les achats ---");
		for (int i = 0; i < marche.length; i++) {
			System.out.println(marche[i].etatEtal());
		}

		System.out.println("--- Achats ---");
		acheterProduit(marche, "sanglier", 3);
		System.out.println();

		System.out.println("--- Marché après les achats ---");
		for (int i = 0; i < marche.length; i++) {
			System.out.println(marche[i].etatEtal());
		}
	}
}

