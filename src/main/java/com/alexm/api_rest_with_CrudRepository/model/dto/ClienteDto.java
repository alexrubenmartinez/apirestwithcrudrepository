package com.alexm.api_rest_with_CrudRepository.model.dto;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Data
@ToString
@Builder
public class ClienteDto implements Serializable {


    private Integer idCliente;
    private String nombre;
    private String apellido;
    private String correo;
    private String fechaRegistro;


}
