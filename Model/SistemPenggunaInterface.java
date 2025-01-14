package APKP2.Model;

public interface SistemPenggunaInterface {
    void tambahUser(User user);
    boolean login(String username, String password);
    User getUser(String username);
    void simpanDataKeFile(String namaFile);
    void bacaDataDariFile(String namaFile);
}
