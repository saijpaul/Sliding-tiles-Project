/********************************************************************************
* Authors: Seema Saijpaul, Pratibha Natani, Neeraja Budamagunta, Maxwell A. Garvey
*********************************************************************************/

package com.softengg;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class SlidingTilePuzzleActivity extends Activity {
	
	private ImageView imageEquationMode = null;
	private ImageView imagePuzzleMode = null;
	private ImageView imageHelp = null;
	private ImageView imageExit = null;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.puzzlemain);
        
        this.imageEquationMode = (ImageView) this.findViewById(R.id.imageEquation);        
        this.imagePuzzleMode = (ImageView) this.findViewById(R.id.imagePuzzle);
        this.imageHelp = (ImageView) this.findViewById(R.id.imageHelp);
        this.imageExit = (ImageView) this.findViewById(R.id.imageExit);
        
        imageEquationMode.setOnClickListener(myListener);
        imagePuzzleMode.setOnClickListener(myListener);
        imageHelp.setOnClickListener(myListener);
        imageExit.setOnClickListener(myListener);
    }
    
    View.OnClickListener myListener = new View.OnClickListener(){
        public void  onClick  (View  v){
        	switch(v.getId()){
        	
	    	case R.id.imageEquation:
	    		Intent msg = new Intent(SlidingTilePuzzleActivity.this,EquationBoardActivity.class);
	    		SlidingTilePuzzleActivity.this.startActivity(msg);
	    	break;
	    	case R.id.imagePuzzle:
	    		
	    	break;
	    	case R.id.imageExit:
	    	
	    		finish();	
		    break;
	    	case R.id.imageHelp:
	   	    	break;
	   }
    }};
}