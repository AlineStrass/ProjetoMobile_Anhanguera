package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.FileInputStream;

public class MainActivity2 extends AppCompatActivity {

    private static final String TAG = "MainActivity2";
    private TextView textViewDisplay;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        textViewDisplay = findViewById(R.id.textViewDisplay);
        backButton = findViewById(R.id.buttonBack);

        Log.i(TAG, "MainActivity2 carregada");

        loadData();

        // Configuração do botão "Voltar"
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Botão Voltar pressionado");
                navigateBack();
            }
        });
    }

    private void loadData() {
        try {
            // Lendo o arquivo JSON
            FileInputStream fis = openFileInput("userData.json");
            int size = fis.available();
            byte[] buffer = new byte[size];
            fis.read(buffer);
            fis.close();

            // Convertendo para string e objeto JSON
            String jsonString = new String(buffer, "UTF-8");
            JSONObject jsonObject = new JSONObject(jsonString);

            // Exibindo os dados no TextView
            String displayText = "Nome: " + jsonObject.getString("name") + "\n" +
                    "Telefone: " + jsonObject.getString("phone") + "\n" +
                    "Email: " + jsonObject.getString("email");
            textViewDisplay.setText(displayText);

            Log.i(TAG, "Dados carregados com sucesso: " + jsonString);
        } catch (Exception e) {
            Log.e(TAG, "Erro ao carregar dados", e);
            textViewDisplay.setText("Erro ao carregar os dados.");
        }
    }

    private void navigateBack() {
        Log.d(TAG, "Navegando de volta para MainActivity");
        Intent intent = new Intent(MainActivity2.this, MainActivity.class);
        startActivity(intent);
    }
}
