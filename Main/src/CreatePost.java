import javax.swing.*;

public class CreatePost implements Runnable{

    public void run(){
        JFrame frame = new JFrame("Make a Post");
        JPanel panel = new JPanel();
        panel.add(new JLabel("Post content"));
        String content = JOptionPane.showInputDialog(panel, "What would you like to say?");
        panel.setBounds(130, 100, 100, 40);
    }
}
