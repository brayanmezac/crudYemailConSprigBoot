package com.example.taller.bda;

import com.example.taller.variables.Cliente;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class Clientebda implements IClientebda {
	
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional (readOnly = true)
	public List<Cliente> findAll() {
		
		return em.createQuery("from Cliente").getResultList();
	}

	@Override
	@Transactional
	public void saved(Cliente cliente) {
		if (cliente.getId() != null && cliente.getId() >0)
		{
			em.merge(cliente);
		}else {
			em.persist(cliente);
		}
	}

	@Override
	@Transactional
	public void delete(Long id) {
		
		em.remove(findOne(id));
		
	}

	@Override
	@Transactional
	public Cliente findOne(Long id) {
		
		return em.find(Cliente.class, id);
	}

	
}
