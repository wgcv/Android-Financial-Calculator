package me.wgcv.calculadora;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Amortizacion extends Activity {
    public EditText j;
    public EditText m;
    public EditText i;
    public EditText c;
    public EditText n;
    public Button tb;
    TextView res;
    public static double  n1, c1, i1, j1, m1, r, r2,r3,	fil;
    public int cont=0;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amortizacion);
        j = (EditText)findViewById(R.id.txtJ);
        m = (EditText)findViewById(R.id.txtM);
        n = (EditText)findViewById(R.id.txtA);
        c = (EditText)findViewById(R.id.txtVN);
        //i = (EditText)findViewById(R.id.txtI);
        res = (TextView)findViewById(R.id.lbRes);
        tb = (Button)findViewById(R.id.btTabla);
     //   bTabla();
    }

    public void bAct(View view){

        j.setEnabled(true);
        m.setEnabled(true);
        i.setEnabled(false);
        cont=1;
    }

    public void bCalc(View view){

        c1= Double.valueOf(c.getText().toString());
        n1= Double.valueOf(n.getText().toString());

            j1=Double.valueOf(j.getText().toString());
            m1=Double.valueOf(m.getText().toString());

            r = 1-(Math.pow((1+j1/m1),-n1*m1));
            r2=r/(j1/m1);
            r3=c1/r2;
            res.setText(Double.toString(r3));
            fil=n1*m1;
            cont=0;

        tb.setEnabled(true);
    }

    public void bTabla(View view){

        tb.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                startActivity(new Intent(Amortizacion.this, Amortizacion2.class));

            }
        });

    }
    @Override
    public void onBackPressed() {
      startActivity(new Intent(Amortizacion.this, Main.class));

    }
}
