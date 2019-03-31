package starwars.coding.com.ParkLah.Login;

import starwars.coding.com.ParkLah.Database.AccountDB;

public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View LoginView;
    private AccountDB accountDB;


    public LoginPresenter(LoginContract.View view, AccountDB accountDB){
        this.LoginView = view;
        this.accountDB = accountDB;
    }


    @Override
    public boolean validInput(String email, String password){
        if(email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            LoginView.showEmailError();
            return false;
        }

        if(password.isEmpty()){
            LoginView.showPasswordError();
            return false;
        }
        return true;
    }

    @Override
    public void onLogin(String email, String password){
        if(!validInput(email, password)) return;
        if(!validAccount(email, password)) return;

        LoginView.showMainUI();
    }

    @Override
    public boolean validAccount(String email, String password) {
        if (!accountDB.checkUser(email, password)){
            LoginView.showLoginError();
            LoginView.reset();
            return false;
        }

        return true;
    }

    @Override
    public void onSignUp() {
        LoginView.showSignUpUI();
    }

}
