package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexao.SingleConnection;
import model.telUser;
import model.usuarios;

public class daoUsuarios {
	
private static Connection connection;
	
	public daoUsuarios() {
		connection = SingleConnection.getConnection();
	}
	
	public List<usuarios> listar () throws Exception{
		List<usuarios> list = new ArrayList<usuarios>();
		
		String sql = "select * from usuarios";
		
		PreparedStatement select = connection.prepareStatement(sql);
		ResultSet resultado = select.executeQuery();
		
		while (resultado.next()){
			usuarios user = new usuarios();
			user.setId(resultado.getLong("id"));
			user.setNome(resultado.getString("nome"));
			user.setEmail(resultado.getString("email"));
			
			list.add(user);
		}
		return list;
	}
	
	public void Salvar(usuarios user){
		String sql="Insert into usuarios(nome,email) values(?,?)";
		try {
			PreparedStatement insert= connection.prepareStatement(sql);
			insert.setString(1, user.getNome());
			insert.setString(2, user.getEmail());
			insert.execute();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public usuarios Buscar(Long id) throws Exception{
		usuarios user = new usuarios();
		String sql = "select * from usuarios where id ="+id;
		PreparedStatement select = connection.prepareStatement(sql);
		ResultSet resultado = select.executeQuery();
		
		while (resultado.next()){
			user.setId(resultado.getLong("id"));
			user.setNome(resultado.getString("nome"));
			user.setEmail(resultado.getString("email"));
		}
		return user;
	}
	
	public void Atualizar (usuarios user) throws SQLException{
		String sql ="update usuarios set nome = ? where id = "+user.getId();
		PreparedStatement update = connection.prepareStatement(sql);
		update.setString(1, user.getNome());
		update.execute();
		connection.commit();
	}
	
	public void Deletar(Long id){
		String sql = "delete from usuarios where id="+id;
		PreparedStatement delete;
		try {
			delete = connection.prepareStatement(sql);
			delete.execute();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

	}
	
	public List<telUser> consultarTel(Long id) throws SQLException {
		List<telUser> dadosUsuario = new ArrayList<telUser>();
		String sql = "select nome,numero, email from telefoneuser as fone ";
		sql +=" inner join usuarios as usua ";
		sql +=" on fone.usuariopessoa = usua.id ";
		sql +=" where usua.id = "+id;
		
		PreparedStatement innerJoin = connection.prepareStatement(sql);
		ResultSet resultado = innerJoin.executeQuery();
		while (resultado.next()) {
			telUser telefoneUsuario = new telUser();
			telefoneUsuario.setEmail(resultado.getString("email"));
			telefoneUsuario.setNome(resultado.getString("nome"));
			telefoneUsuario.setNumero(resultado.getString("numero"));
			
			dadosUsuario.add(telefoneUsuario);
		}
		return dadosUsuario;
	}
	
	public void deleteTelUser(Long id){
		String sqlTel="delete from telefoneuser where usuariopessoa="+id;
		String sqlUser="delete from usuarios where id="+id;
		
		PreparedStatement deleteTel;
		try {
			deleteTel = connection.prepareStatement(sqlTel);
			PreparedStatement deleteUser = connection.prepareStatement(sqlUser);
			deleteTel.execute();
			deleteUser.execute();
			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
		

	}

}
