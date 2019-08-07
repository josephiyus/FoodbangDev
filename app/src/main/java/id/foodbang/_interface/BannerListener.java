package id.foodbang._interface;

import id.foodbang.model.BannerPromo;

public interface BannerListener {
    void onSeeAllPromoClick();
    void onBannerClick(BannerPromo promo);
}
