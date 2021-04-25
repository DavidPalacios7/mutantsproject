package com.mercadolibre.exam.service;

import com.mercadolibre.exam.model.stats.Stats;
import com.mercadolibre.exam.repository.MutantsRepository;
import com.mercadolibre.exam.service.IStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatsServiceImp implements IStatsService {

    @Autowired
    private MutantsRepository mutantsRepository;

    @Override
    public Stats generateStats() {
        long dnaEvaluated = mutantsRepository.count();
        long countDNAMutant= mutantsRepository.countDnaDBByIsMutantTrue();

        return new Stats(countDNAMutant,dnaEvaluated-countDNAMutant,(double) countDNAMutant /dnaEvaluated);
    }
}
