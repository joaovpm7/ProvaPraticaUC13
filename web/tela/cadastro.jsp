<%-- 
    Document   : cadastro
    Created on : 26/04/2022, 08:11:23
    Author     : joao victor
--%>

<%@page import="modelo.Endereco"%>
<%@page import="modelo.Pessoa"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%

    String acao = "cadastrar";
    String nome = "", tipopessoa = "", dddtelefone = "", email = "", salario = "",
            sexo = "", datanascimento = "", cpf = "", especialidade = "", numcrm = "", numcrea = "",
            estadocrm = "", estadocrea = "", tituloprofissional = "", idpessoa = "", localizacao = "";

    String numero = "", cidade = "", bairro = "", cep = "", complemento = "", logradouro = "", uf = "";

    if (request.getParameter("acao") != null) {
        if (request.getParameter("acao").equals("editar")) {
            idpessoa = request.getParameter("idpessoa");
            acao = request.getParameter("acao");
            Pessoa p = new Pessoa();
            p.setId(Long.parseLong(idpessoa));
            boolean achou = p.BuscarPorId();
            if (achou) {
                out.print("<script>"
                        + "window.alert('Cliente não Encontrado');"
                        + "</script>");
            } else {
                nome = p.getNome();
                tipopessoa = p.getTipopessoa();
                dddtelefone = "(" + p.getDdd() + ")" + p.getTelefone();
                email = p.getEmail();
                salario = String.valueOf(p.getSalario());
                sexo = p.getSexo();
                datanascimento = p.getDatacadastro().toString();
                cpf = p.getCpf();
                especialidade = p.getEspecialidade();
                numcrm = p.getNumCRM();
                numcrea = p.getNumCREA();
                estadocrm = p.getEstadoCRM();
                estadocrea = p.getEstadoCREA();
                tituloprofissional = p.getTituloprofissional();
                numero = String.valueOf(p.getLocalizacao().getNumero());
                cidade = String.valueOf(p.getLocalizacao().getCidade());
                bairro = String.valueOf(p.getLocalizacao().getBairro());
                cep = String.valueOf(p.getLocalizacao().getCep());
                complemento = String.valueOf(p.getLocalizacao().getComplemento());
                logradouro = String.valueOf(p.getLocalizacao().getLogradouro());
                uf = String.valueOf(p.getLocalizacao().getUf());
            }
        }
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="../css/bootstrap.css" />
        <link rel="stylesheet" href="../css/estilo.css" />

    </head>
    <body>
        <form action="../PessoaServlet" method="POST">
            <input type="hidden" name="idpessoa" value="<%=idpessoa%>">
            <input type="hidden" name="acao" value="<%=acao%>" />
            <div class="row">
                <div class="col-4"></div>
                <div class="col-4">
                    <div class="row">
                        <div class="col-6"> 
                            <label>Tipo Pessoa</label>
                            <input type="radio"
                                   required
                                   name="tipopessoa"
                                   id="rbdTipo"
                                   value="M"
                                   <%= tipopessoa.equals("Med") ? "checked" : ""%>/>
                            <label for="rbdTipo">Medico</label>

                            <input type="radio"
                                   required
                                   name="tipopessoa"

                                   id="rbdTipo"
                                   value="E"
                                   <%= tipopessoa.equals("Eng") ? "checked" : ""%>/>
                            <label for="rbdTipo">Engenheiro</label>
                        </div>
                        <div class="col-6"> 
                            <label for="inputNome">Nome</label>
                            <input type="text"  name="<%=nome%>" class="form-control" id="inputNome">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-6">
                            <label for="inputTelefone">Telefone</label>
                            <input  placeholder="(00) 00000-0000" type="text" name="<%=dddtelefone%>" class="form-control" id="inputTelefone">
                        </div> 
                        <div class="col-6">
                            <label for="inputEmail" >Email</label>
                            <input type="text"  name="<%=email%>" class="form-control" id="inputEmail">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-6"> 
                            <label>Salario</label>
                            <input type="text"  name="<%=salario%>"  class="form-control" id="inputSalario">
                        </div>
                        <div class="col-6"> 
                            <label for="inputSexo">Sexo</label>
                            <label >Sexo</label>
                            <br/>
                            <input id="rdbM"
                                   required
                                   type="radio" 
                                   name="sexo"
                                   value="M"
                                   <%= sexo.equals("M") ? "checked" : ""%>/>
                            <label for="rdbM">Masculino</label>
                            <br/>
                            <input id="rdbF"
                                   required
                                   type="radio" 
                                   name="sexo"
                                   value="F"
                                   <%= sexo.equals("F") ? "checked" : ""%>/>
                            <label for="rdbF">Feminino</label>
                            <br/>
                            <input id="rdbO"
                                   required
                                   type="radio" 
                                   name="sexo"
                                   value="O"
                                   <%= sexo.equals("O") ? "checked" : ""%>/>
                            <label for="rdbO">Outro</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-6">
                            <label for="inputDataNacimento" >Data Nascimento</label>
                            <input type="date"  name="<%=datanascimento%>" class="form-control" id="inputDataNacimento">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-6">
                            <label for="inputCPF" >CPF</label>
                            <input type="text"  name="<%=cpf%>" class="form-control" id="inputCPF">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-6">
                            <button type="submit" class="btn btn-primary btn-lg btn-block">Cadastrar</button>
                        </div>
                    </div>
                </div>
                <div class="col-4"></div>
            </div>
            <script type="text/javascript" src="../js/bootstrap.js"></script>
            <script type="text/javascript" src="../js/jquery.js"></script>
            <script type="text/javascript" src="../js/jquery.mask.js"></script>
            <script type="text/javascript">
                jQuery(document).ready(function ($) {
                    $("#inputTelefone").on("click", function (e) {
                        $("#inputTelefone").mask("(00) 00000-0000");
                    });
                    $("#inputCPF").on("click", function (e) {
                        $("#inputCPF").mask("000.000.000-00");
                    });
                });
            </script>
    </body>
</form>
</html>
