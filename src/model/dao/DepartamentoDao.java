package model.dao;

import java.util.List;

import model.entities.Departamento;

public interface DepartamentoDao {

	void insert(Departamento obj);

	void update(Departamento obj);

	void deleteById(Integer id);

	Departamento findById(Integer Id); // vai ser implementada para procura dos departmentos por id

	List<Departamento> findAll(); // lista que vai ter todos os departamentos

}
