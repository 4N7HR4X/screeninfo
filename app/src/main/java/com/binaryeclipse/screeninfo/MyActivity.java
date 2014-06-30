package com.binaryeclipse.screeninfo;

import android.app.Activity;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.widget.TextView;

public class MyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        setView();
    }

    private void setView() {

        Display display = getWindowManager().getDefaultDisplay();

        StringBuilder sb = new StringBuilder();
        sb.append("Display Properties:\n");

        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);
        String density = "Density: " + getString(R.string.density) + " - " + displayMetrics.densityDpi + " dpi";
        String dp2pixel = "dp to pixel multiplier: " + displayMetrics.scaledDensity;
        sb.append(density).append('\n');
        sb.append(dp2pixel).append('\n');

        int width, height;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            Point realSize = new Point(), appSize = new Point(), largest = new Point(), smallest = new Point();
            display.getRealSize(realSize);
            display.getSize(appSize);
            display.getCurrentSizeRange(smallest, largest);
            sb.append(String.format("Real Screen Resolution: %1$dx%2$d", realSize.x, realSize.y)).append('\n');
            sb.append(String.format("App Resolution: %1$dx%2$d", appSize.x, appSize.y)).append('\n');
            //sb.append(String.format("Minimum Resolution: %1$dx%2$d", smallest.x, smallest.y)).append('\n');
            //sb.append(String.format("Maximum Resolution: %1$dx%2$d", largest.x, largest.y)).append('\n');
            sb.append(String.format("App Resolution in dp: %1$dx%2$d", (int) (appSize.x/displayMetrics.scaledDensity), (int) (appSize.y/displayMetrics.scaledDensity)));
        } else {
            width = display.getWidth();
            height = display.getHeight();
            sb.append(String.format("Effective App Size: %1$dx%2$d", width, height));
            sb.append(String.format("App Resolution in dp: %1$dx%2$d", (int) (width/displayMetrics.scaledDensity), (int) (height/displayMetrics.scaledDensity)));
        }
        TextView textView = (TextView) findViewById(R.id.dpi_label);
        textView.setTextSize(16);
        textView.setText(sb.toString());
    }

}
