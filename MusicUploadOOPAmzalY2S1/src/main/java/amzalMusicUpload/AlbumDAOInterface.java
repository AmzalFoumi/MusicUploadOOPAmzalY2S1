package amzalMusicUpload;

public interface AlbumDAOInterface {
	public Album getAlbumByName(String albumName);
	
	public Album createAlbumByName(String albumName);

	boolean incrementSongCount(int albumId);
}
