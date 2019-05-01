package project.suzieqcraft.Fragments;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import project.suzieqcraft.Adapters.FullscreenAdapter;
import project.suzieqcraft.Model.Image;
import project.suzieqcraft.R;

import static project.suzieqcraft.View.Gallery.imageList;

public class FullScreen_Fragment extends Fragment {

    FullscreenAdapter fullscreenAdapter;
    RecyclerView fullscreenViewer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fullscreenAdapter = new FullscreenAdapter( imageList );
        fullscreenViewer.setAdapter( fullscreenAdapter );

        // Inflate the layout for this fragment
        return inflater.inflate( R.layout.fullscreenfrag, container, false);

    }


    public static FullScreen_Fragment createIntent(ArrayList<Image> productList) {
        FullScreen_Fragment fullScreen_fragment = new FullScreen_Fragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("key", productList);
        fullScreen_fragment.setArguments(bundle);
        return fullScreen_fragment;
    }







}
