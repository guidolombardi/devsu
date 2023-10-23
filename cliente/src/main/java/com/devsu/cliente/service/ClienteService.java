package com.devsu.cliente.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsu.cliente.exception.ClienteNotFoundException;
import com.devsu.cliente.model.Cliente;
import com.devsu.cliente.repository.ClienteRepository;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente crearCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente actualizarCliente(Long clienteId, Cliente cliente) {
        Optional<Cliente> clienteExistente = clienteRepository.findById(clienteId);
        if (clienteExistente.isPresent()) {
            cliente.setId(clienteId);
            return clienteRepository.save(cliente);
        } else {
            throw new ClienteNotFoundException("Cliente no encontrado con ID: " + clienteId);
        }
    }

    public void eliminarCliente(Long clienteId) {
        Optional<Cliente> clienteExistente = clienteRepository.findById(clienteId);
        if (clienteExistente.isPresent()) {
            clienteRepository.deleteById(clienteId);
        } else {
            throw new ClienteNotFoundException("Cliente no encontrado con ID: " + clienteId);
        }
    }

    public Cliente obtenerClientePorId(Long clienteId) {
    	Optional<Cliente> clienteExistente = clienteRepository.findById(clienteId);
        if (!clienteExistente.isPresent()) {
        	throw new ClienteNotFoundException("Cliente no encontrado con ID: " + clienteId);
        }
        return clienteExistente.get();
    }

    public List<Cliente> obtenerTodosLosClientes() {
        return clienteRepository.findAll();
    }
}