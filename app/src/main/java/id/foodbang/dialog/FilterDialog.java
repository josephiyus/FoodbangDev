package id.foodbang.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import java.util.*;

import id.foodbang.R;
import id.foodbang.common.FoodbangAppCompatActivity;
import id.foodbang.model.PackageSearchParam;

public class FilterDialog extends AppCompatDialogFragment {
    private FoodbangAppCompatActivity parent;
    private List<String> packageId = new ArrayList<>();

    public void setParent(FoodbangAppCompatActivity parent) {
        this.parent = parent;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_filter_pkg, null);

        Map<Integer,String> checkboxes = new HashMap<>();
        checkboxes.put(R.id.cb1, "1");
        checkboxes.put(R.id.cb2, "3");
        checkboxes.put(R.id.cb3, "6");
        checkboxes.put(R.id.cb4, "7");
        checkboxes.put(R.id.cb5, "2");
        checkboxes.put(R.id.cb6, "4");
        checkboxes.put(R.id.cb7, "5");
        checkboxes.put(R.id.cb8, "8");

        for(Map.Entry<Integer,String> checkbox : checkboxes.entrySet()){
            applyCheckboxValue(view, checkbox.getKey(), checkbox.getValue());
        }

        Button btn = view.findViewById(R.id.btn_done);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PackageSearchParam key = new PackageSearchParam();
                key.setPackage_category_id(packageId);
                Map<String,Object> param = new HashMap<>();
                param.put("searchKey", key);
                parent.parentProcess(param);
                dismiss();
            }
        });
        builder.setView(view);
        return builder.create();
    }

    private void applyCheckboxValue(View view, int id, final String val) {
        final CheckBox cb = view.findViewById(id);
        cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cb.isChecked()){
                    if(packageId.indexOf(val)<0){
                        packageId.add(val);
                    }
                }else {
                    if(packageId.indexOf(val)>=0){
                        packageId.remove(packageId.indexOf(val));
                    }
                }
            }
        });
    }
}
