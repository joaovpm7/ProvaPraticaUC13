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

    /**
     * @return the localizacao
     */
    public Endereco getLocalizacao() {
        return localizacao;
    }

    /**
     * @param localizacao the localizacao to set
     */
    public void setLocalizacao(Endereco localizacao) {
        this.localizacao = localizacao;
    }

    /**
     * @return the numCRM
     */
    public String getNumCRM() {
        return numCRM;
    }

    /**
     * @param numCRM the numCRM to set
     */
    public void setNumCRM(String numCRM) {
        this.numCRM = numCRM;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the sexo
     */
    public String getSexo() {
        return sexo;
    }

    /**
     * @param sexo the sexo to set
     */
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    /**
     * @return the cpf
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * @param cpf the cpf to set
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
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
     * @return the salario
     */
    public Double getSalario() {
        return salario;
    }

    /**
     * @param salario the salario to set
     */
    public void setSalario(Double salario) {
        this.salario = salario;
    }

    /**
     * @return the dataNascimento
     */
    public Date getDataNascimento() {
        return dataNascimento;
    }

    /**
     * @param dataNascimento the dataNascimento to set
     */
    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    /**
     * @return the ddd
     */
    public String getDdd() {
        return ddd;
    }

    /**
     * @param ddd the ddd to set
     */
    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    /**
     * @return the telefone
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * @param telefone the telefone to set
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the tipopessoa
     */
    public String getTipopessoa() {
        return tipopessoa;
    }

    /**
     * @param tipopessoa the tipopessoa to set
     */
    public void setTipopessoa(String tipopessoa) {
        this.tipopessoa = tipopessoa;
    }

    /**
     * @return the especialidade
     */
    public String getEspecialidade() {
        return especialidade;
    }

    /**
     * @param especialidade the especialidade to set
     */
    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    /**
     * @return the estadoCRM
     */
    public String getEstadoCRM() {
        return estadoCRM;
    }

    /**
     * @param estadoCRM the estadoCRM to set
     */
    public void setEstadoCRM(String estadoCRM) {
        this.estadoCRM = estadoCRM;
    }

    /**
     * @return the estadoCREA
     */
    public String getEstadoCREA() {
        return estadoCREA;
    }

    /**
     * @param estadoCREA the estadoCREA to set
     */
    public void setEstadoCREA(String estadoCREA) {
        this.estadoCREA = estadoCREA;
    }

    /**
     * @return the numCREA
     */
    public String getNumCREA() {
        return numCREA;
    }

    /**
     * @param numCREA the numCREA to set
     */
    public void setNumCREA(String numCREA) {
        this.numCREA = numCREA;
    }

    /**
     * @return the tituloProfissional
     */
    public String getTituloProfissional() {
        return tituloProfissional;
    }

    /**
     * @param tituloProfissional the tituloProfissional to set
     */
    public void setTituloProfissional(String tituloProfissional) {
        this.tituloProfissional = tituloProfissional;
    }

    /**
     * @return the dtcadastro
     */
    public Timestamp getDtcadastro() {
        return dtcadastro;
    }

    /**
     * @param dtcadastro the dtcadastro to set
     */
    public void setDtcadastro(Timestamp dtcadastro) {
        this.dtcadastro = dtcadastro;
    }

    private long id;
    private String cpf;
    private double salario;
    private Date dataNascimento;
    private String ddd;
    private String telefone;
    private String email;
    private String tipopessoa;
    private String especialidade;
    private String estadoCRM;
    private String estadoCREA;
    private String numCREA;
    private String numCRM;
    private String tituloProfissional;
    private Timestamp dtcadastro;
    private String sexo;
    private String nome;
    private Endereco localizacao;

    public boolean Cadastrar() {
        try {

            Connection conn = BancoDados.getConexao();
            String sql = "INSERT INTO tb_pessoa ";
            sql += " (nome, cpf, datanascimento, sexo, ddd, "
                    + "telefone, email, tipopessoa, salario, "
                    + "especialidade, estadocrm, numcrm, "
                    + "estadocrea, numcrea, tituloprofissional)";
            sql += " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

            PreparedStatement ps = conn.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
           
            ps.setString(1, this.getNome());
            ps.setString(2, this.getCpf());
            ps.setDate(3, this.getDataNascimento());
            ps.setString(4, this.getSexo());
            ps.setString(5, this.getDdd());
            ps.setString(6, this.getTelefone());
            ps.setString(7, this.getEmail());
            ps.setString(8, this.getTipopessoa());
            ps.setDouble(9, this.getSalario());
            ps.setString(10, this.getEspecialidade());
            ps.setString(11, this.getEstadoCRM());
            ps.setString(12, this.getNumCRM());
            ps.setString(13, this.getEstadoCREA());
            ps.setString(14, this.getNumCREA());
            ps.setString(15, this.getTituloProfissional());
         
 
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

    public List<Pessoa> ListarTodos() {
        try {

            Connection conn = BancoDados.getConexao();
            String sql = "SELECT * FROM tb_pessoa; ";
            PreparedStatement ps = conn.prepareStatement(sql);
            List<Pessoa> lista = new ArrayList();
            final ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                Pessoa c = new Pessoa();
                c.setId(rs.getInt("id"));
                c.setNome(rs.getString("nome"));
                c.setCpf(rs.getString("cpf"));
                c.setDataNascimento(rs.getDate("datanascimento"));
                c.setDdd(rs.getString("ddd"));
                c.setTelefone(rs.getString("telefone"));
                c.setEmail(rs.getString("email"));
                c.setSexo(rs.getString("sexo"));
                c.setTipopessoa(rs.getString("tipopessoa"));
                c.setSalario(rs.getDouble("salario"));
                c.setEspecialidade(rs.getString("especialidade"));
                c.setEstadoCRM(rs.getString("estadocrm"));
                c.setNumCRM(rs.getString("numcrm"));
                c.setEstadoCREA(rs.getString("estadocrea"));
                c.setNumCREA(rs.getString("numcrea"));
                c.setTituloProfissional(rs.getString("tituloprofissional"));
                c.setDtcadastro(rs.getTimestamp("dtcadastro"));
                lista.add(c);
            }
            return lista;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public boolean BuscarPorId(String id) {

        try {

            long idcli = Long.parseLong(id);

            Connection conn = BancoDados.getConexao();
            String sql = "SELECT * FROM tb_pessoa WHERE id = ?; ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, idcli);
            final ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                this.setId(rs.getInt("id"));
                this.setNome(rs.getString("nome"));
                this.setCpf(rs.getString("cpf"));
                this.setDataNascimento(rs.getDate("datanascimento"));
                this.setDdd(rs.getString("ddd"));
                this.setTelefone(rs.getString("telefone"));
                this.setEmail(rs.getString("email"));
                this.setSexo(rs.getString("sexo"));
                this.setTipopessoa(rs.getString("tipopessoa"));
                this.setSalario(rs.getDouble("salario"));
                this.setEspecialidade(rs.getString("especialidade"));
                this.setEstadoCRM(rs.getString("estadocrm"));
                this.setNumCRM(rs.getString("numcrm"));
                this.setEstadoCREA(rs.getString("estadocrea"));
                this.setNumCREA(rs.getString("numcrea"));
                this.setTituloProfissional(rs.getString("tipoprofissional"));
                this.setDtcadastro(rs.getTimestamp("dtcadastro"));

                return true;

            } else {
                return false;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean Atualizar() {
        try {
            Connection conn = BancoDados.getConexao(); //conectar com o bando de dados e enviar os dados salvos da classe Pessoa.
            String sql = "UPDATE tb_pessoa"
                    + " SET nome = ?, "
                    + " cpf = ?, "
                    + " datanascimento = ?, "
                    + " sexo = ?, "
                    + " ddd = ?, "
                    + " telefone = ?, "
                    + " email = ?, "
                    + " tipopessoa = ?, "
                    + " salario = ?, "
                    + " especialidade = ?, "
                    + " estadocrm = ?, "
                    + " numcrm = ?, "
                    + " estadocrea = ?, "
                    + " numcrea = ?, "
                    + " tituloprofissional = ? "
                    + " WHERE id = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, this.getNome());
            ps.setString(2, this.getCpf());
            ps.setDate(3, this.getDataNascimento());
            ps.setString(4, this.getSexo());
            ps.setString(5, this.getDdd());
            ps.setString(6, this.getTelefone());
            ps.setString(7, this.getEmail());
            ps.setString(8, this.getTipopessoa());
            ps.setDouble(9, this.getSalario());
            ps.setString(10, this.getEspecialidade());
            ps.setString(11, this.getEstadoCRM());
            ps.setString(12, this.getNumCRM());
            ps.setString(13, this.getEstadoCREA());
            ps.setString(14, this.getNumCREA());
            ps.setString(15, this.getTituloProfissional());
            ps.setLong(16, this.getId());
            int linhasafetadas = ps.executeUpdate();
            if (linhasafetadas > 0) {
                System.out.println("atualizou!");
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
            return false;
        }
    }

    public boolean Excluir(int id) {
        try {
            Connection conn = BancoDados.getConexao(); //conectar com o bando de dados e enviar os dados salvos da classe Pessoa.
            String sql = "DELETE FROM tb_pessoa WHERE id = ?; ";
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

    public void setId(Endereco us) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
