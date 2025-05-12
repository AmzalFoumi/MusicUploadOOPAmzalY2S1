package amzalMusicUpload;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/musicPublishController")
public class MusicPublishControlServlet extends HttpServlet {

	protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		int musicId = Integer.parseInt(request.getParameter("musicId"));
		String title = request.getParameter("title");
	    String genre = request.getParameter("genre");
	    String language = request.getParameter("language");
		
		Double price = Double.parseDouble(request.getParameter("price"));
	    String region = request.getParameter("region");
	    String releaseDate = request.getParameter("releaseDate");
	    
	    
	    PublishedMusic pMusic = new PublishedMusic(musicId, title, genre, language, region, price, releaseDate);
	    
	    Boolean isPublished = MusicDAO.publish(pMusic);
	    
	    
		if (isPublished) {
			
            request.getRequestDispatcher("test.jsp").forward(request, response);

            
        } else {
        	request.setAttribute("errorMsg", "Failed to publish music details. Please try again.");
        	request.setAttribute("music", pMusic);
            request.getRequestDispatcher("musicPublishForm.jsp").forward(request, response);
        }
	    
	}

}
