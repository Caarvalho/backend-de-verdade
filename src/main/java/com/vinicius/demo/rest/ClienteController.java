package com.vinicius.demo.rest;

import java.util.List;

import javax.validation.Valid;

import com.vinicius.demo.model_entity.Cliente;
import com.vinicius.demo.model_repository.ClienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin("http://localhost:4200")
public class ClienteController {
    
  private ClienteRepository rep;

  @GetMapping
  public List<Cliente> getAll(){
    return rep.findAll();
  }
    
  @Autowired
  public ClienteController(ClienteRepository rep){
    this.rep = rep;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Cliente salvar(@RequestBody @Valid Cliente cliente ){
    return rep.save(cliente);
  }

  @GetMapping("{id}")
  public Cliente acharPorId(@PathVariable Integer id){
    return rep.findById(id)
    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente Não Encontrado."));

  }

  @DeleteMapping("{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deletar(@PathVariable Integer id){
    rep.findById(id).map( cliente -> {
      rep.delete(cliente);
      return Void.TYPE;
    }).
    orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente Não Encontrado."));

  }
  @PutMapping("{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void atualizar(@PathVariable Integer id, @RequestBody @Valid Cliente clienteAtualizado){
    rep.findById(id).map( cliente -> {
      clienteAtualizado.setId(cliente.getId());
      rep.save(clienteAtualizado);
      return Void.TYPE;
    }).
    orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente Não Encontrado."));

  }



}
