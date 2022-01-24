package Item;
public class Manga extends Item {

    private String autorManga;
    private int totPagManga;

    public Manga(int idItem, String tituloItem, Disponibilidade disp, String autorManga, int totPagManga) {
        this.IdItem = idItem;
        this.tituloItem = tituloItem;
        this.dispItem = disp;
        this.autorManga = autorManga;
        this.totPagManga = totPagManga;
    }

    public String getAutorManga() {
        return autorManga;
    }

    public void setAutorManga(String autorManga) {
        this.autorManga = autorManga;
    }

    public int getTotPagManga() {
        return totPagManga;
    }

    public void setTotPagManga(int totPagManga) {
        this.totPagManga = totPagManga;
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
        return "Manga, " + IdItem + ", " + tituloItem + ", " + dispItem + ", " + autorManga + ", " + totPagManga;
    }


}
