package test.example.com.uiwidgettest;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import test.example.com.uiwidgettest.R;

/**
 * Created by jerometian on 2015/10/18.
 */
public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.action_main);

    }
}
