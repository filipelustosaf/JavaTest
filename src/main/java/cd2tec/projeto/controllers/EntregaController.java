package cd2tec.projeto.controllers;
import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import cd2tec.projeto.models.Entrega;
import cd2tec.projeto.responses.MessageResponse;
import cd2tec.projeto.services.EntregaService;

@RestController
@RequestMapping(path = "/entrega")
public class EntregaController extends MessageResponseController{
    
    @Autowired 
	private EntregaService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Entrega> findById(@PathVariable Long id) {
		Entrega entrega = service.findById(id);
		return ResponseEntity.ok().body(entrega);
	};

	@GetMapping
	public ResponseEntity<List<Entrega>> findAll() {
		List<Entrega> entregas = service.findAll();
		return ResponseEntity.ok().body(entregas);
	}
	
	@PostMapping
	public ResponseEntity<MessageResponse> create(@RequestBody Entrega entrega) {
		Entrega newEntrega = service.create(entrega);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newEntrega.getId()).toUri();
		return ResponseEntity.created(uri).body(createMessageResponse("Entrega criada com sucesso!"));
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<MessageResponse> update(@PathVariable Long id,
		@RequestBody Entrega entrega) {
		service.update(id, entrega);
		return ResponseEntity.ok().body(createMessageResponse("Entrega atualizado com sucesso!"));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
