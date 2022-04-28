<%-- 
    Document   : index
    Created on : 26/04/2022, 10:50:25
    Author     : sala305b
--%>

<%@page import="java.sql.Date"%>
<%@page import="modelo.Endereco"%>
<%@page import="modelo.Pessoa"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String acao = "cadastrar", id = "", nome = "", cpf = "", datanascimento = "", sexo = "", ddd = "", telefone = "", email = "",
            tipopessoa = "", salario = "", especialidade = "", estadocrm = "", numcrm = "", estadocrea = "", numcrea = "", tituloprofissional = "",
            bairro = "", cep = "", cidade = "", complemento = "", idpessoa = "", logradouro = "", numero = "", uf = "";

    Pessoa pes = new Pessoa();

    if (request.getParameter("acao") != null) {

        if (request.getParameter("acao").equals("editar")) {
            idpessoa = request.getParameter("idpessoa");
            acao = request.getParameter("acao");
            boolean achou = pes.BuscarPorId(idpessoa);
            if (!achou) {
                out.print("<script>"
                        + "window.alert('Contato não Encontrado');"
                        + "</script>");
            }
        } else {
            acao = "editar";
            id = String.valueOf(pes.getId());
            idpessoa = id;
            cpf = pes.getCpf();
            datanascimento = String.valueOf(pes.getDataNascimento());
            sexo = pes.getSexo();
            ddd = pes.getDdd();
            telefone = pes.getEmail();
            tipopessoa = pes.getTipopessoa();
            salario = String.valueOf(pes.getSalario());
            especialidade = pes.getEspecialidade();
            estadocrea = pes.getEstadoCREA();
            estadocrm = pes.getEstadoCRM();
            numcrm = pes.getNumCRM();
            numcrea = pes.getNumCREA();
            tituloprofissional = pes.getTituloProfissional();
            numero = String.valueOf(pes.getLocalizacao().getNumero());
            cidade = String.valueOf(pes.getLocalizacao().getCidade());
            bairro = String.valueOf(pes.getLocalizacao().getBairro());
            cep = String.valueOf(pes.getLocalizacao().getCep());
            complemento = String.valueOf(pes.getLocalizacao().getComplemento());
            logradouro = String.valueOf(pes.getLocalizacao().getLogradouro());
            uf = String.valueOf(pes.getLocalizacao().getUf());
        }

    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro</title>
        <!--Bootsrap 4 CDN-->
        <link rel="stylesheet" href="css/bootstrap.css" >

        <!--Fontawesome CDN-->

        <!--Custom styles-->
        <link rel="stylesheet" type="text/css" href="css/estilo.css">
    </head>
    <body>


        <form action="PessoaServlet" method="POST">
            <input type="hidden" name="acao" value="<%= acao%>"/>
            <input type="hidden" name="idcontato" value="<%= idpessoa%>"/>
            <div class="container">

                <div class="row">
                    <div class="col ">
                        <label id="text"  class="title">Olá !</label> <br/>
                        <label id="text" >Vamos fazer seu cadastro:</label>
                    </div>
                </div>

                <div class="row">
                    <div class="col">
                        <label id="text" >Nome:</label>
                        <input  required value="<%= nome%>" type="text" 
                                name="nome" class="form-control" placeholder="Nome">
                    </div>
                    <div class="col">
                        <label id="text" >CPF:</label>
                        <input required type="text" id="txtcpf" value="<%= cpf%>"
                               name="cpf" class="form-control" placeholder="CPF">
                    </div>
                </div>



                <div class="row">
                    <div class="col">
                        <label id="text" >E-mail:</label>
                        <input required type="email" value="<%= email%>" 
                               name="email" class="form-control" placeholder="E-mail">
                    </div>
                </div>

                <div class="row">
                    <div class="col">
                        <label id="text" for="txtTelefone">Telefone:</label>
                        <br />
                        <input required type="text" id="txtTelefone" 
                               value="<%= ddd + telefone%>" name="dddtelefone" 
                               class="form-control" placeholder="(00) 00000-0000">
                    </div>
                    <div class="col">
                        <label id="text" for="txtlogradouro">Logradouro:</label>
                        <input required type="text" value="<%= logradouro%>" 
                               name="logradouro" 
                               class="form-control" placeholder="Logradouro" />
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        <label id="text" for="txtbairro">Bairro:</label>
                        <input  required type="text" value="<%= bairro%>" 
                                name="bairro" 
                                class="form-control" placeholder="Bairro" />
                    </div>
                    <div class="col">
                        <label id="text" for="txtcomplemento">Complemento:</label>
                        <input required type="text" value="<%= complemento%>" 
                               name="complemento" 
                               class="form-control" placeholder="Complemento" />
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        <label id="text" for="txtcidade">Cidade:</label>
                        <br />
                        <input required type="text" 
                               value="<%= cidade%>" 
                               name="cidade" 
                               class="form-control" placeholder="Cidade">
                    </div>
                    <div class="col">
                        <label id="text" for="txtnumero">Nº:</label>
                        <input required type="number" value="<%= numero%>" 
                               name="numero" 
                               class="form-control" placeholder="Número" />
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        <label id="text" for="">UF:</label><br/>
                        <select required id="sltEstado" name="uf">
                            <option <%= uf.equals("") ? "selected" : ""%>
                                value="">Selecione</option>
                            <option <%= uf.equals("AC") ? "selected" : ""%>
                                value="AC">Acre</option>
                            <option <%= uf.equals("AL") ? "selected" : ""%>
                                value="AL">Alagoas</option>
                            <option <%= uf.equals("AP") ? "selected" : ""%>
                                value="AP">Amapá</option>
                            <option <%= uf.equals("AM") ? "selected" : ""%>
                                value="AM">Amazonas</option>
                            <option <%= uf.equals("BA") ? "selected" : ""%>
                                value="BA">Bahia</option>
                            <option <%= uf.equals("CE") ? "selected" : ""%>
                                value="CE">Ceará</option>
                            <option <%= uf.equals("DF") ? "selected" : ""%>
                                value="DF">Distrito Federal</option>
                            <option <%= uf.equals("ES") ? "selected" : ""%>
                                value="ES">Espirito Santo</option>
                            <option <%= uf.equals("GO") ? "selected" : ""%>
                                value="GO">Goiás</option>
                            <option <%= uf.equals("MA") ? "selected" : ""%>
                                value="MA">Maranhão</option>
                            <option <%= uf.equals("MS") ? "selected" : ""%>
                                value="MS">Mato Grosso do Sul</option>
                            <option <%= uf.equals("MT") ? "selected" : ""%>
                                value="MT">Mato Grosso</option>
                            <option <%= uf.equals("MG") ? "selected" : ""%>
                                value="MG">Minas Gerais</option>
                            <option <%= uf.equals("PA") ? "selected" : ""%>
                                value="PA">Pará</option>
                            <option <%= uf.equals("PB") ? "selected" : ""%>
                                value="PB">Paraíba</option>
                            <option <%= uf.equals("PR") ? "selected" : ""%>
                                value="PR">Paraná</option>
                            <option <%= uf.equals("PE") ? "selected" : ""%>
                                value="PE">Pernambuco</option>
                            <option <%= uf.equals("PI") ? "selected" : ""%>
                                value="PI">Piauí</option>
                            <option <%= uf.equals("RJ") ? "selected" : ""%>
                                value="RJ">Rio de Janeiro</option>
                            <option <%= uf.equals("RN") ? "selected" : ""%> 
                                value="RN">Rio Grande do Norte</option>
                            <option <%= uf.equals("RS") ? "selected" : ""%> 
                                value="RS">Rio Grande do Sul</option>
                            <option <%= uf.equals("RO") ? "selected" : ""%> 
                                value="RO">Rondônia</option>
                            <option <%= uf.equals("RR") ? "selected" : ""%> 
                                value="RR">Roraima</option>
                            <option <%= uf.equals("SC") ? "selected" : ""%> 
                                value="SC">Santa Catarina</option>
                            <option <%= uf.equals("SP") ? "selected" : ""%> 
                                value="SP">São Paulo</option>
                            <option <%= uf.equals("SE") ? "selected" : ""%>
                                value="SE">Sergipe</option>
                            <option <%= uf.equals("TO") ? "selected" : ""%> 
                                value="TO">Tocantins</option>
                        </select>

                    </div>
                    <div class="col">
                        <label id="text" for="txtcep">Cep:</label>
                        <input required id="txtcep" type="text" value="<%= cep%>" 
                               name="cep" 
                               class="form-control" placeholder="Cep" />
                    </div>
                </div>

                <div class="row">
                    <div class="col">
                        <label id="text" for="txtDataNascimento">Data de Nascimento: </label>
                        <br />
                        <input required size="20" type ="date" id="txtDataNascimento" 
                               name="datanascimento" value="<%= datanascimento%>"/>
                    </div>
                    <div class="col">
                        <label id="text" for="rbdsexo">Sexo: </label>
                        <br />
                        <div class="form-check form-check-inline">
                            <input   <%= sexo.equals("M") ? "checked" : ""%>  required
                                                                              type="radio" 
                                                                              name="sexo" id="rdmasculino" value="M">
                            <label id="text"  class="form-check-label" for="rdmasculino">Masculino</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input  <%= sexo.equals("F") ? "checked" : ""%> 
                                required
                                class="form-check-input" type="radio"
                                name="sexo" id="rdfeminino" value="F">
                            <label id="text" class="form-check-label" for="rdfeminino">Feminino</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input  <%= sexo.equals("O") ? "checked" : ""%> 
                                required
                                class="form-check-input" type="radio" 
                                name="sexo" id="rdoutro" value="O" >
                            <label  id="text" class="form-check-label" for="rdoutro">Outro</label>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        <label id="text" for="txtsalario">Salario: </label>
                        <br />
                        <input required size="20" type ="text" id="txtSalario" 
                               name="salario" 
                               value="<%= salario%>" class="form-control" placeholder="Salario"/>
                    </div>
                    <div class="col">
                        <label id="text" for="rdbtipopessoa">Tipo Pessoa: </label>
                        <br />
                        <div class="form-check form-check-inline">
                            <input  required id="rdmedico" 
                                    <%= tipopessoa.equals("M") ? "checked" : ""%>  
                                    class="form-check-input" type="radio" 
                                    name="tipopessoa" id="rbmedico" value="M">
                            <label id="text"  class="form-check-label" for="rdmedico">Medico</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input required  id="rdengenheiro" 
                                   <%= tipopessoa.equals("E") ? "checked" : ""%> 
                                   class="form-check-input" type="radio" 
                                   name="tipopessoa" id="rdengenheiro" value="E">
                            <label id="text" class="form-check-label" for="rdengenheiro">Engenheiro</label>
                        </div>

                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        <label id="text" for="txtespecialidade_titulo">Especialidade/Titulo: </label>
                        <br />
                        <input required size="20" type ="text" id="txtespecialidade_titulo" 
                               name="especialidade_titulo" 
                               value="<%= especialidade + tituloprofissional%>" 
                               class="form-control"/>
                    </div>
                    <div class="col">
                        <label id="text" for="txtestadocrm_crea">Estado CREA/CRM: </label>
                        <input required size="20" type ="text" id="txtestadocrm_crea" 
                               name="estadocrm_crea" 
                               value="<%= estadocrea + estadocrm%>" class="form-control"/>
                    </div>

                </div>
                <div class="row">
                    <div class="col">
                        <label id="text" for="txtnumcrm_crea">Numero CREA/CRM: </label>
                        <input required id="txtcrea_crm"size="20" type ="text"  
                               name="numcrm_crea" 
                               value="<%= numcrea + numcrm%>" class="form-control"/>
                    </div>

                </div>


                <br/>

                <div class="row">
                    <div class="col">
                        <button id="btn" type="submit" class="btn btn-primary btn-lg btn-block"><%= acao%></button>
                    </div>
                </div>
            </div>
        </form>


        <script type="text/javascript" src="js/jquery.js"></script>
        <script type="text/javascript" src="js/bootstrap.js"></script>
        <script type="text/javascript" src="js/jquery.mask.js"></script>
        <script type="text/javascript">

            jQuery(document).ready(function ($) {

                $("#txtcpf").mask("000.000.000-00");
                $("#txtcep").mask("00.000-000");
                $("#txtTelefone").mask("(00) 00000-0000");

                $("#rdmedico").on("click", function (e) {
                    $("#txtcrea_crm").mask("00000000-0");

                });

                $("#rdengenheiro").on("click", function (e) {
                    $("#txtcrea_crm").mask("0000000000");

                });

            });
        </script>
    </body>
</html>
