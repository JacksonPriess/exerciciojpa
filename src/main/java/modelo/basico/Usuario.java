package modelo.basico;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.Transient;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long id;

	@Column(name = "nome", nullable = false, length = 100 )
	private String nome;
	
	//@Transient // Não vai para o banco... para campos calculados geralmente.
	//private String nomeNaoVaiProBanco;	
	
	private String email;
	
	public Usuario () {
		
	}

	public Usuario(String nome, String email) {
		super();
		this.nome = nome;
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override	
	public String toString() {				
		String frase = String.format("Usuário: %d - %s com e-mail %s", id, nome, email);
		return frase;
	}	
	
}
