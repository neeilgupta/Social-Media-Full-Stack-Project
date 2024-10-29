import javax.swing.*;
import java.awt.event.*;

public class OptionSignUpOrLogin implements Runnable {
    private static boolean signUpButtonClicked = false;
    private static boolean loginButtonClicked = false;

    @Override
    public void run() {
        JFrame frame = new JFrame("Please sign up or login");
        JButton signUp = new JButton("Sign up");
        JButton login = new JButton("Login");

        signUp.setBounds(130, 100, 100, 40);
        login.setBounds(130, 300, 100, 40);

        signUp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                signUpButtonClicked = true;
                System.out.println("Button clicked");
            }
        });

        login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loginButtonClicked = true;
                System.out.println("Button clicked");

            }
        });

        frame.add(signUp);
        frame.add(login);
        frame.setSize(400, 500);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public boolean isSignUpButtonClicked() {
        return signUpButtonClicked;
    }

    public boolean isLoginButtonClicked() {
        return loginButtonClicked;
    }

}