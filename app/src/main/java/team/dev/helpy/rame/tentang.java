package team.dev.helpy.rame;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class tentang extends Fragment {

    public tentang() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_tentang, container, false);

       ImageView img=(ImageView)view.findViewById(R.id.insta);
       img.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent i=new Intent();
               i.setAction(Intent.ACTION_VIEW);
               i.addCategory(Intent.CATEGORY_BROWSABLE);
               i.setData(Uri.parse("https://www.instagram.com/ramawahyuk/"));
               startActivity(i);
           }
       });
        return view;
    }


}
