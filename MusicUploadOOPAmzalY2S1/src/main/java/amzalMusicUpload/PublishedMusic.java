package amzalMusicUpload;

public class PublishedMusic extends Music {
    private String region;
    private double price;
    private String releaseDate;
    private String albumName;
    private int albumId;

    public PublishedMusic(String title, String genre, String language,
                          String region, double price, String releaseDate, String albumName) {
        super(title, genre, language);
        this.region = region;
        this.price = price;
        this.releaseDate = releaseDate;
        this.albumName = albumName;
    }

	public PublishedMusic(int id, String title, String genre, String language, String region, double price, String releaseDate, int albumId, String albumName) {
		super(id, title, genre, language);
		this.region = region;
        this.price = price;
        this.releaseDate = releaseDate;
        this.albumId = albumId;
        this.albumName = albumName;
	}
	
	//Overloaded constructor if I want to pass a Music POJO directly
	public PublishedMusic(Music music, String region, double price, String releaseDate,  String albumName) {
        super(music.getId(), music.getTitle(), music.getGenre(), music.getLanguage());
        this.region = region;
        this.price = price;
        this.releaseDate = releaseDate;
        this.albumName = albumName;
    }

	public String getRegion() {
        return region;
    }

    public double getPrice() {
        return price;
    }

    public String getReleaseDate() {
        return releaseDate;
    }
    
    public String getAlbumName() {
    	return albumName;
    }
    
    public int getAlbumId() {
    	return albumId;
    }
}