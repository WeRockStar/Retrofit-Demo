package com.cskku.werockstar.retrofitdemo.base;

import android.app.Application;

import com.cskku.werockstar.retrofitdemo.R;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Kotchaphan on 24/10/2558.
 */
public class ApplicationFonts extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/sukhumvit.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());
    }
}
