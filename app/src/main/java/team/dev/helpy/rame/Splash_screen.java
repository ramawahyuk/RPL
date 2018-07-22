package team.dev.helpy.rame;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

public class Splash_screen extends AppCompatActivity {
    private static final int SPLASH_TIME = 3000;
    ProgressBar pg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_splash_screen);
        pg =(ProgressBar)findViewById(R.id.pg);
        pg.setMax(55);
        pg.setProgress(0);


        new BackgroundTask().execute();

        final Thread thread=new Thread(){
            @Override
            public void run() {
                try{

                    for (int i=0;i<100;i++){
                        pg.setProgress(i);
                        sleep(50);
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    //Intent intent=new Intent(getApplicationContext(), Login.class);
                  //startActivity(new Intent(Splash_screen.this,Login.class));
                    finish();
                }



            }
        };
        thread.start();

    }

    private class BackgroundTask extends AsyncTask {
        Intent intent;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            intent = new Intent(Splash_screen.this, menubeta.class);
        }

        @Override
        protected Object doInBackground(Object[] params) {

            /*  Use this method to load background
             * data that your app needs. */

            try {
                Thread.sleep(SPLASH_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
//            Pass your loaded data here using Intent

//            intent.putExtra("data_key", "");
            startActivity(intent);
            finish();
        }
    }





}
