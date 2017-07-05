package com.example.deepak.partyzing.Presenter;

import com.example.deepak.partyzing.Model.FacebookProfile;
import com.facebook.Profile;

/**
 * Created on 29/7/16.
 */
public class FBPresenter {
    private final FacebookProfile fbProfile;

    public FBPresenter() {
        fbProfile = new FacebookProfile();
    }

    public void setCurrentProfile(Profile profile) {
        fbProfile.setName(profile.getName());
        fbProfile.setId(profile.getId());
    }
}
