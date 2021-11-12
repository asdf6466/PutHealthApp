package kr.co.korearental.petproto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {

    private AlarmManager alarmManager;
    private GregorianCalendar mCalender;

    private NotificationManager notificationManager;
    NotificationCompat.Builder builder;

    private ImageButton button_move_join;
    private ImageButton button_move_eat;
    private ImageButton button_move_map;
    private ImageButton button_move_alarm;
    private ImageButton button_move_check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //산책 기능 이동 버튼
        button_move_map = (ImageButton) findViewById(R.id.button_main_walk);
        button_move_map.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent2 = new Intent(MainActivity.this, MapchoiceActivity.class);
                startActivity(intent2);
            }
        });
        //알람 기능 이동 버튼
        button_move_alarm = (ImageButton) findViewById(R.id.button_main_notice);
        button_move_alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AlarmActivity.class);
                startActivity(intent);
            }
        });
        //사료 기능 이동 버튼
        button_move_eat = (ImageButton) findViewById(R.id.button_main_eat);
        button_move_eat.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity.this, EatActivity.class);
                startActivity(intent1);
            }
        });
        //피부병 체크 기능 이동 버튼
        button_move_check = (ImageButton) findViewById(R.id.button_main_check);
        button_move_check.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity.this, checktest2Activity.class);
                startActivity(intent1);
            }
        });

        /*//내정보 이동 버튼
        button_move_join = (ImageButton) findViewById(R.id.button_main_profile);
        button_move_join.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                //startActivity(intent);
            }
        });*/
    }
}