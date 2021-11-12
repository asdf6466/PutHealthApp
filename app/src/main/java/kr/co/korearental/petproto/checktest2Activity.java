package kr.co.korearental.petproto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.net.Socket;

public class checktest2Activity extends AppCompatActivity {

    Button connect_btn;
    Button send_btn;
    private Socket socket;
    private final int GET_GALLERY_IMAGE = 200;
    private String image_path;
    private ImageView imageView;
    TextView str1;
    TextView str2;
    TextView str3;
    TextView score1;
    TextView score2;
    TextView score3;
    ProgressDialog customProgressDialog2;
    private final int MY_PERMISSIONS_REQUEST_RAED=1002;
    private final int MY_PERMISSIONS_REQUEST_WRITE=1003;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checktest2);

        int permssionCheck2 = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        int permssionCheck3 = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        connect_btn = (Button)findViewById(R.id.connect_btn);
        send_btn = (Button)findViewById(R.id.send);
        imageView = (ImageView)findViewById(R.id.imageView);
        str1 = (TextView)findViewById(R.id.str1);
        str2 = (TextView)findViewById(R.id.str2);
        str3 = (TextView)findViewById(R.id.str3);
        score1 = (TextView)findViewById(R.id.score1);
        score2 = (TextView)findViewById(R.id.score2);
        score3 = (TextView)findViewById(R.id.score3);

        if (permssionCheck2!= PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
                //Toast.makeText(this, "피부병 사용을 위해 저장소 권한이 필요합니다.", Toast.LENGTH_LONG).show();
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_RAED);
                //Toast.makeText(this, "피부병 사용을 위해 저장소 권한이 필요합니다.", Toast.LENGTH_LONG).show();

            }
        }
        if (permssionCheck3!= PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                //Toast.makeText(this,"피부병 사용을 위해 저장소 권한이 필요합니다.",Toast.LENGTH_LONG).show();
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_WRITE);
                //Toast.makeText(this,"피부병 사용을 위해 저장소 권한이 필요합니다.",Toast.LENGTH_LONG).show();

            }
        }

        customProgressDialog2 = new ProgressDialog(this);
        customProgressDialog2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        customProgressDialog2.setCancelable(false);

        connect_btn.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent. setDataAndType(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent, GET_GALLERY_IMAGE);
            }
        });

        send_btn.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                customProgressDialog2.show();
                final TCPClient tcp = new TCPClient(image_path);
                new Thread(tcp).start();



                Handler mHandler = new Handler();
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //while (true){
                        //if(tcp.singal==1){
                        //customProgressDialog2.show();
                        String tmp = tcp.data.substring(2,tcp.data.length()-3);
                        String tmptmp = returnKorean(tmp);
                        str1.setText(tmptmp);
                        String tmp2 = tcp.data2.substring(2,tcp.data2.length()-3);
                        String tmptmp2 = returnKorean(tmp2);
                        str2.setText(tmptmp2);
                        String tmp3 = tcp.data3.substring(2,tcp.data3.length()-3);
                        String tmptmp3 = returnKorean(tmp3);
                        str3.setText(tmptmp3);
                        score1.setText(Float.parseFloat(tcp.data4)*100+"%");
                        score2.setText(Float.parseFloat(tcp.data5)*100+"%");
                        score3.setText(Float.parseFloat(tcp.data6)*100+"%");
                        customProgressDialog2.dismiss();

                        //tcp.singal=0;
                        //break;
                        //}


                        //}

                        /*String tmp = tcp.data.substring(2,tcp.data.length()-3);
                        String tmptmp = returnKorean(tmp);
                        str1.setText(tmptmp);
                        String tmp2 = tcp.data2.substring(2,tcp.data2.length()-3);
                        String tmptmp2 = returnKorean(tmp2);
                        str2.setText(tmptmp2);
                        String tmp3 = tcp.data3.substring(2,tcp.data3.length()-3);
                        String tmptmp3 = returnKorean(tmp3);
                        str3.setText(tmptmp3);
                        score1.setText(Float.parseFloat(tcp.data4)*100+"%");
                        score2.setText(Float.parseFloat(tcp.data5)*100+"%");
                        score3.setText(Float.parseFloat(tcp.data6)*100+"%");*/

                    }
                },10000);


            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GET_GALLERY_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri selectedImageUri = data.getData();
            imageView.setImageURI(selectedImageUri);
            image_path = getRealPathFromURI(selectedImageUri);

        }

    }

    private String getRealPathFromURI(Uri contentUri) {

        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        cursor.moveToFirst();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

        return cursor.getString(column_index);
    }

    private String returnKorean(String str){
        String result_str;
        if(str.contains("allergic")){
            result_str = "알레르기성 피부염";
        }
        else if(str.contains("dandruff")){
            result_str = "비듬";
        }
        else if(str.contains("folli")){
            result_str = "모낭염";
        }
        else return str;

        return result_str;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {

            case MY_PERMISSIONS_REQUEST_RAED: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    //Toast.makeText(this,"승인이 허가되어 있습니다.",Toast.LENGTH_LONG).show();

                } else {
                    //Toast.makeText(this,"아직 승인받지 않았습니다.",Toast.LENGTH_LONG).show();
                }
                return;
            }

            case MY_PERMISSIONS_REQUEST_WRITE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    //Toast.makeText(this,"승인이 허가되어 있습니다.",Toast.LENGTH_LONG).show();

                } else {
                    //Toast.makeText(this,"아직 승인받지 않았습니다.",Toast.LENGTH_LONG).show();
                }
                return;
            }

        }
    }

}
