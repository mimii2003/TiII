package com.ti2cc;

import java.sql.*;

public class DAO {
	private Connection conexao;
	
	public DAO() {
		conexao = null;
	}
	
	public boolean conectar() {
		String driverName = "org.postgresql.Driver";                    
		String serverName = "localhost";
		String mydatabase = "teste";
		int porta = 5432;
		String url = "jdbc:postgresql://" + serverName + ":" + porta +"/" + mydatabase;
		String username = "ti2cc";
		String password = "ti@cc";
		boolean status = false;

		try {
			Class.forName(driverName);
			conexao = DriverManager.getConnection(url, username, password);
			status = (conexao == null);
			System.out.println("Conexão efetuada com o postgres!");
		} catch (ClassNotFoundException e) { 
			System.err.println("Conexão NÃO efetuada com o postgres -- Driver não encontrado -- " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("Conexão NÃO efetuada com o postgres -- " + e.getMessage());
		}

		return status;
	}
	
	public boolean close() {
		boolean status = false;
		
		try {
			conexao.close();
			status = true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return status;
	}
	
	public boolean inserirCompras(Compras compras) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("INSERT INTO compras (chocolate_preto, chocolate_branco,arroz, nome_comprador, senha, sexo) "
					       + "VALUES ("+compras.getChocolate_preto()+ ", '" + compras.getChocolate_branco() + "', '"  
					       + compras.getArroz() + "', '" + compras.getNome_comprador() + "', '" + compras.getSenha() + "', '" + compras.getSexo() +    "');");
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean atualizarCompras(Compras compras) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "UPDATE compras SET nome_comprador = '" + compras.getChocolate_preto()+ ", '" + compras.getChocolate_branco() + "', '"  
				       + compras.getArroz() + "', '" + compras.getNome_comprador() + "', '" + compras.getSenha()+ "', '" + compras.getSexo();
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean excluirCompras(String nome_comprador) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM compras WHERE nome = " + nome_comprador);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	
	public Compras[] getCompras() {
		Compras[] compras = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM compras");		
	         if(rs.next()){
	             rs.last();
	             compras = new Compras[rs.getRow()];
	             rs.beforeFirst();

	             for(int i = 0; rs.next(); i++) {
	            	 compras[i] = new Compras(rs.getInt("chocolate_preto"), rs.getInt("chocolate_branco"), 
	                		                  rs.getInt("arroz"),  rs.getString("nome_comprador"),rs.getString("senha"),rs.getString("sexo").charAt(0));
	             }
	          }
	          st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return compras;
	}

	
	public Compras[] getUsuariosMasculinos() {
		Compras[] compras = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM compras WHERE compras.sexo LIKE 'M'");		
	         if(rs.next()){
	             rs.last();
	             compras = new Compras[rs.getRow()];
	             rs.beforeFirst();

	             for(int i = 0; rs.next(); i++) {
		                compras[i] = new Compras(rs.getInt("chocolate_preto"), rs.getInt("chocolate_branco"), 
      		                  rs.getInt("arroz"),  rs.getString("nome_comprador"),rs.getString("senha"),rs.getString("sexo").charAt(0));
	             }
	          }
	          st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return compras;
	}
}