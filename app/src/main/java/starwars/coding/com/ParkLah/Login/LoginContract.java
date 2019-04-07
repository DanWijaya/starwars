package starwars.coding.com.ParkLah.Login;

public interface LoginContract {

    interface View{

        void showSignUpUI();

        void showLoginError();

        void showEmailError();

        void showPasswordError();

        void showMainUI();

        void reset();
    }

    interface Presenter{

        void onLogin(String email, String password);

        boolean validInput(String email, String password);

        boolean validAccount(String email, String password);

        void onSignUp();

    }
}
