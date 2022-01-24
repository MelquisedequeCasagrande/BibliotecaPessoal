package Item;
public abstract class Item {

	protected int IdItem;
	protected String tituloItem;
	protected Disponibilidade dispItem;

	abstract public int getIdItem();
	abstract public void setIdItem(int idItem);
	abstract public Disponibilidade getDisp();
	abstract public void setDisp(Disponibilidade d);
}
