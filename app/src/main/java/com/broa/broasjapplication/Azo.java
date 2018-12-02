package com.broa.broasjapplication;

public class Azo {
    public static final String KEY_ID_AZO="id_azo";
    public static final String KEY_NAMEFAMIL="nameFamil";
    public static final String KEY_TEL="tel";

    public static final String AZOTABLE = "create table if not exists "
            + DatabaseHlper.TABLE_AZO
            + "(" + Azo.KEY_ID_AZO + " string primary key,"
            + Azo.KEY_NAMEFAMIL + " text not null,"
            + Azo.KEY_TEL + " text not null);";

    private String id_azo;
    private String nameFamil;
    private  String tel;

    public String getId_azo() {
        return id_azo;
    }

    public void setId_azo(String id_azo) {
        this.id_azo = id_azo;
    }

    public String getNameFamil() {
        return nameFamil;
    }

    public void setNameFamil(String nameFamil) {
        this.nameFamil = nameFamil;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }


    @Override
    public String toString() {
        return "کد: "+id_azo+"\n"
                     +"نام: "+nameFamil+"\n"
                     +"شماره تماس: " +tel;
    }
}
