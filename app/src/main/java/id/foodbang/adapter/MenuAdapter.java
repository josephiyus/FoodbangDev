package id.foodbang.adapter;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
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
import id.foodbang.R;
import id.foodbang.model.Menu;
import id.foodbang.utils.RoundedTransformation;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {
    private final Context context;
    private final List<Menu> menus;
    private final Boolean plain;

    public MenuAdapter(Context context, List<Menu> menus, boolean plain) {
        this.context = context;
        this.menus = menus;
        this.plain = plain;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_menu_list, viewGroup, false);

        if (plain){
            view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.item_menu_list1, viewGroup, false);

            return new ViewHolder(view);
        }

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        if(plain){
            viewHolder.tvMenuName.setText(menus.get(i).getName());
        }
        else {
            Picasso.with(context).load(getUriDrawable(R.drawable.no_image))
                    .transform(new RoundedTransformation(32, 0)).into(viewHolder.ivMenu);
            viewHolder.tvMenuName.setText(menus.get(i).getName());
            viewHolder
                    .tvMenuDescription
                    .setText(menus.get(i).getDescription());
        }
    }

    @Override
    public int getItemCount() {
        return menus.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.iv_menu)
        ImageView ivMenu;
        @BindView(R.id.tv_menu_name)
        TextView tvMenuName;
        @BindView(R.id.tv_menu_description)
        TextView tvMenuDescription;

        public ViewHolder(@NonNull View itemView) {
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
