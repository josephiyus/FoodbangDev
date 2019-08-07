package id.foodbang;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.foodbang.engine.AppController;
import id.foodbang.engine.interfaces.RetrofitCallback;
import id.foodbang.engine.session.LoginSession;
import id.foodbang.model.LoginBody;
import id.foodbang.model.LoginResponse;
import id.foodbang.utils.DrawableClickListener;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.btn_register)
    Button btnRegister;
    @BindView(R.id.edt_username)
    TextInputEditText edtUsername;
    @BindView(R.id.edt_password)
    TextInputEditText edtPassword;

    ProgressDialog pDialog;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        pDialog = new ProgressDialog(getApplicationContext());
        pDialog.setTitle("Authenticating...");
        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(false);

        final LoginSession loginSession = new LoginSession(getApplicationContext());

        if (loginSession.isLogin(LoginSession.IS_LOGIN)){
            startActivity(new Intent(getApplicationContext(), NavigationActivity.class));
            finish();
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = edtUsername.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();
                if(username.isEmpty() || password.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Username/password kosong", Toast.LENGTH_SHORT).show();
                }else {
                    AppController app = new AppController();
                    app.login(new LoginBody(username, password), new RetrofitCallback() {
                        @Override
                        public void onResponse(Response<?> response) {
                            if(response.isSuccessful()){
                                LoginResponse loginResponse = (LoginResponse) response.body();
                                if (loginResponse != null) {
                                    loginSession.setIsLogin(LoginSession.IS_LOGIN, true);
                                    loginSession.setLoginSession(LoginSession.USERNAME, loginResponse.getEmail());
                                    loginSession.setLoginSession(LoginSession.ACCESS_TOKEN, loginResponse.getToken());
                                    loginSession.setLoginSession(LoginSession.NAME, loginResponse.getName());
                                    loginSession.setLoginSession(LoginSession.PHONE_NUMBER, loginResponse.getPhoneNumber());

                                    startActivity(new Intent(getApplicationContext(), NavigationActivity.class));
                                    finish();
//                                    Toast.makeText(getApplicationContext(), "You're logged in", Toast.LENGTH_SHORT).show();
                                } else{
                                    Toast.makeText(getApplicationContext(), "Login gagal", Toast.LENGTH_SHORT).show();
                                }
                            } else{
                                Toast.makeText(getApplicationContext(), "Login gagal", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(String result) {
                            Toast.makeText(getApplicationContext(), "Login gagal", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            }
        });

        edtPassword.setOnTouchListener(new DrawableClickListener.RightDrawableClickListener(edtPassword) {
            @Override
            public boolean onDrawableClick() {
                if (edtPassword.getInputType() == 129){
                    edtPassword.setInputType(InputType.TYPE_CLASS_TEXT);
                    return true;
                }
                else{
                    edtPassword.setInputType(129);
                    return false;
                }
            }
        });
    }
}
