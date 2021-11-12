package kr.co.korearental.petproto;

import android.content.Intent;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;


public class TCPClient implements Runnable{

    private static final String serverIP="59.16.75.12";
    private static final int serverPort = 9999;
    private String msg;
    private ArrayList<String> str;
    private ArrayList<Integer> score;
    DataInputStream dis;
    DataOutputStream dos;
    public static String data, data2, data3, data4, data5, data6;

    public TCPClient(String msg){
        super();
        this.msg = msg;

    }


    public ArrayList<Integer> getSocre(){
        return score;
    }

    @Override

    public void run(){
        try{
            InetAddress serverAddr = InetAddress.getByName(serverIP);
            Socket sock = new Socket(serverAddr, serverPort);
            try{
                /*PrintWriter out = new PrintWriter(new BufferedWriter(new
                        OutputStreamWriter(sock.getOutputStream())), true);
                out.println(msg);
                out.flush();*/

                /*DataInputStream*/ dis = new DataInputStream(new
                        FileInputStream(new File(msg)));
                /*DataOutputStream*/ dos = new
                        DataOutputStream(sock.getOutputStream());

                DataOutputStream dos2 = new DataOutputStream(sock.getOutputStream());
                //BufferedReader br = new BufferedReader(new InputStreamReader(sock.getInputStream(),"UTF-8"));

                byte[] buf = new byte[1024];

                while(dis.read(buf)>0)

                {
                    dos.write(buf);
                    dos.flush();
                }

                dos.close();
                dis.close();

                Log.v("TAG","11");

                /*while (true) {
                    Log.v("태그","진입함");
                    try{
                        int count;
                        while (true){
                            String rev = br.readLine();
                            Log.v("태그","읽어온것:");

                            /*for(int i=0;i<count;i++){
                                str.add(dis2.readUTF());
                            }

                            for(int i=0;i<count;i++){
                                score.add((int)dis2.read());
                            }
                        }

                    }catch (Exception e){
                        Log.v("태그","에러남");
                        break;
                    }

                }*/

            }

            catch(Exception e){
                e.printStackTrace();
            }
            finally
            {
                Log.v("TAG","파이널리");
                sock.close();
            }
         /*Log.v("Tag","catch");
         byte[] byteArr = new byte[1024];
         Log.v("Tag","catch2");
         dis = new DataInputStream(sock.getInputStream());
         Log.v("Tag","catch3");
         int readByteCount = dis.read(byteArr);
         String data = new String(byteArr,0,readByteCount,"UTF-8");
         Log.v("tag",data);
         dis.close();
         sock.close();*/

        }
        catch(Exception e){
            e.printStackTrace();
        }
        try {
            InetAddress serverAddr = InetAddress.getByName(serverIP);
            Socket sock1 = new Socket(serverAddr, serverPort);
            byte[] byteArr = new byte[1024];
            dis = new DataInputStream(sock1.getInputStream());
            int readByteCount = dis.read(byteArr);
            data = new String(byteArr, 0, readByteCount, "UTF-8");
            //str.add(data);
            Log.v("tag", data);
            dis.close();
            sock1.close();

            Socket sock2 = new Socket(serverAddr, serverPort);
            byte[] byteArr2 = new byte[1024];
            dis = new DataInputStream(sock2.getInputStream());
            int readByteCount2 = dis.read(byteArr2);
            data2 = new String(byteArr2, 0, readByteCount2, "UTF-8");
            //str.add(data2);
            Log.v("tag", data2);
            dis.close();
            sock2.close();

            Socket sock3 = new Socket(serverAddr, serverPort);
            byte[] byteArr3 = new byte[1024];
            dis = new DataInputStream(sock3.getInputStream());
            int readByteCount3 = dis.read(byteArr3);
            data3 = new String(byteArr3, 0, readByteCount3, "UTF-8");
            //str.add(data3);
            Log.v("tag", data3);
            dis.close();
            sock3.close();

            Socket sock4 = new Socket(serverAddr, serverPort);
            byte[] byteArr4 = new byte[1024];
            dis = new DataInputStream(sock4.getInputStream());
            int readByteCount4 = dis.read(byteArr4);
            data4 = new String(byteArr4, 0, readByteCount4, "UTF-8");
            //score.add(Integer.parseInt(data4));
            Log.v("tag", data4);
            dis.close();
            sock4.close();

            Socket sock5 = new Socket(serverAddr, serverPort);
            byte[] byteArr5 = new byte[1024];
            dis = new DataInputStream(sock5.getInputStream());
            int readByteCount5 = dis.read(byteArr5);
            data5 = new String(byteArr5, 0, readByteCount5, "UTF-8");
            //score.add(Integer.parseInt(data5));
            Log.v("tag", data5);
            dis.close();
            sock5.close();

            Socket sock6 = new Socket(serverAddr, serverPort);
            byte[] byteArr6 = new byte[1024];
            dis = new DataInputStream(sock6.getInputStream());
            int readByteCount6 = dis.read(byteArr6);
            data6 = new String(byteArr6, 0, readByteCount6, "UTF-8");
            //score.add(Integer.parseInt(data6));
            Log.v("tag", data6);
            dis.close();
            sock6.close();

            /*str.add(data);
            Log.v("tag", data);
            str.add(data2);
            Log.v("tag", data2);
            str.add(data3);
            Log.v("tag", data3);
            score.add(Integer.parseInt(data4));
            Log.v("tag", data4);
            score.add(Integer.parseInt(data5));
            Log.v("tag", data5);
            score.add(Integer.parseInt(data6));
            Log.v("tag", data6);*/


        }
        catch (Exception e){

        }
        /*finally {
            str.add(data);
            Log.v("tag", data);
            str.add(data2);
            Log.v("tag", data2);
            str.add(data3);
            Log.v("tag", data3);
            score.add(Integer.parseInt(data4));
            Log.v("tag", data4);
            score.add(Integer.parseInt(data5));
            Log.v("tag", data5);
            score.add(Integer.parseInt(data6));
            Log.v("tag", data6);

        }*/

    }
}