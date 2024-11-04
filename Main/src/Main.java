
/**
 * Social media project
 * <p>
 * Main method, runs eveything
 * <p>
 * Hossein Hatami
 *
 * @version November 3rd, 2024
 *
 */


public class Main {
    public static void main(String[] args) {
        OptionSignUpOrLogin osul = new OptionSignUpOrLogin();
        Thread SUoL = new Thread(osul);
        Thread signUp = new Thread(new SignUp());
        Thread login = new Thread(new Login());
        SUoL.start();
        try {
            SUoL.join();
        } catch (InterruptedException ie) {
            throw new RuntimeException(ie);
        }
        if (osul.isSignUpButtonClicked()) {
            System.out.println("Starting SignUp thread..."); //delete later
            signUp.start();
        } else if (osul.isLoginButtonClicked()) {
            System.out.println("Starting Login thread..."); // delete later
            login.start();
        }
    }
}