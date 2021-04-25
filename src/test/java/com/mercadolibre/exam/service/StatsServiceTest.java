package com.mercadolibre.exam.service;


import com.mercadolibre.exam.model.stats.Stats;
import com.mercadolibre.exam.repository.MutantsRepository;
import com.mercadolibre.exam.service.IStatsService;
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
public class StatsServiceTest {

    @Autowired
    private IStatsService iStatsService;

    @MockBean
    private MutantsRepository mutantsRepository;


    @Before
    public void setUp() {
        Mockito.when(mutantsRepository.count()).thenReturn(30L);
        Mockito.when(mutantsRepository.countDnaDBByIsMutantTrue()).thenReturn(15L);

    }


    @Test
    public void generateStatsTest() {

        Stats statsExpected = new Stats(15,15,0.5);

        Stats statsResult = iStatsService.generateStats();


        Assert.assertNotNull(statsResult);
        Assert.assertEquals(statsExpected,statsResult);

    }

}
