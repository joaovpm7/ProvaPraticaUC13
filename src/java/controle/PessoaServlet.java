/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.io.IOException;
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
            if (request.getParameter("acao").equals("editar")) {
                String nome = request.getParameter("nome");
                String email = request.getParameter("email");
                Double salario = Double.parseDouble(request.getParameter("salario"));
                String sexo = request.getParameter("sexo");
                Date datanascimento = Date.valueOf(request.getParameter("datanascimento"));
                String cpf = request.getParameter("cpf");
                String tipopessoa = request.getParameter("tipopessoa");
                String dddtelefone = request.getParameter("dddtelefone");
                if (tipopessoa.equals("med")) {
                    String especialidade = request.getParameter("titulo_especialidade");
                    String num_crm = request.getParameter("numerocrm_crea");
                    String estado_crm = request.getParameter("estadocrm_crea");
                    Pessoa p = new Pessoa();
                    p.setEspecialidade(especialidade);
                    p.setNumCRM(num_crm);
                    p.setEstadoCRM(estado_crm);
                } else {
                    String tituloprofissional = request.getParameter("titulo_especialidade");
                    String num_crea = request.getParameter("numerocrm_crea");
                    String estado_crm = request.getParameter("estadocrm_crea");
                    Pessoa p = new Pessoa();
                    p.setTituloprofissional(tituloprofissional);
                    p.setNumCREA(num_crea);
                    p.setEstadoCRM(estado_crm);
                }
                Pessoa p = new Pessoa();
                p.setNome(nome);
                p.setEmail(email);
                p.setSalario(salario);
                p.setSexo(sexo);
                p.setDatanascimento(datanascimento);
                p.setCpf(cpf);
                String dddTelLimpo = dddtelefone.replace(" ", " ")
                        .replace("-", "")
                        .replace("(", "")
                        .replace(")", "");
                String ddd = dddTelLimpo.substring(0, 2);
                String telefone = dddTelLimpo.length() == 10
                        ? dddTelLimpo.substring(2, 6)
                        + "-" + dddTelLimpo.substring(6)
                        : dddTelLimpo.substring(2, 7)
                        + "-" + dddTelLimpo.substring(7);
                p.setDdd(ddd);
                p.setTelefone(telefone);
                boolean cadastrou = p.Cadastrar();

                if (cadastrou == true) {
                    response.sendRedirect("listar.jsp");
                } else {
                    response.sendRedirect("index.jsp?erro=cadastrar");
                }
            } else if (request.getParameter("acao").equals("deletar")) {
                String idpessoa = request.getParameter("idpessoa");
                Pessoa p = new Pessoa();
                p.setId(Long.parseLong(idpessoa));
                boolean apagou = p.Deletar();
                if (apagou == true) {
                    response.sendRedirect("listar.jsp");
                } else {
                    response.sendRedirect("listar.jsp?erro=apagar");
                }
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
