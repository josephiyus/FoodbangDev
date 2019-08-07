package id.foodbang.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.foodbang.MainActivity;
import id.foodbang.R;
import id.foodbang.engine.session.LoginSession;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    @BindView(R.id.btn_logout) Button btnLogout;
    @BindView(R.id.tv_customer_name) TextView tvCustomerName;
    @BindView(R.id.tv_customer_email) TextView tvCustomerEmail;
    @BindView(R.id.tv_name) TextView tvName;
    @BindView(R.id.tv_email) TextView tvEmail;
    @BindView(R.id.tv_phone_number) TextView tvPhoneNumber;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, view);
        final LoginSession loginSession = new LoginSession(getContext());

        tvCustomerName.setText(loginSession.getLoginSession("NAME"));
        tvCustomerEmail.setText(loginSession.getLoginSession("USERNAME"));
        tvName.setText(loginSession.getLoginSession("NAME"));
        tvEmail.setText(loginSession.getLoginSession("USERNAME"));
        tvPhoneNumber.setText(loginSession.getLoginSession("PHONE_NUMBER"));

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginSession.removeLoginSession();

                Intent intent = new Intent(getContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                getContext().startActivity(intent);
            }
        });

        return view;
    }

}
