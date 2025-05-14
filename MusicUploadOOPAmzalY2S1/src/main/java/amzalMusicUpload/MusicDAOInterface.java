package amzalMusicUpload;

public interface MusicDAOInterface {
    
	//Uploading Music
	int save(Music music);
	
	//Retrieving Music by ID to display anywhere
    Music getMusicById(int id);
    
    //Publishing the uploaded music
    Boolean publish(PublishedMusic pMusic);
    
    //Deleting the music
    Boolean delete(int musicId);
	
}
