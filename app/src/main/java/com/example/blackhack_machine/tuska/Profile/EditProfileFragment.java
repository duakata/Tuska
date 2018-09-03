package com.example.blackhack_machine.tuska.Profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.blackhack_machine.tuska.R;
import com.example.blackhack_machine.tuska.Utils.UniversalImageLoader;
import com.nostra13.universalimageloader.core.ImageLoader;

public class EditProfileFragment extends Fragment {
    private static final String TAG = "EditProfileFragment";
    private ImageView mProfilePhoto;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_editprofile, container, false);
        mProfilePhoto = (ImageView) view.findViewById(R.id.profile_photo);
        initImageLoader();
        setProfileImage();

        //back arrow navigating
        ImageView backArrow = (ImageView) view.findViewById(R.id.backArrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG,"onCLick: navigating back");
                getActivity().finish();
            }
        });


        return view;

    }

    private void initImageLoader (){
        UniversalImageLoader universalImageLoader = new UniversalImageLoader(getActivity());
        ImageLoader.getInstance().init(universalImageLoader.getConfig());
    }


    private void setProfileImage(){
        Log.d(TAG,"setProfileImage: setting Profile Image");
        String imgURL = "www.androidcentral.com/sites/androidcentral.com/files/styles/xlarge/public/article_images/2016/08/ac-lloyd.jpg?itok=bb72IeLf";
        UniversalImageLoader.setImage(imgURL, mProfilePhoto, null, "https://");
    }

}
