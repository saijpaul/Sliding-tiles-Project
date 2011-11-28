/********************************************************************************
* Authors: Neeraja Budamagunta,Pratibha Natani, Seema Saijpaul, Maxwell A. Garvey
*********************************************************************************/

package com.softengg;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import android.app.Activity	;

import android.view.MotionEvent;
import android.view.View;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class NumberPuzzleActivity extends Activity {
	private ImageView imageHome = null;
    private NumberGameView numberView;
    private TextView textViewToChange;
    private String gridSizeSelected = null;

  /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.numberpuzzle);
    
        //generates tiles    
        numberView = (NumberGameView) findViewById(R.id.numbergame_view);
        numberView.requestFocus();
        
        if (bundle == null) {
        	int gridsize=3;
        	System.out.println("calling NumberGameView");
        	numberView.newGame(null,gridsize);	
        	
         } 
        //shows home icon on puzzle page to go back
        this.imageHome = (ImageView) this.findViewById(R.id.imageHome);
        imageHome.setOnClickListener(myListener);
        
        Spinner spinner = (Spinner) findViewById(R.id.gridSelect);    
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.grid_size, android.R.layout.simple_spinner_item);    
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);    
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new MyOnItemSelectedListener());
     }
    View.OnClickListener myListener = new View.OnClickListener(){
        public void  onClick  (View  v){
        	//switch performs action according to selected icon
        	switch(v.getId()){
        	
	    	case R.id.imageHome:
	    		finish();
	    		break;
	
	   }
    }};
    
    
    
    public class MyOnItemSelectedListener implements OnItemSelectedListener { 
    	int gridsize=0;
    	public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {    
    		gridSizeSelected =  parent.getItemAtPosition(pos).toString();
    		StringTokenizer st = new StringTokenizer(gridSizeSelected,"x;");
        	while(st.hasMoreTokens()){
        		gridSizeSelected = st.nextToken();
        	}
        	
        	gridsize = Integer.parseInt(gridSizeSelected);
        	if(gridsize!=3){
        		System.out.println("grid size selected is : " + gridsize);
    		numberView.newGame(null,gridsize);
        	}
    		
    		  
    }    
    public void onNothingSelected(AdapterView<?> parent) {      
    	// Do nothing.    
    	}
    }
    
    //move tiles using touch
    @Override public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);

        int action = event.getAction();

        switch (action) {
            case MotionEvent.ACTION_DOWN: {
            	System.out.println("action down");
            	numberView.pickTile(event.getX(), event.getY());
                return true;
            }
            case MotionEvent.ACTION_MOVE: {
            	System.out.println("action move");
            	numberView.dragTile(event.getX(), event.getY());
                return true;
            }
            case MotionEvent.ACTION_UP: {
            	System.out.println("action up");
                boolean moved = numberView.dropTile(event.getX(), event.getY());
                //add logic for chking state of game for win state
                if (moved)
                {
                boolean winState = numberView.chkWinstate();
                if (winState)
                		{
        			     System.out.println("wistate is true");
                			Toast.makeText(this, "Yeeeepiii U WIN!!!", Toast.LENGTH_SHORT).show();
                		}
                else 
                		{
        			     System.out.println("keep trying...");
                	//Toast.makeText(this, "Keep trying...", Toast.LENGTH_SHORT).show();
                		}
                } 
                return true;
            }
        }
        
        return false;
    }   


}
