package Amigo;
public class Amigo {
	
	private int idAmigo;
	private String nomeAmigo;

	public Amigo(String nomeAmigo) {
		super();
		this.idAmigo = 0;
		this.nomeAmigo = nomeAmigo;
	}
	//sobreposição da função amigo
	public Amigo(int idAmigo, String nomeAmigo){
		this.nomeAmigo = nomeAmigo ;
		this.idAmigo = idAmigo;
	}

	public int getIdAmigo() {
		return idAmigo;
	}

	public void setIdAmigo(int idAmigo) {
		this.idAmigo = idAmigo;
	}

	public String getNomeAmigo() {
		return nomeAmigo;
	}

	public void setNomeAmigo(String nomeAmigo) {
		this.nomeAmigo = nomeAmigo;
	}

	@Override
	public String toString() {
		return idAmigo + ", " + nomeAmigo;
	}

}
