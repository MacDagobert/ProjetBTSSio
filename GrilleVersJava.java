
public class Grille {    
    // Déclaration de la grille forme de tableau
    public StringBuilder grille[];
// Declaration du constructeur avec un modele par default ( ici vide)
    public Grille() {
       initGrille(grille0);
    }
// Declaration du constructeur avec un modele defini. Le tableau en argument est envoyé a la methode initGrille
    public Grille(String uneGrille[]) {
        initGrille(uneGrille);
     }
// modele par default utilisé si pas d'autres modele fourni
    public String grille0[] = {
     "+---------------+",
     "|               |",
     "|               |",
     "|               |",
     "|               |",
     "|               |",
     "|               |",
     "|               |",
     "|               |",
     "|               |",
     "|               |",
     "|               |",
     "|               |",
     "+---------------+"
     };

    // Initialisation de la grille , taille correspond au nombres de lignes.
    //Chaque ligne du modele est copié dans ce nouveau tableau
    // Attention faut que le nombre de ligne corresponde a grille0 , il faut une corrélation entre  les grilles. Sinon source erreur
    public void initGrille(String g[]) {
        grille = new StringBuilder[14];
        for (int i = 0; i < g.length; i++) 
            grille[i] = new StringBuilder(g[i]);
    }
// reinitialise la grille
    public void reinitGrille() {
        initGrille(grille0);
    }
// remplace un caractere
    public void remplacerCar(int y, int x, char c) {
        grille[y].setCharAt(x, c);
    }
// Affiche la Grille
    public void affiche() {
        for (int i=0; i<grille.length; i++)
            System.out.println(grille[i]);    
    }
// Compare deux grilles
    public Boolean comparer(Grille g2) {
        for (int i=0; i<grille.length; i++)
            if ((grille[i]).compareTo(g2.grille[i])!=0) return false;
        return true;
    }
    public static void main(String[] args) {
        Grille g = new Grille();
        g.affiche();
        g.remplacerCar(3,4,'#');
        g.affiche();
        g.reinitGrille();
        g.affiche();
    
        String grilleTest[] = {
            "+---------------+",
            "|               |",
            "|               |",
            "|               |",
            "|      #        |",
            "|          #    |",
            "|               |",
            "|               |",
            "|               |",
            "|               |",
            "|               |",
            "|               |",
            "|               |",
            "+---------------+"
            };

    Grille g2= new Grille(grilleTest);
    g.remplacerCar(4,7,'#');
    g.remplacerCar(5,11,'#');
    g.affiche();
    Boolean eq = g.comparer(g2);
    System.out.println(eq);    
    }
}