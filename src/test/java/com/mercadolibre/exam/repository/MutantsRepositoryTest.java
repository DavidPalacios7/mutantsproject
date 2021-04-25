package com.mercadolibre.exam.repository;


import com.mercadolibre.exam.model.dna.DnaDB;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MutantsRepositoryTest {

    @Autowired
    private MutantsRepository mutantsRepository;

    @Test
    public void saveDnaSequenceTest() {
        DnaDB dnaDBEntity = new DnaDB("DNATest",false);

        DnaDB dnaDBSaved= mutantsRepository.save(dnaDBEntity);

        Assert.assertEquals(dnaDBEntity,dnaDBSaved);
    }

    @Test
    public void countMutantDnaSequenceTest() {

        long countMutantDNA = mutantsRepository.countDnaDBByIsMutantTrue();

        Assert.assertFalse(countMutantDNA<0);
    }
}
