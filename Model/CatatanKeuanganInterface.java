package APKP2.Model;

import java.util.ArrayList;

public interface CatatanKeuanganInterface {
    void tambahPemasukan(Pemasukan pemasukan);
    void tambahPengeluaran(Pengeluaran pengeluaran);
    int getSaldo();
    ArrayList<Transaksi> getDaftarTransaksi();
    void fromString(String data);
    String toString();
    void editTransaksi(int index, String deskripsi, int jumlah);
    void deleteTransaksi(int index);
}
