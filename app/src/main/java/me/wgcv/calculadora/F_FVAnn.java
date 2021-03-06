package me.wgcv.calculadora;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class F_FVAnn extends Fragment {
	int emptyfields = 0,choice = 0;
	EditText fv,cf,r,t,n;
	TextView answer;
	Button calc,info;
	String answer_fv, answer_cf, answer_r, answer_t;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fvann, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		//hide the keyboard initially
		getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		//get Strings etc...
		calc = 	(Button) getActivity().findViewById(R.id.fvann_calc);
		info = (Button) getActivity().findViewById(R.id.fvann_infobutton);
		answer_fv = (String) getActivity().getResources().getString(R.string.answer_fvann);
		answer_cf = (String) getActivity().getResources().getString(R.string.answer_cf);
		answer_r = (String) getActivity().getResources().getString(R.string.answer_r);
		answer_t = (String) getActivity().getResources().getString(R.string.answer_t);
		
		buttonCalc();
		boolean mDualPane=MiscMethods.landscapeChecker(getActivity());
		if(mDualPane == false){buttonInfo();}		
		
		
	}

	public void buttonInfo() {
		info.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
			Intent infos = new Intent();
			infos.setClass(getActivity(), DetailsActivity2.class);
			infos.putExtra("infos", "fvann");
			startActivity(infos);
			}
		});
	}

	public void buttonCalc() {
		calc.setOnClickListener(new OnClickListener() {			
			public void onClick(View v) {
			fv = (EditText) getActivity().findViewById(R.id.fvann_pv);
			cf = (EditText) getActivity().findViewById(R.id.fvann_cf);
			r = (EditText) getActivity().findViewById(R.id.fvann_r);
			t = (EditText) getActivity().findViewById(R.id.fvann_t);
                n= (EditText) getActivity().findViewById(R.id.fvann_n);

                answer = (TextView) getActivity().findViewById(R.id.answer_t);
			if (fv.getText().toString().equals("")){emptyfields ++;choice = 1;}
			if (cf.getText().toString().equals("")){emptyfields ++;choice = 2;}
			if (r.getText().toString().equals("")){emptyfields ++;choice = 3;}					
			if (t.getText().toString().equals("")){emptyfields ++;choice = 4;}
                if (n.getText().toString().equals("")) {emptyfields++;choice = 5;}

				if (emptyfields > 1){MiscMethods.ErrorToast(1);}
			else{
				switch (choice) {
				case 1://fv
					double cf1 = Double.parseDouble(cf.getText().toString());
					double r1 = Double.parseDouble(r.getText().toString());
					double t1 = Double.parseDouble(t.getText().toString());
                    double n1 = Double.parseDouble(n.getText().toString());
					double result1 = (cf1/(r1/n1))*(Math.pow(1+(r1/n1), t1*n1) -1);
					result1 = MiscMethods.rounder(result1, 2);
					answer.setText(answer_fv+result1);
					break;
				case 2://cf
					double fv2 = Double.parseDouble(fv.getText().toString());
					double r2 = Double.parseDouble(r.getText().toString());
					double t2 = Double.parseDouble(t.getText().toString());
                    double n2 = Double.parseDouble(n.getText().toString());

                    double result2 = (fv2*(r2/n2))/(Math.pow(1+(r2/n2), t2*n2) -1);
					result2 = MiscMethods.rounder(result2, 2);
					answer.setText(answer_cf+result2);
					break;
				case 3://r
					MiscMethods.ErrorToast(2);
					break;
				case 4://t
					double fv4 = Double.parseDouble(fv.getText().toString());
					double cf4= Double.parseDouble(cf.getText().toString());
					double r4 = Double.parseDouble(r.getText().toString());
                    double n4 = Double.parseDouble(n.getText().toString());

                    double result4 = ( Math.log( (fv4*(r4/n4)/cf4)+1 ) ) / (n4*( Math.log(1+(r4/n4)) ));
					result4 = MiscMethods.rounder(result4, 2);
                        answer.setText(answer_t+" "+result4);
					break;
                    case 5://n
                        MiscMethods.ErrorToast(2);
                        break;
				default:
					MiscMethods.ErrorToast(3);
					break;
				}//switch ends
			}//else ends
			
				
						
			}			
		});
	}
	
}
