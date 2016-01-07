package algo;

import donnees.Keyboard;

import java.util.ArrayList;
import java.util.List;

public class AlgoRandom extends Algorithme{
    private List<Keyboard> kl;

    @Override
    protected void launch() {
        //on recupere les parametres renseignes dans l'interface graphique
        int taille = (int) this.getParametre("Taille population");
        int iterations = (int) this.getParametre("Itérations");
        
        //on applique l'algo
        double temps = System.currentTimeMillis();
        for(int iter = 0; iter < iterations; iter ++){
            this.kl = new ArrayList<>();
            for(int i = 0; i < taille; i ++){
                kl.add(Keyboard.GenerateFirstSol());
            }

            //on met a jour le meilleur individu (a afficher)
            Keyboard meilleurResultat = this.getResultat();
            double coutMinimal = meilleurResultat.getCost();
            for(int i = 0; i < kl.size(); i ++){
                double cout = kl.get(i).getCost();
                if(cout < coutMinimal){
                    coutMinimal = cout;
                    meilleurResultat = kl.get(i);
                }
            }
            this.updateResultat(meilleurResultat);
        }
        //on actualise les donnees a afficher
        temps = System.currentTimeMillis() - temps;
        this.setDonnee("Duree (ms)", temps);
        
    }

    @Override
    public void configure() {
        //on met les parametres, les donnees et leurs valeurs par defaut
        this.parametres.put("Taille population", 10);
        this.parametres.put("Itérations", 100);
        this.donnees.put("Duree (ms)", 0);
    }

    @Override
    public String getNom() {
        return "Clavier Aléatoire";
    }

}