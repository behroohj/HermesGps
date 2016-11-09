package com.hermes.gps;

import android.graphics.Typeface;
import android.telephony.gsm.GsmCellLocation;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by imanbahmani on 9/24/16 AD.
 */

public class Font {
    public static void setAllTextView(ViewGroup parent) {
        for (int i = parent.getChildCount() - 1; i >= 0; i--) {
            final View child = parent.getChildAt(i);
            if (child instanceof ViewGroup) {
                setAllTextView((ViewGroup) child);
            } else if (child instanceof TextView) {
                ((TextView) child).setTypeface(getFont());
            }
        }
    }

    public static Typeface getFont() {
        return Typeface.createFromAsset(G.context.getAssets(), "Yekan.ttf");
    }
}
