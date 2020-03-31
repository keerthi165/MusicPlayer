package com.example.soundaryalahari;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.soundaryalahari.databinding.FragmentPlaylistBinding;

import java.util.Arrays;
import java.util.List;

public class PlaylistFragment extends Fragment {
    List<String> songs;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    FragmentPlaylistBinding mBinding;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstances){
         mBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_playlist, container, false);
        View view = mBinding.getRoot();
        //here data must be an instance of the class MarsDataProvider
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        songs = Arrays.asList("INTRODUCTION","SLOKAM 1-10","SLOKAM 11-20","SLOKAM 21-30","SLOKAM 31-40",
                "SLOKAM 41-50","SLOKAM 51-60","SLOKAM 61-70","SLOKAM 71-80","SLOKAM 81-90","SLOKAM 91-100","OM SRI LALITHA DEVEY NAMAHA","REVIEW");
        mBinding.setLifecycleOwner(this);
        mBinding.playlist.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        mBinding.playlist.setLayoutManager(layoutManager);
        adapter = new PlaylistAdapter(getContext(),songs.size(),songs);
        mBinding.playlist.setAdapter(adapter);
    }

}
