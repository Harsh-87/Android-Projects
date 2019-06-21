package com.example.projects;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

import java.util.Locale;

public class Text_to_Speech extends AppCompatActivity {

    Button speak;
    TextToSpeech mtts;
    SeekBar pitchseek,speedseek;
    EditText text_to_be_spoken;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_to_speech);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null) {actionBar.setDisplayHomeAsUpEnabled(true);}
        speak =(Button) findViewById(R.id.speak);
        text_to_be_spoken = (EditText) findViewById(R.id.text_to_be_spoken);
        pitchseek = (SeekBar) findViewById(R.id.pitch_seek);
        speedseek = (SeekBar) findViewById(R.id.speed_seek);
        mtts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status==TextToSpeech.SUCCESS){
                    int result = mtts.setLanguage(Locale.getDefault());
                    if(result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                        Toast.makeText(Text_to_Speech.this,"There Was A Glitch",Toast.LENGTH_SHORT).show();
                    }else{
                        speak.setEnabled(true);
                    }
                }else{
                    Toast.makeText(Text_to_Speech.this,"There Was A Glitch",Toast.LENGTH_SHORT).show();
                }
            }
        });
        speak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speak_now();
            }
        });
    }
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    public void speak_now(){
        String text = text_to_be_spoken.getText().toString();
        if((float)pitchseek.getProgress()/50<0.1){
            pitchseek.setProgress(5);
        }
        if((float)speedseek.getProgress()/50<0.1){
            speedseek.setProgress(5);
        }
        mtts.setPitch((float)pitchseek.getProgress()/50);
        mtts.setSpeechRate((float)speedseek.getProgress()/50);
        mtts.speak(text,TextToSpeech.QUEUE_FLUSH,null);
    }

}
