package info.wwwood.intentspractice.presentationlayer.controllers.activities;

import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.provider.Browser;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import info.wwwood.intentspractice.R;

public class WebActivity extends AppCompatActivity implements View.OnClickListener  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        Button web_btBuscar=(Button) findViewById((R.id.web_btBuscar));
        web_btBuscar.setOnClickListener(this); //IMPLEMENTEM CONTROLADORA DE CLICKS

        Button web_btNavegar=(Button) findViewById((R.id.web_btNavegar));
        web_btNavegar.setOnClickListener(this); //IMPLEMENTEM CONTROLADORA DE CLICKS



    }

    @Override
    public void onClick(View v) {
        Intent intent=null;
        EditText web_EtUrl=(EditText) findViewById(R.id.web_EtUrl);

        switch (v.getId()){
            case R.id.web_btNavegar:
                //to do
                Uri uri=Uri.parse("http://"+web_EtUrl.getText().toString());

                intent = new Intent(Intent.ACTION_VIEW, uri);
                break;
            case R.id.web_btBuscar:
                // to do
                intent=new Intent(Intent.ACTION_WEB_SEARCH);
                intent.putExtra(SearchManager.QUERY,web_EtUrl.getText().toString());
                break;
        }
        startActivity(intent); //engeguem l'activitat
    }
}
