package com.example.userthree.videoplayer.contract;

import android.content.Intent;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;

public interface LoginActivityContract {
    interface View{
        void signIn(GoogleSignInClient mGoogleSignInClient);
        void showError(String message);
        void goToVideoActivity();
    }



    interface Presenter{
        void onSignInButtonClicked();

        void onAccountSignedIn(Intent data);
    }

}
