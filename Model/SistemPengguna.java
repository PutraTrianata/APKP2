package APKP2.Model;

import java.io.*;
import java.util.ArrayList;

public class SistemPengguna implements SistemPenggunaInterface {
    public ArrayList<User> users;

    public SistemPengguna() {
        this.users = new ArrayList<>();
    }

    @Override
    public void tambahUser(User user) {
        users.add(user);
    }

    @Override
    public boolean login(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.checkPassword(password)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public User getUser(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void simpanDataKeFile(String namaFile) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(namaFile))) {
            for (User user : users) {
                writer.write(user.toString());
                writer.write("===\n");
            }
            System.out.println("Data berhasil disimpan.");
        } catch (IOException e) {
            System.out.println("Gagal menyimpan data: " + e.getMessage());
        }
    }

    @Override
    public void bacaDataDariFile(String namaFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(namaFile))) {
            String line;
            User user = null;
            CatatanKeuangan catatan = null;

            while ((line = reader.readLine()) != null) {
                if (line.equals("===")) {
                    if (user != null) {
                        users.add(user);
                    }
                    user = null;
                } else if (line.contains(",")) {
                    String[] parts = line.split(",");
                    user = new User(parts[0].trim(), parts[1].trim());
                    catatan = (CatatanKeuangan) user.getCatatanKeuangan();
                } else if (catatan != null) {
                    catatan.fromString(line);
                }
            }
            if (user != null) {
                users.add(user);
            }
            System.out.println("Data berhasil dimuat.");
        } catch (FileNotFoundException e) {
            System.out.println("File tidak ditemukan. Memulai dengan data baru.");
        } catch (IOException e) {
            System.out.println("Gagal membaca data: " + e.getMessage());
        }
    }
}