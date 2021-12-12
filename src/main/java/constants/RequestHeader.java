package constants;

/**
 * Common request header
 */
public final class RequestHeader {

    /**
     * Key of request header
     */
    public final class Key {

        public static final String AUTHORIZATION = "authorization";

        public static final String CLIENT_SECRET = "clientsecret";

        public static final String API_KEY = "apikey";

        public static final String SCOPE = "scope";

        public static final String KEY = "key";

        public static final String TOKEN = "token";

    }

    /**
     * Value of request header
     */
    public final class Value {

        public static final String PARENT_PORTAL_LITE = "Parent Portal Lite";
        public static final String PARENT_PORTAL = "Parent Portal";
        public static final String SYSTEM_MANAGEMENT = "System Management";
        public static final String MISSION_CONTROL = "Mission Control";
        public static final String INSIGHT = "Insight";
        public static final String PPL_CLIENT_SECRET = "138abf2e93cf885379c77054e15673e73fe70b5117f42ca498a42c3955e1658b";
        public static final String PP_CLIENT_SECRET = "dc507de47bbe7c771e642fc34aa9ca4045b6e68ba35349a88d08b6b14bd69675";
        public static final String PPL_API_KEY = "c62ac14ec31ffdd04d98268aeb6ec3b5244b1bcf";
        public static final String PP_API_KEY = "94d9b8d2c7433f7145ae5d83bf2a73310b6c0628";
        public static final String WEB_API_KEY = "11bb4c1845c7a0137ad0bc8f1df055792849df16";


        public static final String BEARER = "Bearer {accessToken}";

    }

}
