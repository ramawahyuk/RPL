package team.dev.helpy.rame.Desc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import team.dev.helpy.rame.Maps.MapsTebing;
import team.dev.helpy.rame.R;

public class keraton extends AppCompatActivity implements View.OnClickListener{

    ImageView imageView;
   /* @BindView(R.id.text_alamat)TextView isi_alamat;
    @BindView(R.id.text_desc)TextView isi_desc;
    @BindView(R.id.text_harga)TextView isi_harga;
    @BindView(R.id.text_jambuka)TextView isi_jam;
*/
    TextView t_alamat,t_deskripsi,t_harga,t_jam;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keraton);
        imageView=(ImageView)findViewById(R.id.btn_map_tahura);
        imageView.setOnClickListener(this);

       TextView t_alamat=(TextView)findViewById(R.id.text_alamat);
       t_alamat.setText(getResources().getString(R.string.tebing_alamat));

       TextView t_deskripsi=(TextView)findViewById(R.id.text_desc);
        t_deskripsi.setText(getResources().getString(R.string.tebing_deskripsi));

        TextView t_harga=(TextView)findViewById(R.id.text_harga);
        t_harga.setText(getResources().getString(R.string.tebing_hargTiket));

        TextView t_jam=(TextView)findViewById(R.id.text_jambuka);
        t_jam.setText(getResources().getString(R.string.tebing_jamBuka));

    }

    @Override
    public void onClick(View v) {
    if(v==imageView) {
        startActivity(new Intent(this, MapsTebing.class));
    }
    }
}
