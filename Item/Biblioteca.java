package Item;
import java.util.ArrayList;

public class Biblioteca {

	private String nomeBib;
	private ArrayList<Item> alItem;

	public Biblioteca(String nomeBib) {
		this.nomeBib = nomeBib;
		this.alItem = new ArrayList<Item>(); 
	}

	public String getNomeBib() {
		return nomeBib;
	}

	public void setNomeBib(String nomeBib) {
		this.nomeBib = nomeBib;
	}

	public ArrayList<Item> getAlItem() {
		return alItem;
	}

	public void setAlItem(ArrayList<Item> alItem) {
		this.alItem = alItem;
	}

	@Override
	public String toString() {
		var val = new String("");
		for (int i = 0; i < alItem.size()-1; i++){
			val += alItem.get(i) + "\n";
		}
		return val + alItem.get(alItem.size()-1);
	}
	public void addItem (Item item){

		alItem.add(item);
	}
	public int getListSize(){
		return alItem.size();
	}
	public Item find(int i){
		for (Item item: alItem){
			if (item.getIdItem() == i){
				return item;
			}
		}
	return null;
	}
}
