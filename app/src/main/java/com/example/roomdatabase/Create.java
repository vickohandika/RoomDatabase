package com.example.roomdatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Create extends AppCompatActivity {
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        // initiate pemanggilan Room database
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "barangdb").build();
        final EditText etNamaBarang = findViewById(R.id.et_namabarang);
        final EditText etMerkBarang = findViewById(R.id.et_merkbarang);
        final EditText etHargaBarang = findViewById(R.id.et_hargabarang);
        Button btSubmit = findViewById(R.id.bt_submit);

        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Barang b = new Barang();
                b.setHargaBarang(etHargaBarang.getText().toString());
                b.setMerkBarang(etMerkBarang.getText().toString());
                b.setNamaBarang(etNamaBarang.getText().toString());
                insertData(b);
            }
        });
    }

    private void insertData(final Barang barang){
        new AsyncTask<Void, Void, Long>(){
            @Override
            protected Long doInBackground(Void... voids) {
            // melakukan proses insert data
                long status = db.barangDAO().insertBarang(barang);
                return status;
            }
            @Override
            protected void onPostExecute(Long status) {
                Toast.makeText(Create.this, "status row "+status, Toast.LENGTH_SHORT).show();
            }
        }.execute();
    }
}
