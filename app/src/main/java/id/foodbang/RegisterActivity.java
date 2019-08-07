package id.foodbang;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.Max;
import com.mobsandgeeks.saripaar.annotation.Min;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.foodbang.engine.AppController;
import id.foodbang.engine.interfaces.RetrofitCallback;
import id.foodbang.engine.session.LoginSession;
import id.foodbang.model.RegisterRequest;
import id.foodbang.model.RegisterResponse;
import id.foodbang.utils.TextValidator;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity implements Validator.ValidationListener {

    @BindView(R.id.btn_register)
    Button btnRegister;


    @NotEmpty
    @BindView(R.id.edt_name)
    TextInputEditText edtName;

    @NotEmpty
    @Email
    @BindView(R.id.edt_email)
    TextInputEditText edtEmail;

    @NotEmpty
    @Length(min = 11, message = "Invalid phone number")
    @BindView(R.id.edt_phone)
    TextInputEditText edtPhone;

    @BindView(R.id.tv_signin)
    TextView tvSignin;

    @BindView(R.id.til_name)
    TextInputLayout tilName;
    @BindView(R.id.til_email)
    TextInputLayout tilEmail;
    @BindView(R.id.til_phone)
    TextInputLayout tilPhone;

    LoginSession loginSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        loginSession = new LoginSession(this);

        final Validator validator = new Validator(this);
        validator.setValidationListener(this);

        tvSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
                finish();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validator.validate();
            }
        });
    }

    @Override
    public void onValidationSucceeded() {
        AppController app = new AppController();

        RegisterRequest request = new RegisterRequest();
        request.setFullname(edtName.getText().toString().trim());
        request.setEmail(edtEmail.getText().toString().trim());

        String phone = "62" + edtPhone.getText().toString().trim();

        request.setPhoneNumber(phone);

        app.register(request, new RetrofitCallback() {
            @Override
            public void onResponse(Response<?> response) {
                if(response.isSuccessful()){
                    RegisterResponse registerResponse = (RegisterResponse) response.body();
                    assert registerResponse != null;

                    String otp = registerResponse.getOtp();
                    String email = registerResponse.getEmail();

                    if(otp != null && email != null){
                        loginSession.setLoginSession(LoginSession.OTP, otp);
                        loginSession.setLoginSession(LoginSession.USERNAME, registerResponse.getEmail());

                        Intent intent = new Intent(getApplicationContext(), OTPActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

                        startActivity(new Intent(getApplicationContext(), OTPActivity.class));
                    }
                }else{

                    String message = null;
                    try {
                        message = new String(response.errorBody().bytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(String result) {

            }
        });
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);

            // Display error messages ;)
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }
    }
}
