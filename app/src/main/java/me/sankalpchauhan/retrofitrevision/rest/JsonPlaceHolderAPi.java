package me.sankalpchauhan.retrofitrevision.rest;

import java.util.List;
import java.util.Map;

import me.sankalpchauhan.retrofitrevision.Post;
import me.sankalpchauhan.retrofitrevision.rest.model.Comment;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

//interface for getting data
public interface JsonPlaceHolderAPi{
    //Retrofit forces us to put 'backslash'/ at the end of url so that they resolve correctly eg:- https://jsonplaceholder.typicode.com/v3/
    String BASE_URL = "https://jsonplaceholder.typicode.com/";

    //If we want to get every post
//    @GET("posts")
//    Call<List<Post>> getPosts();

    //This is example of https://jsonplaceholder.typicode.com/posts?userId=1
//    @GET("posts")
//    Call<List<Post>> getPosts(@Query("userId") int userId);

    //This is example of https://jsonplaceholder.typicode.com/posts?userId=1&_sort=id&_order=desc
    @GET("posts")
    Call<List<Post>> getPosts(@Query("userId") Integer userId,
                              @Query("_sort") String sort,
                              @Query("_order") String order);

    //If we want to pass multiple user Id's
    @GET("posts")
    Call<List<Post>> getPosts(
            @Query("userId") Integer[] userId,
            @Query("_sort") String sort,
            @Query("_order") String order
    );

    //If we want to pass a QueryMap, a query Map can contain many parameters together
    @GET("posts")
    Call<List<Post>> getPosts(@QueryMap Map<String, String> parameters);


    //The below line of example is for https://jsonplaceholder.typicode.com/posts/1/comments //Here 1 is passed as variable
    @GET("posts/{id}/comments")
    Call<List<Comment>> getComments(@Path("id") int postid);

    //The below line of example is if we want to pass the full query as
    @GET
    Call<List<Comment>> getComments(@Url String Url);

    class Factory {

        private static JsonPlaceHolderAPi service;
        public static JsonPlaceHolderAPi getInstance() {
            if (service == null) {
                Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL).build();
                service = retrofit.create(JsonPlaceHolderAPi.class);
                return service;
            }
            else {
                return service;
            }
        }
    }
}