package br.com.infinitytechnology.filmex.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.Properties;

import br.com.infinitytechnology.filmex.R;

public class PropertyUtil {

    public static String property(Context context, String key) {
        try {
            Properties properties = new Properties();
            AssetManager assetManager = context.getAssets();
            InputStream inputStream = assetManager.open("config.properties");
            properties.load(inputStream);
            return properties.getProperty(key);
        } catch (IOException e) {
            Log.e(context.getString(R.string.app_name), context.getString(R.string.error_getting_property), e);
            return "";
        }
    }
}