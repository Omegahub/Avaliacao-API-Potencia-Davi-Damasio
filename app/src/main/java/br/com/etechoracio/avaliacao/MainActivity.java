package br.com.etechoracio.avaliacao;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import br.com.etechoracio.avaliacao.Service.ProjetoAPIService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class MainActivity extends AppCompatActivity {
    EditText editNum1;
    EditText editNum2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editNum1 = findViewById(R.id.editNum1);
        editNum2 = findViewById(R.id.editNum2);
    }

    private ProjetoAPIService service;

    public void onClick() {
        this.service = new Retrofit.Builder().baseUrl("http://172.16.58.22:8001/api/funcoes/potencia")
                .addConverterFactory(JacksonConverterFactory.create())
                .build()
                .create(ProjetoAPIService.class);


    Call<String> call = this.service.executar(editNum1.getText().toString(), editNum2.getText().toString());
    call.enqueue(new Callback<String>() {
        @Override
        public void onResponse(Call<String> call, Response<String> response) {
           String numeros = response.body();

            Toast.makeText(getApplicationContext(), numeros, Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onFailure(Call<String> call, Throwable t) {
            Log.e("ProjetoAPIService ", "Erro:" + t.getMessage());
        }
    });


    }
}