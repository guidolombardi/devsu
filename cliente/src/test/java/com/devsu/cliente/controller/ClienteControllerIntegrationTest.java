package com.devsu.cliente.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class ClienteControllerIntegrationTest {

	@Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        // Configuración adicional antes de cada prueba, si es necesario
    }

    @Test
    void testGetClienteById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/clientes/{id}", 1))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nombre").value("PruebaCliente"));
    }
	
}
