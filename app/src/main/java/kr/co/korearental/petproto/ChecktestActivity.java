package kr.co.korearental.petproto;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class ChecktestActivity extends AppCompatActivity {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        connect_btn = (Button)findViewById(R.id.connect_btn);
        send_btn = (Button)findViewById(R.id.send);
        imageView = (ImageView)findViewById(R.id.imageView);
        str1 = (TextView)findViewById(R.id.str1);
        str2 = (TextView)findViewById(R.id.str2);
        str3 = (TextView)findViewById(R.id.str3);
        score1 = (TextView)findViewById(R.id.score1);
        score2 = (TextView)findViewById(R.id.score2);
        score3 = (TextView)findViewById(R.id.score3);

        connect_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent. setDataAndType(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent, GET_GALLERY_IMAGE);
            }
        });

        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final TCPClient tcp = new TCPClient(image_path);
                new Thread(tcp).start();

                Handler mHandler = new Handler();
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        String tmp = tcp.data.substring(2,tcp.data.length()-3);
                        str1.setText(tmp);
                        String tmp2 = tcp.data2.substring(2,tcp.data2.length()-3);
                        str2.setText(tmp2);
                        String tmp3 = tcp.data3.substring(2,tcp.data3.length()-3);
                        str3.setText(tmp3);
                        score1.setText(Float.parseFloat(tcp.data4)*100+"%");
                        score2.setText(Float.parseFloat(tcp.data5)*100+"%");
                        score3.setText(Float.parseFloat(tcp.data6)*100+"%");

                    }
                },5000);


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


}