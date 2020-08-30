package com.gvoscar.apps.carshop.pojos;

import java.util.HashMap;
import java.util.Map;


public class User {
    public static final String key_photoUrl = "photoUrl";
    public static final String key_email = "email";
    public static final String key_displayName = "displayName";

    private long createAt;
    private String uid;
    private String photoUrl;
    private String email;
    private String displayName;

    public User() {
    }

    public User(long createAt, String uid, String photoUrl, String email, String displayName) {
        this.createAt = createAt;
        this.uid = uid;
        this.photoUrl = photoUrl;
        this.email = email;
        this.displayName = displayName;
    }

    public long getCreateAt() {
        return createAt;
    }

    public void setCreateAt(long createAt) {
        this.createAt = createAt;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return "User{" +
                "createAt=" + createAt +
                ", uid='" + uid + '\'' +
                ", photoUrl='" + photoUrl + '\'' +
                ", email='" + email + '\'' +
                ", displayName='" + displayName + '\'' +
                '}';
    }

    public Map<String, Object> toMap() {
        return new HashMap<String, Object>() {{
            put("createAt", createAt);
            put("uid", uid);
            put("photoUrl", photoUrl);
            put("email", email);
            put("displayName", displayName);
        }};
    }
}
