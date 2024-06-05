package com.alexm.api_rest_with_CrudRepository.service.impl;

import com.alexm.api_rest_with_CrudRepository.model.dao.ClienteDao;
import com.alexm.api_rest_with_CrudRepository.model.dto.ClienteDto;
import com.alexm.api_rest_with_CrudRepository.model.entity.Cliente;
import com.alexm.api_rest_with_CrudRepository.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClienteImplService implements IClienteService {
    @Autowired
    private ClienteDao clienteDao;

    @Override
    public List<Cliente> listAll() {
        return (List) clienteDao.findAll();
    }

    @Transactional
    @Override
    public Cliente save(ClienteDto clienteDto) {
        Cliente cliente = Cliente.builder().idCliente(clienteDto.getIdCliente())
                .nombre(clienteDto.getNombre())
                .apellido(clienteDto.getApellido())
                .fechaRegistro(clienteDto.getFechaRegistro())
                .correo(clienteDto.getCorreo())
                .build();
        return clienteDao.save(cliente);
    }

    @Transactional(readOnly = true)
    @Override
    public Cliente findById(Integer id) {
        return clienteDao.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void delete(Cliente cliente) {
         clienteDao.delete(cliente);
    }

    @Override
    public boolean existById(Integer id) {
        return clienteDao.existsById(id);
    }
}
