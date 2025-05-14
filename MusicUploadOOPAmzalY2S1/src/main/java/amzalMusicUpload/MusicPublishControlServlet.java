package amzalMusicUpload;

import java.io.IOException;

import javax.servlet.ServletException;
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
	    String albumName = request.getParameter("albumName");
	    
	    int albumId = 0;  //Album ID shows as zero if not associated with any album
	    
	    //Processing of album getting or creation ig user fills album field
	    if (albumName != null && !albumName.trim().isEmpty()) {
	        AlbumDAOInterface albDAO = new AlbumDAO();
	        Album albumReturned = albDAO.getAlbumByName(albumName);

	        if (albumReturned == null) {
	            Album albumCreated = albDAO.createAlbumByName(albumName);
	            if (albumCreated != null) {
	                albumId = albumCreated.getAlbumId();
	            } else {
	                request.setAttribute("errorMsg", "Failed to create album. Please try again.");
	                Music music = new Music(musicId, title, genre, language);
	                request.setAttribute("music", music);
	                request.getRequestDispatcher("musicPublishForm.jsp").forward(request, response);
	                return;
	            }
	        } else {
	        	//Using existing album
	            albumId = albumReturned.getAlbumId();
	            
	            //Increment song count for existing album
	            boolean updated = albDAO.incrementSongCount(albumId);
	            if (!updated) {
	                request.setAttribute("errorMsg", "Failed to update album song count.");
	                Music music = new Music(musicId, title, genre, language);
	                request.setAttribute("music", music);
	                request.getRequestDispatcher("musicPublishForm.jsp").forward(request, response);
	                return;
	            }
	        }
	    } else {
	        // Album field left empty, so we do not associate this music with any album
	        albumName = null; 
	    }
	    
	    
	    
	    PublishedMusic pMusic = new PublishedMusic(musicId, title, genre, language, region, price, releaseDate, albumId, albumName);
	    
	    MusicDAOInterface musDAO = new MusicDAO();
	    Boolean isPublished = musDAO.publish(pMusic);
	    
	    
		if (isPublished) {
			
            request.getRequestDispatcher("test.jsp").forward(request, response);

            
        } else {
        	request.setAttribute("errorMsg", "Failed to publish music details. Please try again.");
        	request.setAttribute("music", pMusic);
            request.getRequestDispatcher("musicPublishForm.jsp").forward(request, response);
        }
	    
	}

}
