package com.mercadolibre.exam.model.stats;

public class Stats {
    public long count_mutant_dna;
    public long count_human_dna;
    public double ratio;

    public Stats (long count_mutant_dna, long count_human_dna, double ratio){
        this.count_mutant_dna = count_mutant_dna;
        this.count_human_dna = count_human_dna;
        this.ratio = ratio;
    }

    public boolean equals (Object statsObject){
        if (!(statsObject instanceof Stats)){
            return false;
        }
        Stats stats = (Stats)statsObject;

        return this.count_human_dna == stats.count_human_dna
                && this.count_mutant_dna == stats.count_mutant_dna
                && this.ratio == stats.ratio;
    }
}
