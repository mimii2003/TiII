package com.ti2cc;

public class Principal {
	
	public static void main(String[] args) {
		
		DAO dao = new DAO();
		
		dao.conectar();

		
		//Inserir um elemento na tabela
		Compras compras = new Compras(1, 4, 2,"joao", "abcd", 'M');
		if(dao.inserirCompras(compras) == true) {
			System.out.println("Inserção com sucesso -> " + compras.toString());
		}
		
		//Mostrar usuários do sexo masculino		
		System.out.println("==== Mostrar usuários do sexo masculino === ");
		Compras[] comprass = dao.getUsuariosMasculinos();
		for(int i = 0; i < comprass.length; i++) {
			System.out.println(comprass[i].toString());
		}

		//Atualizar usuário
		compras.setSenha("nova senha");
		dao.atualizarCompras(compras);

		//Mostrar usuários do sexo masculino
		System.out.println("==== Mostrar usuários === ");
		comprass = dao.getCompras();
		for(int i = 0; i < comprass.length; i++) {
			System.out.println(comprass[i].toString());
		}
		
		//Excluir usuário
		dao.excluirCompras(compras.getNome_comprador());
		
		//Mostrar usuários
		comprass = dao.getCompras();
		System.out.println("==== Mostrar usuários === ");		
		for(int i = 0; i < comprass.length; i++) {
			System.out.println(comprass[i].toString());
		}
		
		dao.close();
	}
}