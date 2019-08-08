package id.foodbang.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import id.foodbang.R;
import id.foodbang.common.FoodbangAppCompatActivity;
import id.foodbang.model.PackageSearchParam;
import id.foodbang.utils.RupiahFormat;

public class FilterDialog extends AppCompatDialogFragment {
    private FoodbangAppCompatActivity parent;
    private List<String> packageId = new ArrayList<>();
    private Float ratingFilter;
    private Integer pricePortionFilter;
    private Integer minPricePortionFilter;

    public void setParent(FoodbangAppCompatActivity parent) {
        this.parent = parent;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if (getActivity() == null) return null;

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.dialog_filter_pkg, null);

        SparseArray<String> checkboxes = new SparseArray<>();
        checkboxes.put(R.id.cb1, "1");
        checkboxes.put(R.id.cb2, "3");
        checkboxes.put(R.id.cb3, "6");
        checkboxes.put(R.id.cb4, "7");
        checkboxes.put(R.id.cb5, "2");
        checkboxes.put(R.id.cb6, "4");
        checkboxes.put(R.id.cb7, "5");
        checkboxes.put(R.id.cb8, "8");

        for (int i = 0; i < checkboxes.size(); i++) {
            int key = checkboxes.keyAt(i);
            String ch = checkboxes.get(key);

            applyCheckboxValue(view, key, ch);
        }

        RatingBar ratingBar = view.findViewById(R.id.ratingBar);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if (fromUser) {
                    ratingBar.setRating(rating);
                    ratingFilter = rating;
                }
            }
        });

        SeekBar seekBarPricePortion = view.findViewById(R.id.seekBarPricePortion);
        seekBarPricePortion.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    TextView pricePortion = view.findViewById(R.id.txt_pricePortion);

                    DecimalFormat rupiahFormat = RupiahFormat.getInstance();
                    pricePortion.setText(rupiahFormat.format(progress));

                    pricePortionFilter = progress;
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        SeekBar seekBarMinPortion = view.findViewById(R.id.seekBarMinPortion);
        seekBarMinPortion.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    TextView priceMinPortion = view.findViewById(R.id.txt_minPortion);

                    DecimalFormat rupiahFormat = RupiahFormat.getInstance();
                    priceMinPortion.setText(rupiahFormat.format(progress));

                    minPricePortionFilter = progress;
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        Button btn = view.findViewById(R.id.btn_done);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PackageSearchParam key = new PackageSearchParam();

                key.setPackage_category_id(packageId);
                if (ratingFilter != null) key.setRating(ratingFilter.toString());
                if (pricePortionFilter != null) key.setHighest_price(pricePortionFilter.toString());
                if (minPricePortionFilter != null)
                    key.setLowest_price(minPricePortionFilter.toString());

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
                } else {
                    if(packageId.indexOf(val)>=0){
                        packageId.remove(val);
                    }
                }
            }
        });
    }
}
