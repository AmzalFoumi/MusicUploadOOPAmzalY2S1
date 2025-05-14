<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="amzalMusicUpload.Music" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	    // Get the Music object from the request
	    Music music = (Music) request.getAttribute("music");
	
	    // If music is available (loaded from DB)
	    if (music != null) {
	%>
		<h3>Publish Music</h3>
		
		<p><strong>Title:</strong> <%= music.getTitle() %></p>
	    <p><strong>Genre:</strong> <%= music.getGenre() %></p>
	    <p><strong>Language:</strong> <%= music.getLanguage() %></p>
		
        <form action="musicPublishController" method="post">
            <input type="hidden" name="musicId" value="<%= music.getId()%>">
            <input type="hidden" name="title" value="<%= music.getTitle()%>">
            <input type="hidden" name="genre" value="<%= music.getGenre()%>">
            <input type="hidden" name="language" value="<%= music.getLanguage()%>">

			<label for="albumName">Album Name (If album does'nt exit, new one will be created for you):</label><br>
            <input type="text" id="albumName" name="albumName" ><br><br>

            <label for="price">Price (USD):</label><br>
            <input type="number" id="price" name="price" ><br><br>

            <label for="region">Release Region:</label><br>
            <input type="text" id="region" name="region" ><br><br>

<!--             <label for="releaseDate">Release Date:</label><br>
            <input type="date" id="releaseDate" name="releaseDate" required><br><br> -->

            <button type="submit">Confirm Publication</button>
        </form>
        
        <%
	    } else {
	%>
	    <!-- Error message if no music found -->
	    <p style="color:red;">Music details not found.</p>
	<%
	    }
	%>
	
	<% 
    	String error = (String) request.getAttribute("errorMsg");
   		if (error != null) { 	
   	%>
   	<p style="color:red;"><%= error %></p>
	<% } %>
        
</body>
</html>