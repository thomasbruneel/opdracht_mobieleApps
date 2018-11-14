package com.example.thomas.slidingnavigationmenu.Room;


@Entity
public class ZoekertjeToevoegen {

    @Entity
    public class Movies {
        @NonNull
        @PrimaryKey
        private String movieId;
        private String movieName;

        public Movies() {
        }

        public String getMovieId() { return movieId; }
        public void setMovieId(String movieId) { this.movieId = movieId; }
        public String getMovieName() { return movieName; }
        public void setMovieName (String movieName) { this.movieName = movieName; }
    }
}
