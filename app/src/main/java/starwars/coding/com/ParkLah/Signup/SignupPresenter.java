package starwars.coding.com.ParkLah.Signup;

import starwars.coding.com.ParkLah.Database.AccountDB;
import starwars.coding.com.ParkLah.Entity.User;
import starwars.coding.com.ParkLah.Login.LoginContract;

public class SignupPresenter implements SignupContract.Presenter {

    private SignupContract.View signupView;
    private AccountDB accountDB;
    private User user;


    public SignupPresenter(SignupContract.View view, AccountDB accountDB){
        this.signupView = view;
        this.accountDB = accountDB;
    }

    @Override
    public void onSignup(String name, String email, String password, String confrimPassword) {

        if(!validInput(name, email, password, confrimPassword)) return;
        if(!validAccount(email)) return;

        this.user = new User();
        user.setEmail(email);
        user.setName(email);
        user.setPassword(password);

        accountDB.addUser(user);
        signupView.reset();
        signupView.showNextUI();
    }

    @Override
    public boolean validInput(String name, String email, String password, String confrimPassword) {
        if(name.isEmpty()){
            signupView.showNameError();
            return false;
        }

        if(email.isEmpty()|| !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            signupView.showEmailError();
            return false;
        }

        if(password.isEmpty()){
            signupView.showPasswordError();
            return false;
        }

        if(!password.contentEquals(confrimPassword)){
            signupView.showMismatchError();
            return false;
        }
        return true;
    }

    @Override
    public boolean validAccount(String email) {

        if(accountDB.checkUser(email)){
            signupView.showAccountError();
            return false;
        }
        return true;
    }

}
