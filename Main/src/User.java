public class User {

    public static void main(String[] args) {
        Thread SUoL = new Thread(new OptionSignUpOrLogin());
        Thread SignUp = new Thread(new SignUp());
        SUoL.start();
        OptionSignUpOrLogin osul  = new OptionSignUpOrLogin();
        if (osul.isSignUpButtonClicked()) {
            SUoL.start();
            SignUp.start();
        }
    }
}