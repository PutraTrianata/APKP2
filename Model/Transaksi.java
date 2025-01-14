package APKP2.Model;

public interface Transaksi {
    String getDeskripsi();
    int getJumlah();
    String toString();
}

abstract class TransaksiBase implements Transaksi {
    protected String deskripsi;
    protected int jumlah;

    public TransaksiBase(String deskripsi, int jumlah) {
        this.deskripsi = deskripsi;
        this.jumlah = jumlah;
    }

    @Override
    public String getDeskripsi() {
        return deskripsi;
    }

    @Override
    public int getJumlah() {
        return jumlah;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }
}



