package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LuigiController.class)
class LuigiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void luigi_shouldReturnCatchPhrase() throws Exception {
        mockMvc.perform(get("/luigi"))
                .andExpect(status().isOk())
                .andExpect(content().string("Luigi time!"));
    }
}
