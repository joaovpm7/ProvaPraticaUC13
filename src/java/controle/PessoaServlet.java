/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Endereco;
import modelo.Pessoa;

/**
 *
 * @author sala305b
 */
@WebServlet(name = "PessoaServlet", urlPatterns = {"/PessoaServlet"})
public class PessoaServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getParameter("acao") != null) {

            if (request.getParameter("acao").equals("cadastrar")) {

                String nome = request.getParameter("nome");
                String cpf = request.getParameter("cpf");
                String sexo = request.getParameter("sexo");
                String dddtelefone = request.getParameter("dddtelefone");
                String email = request.getParameter("email");
                String tipopessoa = request.getParameter("tipopessoa");
                double salario = (Double.parseDouble(request.getParameter("salario")));
                Date dtnascimento = Date.valueOf(
                        request.getParameter("datanascimento"));
                String especialidade_titulo = request.getParameter("especialidade_titulo");
                String numcrm_crea = request.getParameter("numcrm_crea");
                String estadocrm_crea = request.getParameter("estadocrm_crea");

                Pessoa p = new Pessoa();
                p.setNome(nome);
                p.setTipopessoa(tipopessoa);

                if (tipopessoa.equals("M")) {
                    p.setEspecialidade(especialidade_titulo);
                    p.setNumCRM(numcrm_crea);
                    p.setEstadoCRM(estadocrm_crea);

                } else if (tipopessoa.equals("E")) {
                    p.setTituloProfissional(especialidade_titulo);
                    p.setNumCREA(numcrm_crea);
                    p.setEstadoCREA(estadocrm_crea);

                } else {
                    response.sendRedirect("index.jsp?erro=cadastrar");
                }

                p.setSalario(salario);
                p.setCpf(cpf);
                p.setSexo(sexo);
                p.setDataNascimento(dtnascimento);

                String dddTelLimpo = dddtelefone.replace(" ", "")
                        .replace("-", "")
                        .replace("(", "")
                        .replace(")", "");
                String ddd = dddTelLimpo.substring(0, 2);
                String telefone = "";
                if (dddTelLimpo.length() == 10) {
                    telefone = dddTelLimpo.substring(2, 6)
                            + "-" + dddTelLimpo.substring(6);
                } else {
                    telefone = dddTelLimpo.substring(2, 7)
                            + "-" + dddTelLimpo.substring(7);
                }
                p.setDdd(ddd);
                p.setTelefone(telefone);
                p.setEmail(email);

                String cep = request.getParameter("cep");
                String uf = request.getParameter("uf");
                String logradouro = request.getParameter("logradouro");
                String complemento = request.getParameter("complemento");
                String numero = request.getParameter("numero");
                String bairro = request.getParameter("bairro");
                String cidade = request.getParameter("cidade");

                Endereco end = new Endereco();
                end.setCep(cep);
                end.setLogradouro(logradouro);
                end.setNumero(numero);
                end.setComplemento(complemento);
                end.setBairro(bairro);
                end.setCidade(cidade);
                end.setUf(uf);

                p.setLocalizacao(end);

                boolean cadastrou = p.Cadastrar();
                if (cadastrou == true) {

                    p.getLocalizacao().setIdpessoa(p.getId());
                    long cadastrouEnd = p.getLocalizacao().Cadastrar();

                    if (cadastrouEnd > 0) {
                        response.sendRedirect("listar.jsp");
                    } else {
                        response.sendRedirect("index.jsp?erro=cadastrarEndereco");
                    }

                } else {
                    response.sendRedirect("index.jsp?erro=cadastrarPessoa");
                }

            } else if (request.getParameter("acao").equals("apagar")) {
                int id = Integer.parseInt(request.getParameter("idPessoa"));
                Pessoa p = new Pessoa();
                Endereco e = new Endereco();
                boolean apagouE = e.Excluir(id);

                if (apagouE) {
                    boolean apagouP = p.Excluir(id);
                    if (apagouP) {
                        response.sendRedirect("listar.jsp");
                    }
                } 
            } 
        } else if (request.getParameter("acao").equals("editar")) {

            long id = Long.parseLong(request.getParameter("id"));
            String idpessoa = request.getParameter("idpessoa");
            String nome = request.getParameter("nome");
            String cpf = request.getParameter("cpf");
            String sexo = request.getParameter("sexo");
            String dddtelefone = request.getParameter("dddtelefone");
            String email = request.getParameter("email");
            String cep = request.getParameter("cep");

            String tipopessoa = request.getParameter("tipopessoa");
            if (tipopessoa.equals("M")) {
                String especialidade = request.getParameter("especialidade_titulo");
                String numcrm = request.getParameter("numcrm_crea");
                String estadocrm = request.getParameter("estadocrm_crea");
            } else {
                String tituloprofissional = request.getParameter("especialidade_titulo");
                String estadocrea = request.getParameter("estadocrm_crea");
                String numcrea = request.getParameter("numcrm_crea");
            }
            String logradouro = request.getParameter("logradouro");
            String complemento = request.getParameter("complemento");
            String numero = request.getParameter("numero");
            String bairro = request.getParameter("bairro");
            String cidade = request.getParameter("cidade");
            Double salario = (Double.parseDouble(request.getParameter("salario")));
            String uf = request.getParameter("uf");
            Date dtnascimento = Date.valueOf(
                    request.getParameter("datanascimento"));

            Pessoa p = new Pessoa();
            Endereco end = new Endereco();
            p.setNome(nome);
            p.setSalario(salario);
            p.setCpf(cpf);
            p.setSexo(sexo);

            p.setDataNascimento(dtnascimento);
            String dddTelLimpo = dddtelefone.replace(" ", "")
                    .replace("-", "")
                    .replace("(", "")
                    .replace(")", "");
            String ddd = dddTelLimpo.substring(0, 2);
            String telefone = "";
            if (dddTelLimpo.length() == 10) {
                telefone = dddTelLimpo.substring(2, 6)
                        + "-" + dddTelLimpo.substring(6);
            } else {
                telefone = dddTelLimpo.substring(2, 7)
                        + "-" + dddTelLimpo.substring(7);
            }
            p.setDdd(ddd);
            p.setTelefone(telefone);
            p.setEmail(email);
            end.setCep(cep);
            end.setLogradouro(logradouro);
            end.setNumero(numero);
            end.setComplemento(complemento);
            end.setBairro(bairro);
            end.setCidade(cidade);
            end.setUf(uf);

            Endereco us = new Endereco();
            us.setId(Long.parseLong(idpessoa));
            p.setId(us);

            boolean atualizou = p.Atualizar();

            if (atualizou) {
                request.getRequestDispatcher("listar.jsp")//"tela/cadastrar.jsp"
                        .forward(request, response);
            } else {
                String mensagem
                        = "<h1>Atualização não Efetuado</h1>";
                response.getWriter().print(mensagem);
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
