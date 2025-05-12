/*package amzalMusicUpload;

import java.sql.*;

import amzalDBConnect.DBconnect;

public class MusicDAO {

    public static boolean save(Music music) {
        String sql = "INSERT INTO songs (title, genre, language) VALUES (?, ?, ?)";

        try (Connection conn = DBconnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, music.getTitle());
            stmt.setString(2, music.getGenre());
            stmt.setString(3, music.getLanguage());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}*/

package amzalMusicUpload;

import java.sql.*;
import java.time.*;

import amzalDBConnect.DBconnect;

public class MusicDAO {

	public static int save(Music music) {
	    String sql = "INSERT INTO songs (title, genre, language) VALUES (?, ?, ?)";
	    Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	    int generatedId = -1;

	    try {
	        conn = DBconnect.getConnection();
	        if (conn == null) {
	            System.err.println("Database connection failed.");
	            return -1;
	        }
	        stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);  
	        stmt.setString(1, music.getTitle());
	        stmt.setString(2, music.getGenre());
	        stmt.setString(3, music.getLanguage());

	        int rowsAffected = stmt.executeUpdate();
	        if (rowsAffected > 0) {
	            rs = stmt.getGeneratedKeys();
	            if (rs.next()) {
	                generatedId = rs.getInt(1); // return generated ID
	            }
	        } else {
	        	return -1;
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return -1;
	       
	    } finally {
	        // Close resources
	    	try {
	    		if (rs != null) rs.close();;
	    		if (stmt != null) stmt.close();
	    		if (conn != null) conn.close(); 
          } catch (SQLException e) {
              	e.printStackTrace();
              	return -1;
          }
	    }
	    return generatedId;
	}
	
	
	
	// Retrieves a Music object by ID
    public static Music getMusicById(int id) {
        String sql = "SELECT * FROM songs WHERE id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Open DB connection
            conn = DBconnect.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id); // Set the ID parameter

            rs = stmt.executeQuery(); // Execute SELECT

            // If a matching record is found
            if (rs.next()) {
                String title = rs.getString("title");
                String genre = rs.getString("genre");
                String language = rs.getString("language");

                // Return a Music object constructed with DB values
                return new Music(id, title, genre, language);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            // Close resources
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return null; // Return null if not found
    }
	
    
    
    public static Boolean publish(PublishedMusic pMusic) { 
    	String sql = "UPDATE songs SET price = ?, region = ?, releaseDate = ? WHERE id = ?";  //Dont forget release date
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int rowsAffected = 0;
        Timestamp currentTimestamp = Timestamp.valueOf(LocalDateTime.now());
        
        try {
            // Open DB connection
            conn = DBconnect.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setDouble(1, pMusic.getPrice());       
            stmt.setString(2, pMusic.getRegion());        
            stmt.setTimestamp(3, currentTimestamp);
            stmt.setInt(4, pMusic.getId());
            
            rowsAffected = stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            // Close resources
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } 	
        return rowsAffected > 0; //Returns true if only the sql statement runs properly
    }
}	
	
//    public static boolean save(Music music) {
//        String sql = "INSERT INTO songs (title, genre, language) VALUES (?, ?, ?)";
//        Connection conn = null;
//        PreparedStatement stmt = null;
//
//        try {
//            conn = DBconnect.getConnection();
//            if (conn == null) {
//                System.err.println("Database connection failed.");
//                return false;
//            }
//
//            stmt = conn.prepareStatement(sql);
//            stmt.setString(1, music.getTitle());
//            stmt.setString(2, music.getGenre());
//            stmt.setString(3, music.getLanguage());
//
//            return stmt.executeUpdate() > 0;
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//
//        } finally {
//        	/*
//        	 * Finally closing connection and prepared statement objects
//        	 */
//            try {
//                if (stmt != null) stmt.close();
//                if (conn != null) conn.close(); 
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }


