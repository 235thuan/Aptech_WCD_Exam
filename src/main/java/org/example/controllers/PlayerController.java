package org.example.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.entity.Indexer;
import org.example.entity.Player;
import org.example.entity.PlayerIndex;
import org.example.service.IndexService;
import org.example.service.PlayerIndexService;
import org.example.service.PlayerService;

import java.io.*;

import java.util.List;
import java.util.Optional;

@WebServlet(value = "/player")
public class PlayerController extends HttpServlet {
    private PlayerService playerService;
    private IndexService indexerService;
    private PlayerIndexService playerIndexService;

    @Override
    public void init() throws ServletException {
        super.init();
        playerService = new PlayerService();
        indexerService= new IndexService();
        playerIndexService= new PlayerIndexService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null || action.isEmpty()) action = "list";

        switch (action) {
            case "new":
                showNewPlayerForm(req, resp);
                break;
            case "edit":
                showEditPlayerForm(req, resp);
                break;
            case "list":
                showListPlayers(req, resp);
                break;
            case "delete":
                deletePlayer(req, resp);
                break;
            case "view":
                showPlayerDetails(req, resp);
                break;
            default:
                showListPlayers(req, resp);
                break;
        }
    }

    private void showListPlayers(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Player> players = playerService.getAllPlayers();
        req.setAttribute("players", players);
        req.getRequestDispatcher("players.jsp").forward(req, resp);
    }

    private void showNewPlayerForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Fetch all indexers and player indexes to populate dropdowns
        List<Indexer> indexers = indexerService.getAllIndexers(); // Get all indexers from your service
        List<PlayerIndex> playerIndexes = playerIndexService.getAllPlayerIndexs(); // Get all player indexes (adjust service accordingly)

        // Set them as request attributes
        req.setAttribute("indexers", indexers);
        req.setAttribute("playerIndexes", playerIndexes);

        // Forward to the JSP page
        req.getRequestDispatcher("players.jsp").forward(req, resp);
    }


    private void showEditPlayerForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");
        try {
            int id = Integer.parseInt(idStr); // Parse ID from string
            Player player = playerService.getPlayerById(id).orElse(null); // Fetch Player by ID
            if (player != null) {
                req.setAttribute("player", player);
                req.getRequestDispatcher("playerEdit.jsp").forward(req, resp);
            } else {
                resp.sendRedirect("player?action=list"); // Redirect to list if not found
            }
        } catch (Exception e) {
            resp.sendRedirect("player?action=list"); // Redirect to list if ID is invalid
        }
    }

    private void showPlayerDetails(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");
        try {
            int id = Integer.parseInt(idStr); // Parse ID from string
            Player player = playerService.getPlayerById(id).orElse(null); // Fetch Player by ID
            if (player != null) {
                req.setAttribute("player", player);
                req.getRequestDispatcher("playerDetail.jsp").forward(req, resp);
            } else {
                resp.sendRedirect("player?action=list"); // Redirect to list if not found
            }
        } catch (Exception e) {
            resp.sendRedirect("player?action=list"); // Redirect to list if ID is invalid
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action) {
            case "insert":
                insertPlayer(req, resp);
                break;
            case "update":
                updatePlayer(req, resp);
                break;
            case "delete":
                deletePlayer(req, resp);
                break;
            default:
                showListPlayers(req, resp);
                break;
        }
    }

    private void insertPlayer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // Get parameters from the request
            String name = req.getParameter("name");
            String fullName = req.getParameter("fullName");
            String age = req.getParameter("age");
            int indexId = Integer.parseInt(req.getParameter("indexId")); // Get the indexId (foreign key)

            // Retrieve the Indexer entity based on indexId
            Optional<Indexer> optionalIndexer = indexerService.getIndexerById(indexId);

            // Check if the Indexer entity exists
            if (!optionalIndexer.isPresent()) {
                req.setAttribute("error", "Invalid indexer ID.");
                req.getRequestDispatcher("players.jsp").forward(req, resp);
                return;
            }

            Indexer indexer = optionalIndexer.get(); // Safely get the Indexer from Optional

            // Create a new Player and set the values
            Player player = new Player();
            player.setName(name);
            player.setFullName(fullName);
            player.setAge(age);
            player.setIndexer(indexer); // Set the indexer (not indexId directly)

            // Save the player
            playerService.savePlayer(player); // Save player with the indexer reference

            // Redirect to the list page
            resp.sendRedirect("player?action=list");

        } catch (Exception e) {
            req.setAttribute("error", "Error creating player: " + e.getMessage());
            req.getRequestDispatcher("players.jsp").forward(req, resp);
        }
    }


    private void updatePlayer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");
        String name = req.getParameter("name");
        String fullName = req.getParameter("fullName");
        String age = req.getParameter("age");
        int indexId = Integer.parseInt(req.getParameter("indexId")); // Get the indexId (foreign key)

        try {
            int id = Integer.parseInt(idStr);
            // Fetch the Player by ID
            Optional<Player> optionalPlayer = playerService.getPlayerById(id);

            if (!optionalPlayer.isPresent()) {
                req.setAttribute("error", "Player not found.");
                req.getRequestDispatcher("playerEdit.jsp").forward(req, resp);
                return;
            }

            Player player = optionalPlayer.get(); // Safely unwrap the Player object

            // Retrieve the Indexer by ID
            Optional<Indexer> optionalIndexer = indexerService.getIndexerById(indexId);

            if (!optionalIndexer.isPresent()) {
                req.setAttribute("error", "Invalid Index ID.");
                req.getRequestDispatcher("playerEdit.jsp").forward(req, resp);
                return;
            }

            Indexer indexer = optionalIndexer.get(); // Safely unwrap the Indexer

            // Update the player details
            player.setName(name);
            player.setFullName(fullName);
            player.setAge(age);
            player.setIndexer(indexer); // Set the Indexer (not indexId directly)

            // Update the player in the database
            playerService.savePlayer(player);

            // Redirect to the player list
            resp.sendRedirect("player?action=list");

        } catch (Exception e) {
            req.setAttribute("error", "Error updating player: " + e.getMessage());
            req.getRequestDispatcher("playerEdit.jsp").forward(req, resp);
        }
    }

    private void deletePlayer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");

        try {
            int id = Integer.parseInt(idStr);

            // Check if the player exists and delete
            boolean deleted = playerService.deletePlayerById(id);

            if (deleted) {
                resp.sendRedirect("player?action=list&message=Player deleted successfully");
            } else {
                resp.sendRedirect("player?action=list&error=Player not found");
            }

        } catch (NumberFormatException e) {
            req.setAttribute("error", "Invalid player ID.");
            req.getRequestDispatcher("players.jsp").forward(req, resp);
        } catch (Exception e) {
            req.setAttribute("error", "Error deleting player: " + e.getMessage());
            req.getRequestDispatcher("players.jsp").forward(req, resp);
        }
    }

}

