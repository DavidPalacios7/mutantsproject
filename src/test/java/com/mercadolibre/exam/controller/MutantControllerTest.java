package com.mercadolibre.exam.controller;

import static  org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import com.mercadolibre.exam.service.IMutantsService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class MutantControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IMutantsService iMutantsService;


    @Test
    public void canRetrieveByIdWhenExists() throws Exception {
        // given
        Mockito.when(iMutantsService.isMutant(Mockito.any()))
                .thenReturn(true);

        // when

        MockHttpServletResponse response = mockMvc.perform(
                post("/mutants")
                        .content("{\"dna\":[\"AAAA\",\"AAAA\",\"AAAA\",\"XXXX\"]}")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn().getResponse();

        // then
        Assert.assertEquals(response.getStatus(), HttpStatus.OK.value());
       // Assert.assertNull(response.getContentAsString());
    }

}