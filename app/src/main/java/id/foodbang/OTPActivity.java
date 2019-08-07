package id.foodbang;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.chaos.view.PinView;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.foodbang.engine.AppController;
import id.foodbang.engine.interfaces.RetrofitCallback;
import id.foodbang.engine.session.LoginSession;
import id.foodbang.model.LoginBody;
import id.foodbang.model.LoginResponse;
import retrofit2.Response;

public class OTPActivity extends AppCompatActivity {

    @BindView(R.id.pv_otp)
    PinView pvOTP;
    @BindView(R.id.btn_send_otp)
    Button btnSendOTP;
    @BindView(R.id.tv_status_otp)
    TextView tvStatusOTP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        ButterKnife.bind(this);

        final LoginSession loginSession = new LoginSession(this);

        btnSendOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String otp = pvOTP.getText().toString();

                String email = loginSession.getLoginSession(LoginSession.USERNAME);
                String otpData = loginSession.getLoginSession(LoginSession.OTP);

                if (otp.equals(otpData)){
                    AppController app = new AppController();
                    app.login(new LoginBody(email, otpData), new RetrofitCallback() {
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

                                    startActivity(new Intent(getApplicationContext(), NavigationActivity.class)
                                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK));
                                    finish();
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
                } else{
                    pvOTP.setLineColor(getResources().getColor(R.color.colorPrimary));
                    tvStatusOTP.setVisibility(View.VISIBLE);
                    tvStatusOTP.setText("Incorrect OTP" + otp + " " + otpData);
                    tvStatusOTP.setTextColor(getResources().getColor(R.color.colorPrimary));
                }
            }
        });

    }
}
