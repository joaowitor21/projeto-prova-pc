/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifpe.garanhuns.projetoProvaPc.servlet;

import br.edu.ifpe.garanhuns.projetoProvaPc.builders.RespostaProvaQuestaoMultiplaEscolhaBuilder;
import br.edu.ifpe.garanhuns.projetoProvaPc.dominio.Prova;
import br.edu.ifpe.garanhuns.projetoProvaPc.dominio.QuestaoMultiplaEscolha;
import br.edu.ifpe.garanhuns.projetoProvaPc.fachada.Fachada;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author lucas
 */
@WebServlet(name = "SubmeteRespostaDaProvaServlet", urlPatterns = {"/SubmeteRespostaDaProvaServlet"})
public class SubmeteRespostaDaProvaServlet extends HttpServlet {

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
        HttpSession session = request.getSession();
        String matricula = request.getParameter("matricula");
        String senha = request.getParameter("senha");
        RespostaProvaQuestaoMultiplaEscolhaBuilder builder = Fachada.getInstance().getRespostaProvaQuestaoMultiplaEscolhaBuilder(matricula,senha);
        
        Iterator<QuestaoMultiplaEscolha> qs = builder.iterator();
        int i=1;
        while(qs.hasNext()) {
            QuestaoMultiplaEscolha q = qs.next();
            builder.responder(q,request.getParameter("q"+i).charAt(0));
            i++;
        }
        Fachada.getInstance().adicionar(builder);
        response.sendRedirect("pagina_prova_terminada.jsp");
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
