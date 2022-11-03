package com.example.demo.form.app.entity.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.form.app.entity.Usuario;


public interface IUsuarioDao extends CrudRepository<Usuario, Long>{
public Usuario findByUsername(String username);//consulta por estandar de nombre de metodo

}
