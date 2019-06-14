package br.com.glauber.circulusplay.resource;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.glauber.circulusplay.domain.Filme;
import br.com.glauber.circulusplay.service.FilmeService;

@RestController
@RequestMapping(value="/filmes")
public class FilmeResource {
	
	@Autowired
	private FilmeService service;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Filme>> findAll() {
		List<Filme> lista = service.findAll();		 
		return ResponseEntity.ok().body(lista);
	}
	
	@RequestMapping(value="/populares",method=RequestMethod.GET)
	public ResponseEntity<List<Filme>> findAllPopulares() {
		List<Filme> lista = service.findPopulares();		 
		return ResponseEntity.ok().body(lista);
	}
	
	@RequestMapping(value="/buscar/{nome}",method=RequestMethod.GET)
	public ResponseEntity<List<Filme>> findAllPopulares(@PathVariable String nome) {
		List<Filme> lista = service.findPorNome(nome);
		return ResponseEntity.ok().body(lista);
	}	
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Filme> find(@PathVariable Integer id) {
		Filme obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
		
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody Filme filme) {
		Filme obj = service.insert(filme);
				
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody Filme filme, @PathVariable Integer id) {		
		Filme obj = service.update(filme);
		System.out.println(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
