package Item;
public class Gibi extends Item {

    private String autorGibi;
    private int totPagGibi;

    public Gibi(int idItem, String tituloItem, Disponibilidade disp, String autorGibi, int totPagGibi) {
        this.IdItem = idItem;
        this.tituloItem = tituloItem;
        this.dispItem = disp;
        this.autorGibi = autorGibi;
        this.totPagGibi = totPagGibi;
    }

    public String getAutorGibi() {
        return autorGibi;
    }

    public void setAutorGibi(String autorGibi) {
        this.autorGibi = autorGibi;
    }

    public int getTotPagGibi() {
        return totPagGibi;
    }

    public void setTotPagGibi(int totPagGibi) {
        this.totPagGibi = totPagGibi;
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
        return "Gibi, " + IdItem + ", " + tituloItem + ", " + dispItem + ", " + autorGibi + ", " + totPagGibi;
    }


}
