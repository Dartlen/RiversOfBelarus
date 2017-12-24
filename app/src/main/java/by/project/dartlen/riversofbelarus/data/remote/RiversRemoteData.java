package by.project.dartlen.riversofbelarus.data.remote;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;


import org.greenrobot.greendao.annotation.NotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.inject.Inject;
import javax.inject.Singleton;

import by.project.dartlen.riversofbelarus.data.LoadDataCallback;
import by.project.dartlen.riversofbelarus.data.LoadNotesCallback;
import by.project.dartlen.riversofbelarus.data.LoadPostNoteCallback;
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

    public void loadNotes(@NotNull final LoadNotesCallback callback, @NotNull final String phone, @NotNull final Day day,
                          @NotNull final String namePost){
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("Notes");
        table_user.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                GenericTypeIndicator<HashMap<String ,Note>> t = new GenericTypeIndicator<HashMap<String, Note>>(){};

                HashMap<String, Note> note  = dataSnapshot.child(phone).getValue(t);
                HashMap<String, Note> resultNotes = new HashMap<>(0);

                if(note==null)
                    callback.onDataNotAvailable("not found");
                else {
                    for (Map.Entry<String, Note> entry : note.entrySet()) {
                        if(entry.getValue().getTo().equals(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(day.getTo()))
                                && entry.getValue().getPost().equals(namePost))
                        {
                            resultNotes.put(entry.getKey(), entry.getValue());
                        }
                    }
                    List<Note> values = new ArrayList<Note>(resultNotes.values());
                    if (values.size() > 0)

                        callback.onNoteDataLoaded(values);
                    else
                        callback.onDataNotAvailable("not found");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                callback.onDataNotAvailable(databaseError.toString());
            }
        });
    }

    public void postNote(@NotNull final LoadPostNoteCallback callback, @NotNull final String  note,
                         @NotNull final Date date, @NotNull final String phone, @NotNull final String namePost){
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        mRef = database.getReference();
        Note tmpnote = new Note();
        tmpnote.setText(note);

        tmpnote.setTo(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(date));
        tmpnote.setPost(namePost);
        DatabaseReference NotesRef = mRef.child("Notes").child(phone);

        List<Note> listnotes = new ArrayList<>();
        listnotes.add(tmpnote);

        String key = NotesRef.push().getKey();

        tmpnote.setKey(key);

        NotesRef.child(key).setValue(tmpnote);
        callback.onNoteDataLoaded("ok");
    }

}
