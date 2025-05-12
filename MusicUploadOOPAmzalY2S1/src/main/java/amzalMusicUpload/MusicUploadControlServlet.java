
package amzalMusicUpload;

//import amzalDBConnect.DBconnect;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


@WebServlet("/musicUploadController")
public class MusicUploadControlServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, UnsupportedEncodingException {
	    String title = request.getParameter("title");
	    String genre = request.getParameter("genre");
	    String language = request.getParameter("language");
	    
	    String actionValue = request.getParameter("actionValue");
	
	    Music music = new Music(title, genre, language); //Constructor of music object
	    int saveReturn = MusicDAO.save(music);
	
	    if (saveReturn == -1) {
	    	request.setAttribute("errorMsg", "Failed to save music details. Please try again.");
            request.getRequestDispatcher("musicUploadForm.jsp").forward(request, response);
            
        } else {
//        	  request.setAttribute("music", music); 
//            request.getRequestDispatcher("test.jsp").forward(request, response);
        	response.sendRedirect("musicPreview?musicId=" + saveReturn + "&musicTitle=" + URLEncoder.encode(music.getTitle(), "UTF-8") + "&actionValue=" + actionValue);
        }
	    
//	    if (saveReturn != -1) {
//
//	    	response.sendRedirect("musicPreview?musicId=" + saveReturn);
//        } else {
//
//            request.setAttribute("errorMsg", "Failed to save music details. Please try again.");
//            request.getRequestDispatcher("musicUploadForm.jsp").forward(request, response);
//        }
	}
}



/*
@WebServlet("/musicUploadController")
public class MusicUploadControlServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String title = request.getParameter("title");
        String genre = request.getParameter("genre");
        String language = request.getParameter("language");

        // Insert into the database (ignoring audio for now)
        String sql = "INSERT INTO songs (title, genre, language) VALUES (?, ?, ?)";

        try (Connection conn = DBconnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, title);
            stmt.setString(2, genre);
            stmt.setString(3, language);

            int row = stmt.executeUpdate();
            if (row > 0) {
                response.getWriter().println("Music details uploaded successfully.");
            } else {
                response.getWriter().println("Failed to upload music details.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Database error: " + e.getMessage());
        }
    }
}*/
