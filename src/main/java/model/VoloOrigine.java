package model;

public class VoloOrigine extends Volo {
    private Gate imbarco;

    public VoloOrigine() {
        super();
    }

    public Gate getImbarco() {
        return imbarco;
    }

    public void setImbarco(Gate imbarco) {
        this.imbarco = imbarco;
    }
}