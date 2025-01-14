package APKP2.Model;

import java.util.ArrayList;

public class CatatanKeuangan implements CatatanKeuanganInterface {
    private ArrayList<Transaksi> dataTransaksi;

    public CatatanKeuangan() {
        this.dataTransaksi = new ArrayList<>();
    }

    @Override
    public void tambahPemasukan(Pemasukan pemasukan) {
        try {
            dataTransaksi.add(pemasukan);
            System.out.println("Pemasukan berhasil ditambahkan: " + pemasukan);
        } catch (Exception e) {
            System.out.println("Gagal menambahkan pemasukan: " + e.getMessage());
        }
    }

    @Override
    public void tambahPengeluaran(Pengeluaran pengeluaran) {
        try {
            dataTransaksi.add(pengeluaran);
            System.out.println("Pengeluaran berhasil ditambahkan: " + pengeluaran);
        } catch (Exception e) {
            System.out.println("Gagal menambahkan pengeluaran: " + e.getMessage());
        }
    }

    @Override
    public int getSaldo() {
        try {
            int saldo = 0;
            for (Transaksi t : dataTransaksi) {
                saldo += t instanceof Pemasukan ? t.getJumlah() : -t.getJumlah();
            }
            return saldo;
        } catch (Exception e) {
            System.out.println("Gagal menghitung saldo: " + e.getMessage());
            return 0;
        }
    }

    @Override
    public ArrayList<Transaksi> getDaftarTransaksi() {
        try {
            return dataTransaksi;
        } catch (Exception e) {
            System.out.println("Gagal mengambil daftar transaksi: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public String toString() {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("Saldo: ").append(getSaldo()).append("\n");
            for (Transaksi t : dataTransaksi) {
                sb.append(t).append("\n");
            }
            return sb.toString();
        } catch (Exception e) {
            return "Gagal menampilkan data keuangan: " + e.getMessage();
        }
    }

    @Override
    public void fromString(String data) {
        try {
            String[] lines = data.split("\n");
            for (String line : lines) {
                if (line.startsWith("Pemasukan:")) {
                    String[] parts = line.split(" - Rp");
                    String deskripsi = parts[0].replace("Pemasukan: ", "").trim();
                    int jumlah = Integer.parseInt(parts[1].trim());
                    dataTransaksi.add(new Pemasukan(deskripsi, jumlah));
                } else if (line.startsWith("Pengeluaran:")) {
                    String[] parts = line.split(" - Rp");
                    String deskripsi = parts[0].replace("Pengeluaran: ", "").trim();
                    int jumlah = Integer.parseInt(parts[1].trim());
                    dataTransaksi.add(new Pengeluaran(deskripsi, jumlah));
                }
            }
        } catch (Exception e) {
            System.out.println("Gagal memuat data dari string: " + e.getMessage());
        }
    }

    @Override
    public void editTransaksi(int index, String deskripsi, int jumlah) {
        try {
            Transaksi transaksi = dataTransaksi.get(index);
            if (transaksi instanceof TransaksiBase) {
                TransaksiBase transaksiBase = (TransaksiBase) transaksi;
                transaksiBase.setDeskripsi(deskripsi);
                transaksiBase.setJumlah(jumlah);
                System.out.println("Transaksi berhasil diubah: " + transaksi);
            } else {
                System.out.println("Jenis transaksi tidak dapat diubah.");
            }
        } catch (Exception e) {
            System.out.println("Gagal mengubah transaksi: " + e.getMessage());
        }
    }

    @Override
    public void deleteTransaksi(int index) {
        try {
            if (index >= 0 && index < dataTransaksi.size()) {
                Transaksi removed = dataTransaksi.remove(index);
                System.out.println("Transaksi berhasil dihapus: " + removed);
            } else {
                System.out.println("Index transaksi tidak valid.");
            }
        } catch (Exception e) {
            System.out.println("Gagal menghapus transaksi: " + e.getMessage());
        }
    }
}