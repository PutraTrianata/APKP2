package APKP2.Controller;

import APKP2.Model.*;

public class CatatanKeuanganController {
    private SistemPengguna sistemPengguna;

    public CatatanKeuanganController() {
        this.sistemPengguna = new SistemPengguna();
    }

    public void tambahUser(String username, String password) {
        User user = new User(username, password);
        sistemPengguna.tambahUser(user);
        System.out.println("User berhasil ditambahkan: " + username);
    }

    public boolean login(String username, String password) {
        boolean status = sistemPengguna.login(username, password);
        if (status) {
            System.out.println("Login berhasil untuk user: " + username);
        } else {
            System.out.println("Login gagal. Username atau password salah.");
        }
        return status;
    }

    public void tambahPemasukan(String username, String deskripsi, int jumlah) {
        User user = sistemPengguna.getUser(username);
        if (user != null) {
            Pemasukan pemasukan = new Pemasukan(deskripsi, jumlah);
            user.getCatatanKeuangan().tambahPemasukan(pemasukan);
        } else {
            System.out.println("User tidak ditemukan.");
        }
    }

    public void tambahPengeluaran(String username, String deskripsi, int jumlah) {
        User user = sistemPengguna.getUser(username);
        if (user != null) {
            Pengeluaran pengeluaran = new Pengeluaran(deskripsi, jumlah);
            user.getCatatanKeuangan().tambahPengeluaran(pengeluaran);
        } else {
            System.out.println("User tidak ditemukan.");
        }
    }

    public void tampilkanSaldo(String username) {
        User user = sistemPengguna.getUser(username);
        if (user != null) {
            int saldo = user.getCatatanKeuangan().getSaldo();
            System.out.println("Saldo saat ini untuk user " + username + ": Rp" + saldo);
        } else {
            System.out.println("User tidak ditemukan.");
        }
    }

    public void tampilkanTransaksi(String username) {
        User user = sistemPengguna.getUser(username);
        if (user != null) {
            System.out.println("Daftar Transaksi untuk user " + username + ":");
            for (Transaksi transaksi : user.getCatatanKeuangan().getDaftarTransaksi()) {
                System.out.println(transaksi);
            }
        } else {
            System.out.println("User tidak ditemukan.");
        }
    }

    public void editTransaksi(String username, int index, String deskripsi, int jumlah) {
        User user = sistemPengguna.getUser(username);
        if (user != null) {
            user.getCatatanKeuangan().editTransaksi(index, deskripsi, jumlah);
        } else {
            System.out.println("User tidak ditemukan.");
        }
    }

    public void hapusTransaksi(String username, int index) {
        User user = sistemPengguna.getUser(username);
        if (user != null) {
            user.getCatatanKeuangan().deleteTransaksi(index);
        } else {
            System.out.println("User tidak ditemukan.");
        }
    }

    public void simpanDataKeFile(String namaFile) {
        sistemPengguna.simpanDataKeFile(namaFile);
    }

    public void bacaDataDariFile(String namaFile) {
        sistemPengguna.bacaDataDariFile(namaFile);
    }
}
