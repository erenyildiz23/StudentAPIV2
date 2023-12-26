package com.example.shared_preference;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    TextView textView;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);
        Button saveButton = findViewById(R.id.saveButton);
        Button loadButton = findViewById(R.id.loadButton);

        sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editText.getText().toString();
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putString("key", text);
                myEdit.apply();
            }
        });

        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = sharedPreferences.getString("key", "Hiçbir şey kaydedilmedi");
                textView.setText(value);
            }
        });
    }
}
