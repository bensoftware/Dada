package com.dada.ga.models;

import com.dada.ga.R;

/**
 * Created by steeve on 3/20/16.
 */
public enum EnumCategory {

    PREPRIMAIRE(1, "Préprimaire", R.drawable.img3),
    PRIMAIRE(2, "Primaire", R.drawable.img1),
    SECONDAIRE(3, "Secondaire", R.drawable.img2),
    SUPERIEURS(4, "Superieur", R.drawable.img4),
    EXAMENCONCOUR(5, "Examens/Concours", R.drawable.img5),
    BOURSESTECHNIQUES(6, "Bourse/Techniques", R.drawable.img7),
    BOURSESSUPERIEURS(7, "Bourse/Superieurs", R.drawable.img9),
    UNIVERSITE(8, "Université", R.drawable.img4),
    LYCEE(9, "Lycée", R.drawable.img8),
    MOTEUR_RECHERCHE(10,"Recherche", R.drawable.img6);


    int order;
    String libelle;
    int resource;

    private EnumCategory(int i, String libelle, int resource) {
        this.order = i;
        this.libelle = libelle;
        this.resource = resource;
    }

    public int getOrder() {
        return order;
    }

    public String getLibelle() {
        return libelle;
    }

    public int getResource() {
        return resource;
    }
}
