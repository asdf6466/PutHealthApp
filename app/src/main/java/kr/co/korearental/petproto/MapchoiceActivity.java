package kr.co.korearental.petproto;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MapchoiceActivity extends AppCompatActivity {

    private final int MY_PERMISSIONS_REQUEST_Location=1001;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapchoice);

        final TextView testText = (TextView)findViewById(R.id.testtext);

        int permssionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);


        if (permssionCheck!= PackageManager.PERMISSION_GRANTED) {

            //Toast.makeText(this, "권한 승인이 필요합니다", Toast.LENGTH_LONG).show();

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                //Toast.makeText(this, "산책기능 사용을 위해 위치 권한이 필요합니다.", Toast.LENGTH_LONG).show();
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_Location);
                //Toast.makeText(this, "산책기능 사용을 위해 위치 권한이 필요합니다.", Toast.LENGTH_LONG).show();

            }
        }


        Button searchbtn = (Button) findViewById(R.id.search);
        final Button place_searchbtn = (Button) findViewById(R.id.place_search);
        final ArrayList<String> list = new ArrayList<>();
        final ArrayList<String> list2 = new ArrayList<>();

        final String[] route_select = {"애견용품점", "동물병원", "애견카페", "애견호텔", "편의점", "약국", "은행", "공원", "역"};
        final ArrayAdapter adapter = new ArrayAdapter(this, R.layout.simple_list_item_multiple_choice, route_select);
        final ListView listview = (ListView) findViewById(R.id.choose_route_List);
        listview.setAdapter(adapter);
        //listview.setAdapter(buttonListAdapter);


        place_searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show(testText,list2);
            }
        });

        if(listview != null)
        {

            searchbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SparseBooleanArray checkedItems = listview.getCheckedItemPositions();
                    int count = adapter.getCount();

                    for(int i=count-1;i>=0;i--) {
                        if (checkedItems.get(i)) {
                            list.add(route_select[i]);
                        }
                    }
               /*String numStr2 = String.valueOf(count);
               Toast.makeText(MainActivity.this,numStr2,Toast.LENGTH_SHORT).show();*/
                    if(list.size()+list2.size()!=0){
                        if(list.size()+list2.size()>=5){
                            Toast.makeText(MapchoiceActivity.this,"선택한 경유지가 5개 이상입니다",Toast.LENGTH_SHORT).show();
                            list.clear();
                            list2.clear();
                        }
                        else{
                            /*for(int i=count-1;i>=0;i--) {
                                if (checkedItems.get(i)) {
                                    list.add(route_select[i]);
                                }

                            }*/
                            //String numStr2 = String.valueOf(checkedItems.size());
                            //Toast.makeText(MapchoiceActivity.this,numStr2,Toast.LENGTH_SHORT).show();
                            //Toast.makeText(MapchoiceActivity.this,list.toString(),Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), MapActivity.class);
                            intent.putExtra("route", list);
                            intent.putExtra("route2", list2);
                            //intent.putExtra("count", checkedItems.size());
                            intent.putExtra("count", list.size());
                            intent.putExtra("count2", list2.size());


                            startActivity(intent);
                            overridePendingTransition(0, 0);
                            list.clear();
                            list2.clear();
                            finish();

                        }
                    } else {
                        Toast.makeText(MapchoiceActivity.this,"하나 이상의 경로를 선택해주세요",Toast.LENGTH_SHORT).show();
                    }
                    listview.clearChoices();
                    adapter.notifyDataSetChanged();

                }
            });
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_Location: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    //Toast.makeText(this,"승인이 허가되어 있습니다.",Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(this,"아직 승인받지 않았습니다.",Toast.LENGTH_LONG).show();
                }
                return;
            }

        }
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        super.onBackPressed();

    }

    public void show(final TextView text,final ArrayList<String> list){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final EditText name = new EditText(this);
        builder.setTitle("방문하고 싶은 장소를 입력해주세요");
        builder.setView(name);


        builder.setPositiveButton("확인",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //text.setText(name.getText().toString());
                        if(list.isEmpty()){
                            text.setText(name.getText().toString());
                            list.add(name.getText().toString());
                            Toast.makeText(getApplicationContext(),name.getText().toString(),Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"장소를 이미 추가하셨습니다.",Toast.LENGTH_LONG).show();
                        }

                    }
                });

        builder.setNegativeButton("취소",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(getApplicationContext(),"Text3",Toast.LENGTH_LONG).show();
                        list.clear();
                    }
                });
        builder.setNeutralButton("수정", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                list.set(0,name.getText().toString());
                text.setText(name.getText().toString());
                Toast.makeText(getApplicationContext(),"장소를"+name.getText().toString()+"으로 수정하셨습니다.",Toast.LENGTH_LONG).show();
            }
        });
        builder.show();
    }

}