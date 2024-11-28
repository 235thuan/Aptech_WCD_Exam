<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Player Details</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-4">
    <h2>Player Details</h2>
    <table class="table">
        <tr>
            <th>ID</th>
            <td><%= request.getAttribute("playerId") %></td>
        </tr>
        <tr>
            <th>Name</th>
            <td><%= request.getAttribute("name") %></td>
        </tr>
        <tr>

