package com.alexm.api_rest_with_CrudRepository.model.dao;

import com.alexm.api_rest_with_CrudRepository.model.entity.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface ClienteDao extends CrudRepository<Cliente, Integer> {
}
