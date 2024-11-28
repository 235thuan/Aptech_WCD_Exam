package org.example.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.entity.Indexer;
import org.example.service.IndexService;

import java.io.IOException;

@WebServlet(value = "/indexer")
public class IndexController extends HttpServlet {
    private IndexService indexService;

    @Override
    public void init() throws ServletException {
        super.init();
        indexService = new IndexService();  // Ensure IndexService is properly initialized
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null || action.isEmpty()) action = "list";

        switch (action) {
            case "new":
                showNewIndexerForm(req, resp);
                break;
            case "list":
                showListIndexers(req, resp);
                break;
            default:
                showListIndexers(req, resp);
                break;
        }
    }

    private void showListIndexers(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("indexers", indexService.getAllIndexers());
        req.getRequestDispatcher("indexers.jsp").forward(req, resp);  // Display indexers list page
    }

    private void showNewIndexerForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("indexerNew.jsp").forward(req, resp);  // Show form to create a new indexer
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("insert".equals(action)) {
            insertIndexer(req, resp);
        } else {
            showListIndexers(req, resp);
        }
    }

    private void insertIndexer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String name = req.getParameter("name");  // Get indexer name from form

            // Create a new Indexer object
            Indexer indexer = new Indexer();
            indexer.setName(name);

            // Save the new indexer to the database
            indexService.saveIndexer(indexer);

            // Redirect back to the list of indexers
            resp.sendRedirect("indexer?action=list");
        } catch (Exception e) {
            req.setAttribute("error", "Error creating indexer: " + e.getMessage());
            req.getRequestDispatcher("indexerNew.jsp").forward(req, resp);
        }
    }
}