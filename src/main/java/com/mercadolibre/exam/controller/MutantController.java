package com.mercadolibre.exam.controller;


import com.mercadolibre.exam.model.stats.Stats;
import com.mercadolibre.exam.model.dna.Dna;
import com.mercadolibre.exam.service.IMutantsService;
import com.mercadolibre.exam.service.IStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
public class MutantController {

    @Autowired
    private IMutantsService iMutantsService;
    @Autowired
    private IStatsService iStatsService;

    @PostMapping("/mutants")
    public ResponseEntity<HttpStatus> evaluateDnaSequence (@RequestBody Dna dna){

        return iMutantsService.isMutant(dna.getDna())?new ResponseEntity<>(HttpStatus.OK): new  ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @GetMapping("/stats")
    public Stats evaluationsStats (){
        return iStatsService.generateStats();
    }
}
