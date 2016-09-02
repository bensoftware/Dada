package com.dada.ga.models;

/**
 * Created by ben on 03/04/16.
 */
public class SchoolFormation {

    private SchoolItem schoolite;
    private Formation formation;

    public SchoolFormation() {
    }


    public SchoolFormation(SchoolItem schoolite, Formation formation) {
        this.schoolite = schoolite;
        this.formation = formation;
    }

    public SchoolItem getSchoolite() {
        return schoolite;
    }

    public void setSchoolite(SchoolItem schoolite) {
        this.schoolite = schoolite;
    }

    public Formation getFormation() {
        return formation;
    }

    public void setFormation(Formation formation) {
        this.formation = formation;
    }
}
