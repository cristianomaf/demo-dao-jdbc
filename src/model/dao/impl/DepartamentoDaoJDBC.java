package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.mysql.jdbc.Statement;

import db.DB;
import db.DbException;
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Departamento findById(Integer Id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Departamento> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
