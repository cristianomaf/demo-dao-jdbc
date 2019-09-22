package model.dao;

import java.util.List;

import model.entities.Departamento;
import model.entities.Vendedor;

public interface VendedorDao {
	
	void insert(Vendedor obj);

	void update(Vendedor obj);

	void deleteById(Integer id);

	Vendedor findById(Integer Id); // vai ser implementada para procura dos departmentos por id

	List<Vendedor> findAll(); // lista que vai ter todos os departamentos
	List <Vendedor> findByDepartment (Departamento departamento);

}
