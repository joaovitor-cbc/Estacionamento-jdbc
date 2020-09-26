package factoryconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author joao_vitor
 */
public class ConnectionFactory {

    public static Connection getConexao() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            final String url = "jdbc:mysql://localhost:3306/estacionamento?verifyServerCertificate=false&useSSL=true";
            final String usuario = "root";
            final String senha = "root";
            return DriverManager.getConnection(url, usuario, senha);
        } catch (SQLException e) {
            System.out.println("Erro de " + e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }

    public static void fecharConexao(Connection conn) {
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println("Erro de " + e.getMessage());
        }
    }
}
