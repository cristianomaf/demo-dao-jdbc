package application;

import model.dao.DaoFactory;
import model.dao.DepartamentoDao;
import model.entities.Departamento;

public class Program2 {

	public static void main(String[] args) {
		DepartamentoDao departmentDao = DaoFactory.createDepartmentDao();

		System.out.println("\n ===TESTE 1: Departamento INSERIR===");

		Departamento novoDp = new Departamento(null, "Musica");
		departmentDao.insert(novoDp);
		System.out.println("Inserido! New id; " + novoDp.getId());

	}

}
