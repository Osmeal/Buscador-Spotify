package Spotify;

import java.util.List;

public class RespuestaSpotify {
    public Artists artists;

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
}
