package teste.basico;

import infra.DAO;
import modelo.basico.Produto;

public class NovoProdutoTest {

	public static void main(String[] args) {
		Produto produto = new Produto("Caneta", 7.98);		
		DAO<Produto> dao = new DAO<>(Produto.class);
		dao.abrirT().incluir(produto).fecharT().fechar();
	}

}
