package test.example.com.activitytest;

import android.app.Activity;
import android.content.Intent;
import android.drm.DrmStore;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by jerometian on 2015/10/17.
 */
public class FirstActivity extends Activity {

    private  TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.first_layout);
        Button button1 = (Button)findViewById(R.id.button_1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
/*                Toast.makeText(FirstActivity.this, "You clicked Button 1",
                        Toast.LENGTH_SHORT).show();*/

              /*  Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
                startActivity(intent);*/

                /*Intent intent = new Intent("com.example.activitytest.ACTION_START");
               intent.addCategory("com.example.activitytest.MY_CATEGORY");
              //  Intent intent = new Intent(Intent.ACTION_VIEW);
               intent.setData(Uri.parse("http://www.baidu.com"));
                startActivity(intent);*/

            /*    Intent intent= new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:1086"));
                startActivity(intent);*/

            /*    String data = "Hello SecondActivity";
                Intent intent = new Intent(FirstActivity.this,FourActivity.class);
                intent.putExtra("extra_data", data);
                startActivity(intent);*/

                tv = (TextView)findViewById(R.id.TextViewFirst_layout);

                Intent intent = new Intent(FirstActivity.this,FourActivity.class);
                startActivityForResult(intent,1);


            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_item:
                Toast.makeText(this, "You clicked Add", Toast.LENGTH_SHORT).show();
                break;
            case R.id.remove_item:
                Toast.makeText(this, "You clicked Remove", Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            switch (requestCode)
            {
                case 1:
                    if ( resultCode == RESULT_OK)
                    {
                        String returnedData = data.getStringExtra("data_return");
                        Log.d("FirstActivity", returnedData);
                        tv.setText(returnedData);
                    }
                    break;
                default:

            }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }
}
