package com.example.soundaryalahari;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.soundaryalahari.databinding.FragmentPlaylistBinding;
import com.example.soundaryalahari.databinding.FragmentSongsBinding;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SongsFragment extends Fragment {
    List<List<String>> songs = new ArrayList<>();
    List<List<String>> titles = new ArrayList<>();
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    FragmentSongsBinding mBinding;
    int position;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstances){
        mBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_songs, container, false);
        View view = mBinding.getRoot();
        //here data must be an instance of the class MarsDataProvider
        return view;
    }

    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return super.getLifecycle();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        songs.add(Arrays.asList("soundaryalahari_intro_1","soundaryalahari_intro_2"));
        List<String> slokams = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
               slokams.add("soundarya_lahari_" + i);
               if (i % 10 == 0) {
                   int s = i-9;
                   slokams.add("soundaryalahari"+s+"_"+i);
                   songs.add(slokams);
                   slokams = new ArrayList<String>();
               }
           }
        songs.add(Arrays.asList("soundaryalahariend"));
       songs.add(Arrays.asList("soundaryalahari1_10","soundaryalahari11_20","soundaryalahari21_30","soundaryalahari31_40",
                "soundaryalahari41_50","soundaryalahari51_60","soundaryalahari61_70","soundaryalahari71_80","soundaryalahari81_90","soundaryalahari91_100","soundaryalahariend"));
        List<String> slokam_titles = new ArrayList<String>();
        titles.add(Arrays.asList("GANESH PRARTHANA","OM SRI MATHREY NAMAHA"));
        for(int i=1;i<=100;i++){
            slokam_titles.add("SLOKAM " + i);
            if(i%10==0){
                int s = i-9;
                slokam_titles.add("REVIEW SOUNDARYA LAHARI SLOKAM "+s+"-"+i);
                titles.add(slokam_titles);
                slokam_titles = new ArrayList<>();
            }
        }
        titles.add(Arrays.asList("OM SRI LALITHA DEVEY NAMAHA"));
        titles.add(Arrays.asList("SOUNDARYA LAHARI SLOKAM 1-10","SOUNDARYA LAHARI SLOKAM 11-20","SOUNDARYA LAHARI SLOKAM 21-30","SOUNDARYA LAHARI SLOKAM 31-40",
                "SOUNDARYA LAHARI SLOKAM 41-50","SOUNDARYA LAHARI SLOKAM 51-60","SOUNDARYA LAHARI SLOKAM 61-70","SOUNDARYA LAHARI SLOKAM 71-80","SOUNDARYA LAHARI SLOKAM 81-90","SOUNDARYA LAHARI SLOKAM 91-100","OM SRI LALITHA DEVEY NAMAHA"));
        Bundle bundle = this.getArguments();
        position = bundle.getInt("position");
        mBinding.setLifecycleOwner(this);
        mBinding.songs.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        mBinding.songs.setLayoutManager(layoutManager);
        adapter = new SongsAdapter(getContext(),position,songs.get(position).size(),titles.get(position),songs.get(position));
        mBinding.songs.setAdapter(adapter);
    }
}
