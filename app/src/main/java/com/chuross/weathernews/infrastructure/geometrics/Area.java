package com.chuross.weathernews.infrastructure.geometrics;

public enum Area {
    HOKKAIDO("北海道"),
    TOHOKU("東北"),
    KANTO("関東"),
    CHUBU("中部"),
    KINKI("近畿"),
    CHUGOKU("中国"),
    SHIKOKU("四国"),
    KYUSHU("九州");
    private String name;

    private Area(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
