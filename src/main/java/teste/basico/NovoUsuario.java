package teste.basico;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class NovoUsuario {

	public static void main(String[] args) {
		// Entity manager factory -> Interface, que cuida dos EntityManager
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("exercicio-jpa");
		
		// Entity Manager -> Interface respons�vel por fazer o crud, intera��o com o banco de dados.
		EntityManager em = emf.createEntityManager();

		
	}
}
