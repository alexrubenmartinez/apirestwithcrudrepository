package com.alexm.api_rest_with_CrudRepository.controller;


import com.alexm.api_rest_with_CrudRepository.model.dto.ClienteDto;
import com.alexm.api_rest_with_CrudRepository.model.entity.Cliente;
import com.alexm.api_rest_with_CrudRepository.model.payload.MensajeResponse;
import com.alexm.api_rest_with_CrudRepository.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class ClienteController {
    @Autowired
    private IClienteService clienteService;

    @GetMapping("clientes")
    public ResponseEntity<?> showAll() {
       List<Cliente> getList = clienteService.listAll();

        if (getList==null){
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("No hay registros!")
                            .object(null).build()
                    ,HttpStatus.OK);
        }

        return new ResponseEntity<>(
                MensajeResponse.builder()
                        .mensaje(null)
                        .object(getList)
                        .build()
                ,HttpStatus.OK);

    }




    @PostMapping("cliente")
    public ResponseEntity<?> save(@RequestBody ClienteDto clienteDto) {
        Cliente clienteSave =null;
        try {
             clienteSave = clienteService.save(clienteDto);

            return new ResponseEntity<>(MensajeResponse.builder().mensaje("Guardado correctamente").object(ClienteDto.builder().idCliente(clienteSave.getIdCliente())
                    .nombre(clienteSave.getNombre())
                    .apellido(clienteSave.getApellido())
                    .fechaRegistro(clienteSave.getFechaRegistro())
                    .correo(clienteSave.getCorreo())
                    .build()).build(),HttpStatus.CREATED);

        }catch (DataAccessException exDT){
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje(exDT.getMessage())
                            .object(null).build()
                    ,HttpStatus.METHOD_NOT_ALLOWED);

        }

    }


    @PutMapping("cliente/{id}")
    public ResponseEntity<?> update(@RequestBody ClienteDto clienteDto,@PathVariable Integer id) {
        Cliente clienteUpdate=null;

        try {
            if(clienteService.existById(id)){
                clienteDto.setIdCliente(id);
                clienteUpdate= clienteService.save(clienteDto);
                return new ResponseEntity<>(MensajeResponse.builder().mensaje("Guardado correctamente").object(ClienteDto.builder().idCliente(clienteUpdate.getIdCliente())
                        .nombre(clienteUpdate.getNombre())
                        .apellido(clienteUpdate.getApellido())
                        .fechaRegistro(clienteUpdate.getFechaRegistro())
                        .correo(clienteUpdate.getCorreo())
                        .build()).build(),HttpStatus.CREATED);
            }
            else {
                return new ResponseEntity<>(
                        MensajeResponse.builder()
                                .mensaje("El registro que intenta modificar no existe en la base de datos")
                                .object(null).build()
                        ,HttpStatus.NOT_FOUND);
            }





        }catch (DataAccessException exDT){
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje(exDT.getMessage())
                            .object(null).build()
                    ,HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }


    @DeleteMapping("cliente/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        try {
            Cliente clienteDelete = clienteService.findById(id);
            clienteService.delete(clienteDelete);
            return new ResponseEntity<>(clienteDelete,HttpStatus.NO_CONTENT);
        }catch (DataAccessException exDT){
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje(exDT.getMessage())
                            .object(null).build()
                    ,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        }

    @GetMapping("cliente/{id}")
    public ResponseEntity<?> showById(@PathVariable Integer id) {
        Cliente cliente = clienteService.findById(id);

        if (cliente==null){
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("El registro que intenta buscar no existe!")
                            .object(null).build()
                    ,HttpStatus.NOT_FOUND);
        }


        return new ResponseEntity<>(
                MensajeResponse.builder()
                        .mensaje(null)
                        .object(ClienteDto.builder().idCliente(cliente.getIdCliente())
                                .nombre(cliente.getNombre())
                                .apellido(cliente.getApellido())
                                .fechaRegistro(cliente.getFechaRegistro())
                                .correo(cliente.getCorreo())
                                .build()).build()
                ,HttpStatus.OK);

    }

}
