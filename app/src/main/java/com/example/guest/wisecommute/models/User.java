package com.example.guest.wisecommute.models;

import android.net.Uri;

/**
 * Created by administrator on 6/7/17.
 */

public class User {
    private String email;
    private String name;
    private String uid;
    private Uri profileImage;

    public User(String email, String name, String uid, Uri profileImage) {
        this.email = email;
        this.name = name;
        this.uid = uid;
        this.profileImage = profileImage;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getUid() {
        return uid;
    }

    public Uri getProfileImage() {
        return profileImage;
    }
}
