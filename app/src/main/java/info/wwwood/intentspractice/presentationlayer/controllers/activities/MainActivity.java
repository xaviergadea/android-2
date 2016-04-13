package info.wwwood.intentspractice.presentationlayer.controllers.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import info.wwwood.intentspractice.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button main_btWeb=(Button) findViewById((R.id.main_btWeb));
        main_btWeb.setOnClickListener(this); //IMPLEMENTEM CONTROLADORA DE CLICKS

        Button main_btGPS=(Button) findViewById((R.id.main_btGPS));
        main_btGPS.setOnClickListener(this); //IMPLEMENTEM CONTROLADORA DE CLICKS

        //TextView main_tvTexto=(TextView) findViewById(R.id.main_tvTexto);  //devuelve una referencia de un objeto tipo view. Se hace un cast para transformar a Texview(el que està entre parèntesis)

      //  EditText main_editText=(EditText) findViewById(R.id.main_editText);
        //main_tvTexto.setText("HOLA MUNDO");

        /*Persona p1=new Persona();
        Persona p2=new Ingles();
        Log.d("Persona Sueldo",String.valueOf(p1.sueldo)); // 20000
        Log.d("Persona Sueldo",String.valueOf(p1.hablar())); // Hola

        Log.d("Persona Sueldo",String.valueOf(p2.sueldo)); //20000 - Si és propietat o atribut no es sobrescriuen. S'executa la classe de què depens
        Log.d("Ingles Sueldo",String.valueOf(((Ingles)p2).sueldo)); // 50000 - fas la conversió de l'atribut a tipus Ingles
        Log.d("Ingles Sueldo",String.valueOf(p2.hablar())); // Hello - Si és mètode es sobreescriu i executes tal i com has nascut.
    */
    }
   /* public void asignar(View v){ //SI ES POSA ONCLICK A LA VISTA AMB EL NOM DE LA FUNCIÓ
        EditText main_editText=(EditText) findViewById(R.id.main_editText);
        TextView main_tvTexto=(TextView) findViewById(R.id.main_tvTexto);  //devuelve una referencia de un objeto tipo view. Se hace un cast para transformar a Texview(el que està entre parèntesis)
        main_tvTexto.setText(main_editText.getText().toString());
    }*/

    @Override
    public void onClick(View v) {
        /*EditText main_editText=(EditText) findViewById(R.id.main_editText);
        TextView main_tvTexto=(TextView) findViewById(R.id.main_tvTexto);  //devuelve una referencia de un objeto tipo view. Se hace un cast para transformar a Texview(el que està entre parèntesis)
        main_tvTexto.setText(main_editText.getText().toString());*/

        Intent intent=null;

        switch (v.getId()){
            case R.id.main_btWeb:
                intent=new Intent(this,WebActivity.class); // li dius quina classe s'ha d'executar al clickar el botó main_btWeb
                break;
            case R.id.main_btGPS:
                intent=new Intent(this,GPSActivity.class); // li dius quina classe s'ha d'executar al clickar el botó main_btWeb
                break;

        }
        startActivity(intent); //engeguem l'activitat
    }


}

/*class Persona {
    public int sueldo=20000;
    public String hablar(){
        return "Hola";
    }
}
class Ingles extends Persona {
    public int sueldo=50000;
    @Override
    public String hablar(){
        return "Hello";
        // return super.hablar(); executaries el mètode de la classe pare
    }
}*/
