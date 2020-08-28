package com.company;

import java.sql.*;
import java.util.Scanner;

public class KullaniciGiris {


    private Connection baglan() throws SQLException {
        /*** Bağlantı kurulumu **/
        Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/hastanedb",
                "postgres", "umman123");
        if (conn != null)
            System.out.println("Veritabanına bağlandı!");
        else
            System.out.println("Bağlantı girişimi başarısız!");

        return conn;
    }


    public void dogrula() throws SQLException {
        Connection conn = baglan();

        String sql= "SELECT * FROM \"Admin\"";


        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);


        conn.close();

        Scanner sc = new Scanner(System.in);

        String kontrolKullaniciAdi="";
        String kontrolSifre ="";

        int kontrol = 0;

        while(kontrol !=1) {

            System.out.println("Kullanici Adi Giriniz:");
            String kullaniciAdi = sc.next();
            System.out.println("Sifre Giriniz:");
            String sifre = sc.next();


            while(rs.next()) {
                kontrolKullaniciAdi = rs.getString("adi");
                kontrolSifre = rs.getString("sifre");


                if (kontrolKullaniciAdi.equals(kullaniciAdi) && kontrolSifre.equals(sifre)) {
                    System.out.println("Dogrulandi");
                    kontrol = 1;
                }

            }

            if(kontrol == 0)
                System.out.println("Hatali sifre");

        }



        rs.close();
        stmt.close();
    }

    public void hastaAra() throws SQLException {

        Connection conn = baglan();

        System.out.println("Aranacak hastanin no su");
        Scanner sc = new Scanner(System.in);

        String arananHasta = sc.next();

        String query= "SELECT * FROM \"Hastalar\" WHERE \"hastaId\" = "+arananHasta;


        /*** Sorgu çalıştırma ***/
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);


        /*** Bağlantı sonlandırma ***/
        conn.close();


        while(rs.next())
        {

            int hasta_id = rs.getInt("hastaId");
            String hasta_adi = rs.getString("hastaAdi");
            String doktorunun_adi = rs.getString("doktorununAdi");
            String aldigi_ilaclar = rs.getString("aldigiIlaclar");

            System.out.println("\thasta_id : " + hasta_id + "\thasta_adi : " + hasta_adi+
                    "\tdoktorunun_adi : " + doktorunun_adi + "\taldigi_ilaclar : " + aldigi_ilaclar);
        }

        /*** Kaynakları serbest bırak ***/
        rs.close();
        stmt.close();

    }

    public void hastaGuncelle() throws SQLException {

        String hasta_id, degisecek_deger, yeni_deger;
        System.out.println("Hangi nolu hasta icin islem yapmak istiyorsunuz?");
        Scanner sc = new Scanner(System.in);
        hasta_id = sc.next();

        System.out.println("Degisecek sutun adi?");
        degisecek_deger = sc.next();
        System.out.println("Yeni Deger: ");
        yeni_deger = sc.next();

        Connection conn = baglan();

        String sql= "UPDATE \"Hastalar\" SET \"" +degisecek_deger+ "\" = '" +yeni_deger+ "' WHERE \"hastaId\" = " +hasta_id;


        /*** Sorgu çalıştırma ***/
        Statement stmt = conn.createStatement();
        int sonuc = stmt.executeUpdate(sql);

        if(sonuc == 1)
            System.out.println("Güncelleme basarili");
        else
            System.out.println("islem basarisiz oldu");

        /*** Bağlantı sonlandırma ***/
        conn.close();

        /*** Kaynakları serbest bırak ***/
        stmt.close();

    }

    public void hastaSil() throws SQLException {

        String hasta_id;
        System.out.println("Hangi nolu hastayi silmek istiyorsunuz?");

        Scanner sc = new Scanner(System.in);
        hasta_id = sc.next();

        Connection conn = baglan();

        String sql= "DELETE FROM \"Hastalar\" WHERE \"hastaId\" = " +hasta_id;


        /*** Sorgu çalıştırma ***/
        Statement stmt = conn.createStatement();
        int sonuc = stmt.executeUpdate(sql);

        if(sonuc == 1)
            System.out.println("Silme basarili");
        else
            System.out.println("islem basarisiz oldu");

        /*** Bağlantı sonlandırma ***/
        conn.close();

        /*** Kaynakları serbest bırak ***/
        stmt.close();

    }

    public void hastaEkle() throws SQLException {

        String  hasta_adi,doktorunun_adi,aldigi_ilaclar;
        Scanner sc = new Scanner(System.in);

        System.out.println("hasta adini giriniz");
        hasta_adi = sc.nextLine();
        System.out.println("doktorunun adini giriniz");
        doktorunun_adi = sc.nextLine();
        System.out.println("alinan ilaclar");
        aldigi_ilaclar = sc.nextLine();


        Connection conn = baglan();

        String sql= "INSERT INTO \"Hastalar\" (\"hastaAdi\", \"doktorununAdi\", \"aldigiIlaclar\")" +
                "VALUES ('"+hasta_adi+"', '"+doktorunun_adi+"' , '"+aldigi_ilaclar+" ')";


        /*** Sorgu çalıştırma ***/
        Statement stmt = conn.createStatement();
        int sonuc = stmt.executeUpdate(sql);

        if(sonuc == 1)
            System.out.println("Ekleme basarili");
        else
            System.out.println("islem basarisiz oldu");

        /*** Bağlantı sonlandırma ***/
        conn.close();

        /*** Kaynakları serbest bırak ***/
        stmt.close();

    }

}
