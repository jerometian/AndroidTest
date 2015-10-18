package test.example.com.activitytest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by jerometian on 2015/10/17.
 */
public class FourActivity  extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.four_layout);

        /*TextView tv = (TextView)findViewById(R.id.TextView1);

        Intent intent = getIntent();
        String data = intent.getStringExtra("extra_data");
        tv.setText(String.valueOf(data));*/


        Button button4 = (Button)findViewById(R.id.button_4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("data_return","Hello First Activity");
                setResult(RESULT_OK,intent  );
                finish();
            }
        });

    }
}
