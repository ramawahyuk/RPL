package team.dev.helpy.rame.Desc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import team.dev.helpy.rame.Maps.Maps_lereng;
import team.dev.helpy.rame.R;

public class lereng extends AppCompatActivity implements View.OnClickListener{

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lereng);
        imageView=(ImageView)findViewById(R.id.btn_map_tahura);
        imageView.setOnClickListener(this);
        TextView t_alamat=(TextView)findViewById(R.id.text_alamat);
        t_alamat.setText(getResources().getString(R.string.lereng_alamat));

        TextView t_deskripsi=(TextView)findViewById(R.id.text_desc);
        t_deskripsi.setText(getResources().getString(R.string.lereng_deskripsi));

        TextView t_harga=(TextView)findViewById(R.id.text_harga);
        t_harga.setText(getResources().getString(R.string.lereng_hargTiket));

        TextView t_jam=(TextView)findViewById(R.id.text_jambuka);
        t_jam.setText(getResources().getString(R.string.lereng_jamBuka));

        TextView t_fasilitas=(TextView)findViewById(R.id.text_fasilitas);
        t_fasilitas.setText(getResources().getString(R.string.lereng_fasilitas));

    }

    @Override
    public void onClick(View v) {
        if(v==imageView) {
            startActivity(new Intent(this, Maps_lereng.class));
        }
    }
}
