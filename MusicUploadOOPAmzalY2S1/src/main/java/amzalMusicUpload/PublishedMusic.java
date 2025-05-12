package amzalMusicUpload;

public class PublishedMusic extends Music {
    private String region;
    private double price;
    private String releaseDate;

    public PublishedMusic(String title, String genre, String language,
                          String region, double price, String releaseDate) {
        super(title, genre, language);
        this.region = region;
        this.price = price;
        this.releaseDate = releaseDate;
    }

	public PublishedMusic(int id, String title, String genre, String language, String region, double price, String releaseDate) {
		super(id, title, genre, language);
		this.region = region;
        this.price = price;
        this.releaseDate = releaseDate;
	}
	
	//Overloaded constructor if I want to pass a Music POJO directly
	public PublishedMusic(Music music, String region, double price, String releaseDate) {
        super(music.getId(), music.getTitle(), music.getGenre(), music.getLanguage());
        this.region = region;
        this.price = price;
        this.releaseDate = releaseDate;
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
}