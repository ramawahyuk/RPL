package team.dev.helpy.rame;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import team.dev.helpy.rame.Desc.dago;
import team.dev.helpy.rame.Desc.dpakar;
import team.dev.helpy.rame.Desc.floating;
import team.dev.helpy.rame.Desc.kambing;
import team.dev.helpy.rame.Desc.keraton;
import team.dev.helpy.rame.Desc.lawang;
import team.dev.helpy.rame.Desc.lereng;
import team.dev.helpy.rame.Desc.moko;
import team.dev.helpy.rame.Desc.stone;
import team.dev.helpy.rame.Desc.tahura;
import team.dev.helpy.rame.Maps.Maps_kambing;


public class HomeFragment extends Fragment {


   private ImageView btn_img_tebing;
    @BindView(R.id.iv_favoriteTranstudio)
    Button _favTransStudio;
    @BindView(R.id.iv_favoriteMoko) Button _favMoko;
    @BindView(R.id.iv_favoriteCikapundung) Button _favCikapundung;

    TextView btn_tebingkeraton;



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
       // return inflater.inflate(R.layout.fragment_home, container, false);

        ImageView btnImageView=(ImageView)view.findViewById(R.id.btn_tebingkeraton);
        btnImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  in =new Intent(getActivity(),keraton.class);
                in.putExtra("some","somedata");
                startActivity(in);
            }
        });

        ImageView btnLerengAnteng=(ImageView)view.findViewById(R.id.lerenganteng);
        btnLerengAnteng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(getActivity(),lereng.class);
                in.putExtra("tampil","Lereng");
                startActivity(in);
            }
        });

        ImageView btntahura=(ImageView)view.findViewById(R.id.tahura);
        btntahura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(getActivity(),tahura.class);
                in.putExtra("tampil","tahura");
                startActivity(in);
            }
        });
        ImageView btnmoko=(ImageView)view.findViewById(R.id.moko);
        btnmoko.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(getActivity(),moko.class);
                in.putExtra("tampil","bukit moko");
                startActivity(in);
            }
        });
        ImageView btnlawang=(ImageView)view.findViewById(R.id.lawang);
        btnlawang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(getActivity(),lawang.class);
                in.putExtra("tampil","lawang wangi");
                startActivity(in);
            }
        });
        ImageView btnfloating=(ImageView)view.findViewById(R.id.floating);
        btnfloating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(getActivity(),floating.class);
                in.putExtra("tampil","Floating market");
                startActivity(in);
            }
        });
        ImageView btnstone=(ImageView)view.findViewById(R.id.stone);
        btnstone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(getActivity(),stone.class);
                in.putExtra("tampil","Stone Cafe ");
                startActivity(in);
            }
        });
        ImageView btndpakar=(ImageView)view.findViewById(R.id.dpakar);
        btndpakar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(getActivity(),dpakar.class);
                in.putExtra("tampil","Cafe d'Pakar ");
                startActivity(in);
            }
        });
        ImageView btndago=(ImageView)view.findViewById(R.id.Sierra);
        btndago.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(getActivity(),dago.class);
                in.putExtra("tampil","Dago terrace ");
                startActivity(in);
            }
        });
        ImageView btnkambing=(ImageView)view.findViewById(R.id.kambing);
        btnkambing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(getActivity(),kambing.class);
                in.putExtra("tampil","Kambing soon Cafe and resto ");
                startActivity(in);
            }
        });

        return view;
    }




    @OnClick(R.id.btn_tebingkeraton)
    public void tsm_detail(){
        Intent intent = new Intent(getActivity(), MapsActivity.class);
        intent.putExtra("KEY","TSM");
        startActivityForResult(intent, 0);
    }

    @OnClick(R.id.lerenganteng)
    public void bukitMoko_detail(){
        Intent intent = new Intent(getActivity(), MapsActivity.class);
        intent.putExtra("KEY","bukitMoko");
        startActivityForResult(intent, 0);
    }

    @OnClick(R.id.tahura)
    public void cikapundung_detail(){
        Intent intent = new Intent(getActivity(), MapsActivity.class);
        intent.putExtra("KEY","cikapundung");
        startActivityForResult(intent, 0);
    }


    public void img_tebingkeraton(View view){

    }

}



