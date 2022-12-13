package com.example.demo.Constants;

public class AppConstants {
    // TODO: enumでのconstの運用も考えていく必要がある
    // TODO: enumで組むとどういうフォルダ構成などになるのか
    // 現場情報モデルクラスのデータサイズ
    public static final int MAX_SIZE_ID = 255;
    public static final int MAX_SIZE_NAME = 255;
    public static final int MAX_SIZE_SUB_NAME = 255;
    public static final int MAX_SIZE_TYPE = 255;
    public static final int MAX_SIZE_STAFF_NAME = 255;
    public static final int MAX_SIZE_PHOTO = 16383;
    public static final int MAX_SIZE_ADDRESS = 255;
    public static final int MAX_SIZE_STATUS = 255;
    public static final int MAX_SIZE_START_AT = 255;
    public static final int MAX_SIZE_END_AT = 255;
    public static final int MAX_SIZE_CREATED_AT = 255;
    public static final int MAX_SIZE_UPDATED_AT = 255;

    // 問い合わせURL
    public static final String WORK_SITE_URL = "/api/v1/worksites";
    public static final String WORK_SITE_REQUEST_BY_ID_URL = "/{id}";

    // Cors許可URL
    public static final String WORK_SITE_CORS = "http://127.0.0.1:8081";

}
