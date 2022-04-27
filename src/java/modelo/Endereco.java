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

    private long id;
    private long idpessoa;
    private String numero;
    private String cidade;
    private String bairro;
    private String cep;
    private String complemento;
    private String logradouro;
    private String uf;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdpessoa() {
        return idpessoa;
    }

    public void setIdpessoa(long idpessoa) {
        this.idpessoa = idpessoa;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public boolean BuscarPorId() {
        try {
            Connection conn = BancoDados.getConexao();
            String sql = "SELECT * FROM tb_endereco WHERE id = ?; ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, this.getId());
            ps.setString(2, this.getNumero());
            ps.setString(3, this.getCidade());
            ps.setString(4, this.getBairro());
            ps.setString(5, this.getCep());
            ps.setString(6, this.getComplemento());
            ps.setString(7, this.getLogradouro());
            ps.setString(8, this.getUf());
            final ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                this.setId(rs.getLong("id"));
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean Cadastrar() {
        try {

            Connection conn = BancoDados.getConexao();
            String sql = "INSERT INTO tb_endereco ";
            sql += " (numero, cidade, bairro, cep, complemento, logradouro, uf) ";
            sql += " VALUES (?,?,?,?,?,?,?);";
            PreparedStatement ps = conn.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, this.getNumero());
            ps.setString(2, this.getCidade());
            ps.setString(3, this.getBairro());
            ps.setString(4, this.getCep());
            ps.setString(5, this.getComplemento());
            ps.setString(6, this.getLogradouro());
            ps.setString(7, this.getUf());
            int linhasafetadas = ps.executeUpdate();
            if (linhasafetadas > 0) {
                final ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    final long lastId = rs.getLong(1);
                    System.out.println("O numero do id é:"
                            + lastId);
                    this.setId(lastId);
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean Deletar() {
        try {
            Connection conn = BancoDados.getConexao(); //conectar com o bando de dados e enviar os dados salvos da classe Contato.
            String sql = "DELETE FROM tb_endereco WHERE id = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setLong(1, this.getId());
            int linhasafetadas = ps.executeUpdate();
            if (linhasafetadas > 0) {
                System.out.println("APAGOU!");
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
            return false;
        }
    }

    public List<Endereco> ListarTodos() {
        try {
            Connection conn = BancoDados.getConexao();
            String sql = "SELECT * FROM tb_endereco";
            PreparedStatement ps = conn.prepareStatement(sql);
            List<Endereco> lista = new ArrayList();
            final ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Endereco e = new Endereco();
                e.setNumero(rs.getString("numero"));
                e.setCidade(rs.getString("cidade"));
                e.setBairro(rs.getString("bairro"));
                e.setCep(rs.getString("cep"));
                e.setComplemento(rs.getString("complemento"));
                e.setLogradouro(rs.getString("logradouro"));
                e.setUf(rs.getString("uf"));
                lista.add(e);
            }
            return lista;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
