package id.foodbang.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import java.util.ArrayList;
import java.util.List;

import id.foodbang.R;

public class FilterDialog extends AppCompatDialogFragment {

    private List<String> packageId = new ArrayList<>();

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_filter_pkg, null);

        final CheckBox cb = view.findViewById(R.id.cb1);
        cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cb.isChecked()){
                    packageId.add("1");
                }else {

                }
            }
        });

        cb = view.findViewById(R.id.cb3);
        cb = view.findViewById(R.id.cb4);
        cb = view.findViewById(R.id.cb5);
        cb = view.findViewById(R.id.cb6);
        cb = view.findViewById(R.id.cb7);
        cb = view.findViewById(R.id.cb8);
        Button btn = view.findViewById(R.id.btn_done);
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
