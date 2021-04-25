package com.mercadolibre.exam.model.dna;

import com.mercadolibre.exam.model.stats.Stats;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="DnaDB")
public class DnaDB {

    @Id
    private String dna;

    private boolean isMutant;

    public DnaDB(){

    }

    public DnaDB(String dna, boolean isMutant){
        this.dna=dna;
        this.isMutant = isMutant;
    }

    public boolean equals (Object dnaObject){
        if (!(dnaObject instanceof DnaDB)){
            return false;
        }
        DnaDB dnaDB = (DnaDB)dnaObject;

        return this.dna.equals(dnaDB.dna);
    }
}
