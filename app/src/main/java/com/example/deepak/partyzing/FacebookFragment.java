package com.example.deepak.partyzing;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.w3c.dom.Text;

import java.util.Arrays;
import java.util.List;

/**
 * Created on 28/6/16.
 */
public class FacebookFragment extends Fragment {
    private CallbackManager mCallbackManager;
    private AccessTokenTracker mTokenTracker;
    private ProfileTracker mProfileTracker;
    String name, id;
    private final String loginPermissions[] = {"user_photos", "email", "user_birthday", "user_friends"};

    /**
     * Callbacks handling methods after facebook API is called
     */
    private FacebookCallback<LoginResult> mCallback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            AccessToken accessToken = loginResult.getAccessToken();
            Profile profile = Profile.getCurrentProfile();
            name = profile.getName();  //name and id are not used as for now
            id = profile.getId();      //they need to be stored in DB
            Intent intent = new Intent(getActivity(), OccasionActivity.class);       //starting with next screen on successful login
            startActivity(intent);
        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onError(FacebookException error) {
            Toast.makeText(getContext(), R.string.connectivity_error, Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getActivity().getApplicationContext());         //initializing facebook sdk
        mCallbackManager = CallbackManager.Factory.create();

        mTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {

            }
        };
        mProfileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                name = currentProfile.getName();     //name and id are not used as for now
                id = currentProfile.getId();         //as they need to be stored in DB

            }
        };
        mTokenTracker.startTracking();               //Initializing Token tracker
        mProfileTracker.startTracking();             //Initializing Profile tracker
    }

    /**
     * Setting login permissions , callbackManager and callback methods
     */
    @Override
    public void onStart() {
        super.onStart();
        List<String> permissionNeeds = Arrays.asList(loginPermissions[0], loginPermissions[1], loginPermissions[2], loginPermissions[3]);
        LoginManager.getInstance().logInWithReadPermissions(this, permissionNeeds);
        LoginManager.getInstance().registerCallback(mCallbackManager, mCallback);
    }

    //call to callback Manager on onActivityResult
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    //Stop tracking when the user is connected to the facebook
    @Override
    public void onStop() {
        super.onStop();
        mTokenTracker.stopTracking();
        mProfileTracker.stopTracking();
    }
}
