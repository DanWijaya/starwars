package starwars.coding.com.ParkLah.Signup;

import starwars.coding.com.ParkLah.Database.DataBase;
import starwars.coding.com.ParkLah.Entity.User;

public class SignupPresenter implements SignupContract.Presenter {

    private SignupContract.View signupView;
    private DataBase dataBase;
    private User user;

    public SignupPresenter(SignupContract.View view, DataBase dataBase){
        this.signupView = view;
        this.dataBase = dataBase;
    }

    /**
     * This method registered the user signing up into the database
     * @param name User's name
     * @param email User's email
     * @param password User's password
     * @param confrimPassword Repeated password
     */
    @Override
    public void onSignup(String name, String email, String password, String confrimPassword) {

        if(!validInput(name, email, password, confrimPassword)) return;
        if(!validAccount(email)) return;

        this.user = new User();
        user.setEmail(email);
        user.setName(email);
        user.setPassword(password);

        dataBase.addUser(user);
        signupView.reset();
        signupView.showNextUI();
    }

    /**
     * This method validates the name, email, and password of the user signing up
     * @param name User's name
     * @param email User's email
     * @param password User's password
     * @param confrimPassword Repeated password
     * @return
     */
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

    /**
     * This method validates the account's existance in the database
     * @param email User's email
     * @return True or false depending on the result
     */
    @Override
    public boolean validAccount(String email) {

        if(dataBase.checkUser(email)){
            signupView.showAccountError();
            return false;
        }
        return true;
    }

}
