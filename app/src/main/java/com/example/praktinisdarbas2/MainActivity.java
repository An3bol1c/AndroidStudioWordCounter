package com.example.praktinisdarbas2;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private final TextCounter counter = new TextCounter();

    private EditText input;
    private Spinner spinner;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input = findViewById(R.id.textInput);
        spinner = findViewById(R.id.spinner);
        result = findViewById(R.id.textOutput);

        input.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) { updateResult(); }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateResult();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        updateResult();
    }

    private void updateResult() {
        String text = input.getText().toString();
        String label;
        int count;

        switch (spinner.getSelectedItemPosition()) {
            case 0:
                label = "Sentences";
                count = counter.countSentences(text);
                break;
            case 1:
                label = "Words";
                count = counter.countWords(text);
                break;
            case 2:
                label = "Punctuation";
                count = counter.countPunctuation(text);
                break;
            default:
                label = "Numbers";
                count = counter.countNumbers(text);
                break;
        }
        result.setText(getString(R.string.result_format, label, count));
    }
}
