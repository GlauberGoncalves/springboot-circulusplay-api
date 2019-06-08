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

import br.com.glauber.circulusplay.domain.Genero;
import br.com.glauber.circulusplay.service.GeneroService;

@RestController
@RequestMapping(value="/generos")
public class GeneroResource {

	@Autowired
	private GeneroService service;

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Genero>> findAll() {
		List<Genero> lista = service.findAll();
		return ResponseEntity.ok().body(lista);
	}

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Genero> find(@PathVariable Integer id) {
		Genero obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody Genero genero) {
		Genero obj = service.insert(genero);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();

		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody Genero Genero, @PathVariable Integer id) {
		Genero obj = service.update(Genero);
		System.out.println(obj);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}