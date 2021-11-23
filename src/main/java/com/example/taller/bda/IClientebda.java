package com.example.taller.bda;

import com.example.taller.variables.Cliente;

import java.util.List;

public interface IClientebda {
	
	public List<Cliente> findAll();
	
	public void saved(Cliente cliente);
	
	public void delete(Long id);
	
	public Cliente findOne(Long id);

}
