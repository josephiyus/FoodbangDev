package id.foodbang.adapter;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.foodbang.PackageActivity;
import id.foodbang.R;
import id.foodbang.model.PackageData;
import id.foodbang.utils.RoundedTransformation;
import id.foodbang.utils.ThousandSeparator;

public class PackageAllAdapter extends RecyclerView.Adapter<PackageAllAdapter.ViewHolder> {
    private final Context context;
    private List<PackageData> packages;

    public PackageAllAdapter(Context context, List<PackageData> packages) {
        this.context = context;
        this.packages = packages;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_package_list1, viewGroup, false);

        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.tvPackageName.setText(packages.get(i).getName());
        viewHolder.tvMerchantName.setText(packages.get(i).getMerchant());
        viewHolder.tvPackageDesc.setText(packages.get(i).getDescription());
        viewHolder.tvPackagePrice.setText("Rp. " + ThousandSeparator.createCurrency(String.valueOf(packages.get(i).getPrice())));

        if (packages.get(i).getFiles() == null){
            Picasso.with(context).load(getUriDrawable(R.drawable.no_image))
                    .transform(new RoundedTransformation(16, 0)).into(viewHolder.ivPackage);
        }else{
            Picasso.with(context).load(packages.get(i).getFiles())
                    .transform(new RoundedTransformation(16, 0)).into(viewHolder.ivPackage);
        }

        viewHolder.cvPackage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PackageActivity.class);
                intent.putExtra("package_id", packages.get(i).getId());
                intent.putExtra("package_image", packages.get(i).getFiles());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return packages.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.iv_package1)
        ImageView ivPackage;
        @BindView(R.id.cv_package1)
        CardView cvPackage;
        @BindView(R.id.tv_package_name1)
        TextView tvPackageName;
        @BindView(R.id.tv_merchant_name)
        TextView tvMerchantName;
        @BindView(R.id.tv_package_description)
        TextView tvPackageDesc;
        @BindView(R.id.tv_package_price)
        TextView tvPackagePrice;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private Uri getUriDrawable(int resId){
        return Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE +
                "://" + context.getResources().getResourcePackageName(resId)
                + '/' + context.getResources().getResourceTypeName(resId) + '/' + context.getResources().getResourceEntryName(resId));
    }
}
