package modelo.basico;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

class UsuarioTest {
	

	void ensureCanPersistUsuario() {
		// Entity manager factory -> Interface, que cuida dos EntityManager
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("exercicio-jpa");

		// Entity Manager -> Interface responsável por fazer o crud, interação com o
		// banco de dados.
		EntityManager em = emf.createEntityManager();
		
		Usuario novoUsuario = new Usuario("Jackson", "jp@gmail.com");
				
		em.getTransaction().begin();
		em.persist(novoUsuario);
		em.getTransaction().commit();
		
		System.out.println(novoUsuario.toString());
		
		em.close();
		emf.close();
		
		assertEquals("Jackson", novoUsuario.getNome());
		assertEquals("jp@gmail.com", novoUsuario.getEmail());
	}	
	
	
	void ensureCanFindUsuario() {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("exercicio-jpa");
		EntityManager em = emf.createEntityManager();		
		Usuario usuarioResult = em.find(Usuario.class, 1L);
		em.close();
		emf.close();		
		assertEquals( "Jackson", usuarioResult.getNome() );		
		
	}
	
	
	void ensureCanFindManyUsers() {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("exercicio-jpa");
		EntityManager em = emf.createEntityManager();		
		
		//Usuario usuarioResult = em.find(Usuario.class, 1L);
		                        // class name , JPA convert to SQL... 
		String jpql = "SELECT u from Usuario u";
		TypedQuery<Usuario> query = em.createQuery(jpql, Usuario.class); //Reflection... 
		query.setMaxResults(5);
		
		
		List<Usuario> usuarios = query.getResultList();
		
		usuarios.forEach( usuario -> 
			System.out.println(usuario.toString())			
		);
		
		em.close();
		emf.close();		
		assertEquals( 5 , usuarios.size() );		
		
	}
	
	
	void ensureCanChangeUser() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("exercicio-jpa");
		EntityManager em = emf.createEntityManager();	
		em.getTransaction().begin();		
		Usuario usuario = em.find(Usuario.class, 8L);		
		usuario.setEmail("marina@valter.com");
		usuario.setNome("Mariana");		
		em.merge(usuario);		
		em.getTransaction().commit();		
		String nomeNovo = em.find(Usuario.class, 8L).getNome();		
		em.close();
		emf.close();		
		assertEquals( "Mariana" , nomeNovo );
	}
	
	@Test
	void ensureCanRemoveUser() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("exercicio-jpa");
		EntityManager em = emf.createEntityManager();		
				
		Usuario usuario = em.find(Usuario.class, 8L);
		
		if (usuario != null) {  
			em.getTransaction().begin();
			em.remove(usuario);		
			em.getTransaction().commit();
		}		
				
		em.close();
		emf.close();		
		
	}


}
