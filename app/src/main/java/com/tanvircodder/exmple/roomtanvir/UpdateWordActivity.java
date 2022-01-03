package com.tanvircodder.exmple.roomtanvir;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.tanvircodder.exmple.roomtanvir.model.DatabaseClient;
import com.tanvircodder.exmple.roomtanvir.model.Word;

public class UpdateWordActivity extends AppCompatActivity {
    EditText mText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_word);
        mText = (EditText)findViewById(R.id.update_text);
        final Word word =(Word) getIntent().getSerializableExtra("word");
        mText.setText(word.getWordName());
        findViewById(R.id.update_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateWords(word);
            }
        });
        findViewById(R.id.delete_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(UpdateWordActivity.this);
                builder.setTitle("Are you sure?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        deleteWords(word);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
//                        we will do nothing
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }
    private void updateWords(Word word){
        String newWord = mText.getText().toString();
        if (newWord.isEmpty()){
            mText.setError("Need to be Filled");
            mText.requestFocus();
            return;
        }
        class WordUpdate extends AsyncTask<Void,Void,Void>{

            @Override
            protected Void doInBackground(Void... voids) {
                word.setWordName(newWord);
                DatabaseClient.getmInstance(getApplicationContext()).getAppDatabase()
                        .wordDao()
                        .update(word);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getApplicationContext(),"Updated",Toast.LENGTH_LONG).show();
                finish();
                startActivity(new Intent(UpdateWordActivity.this,MainActivity.class));
            }
        }
        WordUpdate wordUpdate = new WordUpdate();
        wordUpdate.execute();
    }
    public void deleteWords(Word word){
        class WordDelete extends AsyncTask<Void , Void, Void>{

            @Override
            protected Void doInBackground(Void... voids) {
                DatabaseClient.getmInstance(getApplicationContext()).getAppDatabase()
                        .wordDao()
                        .delete(word);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_LONG).show();
                finish();
                startActivity(new Intent(UpdateWordActivity.this, MainActivity.class));
            }
        }
        WordDelete wordDelete = new WordDelete();
        wordDelete.execute();
    }

}