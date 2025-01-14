package APKP2.Model;

public class User {
    public String username;
    public String password;
    public CatatanKeuanganInterface catatanKeuangan;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.catatanKeuangan = new CatatanKeuangan();
    }

    public String getUsername() {
        return username;
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    public CatatanKeuanganInterface getCatatanKeuangan() {
        return catatanKeuangan;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(username).append(",").append(password).append("\n");
        sb.append(catatanKeuangan);
        return sb.toString();
    }
}