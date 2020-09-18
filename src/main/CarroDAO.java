package main;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joao_vitor
 */
public class CarroDAO {

    private Connection conn;
    private PreparedStatement stmt;
    private Statement st;
    private List<Carro> lista = new ArrayList<>();

    void close() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Erro de " + e.getMessage());
        }
    }

    public void inserir(Carro carro) {
        String sql = "INSERT INTO carro (placa,cor,descricao) VALUES (?,?,?)";
        try {
            conn = ConnectionFactory.getConexao();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, carro.getPlaca());
            stmt.setString(2, carro.getCor());
            stmt.setString(3, carro.getDescricao());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Erro de " + e.getMessage());
        } finally {
            close();
        }

    }

    public void alterar(Carro carro) {
        String sql = "UPDATE carro SET placa = ?, cor = ?, descricao= ? WHERE id = ?";
        try {
            conn = ConnectionFactory.getConexao();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, carro.getPlaca());
            stmt.setString(2, carro.getCor());
            stmt.setString(3, carro.getDescricao());
            stmt.setInt(4, carro.getId());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Erro de " + e.getMessage());
        } finally {
            close();
        }
    }

    public void excluir(int valor) {
        String sql = "DELETE FROM carro WHERE id = ?";
        try {
            conn = ConnectionFactory.getConexao();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, valor);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Erro de " + e.getMessage());
        } finally {
            close();
        }
    }

    public List<Carro> listaTodos() {
        String sql = "SELECT + FROM carro";
        try {
            conn = ConnectionFactory.getConexao();
            st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Carro carro = new Carro();
                carro.setId(rs.getInt("id"));
                carro.setPlaca(rs.getString("placa"));
                carro.setCor(rs.getString("cor"));
                carro.setDescricao(rs.getString("descricao"));
                lista.add(carro);
            }
            st.close();
        } catch (SQLException e) {
            System.out.println("Erro de " + e.getMessage());
        } finally {
            close();
        }
        return lista;
    }

    public List<Carro> listaPelaPlaca(String valor) {
        String sql = "SELECT + FROM carro WHERE placa LIKE ?";
        try {
            conn = ConnectionFactory.getConexao();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + valor + "%");
            ResultSet resultado = stmt.executeQuery();
            stmt.execute();
            while (resultado.next()) {
                Carro carro = new Carro();
                carro.setId(resultado.getInt("id"));
                carro.setPlaca(resultado.getString("placa"));
                carro.setCor(resultado.getString("cor"));
                carro.setDescricao(resultado.getString("descricao"));
                lista.add(carro);
            }
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Erro de " + e.getMessage());
        } finally {
            close();
        }
        return lista;
    }

}
