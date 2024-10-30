
import javax.swing.*;

public class OptionSignUpOrLogin implements Runnable {
    private boolean signUpButtonClicked = false;
    private boolean loginButtonClicked = false;
    private boolean exit = false;


    @Override
    public void run() {
        JFrame frame = new JFrame("Please sign up or login");
        frame.setResizable(true);
        JButton signUp = new JButton("Sign up");
        JButton login = new JButton("Login");
        frame.setLocation(500, 200);

        signUp.setBounds(150, 100, 100, 40);
        login.setBounds(150, 300, 100, 40);

        signUp.addActionListener(_ -> {
            signUpButtonClicked = true;
            System.out.println("Button clicked");
            stop();
        });

        login.addActionListener(_ -> {
            loginButtonClicked = true;
            System.out.println("Button clicked");
            stop();
        });

        frame.add(signUp);
        frame.add(login);
        frame.setSize(400, 500);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        while (!exit) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
                break;
            }
        }
        frame.dispose();
    }


    public boolean isSignUpButtonClicked() {
        return signUpButtonClicked;
    }

    public boolean isLoginButtonClicked() {
        return loginButtonClicked;
    }

    public void stop() {
        exit = true;
    }
}