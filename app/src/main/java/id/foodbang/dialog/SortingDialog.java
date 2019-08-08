package id.foodbang.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import java.util.HashMap;
import java.util.Map;

import id.foodbang.R;
import id.foodbang.common.FoodbangAppCompatActivity;
import id.foodbang.model.PackageSortParam;

public class SortingDialog extends AppCompatDialogFragment {
    private FoodbangAppCompatActivity parent;

    public void setParent(FoodbangAppCompatActivity parent) {
        this.parent = parent;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_sorting_pkg, null);
        Button btn = view.findViewById(R.id.btn_highest_price);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PackageSortParam packageSortParam = new PackageSortParam();
                packageSortParam.setPrice("max");

                Map<String, Object> param = new HashMap<>();
                param.put("sortKey", packageSortParam);

                parent.parentProcess(param);

                dismiss();
            }
        });

        btn = view.findViewById(R.id.btn_lowest_price);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PackageSortParam packageSortParam = new PackageSortParam();
                packageSortParam.setPrice("min");

                Map<String, Object> param = new HashMap<>();
                param.put("sortKey", packageSortParam);

                parent.parentProcess(param);

                //parent.printToast()
                dismiss();
            }
        });

        btn = view.findViewById(R.id.btn_highest_rating);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PackageSortParam packageSortParam = new PackageSortParam();
                packageSortParam.setRating("max");

                Map<String, Object> param = new HashMap<>();
                param.put("sortKey", packageSortParam);

                parent.parentProcess(param);

                //parent.printToast();
                dismiss();
            }
        });

        btn = view.findViewById(R.id.btn_lowest_rating);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PackageSortParam packageSortParam = new PackageSortParam();
                packageSortParam.setRating("min");

                Map<String, Object> param = new HashMap<>();
                param.put("sortKey", packageSortParam);

                parent.parentProcess(param);

                //parent.printToast();
                dismiss();
            }
        });

        btn = view.findViewById(R.id.btn_near_me);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PackageSortParam packageSortParam = new PackageSortParam();
                packageSortParam.setPortion("near");

                Map<String, Object> param = new HashMap<>();
                param.put("sortKey", packageSortParam);

                parent.parentProcess(param);

                //parent.printToast();
                dismiss();
            }
        });
        builder.setView(view);
        return builder.create();
    }
}
