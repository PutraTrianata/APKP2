package APKP2.View;

import APKP2.Controller.CatatanKeuanganController;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CatatanKeuanganView {
    private CatatanKeuanganController controller;
    private Scanner scanner;

    public CatatanKeuanganView() {
        this.controller = new CatatanKeuanganController();
        this.scanner = new Scanner(System.in);
    }

    public void tampilkanMenu() {
        final String NAMA_FILE = "APKP2/dataKeuanganUser.txt";
        controller.bacaDataDariFile(NAMA_FILE);

        while (true) {
            try {
                System.out.println("\n=== Menu Utama ===");
                System.out.println("1. Buat Pengguna");
                System.out.println("2. Login");
                System.out.println("3. Keluar");
                System.out.print("Pilihan Anda: ");
                int pilihan = scanner.nextInt();
                scanner.nextLine();

                switch (pilihan) {
                    case 1 -> tambahUser();
                    case 2 -> login();
                    case 3 -> {
                        controller.simpanDataKeFile(NAMA_FILE);
                        System.out.println("Keluar dari program. Sampai jumpa!");
                        return;
                    }
                    default -> System.out.println("Pilihan tidak valid.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Input tidak valid, silakan coba lagi.");
                scanner.nextLine(); // Clear buffer
            }
        }
    }

    private void tambahUser() {
        System.out.print("Masukkan Username: ");
        String username = scanner.nextLine();
        System.out.print("Masukkan Password: ");
        String password = scanner.nextLine();
        controller.tambahUser(username, password);
        System.out.println("Pengguna berhasil dibuat!");
    }

    private void login() {
        System.out.print("Masukkan Username: ");
        String username = scanner.nextLine();
        System.out.print("Masukkan Password: ");
        String password = scanner.nextLine();

        if (controller.login(username, password)) {
            System.out.println("Login berhasil!");
            tampilkanMenuKeuangan(username);
        } else {
            System.out.println("Login gagal. Username atau password salah.");
        }
    }

    private void tampilkanMenuKeuangan(String username) {
        while (true) {
            try {
                System.out.println("\n=== Menu Keuangan ===");
                System.out.println("1. Tambah Pemasukan");
                System.out.println("2. Tambah Pengeluaran");
                System.out.println("3. Lihat Transaksi");
                System.out.println("4. Edit Transaksi");
                System.out.println("5. Hapus Transaksi");
                System.out.println("6. Logout");
                System.out.print("Pilihan Anda: ");
                int pilihan = scanner.nextInt();
                scanner.nextLine();

                switch (pilihan) {
                    case 1 -> tambahPemasukan(username);
                    case 2 -> tambahPengeluaran(username);
                    case 3 -> lihatTransaksi(username);
                    case 4 -> editTransaksi(username);
                    case 5 -> hapusTransaksi(username);
                    case 6 -> {
                        System.out.println("Logout berhasil.");
                        return;
                    }
                    default -> System.out.println("Pilihan tidak valid.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Input tidak valid, silakan coba lagi.");
                scanner.nextLine(); // Clear buffer
            }
        }
    }

    private void tambahPemasukan(String username) {
        System.out.print("Deskripsi Pemasukan: ");
        String deskripsi = scanner.nextLine();
        System.out.print("Jumlah Pemasukan: ");
        int jumlah = scanner.nextInt();
        scanner.nextLine();
        controller.tambahPemasukan(username, deskripsi, jumlah);
    }

    private void tambahPengeluaran(String username) {
        System.out.print("Deskripsi Pengeluaran: ");
        String deskripsi = scanner.nextLine();
        System.out.print("Jumlah Pengeluaran: ");
        int jumlah = scanner.nextInt();
        scanner.nextLine();
        controller.tambahPengeluaran(username, deskripsi, jumlah);
    }

    private void lihatTransaksi(String username) {
        System.out.println("\n=== Daftar Transaksi ===");
        controller.tampilkanTransaksi(username);
        controller.tampilkanSaldo(username);
    }

    private void editTransaksi(String username) {
        System.out.print("Masukkan index transaksi yang ingin diubah: ");
        int index = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Deskripsi baru: ");
        String deskripsi = scanner.nextLine();
        System.out.print("Jumlah baru: ");
        int jumlah = scanner.nextInt();
        scanner.nextLine();
        controller.editTransaksi(username, index - 1, deskripsi, jumlah); // Koreksi indeks
    }

    private void hapusTransaksi(String username) {
        System.out.print("Masukkan index transaksi yang ingin dihapus: ");
        int index = scanner.nextInt();
        scanner.nextLine();
        controller.hapusTransaksi(username, index - 1); // Koreksi indeks
    }

    public static void main(String[] args) {
        CatatanKeuanganView view = new CatatanKeuanganView();
        view.tampilkanMenu();
    }
}
