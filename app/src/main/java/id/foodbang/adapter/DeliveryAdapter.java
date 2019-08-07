package id.foodbang.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.foodbang.DeliveryActivity;
import id.foodbang.R;
import id.foodbang.engine.AppController;
import id.foodbang.engine.interfaces.RetrofitCallback;
import id.foodbang.model.DetailPackage;
import id.foodbang.model.OrderData;
import id.foodbang.model.PackageData;
import id.foodbang.utils.ThousandSeparator;
import retrofit2.Response;

public class DeliveryAdapter extends RecyclerView.Adapter<DeliveryAdapter.ViewHolder> {

    private Context context;
    private List<OrderData> orderData;

    public DeliveryAdapter(Context context, List<OrderData> orderData) {
        this.context = context;
        this.orderData = orderData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_delivery_list, viewGroup, false);

        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        setColorStatus(orderData.get(i).getBookedStatus(), viewHolder.tvStatusOrder);
        viewHolder.tvStatusOrder.setText(orderData.get(i).getBookedStatus().toUpperCase());
        viewHolder.tvPackagePortion.setText(String.valueOf(orderData.get(i).getRequestOrderPortion()) + " porsi");
        String[] dateTime = getDateTime(orderData.get(i).getRequestOrderDate());
        viewHolder.tvOrderDate.setText(dateTime[0] + " " + dateTime[1]);
        viewHolder.tvTotalPrice.setText("Rp. " + ThousandSeparator.createCurrency(String.valueOf(orderData.get(i).getTotalPrice())));

        AppController app = new AppController();
        app.getPackage(orderData.get(i).getPackageId(), new RetrofitCallback() {
            @Override
            public void onResponse(Response<?> response) {
                if(response.isSuccessful()){
                    DetailPackage detailPackage = (DetailPackage) response.body();
                    assert detailPackage != null;
                    PackageData packageData = detailPackage.get_package();
                    viewHolder.tvPackageName.setText(packageData.getName());
                }
            }

            @Override
            public void onFailure(String result) {

            }
        });

        viewHolder.rlDeliveryList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!orderData.get(i).getBookedStatus().equals("rejected"))
                    context.startActivity(new Intent(context, DeliveryActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return orderData.size();
    }

    private void setColorStatus(String status, TextView view){
        switch (status){
            case "requested":
                view.setTextColor(context.getResources().getColor(R.color.success_tag));
                break;
            case "approved":
                view.setTextColor(context.getResources().getColor(R.color.request_tag));
                break;
            case "rejected":
                view.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                break;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.tv_status_order)
        TextView tvStatusOrder;
        @BindView(R.id.tv_package_name)
        TextView tvPackageName;
        @BindView(R.id.tv_package_portion)
        TextView tvPackagePortion;
        @BindView(R.id.tv_order_date)
        TextView tvOrderDate;
        @BindView(R.id.tv_total_price)
        TextView tvTotalPrice;
        @BindView(R.id.rl_delivery_list)
        RelativeLayout rlDeliveryList;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @SuppressLint("SimpleDateFormat")
    private String[] getDateTime(String dateTime){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        Date date = null;//You will get date object relative to server/client timezone wherever it is parsed
        try {
            date = dateFormat.parse(dateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        DateFormat formatDate = new SimpleDateFormat("EEE, d MMM yyyy"); //If you need time just put specific format for time like 'HH:mm:ss'
        DateFormat formatTime = new SimpleDateFormat("HH:mm");
        String[] date_time = {formatDate.format(date), formatTime.format(date)};

        return date_time;
    }

}
