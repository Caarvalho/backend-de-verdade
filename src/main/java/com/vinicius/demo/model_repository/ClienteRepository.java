package com.vinicius.demo.model_repository;

import com.vinicius.demo.model_entity.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ClienteRepository extends JpaRepository <Cliente, Integer >{
    
}
