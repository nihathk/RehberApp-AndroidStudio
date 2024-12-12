package com.example.rehber_uygulamasi_final_odevi;

import android.content.DialogInterface;

import android.os.Bundle;
import android.text.InputType;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnCreateContextMenuListener {
    ListView kisiler;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        kisiler = findViewById(R.id.kisiler);
        KisileriAktar(kisiler);
        registerForContextMenu(kisiler);
        kisiler.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                if (v.getId() == R.id.kisiler) {
                    AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                    menu.setHeaderTitle(kisiler.getItemAtPosition(info.position).toString());
                    menu.add(0, 0, 0, "Sil");
                    menu.add(0, 1, 0, "Düzenle");
                }
            }
        });
        Button addContactBtn = (Button) findViewById(R.id.KisiEkleBtn);
        addContactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Kişi Ekle");
                LinearLayout layout = new LinearLayout(MainActivity.this);
                layout.setOrientation(LinearLayout.VERTICAL);
                final EditText adEditText = new EditText(MainActivity.this);
                adEditText.setHint("Ad Soyad");
                final EditText numaraEditText = new EditText(MainActivity.this);
                numaraEditText.setHint("Numara");
                numaraEditText.setInputType(InputType.TYPE_CLASS_PHONE);
                final EditText emailEditText = new EditText(MainActivity.this);
                emailEditText.setHint("E-Posta");
                emailEditText.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                final EditText adresEditText = new EditText(MainActivity.this);
                adresEditText.setHint("Adres");
                final EditText websiteEditText = new EditText(MainActivity.this);
                websiteEditText.setHint("Website");
                layout.addView(adEditText);
                layout.addView(numaraEditText);
                layout.addView(emailEditText);
                layout.addView(adresEditText);
                layout.addView(websiteEditText);
                builder.setView(layout);
                builder.setNegativeButton("Ekle", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (adEditText.getText().toString().trim() != "" && numaraEditText.getText().toString().trim() != "") {
                            Veritabani veritabani = new Veritabani(MainActivity.this);
                            veritabani.KisiEkle(adEditText.getText().toString().trim(), numaraEditText.getText().toString().trim(), emailEditText.getText().toString().trim(), adresEditText.getText().toString().trim(), websiteEditText.getText().toString().trim());
                            KisileriAktar(kisiler);
                        }
                    }
                });
                builder.setPositiveButton("İptal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }

    public boolean onContextItemSelected(MenuItem item) {
        boolean donus;
        switch (item.getItemId()) {

            case 0:
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                String secili = kisiler.getItemAtPosition(info.position).toString();
                String[] dizi = secili.split("-");
                final long id = Long.parseLong(dizi[0].trim());
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
                alertBuilder.setTitle("Kişi Sil");
                alertBuilder.setMessage("\"" + secili + "\" kişisini silmek istediğinize emin misiniz?");
                alertBuilder.setNegativeButton("Evet", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Veritabani veritabani = new Veritabani(MainActivity.this);
                        veritabani.KisiSil(id);
                        KisileriAktar(kisiler);
                    }
                });
                alertBuilder.setPositiveButton("Hayır", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog alertDialog = alertBuilder.create();
                alertDialog.show();
                break;
            case 1:
                AdapterView.AdapterContextMenuInfo inf = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                String seciliVeri = kisiler.getItemAtPosition(inf.position).toString();
                String[] diziVeri = seciliVeri.split("-");
                final long lId = Long.parseLong(diziVeri[0].trim());
                final String eskiAd = diziVeri[1].trim();
                final String eskiTelefon = diziVeri[2].trim();
                final String eskiEmail = diziVeri[3].trim();
                final String eskiAdres = diziVeri[4].trim();
                final String eskiWebsite = diziVeri[5].trim();
                AlertDialog.Builder bAlert = new AlertDialog.Builder(this);
                bAlert.setTitle("Kişi Düzenle");
                LinearLayout lNesne = new LinearLayout(this);
                lNesne.setOrientation(LinearLayout.VERTICAL);
                final EditText yeniAd = new EditText(this);
                yeniAd.setHint("Ad Soyad");
                yeniAd.setText(eskiAd);
                final EditText yeniTelefon = new EditText(this);
                yeniTelefon.setHint("Telefon");
                yeniTelefon.setInputType(InputType.TYPE_CLASS_PHONE);
                yeniTelefon.setText(eskiTelefon);
                final EditText yeniEmail = new EditText(this);
                yeniEmail.setHint("E-Posta");
                yeniEmail.setInputType(InputType.TYPE_TEXT_VARIATION_WEB_EMAIL_ADDRESS);
                yeniEmail.setText(eskiEmail);
                final EditText yeniAdres = new EditText(this);
                yeniAdres.setHint("Adres");
                yeniAdres.setText(eskiAdres);
                final EditText yeniWebsite = new EditText(this);
                yeniWebsite.setHint("Website");
                yeniWebsite.setText(eskiWebsite);
                lNesne.addView(yeniAd);
                lNesne.addView(yeniTelefon);
                lNesne.addView(yeniEmail);
                lNesne.addView(yeniAdres);
                lNesne.addView(yeniWebsite);
                bAlert.setView(lNesne);
                bAlert.setNegativeButton("Düzenle", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (yeniAd.getText().toString().trim() != "" && yeniTelefon.getText().toString().trim() != "") {
                            Veritabani veritabani = new Veritabani(MainActivity.this);
                            veritabani.KisiDuzenle(lId, yeniAd.getText().toString().trim(), yeniTelefon.getText().toString().trim(), yeniEmail.getText().toString().trim(), yeniAdres.getText().toString().trim(), yeniWebsite.getText().toString().trim());
                            KisileriAktar(kisiler);
                        }
                    }
                });
                bAlert.setPositiveButton("İptal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog Alert = bAlert.create();
                Alert.show();
                break;
            default:
                donus = false;
                break;
        }
        donus = true;
        return donus;
    }

    public void KisileriBosalt(ListView lv) {
        List<String> bos = new ArrayList<String>();
        ArrayAdapter<String> bosAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, bos);
        lv.setAdapter(bosAdapter);
    }

    public void KisileriAktar(ListView lv) {
        KisileriBosalt(lv);
        Veritabani veritabani = new Veritabani(this);
        List<String> veriler = veritabani.KisileriListele();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, veriler);
        lv.setAdapter(adapter);

    }
}