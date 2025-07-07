package model;
/**
 * Enum che rappresenta lo stato di un volo.
 */

public enum StatoVolo {
    /** Il volo è programmato e non ancora partito */

    PROGRAMMATO,
    /** Il volo è in ritardo */

    IN_RITARDO,
    /** Il volo è atterrato */

    ATTERRATO,
    /** Il volo è decollato */

    DECOLLATO,
    /** Il volo è stato cancellato */

    CANCELLATO
}
