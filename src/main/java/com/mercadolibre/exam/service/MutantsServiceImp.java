package com.mercadolibre.exam.service;

import com.mercadolibre.exam.model.dna.DnaDB;
import com.mercadolibre.exam.repository.MutantsRepository;
import com.mercadolibre.exam.service.IMutantsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Scope(value="prototype", proxyMode= ScopedProxyMode.TARGET_CLASS)
public class MutantsServiceImp implements IMutantsService {

    @Autowired
    private MutantsRepository mutantsRepository;

    private List<String> completeDNASequence = new ArrayList<>();

    @Override
    public boolean isMutant (String[] dna){
        List<String> dnaList = Arrays.asList(dna);
        boolean noMutantSequences = false;
        String dnaSequenceResult = dnaList.stream()
                .map(String::valueOf)
                .collect(Collectors.joining("-"));
        if (dnaList.size()>=4){
            List<String> reverseDNASequence = dnaList.stream().map(elementToReverse -> new StringBuilder(elementToReverse).reverse().toString()).collect(Collectors.toList());
            generateVerticalHorizontalDNASequence(dnaList);
            generateDiagonalDNASequence(dnaList);
            generateDiagonalDNASequence(reverseDNASequence);
            generateMainDiagonalDNASequence(dnaList);
            generateMainDiagonalDNASequence(reverseDNASequence);

            noMutantSequences = evaluateSequence(completeDNASequence)>1;
        }
        
       mutantsRepository.save(new DnaDB(dnaSequenceResult,noMutantSequences));
        return noMutantSequences;
    }



    private long evaluateSequence (List<String> completeDNASequence){

        return completeDNASequence.stream().parallel().filter(dnaSequence -> dnaSequence.contains("AAAA")
                    ||dnaSequence.contains("TTTT")||dnaSequence.contains("CCCC")||dnaSequence.contains("GGGG"))
                .count();
    }

    private void generateVerticalHorizontalDNASequence (List<String> dnaSequenceList){
        this.completeDNASequence.addAll(dnaSequenceList);
        StringBuilder elementVertical = new StringBuilder();

        for (int i=0 ;i<dnaSequenceList.size();i++){
            for (String s : dnaSequenceList) {
                elementVertical.append(s.charAt(i));
            }
            this.completeDNASequence.add(elementVertical.toString());
            elementVertical = new StringBuilder();
        }

    }

    private void generateDiagonalDNASequence (List<String> dnaSequenceList){
        StringBuilder elementDiagSup= new StringBuilder();
        StringBuilder elementDiagiNF= new StringBuilder();

        for (int i=0; i<(dnaSequenceList.size()-4); i++){
            int column=0;
            for (int j=i; j<dnaSequenceList.size()-1;j++){
                elementDiagSup.append(dnaSequenceList.get(column).charAt(j+1));
                elementDiagiNF.append(dnaSequenceList.get(j+1).charAt(column));
                column++;
            }
            this.completeDNASequence.add(elementDiagSup.toString());
            this.completeDNASequence.add(elementDiagiNF.toString());
            elementDiagSup = new StringBuilder();
            elementDiagiNF = new StringBuilder();
        }
    }

    private void generateMainDiagonalDNASequence (List<String> dnaSequenceList){
        StringBuilder elementDiagonal= new StringBuilder();

        for (String dnaElement:dnaSequenceList){
            elementDiagonal.append(dnaElement.charAt(dnaSequenceList.indexOf(dnaElement)));
        }
        this.completeDNASequence.add(elementDiagonal.toString());

    }
}
