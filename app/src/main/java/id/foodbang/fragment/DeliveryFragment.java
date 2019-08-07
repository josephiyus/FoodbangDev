package id.foodbang.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.foodbang.R;
import id.foodbang.adapter.DeliveryAdapter;
import id.foodbang.engine.AppController;
import id.foodbang.engine.interfaces.RetrofitCallback;
import id.foodbang.engine.session.LoginSession;
import id.foodbang.model.OrderData;
import id.foodbang.model.OrderListRequest;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class DeliveryFragment extends Fragment {

    @BindView(R.id.rv_deliver_list)
    RecyclerView rvDeliveryList;

    public DeliveryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_delivery, container, false);
        ButterKnife.bind(this, view);

        final List<OrderData> orderData = new ArrayList<>();
        LoginSession loginSession = new LoginSession(getContext());

        rvDeliveryList.setHasFixedSize(true);
        rvDeliveryList.setHorizontalScrollBarEnabled(false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvDeliveryList.setLayoutManager(layoutManager);
        final DeliveryAdapter deliveryAdapter = new DeliveryAdapter(getContext(), orderData);
        rvDeliveryList.setAdapter(deliveryAdapter);

        AppController app = new AppController(loginSession.getLoginSession(LoginSession.ACCESS_TOKEN));
        app.listOrder(new RetrofitCallback() {
            @Override
            public void onResponse(Response<?> response) {
                if(response.isSuccessful()){
                    OrderListRequest orderListRequest = (OrderListRequest) response.body();

                    assert orderListRequest != null;
                    orderData.addAll(orderListRequest.getData());

                    deliveryAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(String result) {

            }
        });

        return view;
    }

}
