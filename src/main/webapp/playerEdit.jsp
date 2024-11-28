<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Player</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-4">
    <h2>Edit Player</h2>
    <form action="player?action=update" method="post">
        <input type="hidden" name="playerId" value="<%= request.getAttribute("playerId") %>">

        <div class="form-group">
            <label for="name">Player Name:</label>
            <input type="text" class="form-control" id="name" name="name" value="<%= request.getAttribute("name") %>" required>
        </div>

        <div class="form-group">
            <label for="age">Player Age:</label>
            <input type="text" class="form-control" id="age" name="age" value="<%= request.getAttribute("age") %>" required>
        </div>

        <div class="form-group">
            <label for="indexId">Index Name:</label>
            <select class="form-control" id="indexId" name="indexId">
                <option value="1" <%= request.getAttribute("indexId").equals("1") ? "selected" : "" %>>Speed</option>
                <option value="2" <%= request.getAttribute("indexId").equals("2") ? "selected" : "" %>>Strength</option>
                <option value="3" <%= request.getAttribute("indexId").equals("3") ? "selected" : "" %>>Accuracy</option>
            </select>
        </div>

        <button type="submit" class="btn btn-primary">Save Changes</button>
        <a href="player?action=list" class="btn btn-secondary">Cancel</a>
    </form>
</div>
</body>
</html>
