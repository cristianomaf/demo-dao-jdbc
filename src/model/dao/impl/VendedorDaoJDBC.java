package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.VendedorDao;
import model.entities.Departamento;
import model.entities.Vendedor;

public class VendedorDaoJDBC implements VendedorDao {
	// conexao com banco dependencia
	private Connection conn;

	public VendedorDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Vendedor obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Vendedor obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Vendedor findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		// conexao banco
		try {
			st = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName " + "FROM seller INNER JOIN department "
							+ "ON seller.DepartmentId = department.Id " + "WHERE seller.Id = ?");

			st.setInt(1, id);
			rs = st.executeQuery();

			// verifica se tem resultado e instancia um dep e um vendedor com o departamento
			if (rs.next()) {
				Departamento dep = instanciaDepartamento(rs);

				Vendedor obj = instanciaVendedor(rs, dep);

				return obj;

			}
			return null;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);

		}

	}

	
	private Vendedor instanciaVendedor(ResultSet rs, Departamento dep) throws SQLException {
		Vendedor obj = new Vendedor();
		obj.setId(rs.getInt("Id"));
		obj.setNome(rs.getString("Name"));
		obj.setEmail(rs.getString("Email"));
		obj.setSalarioBase(rs.getDouble("BaseSalary"));
		obj.setNascimento(rs.getDate("BirthDate"));

		obj.setDepartamento(dep);
		return obj;
	}

	//metodo instancia departamento sem tratar excecao pois ja sera tratada 
	private Departamento instanciaDepartamento(ResultSet rs) throws SQLException {		
		Departamento dep = new Departamento();
		dep.setId(rs.getInt("DepartmentId"));
		dep.setNome(rs.getString("DepName"));
		return dep;
	}

	@Override
	public List<Vendedor> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		// conexao banco
		try {
			st = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName " //***tem que ter o espaco em branco no final
					+"FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "ORDER BY Name");

			
			rs = st.executeQuery();
			
			List<Vendedor> lista = new ArrayList<>();
			
			Map<Integer, Departamento> map = new HashMap<>(); //guarda departamento que forem instanciados
			

			//Aqui pode ter mais de um resultado entao while
			while (rs.next()) {
				Departamento dep = map.get(rs.getInt("DepartmentId")); //verifica se ja existe um dep com o id
				
				if(dep == null) {
				dep = instanciaDepartamento(rs);
				map.put(rs.getInt("DepartmentId"), dep);
				}

				Vendedor obj = instanciaVendedor(rs, dep);
				
				lista.add(obj);
				

			}
			return lista;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);

		}
		
	
	}

	@Override
	public List<Vendedor> findByDepartment(Departamento departamento) {
		PreparedStatement st = null;
		ResultSet rs = null;
		// conexao banco
		try {
			st = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName " //***tem que ter o espaco em branco no final
					+"FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "WHERE DepartmentId = ? "
					+ "ORDER BY Name");

			st.setInt(1, departamento.getId());
			rs = st.executeQuery();
			
			List<Vendedor> lista = new ArrayList<>();
			
			Map<Integer, Departamento> map = new HashMap<>(); //guarda departamento que forem instanciados
			

			//Aqui pode ter mais de um resultado entao while
			while (rs.next()) {
				Departamento dep = map.get(rs.getInt("DepartmentId")); //verifica se ja existe um dep com o id
				
				if(dep == null) {
				dep = instanciaDepartamento(rs);
				map.put(rs.getInt("DepartmentId"), dep);
				}

				Vendedor obj = instanciaVendedor(rs, dep);
				
				lista.add(obj);
				

			}
			return lista;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);

		}
	}
}

		
	


