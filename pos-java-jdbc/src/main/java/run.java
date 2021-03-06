import java.sql.SQLException;

import dao.daoUsuarios;
import model.usuarios;

public class run {

	public static void main(String[] args) {

		daoUsuarios daousuarios = new daoUsuarios();
		usuarios user = new usuarios();
		//Insert
		/*
		user.setNome("marcos");
		user.setEmail("marcos@gmai.com");
		daousuarios.Salvar(user);
		*/
		//Select
		/*
		try {
			user = daousuarios.Buscar(1L);
			//System.out.println(user.getId() +" "+ user.getNome() +" "+ user.getEmail());
			System.out.println(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		*/
		//Update
		/*
		try {			
			user = daousuarios.Buscar(1L);
			user.setNome("igor");;
			daousuarios.Atualizar(user);
			user = daousuarios.Buscar(1L);
			System.out.println(user);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		*/
		//Deletar
		//daousuarios.Deletar(7L);
		/*
		try {
			System.out.println(daousuarios.consultarTel(1L));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		daousuarios.deleteTelUser(1L);

	}

}
