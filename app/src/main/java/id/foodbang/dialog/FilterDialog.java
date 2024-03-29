package id.foodbang.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatDialogFragment;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RatingBar;
import android.widget.TextView;

import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarChangeListener;
import com.crystal.crystalrangeseekbar.widgets.CrystalRangeSeekbar;

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
    private final SparseArray<String> checkboxes = new SparseArray<>();
    private final SparseArray<String> locations = new SparseArray<>();
    private RatingBar ratingBar;
    private CrystalRangeSeekbar seekBarPricePortion;
    private CrystalRangeSeekbar seekBarMinPortion;

    private FoodbangAppCompatActivity parent;

    private final List<String> packageId = new ArrayList<>();
    private final List<String> locationId = new ArrayList<>();
    private Float ratingFilter;

    private Number minPricePortionFilter;
    private Number maxPricePortionFilter;

    private Number minPriceMinPortionFilter;
    private Number maxPriceMinPortionFilter;

    public void setParent(FoodbangAppCompatActivity parent) {
        this.parent = parent;
    }

    @Override
    public void onDestroy() {
        if (this.ratingBar != null) this.ratingBar = null;
        if (this.seekBarPricePortion != null) this.seekBarPricePortion = null;
        if (this.seekBarMinPortion != null) this.seekBarMinPortion = null;
        if (this.parent != null) this.parent = null;

        if (this.ratingFilter != null) this.ratingFilter = null;
        if (this.minPricePortionFilter != null) this.minPricePortionFilter = null;
        if (this.maxPricePortionFilter != null) this.maxPricePortionFilter = null;
        if (this.minPriceMinPortionFilter != null) this.minPriceMinPortionFilter = null;
        if (this.maxPriceMinPortionFilter != null) this.maxPriceMinPortionFilter = null;

        super.onDestroy();
    }

    private void initializeCatCheckboxes(final View view) {
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

            applyCatCheckboxValue(view, key, ch);
        }
    }

    private void initializeLocCheckboxes(final View view) {
        locations.put(R.id.loc1, "1");
        locations.put(R.id.loc2, "2");
        locations.put(R.id.loc3, "3");
        locations.put(R.id.loc4, "4");
        locations.put(R.id.loc5, "5");
        locations.put(R.id.loc6, "6");
        locations.put(R.id.loc7, "7");
        locations.put(R.id.loc8, "8");
        locations.put(R.id.loc9, "9");
        locations.put(R.id.loc10, "10");
        locations.put(R.id.loc11, "11");

        for (int i = 0; i < locations.size(); i++) {
            int key = locations.keyAt(i);
            String ch = locations.get(key);

            applyLocCheckboxValue(view, key, ch);
        }
    }

    @Override
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        if (getActivity() != null) {
            LayoutInflater inflater = getActivity().getLayoutInflater();
            final View view = inflater.inflate(R.layout.dialog_filter_pkg, null);

            this.initializeCatCheckboxes(view);
            this.initializeLocCheckboxes(view);

            ratingBar = view.findViewById(R.id.ratingBar);
            ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                    if (fromUser) {
                        ratingBar.setRating(rating);
                        ratingFilter = rating;
                    }
                }
            });

            seekBarPricePortion = view.findViewById(R.id.seekBarPricePortion);
            seekBarPricePortion.setOnRangeSeekbarChangeListener(new OnRangeSeekbarChangeListener() {
                @Override
                public void valueChanged(Number minValue, Number maxValue) {
                    TextView minPricePortion = view.findViewById(R.id.txt_minPricePortion);
                    TextView maxPricePortion = view.findViewById(R.id.txt_maxPricePortion);

                    DecimalFormat rupiahFormat = RupiahFormat.getInstance();

                    minPricePortion.setText(rupiahFormat.format(minValue));
                    maxPricePortion.setText(rupiahFormat.format(maxValue));

                    minPricePortionFilter = minValue;
                    maxPricePortionFilter = maxValue;
                }
            });


            seekBarMinPortion = view.findViewById(R.id.seekBarMinPortion);
            seekBarMinPortion.setOnRangeSeekbarChangeListener(new OnRangeSeekbarChangeListener() {
                @Override
                public void valueChanged(Number minValue, Number maxValue) {
                    TextView minPriceMinPortion = view.findViewById(R.id.txt_minPriceMinPortion);
                    TextView maxPriceMinPortion = view.findViewById(R.id.txt_maxPriceMinPortion);

                    DecimalFormat rupiahFormat = RupiahFormat.getInstance();

                    minPriceMinPortion.setText(rupiahFormat.format(minValue));
                    maxPriceMinPortion.setText(rupiahFormat.format(maxValue));

                    minPriceMinPortionFilter = minValue;
                    maxPriceMinPortionFilter = maxValue;
                }
            });

            final Button btnSearch = view.findViewById(R.id.btn_done);
            btnSearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PackageSearchParam key = new PackageSearchParam();

                    key.setPackage_category_id(packageId);
                    key.setPackage_location_id(locationId);

                    if (ratingFilter != null) key.setRating(ratingFilter.toString());

                    if (minPricePortionFilter != null)
                        key.setLowest_price(minPricePortionFilter.toString());
                    if (maxPricePortionFilter != null)
                        key.setHighest_price(maxPricePortionFilter.toString());

                    if (minPriceMinPortionFilter != null)
                        key.setLowest_price_minportion(minPriceMinPortionFilter.toString());
                    if (maxPriceMinPortionFilter != null)
                        key.setHighest_price_minportion(maxPriceMinPortionFilter.toString());

                    if (minPricePortionFilter != null)
                        key.setLowest_price(minPricePortionFilter.toString());

                    Map<String, Object> param = new HashMap<>();
                    param.put("searchKey", key);

                    parent.parentProcess(param);

                    FilterDialog.this.dismiss();
                }
            });

            final Button btnReset = view.findViewById(R.id.btn_reset);
            btnReset.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View vw) {
                    ratingBar.setRating(0);

                    seekBarPricePortion.setMinValue(0).setMaxValue(1000000).apply();

                    seekBarMinPortion.setMinValue(0).setMaxValue(1000000).apply();

                    for (int i = 0; i < checkboxes.size(); i++) {
                        int key = checkboxes.keyAt(i);
                        String ch = checkboxes.get(key);

                        removeCatCheckboxValue(view, key, ch);
                    }

                    for (int i = 0; i < locations.size(); i++) {
                        int key = locations.keyAt(i);
                        String ch = locations.get(key);

                        removeLocCheckboxValue(view, key, ch);
                    }
                }
            });

            builder.setView(view);
        }

        return builder.create();
    }

    private void applyCatCheckboxValue(final View view, final Integer id, final String val) {
        final CheckBox cb = view.findViewById(id);
        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
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

    private void removeCatCheckboxValue(final View view, final Integer id, final String val) {
        final CheckBox cb = view.findViewById(id);

        if (packageId.indexOf(val) >= 0) {
            cb.setChecked(false);
            packageId.remove(val);
        }
    }

    private void applyLocCheckboxValue(final View view, final Integer id, final String val) {
        final CheckBox cb = view.findViewById(id);

        cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cb.isChecked()) {
                    if (locationId.indexOf(val) < 0) {
                        locationId.add(val);
                    }
                } else {
                    if (locationId.indexOf(val) >= 0) {
                        locationId.remove(val);
                    }
                }
            }
        });
    }

    private void removeLocCheckboxValue(final View view, final Integer id, final String val) {
        final CheckBox cb = view.findViewById(id);

        if (locationId.indexOf(val) >= 0) {
            cb.setChecked(false);
            locationId.remove(val);
        }
    }
}
