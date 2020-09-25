package entity.media;

import java.util.Date;
import java.util.List;

public class CD extends Media{
    
    String artist;
    String recordLabel;
    List<String> trackList;
    String musicType;
    Date releasedDate;

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

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getRecordLabel() {
        return this.recordLabel;
    }

    public void setRecordLabel(String recordLabel) {
        this.recordLabel = recordLabel;
    }

    public List<String> getTrackList() {
        return this.trackList;
    }

    public void setTrackList(List<String> trackList) {
        this.trackList = trackList;
    }

    public String getMusicType() {
        return this.musicType;
    }

    public void setMusicType(String musicType) {
        this.musicType = musicType;
    }

    public Date getReleasedDate() {
        return this.releasedDate;
    }

    public void setReleasedDate(Date releasedDate) {
        this.releasedDate = releasedDate;
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
