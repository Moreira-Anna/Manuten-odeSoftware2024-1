/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DÃO;

/**
 *
 * @author Ana moreira
 */
import Model.Disponivel;
import Model.Livro;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class DisponivelDao {

    public void create(Disponivel L) {

        try {
            JDBCUtil.init(new File(System.getProperty("user.dir") + "/src/DÃO/bancoD.properties"));
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(DisponivelDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        PreparedStatement stmt;

        try {
            stmt = JDBCUtil.getConnection().prepareStatement("INSERT INTO livro (nomeLivro, Editora , Genero) VALUES(?, ?, ?)");
            stmt.setString(1, L.getnomeLivro());
            stmt.setString(2, L.getEditora());
            stmt.setString(3, L.getGenero());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Livro já cadastrado.");
            System.out.println(ex);
        } finally {
            try {
                JDBCUtil.getConnection().close();
            } catch (SQLException ex) {
                Logger.getLogger(DisponivelDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public Livro read(Livro L) {
        Livro result = new Disponivel();

        try {
            try {
                JDBCUtil.init(new File(System.getProperty("user.dir") + "/DÃO/bancoD.properties"));
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(DisponivelDao.class.getName()).log(Level.SEVERE, null, ex);
            }

            PreparedStatement stmt;

            try {
                stmt = JDBCUtil.getConnection().prepareStatement("SELECT * from Livro L WHERE L.nomeLivro = ?");
                stmt.setString(3, L.getnomeLivro());

                result = (Livro) stmt.executeQuery();
            } catch (SQLException ex) {
                System.out.println(ex);
            } finally {
                JDBCUtil.getConnection().close();
            }
            return result;
        } catch (SQLException ex) {
            Logger.getLogger(DisponivelDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public void salvar(Disponivel d) {
        String sql = "INSERT INTO disponivel (nomelivro, editora, genero, prateleira, quantidade, corredor) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            try {
                JDBCUtil.init(new File(System.getProperty("user.dir") + "/src/DÃO/bancoD.properties"));
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(DisponivelDao.class.getName()).log(Level.SEVERE, null, ex);
            }

            try ( PreparedStatement preparedStatement = JDBCUtil.getConnection().prepareStatement(sql)) {
                preparedStatement.setString(1, d.getnomeLivro());
                preparedStatement.setString(2, d.getEditora());
                preparedStatement.setString(3, d.getGenero());
                preparedStatement.setString(4, d.getPrateleira());
                preparedStatement.setInt(5, d.getQuantidade());
                preparedStatement.setInt(6, d.getCorredor());

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
        }
    }

    public void remover(String nomeLivro) {
        String sql = "DELETE FROM disponivel WHERE nomelivro = ?";
        try {
            JDBCUtil.init(new File(System.getProperty("user.dir") + "/src/DÃO/bancoD.properties"));
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(DisponivelDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        try ( PreparedStatement preparedStatement = JDBCUtil.getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, nomeLivro);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public void atualizar(Disponivel d) {
        String sql = "UPDATE disponivel SET nomelivro = ?, editora = ?, genero = ?, prateleira = ?, quantidade = ?,corredor = ? WHERE nomelivro = ?";
        try {
            JDBCUtil.init(new File(System.getProperty("user.dir") + "/src/DÃO/bancoD.properties"));
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(DisponivelDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        try ( PreparedStatement preparedStatement = JDBCUtil.getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, d.getnomeLivro());
            preparedStatement.setString(2, d.getEditora());
            preparedStatement.setString(3, d.getGenero());
            preparedStatement.setString(4, d.getPrateleira());
            preparedStatement.setInt(5, d.getQuantidade());
            preparedStatement.setInt(6, d.getCorredor());
            preparedStatement.setString(7, d.getnomeLivro());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public Disponivel buscarPorNome(String nomeLivro) {
        String sql = "SELECT * FROM disponivel WHERE nomeLivro = ?";
        try {
            JDBCUtil.init(new File(System.getProperty("user.dir") + "/src/DÃO/bancoD.properties"));
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(DisponivelDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        try ( PreparedStatement preparedStatement = JDBCUtil.getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, nomeLivro);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return criarDisponivelAPartirDoResultSet(resultSet);
            }
        } catch (SQLException e) {
        }

        return null;
    }

    private Disponivel criarDisponivelAPartirDoResultSet(ResultSet resultSet) throws SQLException {
        Disponivel d = new Disponivel();
        d.setnomeLivro(resultSet.getString("nomeLivro"));
        d.setEditora(resultSet.getString("editora"));
        d.setGenero(resultSet.getString("Genero"));
        d.setPrateleira(resultSet.getString("prateleira"));
        d.setQuantidade(resultSet.getInt("quantidade"));
        d.setCorredor(resultSet.getInt("corredor"));

        return d;
    }

    public Disponivel addDisponivel(String nomeLivro, String editora, String genero, String prateleira, int quantidade, int corredor) {
        Disponivel disponivel = new Disponivel();
        disponivel.setnomeLivro(nomeLivro);
        disponivel.setEditora(editora);
        disponivel.setGenero(genero);
        disponivel.setPrateleira(prateleira);
        disponivel.setQuantidade(quantidade);
        disponivel.setCorredor(corredor);

        return disponivel;
    }
}
