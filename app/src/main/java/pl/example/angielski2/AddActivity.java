package pl.example.angielski2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

    EditText eng, pl, score;
    Button buttonAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

         eng = findViewById(R.id.addEng);
         pl = findViewById(R.id.addPl);
         score = findViewById(R.id.addScore);

         buttonAdd = findViewById(R.id.buttonAdd);


         buttonAdd.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 MyDbHelper myDB = new MyDbHelper(AddActivity.this);
                 String engTxt = eng.getText().toString();
                 String plTxt = pl.getText().toString();
                 String scoreTxt = score.getText().toString();

                   myDB.addData(engTxt, plTxt,scoreTxt);

             }
         });

    }
}