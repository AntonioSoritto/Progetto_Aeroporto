package util;

import java.sql.*;
/**
 * Implementa il pattern Singleton per la gestione della connessione al database PostgreSQL.
 * Permette di ottenere un'unica istanza condivisa della connessione.
 */

public class ConnessioneDatabase {
    /** Istanza Singleton della classe */

    private static ConnessioneDatabase instance;
    /** Oggetto di connessione al database */

    public Connection connection = null;
    // Credenziali e informazioni di connessione (in un'app reale, valuta di proteggerle opportunamente)

    private String nome = "postgres";
    private String password = "Giovanna.1991!";
    private String url = "jdbc:postgresql://localhost:5432/Aeroporto_OO";
    private String driver = "org.postgresql.Driver";
    /**
     * Costruttore che inizializza la connessione al database.
     *
     * @throws SQLException eccezione generata in caso di errore di connessione
     */

    public ConnessioneDatabase() throws SQLException {
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, nome, password);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    /**
     * Restituisce l'istanza Singleton della connessione.
     *
     * @return istanza della connessione al database
     * @throws SQLException se si verifica un errore nella connessione
     */

    public static ConnessioneDatabase getInstance() throws SQLException {
        if (instance == null || instance.connection.isClosed()) {
            instance = new ConnessioneDatabase();
        }
        return instance;
    }
}