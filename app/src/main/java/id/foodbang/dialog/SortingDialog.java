package id.foodbang.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import id.foodbang.R;

public class SortingDialog extends AppCompatDialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_sorting_pkg, null);
        Button btn = view.findViewById(R.id.btn_highest_price);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //parent.printToast();
                dismiss();
            }
        });

        btn = view.findViewById(R.id.btn_lowest_price);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //parent.printToast();
                dismiss();
            }
        });

        btn = view.findViewById(R.id.btn_highest_rating);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //parent.printToast();
                dismiss();
            }
        });

        btn = view.findViewById(R.id.btn_lowest_rating);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //parent.printToast();
                dismiss();
            }
        });

        btn = view.findViewById(R.id.btn_near_me);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //parent.printToast();
                dismiss();
            }
        });
        builder.setView(view);
        return builder.create();
    }
}
