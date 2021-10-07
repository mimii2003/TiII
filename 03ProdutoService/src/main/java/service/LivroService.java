package service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import dao.LivroDAO;
import model.Livro;
import spark.Request;
import spark.Response;


public class LivroService {

	private LivroDAO livroDAO;

	public LivroService() {
		try {
			livroDAO = new LivroDAO("produto.dat");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public Object add(Request request, Response response) {
		String nome = request.queryParams("nome");
		float preco = Float.parseFloat(request.queryParams("preco"));
		int quantidade = Integer.parseInt(request.queryParams("quantidade"));
		LocalDateTime dataPublicacao = LocalDateTime.parse(request.queryParams("dataPublicacao"));
		String genero = request.queryParams("genero");
		int id = livroDAO.getMaxId() + 1;

		Livro livro = new Livro(id, nome, preco, quantidade, dataPublicacao, null, genero);

		livroDAO.add(livro);

		response.status(201); // 201 Created
		return id;
	}

	public Object get(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));
		
		Livro livro = (Livro) livroDAO.get(id);
		
		if (livro != null) {
    	    response.header("Content-Type", "application/xml");
    	    response.header("Content-Encoding", "UTF-8");

            return "<livro>\n" + 
            		"\t<id>" + livro.getId() + "</id>\n" +
            		"\t<nome>" + livro.getNome() + "</nome>\n" +
            		"\t<preco>" + livro.getPreco() + "</preco>\n" +
            		"\t<quantidade>" + livro.getQuant() + "</quantidade>\n" +
            		"\t<fabricacao>" + livro.getDataPublicacao() + "</fabricacao>\n" +
            		"\t<genero>" + livro.getGenero() + "</genero>\n" +
            		"</livro>\n";
        } else {
            response.status(404); // 404 Not found
            return "Livro " + id + " não encontrado.";
        }

	}

	public Object update(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
        
		Livro livro = (Livro) livroDAO.get(id);

        if (livro != null) {
        	livro.setNome(request.queryParams("nome"));
        	livro.setPreco(Float.parseFloat(request.queryParams("preco")));
        	livro.setQuant(Integer.parseInt(request.queryParams("quantidade")));
        	livro.setDataPublicacao(LocalDateTime.parse(request.queryParams("dataPublicacao")));
        	livro.setGenero(request.queryParams("genero"));

        	livroDAO.update(livro);
        	
            return id;
        } else {
            response.status(404); // 404 Not found
            return "Livro não encontrado.";
        }

	}

	public Object remove(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));

        Livro livro = (Livro) livroDAO.get(id);

        if (livro != null) {

        	livroDAO.remove(livro);

            response.status(200); // success
        	return id;
        } else {
            response.status(404); // 404 Not found
            return "Livro não encontrado.";
        }
	}

	public Object getAll(Request request, Response response) {
		StringBuffer returnValue = new StringBuffer("<livros type=\"array\">");
		for (Livro livro : livroDAO.getAll()) {
			returnValue.append("\n<livro>\n" + 
            		"\t<id>" + livro.getId() + "</id>\n" +
            		"\t<nome>" + livro.getNome() + "</nome>\n" +
            		"\t<preco>" + livro.getPreco() + "</preco>\n" +
            		"\t<quantidade>" + livro.getQuant() + "</quantidade>\n" +
            		"\t<fabricacao>" + livro.getDataPublicacao() + "</fabricacao>\n" +
            		"\t<genero>" + livro.getGenero() + "</genero>\n" +
            		
            		"</livro>\n");
		}
		returnValue.append("</livros>");
	    response.header("Content-Type", "application/xml");
	    response.header("Content-Encoding", "UTF-8");
		return returnValue.toString();
	}
}
