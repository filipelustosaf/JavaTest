package cd2tec.projeto.services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import cd2tec.projeto.exceptions.ResourceNotFoundException;
import cd2tec.projeto.models.Cep;
import cd2tec.projeto.models.Entrega;
import cd2tec.projeto.repositories.EntregaRepository;

@Service
public class EntregaService {
    
    @Autowired
    private EntregaRepository entregaRepository;
    
    public Entrega findById(Long id) {	
		return entregaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ID não encontrado!"));
	}
	
	public List<Entrega> findAll() {
		return entregaRepository.findAll();
	}	

	public Entrega create(Entrega entrega) {
		Cep cepOrigem = encontraCep(entrega.getCepOrigem());
		Cep cepDestino = encontraCep(entrega.getCepDestino());
	
		entrega.setCepDestino(cepDestino.getCep());
		entrega.setCepOrigem(cepOrigem.getCep());
		entrega.setDddDestino(cepDestino.getDdd());
		entrega.setDddOrigem(cepOrigem.getDdd());
		entrega.setEstadoDestino(cepDestino.getUf());
		entrega.setEstadoOrigem(cepOrigem.getUf());
		entrega.setFrete(calculoFrete(entrega));
        entrega.setPrazo(calculoPrazo(entrega));
		return entregaRepository.save(entrega);
	}
	
	public Entrega update(Long id, Entrega novaEntrega) {
		Entrega entrega = findById(id);	
        entrega.setDestinatario(novaEntrega.getDestinatario() != null ? novaEntrega.getDestinatario() : entrega.getDestinatario());
		entrega.setPeso(novaEntrega.getPeso() != null ? novaEntrega.getPeso() : entrega.getPeso());

		if (novaEntrega.getCepOrigem() != null && novaEntrega.getCepOrigem() != entrega.getCepOrigem()){
			Cep cepOrigem = encontraCep(novaEntrega.getCepOrigem());
			entrega.setCepOrigem(cepOrigem.getCep());
			entrega.setDddOrigem(cepOrigem.getDdd());
			entrega.setEstadoOrigem(cepOrigem.getUf());
		}
		if (novaEntrega.getCepDestino() != null && novaEntrega.getCepDestino() != entrega.getCepDestino()){
			Cep cepDestino = encontraCep(novaEntrega.getCepDestino());
			entrega.setCepDestino(cepDestino.getCep());
			entrega.setDddDestino(cepDestino.getDdd());
			entrega.setEstadoDestino(cepDestino.getUf());
		}
		
		entrega.setFrete(calculoFrete(entrega));
		entrega.setPrazo(calculoPrazo(entrega));
		entrega.setEnderecoDestino(novaEntrega.getEnderecoDestino() != null ? novaEntrega.getEnderecoDestino() : entrega.getEnderecoDestino());
		
		return entregaRepository.save(entrega);
	}	
	
	public void delete(Long id) {
		Entrega entrega = findById(id);
		entregaRepository.delete(entrega);	
	}

	public Cep encontraCep(String cep) {

		RestTemplate template = new RestTemplate();

		UriComponents uri = UriComponentsBuilder.newInstance()
				.scheme("https")
				.host("viacep.com.br")
				.path("ws/"+cep+"/json")
				.build();
	
		ResponseEntity<Cep> response = template.getForEntity(uri.toUriString(), Cep.class);
	
		return response.getBody();
	}


	public Double calculoFrete(Entrega entrega) {
		if(entrega.getDddOrigem().equals(entrega.getDddDestino())) {
			entrega.setFrete(entrega.getPeso() * 0.5);
		 } else if (entrega.getEstadoOrigem().equals(entrega.getEstadoDestino())){
			 entrega.setFrete(entrega.getPeso() * 0.25);
		 } else {
			 entrega.setFrete(entrega.getPeso());
		 }
		 return entrega.getFrete();
	}

	public Integer calculoPrazo(Entrega entrega) {
		if(entrega.getDddOrigem().equals(entrega.getDddDestino())) {
			entrega.setPrazo(1);
		 } else if (entrega.getEstadoOrigem().equals(entrega.getEstadoDestino())){
			 entrega.setPrazo(3);
		 } else {
			 entrega.setPrazo(10);
		 }
		 return entrega.getPrazo();
	}

}
