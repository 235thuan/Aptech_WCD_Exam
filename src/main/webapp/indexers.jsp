<%@ page import="org.example.entity.Indexer" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Indexer List</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>

<div class="container mt-5">
    <h2>Indexers List</h2>

    <!-- Button to create a new indexer -->
    <a href="indexer?action=new" class="btn btn-primary mb-3">Create New Indexer</a>

    <!-- Display any error or success messages -->
    <div>
        <%= request.getAttribute("error") != null ? "<div class='alert alert-danger'>" + request.getAttribute("error") + "</div>" : "" %>
        <%= request.getAttribute("success") != null ? "<div class='alert alert-success'>" + request.getAttribute("success") + "</div>" : "" %>
    </div>

    <!-- Table of indexers -->
    <table class="table table-striped">
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
        </tr>
        </thead>
        <tbody>
        <!-- Loop through indexers -->
        <%
            List<Indexer> indexers = (List<Indexer>) request.getAttribute("indexers");
            if (indexers != null && !indexers.isEmpty()) {
                for (Indexer indexer : indexers) {
        %>
        <tr>
            <td><%= indexer.getIndexId() %></td>
            <td><%= indexer.getName() %></td>
        </tr>
        <%
            }
        } else {
        %>
        <tr>
            <td colspan="2">No indexers found.</td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
