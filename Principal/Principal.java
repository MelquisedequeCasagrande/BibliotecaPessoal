package Principal;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import Amigo.*;
import Emprestimo.*;
import Item.*;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
	private static Biblioteca bib = new Biblioteca("Biblioteca Pessoal");
	private static ListaEmprestimos emprestimos = new ListaEmprestimos();
	private static ListaAmigos amigos = new ListaAmigos();
	private static Scanner teclado = new Scanner(System.in);

	public static void main(String[] args) {

		int opcao;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		//abrimos nosso arquivo para ter acesso aos dados pre cadastrados
		abrirArquivo("DadosBiblioteca.txt");
		abrirArquivo("DadosAmigos.txt");
		abrirArquivo("DadosEmprestimos.txt");

		try {
			do {
				System.out.println("\n:: Menu");
				System.out.println("1 - Cadastrar item");
				System.out.println("2 - Cadastrar amigo");
				System.out.println("3 - Emprestar");
				System.out.println("4 - Devolver");
				System.out.println("5 - Listar emprestimos atuais");
				System.out.println("6 - Listar histórico de empréstimo");
				System.out.println("7 - Listar biblioteca");
				System.out.println("8 - Alterar estado");
				System.out.println("0 - Sair");
				System.out.println("\n opção?");
				opcao = teclado.nextInt();
				teclado.nextLine();

				switch (opcao) {
					case 0:
						limpaConsole();
						System.out.println("\n Obrigado por usar o sistema");
						return;

					case 1:
						limpaConsole();
						System.out.println("Qual o tipo do item:");
						System.out.println("1 - Livro");
						System.out.println("2 - Manga");
						System.out.println("3 - Gibi");
						cadastrarItem(teclado.nextInt());
						teclado.nextLine();
						break;

					case 2:
						limpaConsole();
						cadastrarAmigo();
						break;
					case 3:
						limpaConsole();
						listarHistoricodeEmprestimo();
						emprestar();
						break;

					case 4:
						limpaConsole();
						devolver();
						break;

					case 5:
						limpaConsole();
						listarEmprestimosAtuais();
						break;

					case 6:
						limpaConsole();
						listarHistoricodeEmprestimo();
						break;

					case 7:
						limpaConsole();
						listarBiblioteca();
						break;

					case 8:
						limpaConsole();
						alterarEstado();
						break;
				}
			}while(true);
		}
		catch (Exception e){
			System.out.println(e.getMessage());
		}
		finally {
			teclado.close();
		}
		teclado.close();
	}

	// funcao utilizada para cadastrar itens
	public static void cadastrarItem(int tipo) throws Exception{
		Item item;
		String titulo;
		String autor;
		int totalPag;
		//teste para ver se o usuario digitou um valor valido
		if (tipo < 1 || tipo > 3){
			throw new IllegalArgumentException("Invalid argument");
		}
		//Leitura do teclado
		System.out.println("Titulo: ");
		teclado.nextLine();
		titulo = teclado.nextLine();
		System.out.println("Autor: ");
		autor = teclado.nextLine();
		System.out.println("Total de paginas: ");
		totalPag = teclado.nextInt();
		teclado.nextLine();
		//adicionando 1 no id do item
		int id = bib.getAlItem().size() + 1;

		//testando se o usuario não colocou nenhuma string vazia ou deixou o numero de pag igual a 5
		if (titulo.equals("") || autor.equals("") || totalPag == 0){
			throw new IllegalStateException("Invalid input");
		}
		//adiciona o item em sua devida Array
		//inicializamos a disponibilidade sempre em DISPONIVEL
		if (tipo == 1){
			item = new Livro( id, titulo, Disponibilidade.DISPONIVEL, autor, totalPag);
		}
		else if (tipo == 2){
			item = new Manga( id, titulo, Disponibilidade.DISPONIVEL, autor, totalPag);
		}
		else {
			item = new Gibi( id, titulo, Disponibilidade.DISPONIVEL, autor, totalPag);
		}

		bib.addItem(item);//adicionamos na biblioteca
		salvar();//chamamos a funcao salvar para salvar no arquivo txt
	}

	//funcao responsavel por cadastrar amigo
	public static void cadastrarAmigo() throws Exception{
		int idAmigo;
		String nome;
		Amigo amigo;

		System.out.println("Nome: ");
		nome = teclado.nextLine();


		amigos.addAmigo(nome);
		salvar();
	}

	//funcao responsavel por emprestar o livro
	public static void emprestar() throws Exception{
		int emp;
		int iD;
		//gera uma arraylist com apenas o itens disponiveis da nossa biblioteca
		ArrayList<Item> items = bib.getAlItem()
				.stream()
				.filter(x -> x.getDisp() == Disponibilidade.DISPONIVEL)
				.collect(Collectors.toCollection(ArrayList::new));

		amigos.getAlAmigos().forEach(System.out::println);
		System.out.print("Qual o seu ID: ");
		iD = teclado.nextInt();
		teclado.nextLine();

		//verificacao do id do amigo para ver se ele está autorizado a emprestar um livro
		if (iD < 1 || iD > amigos.getListSize()) {
			throw new IllegalStateException("Voce nao esta autorizado");
		}

		items.forEach(System.out::println);

		System.out.println("Qual você deseja emprestar?");
		emp = teclado.nextInt();
		teclado.nextLine();

		//verificacao se o livro que o amigo selecionou está na nossa biblioteca
		if (emp < 0 || emp > bib.getListSize()){
			throw new IllegalStateException("Não disponivel");
		}
		//loop resposavel por verificar o livro na biblioteca e alterar o seu estado para EMPRESTADO
		//concretizando assim o emprestimo
		for(Item i:items){
			if (i.getIdItem() == emp && i.getDisp() == Disponibilidade.DISPONIVEL){
				i.setDisp(Disponibilidade.EMPRESTADO);
				Emprestimo e = new Emprestimo(iD, emp, LocalDate.now(), LocalDate.now().plusMonths(2));
				emprestimos.addEmprestimo(e);
				System.out.println("Emprestado com sucesso!");
				salvar();
				return;
			}
		}
		System.out.println("Livro nao disponivel!");
	}

	//funcao responsavel por devolver o item a biblioteca
	public static void devolver() throws Exception{
		int iD;
		int iDItem;

		System.out.println("qual o seu ID: ");
		iD = teclado.nextInt();
		teclado.nextLine();

		//gera um arraylist apenas com os itens emprestados pelo id que foi fornecido acima
		ArrayList<Emprestimo> emprestados = emprestimos.getAlEmprestimos()
				.stream()
				.filter(x -> x.getIdAmigo() == iD && x.getDataDevolucao().isAfter(LocalDate.now()))
				.collect(Collectors.toCollection(ArrayList::new));

		//verifica se a lista gerada tem algum item
		if (emprestados.size() < 1){
			throw new IllegalStateException("Nao a item emprestados");
		}

		emprestados.forEach(System.out::println);

		System.out.println("Qual o ID do item que voce deseja devolver?");
		iDItem = teclado.nextInt();
		teclado.nextLine();

		//loop que altera o status do item para DISPONIVEL
		for (Emprestimo i:emprestados){
			if (i.getIdItem() == iDItem){
				Item item = bib.find(iDItem);
				if (item != null){
					i.setDataDevolucao(LocalDate.now());
					item.setDisp(Disponibilidade.DISPONIVEL);
					System.out.println("Devolvido com sucesso!");
					System.out.println("Aperte enter para continuar");
					teclado.nextLine();
					salvar();
					return;
				}
				throw new IllegalStateException("Ocorreu um erro ao devolver o livro!");
			}
		}

		throw new IllegalStateException("Livro nao encontrado");

	}

	//funcao responsavel por listar os emprestimos atuais
	public static void listarEmprestimosAtuais() throws Exception{
		//verificacao se a lista nao está vazia
		if (emprestimos.getListSize() < 1){
			throw new IllegalStateException("Não a emprestimos a serem listados!");
		}

		//gera um arraylist apenas com os que estão atualmente emprestados
		ArrayList<Emprestimo> emp = emprestimos.getAlEmprestimos()
				.stream()
				.filter(x -> x.getDataDevolucao().isAfter(LocalDate.now()))
				.collect(Collectors.toCollection(ArrayList::new));
		System.out.println("emprestimos atuais:");
		emp.forEach(System.out::println);
		System.out.println("Aperte enter para continuar");
		teclado.nextLine();
	}

	public static void listarHistoricodeEmprestimo(){
		System.out.println("lista de emprestados");
		emprestimos.getAlEmprestimos().forEach(System.out::println);
		System.out.println("Aperte enter para continuar");
		teclado.nextLine();	}

	public static void listarBiblioteca(){
		System.out.println("lista de itens da biblioteca");
		bib.getAlItem().forEach(System.out::println);
		System.out.println("Aperte enter para continuar");
		teclado.nextLine();
	}

	//funcao que altera o estado do item
	public static void alterarEstado(){
		int iD;
		int opcao;

		System.out.println("Qual o ID do item: ");
		iD = teclado.nextInt();

		if (iD < 1 || iD > bib.getListSize()){
			throw new IllegalStateException("Nao possei esse item");
		}

		System.out.println("Qual a estado do item?");
		System.out.println("1 - Consulta local");
		System.out.println("2 - Danificado");
		System.out.println("3 - Extraviado");
		opcao = teclado.nextInt();
		teclado.nextLine();

		switch (opcao){
			case 1:
				Item item = bib.find(iD);
				if (item.getDisp() == Disponibilidade.DISPONIVEL) {
					System.out.println("Livro disponivel para consulta local!");
					item.setDisp(Disponibilidade.CONSULTALOCAL);
					System.out.println("Aperte enter para continuar");
					teclado.nextLine();
				}
				else{
					System.out.println("livro indisponivel!");
				}
				break;

			case 2:
				bib.find(iD).setDisp(Disponibilidade.DANIFICADO);
				System.out.println("Estado alterado para danificado");
				System.out.println("Aperte enter para continuar");
				teclado.nextLine();
				break;

			case 3:
				bib.find(iD).setDisp(Disponibilidade.EXTRAVIADO);
				System.out.println("Estado alterado para extraviado");
				System.out.println("Aperte enter para continuar");
				teclado.nextLine();
				break;
		}
	}

	//funcao que salva os dados nos arquivos txt
	public static void salvar(){
		String pathAmigos = "DadosAmigos.txt";
		String pathEmprestimos = "DadosEmprestimos.txt";
		String pathBiblioteca = "DadosBiblioteca.txt";
		escrever(pathAmigos, amigos);
		escrever(pathBiblioteca, bib);
		escrever(pathEmprestimos, emprestimos);
	}

	//funcao que escreve os dados nos arquivos
	public static void escrever(String path, Object o){
		try (BufferedWriter br = new BufferedWriter(new FileWriter(path))) {
			br.write(o.toString());
			br.newLine();
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}

	//funcao responsavel por abrir o arquivo r
	public static void abrirArquivo(String path){
		if (path != "" || path == "\n"){
			String linha;

			try (BufferedReader br = new BufferedReader(new FileReader(path))){
				linha = br.readLine();
				while (linha != null){
					if (path.contains("Biblioteca")) {
						carregarLivro(linha);
					}
					else if (path.contains("Amigos")) {
						carregarAmigos(linha);
					}
					else if (path.contains("Emprestimos")) {
						carregarEmprestimos(linha);
					}
					linha = br.readLine();
				}
			}
			catch (IOException e){
				//e.printStackTrace();
				e.getMessage();
			}
		}
	}

	//funcao responsavel por carregar os livros cadastrados no banco de dados
	public static void carregarLivro(String cLivro){
		if (cLivro != "" || cLivro == "\n"){
			String []vet = cLivro.split(", ");
			int id = Integer.parseInt(vet[1]);
			String titulo = vet[2];
			Disponibilidade disp = Disponibilidade.valueOf(vet[3]);
			String autor = vet[4];
			int totPag = Integer.parseInt(vet[5]);
			Item item = null;
			switch (vet[0]){
				case "Livro":
					item = new Livro(id, titulo, disp, autor, totPag);
					break;

				case "Gibi":
					item = new Gibi(id, titulo, disp, autor, totPag);
					break;

				case "Manga":
					item = new Manga(id, titulo, disp, autor, totPag);
			}
			bib.addItem(item);
		}
	}

	//funcao responsavel por carregar os amigos cadastrados no banco de dados
	public static void carregarAmigos(String aAmigos){
		if (aAmigos != "" || aAmigos == "\n"){
			String []vet = aAmigos.split(", ");
			int id = Integer.parseInt(vet[0]);
			String nome = vet[1];

			amigos.addAmigo(new Amigo(id, nome));
		}
	}

	//funcao responsavel por carregar os emprestismo cadastrados  no bancod e de dados
	public static void carregarEmprestimos(String eEmprestimos){
		//if (eEmprestimos != "" || eEmprestimos == "\n"){
			String []vet = eEmprestimos.split(", ");
			int idAmigo = Integer.parseInt(vet[0]);
			int idItem = Integer.parseInt(vet[1]);
			LocalDate dataEmprestimo = LocalDate.parse(vet[2]);
			LocalDate dataDevolucao = LocalDate.parse(vet[3]);

			emprestimos.addEmprestimo(new Emprestimo(idAmigo, idItem, dataEmprestimo, dataDevolucao));
	}

	//"limpa" o nosso console
	public static void limpaConsole(){
		for (int i = 0; i < 20; i++)
			System.out.println();
	}
}

