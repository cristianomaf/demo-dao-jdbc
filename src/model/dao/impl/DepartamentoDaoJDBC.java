package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import db.DB;
import db.DbException;
import db.DbIntegrityException;
import model.dao.DepartamentoDao;
import model.entities.Departamento;

public class DepartamentoDaoJDBC implements DepartamentoDao {
	
	// conexao com banco dependencia
		private Connection conn;
		
		public DepartamentoDaoJDBC(Connection conn) {
			this.conn = conn;
		}
		
		

	@Override
	public void insert(Departamento obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("INSERT INTO Department " + "(Name) "
					+ "VALUES " 
					+ "(?)", 
					Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, obj.getNome());
			

			int linhasAfetadas = st.executeUpdate();

			if (linhasAfetadas > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
				DB.closeResultSet(rs);
			} else {
				throw new DbException("Erro inesperado! Nenhuma linha afetada");
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);

		}
		
	}

	@Override
	public void update(Departamento obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("UPDATE department "
					+ "SET Name = ? "
					+  "WHERE Id = ?");

			st.setString(1, obj.getNome());
			st.setInt(2, obj.getId());
			
			st.executeUpdate();

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);

		}
		
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
				"DELETE FROM department WHERE Id = ?");

			st.setInt(1, id);

			st.executeUpdate();
		}
		catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		} 
		finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public Departamento findById(Integer Id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
				"SELECT * FROM department WHERE Id = ?");
			st.setInt(1, Id);
			rs = st.executeQuery();
			if (rs.next()) {
				Departamento obj = new Departamento();
				obj.setId(rs.getInt("Id"));
				obj.setNome(rs.getString("Name"));
				return obj;
			}
			return null;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		
	}

	@Override
	public List<Departamento> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
				"SELECT * FROM department ORDER BY Name");
			rs = st.executeQuery();

			List<Departamento> list = new ArrayList<>();

			while (rs.next()) {
				Departamento obj = new Departamento();
				obj.setId(rs.getInt("Id"));
				obj.setNome(rs.getString("Name"));
				list.add(obj);
			}
			return list;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	
	}
	

}
