package model;
/**
 * Rappresenta un volo in partenza da un aeroporto di origine, includendo il gate di imbarco.
 * Estende la classe {@link Volo}.
 */

public class VoloOrigine extends Volo {
    /** Gate di imbarco assegnato al volo */

    private Gate imbarco;
    /**
     * Costruttore di default della classe VoloOrigine.
     */

    public VoloOrigine() {
        super();
    }
    /**
     * Restituisce il gate di imbarco.
     *
     * @return il gate di imbarco assegnato al volo
     */

    public Gate getImbarco() {
        return imbarco;
    }
    /**
     * Imposta il gate di imbarco.
     *
     * @param imbarco il gate di imbarco da assegnare al volo
     */

    public void setImbarco(Gate imbarco) {
        this.imbarco = imbarco;
    }
}