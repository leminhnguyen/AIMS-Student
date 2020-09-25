package entity.media;

import java.util.Date;

public class DVD extends Media{
    
    String discType;
    String director;
    int runtime;
    String studio;
    String subtitles;
    Date releasedDate;
    String filmType;

    public DVD(int id, String title, String category, int price, int quantity, String type,
               String discType, String director, int runtime, String studio, String subtitles, Date releasedDate, String filmType) {
        super(id, title, category, price, quantity, type);
        this.discType = discType;
        this.director = director;
        this.runtime = runtime;
        this.studio = studio;
        this.subtitles = subtitles;
        this.releasedDate = releasedDate;
        this.filmType = filmType;
    }


    public String getDiscType() {
        return this.discType;
    }

    public void setDiscType(String discType) {
        this.discType = discType;
    }

    public String getDirector() {
        return this.director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getRuntime() {
        return this.runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public String getStudio() {
        return this.studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getSubtitles() {
        return this.subtitles;
    }

    public void setSubtitles(String subtitles) {
        this.subtitles = subtitles;
    }

    public Date getReleasedDate() {
        return this.releasedDate;
    }

    public void setReleasedDate(Date releasedDate) {
        this.releasedDate = releasedDate;
    }

    public String getFilmType() {
        return this.filmType;
    }

    public void setFilmType(String filmType) {
        this.filmType = filmType;
    }


    @Override
    public String toString() {
        return "{" +
            super.toString() +
            " discType='" + discType + "'" +
            ", director='" + director + "'" +
            ", runtime='" + runtime + "'" +
            ", studio='" + studio + "'" +
            ", subtitles='" + subtitles + "'" +
            ", releasedDate='" + releasedDate + "'" +
            ", filmType='" + filmType + "'" +
            "}";
    }    

}
