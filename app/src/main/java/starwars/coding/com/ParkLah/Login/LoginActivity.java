package starwars.coding.com.ParkLah.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import starwars.coding.com.ParkLah.Control.APIManager;
import starwars.coding.com.ParkLah.Database.AccountDB;
import starwars.coding.com.ParkLah.MainPage.MapActivity;
import starwars.coding.com.ParkLah.R;
import starwars.coding.com.ParkLah.Signup.SignUpActivity;
import starwars.coding.com.ParkLah.Database.AccSqlManager;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, LoginContract.View {

    private final AppCompatActivity activity = LoginActivity.this;

    private NestedScrollView nestedScrollView;

    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;

    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPassword;

    private AppCompatButton appCompatButtonLogin;
    private AppCompatTextView textViewLinkRegister;

    private LoginContract.Presenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        getSupportActionBar().hide();
        initViews();
        initListeners();

        APIManager apiManager = APIManager.getaInstance(this);
        apiManager.new fetchCarparkInformation().execute();
        apiManager.new fectchCarparkAvailability().execute();

        AccountDB accountDB = AccSqlManager.getInstance(this);
        this.loginPresenter = new LoginPresenter(this, accountDB);
    }

    /**
     * This method is to initialize views
     */
    private void initViews() {
        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);

        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);

        textInputEditTextEmail = (TextInputEditText) findViewById(R.id.textInputEditTextEmail);
        textInputEditTextPassword = (TextInputEditText) findViewById(R.id.textInputEditTextPassword);

        appCompatButtonLogin = (AppCompatButton) findViewById(R.id.appCompatButtonLogin);
        textViewLinkRegister = (AppCompatTextView) findViewById(R.id.textViewLinkRegister);
    }

    /**
     * This method is to initialize listeners
     */
    private void initListeners() {
        appCompatButtonLogin.setOnClickListener(this);
        textViewLinkRegister.setOnClickListener(this);
    }

    /**
     * This method passes the control to presenter,
     * let the presenter handle the events.
     * @param v
     */

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.appCompatButtonLogin:
                String email = textInputEditTextEmail.getText().toString().trim();
                String password = textInputEditTextPassword.getText().toString().trim();
                loginPresenter.onLogin(email, password);
                break;
            case R.id.textViewLinkRegister:
                loginPresenter.onSignUp();
                break;
        }
    }

    /**
     * This method is to validate the input text fields and verify login credentials from SQLite
     */
//    private void verifyFromSQLite() {
//        if (!inputValidation.isInputEditTextFilled(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
//            return;
//        }
//        if (!inputValidation.isInputEditTextEmail(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
//            return;
//        }
//        if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_message_email))) {
//            return;
//        }
//
//        if (databaseHelper.checkUser(textInputEditTextEmail.getText().toString().trim()
//                , textInputEditTextPassword.getText().toString().trim())) {
//
//
////            Intent accountsIntent = new Intent(activity, UsersListActivity.class);
////            accountsIntent.putExtra("EMAIL", textInputEditTextEmail.getText().toString().trim());
//            emptyInputEditText();
//
//            Intent test = new Intent(activity, MapActivity.class);
//            startActivity(test);
////            startActivity(accountsIntent);
//
//
//        } else {
//            // Snack Bar to show success message that record is wrong
//            Snackbar.make(nestedScrollView, getString(R.string.error_valid_email_password), Snackbar.LENGTH_LONG).show();
//        }
//    }

    /**
     * This method is to empty all input edit text
     */
    public void reset() {
        textInputEditTextEmail.setText(null);
        textInputEditTextPassword.setText(null);
    }

    @Override
    public void showSignUpUI() {
        Intent signUpPage = new Intent(this, SignUpActivity.class);
        startActivity(signUpPage);
    }

    @Override
    public void showLoginError() {
        String loginErrorMessage = getString(R.string.error_valid_email_password);
        Snackbar.make(nestedScrollView, getString(R.string.error_valid_email_password), Snackbar.LENGTH_LONG).show();
    }

    public void showEmailError(){
        String errorMessage = getString(R.string.error_message_email);
        textInputLayoutEmail.setError(errorMessage);
    }

    @Override
    public void showPasswordError() {
        String errorMessage = getString(R.string.error_message_password);
        textInputLayoutPassword.setError(errorMessage);

    }

    @Override
    public void showMainUI() {
        Intent mainPage = new Intent(this, MapActivity.class);
        startActivity(mainPage);
    }

}