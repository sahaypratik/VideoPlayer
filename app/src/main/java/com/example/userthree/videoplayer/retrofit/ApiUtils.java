package com.example.userthree.videoplayer.retrofit;

public class ApiUtils
{
    public static final String BASE_URL="https://interview-e18de.firebaseio.com/";

    public static UserService getUserService()
    {
        return RetrofitClient.getClient(BASE_URL).create(UserService.class);

    }
}
