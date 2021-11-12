package kr.co.korearental.petproto;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Parcelable;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.os.Bundle;

public class EatActivity extends AppCompatActivity {

    private Button btn_cal_eat;
    private EditText edit_dogweight;
    private EditText edit_weight_answer;

    private RadioButton radio1;
    private RadioButton radio2;
    private RadioButton radio3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eat);

        btn_cal_eat = (Button) findViewById(R.id.button_eat_answer);
        
        final Intent resultIntent = new Intent(EatActivity.this, EatResultActivity.class);

        final RadioGroup radio_Group_eat = (RadioGroup) findViewById(R.id.radio_group_eat);
        radio_Group_eat.clearCheck();

        btn_cal_eat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int rbe = ((RadioGroup) radio_Group_eat.findViewById(R.id.radio_group_eat)).getCheckedRadioButtonId();

                edit_dogweight = (EditText) findViewById(R.id.edit_weight);
                int weight = Integer.parseInt(edit_dogweight.getText().toString());
                TextView result = (TextView) findViewById(R.id.text_invisible);
                TextView result2 = (TextView) findViewById(R.id.text_invisible2);

                if(edit_dogweight.getText().length() == 0){
                    Toast.makeText(EatActivity.this,"숫자입력",Toast.LENGTH_LONG).show();
                    return;
                }

                switch(rbe){
                    case R.id.radio_minus:{
                        if(weight>=1 && weight<2)
                        {
                            result.setText("70Kcal");
                            result2.setText("22g 급여");
                            String message = result.getText().toString();
                            String message2 = result2.getText().toString();
                            resultIntent.putExtra("weight_result1", message);
                            resultIntent.putExtra("weight_result2", message2);
                            startActivity(resultIntent);
                        }
                        else if(weight>=2 && weight<3)
                        {
                            result.setText("118Kcal");
                            result2.setText("37g 급여");
                            String message = result.getText().toString();
                            String message2 = result2.getText().toString();
                            resultIntent.putExtra("weight_result1", message);
                            resultIntent.putExtra("weight_result2", message2);
                            startActivity(resultIntent);
                        }
                        else if(weight>=3 && weight<4)
                        {
                            result.setText("160Kcal");
                            result2.setText("50g 급여");
                            String message = result.getText().toString();
                            String message2 = result2.getText().toString();
                            resultIntent.putExtra("weight_result1", message);
                            resultIntent.putExtra("weight_result2", message2);
                            startActivity(resultIntent);
                        }
                        else if(weight>=4 && weight<5)
                        {
                            result.setText("198Kcal");
                            result2.setText("62g 급여");
                            String message = result.getText().toString();
                            String message2 = result2.getText().toString();
                            resultIntent.putExtra("weight_result1", message);
                            resultIntent.putExtra("weight_result2", message2);
                            startActivity(resultIntent);
                        }
                        else if(weight>=5 && weight<6)
                        {
                            result.setText("234Kcal");
                            result2.setText("73g 급여");
                            String message = result.getText().toString();
                            String message2 = result2.getText().toString();
                            resultIntent.putExtra("weight_result1", message);
                            resultIntent.putExtra("weight_result2", message2);
                            startActivity(resultIntent);
                        }
                        else if(weight>=6 && weight<7)
                        {
                            result.setText("268Kcal");
                            result2.setText("84g 급여");
                            String message = result.getText().toString();
                            String message2 = result2.getText().toString();
                            resultIntent.putExtra("weight_result1", message);
                            resultIntent.putExtra("weight_result2", message2);
                            startActivity(resultIntent);
                        }
                        else if(weight>=7 && weight<8)
                        {
                            result.setText("301Kcal");
                            result2.setText("95g 급여");
                            String message = result.getText().toString();
                            String message2 = result2.getText().toString();
                            resultIntent.putExtra("weight_result1", message);
                            resultIntent.putExtra("weight_result2", message2);
                            startActivity(resultIntent);
                        }
                        else if(weight>=8 && weight<9)
                        {
                            result.setText("333Kcal");
                            result2.setText("105g 급여");
                            String message = result.getText().toString();
                            String message2 = result2.getText().toString();
                            resultIntent.putExtra("weight_result1", message);
                            resultIntent.putExtra("weight_result2", message2);
                            startActivity(resultIntent);
                        }
                        else if(weight>=9 && weight<10)
                        {
                            result.setText("364Kcal");
                            result2.setText("114g 급여");
                            String message = result.getText().toString();
                            String message2 = result2.getText().toString();
                            resultIntent.putExtra("weight_result1", message);
                            resultIntent.putExtra("weight_result2", message2);
                            startActivity(resultIntent);
                        }
                        else if(weight>=10 && weight<11)
                        {
                            result.setText("394Kcal");
                            result2.setText("124g 급여");
                            String message = result.getText().toString();
                            String message2 = result2.getText().toString();
                            resultIntent.putExtra("weight_result1", message);
                            resultIntent.putExtra("weight_result2", message2);
                            startActivity(resultIntent);
                        }
                        else if(weight>=11 && weight<12)
                        {
                            result.setText("423Kcal");
                            result2.setText("133g 급여");
                            String message = result.getText().toString();
                            String message2 = result2.getText().toString();
                            resultIntent.putExtra("weight_result1", message);
                            resultIntent.putExtra("weight_result2", message2);
                            startActivity(resultIntent);
                        }
                        else if(weight>=12 && weight<13)
                        {
                            result.setText("451Kcal");
                            result2.setText("142g 급여");
                            String message = result.getText().toString();
                            String message2 = result2.getText().toString();
                            resultIntent.putExtra("weight_result1", message);
                            resultIntent.putExtra("weight_result2", message2);
                            startActivity(resultIntent);
                        }
                        else if(weight>=13 && weight<14)
                        {
                            result.setText("479Kcal");
                            result2.setText("150g 급여");
                            String message = result.getText().toString();
                            String message2 = result2.getText().toString();
                            resultIntent.putExtra("weight_result1", message);
                            resultIntent.putExtra("weight_result2", message2);
                            startActivity(resultIntent);
                        }
                        else if(weight>=14 && weight<15)
                        {
                            result.setText("507Kcal");
                            result2.setText("159g 급여");
                            String message = result.getText().toString();
                            String message2 = result2.getText().toString();
                            resultIntent.putExtra("weight_result1", message);
                            resultIntent.putExtra("weight_result2", message2);
                            startActivity(resultIntent);
                        }
                        else if(weight>=15 && weight<20)
                        {
                            result.setText("534Kcal");
                            result2.setText("168g 급여");
                            String message = result.getText().toString();
                            String message2 = result2.getText().toString();
                            resultIntent.putExtra("weight_result1", message);
                            resultIntent.putExtra("weight_result2", message2);
                            startActivity(resultIntent);
                        }
                        else if(weight>=20 && weight<25)
                        {
                            result.setText("662Kcal");
                            result2.setText("208g 급여");
                            String message = result.getText().toString();
                            String message2 = result2.getText().toString();
                            resultIntent.putExtra("weight_result1", message);
                            resultIntent.putExtra("weight_result2", message2);
                            startActivity(resultIntent);
                        }
                        else if(weight>=25 && weight<30)
                        {
                            result.setText("783Kcal");
                            result2.setText("246g 급여");
                            String message = result.getText().toString();
                            String message2 = result2.getText().toString();
                            resultIntent.putExtra("weight_result1", message);
                            resultIntent.putExtra("weight_result2", message2);
                            startActivity(resultIntent);
                        }
                        else if(weight>=30 && weight<35)
                        {
                            result.setText("897Kcal");
                            result2.setText("282g 급여");
                            String message = result.getText().toString();
                            String message2 = result2.getText().toString();
                            resultIntent.putExtra("weight_result1", message);
                            resultIntent.putExtra("weight_result2", message2);
                            startActivity(resultIntent);
                        }
                        else if(weight>=35 && weight<40)
                        {
                            result.setText("1007Kcal");
                            result2.setText("316g 급여");
                            String message = result.getText().toString();
                            String message2 = result2.getText().toString();
                            resultIntent.putExtra("weight_result1", message);
                            resultIntent.putExtra("weight_result2", message2);
                            startActivity(resultIntent);
                        }
                        else if(weight>=40 && weight<45)
                        {
                            result.setText("1113Kcal");
                            result2.setText("350g 급여");
                            String message = result.getText().toString();
                            String message2 = result2.getText().toString();
                            resultIntent.putExtra("weight_result1", message);
                            resultIntent.putExtra("weight_result2", message2);
                            startActivity(resultIntent);
                        }
                        else if(weight>=45 && weight<50)
                        {
                            result.setText("1216Kcal");
                            result2.setText("382g 급여");
                            String message = result.getText().toString();
                            String message2 = result2.getText().toString();
                            resultIntent.putExtra("weight_result1", message);
                            resultIntent.putExtra("weight_result2", message2);
                            startActivity(resultIntent);
                        }
                        else if(weight>=50 && weight<55)
                        {
                            result.setText("1316Kcal");
                            result2.setText("413g 급여");
                            String message = result.getText().toString();
                            String message2 = result2.getText().toString();
                            resultIntent.putExtra("weight_result1", message);
                            resultIntent.putExtra("weight_result2", message2);
                            startActivity(resultIntent);
                        }
                        else if(weight>=55 && weight<60)
                        {
                            result.setText("1414Kcal");
                            result2.setText("444g 급여");
                            String message = result.getText().toString();
                            String message2 = result2.getText().toString();
                            resultIntent.putExtra("weight_result1", message);
                            resultIntent.putExtra("weight_result2", message2);
                            startActivity(resultIntent);
                        }
                        else if(weight>=60)
                        {
                            result.setText("1509Kcal");
                            result2.setText("474g 급여");
                            String message = result.getText().toString();
                            String message2 = result2.getText().toString();
                            resultIntent.putExtra("weight_result1", message);
                            resultIntent.putExtra("weight_result2", message2);
                            startActivity(resultIntent);
                        }
                        break;
                    }
                    case R.id.radio_keep:{
                        if(weight>=1 && weight<2)
                        {
                            result.setText("112Kcal");
                            result2.setText("35g 급여");
                            String message = result.getText().toString();
                            String message2 = result2.getText().toString();
                            resultIntent.putExtra("weight_result1", message);
                            resultIntent.putExtra("weight_result2", message2);
                            startActivity(resultIntent);
                        }
                        else if(weight>=2 && weight<3)
                        {
                            result.setText("188Kcal");
                            result2.setText("59g 급여");
                            String message = result.getText().toString();
                            String message2 = result2.getText().toString();
                            resultIntent.putExtra("weight_result1", message);
                            resultIntent.putExtra("weight_result2", message2);
                            startActivity(resultIntent);
                        }
                        else if(weight>=3 && weight<4)
                        {
                            result.setText("255Kcal");
                            result2.setText("80g 급여");
                            String message = result.getText().toString();
                            String message2 = result2.getText().toString();
                            resultIntent.putExtra("weight_result1", message);
                            resultIntent.putExtra("weight_result2", message2);
                            startActivity(resultIntent);
                        }
                        else if(weight>=4 && weight<5)
                        {
                            result.setText("317Kcal");
                            result2.setText("99g 급여");
                            String message = result.getText().toString();
                            String message2 = result2.getText().toString();
                            resultIntent.putExtra("weight_result1", message);
                            resultIntent.putExtra("weight_result2", message2);
                            startActivity(resultIntent);
                        }
                        else if(weight>=5 && weight<6)
                        {
                            result.setText("374Kcal");
                            result2.setText("118g 급여");
                            String message = result.getText().toString();
                            String message2 = result2.getText().toString();
                            resultIntent.putExtra("weight_result1", message);
                            resultIntent.putExtra("weight_result2", message2);
                            startActivity(resultIntent);
                        }
                        else if(weight>=6 && weight<7)
                        {
                            result.setText("429Kcal");
                            result2.setText("135g 급여");
                            String message = result.getText().toString();
                            String message2 = result2.getText().toString();
                            resultIntent.putExtra("weight_result1", message);
                            resultIntent.putExtra("weight_result2", message2);
                            startActivity(resultIntent);
                        }
                        else if(weight>=7 && weight<8)
                        {
                            result.setText("482Kcal");
                            result2.setText("151g 급여");
                            String message = result.getText().toString();
                            String message2 = result2.getText().toString();
                            resultIntent.putExtra("weight_result1", message);
                            resultIntent.putExtra("weight_result2", message2);
                            startActivity(resultIntent);
                        }
                        else if(weight>=8 && weight<9)
                        {
                            result.setText("533Kcal");
                            result2.setText("167g 급여");
                            String message = result.getText().toString();
                            String message2 = result2.getText().toString();
                            resultIntent.putExtra("weight_result1", message);
                            resultIntent.putExtra("weight_result2", message2);
                            startActivity(resultIntent);
                        }
                        else if(weight>=9 && weight<10)
                        {
                            result.setText("582Kcal");
                            result2.setText("183g 급여");
                            String message = result.getText().toString();
                            String message2 = result2.getText().toString();
                            resultIntent.putExtra("weight_result1", message);
                            resultIntent.putExtra("weight_result2", message2);
                            startActivity(resultIntent);
                        }
                        else if(weight>=10 && weight<11)
                        {
                            result.setText("630Kcal");
                            result2.setText("198g 급여");
                            String message = result.getText().toString();
                            String message2 = result2.getText().toString();
                            resultIntent.putExtra("weight_result1", message);
                            resultIntent.putExtra("weight_result2", message2);
                            startActivity(resultIntent);
                        }
                        else if(weight>=11 && weight<12)
                        {
                            result.setText("676Kcal");
                            result2.setText("212g 급여");
                            String message = result.getText().toString();
                            String message2 = result2.getText().toString();
                            resultIntent.putExtra("weight_result1", message);
                            resultIntent.putExtra("weight_result2", message2);
                            startActivity(resultIntent);
                        }
                        else if(weight>=12 && weight<13)
                        {
                            result.setText("722Kcal");
                            result2.setText("227g 급여");
                            String message = result.getText().toString();
                            String message2 = result2.getText().toString();
                            resultIntent.putExtra("weight_result1", message);
                            resultIntent.putExtra("weight_result2", message2);
                            startActivity(resultIntent);
                        }
                        else if(weight>=13 && weight<14)
                        {
                            result.setText("767Kcal");
                            result2.setText("241g 급여");
                            String message = result.getText().toString();
                            String message2 = result2.getText().toString();
                            resultIntent.putExtra("weight_result1", message);
                            resultIntent.putExtra("weight_result2", message2);
                            startActivity(resultIntent);
                        }
                        else if(weight>=14 && weight<15)
                        {
                            result.setText("811Kcal");
                            result2.setText("255g 급여");
                            String message = result.getText().toString();
                            String message2 = result2.getText().toString();
                            resultIntent.putExtra("weight_result1", message);
                            resultIntent.putExtra("weight_result2", message2);
                            startActivity(resultIntent);
                        }
                        else if(weight>=15 && weight<20)
                        {
                            result.setText("854Kcal");
                            result2.setText("268g 급여");
                            String message = result.getText().toString();
                            String message2 = result2.getText().toString();
                            resultIntent.putExtra("weight_result1", message);
                            resultIntent.putExtra("weight_result2", message2);
                            startActivity(resultIntent);
                        }
                        else if(weight>=20 && weight<25)
                        {
                            result.setText("1059Kcal");
                            result2.setText("333g 급여");
                            String message = result.getText().toString();
                            String message2 = result2.getText().toString();
                            resultIntent.putExtra("weight_result1", message);
                            resultIntent.putExtra("weight_result2", message2);
                            startActivity(resultIntent);
                        }
                        else if(weight>=25 && weight<30)
                        {
                            result.setText("1252Kcal");
                            result2.setText("393g 급여");
                            String message = result.getText().toString();
                            String message2 = result2.getText().toString();
                            resultIntent.putExtra("weight_result1", message);
                            resultIntent.putExtra("weight_result2", message2);
                            startActivity(resultIntent);
                        }
                        else if(weight>=30 && weight<35)
                        {
                            result.setText("1436Kcal");
                            result2.setText("451g 급여");
                            String message = result.getText().toString();
                            String message2 = result2.getText().toString();
                            resultIntent.putExtra("weight_result1", message);
                            resultIntent.putExtra("weight_result2", message2);
                            startActivity(resultIntent);
                        }
                        else if(weight>=35 && weight<40)
                        {
                            result.setText("1612Kcal");
                            result2.setText("506g 급여");
                            String message = result.getText().toString();
                            String message2 = result2.getText().toString();
                            resultIntent.putExtra("weight_result1", message);
                            resultIntent.putExtra("weight_result2", message2);
                            startActivity(resultIntent);
                        }
                        else if(weight>=40 && weight<45)
                        {
                            result.setText("1781Kcal");
                            result2.setText("559g 급여");
                            String message = result.getText().toString();
                            String message2 = result2.getText().toString();
                            resultIntent.putExtra("weight_result1", message);
                            resultIntent.putExtra("weight_result2", message2);
                            startActivity(resultIntent);
                        }
                        else if(weight>=45 && weight<50)
                        {
                            result.setText("1946Kcal");
                            result2.setText("611g 급여");
                            String message = result.getText().toString();
                            String message2 = result2.getText().toString();
                            resultIntent.putExtra("weight_result1", message);
                            resultIntent.putExtra("weight_result2", message2);
                            startActivity(resultIntent);
                        }
                        else if(weight>=50 && weight<55)
                        {
                            result.setText("2106Kcal");
                            result2.setText("661g 급여");
                            String message = result.getText().toString();
                            String message2 = result2.getText().toString();
                            resultIntent.putExtra("weight_result1", message);
                            resultIntent.putExtra("weight_result2", message2);
                            startActivity(resultIntent);
                        }
                        else if(weight>=55 && weight<60)
                        {
                            result.setText("2262Kcal");
                            result2.setText("710g 급여");
                            String message = result.getText().toString();
                            String message2 = result2.getText().toString();
                            resultIntent.putExtra("weight_result1", message);
                            resultIntent.putExtra("weight_result2", message2);
                            startActivity(resultIntent);
                        }
                        else if(weight>=60)
                        {
                            result.setText("2415Kcal");
                            result2.setText("758g 급여");
                            String message = result.getText().toString();
                            String message2 = result2.getText().toString();
                            resultIntent.putExtra("weight_result1", message);
                            resultIntent.putExtra("weight_result2", message2);
                            startActivity(resultIntent);
                        }

                        break;
                    }
                    case R.id.radio_plus:{
                        if(weight>=1 && weight<2)
                        {
                            result.setText("154Kcal");
                            result2.setText("48g 급여");
                            String message = result.getText().toString();
                            String message2 = result2.getText().toString();
                            resultIntent.putExtra("weight_result1", message);
                            resultIntent.putExtra("weight_result2", message2);
                            startActivity(resultIntent);
                        }
                        else if(weight>=2 && weight<3)
                        {
                            result.setText("258Kcal");
                            result2.setText("81g 급여");
                            String message = result.getText().toString();
                            String message2 = result2.getText().toString();
                            resultIntent.putExtra("weight_result1", message);
                            resultIntent.putExtra("weight_result2", message2);
                            startActivity(resultIntent);
                        }
                        else if(weight>=3 && weight<4)
                        {
                            result.setText("350Kcal");
                            result2.setText("110g 급여");
                            String message = result.getText().toString();
                            String message2 = result2.getText().toString();
                            resultIntent.putExtra("weight_result1", message);
                            resultIntent.putExtra("weight_result2", message2);
                            startActivity(resultIntent);
                        }
                        else if(weight>=4 && weight<5)
                        {
                            result.setText("436Kcal");
                            result2.setText("136g 급여");
                            String message = result.getText().toString();
                            String message2 = result2.getText().toString();
                            resultIntent.putExtra("weight_result1", message);
                            resultIntent.putExtra("weight_result2", message2);
                            startActivity(resultIntent);
                        }
                        else if(weight>=5 && weight<6)
                        {
                            result.setText("514Kcal");
                            result2.setText("163g 급여");
                            String message = result.getText().toString();
                            String message2 = result2.getText().toString();
                            resultIntent.putExtra("weight_result1", message);
                            resultIntent.putExtra("weight_result2", message2);
                            startActivity(resultIntent);
                        }
                        else if(weight>=6 && weight<7)
                        {
                            result.setText("590Kcal");
                            result2.setText("186g 급여");
                            String message = result.getText().toString();
                            String message2 = result2.getText().toString();
                            resultIntent.putExtra("weight_result1", message);
                            resultIntent.putExtra("weight_result2", message2);
                            startActivity(resultIntent);
                        }
                        else if(weight>=7 && weight<8)
                        {
                            result.setText("663Kcal");
                            result2.setText("207g 급여");
                            String message = result.getText().toString();
                            String message2 = result2.getText().toString();
                            resultIntent.putExtra("weight_result1", message);
                            resultIntent.putExtra("weight_result2", message2);
                            startActivity(resultIntent);
                        }
                        else if(weight>=8 && weight<9)
                        {
                            result.setText("733Kcal");
                            result2.setText("229g 급여");
                            String message = result.getText().toString();
                            String message2 = result2.getText().toString();
                            resultIntent.putExtra("weight_result1", message);
                            resultIntent.putExtra("weight_result2", message2);
                            startActivity(resultIntent);
                        }
                        else if(weight>=9 && weight<10)
                        {
                            result.setText("800Kcal");
                            result2.setText("252g 급여");
                            String message = result.getText().toString();
                            String message2 = result2.getText().toString();
                            resultIntent.putExtra("weight_result1", message);
                            resultIntent.putExtra("weight_result2", message2);
                            startActivity(resultIntent);
                        }
                        else if(weight>=10 && weight<11)
                        {
                            result.setText("866Kcal");
                            result2.setText("272g 급여");
                            String message = result.getText().toString();
                            String message2 = result2.getText().toString();
                            resultIntent.putExtra("weight_result1", message);
                            resultIntent.putExtra("weight_result2", message2);
                            startActivity(resultIntent);
                        }
                        else if(weight>=11 && weight<12)
                        {
                            result.setText("929Kcal");
                            result2.setText("291g 급여");
                            String message = result.getText().toString();
                            String message2 = result2.getText().toString();
                            resultIntent.putExtra("weight_result1", message);
                            resultIntent.putExtra("weight_result2", message2);
                            startActivity(resultIntent);
                        }
                        else if(weight>=12 && weight<13)
                        {
                            result.setText("993Kcal");
                            result2.setText("312g 급여");
                            String message = result.getText().toString();
                            String message2 = result2.getText().toString();
                            resultIntent.putExtra("weight_result1", message);
                            resultIntent.putExtra("weight_result2", message2);
                            startActivity(resultIntent);
                        }
                        else if(weight>=13 && weight<14)
                        {
                            result.setText("1055Kcal");
                            result2.setText("332g 급여");
                            String message = result.getText().toString();
                            String message2 = result2.getText().toString();
                            resultIntent.putExtra("weight_result1", message);
                            resultIntent.putExtra("weight_result2", message2);
                            startActivity(resultIntent);
                        }
                        else if(weight>=14 && weight<15)
                        {
                            result.setText("1115Kcal");
                            result2.setText("351g 급여");
                            String message = result.getText().toString();
                            String message2 = result2.getText().toString();
                            resultIntent.putExtra("weight_result1", message);
                            resultIntent.putExtra("weight_result2", message2);
                            startActivity(resultIntent);
                        }
                        else if(weight>=15 && weight<20)
                        {
                            result.setText("1174Kcal");
                            result2.setText("368g 급여");
                            String message = result.getText().toString();
                            String message2 = result2.getText().toString();
                            resultIntent.putExtra("weight_result1", message);
                            resultIntent.putExtra("weight_result2", message2);
                            startActivity(resultIntent);
                        }
                        else if(weight>=20 && weight<25)
                        {
                            result.setText("1456Kcal");
                            result2.setText("458g 급여");
                            String message = result.getText().toString();
                            String message2 = result2.getText().toString();
                            resultIntent.putExtra("weight_result1", message);
                            resultIntent.putExtra("weight_result2", message2);
                            startActivity(resultIntent);
                        }
                        else if(weight>=25 && weight<30)
                        {
                            result.setText("1721Kcal");
                            result2.setText("540g 급여");
                            String message = result.getText().toString();
                            String message2 = result2.getText().toString();
                            resultIntent.putExtra("weight_result1", message);
                            resultIntent.putExtra("weight_result2", message2);
                            startActivity(resultIntent);
                        }
                        else if(weight>=30 && weight<35)
                        {
                            result.setText("1975Kcal");
                            result2.setText("620g 급여");
                            String message = result.getText().toString();
                            String message2 = result2.getText().toString();
                            resultIntent.putExtra("weight_result1", message);
                            resultIntent.putExtra("weight_result2", message2);
                            startActivity(resultIntent);
                        }
                        else if(weight>=35 && weight<40)
                        {
                            result.setText("2217Kcal");
                            result2.setText("696g 급여");
                            String message = result.getText().toString();
                            String message2 = result2.getText().toString();
                            resultIntent.putExtra("weight_result1", message);
                            resultIntent.putExtra("weight_result2", message2);
                            startActivity(resultIntent);
                        }
                        else if(weight>=40 && weight<45)
                        {
                            result.setText("2449Kcal");
                            result2.setText("768g 급여");
                            String message = result.getText().toString();
                            String message2 = result2.getText().toString();
                            resultIntent.putExtra("weight_result1", message);
                            resultIntent.putExtra("weight_result2", message2);
                            startActivity(resultIntent);
                        }
                        else if(weight>=45 && weight<50)
                        {
                            result.setText("2676Kcal");
                            result2.setText("840g 급여");
                            String message = result.getText().toString();
                            String message2 = result2.getText().toString();
                            resultIntent.putExtra("weight_result1", message);
                            resultIntent.putExtra("weight_result2", message2);
                            startActivity(resultIntent);
                        }
                        else if(weight>=50 && weight<55)
                        {
                            result.setText("2896Kcal");
                            result2.setText("909g 급여");
                            String message = result.getText().toString();
                            String message2 = result2.getText().toString();
                            resultIntent.putExtra("weight_result1", message);
                            resultIntent.putExtra("weight_result2", message2);
                            startActivity(resultIntent);
                        }
                        else if(weight>=55 && weight<60)
                        {
                            result.setText("3110Kcal");
                            result2.setText("976g 급여");
                            String message = result.getText().toString();
                            String message2 = result2.getText().toString();
                            resultIntent.putExtra("weight_result1", message);
                            resultIntent.putExtra("weight_result2", message2);
                            startActivity(resultIntent);
                        }
                        else if(weight>=60)
                        {
                            result.setText("3321Kcal");
                            result2.setText("1042g 급여");
                            String message = result.getText().toString();
                            String message2 = result2.getText().toString();
                            resultIntent.putExtra("weight_result1", message);
                            resultIntent.putExtra("weight_result2", message2);
                            startActivity(resultIntent);
                        }
                            break;
                    }
                }
            }
        });
    }
}
