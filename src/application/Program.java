package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.VendedorDao;
import model.entities.Departamento;
import model.entities.Vendedor;

public class Program {

	public static void main(String[] args) {
	
		
	
		VendedorDao vendedorDao = DaoFactory.createVendedorDao();
		
		System.out.println("===TESTE 1: Vendedor findById ===");
		
		Vendedor vendedor = vendedorDao.findById(3);
		
		System.out.println(vendedor);
		
		
		System.out.println("\n ===TESTE 2: Vendedor findByDepartamento ===");
		Departamento departamento = new Departamento(2,null);
		
		List<Vendedor>lista = vendedorDao.findByDepartment(departamento);
		
		for (Vendedor obj : lista) {
			System.out.println(obj);
			
		}
	}

}
