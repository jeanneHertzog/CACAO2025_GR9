//Nils
package abstraction.eq5Transformateur2;

import abstraction.eqXRomu.filiere.Filiere;
import abstraction.eqXRomu.produits.Chocolat;
import abstraction.eqXRomu.produits.Feve;


public class ProcessChoco extends FraisAditionnel {

    protected double proportionFeve;
    protected double coutIngredientSecondaire;
    protected double coutAditionnelParTablette;


    public ProcessChoco(){
        super();
        this.proportionFeve=100;
        this.coutIngredientSecondaire=450; // euro par tonne de chocolat produite
        this.coutAditionnelParTablette=0.30; // euro par tablette produite

    }

    protected double getcoutAditionnelParTablette(){
        return this.coutAditionnelParTablette;
    }
    
    public void fabriquerChocolat(Chocolat chocolat,Feve feve, double quantite) {
        if (quantite >0) { 
            this.ajouterStock(this, chocolat, quantite*10000, super.cryptogramme);    // *10000 pour passer de tonne à unité de tablette de chocolat
            this.retirerStock(this, feve, quantite, super.cryptogramme);
            Filiere.LA_FILIERE.getBanque().payerCout(this, super.cryptogramme,"Achat d'ingrédient secondaire",quantite*this.coutIngredientSecondaire);
            Filiere.LA_FILIERE.getBanque().payerCout(this, super.cryptogramme,"Frais de production",quantite*10000*this.coutAditionnelParTablette);
        }
    }

    

    public void decisionProcessChoco(){
        if (super.getQuantiteStock(Feve.F_MQ)>10){
            this.fabriquerChocolat(Chocolat.C_MQ,Feve.F_MQ,10);
            super.journal.ajouter("fabrication de 10 tonnes de chocolat de qualité moyenne");
        }
        
    }


    public void next(){
        decisionProcessChoco();
        super.next();
    }
}
