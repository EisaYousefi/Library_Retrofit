package com.broa.broasjapplication;

public class Books {
    private String id;
    private String bookName;
    private String authorName;
    private String publishers;
    private int price;
    public static final String KEY_ID="id";
    public static final String KEY_BOOKNAME="bookName";
    public static final String KEY_AUTHORNAME="authorName";
    public static final String KEY_PUBLISHERS="publishers";
    public static final String KEY_PRICE="price";

    public static final String BOOKTABLE = "create table if not exists "
            + DatabaseHlper.TABLE_BOOK
            + "(" + Books.KEY_ID + " string primary key,"
            + Books.KEY_AUTHORNAME + " text not null,"
            + Books.KEY_BOOKNAME + " text not null,"
            + Books.KEY_PRICE + " int,"
            + Books.KEY_PUBLISHERS + " text);";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getPublishers() {
        return publishers;
    }

    public void setPublishers(String publishers) {
        this.publishers = publishers;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "نام کتاب :"+bookName +"\n"
                +"کد کتاب :"+id+"\n"
                +"نام نویسنده :"+"   "+authorName+"\n";
    }

}
