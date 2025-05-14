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
		<h3>Deletion Confirmation</h3>
		
		<p><strong>Title:</strong> <%= music.getTitle() %></p>
	    <p><strong>Genre:</strong> <%= music.getGenre() %></p>
	    <p><strong>Language:</strong> <%= music.getLanguage() %></p>
		
        <form action="musicDeleteController" method="post">
            <input type="hidden" name="musicId" value="<%= music.getId()%>">
            <input type="hidden" name="title" value="<%= music.getTitle()%>">

           	<h1>Are you sure you want to delete this music?</h1> 
			<h2>This is irreversible</h2>
          	
            <button type="submit">Confirm Deletion</button>
        </form>
        
        <%
	    } else {
	%>
	    <!-- Error message if no music found -->
	    <p style="color:red;">Music details not found.</p>
	<%
	    }
	%>
	
        
</body>
</html>