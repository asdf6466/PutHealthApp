package kr.co.korearental.petproto;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.SimpleFormatter;

public class AlarmRecevier extends BroadcastReceiver {
    public AlarmRecevier(){ }

    NotificationManager manager;
    NotificationCompat.Builder builder;

    //오레오 이상은 반드시 채널을 설정해줘야 Notification이 작동함
    private static String CHANNEL_ID = "channel1";
    private static String CHANNEL_NAME = "Channel1";

    @Override
    public void onReceive(Context context, Intent intent) {
        AlarmManager am = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);

        builder = null;
        manager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            manager.createNotificationChannel(
                    new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT)
            );
            builder = new NotificationCompat.Builder(context, CHANNEL_ID);
        } else {
            builder = new NotificationCompat.Builder(context);
        }

        Calendar calendar11 = Calendar.getInstance();
        //calendar11.getTimeInMillis();
        SimpleDateFormat SF = new SimpleDateFormat("Hmm");
        String TimeSF = SF.format(calendar11.getTime());
        int IntTimeSF = Integer.parseInt(TimeSF);

        Log.v("Y", String.valueOf(TimeSF));

        //알림창 클릭 시 activity 화면 부름
        Intent intent2 = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,101,intent2, PendingIntent.FLAG_UPDATE_CURRENT);


        if(800<=IntTimeSF&&IntTimeSF<=803)
        {
            builder.setContentTitle("08:00의 산책시간입니다!");
            builder.setContentText("어서 애완견의 목줄과 입마개, 비닐봉투를 챙기시고 밖으로 나가세요!");
        }
        else if(1300<=IntTimeSF&&IntTimeSF<=1303)
        {
            builder.setContentTitle("13:00의 산책시간입니다!");
            builder.setContentText("어서 애완견의 목줄과 입마개, 비닐봉투를 챙기시고 밖으로 나가세요!");
        }
        else if(1900<=IntTimeSF&&IntTimeSF<=1903)
        {
            builder.setContentTitle("19:00의 산책시간입니다!");
            builder.setContentText("어서 애완견의 목줄과 입마개, 비닐봉투를 챙기시고 밖으로 나가세요!");
        }
        else if(2300<=IntTimeSF&&IntTimeSF<=2303)
        {
            builder.setContentTitle("23:00의 산책시간입니다!");
            builder.setContentText("어서 애완견의 목줄과 입마개, 비닐봉투를 챙기시고 밖으로 나가세요!");
        }
        else if(730<=IntTimeSF&&IntTimeSF<=733)
        {
            builder.setContentTitle("07:30의 식사시간입니다!");
            builder.setContentText("애완견에게 정해진 사료와 충분한 식수를 주세요!");
        }
        else if(1230<=IntTimeSF&&IntTimeSF<=1233)
        {
            builder.setContentTitle("12:30의 식사시간입니다!");
            builder.setContentText("애완견에게 정해진 사료와 충분한 식수를 주세요!");
        }
        else if(1830<=IntTimeSF&&IntTimeSF<=1833)
        {
            builder.setContentTitle("18:30의 식사시간입니다!");
            builder.setContentText("애완견에게 정해진 사료와 충분한 식수를 주세요!");
        }
        else if(830<=IntTimeSF&&IntTimeSF<=833)
        {
            builder.setContentTitle("08:30의 양치 시간입니다!");
            builder.setContentText("애완견의 치아 건강을 위해서 꼭 양치해주세요!");
        }
        else if(1330<=IntTimeSF&&IntTimeSF<=1333)
        {
            builder.setContentTitle("13:30의 양치 시간입니다!");
            builder.setContentText("애완견의 치아 건강을 위해서 꼭 양치해주세요!");
        }
        else if(1930<=IntTimeSF&&IntTimeSF<=1933)
        {
            builder.setContentTitle("19:30의 양치 시간입니다!");
            builder.setContentText("애완견의 치아 건강을 위해서 꼭 양치해주세요!");
        }
        else if(900<=IntTimeSF&&IntTimeSF<=903)
        {
            builder.setContentTitle("09:00의 빗질 시간입니다!");
            builder.setContentText("애완견의 피부병 예방을 위해서 빗질을 해주세요!");
        }
        else if(1400<=IntTimeSF&&IntTimeSF<=1403)
        {
            builder.setContentTitle("14:00의 빗질 시간입니다!");
            builder.setContentText("애완견의 피부병 예방을 위해서 빗질을 해주세요!");
        }
        else if(2000<=IntTimeSF&&IntTimeSF<=2003)
        {
            builder.setContentTitle("20:00의 빗질 시간입니다!");
            builder.setContentText("애완견의 피부병 예방을 위해서 빗질을 해주세요!");
        }
        else if(0<=IntTimeSF&&IntTimeSF<=3)
        {
            builder.setContentTitle("24:00의 빗질 시간입니다!");
            builder.setContentText("애완견의 피부병 예방을 위해서 빗질을 해주세요!");
        }
        else
            {
            //알림창 제목
            builder.setContentTitle("PETHEALTH 어플입니다.");
            builder.setContentText("설정하신 시간의 알림입니다!");
        }

        //알림창 아이콘
        builder.setSmallIcon(R.drawable.icon_alarm2);
        //알림창 터치시 자동 삭제
        builder.setAutoCancel(true);

        builder.setContentIntent(pendingIntent);

        Notification notification = builder.build();
        manager.notify(1,notification);

    }
}
