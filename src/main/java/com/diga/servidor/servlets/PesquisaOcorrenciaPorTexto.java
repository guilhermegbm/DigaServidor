/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diga.servidor.servlets;

import com.diga.servidor.controle.ControleOcorrencia;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.diga.servidor.modelo.beans.Ocorrencia;
import com.diga.servidor.modelo.beans.Usuario;
import com.diga.servidor.modelo.persistencia.UsuarioDAO;
import com.diga.servidor.utils.GsonBuilderUtil;

/**
 *
 * @author Guilherme
 */
@WebServlet(name = "PesquisaOcorrenciaPorTexto", urlPatterns = {"/diga_api/PesquisaOcorrenciaPorTexto"}, initParams = {
    @WebInitParam(name = "nomeUsuario", value = "")
    , @WebInitParam(name = "senha", value = "")
    , @WebInitParam(name = "textoPesquisar", value = "")})
public class PesquisaOcorrenciaPorTexto extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (UsuarioDAO.autenticaUsuario(request.getParameter("nomeUsuario"), request.getParameter("senha"))) {
            Usuario u = UsuarioDAO.listarUsuarioPorNomeUsuarioESenha(request.getParameter("nomeUsuario"), request.getParameter("senha"));
            response.setHeader("auth", "1");
            
            List<Ocorrencia> ocorrencias = ControleOcorrencia.listarOcorrenciasPorTextoPesquisa(u.getCodigo(), request.getParameter("textoPesquisar"));

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().println(GsonBuilderUtil.constroiGsonComData("yyyy-MM-dd HH:mm:ss").toJson(ocorrencias));
        } else {
            response.setHeader("auth", "0");

            List<Ocorrencia> o = null;

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().println(new Gson().toJson(o));
        }
    }
}
