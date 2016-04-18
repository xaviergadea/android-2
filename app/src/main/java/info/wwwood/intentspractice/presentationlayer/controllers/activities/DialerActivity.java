package info.wwwood.intentspractice.presentationlayer.controllers.activities;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import info.wwwood.intentspractice.R;
import info.wwwood.intentspractice.presentationlayer.widgets.EditTextNoKB;

public class DialerActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialer);
        TableLayout dialer_tlButtons = (TableLayout) findViewById(R.id.dialer_tlButtons);
        int numRows = dialer_tlButtons.getChildCount();
        for (int iFila = 0; iFila < numRows; iFila++) {
            TableRow fila = (TableRow) dialer_tlButtons.getChildAt(iFila);
            for (int iColumna = 0; iColumna < fila.getChildCount(); iColumna++) {
                View view = fila.getChildAt(iColumna);
                if (view instanceof Button || view instanceof ImageButton) {
                    view.setOnClickListener(this);
                }
            }
        }
    }

    @Override
    public void onClick(View v) {

        EditTextNoKB dialer_etNumber = (EditTextNoKB) findViewById(R.id.dialer_etNumber);
        if (v instanceof Button) {
            dialer_etNumber.setText(dialer_etNumber.getText().toString() + ((Button) v).getText().toString());
        } else if (v instanceof ImageButton) {
            Intent intent = null;
            switch (v.getId()) {
                case R.id.dialer_ibtCall:
                   llamar();
                    break;
                case R.id.dialer_ibtSearch:
                    intent=new Intent(this,ContactActivity.class);
                    startActivityForResult(intent,1);
                    break;
            }

        }
    }
    private void llamar(){
        EditTextNoKB dialer_etNumber = (EditTextNoKB) findViewById(R.id.dialer_etNumber);
        Intent intent = null;
        Uri uri = Uri.parse("tel:" + dialer_etNumber.getText().toString());
        intent = new Intent(Intent.ACTION_CALL, uri);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    1);

            return;
        }
        startActivity(intent);
    }
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){
        switch (requestCode) {
            case 1: { // 1 perquè ho hem posat com a constant a ActivityCompat.requestPermissions
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    llamar();

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {
                    Toast.makeText(this,"ooooooh cada vez que no me das el permiso dios mata a un gatito",Toast.LENGTH_LONG).show();
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                ((EditText) findViewById(R.id.dialer_etNumber)).setText(data.getStringExtra("Telefono"));
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }
}
