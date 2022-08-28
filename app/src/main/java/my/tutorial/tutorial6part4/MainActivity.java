package my.tutorial.tutorial6part4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {


    EditText address1;
    EditText address2;
    EditText address3;
    EditText area;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        area = findViewById(R.id.area1);
        address1 = findViewById(R.id.address1);
        address2 = findViewById(R.id.address2);
        address3 = findViewById(R.id.address3);

    }

    public void findMap(View view)
    {
        String areastr = area.getText().toString();
        String address1str = address1.getText().toString();
        String address2str = address2.getText().toString();
        String address3str = address3.getText().toString();

        Intent intent = new Intent(MainActivity.this, MapsActivity.class);
        intent.putExtra("area", areastr);
        intent.putExtra("address1", address1str);
        intent.putExtra("address2", address2str);
        intent.putExtra("address3", address3str);
        MainActivity.this.startActivity(intent);
    }
}