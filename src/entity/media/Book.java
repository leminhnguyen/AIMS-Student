package entity.media;

import java.util.Date;

public class Book extends Media{
    
    String author;
    String coverType;
    String publisher;
    Date publishDate;
    int numOfPages;
    String language;
    String bookCategory;

    public Book (){

    }

    public Book(String author, String coverType, String publisher, Date publishDate, int numOfPages, String language, String bookCategory) {
        this.author = author;
        this.coverType = coverType;
        this.publisher = publisher;
        this.publishDate = publishDate;
        this.numOfPages = numOfPages;
        this.language = language;
        this.bookCategory = bookCategory;
    }


    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCoverType() {
        return this.coverType;
    }

    public void setCoverType(String coverType) {
        this.coverType = coverType;
    }

    public String getPublisher() {
        return this.publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Date getPublishDate() {
        return this.publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public int getNumOfPages() {
        return this.numOfPages;
    }

    public void setNumOfPages(int numOfPages) {
        this.numOfPages = numOfPages;
    }

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getBookCategory() {
        return this.bookCategory;
    }

    public void setBookCategory(String bookCategory) {
        this.bookCategory = bookCategory;
    }

}
