package com.example.streetbellpos.helpers;

/**
 * Created by saranya_suresh on 22/06/17.
 */
public class MyVisibilityManager {

    private static boolean mIsVisible = false;

    public static boolean getIsVisible() {
        return mIsVisible;
    }

    public static void setIsVisible(boolean visible) {
        mIsVisible = visible;
    }

}
