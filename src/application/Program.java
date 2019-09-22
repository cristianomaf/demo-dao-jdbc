package application;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.VendedorDao;
import model.entities.Departamento;
import model.entities.Vendedor;

public class Program {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		VendedorDao vendedorDao = DaoFactory.createVendedorDao();

		System.out.println("===TESTE 1: Vendedor findById ===");

		Vendedor vendedor = vendedorDao.findById(3);

		System.out.println(vendedor);

		System.out.println("\n ===TESTE 2: Vendedor findByDepartamento ===");
		Departamento departamento = new Departamento(2, null);

		List<Vendedor> lista = vendedorDao.findByDepartment(departamento);

		for (Vendedor obj : lista) {
			System.out.println(obj);

		}

		System.out.println("\n ===TESTE 3: Vendedor findALL===");

		lista = vendedorDao.findAll();

		for (Vendedor obj : lista) {
			System.out.println(obj);

		}

		System.out.println("\n ===TESTE 4: Vendedor INSERIR===");
		
		Vendedor novoVendedor = new Vendedor(null,"Greg","greg@gmail.com",new Date(),4000.0,departamento);
		
		vendedorDao.insert(novoVendedor);
		
		System.out.println("Inserido! New id = " +novoVendedor.getId());
		

		System.out.println("\n ===TESTE 5: Vendedor UPDATE ===");	
		vendedor = vendedorDao.findById(1);
		vendedor.setNome("Martha Waine");
		vendedorDao.update(vendedor);
		System.out.println("Update Realizado");
		
		System.out.println("\n ===TESTE 6: Vendedor DELETE ===");
		
		System.out.println("Entre com id para deletar:");
		int id = sc.nextInt();
		vendedorDao.deleteById(id);
		System.out.println("Deletado com sucesso");
		
		sc.close();
		
		
		
}
}
