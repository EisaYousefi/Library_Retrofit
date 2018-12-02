package com.broa.broasjapplication;

public class Amanat {
    public static final String KEY_NAME_AMANAT        ="name_amanat";
    public static final String KEY_ID_NAME_AMANAT     ="id_name_amanat";
    public static final String KEY_BOOK_AMANAT        ="name_book_amanat";
    public static final String KEY_ID_BOOK_AMANAT     ="id_book_amanat";
    public static final String KEY_WRITER_BOOK_AMANAT ="writername_book_amanat";
    public static final String KEY_TEL_AMANAT="tel_amanat";


    public static final String BTABLEAMANAT = "create table if not exists "
            + DatabaseHlper.TABLE_AMANAT
            + "(" + KEY_ID_BOOK_AMANAT + " text,"
            + KEY_BOOK_AMANAT + " text not null,"
            + KEY_WRITER_BOOK_AMANAT + " text not null,"
            + KEY_ID_NAME_AMANAT + " text,"
            + KEY_NAME_AMANAT + " text,"
            + KEY_TEL_AMANAT + " text);";


    private String name_book_amanat;
    private String id_book_amanat;
    private String writername_book_amanat;
    private String tel_amanat;
    private String name_amanat;
    private String id_name_amanat;

    public String getTel_amanat() {
        return tel_amanat;
    }

    public void setTel_amanat(String tel_amanat) {
        this.tel_amanat = tel_amanat;
    }

    public String getWritername_book_amanat() {
        return writername_book_amanat;
    }

    public void setWritername_book_amanat(String writername_book_amanat) {
        this.writername_book_amanat = writername_book_amanat;
    }




    public String getName_amanat() {
        return name_amanat;
    }

    public void setName_amanat(String name_amanat) {
        this.name_amanat = name_amanat;
    }

    public String getId_name_amanat() {
        return id_name_amanat;
    }

    public void setId_name_amanat(String id_name_amanat) {
        this.id_name_amanat = id_name_amanat;
    }

    public String getName_book_amanat() {
        return name_book_amanat;
    }

    public void setName_book_amanat(String name_book_amanat) {
        this.name_book_amanat = name_book_amanat;
    }

    public String getId_book_amanat() {
        return id_book_amanat;
    }


    @Override
    public String toString() {
        return           "کد کتاب: "+id_book_amanat+"   " +
                "نام کتاب: "        + name_book_amanat+"\n"+
                "نام امانت گیرنده: "+ name_amanat +"\n"+
                "شماره تلفن: "      +tel_amanat;
    }

    public void setId_book_amanat(String id_book_amanat) {
        this.id_book_amanat = id_book_amanat;
    }
}
