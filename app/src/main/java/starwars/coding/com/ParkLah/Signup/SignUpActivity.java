package starwars.coding.com.ParkLah.Signup;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.Toast;

//import starwars.coding.com.parklahapp1.R;
import starwars.coding.com.ParkLah.Database.AccountDB;
import starwars.coding.com.ParkLah.Login.LoginActivity;
import starwars.coding.com.ParkLah.R;
import starwars.coding.com.ParkLah.Entity.User;
import starwars.coding.com.ParkLah.Database.AccSqlManager;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener, SignupContract.View {

    private final AppCompatActivity activity = SignUpActivity.this;

    private NestedScrollView nestedScrollView;

    private TextInputLayout textInputLayoutName;
    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;
    private TextInputLayout textInputLayoutConfirmPassword;

    private TextInputEditText textInputEditTextName;
    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPassword;
    private TextInputEditText textInputEditTextConfirmPassword;

    private AppCompatButton appCompatButtonRegister;
    private AppCompatTextView appCompatTextViewLoginLink;

//    private InputValidation inputValidation;
//    private AccSqlManager accSqlManager;
    private User user;

    private SignupContract.Presenter signupPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
//        getSupportActionBar().hide();

        initViews();
        initListeners();

        AccountDB accountDB = AccSqlManager.getInstance(activity);
        this.signupPresenter = new SignupPresenter(this, accountDB);
    }

    /**
     * This method is to initialize views
     */
    private void initViews() {
        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);

        textInputLayoutName = (TextInputLayout) findViewById(R.id.textInputLayoutName);
        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);
        textInputLayoutConfirmPassword = (TextInputLayout) findViewById(R.id.textInputLayoutConfirmPassword);

        textInputEditTextName = (TextInputEditText) findViewById(R.id.textInputEditTextName);
        textInputEditTextEmail = (TextInputEditText) findViewById(R.id.textInputEditTextEmail);
        textInputEditTextPassword = (TextInputEditText) findViewById(R.id.textInputEditTextPassword);
        textInputEditTextConfirmPassword = (TextInputEditText) findViewById(R.id.textInputEditTextConfirmPassword);

        appCompatButtonRegister = (AppCompatButton) findViewById(R.id.appCompatButtonRegister);

        appCompatTextViewLoginLink = (AppCompatTextView) findViewById(R.id.appCompatTextViewLoginLink);

    }

    /**
     * This method is to initialize listeners
     */
    private void initListeners() {
        appCompatButtonRegister.setOnClickListener(this);
        appCompatTextViewLoginLink.setOnClickListener(this);

    }

    /**
     * This method is to initialize objects to be used
     */
//    private void initObjects() {
//        inputValidation = new InputValidation(activity);
//        accSqlManager = new AccSqlManager(activity);
//        user = new User();
//
//    }


    /**
     * This implemented method is to listen the click on view
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.appCompatButtonRegister:
                String name = textInputEditTextName.getText().toString().trim();
                String email = textInputEditTextEmail.getText().toString().trim();
                String password = textInputEditTextPassword.getText().toString().trim();
                String confirmPassword = textInputEditTextConfirmPassword.getText().toString().trim();

                Toast.makeText(LoginActivity.this, "Register succeeds", Toast.LENGTH_SHORT).show();
                signupPresenter.onSignup(name, email, password, confirmPassword);
                break;

            case R.id.appCompatTextViewLoginLink:
                finish();
                break;
        }
    }

    // What I edited, to prompt user to Login activity once register activity is successful.
    private void initLoginAfterRegister(){
        Snackbar.make(nestedScrollView, getString(R.string.success_message), Snackbar.LENGTH_LONG).show();
//        Toast.makeText(getApplicationContext(), getString(R.string.success_message), Toast.LENGTH_SHORT);
        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
        startActivity(intent);

    }

    /**
     * This method is to validate the input text fields and post data to SQLite
     */
//    private void postDataToSQLite() {
//        if (!inputValidation.isInputEditTextFilled(textInputEditTextName, textInputLayoutName, getString(R.string.error_message_name))) {
//            return;
//        }
//        if (!inputValidation.isInputEditTextFilled(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
//            return;
//        }
//        if (!inputValidation.isInputEditTextEmail(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
//            return;
//        }
//        if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_message_password))) {
//            return;
//        }
//        if (!inputValidation.isInputEditTextMatches(textInputEditTextPassword, textInputEditTextConfirmPassword,
//                textInputLayoutConfirmPassword, getString(R.string.error_password_match))) {
//            return;
//        }
//
//        if (!accSqlManager.checkUser(textInputEditTextEmail.getText().toString().trim())) {
//
//            user.setName(textInputEditTextName.getText().toString().trim());
//            user.setEmail(textInputEditTextEmail.getText().toString().trim());
//            user.setPassword(textInputEditTextPassword.getText().toString().trim());
//
//            accSqlManager.addUser(user);
//
//            emptyInputEditText();
//            initLoginAfterRegister();
//
//
//        } else {
//            // Snack Bar to show error message that record already exists
//            Snackbar.make(nestedScrollView, getString(R.string.error_email_exists), Snackbar.LENGTH_LONG).show();
//        }
//
//
//    }


    public void reset(){
        textInputEditTextName.setText(null);
        textInputEditTextEmail.setText(null);
        textInputEditTextPassword.setText(null);
        textInputEditTextConfirmPassword.setText(null);
    }

    @Override
    public void showNextUI() {
        Snackbar.make(nestedScrollView, getString(R.string.success_message), Snackbar.LENGTH_LONG).show();
//        Toast.makeText(getApplicationContext(), getString(R.string.success_message), Toast.LENGTH_SHORT);
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void showNameError() {
        String nameError = getString(R.string.error_message_name);
        textInputLayoutName.setError(nameError);
    }

    @Override
    public void showEmailError() {
        String emailError = getString(R.string.error_message_email);
        textInputLayoutEmail.setError(emailError);
    }

    @Override
    public void showPasswordError() {
        String passwordError = getString(R.string.error_message_password);
        textInputLayoutPassword.setError(passwordError);
    }

    @Override
    public void showMismatchError() {
        String mismatchError = getString(R.string.error_password_match);
        textInputLayoutConfirmPassword.setError(mismatchError);
    }

    @Override
    public void showAccountError() {
        String accountExists = getString(R.string.error_email_exists);
        Snackbar.make(nestedScrollView, accountExists, Snackbar.LENGTH_LONG).show();
    }
}
