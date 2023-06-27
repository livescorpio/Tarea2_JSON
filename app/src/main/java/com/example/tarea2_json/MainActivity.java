package com.example.tarea2_json;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import webService.Asynchtask;
import webService.WebService;

public class MainActivity extends AppCompatActivity implements Asynchtask {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
    public void mostrarInfo(View view){

        Bundle bundle = this.getIntent().getExtras();
        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService(" https://jsonplaceholder.typicode.com/users"
                ,datos, MainActivity.this, MainActivity.this);
        ws.execute("GET");
    }

    @Override
    public void processFinish(String result) throws JSONException {
        TextView info= (TextView) findViewById(R.id.tvMostrarJson);
        String listaJson=" ";
        JSONArray JSONdatos= new JSONArray(result);

        for (int i = 0; i < JSONdatos.length(); i++) {
            JSONObject usuarios = JSONdatos.getJSONObject(i);
            String nombre = usuarios.getString("name");
            String nombreUsuArio = usuarios.getString("username");
            String direccion = usuarios.getString("email");
            String telefono = usuarios.getString("phone");
            String SitioWeb = usuarios.getString("website");
            listaJson = listaJson + " (" + i +") "+ " " + nombre +  ",  " + nombreUsuArio+",  "
                    + direccion+",  "+telefono+",  "+SitioWeb+"\n";
            final int contador = i;
            final String finalLstUser = listaJson;
            new android.os.Handler().postDelayed(new Runnable() {
                public void run() {
                    info.setText(finalLstUser);

                    if (contador < JSONdatos.length() - 1) {
                        info.append("\n");
                    }
                }
            }, (i + 1) * 500);
        }


    }
}