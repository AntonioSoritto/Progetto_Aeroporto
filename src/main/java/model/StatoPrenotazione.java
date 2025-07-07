package model;
/**
 * Enum che rappresenta lo stato di una prenotazione.
 */

public enum StatoPrenotazione {
    /** La prenotazione è stata confermata */

    CONFERMATA,
    /** La prenotazione è in attesa di conferma o elaborazione */

    IN_ATTESA,
    /** La prenotazione è stata cancellata */

    CANCELLATA
}
