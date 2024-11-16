package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONObject;

import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private EditText editTextName, editTextPhone, editTextEmail;
    private Button sendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Código para teste de erro - já corrigido
        String text = null;
        if (text != null) {
            Log.d("MainActivity", text.toString());
        } else {
            Log.d("MainActivity", "Text is null");
        }
        // Fim do código para teste de erro

        editTextName = findViewById(R.id.editTextText);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextEmail = findViewById(R.id.editTextTextEmailAddress);
        sendButton = findViewById(R.id.sendButton);

        Log.i(TAG, "App iniciado e MainActivity carregada");

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Botão Enviar pressionado");
                saveData();
                clearFields();
                navigateToMainActivity2();
            }
        });
    }

    private void saveData() {
        try {
            // Criação do objeto JSON
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", editTextName.getText().toString());
            jsonObject.put("phone", editTextPhone.getText().toString());
            jsonObject.put("email", editTextEmail.getText().toString());

            // Salvando no arquivo JSON
            String jsonString = jsonObject.toString();
            FileOutputStream fos = openFileOutput("userData.json", MODE_PRIVATE);
            fos.write(jsonString.getBytes());
            fos.close();

            Log.i(TAG, "Dados salvos com sucesso: " + jsonString);
        } catch (Exception e) {
            Log.e(TAG, "Erro ao salvar dados", e);
        }
    }

    private void clearFields() {
        editTextName.setText("");
        editTextPhone.setText("");
        editTextEmail.setText("");
        Log.v(TAG, "Campos limpos após salvar os dados");
    }

    private void navigateToMainActivity2() {
        Log.d(TAG, "Navegando para MainActivity2");
        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
        startActivity(intent);
    }
}
