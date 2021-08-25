package com.dpearth.dvox.models.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.dpearth.dvox.R;
import com.dpearth.dvox.RandomNameGenerator;
import com.dpearth.dvox.databinding.ActivityUserProfile2Binding;
import com.dpearth.dvox.livedata.User;
import com.dpearth.dvox.username.Username;


public class UserFragment extends Fragment {
    private Button generateNewNameButton;
    private TextView generatedNameTextView;
    public static final String USERNAME_PREFS = "usernamePrefs";
    private String username;

    private ActivityUserProfile2Binding binding;

    private Username usernameInstance;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //return inflater.inflate(R.layout.activity_user_profile2, container, false);

        //for all fragments
        usernameInstance = new Username(getActivity());
        usernameInstance.retrieveUsername(true);

        binding = DataBindingUtil.inflate(inflater, R.layout.activity_user_profile2, container, false);
        SharedPreferences preferences = getActivity().getSharedPreferences(USERNAME_PREFS, Context.MODE_PRIVATE);
        String name = preferences.getString(USERNAME_PREFS, "");
        binding.setUser(new User(name));
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        ActivityMainBinding binding = DataBindingUtil.setContentView(getActivity(), R.layout.activity_main);


        /*  Button for Regenerating a new name  */
        generateNewNameButton = getActivity().findViewById(R.id.generate_new_name_button);
        generatedNameTextView = getActivity().findViewById(R.id.profile_username);



        generateNewNameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                generateName();
            }
        });

        loadData();
        updateViews();

    }

    private void generateName() {

        usernameInstance.generateUsername(false);

        username = usernameInstance.getUsernameString();
        Log.d("Usernae", "generateName: " + username);

        binding.setUser(new User(username));

        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(USERNAME_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit().putString(USERNAME_PREFS, username);

        editor.apply();

        Toast.makeText(getActivity(), "Data Saved", Toast.LENGTH_SHORT).show();


    }

    public void loadData(){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(USERNAME_PREFS, Context.MODE_PRIVATE);

        username = sharedPreferences.getString(USERNAME_PREFS, "Generate Username");

    }

    public void updateViews() {

        generatedNameTextView.setText(username);
    }


}

