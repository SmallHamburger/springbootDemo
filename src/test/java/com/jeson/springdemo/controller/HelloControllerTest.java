package com.jeson.springdemo.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HelloControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void allPerson() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/person")).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void findPerson() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/person/10")).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void addPerson() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/person")
                .param("name", "marry")
                .param("age", "25")
        )
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}