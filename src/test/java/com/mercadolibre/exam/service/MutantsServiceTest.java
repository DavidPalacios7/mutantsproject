package com.mercadolibre.exam.service;

import com.mercadolibre.exam.repository.MutantsRepository;
import com.mercadolibre.exam.repository.MutantsRepositoryTest;
import com.mercadolibre.exam.service.IMutantsService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
@RunWith(SpringRunner.class)
public class MutantsServiceTest {

    @Autowired
    private IMutantsService iMutantsService;

    @MockBean
    private MutantsRepository mutantsRepository;


    @Before
    public void setUp() {
        Mockito.when(mutantsRepository.save(Mockito.any())).thenReturn(Mockito.any());
    }


    @Test
    public void dnaSequenceTest() {
        String[] mutantDNASequence = new String[]{"AAAAAA","TTTTTT","AAAAAA","AAAAAA","GGGGGG","CCCCCC"};
        String[] humanDNASequence = new String[]{"XXXXXX","XXXXXX","XXXXXX","XXXXXX","XXXXXX","XXXXXX"};
        String[] noEvalueDNASequence = new String[]{"XX","XX"};


        boolean resultIsMutant = iMutantsService.isMutant(mutantDNASequence);
        boolean resultNoMutant = iMutantsService.isMutant(humanDNASequence);
        boolean resultNoevaluate = iMutantsService.isMutant(noEvalueDNASequence);


        Assert.assertTrue(resultIsMutant);
        Assert.assertFalse(resultNoMutant);
        Assert.assertFalse(resultNoevaluate);



    }

}
