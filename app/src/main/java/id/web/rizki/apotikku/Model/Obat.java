package id.web.rizki.apotikku.Model;

/**
 * Created by rizki on 19/12/17.
 */

public class Obat {
    private String Name,Image,Description,Komposisi,Indikasi,Dosis,Efek,Kemasan,Harga,MenuId,Status;

    public Obat() {
    }

    public Obat(String name, String image, String description, String komposisi, String indikasi, String dosis, String efek, String kemasan, String harga, String menuId, String status) {
        Name = name;
        Image = image;
        Description = description;
        Komposisi = komposisi;
        Indikasi = indikasi;
        Dosis = dosis;
        Efek = efek;
        Kemasan = kemasan;
        Harga = harga;
        MenuId = menuId;
        Status = status;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getKomposisi() {
        return Komposisi;
    }

    public void setKomposisi(String komposisi) {
        Komposisi = komposisi;
    }

    public String getIndikasi() {
        return Indikasi;
    }

    public void setIndikasi(String indikasi) {
        Indikasi = indikasi;
    }

    public String getDosis() {
        return Dosis;
    }

    public void setDosis(String dosis) {
        Dosis = dosis;
    }

    public String getEfek() {
        return Efek;
    }

    public void setEfek(String efek) {
        Efek = efek;
    }

    public String getKemasan() {
        return Kemasan;
    }

    public void setKemasan(String kemasan) {
        Kemasan = kemasan;
    }

    public String getHarga() {
        return Harga;
    }

    public void setHarga(String harga) {
        Harga = harga;
    }

    public String getMenuId() {
        return MenuId;
    }

    public void setMenuId(String menuId) {
        MenuId = menuId;
    }
}
