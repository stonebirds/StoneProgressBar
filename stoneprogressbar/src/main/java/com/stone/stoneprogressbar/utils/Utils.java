package com.stone.stoneprogressbar.utils;

import android.content.Context;
import android.util.TypedValue;

public class Utils {
    /**
     * dp 2 px
     *
     * @param dpVal
     */
    public static int dp2px(Context context,int dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, context.getResources().getDisplayMetrics());
    }

    /**
     * sp 2 px
     *
     * @param spVal
     * @return
     */
    public static int sp2px(Context context,int spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, context.getResources().getDisplayMetrics());

    }
}
