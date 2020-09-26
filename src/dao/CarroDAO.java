package dao;

import factoryconnection.ConnectionFactory;
import model.Carro;
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


    void close(Connection con, Statement stm) {
        try {
            if(stm != null){
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("Erro de " + e.getMessage());
        }
    }

    public Long inserir(Carro carro) {
        String sql = "INSERT INTO carro (placa,cor,descricao) VALUES (?,?,?)";
        Long ultimoId = -1L;
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = ConnectionFactory.getConexao();
            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, carro.getPlaca());
            stmt.setString(2, carro.getCor());
            stmt.setString(3, carro.getDescricao());
            stmt.execute();
            ResultSet rs1 = stmt.getGeneratedKeys();
            if (rs1.next()) {
                ultimoId = rs1.getLong(1);
            }
            return ultimoId;
        } catch (SQLException e) {
            System.out.println("Erro de " + e.getMessage());
            return ultimoId;
        } finally {
            close(conn, stmt);
        }

    }

    public void alterar(Carro carro) {
        String sql = "UPDATE carro SET placa = ?, cor = ?, descricao= ? WHERE id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = ConnectionFactory.getConexao();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, carro.getPlaca());
            stmt.setString(2, carro.getCor());
            stmt.setString(3, carro.getDescricao());
            stmt.setInt(4, carro.getId());
            stmt.execute();
        } catch (SQLException e) {
            System.out.println("Erro de " + e.getMessage());
        } finally {
            close(conn,stmt);
        }
    }

    public void excluir(int valor) {
        String sql = "DELETE FROM carro WHERE id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = ConnectionFactory.getConexao();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, valor);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro de " + e.getMessage());
        } finally {
            close(conn,stmt);
        }
    }

    public List<Carro> listaTodos() {
        String sql = "SELECT * FROM carro";
        List<Carro> lista = new ArrayList<>();
        Connection conn = null;
        Statement st = null;
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
        } catch (SQLException e) {
            System.out.println("Erro de " + e.getMessage());
        } finally {
            close(conn,st);
        }
        return lista;
    }

    public List<Carro> listaPelaPlaca(String valor) {
        String sql = "SELECT * FROM carro WHERE placa LIKE ?";
        List<Carro> lista = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
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
        } catch (SQLException e) {
            System.out.println("Erro de " + e.getMessage());
        } finally {
            close(conn,stmt);
        }
        return lista;
    }
}
