/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diga.servidor.servlets;

import com.diga.servidor.controle.ControleOcorrencia;
import com.diga.servidor.controle.ControleUsuario;
import com.diga.servidor.utils.GsonBuilderUtil;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Guilherme
 */
@WebServlet(name = "AtualizaFeed", urlPatterns = {"/diga_api/AtualizaFeed"}, initParams = {
    @WebInitParam(name = "nomeUsuario", value = "")
    , @WebInitParam(name = "senha", value = "")
    , @WebInitParam(name = "dataInicial", value = "")
    , @WebInitParam(name = "usuCodigo", value = "")})
public class AtualizaFeed extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (ControleUsuario.autenticaUsuario(request.getParameter("nomeUsuario"), request.getParameter("senha"))) {
            response.setHeader("auth", "1");

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().println(GsonBuilderUtil.constroiGsonComData("yyyy-MM-dd HH:mm:ss").toJson(ControleOcorrencia.atualizaFeed(request.getParameter("dataInicial"), Integer.parseInt(request.getParameter("usuCodigo")))));
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
