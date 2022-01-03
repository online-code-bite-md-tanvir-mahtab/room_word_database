package com.tanvircodder.exmple.roomtanvir;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.tanvircodder.exmple.roomtanvir.model.DatabaseClient;
import com.tanvircodder.exmple.roomtanvir.model.Word;

public class AddWordActivity extends AppCompatActivity {
    private EditText editWrodText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word);

        editWrodText = (EditText) findViewById(R.id.edit_word);

        findViewById(R.id.button_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveWords();
            }
        });
    }

    private void saveWords(){
        String sWords = editWrodText.getText().toString();

        if (sWords.isEmpty()){
            editWrodText.setError("You need to fill the box");
            editWrodText.requestFocus();
            return;
        }

        class AddWord extends AsyncTask<Void,Void, Void>{

            @Override
            protected Void doInBackground(Void... voids) {
                Word word = new Word();
                word.setWordName(sWords);
                DatabaseClient.getmInstance(getApplicationContext()).getAppDatabase()
                        .wordDao().insert(word);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                finish();
                startActivity(new Intent(AddWordActivity.this,MainActivity.class));
                Toast.makeText(getApplicationContext(),"Saved",Toast.LENGTH_LONG).show();
            }
        }
        AddWord addWord = new AddWord();
        addWord.execute();
    }
}