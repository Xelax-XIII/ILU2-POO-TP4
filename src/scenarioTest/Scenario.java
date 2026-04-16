package scenarioTest;

import personnages.Gaulois;
import produit.Poisson;
import produit.Sanglier;
import villagegaulois.Etal;
import villagegaulois.IEtal;
import villagegaulois.IVillage;

public class Scenario {

	public static void main(String[] args) {

		IVillage village = new IVillage() {
			private IEtal[] marche = new IEtal[3];
			private int nbEtalsOccupes = 0;

			@Override
			public <P extends produit.Produit> boolean installerVendeur(Etal<P> etal, Gaulois vendeur, P[] produit,
					int prix) {
				if (nbEtalsOccupes < marche.length) {
					etal.installerVendeur(vendeur, produit, prix);
					marche[nbEtalsOccupes] = etal;
					nbEtalsOccupes++;
					return true;
				}
				return false;
			}

			@Override
			public void acheterProduit(String produit, int quantiteSouhaitee) {
				int quantiteRestante = quantiteSouhaitee;
				for (int i = 0; i < nbEtalsOccupes && quantiteRestante > 0; i++) {
					int quantiteDisponible = marche[i].contientProduit(produit, quantiteRestante);
					if (quantiteDisponible > 0) {
						int prixPaye = marche[i].acheterProduit(quantiteDisponible);
						System.out.println("A l'étal n° " + (i + 1) + ", j'achète " + quantiteDisponible + " " + produit
								+ "s et je paye " + prixPaye + " sous.");
						quantiteRestante -= quantiteDisponible;
					}
				}
				System.out.println("Je voulais " + quantiteSouhaitee + " " + produit + "s, j'en ai acheté "
						+ (quantiteSouhaitee - quantiteRestante) + ".");
			}

			@Override
			public String toString() {
				StringBuilder resultat = new StringBuilder();
				for (int i = 0; i < nbEtalsOccupes; i++) {
					resultat.append(marche[i].etatEtal());
				}
				return resultat.toString();
			}
		};

		Gaulois ordralfabetix = new Gaulois("Ordralfabétix", 9);
		Gaulois obelix = new Gaulois("Obélix", 20);
		Gaulois asterix = new Gaulois("Astérix", 6);

		Etal<Sanglier> etalSanglierObelix = new Etal<>();
		Etal<Sanglier> etalSanglierAsterix = new Etal<>();
		Etal<Poisson> etalPoisson = new Etal<>();

		Sanglier sanglier1 = new Sanglier(200, obelix);
		Sanglier sanglier2 = new Sanglier(150, obelix);
		Sanglier sanglier3 = new Sanglier(100, asterix);
		Sanglier sanglier4 = new Sanglier(50, asterix);

		Sanglier[] sangliersObelix = { sanglier1, sanglier2 };
		Sanglier[] sangliersAsterix = { sanglier3, sanglier4 };

		Poisson poisson1 = new Poisson("lundi");
		Poisson[] poissons = { poisson1 };

		village.installerVendeur(etalSanglierAsterix, asterix, sangliersAsterix, 10);
		village.installerVendeur(etalSanglierObelix, obelix, sangliersObelix, 8);
		village.installerVendeur(etalPoisson, ordralfabetix, poissons, 5);

		System.out.println(village);

		village.acheterProduit("sanglier", 3);

		System.out.println(village);
	}

}
