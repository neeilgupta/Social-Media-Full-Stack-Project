import javax.swing.*;

public class SignUp implements Runnable {
    private int userID;
    private String userName;
    private String displayName;
    private String password;

    @Override
    public void run() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Please enter your username:"));
        userName = JOptionPane.showInputDialog(panel, "Please enter your username:");
    }

    public int getUserID() {
        return userID;
    }
    public void setUserID(int userID) {
        this.userID = userID;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getDisplayName() {
        return displayName;
    }
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }


}
