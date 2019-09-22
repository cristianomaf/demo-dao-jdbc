package application;

import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.DepartamentoDao;
import model.entities.Departamento;

public class Program2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		DepartamentoDao departmentDao = DaoFactory.createDepartmentDao();

		System.out.println("\n ===TESTE 1: Departamento INSERIR===");

		Departamento novoDp = new Departamento(null, "Musica");
		departmentDao.insert(novoDp);
		System.out.println("Inserido! New id; " + novoDp.getId());

		System.out.println("\n ===TESTE 2: Departamento UPDATE ===");
		Departamento novoDp2 = departmentDao.findById(8);
		novoDp2.setNome("Casa");
		departmentDao.update(novoDp2);
		System.out.println("Update Realizado");

		System.out.println("=== TESTE 3: findById =======");
		Departamento dep = departmentDao.findById(1);
		System.out.println(dep);

		System.out.println("\n=== TESTE 4: findAll =======");
		List<Departamento> list = departmentDao.findAll();
		for (Departamento d : list) {
		System.out.println(d);
		}

			System.out.println("\n=== TEST 5: delete =======");
			System.out.print("Enter id for delete test: ");
			int id = sc.nextInt();
			departmentDao.deleteById(id);
			System.out.println("Delete completed");
			
			sc.close();

		
	}

}
