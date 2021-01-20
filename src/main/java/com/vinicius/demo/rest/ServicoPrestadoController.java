package com.vinicius.demo.rest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


import com.vinicius.demo.model_entity.Cliente;
import com.vinicius.demo.model_entity.ServicoPrestado;
import com.vinicius.demo.model_repository.ClienteRepository;
import com.vinicius.demo.model_repository.ServicoPrestadoRepository;
import com.vinicius.demo.rest.dto.ServicoPrestadoDTO;
import com.vinicius.demo.util.BigDecimalConverter;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/servicos-prestados")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:4200")

public class ServicoPrestadoController {
    private final ClienteRepository clienteRep;
    private final ServicoPrestadoRepository rep;
    private final BigDecimalConverter bigDecimalConverter;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) 
    public ServicoPrestado salvar( @RequestBody ServicoPrestadoDTO dto){
        
        LocalDate data = LocalDate.parse(dto.getData(),
        DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        
        Integer idCliente = dto.getIdCliente();
        Cliente cliente = clienteRep.findById(idCliente)
        .orElseThrow(()->
        new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente Inexistente"));
        
        BigDecimal valor = bigDecimalConverter.converter(dto.getPreco());

        ServicoPrestado servicoPrestado = new ServicoPrestado();
        servicoPrestado.setDescricao(dto.getDescricao());
        servicoPrestado.setData(data);
        servicoPrestado.setCliente(cliente);
        servicoPrestado.setValor(valor);
        return rep.save(servicoPrestado);

    }

    @GetMapping
    public List<ServicoPrestado> pesquisar(
        @RequestParam(value = "nome", required = false, defaultValue = "") String nome,
        @RequestParam(value ="mes", required = false) Integer mes 
    ){
    return rep.findByNomeClienteAndMes( "%" + nome + "%", mes);
    }
}   
