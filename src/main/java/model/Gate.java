package model;
/**
 * Rappresenta un gate di imbarco collegato a un volo, identificato da un identificativo numerico.
 */

public class Gate {
    /** Identificativo numerico del gate */

    private int idGate;
    /**
     * Costruttore completo.
     *
     * @param idGate identificativo del gate
     */

    public Gate(int idGate) {
        this.idGate = idGate;
    }
    /**
     * Costruttore vuoto.
     */

    public Gate(){}
    /**
     * Imposta l'identificativo del gate.
     *
     * @param idGate nuovo identificativo
     */

    public void setidGate(int idGate) {
        this.idGate = idGate;
    }
    /**
     * Restituisce l'identificativo del gate.
     *
     * @return id del gate
     */

    public int getidGate() {
        return idGate;
    }

}
