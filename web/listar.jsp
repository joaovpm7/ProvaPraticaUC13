<%-- 
    Document   : listar
    Created on : 26/04/2022, 10:50:35
    Author     : sala305b
--%>

<%@page import="modelo.Endereco"%>
<%@page import="modelo.Pessoa"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    List<Pessoa> pessoa = new Pessoa().ListarTodos();


%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <!--Bootsrap 4 CDN-->
        <link rel="stylesheet" href="css/bootstrap.css" >
    </head>
    <body>
        <h1>Listagem</h1>

        <table class="table table-bordered">
            <tr>
                <td>ID</td>
                <td>Nome</td>
                <td>CPF </td>[
                <td>Bairro</td>
                <td>Cidade</td>
                <td>UF</td>
                <td>Data Nasc</td>
                <td>Sexo</td>
                <td>Email</td>
                <td>DDD/Telefone</td>
                <td>Profissão</td>
                <td>NumeroCRM/CREA</td>
                <td>EstadoCRM/CREA</td>
                <td>Espec/Titulo Prof</td>
                <td>Data Cadastro</td>
                <td>Ações</td>

            </tr>           

            <% for (Pessoa pes : pessoa) {

                    Endereco e = new Endereco();
                    e.setIdpessoa(pes.getId());
                    if(e.BuscarPorId() == true){
                        pes.setLocalizacao(e);
                    }else{
                        pes.getLocalizacao().setBairro("");
                        pes.getLocalizacao().setCep("");
                        pes.getLocalizacao().setCidade("");
                        pes.getLocalizacao().setComplemento("");
                        pes.getLocalizacao().setLogradouro("");
                        pes.getLocalizacao().setNumero("");
                        pes.getLocalizacao().setUf("");
                    }
                    String s = new SimpleDateFormat("dd/MM/yyyy").format(pes.getDataNascimento());
                    String cad = new SimpleDateFormat("dd/MM/yyyy HH:MM:SS").format(pes.getDtcadastro());


            %>

            <tr>
                <td><%= pes.getId()%></td>
                <td><%= pes.getNome()%></td>
                <td><%= pes.getCpf()%></td>
                <td><%= pes.getLocalizacao().getBairro()%></td>
                <td><%= pes.getLocalizacao().getCidade()%></td>
                <td><%= pes.getLocalizacao().getUf()%></td>
                <td><%=s%></td>
                <td><%= pes.getSexo()%></td>
                <td><%= pes.getEmail()%></td>
                <td><%= "(" + pes.getDdd() + ")" + " " + pes.getTelefone()%></td>
                <td><%= pes.getTipopessoa().equals("M") ? "Médico" : "Engenheiro"  %></td>
                <td><%= pes.getTipopessoa().equals("M") ? pes.getNumCRM(): pes.getNumCREA() %></td>
                <td><%= pes.getTipopessoa().equals("M") ? pes.getEstadoCRM(): pes.getEstadoCREA() %></td>
                <td><%= pes.getTipopessoa().equals("M") ? pes.getEspecialidade(): pes.getTituloProfissional() %></td>

                <td><%= cad%></td>
                <td width="15%">
                    <div class="row">
                        <div class="col">
                            <form action="index.jsp" method="POST">
                                <input type="hidden" name="acao" value="editar">
                                <input type="hidden" name="idpessoa" value="<%= pes.getId()%>">
                                <button class="btn btn-primary" type="submit">Editar</button>
                            </form> 
                        </div>
                        <div class="col">
                            <form action="PessoaEndServlet" method="POST">
                                <input type="hidden" name="acao" value="apagar">
                                <input type="hidden" name="idpessoa" value="<%= pes.getId()%>">
                                <button class="btn btn-danger" type="submit">Deletar</button>
                            </form> 
                        </div>
                    </div>
                </td> 
                <td></td> 
            </tr>
            <% }

            %>
        </table>



        <script type="text/javascript" src="js/jquery.js"></script>
        <script type="text/javascript" src="js/bootstrap.js"></script>
        <script type="text/javascript" src="js/jquery.mask.js"></script>
    </body>
</html>