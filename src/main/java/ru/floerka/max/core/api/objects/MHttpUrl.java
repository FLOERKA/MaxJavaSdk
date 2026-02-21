package ru.floerka.max.core.api.objects;

import okhttp3.HttpUrl;
import ru.floerka.max.core.http.HttpConstants;

public class MHttpUrl {

    private final String baseUrl;
    private final String args;

    public MHttpUrl(String args) {
        this.baseUrl = HttpConstants.BASE_URL;

        if(args.startsWith("/")) {
            if(baseUrl.endsWith("/"))
                this.args = args.substring(0, args.length()-1);
            else
                this.args = args;
        } else {
            if(baseUrl.endsWith("/"))
                this.args = args;
            else {
                this.args = "/" + args;
            }
        }
    }

    public String toUrl() {
        return baseUrl + args;
    }
    public okhttp3.HttpUrl.Builder toOkHttpBuilder() {
        return HttpUrl.parse(toUrl()).newBuilder();
    }
}
