package amzalMusicUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;


@WebServlet("/musicPreview")
public class MusicPreviewControlServlet extends HttpServlet {

    // Handles GET requests like musicPreview?musicId=5
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Read the details from the query string
        String idParam = request.getParameter("musicId");
        String titleParam = request.getParameter("title");
        
        String actionValue = request.getParameter("actionValue");

        if (idParam != null) {
            try {
                int id = Integer.parseInt(idParam); // Convert to int

                // Load music from DB
                MusicDAOInterface DAO = new MusicDAO();
                Music music = DAO.getMusicById(id);
             
                if (music != null) {
                	
                	request.setAttribute("music", music);
                	
                	if ("upload".equals(actionValue)) {
                    	
                        request.getRequestDispatcher("musicUploadPreview.jsp").forward(request, response);
                        return;
                    }
                	
                	else if ("publish".equals(actionValue)) {
                		
                        request.getRequestDispatcher("musicPublishForm.jsp").forward(request, response);
                        return;
                	}
                	
                	else if ("delete".equals(actionValue)) {
                		request.getRequestDispatcher("musicDeleteConfirm.jsp").forward(request, response);
                        return;
                	}
                }

            } catch (NumberFormatException e) {
                e.printStackTrace(); // Log bad ID format
            }
        }

        // If anything fails, redirect back to dashboard
//        response.sendRedirect("artistDashboard.jsp?error=invalid_music");
    }
}
