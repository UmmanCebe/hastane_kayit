package com.company;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException {
        KullaniciGiris kullaniciGiris = new KullaniciGiris();


        System.out.println("Hosgeldiniz lutfen giris yapiniz");
        kullaniciGiris.dogrula();



        Scanner sc = new Scanner(System.in);
        int menu = 0;

        while(menu != 5)
        {
            System.out.println("\n1-Hasta Ekle");
            System.out.println("2-Hasta Sil");
            System.out.println("3-Hasta Guncelle");
            System.out.println("4-Hasta Ara");
            System.out.println("5-Cikis");

            menu = sc.nextInt();
            switch (menu){
                case 1:
                    kullaniciGiris.hastaEkle();
                    break;

                case 2:
                    kullaniciGiris.hastaSil();
                    break;

                case 3:
                    kullaniciGiris.hastaGuncelle();
                    break;

                case 4:
                    kullaniciGiris.hastaAra();
                    break;

                case 5:
                    break;
            }
        }



    }
}
