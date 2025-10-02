package Spotify;

import java.util.List;

public class RespuestaSpotify {
	public Artists artists;
	public Tracks tracks;

	public static class Artists {
		public List<Artist> items;
	}

	public static class Artist {
		public String name;
		public Followers followers;
		public List<String> genres;
		public List<Image> images;
	}

	public static class Followers {
		public int total;
	}

	public static class Image {
		public String url;
	}

	public static class Tracks {
		public List<Track> items;
	}

	public static class Track {
		public String id;
		public String name;
		public List<Artist> artists;
		public Album album;
	}

	public static class Album {
		public List<Image> images;
	}
}
