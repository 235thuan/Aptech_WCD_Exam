<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create Indexer</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>

<div class="container mt-5">
    <h2>Create New Indexer</h2>

    <!-- Display any error message if it exists -->
    <div>
        <%= request.getAttribute("error") != null ? "<div class='alert alert-danger'>" + request.getAttribute("error") + "</div>" : "" %>
    </div>

    <!-- Form to create a new indexer -->
    <form action="indexer?action=insert" method="POST">
        <div class="form-group">
            <label for="name">Indexer Name</label>
            <input type="text" id="name" name="name" class="form-control" required>
        </div>
        <button type="submit" class="btn btn-primary">Create Indexer</button>
    </form>

    <p class="mt-3"><a href="indexer?action=list">Back to Indexers List</a></p>
</div>

</body>
</html>
