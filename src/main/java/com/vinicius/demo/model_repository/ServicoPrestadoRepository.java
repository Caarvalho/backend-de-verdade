package com.vinicius.demo.model_repository;

import java.util.List;

import com.vinicius.demo.model_entity.ServicoPrestado;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ServicoPrestadoRepository extends JpaRepository<ServicoPrestado, Integer>{
    
    @Query("select s from ServicoPrestado s join s.cliente c " + 
    "where upper(c.nome)  like upper( :nome) and MONTH(s.data) = :mes ")
    List<ServicoPrestado> findByNomeClienteAndMes
    (@Param("nome") String nome, @Param("mes") Integer mes);
        
    
}
