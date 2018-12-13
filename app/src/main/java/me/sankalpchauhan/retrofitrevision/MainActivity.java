package me.sankalpchauhan.retrofitrevision;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import me.sankalpchauhan.retrofitrevision.rest.JsonPlaceHolderAPi;
import me.sankalpchauhan.retrofitrevision.rest.model.Comment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    TextView textViewResult;
    JsonPlaceHolderAPi jsonPlaceHolderAPi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewResult = findViewById(R.id.textViewResult);

       // getPosts();
        getComments();

    }

    private void getPosts(){

        //If we want to avoid any passed value we can nullify it by passing null, null can only be passed to Wrapper class so use Integer instead of int
        JsonPlaceHolderAPi.Factory.getInstance().getPosts(4, "id", "desc").enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code());
                    return;
                }

                List<Post> posts = response.body();

                for (Post post : posts) {
                    String content = "";
                    content += "ID: " + post.getID() + "\n";
                    content += "User ID: " + post.getUserID() + "\n";
                    content += "Title: " + post.getTitle() + "\n";
                    content += "Text: " + post.getText() + "\n\n";

                    textViewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });

        //If we used HashMap then we need to pass the below lines:- (BUT THIS IS NOT RECOMMENDED AS WE ARE NOT ABLE TO PASS MULTIPLE PARAMETEWRS)
//        Map<String, String> parameters = new HashMap<>();
//        parameters.put("userId", "1");
//        parameters.put("_sort", "id");
//        parameters.put("_order", "desc");
//
//        Call<List<Post>> call = jsonPlaceHolderApi.getPosts(parameters);
    }

    private void getComments(){

        //SINCE URL IS DEFINED IN API NOW WE CAN USE JsonPlaceHolderAPi.Factory.getInstance().getComments("any endpoint url like post/3/comment").enqueue(new Callback<List<Comment>>()
        JsonPlaceHolderAPi.Factory.getInstance().getComments(3).enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code());
                    return;
                }

                List<Comment> comments = response.body();

                for (Comment comment : comments) {
                    String content = "";
                    content += "ID: " + comment.getId() + "\n";
                    content += "User ID: " + comment.getPostId() + "\n";
                    content += "Name: " + comment.getName() + "\n";
                    content += "Email: " + comment.getEmail() + "\n\n";

                    textViewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }
}
