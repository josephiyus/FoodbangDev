package id.foodbang.model;

public class OrderStepItem {
    private final String title;
    private String summary;
    private Boolean is_editable;

    public OrderStepItem(String title, String summary, Boolean is_editable) {
        this.title = title;
        this.summary = summary;
        this.is_editable = is_editable;
    }

    public String getTitle() {
        return title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Boolean getIs_editable() {
        return is_editable;
    }

    public void setIs_editable(Boolean is_editable) {
        this.is_editable = is_editable;
    }
}