package Item;
public class Livro extends Item {

	private String autorLivro;
	private int totPagLivro;

	public Livro(int idItem, String tituloItem, Disponibilidade disp, String autorLivro, int totPagLivro) {
		this.IdItem = idItem;
		this.tituloItem = tituloItem;
		this.dispItem = disp;
		this.autorLivro = autorLivro;
		this.totPagLivro = totPagLivro;
	}

	public String getAutorLivro() {
		return autorLivro;
	}

	public void setAutorLivro(String autorLivro) {
		this.autorLivro = autorLivro;
	}

	public int getTotPagLivro() {
		return totPagLivro;
	}

	public void setTotPagLivro(int totPagLivro) {
		this.totPagLivro = totPagLivro;
	}

	@Override
	public int getIdItem() {
		return IdItem;
	}

	@Override
	public void setIdItem(int idItem) {
		IdItem = IdItem;
	}

	@Override
	public Disponibilidade getDisp() {
		return dispItem;
	}

	@Override
	public void setDisp(Disponibilidade d) {
		dispItem = d;
	}

	@Override
	public String toString() {
		return "Livro, " + IdItem + ", " + tituloItem + ", " + dispItem + ", " + autorLivro + ", " + totPagLivro;
	}


}
