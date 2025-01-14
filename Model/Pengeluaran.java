package APKP2.Model;

public class Pengeluaran extends TransaksiBase {
    public Pengeluaran(String deskripsi, int jumlah) {
        super(deskripsi, jumlah);
    }

    @Override
    public String toString() {
        return "Pengeluaran: " + deskripsi + " - Rp" + jumlah;
    }
}
