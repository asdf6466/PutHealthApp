package kr.co.korearental.petproto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EatResultActivity extends AppCompatActivity {

    private Button btn_return;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eat_result);

        final Intent mainIntent = new Intent(EatResultActivity.this, MainActivity.class);

        Intent weightgetintent = getIntent();

        String getweightmeg1 = weightgetintent.getStringExtra("weight_result1");
        String getweightmeg2 = weightgetintent.getStringExtra("weight_result2");

        TextView weight_result_txt = (TextView) findViewById(R.id.text_dog_weight_result);
        weight_result_txt.setText(getweightmeg1);
        TextView weight_result_txt2 = (TextView) findViewById(R.id.text_dog_weight_result2);
        weight_result_txt2.setText(getweightmeg2);

        btn_return = (Button) findViewById(R.id.button_return);
        btn_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(mainIntent);
            }
        });
    }
}
