/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import util.BancoDados;

/**
 *
 * @author sala305b
 */
public class Pessoa {

    private Long id;
    private Endereco localizacao;
    private String nome;
    private String tipopessoa;
    private String telefone;
    private String ddd;
    private String email;
    private Double salario;
    private String sexo;
    private Date datanascimento;
    private String cpf;
    private String especialidade;
    private String numcrm;
    private String estadocrm;
    private String estadocrea;
    private String numcrea;
    private String tituloprofissional;
    private Timestamp datacadastro;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public String getNumCRM() {
        return numcrm;
    }

    public void setNumCRM(String numCRM) {
        this.numcrm = numCRM;
    }

    public String getEstadoCRM() {
        return estadocrm;
    }

    public void setEstadoCRM(String estadoCRM) {
        this.estadocrm = estadoCRM;
    }

    public String getEstadoCREA() {
        return estadocrea;
    }

    public void setEstadoCREA(String estadoCREA) {
        this.estadocrea = estadoCREA;
    }

    public String getNumCREA() {
        return numcrea;
    }

    public void setNumCREA(String numCREA) {
        this.numcrea = numCREA;
    }

    public String getTituloprofissional() {
        return tituloprofissional;
    }

    public void setTituloprofissional(String tituloprofissional) {
        this.tituloprofissional = tituloprofissional;
    }

    public Date getDatanascimento() {
        return datanascimento;
    }

    public void setDatanascimento(Date datanascimento) {
        this.datanascimento = datanascimento;
    }

    public Timestamp getDatacadastro() {
        return datacadastro;
    }

    public void setDatacadastro(Timestamp datacadastro) {
        this.datacadastro = datacadastro;
    }

    public String getTipopessoa() {
        return tipopessoa;
    }

    public void setTipopessoa(String tipopessoa) {
        this.tipopessoa = tipopessoa;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Endereco getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(Endereco localizacao) {
        this.localizacao = localizacao;
    }

    public boolean BuscarPorId() {
        try {
            Connection conn = BancoDados.getConexao();
            String sql = "SELECT * FROM tb_pessoa WHERE id = ?; ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, this.getId());
            final ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                this.setId(rs.getLong("id"));
                this.setNome(rs.getString("nome"));
                this.setTipopessoa(rs.getString("tipopessoa"));
                this.setTelefone(rs.getString("telefone"));
                this.setDdd(rs.getString("ddd"));
                this.setEmail(rs.getString("email"));
                this.setSalario(rs.getDouble("salario"));
                this.setSexo(rs.getString("sexo"));
                this.setDatanascimento(rs.getDate("datanascimento"));
                this.setCpf(rs.getString("cpf"));
                this.setEspecialidade(rs.getString("especialidade"));
                this.setNumCRM(rs.getString("numcrm"));
                this.setNumCREA(rs.getString("numcrea"));
                this.setEstadoCRM(rs.getString("estadocrm"));
                this.setEstadoCREA(rs.getString("estadocrea"));
                this.setTituloprofissional(rs.getString("tituloprofissional"));
                this.setDatacadastro(rs.getTimestamp("datacadastro"));
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
            String sql = "INSERT INTO tb_pessoa ";
            sql += " (nome, tipopessoa, telefone, ddd, "
                    + "email, salario, sexo, datanascimento, cpf, especialidade, numcrm,"
                    + "estadocrm, estadocrea, tituloprofissional) ";
            sql += " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
            PreparedStatement ps = conn.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            //nome, tipopessoa, telefone, ddd, email, salario, sexo, datanascimento, cpf, especialidade, numcrm, numcrea, estadocrm, estadocrea, tituloprofissional, datacadastro
            ps.setString(1, this.getNome());
            ps.setString(2, this.getTipopessoa());
            ps.setString(3, this.getTelefone());
            ps.setString(4, this.getDdd());
            ps.setString(5, this.getEmail());
            ps.setDouble(6, this.getSalario());
            ps.setString(7, this.getSexo());
            ps.setDate(8, this.getDatanascimento());
            ps.setString(9, this.getCpf());
            ps.setString(10, this.getEspecialidade());
            ps.setString(11, this.getNumCRM());
            ps.setString(12, this.getEstadoCRM());
            ps.setString(13, this.getEstadoCREA());
            ps.setString(14, this.getTituloprofissional());
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
            String sql = "DELETE FROM tb_pessoa WHERE id = ?;";
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

    public List<Pessoa> ListarTodos() {
        try {

            Connection conn = BancoDados.getConexao();
            String sql = "SELECT * FROM tb_pessoa";
            PreparedStatement ps = conn.prepareStatement(sql);
            List<Pessoa> lista = new ArrayList();
            final ResultSet rs = ps.executeQuery();
            while (rs.next()) {
//id, nome, tipopessoa, telefone, ddd, email, salario, sexo, datanascimento, cpf, especialidade, numcrm, numcrea, estadocrm, estadocrea, tituloprofissional, datacadastro
                Pessoa p = new Pessoa();
                p.setId(rs.getLong("id"));
                p.setNome(rs.getString("nome"));
                p.setTipopessoa(rs.getString("tipopessoa"));
                p.setTelefone(rs.getString("telefone"));
                p.setDdd(rs.getString("ddd"));
                p.setEmail(rs.getString("email"));
                p.setSalario(rs.getDouble("salario"));
                p.setSexo(rs.getString("sexo"));
                p.setDatanascimento(rs.getDate("datanascimento"));
                p.setCpf(rs.getString("cpf"));
                p.setEspecialidade(rs.getString("especialidade"));
                p.setNumCRM(rs.getString("numcrm"));
                p.setNumCREA(rs.getString("numcrea"));
                p.setEstadoCRM(rs.getString("estadocrm"));
                p.setEstadoCREA(rs.getString("estadocrea"));
                p.setTituloprofissional(rs.getString("tituloprofissional"));
                p.setDatacadastro(rs.getTimestamp("datacadastro"));
                lista.add(p);
            }
            return lista;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
