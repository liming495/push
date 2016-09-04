package com.guppy.common;

import com.google.gson.JsonArray;
import com.google.gson.JsonPrimitive;
import com.guppy.common.utils.Base64;
import com.guppy.common.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Created by guppy
 * on 16-9-3 ÏÂÎç10:11.
 */
public class ServiceHelper {
    private static final Logger LOG = LoggerFactory.getLogger(ServiceHelper.class);
    private static final Pattern PUSH_PATTERNS = Pattern.compile("[^a-zA-Z0-9]");
    private static final String BASIC_PREFIX = "Basic";
    private static final Random RANDOM = new Random(System.currentTimeMillis());
    private static final int MIN = 100000;
    private static final int MAX = 2147483647;
    private static final int MAX_BADGE_NUMBER = 99999;
    private static final Pattern USERNAME_PATTERN = Pattern.compile("^[a-zA-Z0-9][a-zA-Z_0-9.¡¢¡£@,-]*");
    private static final Pattern DATE_PATTERN = Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9]{2}");
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public ServiceHelper() {
    }

    public static boolean isValidIntBadge(int intBadge) {
        return intBadge >= 0 && intBadge <= 99999;
    }

    public static int generateSendno() {
        return RANDOM.nextInt(2147383648) + 100000;
    }

    public static String getBasicAuthorization(String username, String password) {
        String encodeKey = username + ":" + password;
        return "Basic " + String.valueOf(Base64.encode(encodeKey.getBytes()));
    }

    public static void checkBasic(String appKey, String masterSecret) {
        if(!StringUtils.isEmpty(appKey) && !StringUtils.isEmpty(masterSecret)) {
            if(appKey.length() != 24 || masterSecret.length() != 24 || PUSH_PATTERNS.matcher(appKey).find() || PUSH_PATTERNS.matcher(masterSecret).find()) {
                throw new IllegalArgumentException("appKey and masterSecret format is incorrect. They should be 24 size, and be composed with alphabet and numbers. Please confirm that they are coming from JPush Web Portal.");
            }
        } else {
            throw new IllegalArgumentException("appKey and masterSecret are both required.");
        }
    }

    public static JsonArray fromSet(Set<String> sets) {
        JsonArray array = new JsonArray();
        if(null != sets && sets.size() > 0) {
            Iterator var2 = sets.iterator();

            while(var2.hasNext()) {
                String item = (String)var2.next();
                array.add(new JsonPrimitive(item));
            }
        }

        return array;
    }

    public static boolean checkUsername(String username) {
        return USERNAME_PATTERN.matcher(username).matches();
    }

    public static boolean isValidBirthday(String birthday) {
        try {
            if(!DATE_PATTERN.matcher(birthday).matches()) {
                return false;
            } else {
                DATE_FORMAT.parse(birthday);
                return true;
            }
        } catch (Exception var2) {
            LOG.error("incorrect date format. " + birthday, var2);
            return false;
        }
    }

    static {
        DATE_FORMAT.setLenient(false);
    }
}
