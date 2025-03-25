package abstraction.eq6Transformateur3;

import java.util.LinkedList;
import java.util.List;

import abstraction.eqXRomu.bourseCacao.IAcheteurBourse;
//de quoi hérite notre classe ? aucun héritage mais une interface : IActeur 
//on importe cette interface : 
import abstraction.eqXRomu.general.Variable;
import abstraction.eqXRomu.produits.Feve;


// @author Eric SCHILTZ

//notre objectif est donc de créer un fils des programmes crées précédemment
//le fait de faire des achats en bourse sera une méthode
//le but étant que la suite hérite de nous et de nos méthodes et donc puisse faire des achats en bourse
//on va aller chercher parfois si besoin dans le code fait précédemment Père si besoin
//Mais globalement il faut recommencer à 0
//Notre entreprise finale sera une instance de notre classe. 
//On va s'inspirer de ce qu'a fait Henri
//on doit pouvoir respecter la nomenclature commune et donc correspondre à l'interface IAcheteur
public class Transformateur3AcheteurBoursef extends Transformateur3ContratCadreAcheteur implements IAcheteurBourse{
    //il faut voir le pb comme si on était la classe principale de notre boîte, la dernière. 
    //il faut que l'instance qu'elle créée soit notre entreprise
    //des attributs
    //il faut que les attributs de notre instance que l'on rajoute soit donc la liste des achats en 
    //bourses faits :soit une liste avec les achats en bourse fait : liste de fèves et liste de stocks
    //voulus
    //et une liste avec les achats en bourse courants :liste de fèves et liste de stocks
    //voulus
	protected List<Variable> StockFevevouluencours;
	protected List<Feve> Fevevoulueencours;
    protected List<Variable> Stockvoulufaits;
    protected List<Feve> Fevevouluefaites;
    //des constructeurs
    //notre contrainte et que l'on veut rien en variable 
    public Transformateur3AcheteurBoursef(){
        this.StockFevevouluencours = new LinkedList<Variable>();
        this.Fevevoulueencours = new LinkedList<Feve>();
        this.Stockvoulufaits = new LinkedList<Variable>();
        this.Fevevouluefaites = new LinkedList<Feve>();
    }

    //des méthodes

    //méthode next. Réaliser à chaque step. 
    //à chaque next on doit essayer d'acheter en bourse 
    public void next() {
        //on va parcourir toutes les fèves que l'on veut récupérer le cours correspondant grâce à 
        //une méthode et grâce à double demande connaître la quantité de fèves que l'on désire acheter 
        //pour ce type de fève à ce next
        //on va après l'acheter
        //Dans Fève, les 6 premières lognes peuvent être vues comme des instances 'de référence'
        //de la classe. Des instances 'statiques' de la classe auquelles tout le monde peut accéder
        //on s'assure d'avoir bien BourseCacao
        //on récupère les cours de toutes les fèves
        //List<Variable> a = BourseCacao.getIndicateurs();
        //pour l'instant on va ne faire que pour les fèves basses qualités
        //fève BQ de nom java : Feve.F_BQ
        //fève BQ équitable     
        //fève MQ
        //fève HQ équitable 
        // tout cela c'est la filière le code mère qui va s'en occuper ce n'est pas notre rôle
        super.next();
    }

    //méthodes pour correspondre à l'interface IAcheteur

    /**
	 * Retourne la quantite en tonnes de feves de type f desiree par l'acheteur 
	 * sachant que le cours actuel de la feve f est cours
	 * @param f le type de feve
	 * @param cours le cours actuel des feves de type f
	 * @return la quantite en tonnes de feves de type f desiree 
	 */
    @Override
    public double demande(Feve f, double cours) {
        //pour l'instant simple qu'importe le type de fève ou le cours en cours
        //on commande une quantité de 1000
        //le but est juste que le code fonctionne pour l'instant
        return 1000;

    }

    /**
	 * Methode appelee par la bourse pour avertir l'acheteur qu'il vient d'acheter
	 * quantiteEnT tonnes de feve f au prix de  coursEnEuroParT euros par tonne.
	 * L'acteur this doit augmenter son stock de feves de type f de la 
	 * quantite quantiteEnT.
	 * Lorsque cette methode est appelee la transaction bancaire a eu lieu (l'acheteur
	 * n'a pas a s'occuper du paiement qui a deja ete effectue)
	 */
    @Override
    public void notificationAchat(Feve f, double quantiteEnT, double coursEnEuroParT) {
        //avertir l'acheteur qu'il vient d'acheter quantiteEnT tonnes de feve f 
        //au prix de  coursEnEuroParT euros par tonne.
        journalBourse.ajouter("vous venez d'acheter de la fève"+f+"au prix de"+coursEnEuroParT+"par tonne");
        //L'acteur this doit augmenter son stock de feves de type f de la quantite quantiteEnT.
        super.stockFeves.addToStock(f,quantiteEnT);
    }

    /**
	 * Methode appelee par la bourse pour avertir l'acheteur qu'il vient 
	 * d'etre ajoute a la black list : l'acteur a passe une commande en bourse
	 * qu'il n'a pas pu honorer du fait d'un compte en banque trop faible. 
	 * this ne pourra pas acheter en bourse pendant la duree precisee en 
	 * parametre (toute commande sera ignoree prendant les prochaines duree etapes)
	 */
    @Override
    public void notificationBlackList(int dureeEnStep) {
        //pour l'instant pas d'autres réactions
        journalBourse.ajouter("on est sur la Blacklist on ne peut plus acheter en Bourse pendant"+dureeEnStep);
    }
}
