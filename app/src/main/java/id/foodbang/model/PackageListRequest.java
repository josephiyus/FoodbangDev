package id.foodbang.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PackageListRequest {
    public PackageListRequest() {
    }

    public PackageListRequest(PackageSearchParam search, PackageSortParam filter) {
        this.search = search;
        this.filter = filter;
    }

    @SerializedName("search")
    @Expose
    private PackageSearchParam search;

    @SerializedName("filter")
    @Expose
    private PackageSortParam filter;

    public PackageSearchParam getSearch() {
        return search;
    }

    public void setSearch(PackageSearchParam search) {
        this.search = search;
    }

    public PackageSortParam getFilter() {
        return filter;
    }

    public void setFilter(PackageSortParam filter) {
        this.filter = filter;
    }
}
