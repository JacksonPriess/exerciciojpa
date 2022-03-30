package infra;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import modelo.basico.Produto;

public class DAO<E> {

	//Quando static é usado, o método e o atributo passam a ser da classe e não do objeto.
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Class<E> classe;
	
	//Bloco de inicialização, é executado antes do construtor.
	static {
		try {
			emf = Persistence.createEntityManagerFactory("exercicio-jpa");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public DAO() {
		this(null);
	}
	
	public DAO(Class<E> classe) {	
		this.classe = classe;
		//Representa a conexão
		em = emf.createEntityManager();
	}
	
	public DAO<E> test() {
		return this;
	}
	
	public DAO<E> abrirT() {
		em.getTransaction().begin();
		return this;
	}
	
	public DAO<E> fecharT() {
		em.getTransaction().commit();
		return this;
	}
	
	public void fechar() {
		em.close();
	}
	
	public DAO<E> incluir(E entidade) {
		em.persist(entidade);
		return this;
	}
	
	public DAO<E> incluirAtomico(E entidade) {		
		return this.abrirT().incluir(entidade).fecharT();
	}
	
	public List<E> obterTodos() {
		return this.obterTodos(10, 0);
	}
														// deslocamento -> offSet
	public List<E> obterTodos(int quantidadeRegistros, int deslocamento ) {
		
		if ( classe == null ) 
			throw new UnsupportedOperationException("Classe nula");		
		
		String jpql = "select e from "+ classe.getName() + " e";
		TypedQuery<E> query = em.createQuery(jpql, classe);
		query.setMaxResults(quantidadeRegistros);
		query.setFirstResult(deslocamento);		
		return query.getResultList();
		
	}
	
	public DAO<E> remover(E entidade) {
		em.remove(entidade);
		return this;
	}
	
	public DAO<E> alterar(E entidade) {
		em.merge(entidade);
		return this;
	}
	
	
}
