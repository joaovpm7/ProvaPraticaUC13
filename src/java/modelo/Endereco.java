/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.BancoDados;

/**
 *
 * @author sala305b
 */
public class Endereco {

    public long getIdpessoa() {
        return idpessoa;
    }

    public void setIdpessoa(long idpessoa) {
        this.idpessoa = idpessoa;
    }

    /**
     * @return the cidade
     */
    public String getCidade() {
        return cidade;
    }

    /**
     * @param cidade the cidade to set
     */
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    /**
     * @return the bairro
     */
    public String getBairro() {
        return bairro;
    }

    /**
     * @param bairro the bairro to set
     */
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the cep
     */
    public String getCep() {
        return cep;
    }

    /**
     * @param cep the cep to set
     */
    public void setCep(String cep) {
        this.cep = cep;
    }

    /**
     * @return the complemento
     */
    public String getComplemento() {
        return complemento;
    }

    /**
     * @param complemento the complemento to set
     */
    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    /**
     * @return the logradouro
     */
    public String getLogradouro() {
        return logradouro;
    }

    /**
     * @param logradouro the logradouro to set
     */
    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    /**
     * @return the numero
     */
    public String getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * @return the uf
     */
    public String getUf() {
        return uf;
    }

    /**
     * @param uf the uf to set
     */
    public void setUf(String uf) {
        this.uf = uf;
    }

    private long id;
    private long idpessoa;
    private String cep;
    private String complemento;
    private String bairro;
    private String cidade;
    private String logradouro;
    private String numero;
    private String uf;

    public long Cadastrar() {
        try {

            Connection conn = BancoDados.getConexao();
            String sql = "INSERT INTO tb_endereco ";
            sql += "(bairro, cep, cidade, complemento,"
                    + " logradouro, numero, uf, idpessoa )";
            sql += " VALUES (?,?,?,?,?,?,?,?);";
            PreparedStatement ps = conn.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, this.getBairro());
            ps.setString(2, this.getCep());
            ps.setString(3, this.getCidade());
            ps.setString(4, this.getComplemento());
            ps.setString(5, this.getLogradouro());
            ps.setString(6, this.getNumero());
            ps.setString(7, this.getUf());
            ps.setLong(8, this.getIdpessoa());

            ps.setString(7, this.getUf());
            int linhasafetadas = ps.executeUpdate();
            if (linhasafetadas > 0) {
                final ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    final long lastId = rs.getLong(1);

                    this.setId(lastId);
                    System.out.println(
                            "O id do cliente é:" + lastId);

                    return lastId;
                } else {
                    return 0;
                }
            } else {
                return 0;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    public List<Endereco> ListarTodos() {
        try {

            Connection conn = BancoDados.getConexao();
            String sql = "SELECT * FROM tb_endereco; ";
            PreparedStatement ps = conn.prepareStatement(sql);
            List<Endereco> lista = new ArrayList();
            final ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                Endereco c = new Endereco();
                Pessoa p = new Pessoa();
                c.setId(rs.getLong("id"));
                c.setIdpessoa(rs.getLong("idpessoa"));
                c.setCep(rs.getString("cep"));
                c.setLogradouro(rs.getString("logradouro"));
                c.setNumero(rs.getString("numero"));
                c.setComplemento(rs.getString("complemento"));
                c.setBairro(rs.getString("bairro"));
                c.setCidade(rs.getString("cidade"));
                c.setUf(rs.getString("uf"));
                lista.add(c);
            }
            return lista;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public boolean BuscarPorId() {

        try {

            Connection conn = BancoDados.getConexao();
            String sql = "SELECT * FROM tb_endereco WHERE idpessoa = ?; ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, this.getIdpessoa());
            final ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                this.setId(rs.getLong("id"));
                this.setIdpessoa(rs.getLong("idpessoa"));
                this.setCep(rs.getString("cep"));
                this.setLogradouro(rs.getString("logradouro"));
                this.setNumero(rs.getString("numero"));
                this.setComplemento(rs.getString("complemento"));
                this.setBairro(rs.getString("bairro"));
                this.setCidade(rs.getString("cidade"));
                this.setUf(rs.getString("uf"));

                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean Excluir(int id) {
        try {
            Connection conn = BancoDados.getConexao(); //conectar com o bando de dados e enviar os dados salvos da classe Contato.
            String sql = "DELETE FROM tb_endereco WHERE idPessoa = ?; ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, id);
            int linhasafetadas = ps.executeUpdate();
            if (linhasafetadas > 0) {
                System.out.println("Apagou!!");
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
            return false;
        }
    }

}
