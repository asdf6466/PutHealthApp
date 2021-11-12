package kr.co.korearental.petproto;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.DialogFragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class AlarmActivity extends AppCompatActivity {

    private AlarmManager alarmManager;
    private GregorianCalendar mCalender;

    private NotificationManager notificationManager;
    NotificationCompat.Builder builder;

    private Button btn_alarm_on;
    int hour;
    int min;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        mCalender = new GregorianCalendar();
        Log.v("HelloAlarmActivity", mCalender.getTime().toString());
        setContentView(R.layout.activity_alarm);

        final Intent mainIntent = new Intent(AlarmActivity.this, MainActivity.class);

        final RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup1);
        radioGroup.clearCheck();

        final CheckBox ck1_1 = (CheckBox)findViewById(R.id.checkBox_alarm_walk);
        final CheckBox ck1_2 = (CheckBox)findViewById(R.id.checkBox_alarm_walk2);
        final CheckBox ck1_3 = (CheckBox)findViewById(R.id.checkBox_alarm_walk3);
        final CheckBox ck1_4 = (CheckBox)findViewById(R.id.checkBox_alarm_walk4);
        //////
        final CheckBox ck2_1 = (CheckBox)findViewById(R.id.checkBox_alarm_eat);
        final CheckBox ck2_2 = (CheckBox)findViewById(R.id.checkBox_alarm_eat2);
        final CheckBox ck2_3 = (CheckBox)findViewById(R.id.checkBox_alarm_eat3);
        ///////
        final CheckBox ck3_1 = (CheckBox)findViewById(R.id.checkBox_alarm_brush);
        final CheckBox ck3_2 = (CheckBox)findViewById(R.id.checkBox_alarm_brush2);
        final CheckBox ck3_3 = (CheckBox)findViewById(R.id.checkBox_alarm_brush3);
        ///////
        final CheckBox ck4_1 = (CheckBox)findViewById(R.id.checkBox_alarm_fur);
        final CheckBox ck4_2 = (CheckBox)findViewById(R.id.checkBox_alarm_fur2);
        final CheckBox ck4_3 = (CheckBox)findViewById(R.id.checkBox_alarm_fur3);
        final CheckBox ck4_4 = (CheckBox)findViewById(R.id.checkBox_alarm_fur4);
        ///////

        final EditText et_time = (EditText) findViewById(R.id.CusTime);
        et_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int cushour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int cusminute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(AlarmActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        String state = "AM";
                        hour = selectedHour;
                        min = selectedMinute;
                        // 선택한 시간이 12를 넘을경우 "PM"으로 변경 및 -12시간하여 출력 (ex : PM 6시 30분)
                        if (selectedHour > 12) {
                            selectedHour -= 12;
                            state = "PM";
                        }
                        // EditText에 출력할 형식 지정
                        et_time.setText(state + " " + selectedHour + "시 " + selectedMinute + "분");
                    }
                }, cushour, cusminute, false); // true의 경우 24시간 형식의 TimePicker 출현
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });


        btn_alarm_on = (Button) findViewById(R.id.button_alarm_on);
        btn_alarm_on.setOnClickListener(new View.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                int rb = ((RadioGroup) radioGroup.findViewById(R.id.radioGroup1)).getCheckedRadioButtonId();

                switch(rb){
                    case R.id.radio_alarm_on:{
                        if(ck1_1.isChecked())
                        {
                            setAlarm1_1();
                        };
                        if(ck1_2.isChecked())
                        {
                            setAlarm1_2();
                        };
                        if(ck1_3.isChecked())
                        {
                            setAlarm1_3();
                        };
                        if(ck1_4.isChecked())
                        {
                            setAlarm1_4();
                        };
                        /////////
                        if(ck2_1.isChecked())
                        {
                            setAlarm2_1();
                        };
                        if(ck2_2.isChecked())
                        {
                            setAlarm2_2();
                        };
                        if(ck2_3.isChecked())
                        {
                            setAlarm2_3();
                        };
                        ////////
                        if(ck3_1.isChecked())
                        {
                            setAlarm3_1();
                        };
                        if(ck3_2.isChecked())
                        {
                            setAlarm3_2();
                        };
                        if(ck3_3.isChecked())
                        {
                            setAlarm3_3();
                        };
                        ////////
                        if(ck4_1.isChecked())
                        {
                            setAlarm4_1();
                        };
                        if(ck4_2.isChecked())
                        {
                            setAlarm4_2();
                        };
                        if(ck4_3.isChecked())
                        {
                            setAlarm4_3();
                        };
                        if(ck4_4.isChecked())
                        {
                            setAlarm4_4();
                        };
                        setAlarmcm();
                        startActivity(mainIntent);
                        break;
                    }
                    case R.id.radio_alarm_off:{
                        String id = "channel1";
                        notificationManager.deleteNotificationChannel(id);
                        String id2 = "channel2";
                        notificationManager.deleteNotificationChannel(id2);
                        ///////////
                        startActivity(mainIntent);
                        break;
                    }
                }
            }
        });

    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    private <notificationManager> void setAlarmcm() {
        String log="1";

        Log.v("커스텀", String.valueOf(hour));
        Log.v("커스텀",String.valueOf(min));

        if(hour!=0&&min!=0)
        {
            //AlarmReceiver에 값 전달
            Intent receiverIntent = new Intent(this, AlarmRecevier2.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, receiverIntent, 0);

            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            calendar.set(Calendar.HOUR_OF_DAY,hour);
            calendar.set(Calendar.MINUTE,min);
            calendar.set(Calendar.SECOND,00);

            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);
        }
        else
        {
            Log.v("커스텀1",log);
        }
    }

    //알림 기능
    private void setAlarm1_1() {
        String log="1";
        Log.v("1_1 실행",log);

        //AlarmReceiver에 값 전달
        Intent receiverIntent1_1 = new Intent(this, AlarmRecevier.class);
        PendingIntent pendingIntent1_1 = PendingIntent.getBroadcast(this, 11, receiverIntent1_1, 0);

        Calendar calendar11 = Calendar.getInstance();
        calendar11.setTimeInMillis(System.currentTimeMillis());
        calendar11.set(Calendar.HOUR_OF_DAY,8);
        calendar11.set(Calendar.MINUTE,00);
        calendar11.set(Calendar.SECOND,00);

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar11.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent1_1);
    }
    private void setAlarm1_2() {

        String log="1";
        Log.v("1_2 실행",log);

        //AlarmReceiver에 값 전달
        Intent receiverIntent1_2 = new Intent(this, AlarmRecevier.class);
        PendingIntent pendingIntent1_2 = PendingIntent.getBroadcast(this, 12, receiverIntent1_2, 0);

        Calendar calendar12 = Calendar.getInstance();
        calendar12.setTimeInMillis(System.currentTimeMillis());
        calendar12.set(Calendar.HOUR_OF_DAY,13);
        calendar12.set(Calendar.MINUTE,00);
        calendar12.set(Calendar.SECOND,00);

        alarmManager.setRepeating(AlarmManager.RTC, calendar12.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent1_2);
    }
    private void setAlarm1_3() {

        String log="1";
        Log.v("1_3 실행",log);

        //AlarmReceiver에 값 전달
        Intent receiverIntent1_3 = new Intent(this, AlarmRecevier.class);
        PendingIntent pendingIntent1_3 = PendingIntent.getBroadcast(this, 13, receiverIntent1_3, 0);

        Calendar calendar13 = Calendar.getInstance();
        calendar13.setTimeInMillis(System.currentTimeMillis());
        calendar13.set(Calendar.HOUR_OF_DAY,19);
        calendar13.set(Calendar.MINUTE,00);
        calendar13.set(Calendar.SECOND,00);

        alarmManager.setRepeating(AlarmManager.RTC, calendar13.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent1_3);
    }
    private void setAlarm1_4() {

        String log="1";
        Log.v("1_4 실행",log);

        //AlarmReceiver에 값 전달
        Intent receiverIntent1_4 = new Intent(this, AlarmRecevier.class);
        PendingIntent pendingIntent1_4 = PendingIntent.getBroadcast(this, 14, receiverIntent1_4, 0);

        Calendar calendar14 = Calendar.getInstance();
        calendar14.setTimeInMillis(System.currentTimeMillis());
        calendar14.set(Calendar.HOUR_OF_DAY,23);
        calendar14.set(Calendar.MINUTE,00);
        calendar14.set(Calendar.SECOND,00);

        alarmManager.setRepeating(AlarmManager.RTC, calendar14.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent1_4);
    }
    /////////////////////
    private void setAlarm2_1() {

        String log="1";
        Log.v("2_1 실행",log);

        //AlarmReceiver에 값 전달
        Intent receiverIntent2_1 = new Intent(this, AlarmRecevier.class);
        PendingIntent pendingIntent2_1 = PendingIntent.getBroadcast(this, 21, receiverIntent2_1, 0);

        Calendar calendar21 = Calendar.getInstance();
        calendar21.setTimeInMillis(System.currentTimeMillis());
        calendar21.set(Calendar.HOUR_OF_DAY,7);
        calendar21.set(Calendar.MINUTE,30);
        calendar21.set(Calendar.SECOND,00);

        alarmManager.setRepeating(AlarmManager.RTC, calendar21.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent2_1);

    }
    private void setAlarm2_2() {

        String log="1";
        Log.v("2_2 실행",log);

        //AlarmReceiver에 값 전달
        Intent receiverIntent2_2 = new Intent(this, AlarmRecevier.class);
        PendingIntent pendingIntent2_2 = PendingIntent.getBroadcast(this, 22, receiverIntent2_2, 0);

        Calendar calendar22 = Calendar.getInstance();
        calendar22.setTimeInMillis(System.currentTimeMillis());
        calendar22.set(Calendar.HOUR_OF_DAY,12);
        calendar22.set(Calendar.MINUTE,30);
        calendar22.set(Calendar.SECOND,00);

        alarmManager.setRepeating(AlarmManager.RTC, calendar22.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent2_2);
    }
    private void setAlarm2_3() {

        String log="1";
        Log.v("2_3 실행",log);

        //AlarmReceiver에 값 전달
        Intent receiverIntent2_3 = new Intent(this, AlarmRecevier.class);
        PendingIntent pendingIntent2_3 = PendingIntent.getBroadcast(this, 23, receiverIntent2_3, 0);

        Calendar calendar23 = Calendar.getInstance();
        calendar23.setTimeInMillis(System.currentTimeMillis());
        calendar23.set(Calendar.HOUR_OF_DAY,18);
        calendar23.set(Calendar.MINUTE,30);
        calendar23.set(Calendar.SECOND,00);

        alarmManager.setRepeating(AlarmManager.RTC, calendar23.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent2_3);
    }
    ///////////////
    private void setAlarm3_1() {

        String log="1";
        Log.v("3_1 실행",log);

        //AlarmReceiver에 값 전달
        Intent receiverIntent3_1 = new Intent(this, AlarmRecevier.class);
        PendingIntent pendingIntent3_1 = PendingIntent.getBroadcast(this, 31, receiverIntent3_1, 0);

        Calendar calendar31 = Calendar.getInstance();
        calendar31.setTimeInMillis(System.currentTimeMillis());
        calendar31.set(Calendar.HOUR_OF_DAY,8);
        calendar31.set(Calendar.MINUTE,30);
        calendar31.set(Calendar.SECOND,00);

        alarmManager.setRepeating(AlarmManager.RTC, calendar31.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent3_1);
    }
    private void setAlarm3_2() {

        String log="1";
        Log.v("3_2 실행",log);

        //AlarmReceiver에 값 전달
        Intent receiverIntent3_2 = new Intent(this, AlarmRecevier.class);
        PendingIntent pendingIntent3_2 = PendingIntent.getBroadcast(this, 32, receiverIntent3_2, 0);

        Calendar calendar32 = Calendar.getInstance();
        calendar32.setTimeInMillis(System.currentTimeMillis());
        calendar32.set(Calendar.HOUR_OF_DAY,13);
        calendar32.set(Calendar.MINUTE,30);
        calendar32.set(Calendar.SECOND,00);

        alarmManager.setRepeating(AlarmManager.RTC, calendar32.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent3_2);
    }
    private void setAlarm3_3() {

        String log="1";
        Log.v("3_3 실행",log);

        //AlarmReceiver에 값 전달
        Intent receiverIntent3_3 = new Intent(this, AlarmRecevier.class);
        PendingIntent pendingIntent3_3 = PendingIntent.getBroadcast(this, 33, receiverIntent3_3, 0);

        Calendar calendar33 = Calendar.getInstance();
        calendar33.setTimeInMillis(System.currentTimeMillis());
        calendar33.set(Calendar.HOUR_OF_DAY,19);
        calendar33.set(Calendar.MINUTE,30);
        calendar33.set(Calendar.SECOND,00);

        alarmManager.setRepeating(AlarmManager.RTC, calendar33.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent3_3);
    }
    //////////////////
    private void setAlarm4_1() {

        String log="1";
        Log.v("4_1 실행",log);

        //AlarmReceiver에 값 전달
        Intent receiverIntent4_1 = new Intent(this, AlarmRecevier.class);
        PendingIntent pendingIntent4_1 = PendingIntent.getBroadcast(this, 41, receiverIntent4_1, 0);

        Calendar calendar41 = Calendar.getInstance();
        calendar41.setTimeInMillis(System.currentTimeMillis());
        calendar41.set(Calendar.HOUR_OF_DAY,9);
        calendar41.set(Calendar.MINUTE,00);
        calendar41.set(Calendar.SECOND,00);

        alarmManager.setRepeating(AlarmManager.RTC, calendar41.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent4_1);
    }
    private void setAlarm4_2() {

        String log="1";
        Log.v("4_2 실행",log);

        //AlarmReceiver에 값 전달
        Intent receiverIntent4_2 = new Intent(this, AlarmRecevier.class);
        PendingIntent pendingIntent4_2 = PendingIntent.getBroadcast(this, 42, receiverIntent4_2, 0);

        Calendar calendar42 = Calendar.getInstance();
        calendar42.setTimeInMillis(System.currentTimeMillis());
        calendar42.set(Calendar.HOUR_OF_DAY,14);
        calendar42.set(Calendar.MINUTE,00);
        calendar42.set(Calendar.SECOND,00);

        alarmManager.setRepeating(AlarmManager.RTC, calendar42.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent4_2);
    }
    private void setAlarm4_3() {

        String log="1";
        Log.v("4_3 실행",log);

        //AlarmReceiver에 값 전달
        Intent receiverIntent4_3 = new Intent(this, AlarmRecevier.class);
        PendingIntent pendingIntent4_3 = PendingIntent.getBroadcast(this, 43, receiverIntent4_3, 0);

        Calendar calendar43 = Calendar.getInstance();
        calendar43.setTimeInMillis(System.currentTimeMillis());
        calendar43.set(Calendar.HOUR_OF_DAY,20);
        calendar43.set(Calendar.MINUTE,00);
        calendar43.set(Calendar.SECOND,00);

        alarmManager.setRepeating(AlarmManager.RTC, calendar43.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent4_3);
    }
    private void setAlarm4_4() {

        String log="1";
        Log.v("4_4 실행",log);

        //AlarmReceiver에 값 전달
        Intent receiverIntent4_4 = new Intent(this, AlarmRecevier.class);
        PendingIntent pendingIntent4_4 = PendingIntent.getBroadcast(this, 44, receiverIntent4_4, 0);

        Calendar calendar44 = Calendar.getInstance();
        calendar44.setTimeInMillis(System.currentTimeMillis());
        calendar44.set(Calendar.HOUR_OF_DAY,24);
        calendar44.set(Calendar.MINUTE,00);
        calendar44.set(Calendar.SECOND,00);

        alarmManager.setRepeating(AlarmManager.RTC, calendar44.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent4_4);
    }

/*
    //알림 기능
    private void setAlarm() {

        //AlarmReceiver에 값 전달
        Intent receiverIntent = new Intent(AlarmActivity.this, AlarmRecevier.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(AlarmActivity.this, 0, receiverIntent, 0);

        //String from = "21:47:30"; //임의로 날짜와 시간을 지정

        //날짜 포맷을 바꿔주는 소스코드
        //SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
//        Date datetime = null;
//        try {
//            datetime = dateFormat.parse(from);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY,18);
        calendar.set(Calendar.MINUTE,10);
        calendar.set(Calendar.SECOND,00);

        alarmManager.setRepeating(AlarmManager.RTC, calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);
    }
    */
}
