package info.wwwood.intentspractice.presentationlayer.controllers.activities;

import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import info.wwwood.intentspractice.R;

public class GPSActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);
        Button GPS_btLocalizar=(Button) findViewById((R.id.GPS_btLocalizar));
        GPS_btLocalizar.setOnClickListener(this); //IMPLEMENTEM CONTROLADORA DE CLICKS
    }
    public void onClick(View v) {
        Intent intent=null;
        EditText GPS_EtLocalizar=(EditText) findViewById(R.id.GPS_EtLocalizar);

        switch (v.getId()){
            case R.id.GPS_btLocalizar:
                //to do
                Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + GPS_EtLocalizar.getText().toString());
                intent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                 intent.setPackage("com.google.android.apps.maps");
                break;
        }
        startActivity(intent); //engeguem l'activitat
    }
}
