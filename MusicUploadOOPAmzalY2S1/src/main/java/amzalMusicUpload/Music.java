package amzalMusicUpload;

public class Music {
    private int id;
	private String title;
    private String genre;
    private String language;
	
    public Music(String title, String genre, String language) {
		
		this.title = title;
		this.genre = genre;
		this.language = language;
	}
    

	public Music(int id, String title, String genre, String language) {
		
		this.id = id;
		this.title = title;
		this.genre = genre;
		this.language = language;
	}



	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return the genre
	 */
	public String getGenre() {
		return genre;
	}

	/**
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}  
    
}

