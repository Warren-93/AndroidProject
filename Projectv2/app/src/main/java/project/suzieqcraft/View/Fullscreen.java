package project.suzieqcraft.View;

import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

import project.suzieqcraft.Adapters.FullscreenAdapter;
import project.suzieqcraft.Model.Image;
import project.suzieqcraft.R;

public class Fullscreen extends FragmentActivity {

    FullscreenAdapter fullscreenAdapter;
    RecyclerView fullscreenViewer;
    ImageView fullsizeImage;
    private ArrayList<Image> imageArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_fullscreen );

    fullscreenViewer = findViewById( R.id.fullscreenViewer );
    fullsizeImage = findViewById( R.id.fullsizeImage );


    final LinearLayoutManager linearLayoutManager = new LinearLayoutManager( this );
    fullscreenViewer.setLayoutManager( linearLayoutManager );


    }
}
