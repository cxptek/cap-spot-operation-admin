package com.cxptek.constant;


import java.util.List;
import java.util.Locale;

public class Constant {
    public static final String PAGE_DEFAULT = "1";
    public static final String PAGE_SIZE = "15";
    public static final String SORT_DEFAULT = "createdAt";
    public static final String SORT_DIRECTION_DEFAULT = "DESC";

    // API Key
    public static final int AES_KEY_LENGTH = 256;
    public static final int API_KEY_LENGTH = 64;

    public static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*\\W)(?=\\S+$).{8,30}$";
    public static final String EMAIL_REGEX = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

    public static final String BLACK_LIST_KEY_CACHE = "BRIDE_BLACK_LIST_KEY_CACHE";

    public static final String PATTERN_DATE_YYYMMDD_HHMMSS = "yyyy-MM-dd HH:mm:ss";

    public static final String PATTERN_DATE_YYYMMDD_T_HHMMSS = "yyyy-MM-dd'T'HH:mm:ssXXX";

    public static final Locale LOCALE_EN = new Locale("en", "US");

    public static final List<Locale> SUPPORTED_LOCALES = List.of(
            LOCALE_EN
    );

    // Auth jwt claims
    public static final String USERNAME_CLAIM = "username";
    public static final String AUTHORITIES_CLAIM = "authorities";

    public static final String URL_PATTERN = "https?://(localhost(:\\d+)?|[\\w.-]+)(/v\\d+)?(/[\\w.-]+)*(\\?([\\w.-]+=[\\w.-]+(&[\\w.-]+=[\\w.-]+)*)?)?";

    public static final String NEW_LINE = "\n";

    // Roles

    public static final String ROLE_MANAGER = "MANAGER";
    public static final String ROLE_OPERATOR = "OPERATOR";
    public static final List<String> SYS_USER_ROLES = List.of(ROLE_MANAGER, ROLE_OPERATOR);

    public static final String ROLE_PARTNER = "PARTNER";

    // Caches
    public static final String IN_MEMORY_CACHE_MANAGE = "inMemoryCache";
    public static final String SYS_USER_CACHE = "SYS_USER_CACHE";
    public static final String PARTNERS_CACHE = "PARTNERS_CACHE";
    public static final String PARTNER_CACHE_BY_ID = "PARTNER_CACHE_BY_ID";
    public static final String PARTNER_PUBLIC_KEYS_CACHE_BY_ID = "PARTNER_PUBLIC_KEYS_CACHE_BY_ID";

    public static final String X_PARTNER_ID = "X-PARTNER-ID";
    public static final String TRADING_PAIR_FOR_USER_OF_CEX = "CEX";
}
