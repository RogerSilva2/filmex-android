package br.com.infinitytechnology.filmex.utils;

import android.content.Context;
import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import br.com.infinitytechnology.filmex.R;

public class DateUtil {

    public static String format(Context context, String source) {
        try {
            String formatDateLocal = PropertyUtil.property(context, "format.date.local");
            String formatDateRemote = PropertyUtil.property(context, "format.date.remote");
            DateFormat dateFormat1 = new SimpleDateFormat(formatDateRemote, Locale.US);
            DateFormat dateFormat2 = new SimpleDateFormat(formatDateLocal,
                    context.getResources().getConfiguration().locale);
            Date date = dateFormat1.parse(source);
            return dateFormat2.format(date);
        } catch (ParseException e) {
            Log.e(context.getString(R.string.app_name), context.getString(R.string.error_date_format), e);
            return "";
        }
    }
}