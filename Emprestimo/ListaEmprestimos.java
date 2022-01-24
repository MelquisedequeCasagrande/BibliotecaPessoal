package Emprestimo;

import java.util.ArrayList;

public class ListaEmprestimos {

	private ArrayList<Emprestimo> alEmprestimos;

	public ListaEmprestimos() {
		super();
		this.alEmprestimos = new ArrayList<Emprestimo>();
	}

	public ArrayList<Emprestimo> getAlEmprestimos() {
		return alEmprestimos;
	}

	public void setAlEmprestimos(ArrayList<Emprestimo> alEmprestimos) {
		this.alEmprestimos = alEmprestimos;
	}

	public int getListSize(){
		return alEmprestimos.size();
	}

	//funcao responsavel por add os emprestimos na arraylist
	public void addEmprestimo(Emprestimo e){
		if (e != null){
			alEmprestimos.add(e);
		}
	}

	@Override
	public String toString() {
		var val = new String("");
		for (int i = 0; i < alEmprestimos.size()-1; i++){
			val += alEmprestimos.get(i) + "\n";
		}
		return val + alEmprestimos.get(alEmprestimos.size()-1);
	}
}
