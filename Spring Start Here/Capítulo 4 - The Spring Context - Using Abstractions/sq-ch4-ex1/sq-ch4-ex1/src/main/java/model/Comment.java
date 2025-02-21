package model;

public class Comment {
    private String author;
    private String text;

    public Comment() {}
    public Comment(String author, String text) {
        this.author = author;
        this.text = text;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
