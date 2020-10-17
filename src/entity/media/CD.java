package entity.media;

import java.util.Date;
import java.util.List;

public class CD extends Media{
    
    String artist;
    String recordLabel;
    List<String> trackList;
    String musicType;
    Date releasedDate;

    public CD (){

    }

    public CD (int id, String title, String category, int price, int quantity, String type,
              String artist, String recordLabel, List<String> trackList, String musicType, Date releasedDate) {
        super(id, title, category, price, quantity, type);
        this.artist = artist;
        this.recordLabel = recordLabel;
        this.trackList = trackList;
        this.musicType = musicType;
        this.releasedDate = releasedDate;
    }

    public CD (int id, String title, String category, int price, int quantity, String type, 
             String artist, String recordLabel){
        super(id, title, category, price, quantity, type);
        this.artist = artist;
        this.recordLabel = recordLabel;
    }


    public String getArtist() {
        return this.artist;
    }

    public CD setArtist(String artist) {
        this.artist = artist;
        return this;
    }

    public String getRecordLabel() {
        return this.recordLabel;
    }

    public CD setRecordLabel(String recordLabel) {
        this.recordLabel = recordLabel;
        return this;
    }

    public List<String> getTrackList() {
        return this.trackList;
    }

    public CD setTrackList(List<String> trackList) {
        this.trackList = trackList;
        return this;
    }

    public String getMusicType() {
        return this.musicType;
    }

    public CD setMusicType(String musicType) {
        this.musicType = musicType;
        return this;
    }

    public Date getReleasedDate() {
        return this.releasedDate;
    }

    public CD setReleasedDate(Date releasedDate) {
        this.releasedDate = releasedDate;
        return this;
    }


    @Override
    public String toString() {
        return "{" +
            super.toString() +
            " artist='" + artist + "'" +
            ", recordLabel='" + recordLabel + "'" +
            ", trackList='" + trackList + "'" +
            ", musicType='" + musicType + "'" +
            ", releasedDate='" + releasedDate + "'" +
            "}";
    }

}
