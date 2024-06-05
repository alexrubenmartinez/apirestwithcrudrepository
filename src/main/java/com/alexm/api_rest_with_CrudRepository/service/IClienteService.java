package com.alexm.api_rest_with_CrudRepository.service;

import com.alexm.api_rest_with_CrudRepository.model.dto.ClienteDto;
import com.alexm.api_rest_with_CrudRepository.model.entity.Cliente;

import java.util.List;

public interface IClienteService {
    List<Cliente> listAll();
    Cliente save(ClienteDto cliente);
    Cliente findById(Integer id);
    void delete(Cliente cliente);
    boolean existById(Integer id);


}
