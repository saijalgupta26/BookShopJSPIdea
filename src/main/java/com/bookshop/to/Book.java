package com.bookshop.to;

public class Book {
    private String name;
    private int id;
    private String author;
    private String publication;
    public Book(String name, int id, String author, String publication) {
        this.name = name;
        this.id = id;
        this. author= author;
        this.publication = publication;
    }
    public String getAuthor(){
        return author;
    }
    public void setId(int id) {
        this.id=id;
    }
    public String getPublication() {
        return publication;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public void setname(String name) {
        this.name = name;
    }
    public int getId() {
        return id;
    }
    public void setPublication(String publication) {
        this.publication = publication;
    }
    public String getname() {
        return name;
    }






}

