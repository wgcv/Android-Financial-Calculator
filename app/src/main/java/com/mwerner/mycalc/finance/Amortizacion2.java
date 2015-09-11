package com.mwerner.mycalc.finance;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.text.DecimalFormat;

public class Amortizacion2 extends Activity {

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Eliminamos el titulo de la ventana
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_amortizacion2);


        LinearLayout ll = (LinearLayout) findViewById(R.id.linear);

        TextView titulo= new TextView(this);
        titulo.setText("TABLA DE AMORTIZACION");
        ll.addView(titulo);
        //Llamamos al metodo dibujarTabla (AnchoBorde, Filas, Columnas, colorBorde)
        TableLayout etiquetaTabla = dibujarTabla(2, "#696969");
        ll.addView(etiquetaTabla);


    }
    /*
     * Método dibujarTabla
     * Devuelve una TableLayout con borde
     * recibe:
     * 	tamaño del borde - int
     *  numero de filas - int
     *  numero de columnas - int
     *  color en Hexadecimal #FFFFFF - String
     */
    public TableLayout dibujarTabla(int tamBorde, String colorBorde){
        Amortizacion amor = null;
        DecimalFormat df = new DecimalFormat("0.00");
        TableLayout tabla = new TableLayout(this);

        TableRow fila = new TableRow(this);

        int ancho=(getWindowManager().getDefaultDisplay().getWidth()/5)-(tamBorde+(tamBorde/(5)));
        //No se porqué pero si le resto uno funciona...
        ancho--;

        System.out.println("Tamaño ventana: "+getWindowManager().getDefaultDisplay().getWidth()+
                " ancho:"+ancho);
        int contadorColumnas=0;
        int contadorFilas=0;

        String[][] tabl = new String[500][500];

        double a=0,b=0,c = 0,d=0;
        for (int i = 0; i <= amor.fil+1; i++) {
            for (int j = 0; j < 5; j++) {

                //Si Ya ha dibujado la cantidad de columnas
                if(contadorColumnas==5){
                    tabla.addView(fila);
                    fila = new TableRow(this);
                    contadorColumnas=0;
                    contadorFilas++;

                }

                //Definimos los bordes de la tabla
                RelativeLayout borde = new RelativeLayout(this);
                //Dibuja los de arriba y la izquierda siempre
                borde.setPadding(tamBorde,tamBorde,0,0);
                //Pero
                //Si ya es la ultima columna de la fila...

                //Dibuja los de arriba a la derecha e izquierda.
                borde.setPadding(tamBorde, tamBorde, tamBorde, 0);

                //Si Es la ultima fila

                //Dibuja arriba, izquierda y abajo
                borde.setPadding(tamBorde,tamBorde,0,tamBorde);
                //Si ademas de ser la ultima fila es la ultima columna

                //Dibuja todos los lados
                borde.setPadding(tamBorde,tamBorde,tamBorde,tamBorde);


                //Color del borde.
                borde.setBackgroundColor(Color.parseColor(colorBorde));

                //Ahora el texto..
                TextView texto = new TextView(this);


                tabl[0][0]="PERIODO";
                tabl[0][1]="SALDO";
                tabl[0][2]="INTERES";
                tabl[0][3]="PAGO";
                tabl[0][4]="ABONO";


                tabl[i+1][0]=String.valueOf(i+1);
                int e = Integer.parseInt(tabl[i+1][0]);


                a=amor.c1;
                b=amor.c1*0.08;
                c=amor.r3;
                d=amor.r3-b;

                tabl[1][1]=String.valueOf(df.format(a));


                tabl[1][2]=String.valueOf(df.format(b));


                tabl[1][3]=String.valueOf(df.format(c));


                tabl[1][4]=String.valueOf(df.format(d));

                for (int k = 0; k < e; k++) {

                    a=a-d;
                    b=a*0.08;
                    d=c-b;

                    tabl[i+2][1]=String.valueOf(df.format(a));

                    tabl[i+2][2]=String.valueOf(df.format(b));


                    tabl[i+2][3]=String.valueOf(df.format(c));


                    tabl[i+2][4]=String.valueOf(df.format(d));
                }

                texto.setText(tabl[i][j]);
                texto.setWidth(ancho);
                texto.setGravity(Gravity.CLIP_HORIZONTAL);
                texto.setPadding(2, 2, 2, 2);
                //Importante el color, porque si no se verá de color del borde!!
                texto.setBackgroundColor(Color.BLACK);
                texto.setTextColor(Color.WHITE );
                //Al borde le añadimos el texto
                borde.addView(texto);

                //Y a la fila el borde con el texto
                fila.addView(borde);
                contadorColumnas++;
            }
        }


        return tabla;
    }
}