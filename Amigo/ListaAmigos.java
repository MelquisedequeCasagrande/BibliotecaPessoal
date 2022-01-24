package Amigo;
import java.util.ArrayList;

public class ListaAmigos {

	private ArrayList<Amigo> alAmigos;
	
	public ListaAmigos() {
		super();
		this.alAmigos = new ArrayList<Amigo>();
	}
	//funcao responsavel por add o amigo na Arraylist
	public int addAmigo (String nome) {
		int idAmigo = alAmigos.size() + 1; 
		Amigo amigo = new Amigo(nome);
		amigo.setIdAmigo(idAmigo);
		alAmigos.add(amigo);
		return idAmigo;
	}

	public ArrayList<Amigo> getAlAmigos() {
		return alAmigos;
	}

	public void setAlAmigos(ArrayList<Amigo> alAmigos) {
		this.alAmigos = alAmigos;
	}

	public int getListSize(){
		return alAmigos.size();
	}

	public void addAmigo(Amigo amigo) {
		alAmigos.add(amigo);
	}

	@Override
	public String toString() {
		var val = new String("");
		for (int i = 0; i < alAmigos.size()-1; i++){
			val += alAmigos.get(i) + "\n";
		}
		return val + alAmigos.get(alAmigos.size()-1);
	}



}
