public class User {

    public static void main(String[] args) {
        Thread SUoL = new Thread(new OptionSignUpOrLogin());
        Thread SignUp = new Thread(new SignUp());
        Thread Login = new Thread(new Login());
        SUoL.start();
        OptionSignUpOrLogin osul  = new OptionSignUpOrLogin();
        if (osul.isSignUpButtonClicked()) {
            SUoL.interrupt();
            SignUp.start();
        } else if (osul.isLoginButtonClicked()) {
            SUoL.interrupt();

        }
    }
}