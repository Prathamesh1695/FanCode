package com.example.fancode;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

public interface ApiService {

    @GET("users")
    Call<List<User>> getUsers();

    @GET("todos")
    Call<List<Todo>> getTodosByUserId(@Query("userId") int i);
}
