package com.example.icp11;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    EditText textEdit;
    Button btn;
    TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // getting the elements using id's
        textEdit = findViewById(R.id.editTextTTS);
        btn = findViewById(R.id.buttonTTS);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tts = new TextToSpeech(MainActivity.this, new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if (status == TextToSpeech.SUCCESS) {
                            // setting the language to US locale
                            int result = tts.setLanguage(Locale.US);
                            if (result == TextToSpeech.LANG_NOT_SUPPORTED ||
                                    result == TextToSpeech.LANG_MISSING_DATA)
                                Log.e("message", "language is not supported");
                            else {
                                // calling the speak method
                                speak();
                            }
                        } else
                            Log.e("message", "TTS is not supported");
                    }
                });
            }
                               }
        );

    }

    void speak() {
        String s = String.valueOf(textEdit.getText());
        // Validating the user's input
        if(s.length()==0) {
            tts.speak("please enter something", TextToSpeech.QUEUE_ADD, null);
        }
        else {
            Log.i("message", s);
            Log.i("message", "about to speak");
            tts.speak(s,
                    TextToSpeech.QUEUE_ADD, null);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        // whenever the app is in background the speach stops
        tts.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //shuting down the speech service so that other applications will be able to use this
        tts.shutdown();
    }
}