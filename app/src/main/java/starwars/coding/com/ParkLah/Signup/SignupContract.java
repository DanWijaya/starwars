package starwars.coding.com.ParkLah.Signup;

public interface SignupContract {

    interface View{

        void showNextUI();

        void showNameError();

        void showEmailError();

        void showPasswordError();

        void showMismatchError();

        void showAccountError();

        void reset();


    }

    interface Presenter{

        void onSignup(String name, String email, String password, String confrimPassword);

        boolean validInput(String name, String email, String password, String confrimPassword);

        boolean validAccount(String email);

    }
}
