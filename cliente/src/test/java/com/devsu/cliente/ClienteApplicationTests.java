package com.devsu.cliente;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.devsu.cliente.model.Cliente;
import com.devsu.cliente.repository.ClienteRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ClienteApplicationTests {
	
	@Autowired
    private ClienteRepository clienteRepository;

	@Test
	void contextLoads() {
	}
	
	@Test
    void testClienteEntity() {
        Cliente cliente = new Cliente();
        cliente.setNombre("John Doe");
        cliente.setDireccion("123 Main St");
        cliente.setTelefono("1234567890");
        cliente.setContrasena("mypassword");
        cliente.setEstado(true);

        clienteRepository.save(cliente);

        Cliente savedCliente = clienteRepository.findById(cliente.getId()).orElse(null);

        assertThat(savedCliente).isEqualTo(cliente);
    }
}
