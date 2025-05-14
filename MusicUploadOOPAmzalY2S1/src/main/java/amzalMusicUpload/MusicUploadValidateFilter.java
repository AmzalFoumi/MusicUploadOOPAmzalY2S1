package amzalMusicUpload;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
 * This filter is to validate data input in the music upload form
 */

@WebFilter("/musicUploadController")
public class MusicUploadValidateFilter extends HttpFilter {

    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        // Get form inputs
        String title = request.getParameter("title");
        String genre = request.getParameter("genre");
        String language = request.getParameter("language");

        // Validation flags
        boolean valid = true;
        String errorMsg = "";

        // Title validation
        if (title == null || title.trim().isEmpty() || title.length() > 255) {
            errorMsg = "Invalid title. Must be a string under 255 characters.";
            valid = false;
        }

        // Genre validation
        if (genre == null || genre.trim().isEmpty() || genre.length() > 255) {
            errorMsg = "Invalid genre. Must be a string under 255 characters.";
            valid = false;
        }

        // Language validation
        if (language == null || language.trim().isEmpty() ||  !isValidLanguage(language)) {
            errorMsg = "Invalid language. Allowed: English, Sinhala, Tamil, Chinese.";
            valid = false;
        }

        if (!valid) {
            request.setAttribute("errorMsg", errorMsg);
            // Forward user back to the JSP with error message
            
            request.getRequestDispatcher("/musicUploadForm.jsp").forward(request, response);
        } else {
            // Continue if all inputs are valid
            chain.doFilter(request, response);
        }
    }

    private boolean isValidLanguage(String lang) {
        String lowerLang = lang.toLowerCase();
        return lowerLang.equals("english") || lowerLang.equals("sinhala") ||
               lowerLang.equals("tamil") || lowerLang.equals("chinese");
    }
}

