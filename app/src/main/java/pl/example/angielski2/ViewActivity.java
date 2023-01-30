package pl.example.angielski2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewActivity extends AppCompatActivity implements OnButtonClickListener {

    RecyclerView recyclerView;
    ArrayList<String> id, english, polish, score;
    MyDbHelper db;
    MyAdapter myAdapter;

    ImageButton imageButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        db = new MyDbHelper(this);

        id = new ArrayList<>();
        english = new ArrayList<>();
        polish = new ArrayList<>();
        score = new ArrayList<>();

        recyclerView = findViewById(R.id.recycler);

        imageButton =  findViewById(R.id.myImageButton);


        myAdapter = new MyAdapter(id, english, polish, score, this);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        displayData();

    }
        private void displayData () {
            Cursor cursor = db.getData();
            if (cursor.getCount() == 0) {
                Toast.makeText(ViewActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                return;
            } else {
                while (cursor.moveToNext()) {
                    id.add(cursor.getString(0));
                    english.add(cursor.getString(1));
                    polish.add(cursor.getString(2));
                    score.add(cursor.getString(3));
                }
            }
        }
    @Override
    public void onButtonClick(int position) {
        int res = getResources().getIdentifier("music", "raw", getPackageName());
        MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), res );
        mediaPlayer.start();
    }
}