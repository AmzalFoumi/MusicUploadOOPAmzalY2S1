package amzalMusicUpload;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;

/*
 * This filter runs everytime MusicPublishControlServlet is called
 */
@WebFilter("/musicPublishController")
public class MusicPublishValidateFilter extends HttpFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		 // Get form inputs
		Integer musicId = Integer.parseInt(request.getParameter("musicId"));
        String title = request.getParameter("title");
        String genre = request.getParameter("genre");
        String language = request.getParameter("language");
        String priceStr = request.getParameter("price");
        String region = request.getParameter("region");
        String albumName = request.getParameter("albumName");
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
        if (language == null || language.trim().isEmpty() || !isValidLanguage(language)) {
            errorMsg = "Invalid language. Allowed: English, Sinhala, Tamil, Chinese.";
            valid = false;
        }
        // Price validation
        if (priceStr == null || priceStr.trim().isEmpty() || !isValidPositiveDouble(priceStr)) {
            errorMsg = "Invalid price. Must be a positive number.";
            valid = false;
        }
        // Region validation
        if (region == null || region.trim().isEmpty() || !isValidRegion(region)) {
            errorMsg = "Invalid region. Allowed: Europe, Asia, America, Africa, Australia.";
            valid = false;
        }

        if (!valid) {
        	
        	Music music = new Music(musicId, title, genre, language);
        	request.setAttribute("music", music); //Have to create this music object and pass it back to the publish form, otherwise error in loading the preview in the form
        	
            request.setAttribute("errorMsg", errorMsg);
            request.getRequestDispatcher("/musicPublishForm.jsp").forward(request, response);
        } else {
            chain.doFilter(request, response);
        }
    }

    private boolean isValidLanguage(String lang) {
        String lowerLang = lang.toLowerCase();
        return lowerLang.equals("english") || lowerLang.equals("sinhala") ||
               lowerLang.equals("tamil") || lowerLang.equals("chinese");
    }

    private boolean isValidRegion(String region) {
        String lowerRegion = region.toLowerCase();
        return lowerRegion.equals("europe") || lowerRegion.equals("asia") ||
               lowerRegion.equals("america") || lowerRegion.equals("africa") ||
               lowerRegion.equals("australia");
    }

    private boolean isValidPositiveDouble(String value) {
        try {
            double d = Double.parseDouble(value);
            return d > 0;  // Will return true if d is a double value AND positive
        } catch (NumberFormatException e) {
            return false;
        }
    }
	
	
}
