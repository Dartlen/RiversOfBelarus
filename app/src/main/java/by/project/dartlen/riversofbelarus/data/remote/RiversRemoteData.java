package by.project.dartlen.riversofbelarus.data.remote;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;


import org.greenrobot.greendao.annotation.NotNull;

import java.util.List;


import javax.inject.Inject;
import javax.inject.Singleton;

import by.project.dartlen.riversofbelarus.data.CheckUserCallback;
import by.project.dartlen.riversofbelarus.data.LoadDataCallback;
import by.project.dartlen.riversofbelarus.data.LoadPostsCallback;
import by.project.dartlen.riversofbelarus.data.LoadRiversCallback;
import by.project.dartlen.riversofbelarus.data.LoadUserCallback;

/***
 * Created by Dartlen on 13.11.2017.
 */

@Singleton
public class RiversRemoteData {

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mRef;
    private List<Post> list;

    @Inject
    public RiversRemoteData() {}

    public void getPosts(@NotNull final LoadDataCallback callback){
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        mRef = database.getReference();
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                GenericTypeIndicator<List<Post>> t = new GenericTypeIndicator<List<Post>>(){};
                list = dataSnapshot.child("post").getValue(t);
                callback.onRiversLoaded(list);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                callback.onDataNotAvailable(databaseError.toString());
            }
        });
    }

    public void getUser(@NotNull final LoadUserCallback callback, @NotNull final String phone){
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");
        table_user.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.child(phone).getValue(User.class);
                callback.onUserLoaded(user);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                callback.onDataNotAvailable(databaseError.toString());
            }
        });
    }

    public void registerUser(@NotNull final LoadUserCallback callback, @NotNull final User user,
                             @NotNull final String phone){
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        table_user.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if(dataSnapshot.child(phone).exists()) {
                   callback.onDataNotAvailable("fail");
                }
                else{

                    table_user.child(phone).setValue(user);

                    callback.onUserLoaded(user);


                    /*table_user.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            User user = dataSnapshot.child(phone).getValue(User.class);
                            callback.onUserLoaded(user);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            callback.onDataNotAvailable(databaseError.toString());
                        }
                    });*/
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                callback.onDataNotAvailable(databaseError.toString());
            }
        });


    }
}
