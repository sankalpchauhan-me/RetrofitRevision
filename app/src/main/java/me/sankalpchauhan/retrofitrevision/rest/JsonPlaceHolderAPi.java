package me.sankalpchauhan.retrofitrevision.rest;

import java.util.List;
import java.util.Map;

import me.sankalpchauhan.retrofitrevision.Post;
import me.sankalpchauhan.retrofitrevision.rest.model.Comment;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

//interface for getting data
public interface JsonPlaceHolderAPi{
    //Retrofit forces us to put 'backslash'/ at the end of url so that they resolve correctly eg:- https://jsonplaceholder.typicode.com/v3/
    String BASE_URL = "https://jsonplaceholder.typicode.com/";

    //If we want to get every post
    @GET("posts")
    Call<List<Post>> getPosts();

    //This is example of https://jsonplaceholder.typicode.com/posts?userId=1
    @GET("posts")
    Call<List<Post>> getPosts(@Query("userId") int userId);

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

    //POST request Example
    @POST("posts")
    Call<Post> createPost(@Body Post post);


    //THIS IS FOR HTML BUT MOST RETROFIT USES JSON

    //Now we will take a look at form encoded url this kind of url encode it in the HTML form format and automatically replaces blank spaces with %20 etc
    // eg userId=23 & title = New%20Title like this
    @FormUrlEncoded
    @POST("posts")
    Call<Post> createPost(
            @Field("userId") int userId,
            @Field("title") String title,
            @Field("body") String text
    );

    @FormUrlEncoded
    @POST("posts")
    Call<Post> createPost(@FieldMap Map<String, String> fields);


    //Put requests change tbe hwole data for the particular object if we don't send a particular data NULL will be used
    @PUT("posts/{id}")
    Call<Post> putPost(@Path("id") int id, @Body Post post);

    //Patch requests change a specific data point in a particular object so if we don't send a particular data old data will be used
    @PATCH("posts/{id}")
    Call<Post> patchPost(@Path("id") int id, @Body Post post);


    //DELETE
    @DELETE("posts/{id}")
    Call<Void> deletePost(@Path("id") int id);


    //Singleton Pattern only one instance can acess
    class Factory {

        private static JsonPlaceHolderAPi service;
        public static JsonPlaceHolderAPi getInstance() {
            if (service == null) {
                //If we want to serialize nulls i.e. for retrofit to consider them (because if we do not do this null value will not be considered of adding)
                //We have to add the following line
                // Gson gson = new GsonBuilder.serializeNulls.create();
                //and pass this in our GSONCONVERTOR FACTORY inside .addConvertorFactory like this:-
                // GsonConverterFactory.create(gson)
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