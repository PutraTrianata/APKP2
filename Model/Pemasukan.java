package APKP2.Model;

public class Pemasukan extends TransaksiBase {
    public Pemasukan(String deskripsi, int jumlah) {
        super(deskripsi, jumlah);
    }

    @Override
    public String toString() {
        return "Pemasukan: " + deskripsi + " - Rp" + jumlah;
    }
}


