<%@ page import="org.example.entity.Player" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.entity.PlayerIndex" %>
<%@ page import="org.example.entity.Indexer" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Player Information</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script>
        // Basic form validation
        function validateForm() {
            var name = document.forms["playerForm"]["name"].value;
            var age = document.forms["playerForm"]["age"].value;
            var indexId = document.forms["playerForm"]["indexId"].value;

            if (name == "" || age == "" || indexId == "") {
                alert("All fields must be filled out.");
                return false;
            }
        }
    </script>
</head>
<body>

<div class="container mt-5">
    <h2>Player Information</h2>

    <!-- Error handling -->
    <div>
        <%= request.getAttribute("error") != null ? "<div class='alert alert-danger'>" + request.getAttribute("error") + "</div>" : "" %>
    </div>

    <!-- Form to create a new player -->
    <form name="playerForm" action="player?action=insert" method="POST" onsubmit="return validateForm()">
        <div class="form-group">
            <label for="name">Player Name</label>
            <input type="text" id="name" name="name" class="form-control" required>
        </div>
        <div class="form-group">
            <label for="age">Player Age</label>
            <input type="text" id="age" name="age" class="form-control" required>
        </div>

        <!-- Dropdown for Index Name -->
        <div class="form-group">
            <label for="indexId">Index Name</label>
            <select id="indexId" name="indexId" class="form-control" required>
                <option value="">Select Index</option>
                <%
                    List<Indexer> indexers = (List<Indexer>) request.getAttribute("indexers");
                    if (indexers != null) {
                        for (Indexer indexer : indexers) {
                %>
                <option value="<%= indexer.getIndexId() %>"><%= indexer.getName() %></option>
                <%
                        }
                    }
                %>
            </select>
        </div>

        <!-- Dropdown for Value (PlayerIndex) -->
        <div class="form-group">
            <label for="playerIndex">Value</label>
            <select id="playerIndex" name="playerIndex" class="form-control" required>
                <option value="">Select Value</option>
                <%
                    List<PlayerIndex> playerIndexes = (List<PlayerIndex>) request.getAttribute("playerIndexes");
                    if (playerIndexes != null) {
                        for (PlayerIndex playerIndex : playerIndexes) {
                %>
                <option value="<%= playerIndex.getId() %>"><%= playerIndex.getValue() %></option>
                <%
                        }
                    }
                %>
            </select>
        </div>

        <button type="submit" class="btn btn-primary">Add Player</button>
    </form>

    <h3 class="mt-5">Player List</h3>

    <table class="table table-striped">
        <thead>
        <tr>
            <th>ID</th>
            <th>Player Name</th>
            <th>Player Age</th>
            <th>Index Name</th>
            <th>Value</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <!-- Loop through players -->
        <%
            List<Player> players = (List<Player>) request.getAttribute("players"); // Get players list from request
            if (players != null && !players.isEmpty()) {
                for (Player player : players) {
        %>
        <tr>
            <td><%= player.getPlayerId() %></td>
            <td><%= player.getName() %></td>
            <td><%= player.getAge() %></td>

            <td>
                <%
                    // Assuming Indexer has a 'getName()' method
                    String indexName = "";
                    if (player.getIndexer() != null) {
                        indexName = player.getIndexer().getName(); // Get the indexer's name (e.g., 'speed', 'strength', etc.)
                    }
                %>
                <%= indexName %>
            </td>

            <!-- Loop through the player's PlayerIndex list to get the value -->
            <td>
                <%
                    boolean valueFound = false;
                    if (player.getPlayerIndexes() != null && !player.getPlayerIndexes().isEmpty()) {
                        // Loop through the PlayerIndex list to find values for the index
                        for (PlayerIndex playerIndex : player.getPlayerIndexes()) {
                            // If the current playerIndex matches a specific index (e.g., "speed", "strength")
                            if (playerIndex.getIndexer().getName().equals("speed")) {

                                valueFound = true;
                                break;
                            }
                        }
                    }

                %>
            </td>

            <td>
                <a href="player?action=edit&id=<%= player.getPlayerId() %>" class="btn btn-warning btn-sm">Edit</a>
                <a href="player?action=delete&id=<%= player.getPlayerId() %>" class="btn btn-danger btn-sm">Delete</a>
            </td>
        </tr>
        <%
                }
            }
        %>
        </tbody>
    </table>

</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
