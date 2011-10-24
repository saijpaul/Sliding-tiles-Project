/********************************************************************************
* Authors: Seema Saijpaul, Pratibha Natani, Neeraja Budamagunta, Maxwell A. Garvey
*********************************************************************************/

package com.softengg;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
        //set layout of the main page
        setContentView(R.layout.puzzlemain);
        
        // display icons on the main page
        this.imageEquationMode = (ImageView) this.findViewById(R.id.imageEquation);        
        this.imagePuzzleMode = (ImageView) this.findViewById(R.id.imagePuzzle);
        this.imageHelp = (ImageView) this.findViewById(R.id.imageHelp);
        this.imageExit = (ImageView) this.findViewById(R.id.imageExit);
        //set listener on click event of above icons
        imageEquationMode.setOnClickListener(myListener);
        imagePuzzleMode.setOnClickListener(myListener);
        imageHelp.setOnClickListener(myListener);
        imageExit.setOnClickListener(myListener);
    }
    
    View.OnClickListener myListener = new View.OnClickListener(){
        public void  onClick  (View  v){
        	//switch performs action according to selected icon
        	switch(v.getId()){
        	
	    	case R.id.imageEquation:
	    		// switch to equation board
	    		Intent msg = new Intent(SlidingTilePuzzleActivity.this,EquationBoardActivity.class);
	    		SlidingTilePuzzleActivity.this.startActivity(msg);
	    	break;
	    	case R.id.imagePuzzle:
	    		
	    	break;
	    	case R.id.imageExit:
	    	
	    		finish();	
		    break;
		    //setup the help 
	    	case R.id.imageHelp:
	            // prepare the alert box
	            AlertDialog.Builder alertbox = new AlertDialog.Builder(SlidingTilePuzzleActivity.this);
	            // set the message to display
	            alertbox.setMessage("HELP: The Sliding Tiles Puzzle allows you to play in two modes." +
	            		"" + 
	            		"Mode 1 : You define basic mathematic equations using digits 0-9 and the addition, " +
	            		"subtracton and multiplication operators. Equations can be formed either horizontally " +
	            		" from left to right or vertically from top to bottom. " +
	            		"Equations must include required operators (i.e. 9=9 is not allowed)." +
	            		"A candidate equation is submitted by dragging a finger across the screen." +
	            		"If the equation is valid, then you earn points cumutatively. You can form new " +
	            		"equations by moving tiles around the board.");
	 
	            CharSequence ok = "OK";
	            // set a positive/yes button and create a listener
	            alertbox.setPositiveButton(ok, new DialogInterface.OnClickListener() {
	 
	                // close the help when the button is clicked
	                public void onClick(DialogInterface arg0, int arg1) {
	                	arg0.dismiss();
	                }
	            });
	 
	            // display box
	            alertbox.show();
	   	    	break;
	   }
    }};
}