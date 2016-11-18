package com.example.krupal.facebookgraphapi;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphRequestBatch;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Krupal on 11/16/2016.
 */
public class MainFragment extends Fragment {


    private CallbackManager callbackManager;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getActivity().getApplicationContext());
        callbackManager = CallbackManager.Factory.create();


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       return inflater.inflate(R.layout.mainfragment, container,false);
    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LoginButton loginButton = (LoginButton) view.findViewById(R.id.login_button);

        //loginButton.setReadPermissions("user_friends");
        loginButton.setFragment(this);
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(getActivity().getApplicationContext(),"Login Sucess!",Toast.LENGTH_LONG).show();
                LoginManager.getInstance().logInWithReadPermissions(getActivity(),
                        Arrays.asList("user_friends", "user_location", "user_birthday", "user_likes",
                                "user_photos"));

                GraphRequest request = GraphRequest.newMeRequest(
                        AccessToken.getCurrentAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(final JSONObject object,
                                                    GraphResponse response) {


                                String name = object.optString("name");
                                String id = object.optString("id");
                                String link = object.optString("link");
                                String birthday  = object.optString("birthday");
                                String location = object.optJSONObject("location").optString("name");

                                Log.d("Name", name);
                                Log.d("Id", id);
                                Log.d("Link", link);
                                Log.d("birthday", birthday);
                                Log.d("location", location);
                                ProfilePictureView profilePictureView = (ProfilePictureView)view.findViewById(R.id.picture);
                                profilePictureView.setProfileId(id);

                                GraphRequestBatch batch = new GraphRequestBatch(
                                        GraphRequest.newMeRequest(
                                                AccessToken.getCurrentAccessToken(),
                                                new GraphRequest.GraphJSONObjectCallback() {
                                                    @Override
                                                    public void onCompleted(
                                                            JSONObject jsonObject,
                                                            GraphResponse response) {
                                                        // Application code for user
                                                    }
                                                }),
                                        GraphRequest.newMyFriendsRequest(
                                                AccessToken.getCurrentAccessToken(),
                                                new GraphRequest.GraphJSONArrayCallback() {
                                                    @Override
                                                    public void onCompleted(
                                                            JSONArray jsonArray,
                                                            GraphResponse response) {
                                                        // Application code for users friends

                                                        JSONObject object1 = response.getJSONObject();
                                                        JSONObject summary = object1.optJSONObject("summary");
                                                       Log.d("summary",summary.optString("total_count"));
                                                        JSONArray listFriends = object1.optJSONArray("data");
                                                        Log.d("listFriends",listFriends.length()+"");
                                                        JSONArray birthday = object1.optJSONArray("user_birthday");
                                                        Log.d("listFriends",listFriends.length()+"");



                                                    }
                                                })
                                );
                                batch.addCallback(new GraphRequestBatch.Callback() {
                                    @Override
                                    public void onBatchCompleted(GraphRequestBatch graphRequests) {
                                        // Application code for when the batch finishes
                                    }
                                });
                                batch.executeAsync();

                            }

                        });


                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,link,birthday,location");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {
                Toast.makeText(getActivity().getApplicationContext(),"Login Cancle!",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(getActivity().getApplicationContext(),"Login Error!",Toast.LENGTH_LONG).show();

            }
        });

    }




    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }

    @Override
    public void onStop() {
        super.onStop();

    }
}