package br.com.glauber.circulusplay.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.glauber.circulusplay.dao.ComentarioPostagemDAO;
import br.com.glauber.circulusplay.domain.Comentario;
import br.com.glauber.circulusplay.domain.ComentarioPostagem;
import br.com.glauber.circulusplay.domain.Filme;
import br.com.glauber.circulusplay.service.exceptions.DataIntegrityException;
import br.com.glauber.circulusplay.service.exceptions.ObjectNotFoundException;

@Service
public class ComentarioService {

	@Autowired
	private ComentarioPostagemDAO dao;
	
	public Comentario find(Integer id) {
		Comentario obj = dao.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id
					+ ", Tipo: " + Filme.class.getName());
		}
		return obj;
	}

	public Comentario insert(ComentarioPostagem obj) {
		obj.setId(null);
		return dao.save(obj);
	}
	
	public Comentario update(ComentarioPostagem obj) {
		find(obj.getId());		
		return dao.save(obj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			dao.delete(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma Comentario que possui produtos");
		}
	}
	
	public List<ComentarioPostagem> findAll() {
		return dao.findAll();
	}
	
	public Page<ComentarioPostagem> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return dao.findAll(pageRequest);
	}

	public List<Comentario> findByPostagemId(int postagem) {
		return dao.findByPostagemId(postagem);
	}
	
}