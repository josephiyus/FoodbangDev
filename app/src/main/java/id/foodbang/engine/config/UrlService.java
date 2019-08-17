package id.foodbang.engine.config;

public class UrlService {

    public static final String staging_url = "http://192.168.0.112:3000/api/v1/";
    public static final String production_url = "http://foodbang.id/api/v1/";

    public static final String login = "customers/login";
    public static final String packages = "publics/packages";
    public static final String packages_search = "publics/packages/lists";
    public static final String _package = "publics/packages/{id}";
    public static final String orders = "customers/orders";
    public static final String merchant = "publics/merchants/{id}";
    public static final String signup = "customers/signup";
}
