package com.example.ex1;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {

	int a = 10;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		
		final EditText et1 = (EditText) findViewById(R.id.editText1);
		final EditText et2 = (EditText) findViewById(R.id.editText2);

		final TextView t1 = (TextView) findViewById(R.id.textView1);
		
		ImageView p = (ImageView) findViewById(R.id.p);
		p.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				int a = Integer.parseInt(et1.getText().toString());
				int b = Integer.parseInt(et2.getText().toString());
				
				t1.setText((a+b)+"");
				
			}
		});
		
		
		ImageView m  = (ImageView) findViewById(R.id.m);
		m.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				int a = Integer.parseInt(et1.getText().toString());
				int b = Integer.parseInt(et2.getText().toString());
				
				t1.setText((a-b)+"");
				
			}
		});
		
		ImageView x = (ImageView) findViewById(R.id.x);
		x.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				int a = Integer.parseInt(et1.getText().toString());
				int b = Integer.parseInt(et2.getText().toString());
				
				t1.setText((a*b)+"");
				
			}
		});
		
		ImageView d = (ImageView) findViewById(R.id.d);
		d.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				int a = Integer.parseInt(et1.getText().toString());
				int b = Integer.parseInt(et2.getText().toString());
				
				t1.setText((a/b)+"");
				
			}
		});

		
		
		
		
		
		
		
		
		
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up ImageView, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
