package com.example.modelpaper;

import android.provider.BaseColumns;

public class UserProfile {
    private UserProfile() {
    }

    public static class Users implements BaseColumns{

        public static final String TABLE_NAME = "UserInfo";
        public static final String COLUMN_USER_NAME = "userName";
        public static final String COLUMN_PASSWORD = "password";
        public static final String COLUMN_DATE_OF_BIRTH = "dateOfBirth";
        public static final String COLUMN_GENDER = "Gender";

    }

}
