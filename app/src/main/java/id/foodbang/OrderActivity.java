package id.foodbang;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.io.IOException;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.foodbang.engine.AppController;
import id.foodbang.engine.interfaces.RetrofitCallback;
import id.foodbang.engine.session.LoginSession;
import id.foodbang.model.OrderData;
import id.foodbang.model.OrderItem;
import id.foodbang.model.OrderRequest;
import id.foodbang.model.OrderResponse;
import id.foodbang.utils.ThousandSeparator;
import retrofit2.Response;

public class OrderActivity extends AppCompatActivity
{
    @BindView(R.id.btn_submit)
    Button btnSubmit;

    @BindView(R.id.et_selected_package)
    TextView tvSelectedPackage;

    @BindView(R.id.tv_select_date)
    TextView tvSelectDate;

    @BindView(R.id.tv_select_time)
    TextView tvSelectTime;

    @BindView(R.id.et_portion)
    TextInputEditText etPortion;

    @BindView(R.id.et_ordering_note)
    TextInputEditText etOrderingNote;

    @BindView(R.id.et_ordering_email)
    TextInputEditText etOrderingEmail;

    @BindView(R.id.et_ordering_number)
    TextInputEditText etOrderingNumber;

    @BindView(R.id.et_ordering_location)
    TextInputEditText etOrderingLocation;

    @BindView(R.id.tv_subtotal)
    TextView tvSubtotal;

    @BindView(R.id.tv_total)
    TextView tvTotal;

    @BindView(R.id.tv_tax)
    TextView tvTax;

    int package_id, package_price, portion;
    String package_image, package_name;

    LoginSession loginSession;
    boolean changeTime =  false,
            changeDate = false;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        ButterKnife.bind(this);

        if(this.getSupportActionBar() != null)
        {
            this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            this.getSupportActionBar().setTitle("Detail Pemesanan");
        }

        loginSession = new LoginSession(this);

        package_id = getIntent().getIntExtra("package_id", 0);
        package_image = getIntent().getStringExtra("package_image");
        package_name = getIntent().getStringExtra("package_name");
        package_price = getIntent().getIntExtra("package_price", 0);
        portion = getIntent().getIntExtra("portion", 0);

        tvSelectedPackage.setText(package_name);
        etOrderingEmail.setText(loginSession.getLoginSession(LoginSession.USERNAME));
        etOrderingNumber.setText(loginSession.getLoginSession(LoginSession.PHONE_NUMBER));

        final OrderRequest request = new OrderRequest();
        request.setOrder_item(new OrderItem());
        request.getOrder_item().setPackageId(package_id);

        etPortion.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().isEmpty()){
                    int subtotal = package_price * Integer.parseInt(s.toString());
                    int tax = (int) (0.1 * subtotal);
                    int total = subtotal + tax;

                    tvSubtotal.setText(ThousandSeparator.createCurrency(String.valueOf(subtotal)));
                    tvTax.setText(ThousandSeparator.createCurrency(String.valueOf(tax)));
                    tvTotal.setText("Rp. " + ThousandSeparator.createCurrency(String.valueOf(total)));
                }
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(etPortion.getText() != null && etOrderingLocation.getText() != null && etOrderingNote.getText() != null)
                {
                    if (etPortion.getText().toString().isEmpty() || etOrderingLocation.getText().toString().isEmpty() || !changeDate || !changeTime)
                    {
                        Toast.makeText(getApplicationContext(), "Tolong lengkapi detail pesanan", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        final int por = Integer.parseInt(etPortion.getText().toString());
                        String date = tvSelectDate.getText().toString() + " " + tvSelectTime.getText().toString();
                        String location = etOrderingLocation.getText().toString();
                        String note = etOrderingNote.getText().toString();

                        if (por < portion) {
                            Toast.makeText(getApplicationContext(), "Mohon maaf, minumum pemesanan " + portion + "porsi.", Toast.LENGTH_SHORT).show();
                        } else {

                            request.getOrder_item().setRequestOrderPortion(por);
                            request.getOrder_item().setRequestOrderDate(date);
                            request.getOrder_item().setRequestOrderLocation(location);
                            request.getOrder_item().setRequestOrderNote(note);

                            AppController app = new AppController(new LoginSession(OrderActivity.this)
                                    .getLoginSession(LoginSession.ACCESS_TOKEN));
                            app.order(request, new RetrofitCallback() {
                                @Override
                                public void onResponse(Response<?> response) {
                                    if (response.isSuccessful()) {
                                        OrderResponse orderResponse = (OrderResponse) response.body();

                                        assert orderResponse != null;
                                        OrderData orderData = orderResponse.getData();

                                        if(orderData != null) {
                                            Intent intent = new Intent(getApplicationContext(), ReviewOrderActivity.class);
                                            intent.putExtra("order_data", orderData);
                                            intent.putExtra("package_image", package_image);
                                            intent.putExtra("package_name", package_name);
                                            intent.putExtra("real_price", (por * package_price));
                                            startActivity(intent);
                                            finish();
                                        }
                                    } else {
                                        String message;
                                        try {
                                            message = new String(response.errorBody().bytes());

                                            Toast mtoast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG);
                                            mtoast.setGravity(Gravity.CENTER, 0, 0);

                                            TextView message_mtoast = mtoast.getView().findViewById(android.R.id.message);
                                            message_mtoast.setTextSize(19);

                                            mtoast.show();
                                        } catch (IOException | NullPointerException e) {
                                            e.printStackTrace();

                                            message = "Ups! Something Wrong!";
                                            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }

                                @Override
                                public void onFailure(String result) {
                                    Toast.makeText(getApplicationContext(), "Ups! Something Wrong!", Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                    }
                }
            }
        });

        tvSelectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });

        tvSelectTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePicker();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    private void showDatePicker(){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(OrderActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        tvSelectDate.setText(year + "-" + month + "-" + day);
                        changeDate = true;
                    }
                }, year, month, dayOfMonth);

        datePickerDialog.show();
    }

    private  void showTimePicker(){
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(OrderActivity.this,
                new TimePickerDialog.OnTimeSetListener() {
                    @SuppressLint("DefaultLocale")
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        tvSelectTime.setText(new StringBuilder().append(String.format("%02d", hourOfDay)).append(":")
                                .append(String.format("%02d", minute)).append(":00"));
                        changeTime = true;
                    }
                }, hour, minute, true);

        timePickerDialog.show();
    }
}
