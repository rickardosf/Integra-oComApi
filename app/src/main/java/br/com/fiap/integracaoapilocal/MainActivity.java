package br.com.fiap.integracaoapilocal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://10.20.22.17:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitDadosInterface api = retrofit.create(RetrofitDadosInterface.class);
        api.getDados().enqueue(new Callback<Contato>() {
            @Override
            public void onResponse(Call<Contato> call, Response<Contato> response) {
                Contato contato = response.body();
                Toast.makeText(MainActivity.this, contato.getNome(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Contato> call, Throwable t) {
                Toast.makeText(MainActivity.this, "ERRO", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
