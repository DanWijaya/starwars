package starwars.coding.com.ParkLah.Login;

import android.content.Context;

import starwars.coding.com.ParkLah.Database.DataBase;
import starwars.coding.com.ParkLah.Database.DataBaseManager;

public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View loginView;
    private DataBase dataBase;


    public LoginPresenter(LoginContract.View view){
        this.loginView = view;
        this.dataBase = DataBaseManager.getInstance((Context) loginView);
    }

    /**
     * This method validates the email ID and password that would have been provided by the user
     * @param email The entered Email-ID
     * @param password The entered Password
     * @return True or false depending on validation result
     */
    @Override
    public boolean validInput(String email, String password){
        if(email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            loginView.showEmailError();
            return false;
        }

        if(password.isEmpty()){
            loginView.showPasswordError();
            return false;
        }
        return true;
    }

    /**
     * This method directs the user to the main UI if the entered information is valid and registered
     * @param email User entered email ID
     * @param password User entered password
     */
    @Override
    public void onLogin(String email, String password){
        if(!validInput(email, password)) return;
        if(!validAccount(email, password)) return;

        loginView.showMainUI();
    }

    /**
     * This method checks whether the entered credentials belong to a registered account
     * @param email User entered email ID
     * @param password User entered password
     * @return True or false based on result
     */
    @Override
    public boolean validAccount(String email, String password) {
        if (!dataBase.checkUser(email, password)){
            loginView.showLoginError();
            loginView.reset();
            return false;
        }

        return true;
    }

    /**
     * This method displays the UI for the user to sign up
     */
    @Override
    public void onSignUp() {
        loginView.showSignUpUI();
    }

}
