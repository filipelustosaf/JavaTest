package cd2tec.projeto.exceptions;

public class ResourceNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(String resposta) {
		super(resposta);
	}
}
