package info.wwwood.intentspractice.presentationlayer.controllers.activities;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import info.wwwood.intentspractice.R;

public class ContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_CONTACTS},
                    1);

            return;
        } else {
            loadContacts();
        }


    }
    private void loadContacts() {
        setContentView(R.layout.activity_contact);
        Cursor cursor=getContentResolver().query(
                ContactsContract.Contacts.CONTENT_URI,
                null,
                ContactsContract.Contacts.HAS_PHONE_NUMBER+" =?",
                new String[]{"1"},
                ContactsContract.Contacts.DISPLAY_NAME+ " Desc"
        );

        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(
                this,
                android.R.layout.simple_list_item_1,
                cursor,
                new String[]{ContactsContract.Contacts.DISPLAY_NAME},
                new int[]{android.R.id.text1},
                1 //1 o 0 sempre 1 perquè el 0 és depecrated
        );

        ListView lvContactos = (ListView) findViewById(R.id.contact_lvContactos);
        lvContactos.setAdapter(simpleCursorAdapter);

        lvContactos.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                       cambiarADialer(id);
                    }
                }
        );
    }
    private void cambiarADialer(long id){
        List<String> telefonos =new ArrayList<String>();
        Cursor cursor=getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null,
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID+"=?",
                new String[]{String.valueOf(id)},
                null
        );
        while (cursor.moveToNext()){
            telefonos.add(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));
        }
        cursor.close();
        if (telefonos.size()>0){
            Intent intent=new Intent();
            intent.putExtra("Telefono",telefonos.get(0));
            setResult(Activity.RESULT_OK, intent);
            finish();
        } else {
            Toast.makeText(this,"No tiene número!",Toast.LENGTH_LONG).show();
            Intent returnIntent = new Intent();
            setResult(Activity.RESULT_CANCELED, returnIntent);
            finish();
        }


    }
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){
        switch (requestCode) {
            case 1: { // 1 perquè ho hem posat com a constant a ActivityCompat.requestPermissions
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    loadContacts();

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {
                    Toast.makeText(this,"Tienes que aceptar los permisos!",Toast.LENGTH_LONG).show();
                    Intent returnIntent = new Intent();
                    setResult(Activity.RESULT_CANCELED, returnIntent);
                    finish();
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
}
