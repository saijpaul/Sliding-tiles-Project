/********************************************************************************
* Authors: Seema Saijpaul, Pratibha Natani, Neeraja Budamagunta, Maxwell A. Garvey
*********************************************************************************/

package com.softengg;

import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableRow;

public class SlidingTilePuzzleActivity extends Activity {
	
	private GifDecoderView imageEquationMode;
	private ImageView imagePuzzleMode = null;
	private ImageView imageHelp = null;
	private GifDecoderView gifView;
	//private int idofView;
	
		
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        InputStream stream = null;
        try {
            stream = getAssets().open("equationbutton.gif");
        } catch (IOException e) {
            e.printStackTrace();
        }
        InputStream streams = null;
        try {
            streams = getAssets().open("puzzlebutton.gif");
        } catch (IOException e) {
            e.printStackTrace();
        }
        setContentView(R.layout.puzzlemain);
//        gifView = (GifDecoderView) findViewById(R.id.anim_view);
//        System.out.println("gif"+gifView);
   //     gifView.requestFocus();
     // let your LinearLayout have an id of 'container'
       
        
        LinearLayout container = (LinearLayout) findViewById(R.id.lay1);
        GifDecoderView gifView = new GifDecoderView(this,stream);
        GifDecoderView gifView1 = new GifDecoderView(this,streams);
        
        /* Create a new row to be added. */
        TableRow tr = new TableRow(this);
        tr.setLayoutParams(new LayoutParams(
                        LayoutParams.WRAP_CONTENT,
                        LayoutParams.WRAP_CONTENT));
        tr.addView(gifView);
        gifView1.setPadding(6,0,0,0);
        tr.addView(gifView1);
        TableRow tr1 = new TableRow(this);
        tr1.setGravity(Gravity.CENTER);
        imageHelp = new ImageView(this);
        imageHelp.setImageResource(R.drawable.helpbutton);
        imageHelp.setAdjustViewBounds(true);
        imageHelp.setMaxWidth(50);
        imageHelp.setMaxHeight(50);
        imageHelp.setMinimumWidth(50);
        imageHelp.setMinimumHeight(50);
        tr1.setPadding(200, 10,0,0);
       // imageHelp.setssetPadding(40, 0, 0,0);
        tr1.addView(imageHelp);
        /* Add row to TableLayout. */
        container.addView(tr,new LinearLayout.LayoutParams(
            LayoutParams.WRAP_CONTENT,
            LayoutParams.WRAP_CONTENT));
        /* Add row to TableLayout. */
        container.addView(tr1,new LinearLayout.LayoutParams(
            LayoutParams.WRAP_CONTENT,
            LayoutParams.WRAP_CONTENT));
//        
        
        
       // gifView.playGif(stream);
//        GifMovieView view = new GifMovieView(this, stream);
  //      GifDecoderView view = new GifDecoderView(this, stream);
        
//        LinearLayout board = new LinearLayout(SlidingTilePuzzleActivity.this);
//        LayoutInflater inflater = getLayoutInflater();
//        
//        board.addView(inflater.inflate(view.getId(), null));
//        this.setContentView(board);
//        //set layout of the main page
        
        
        // display icons on the main page
        this.imageEquationMode = (GifDecoderView) this.findViewById(R.id.imageEquation);
        
        System.out.println("equation mode"+imageEquationMode);
       // this.imagePuzzleMode = (ImageView) this.findViewById(R.id.imagePuzzle);
        //this.imageHelp = (ImageView) this.findViewById(R.id.imageHelp);

        //set listener on click event of above icons
       // gifView.setOnClickListener(myListener);
        
        gifView.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

	    		Intent msg = new Intent(SlidingTilePuzzleActivity.this,EquationBoardActivity.class);
	    		
	    		SlidingTilePuzzleActivity.this.startActivity(msg);

            }

        });
//        idofView = gifView.getId();
        gifView1.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

	    		Intent msg = new Intent(SlidingTilePuzzleActivity.this,NumberPuzzleActivity.class);
	    		SlidingTilePuzzleActivity.this.startActivity(msg);

            }

        });

        //imagePuzzleMode.setOnClickListener(myListener);
//        imageHelp.setOnClickListener(myListener);
        imageHelp.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

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

            }

        });

    	   
           

    }
    
   
    
//    View.OnClickListener myListener = new View.OnClickListener(){
//    //	final int idd = gifView.getId();
//        public void  onClick  (View  v){
//        	//switch performs action according to selected icon
//        	switch(v.getId()){
//        	
////	    	case idd:
////	    		// switch to equation board
////	    		Intent msg = new Intent(SlidingTilePuzzleActivity.this,EquationBoardActivity.class);
////	    		SlidingTilePuzzleActivity.this.startActivity(msg);
//	    //	break;
////	    	case R.id.imagePuzzle:
////	    		
////	    	break;
//		    //setup the help 
//	    	case R.id.imageHelp:
//	            // prepare the alert box
//	            AlertDialog.Builder alertbox = new AlertDialog.Builder(SlidingTilePuzzleActivity.this);
//	            // set the message to display
//	            alertbox.setMessage("HELP: The Sliding Tiles Puzzle allows you to play in two modes." +
//	            		"" + 
//	            		"Mode 1 : You define basic mathematic equations using digits 0-9 and the addition, " +
//	            		"subtracton and multiplication operators. Equations can be formed either horizontally " +
//	            		" from left to right or vertically from top to bottom. " +
//	            		"Equations must include required operators (i.e. 9=9 is not allowed)." +
//	            		"A candidate equation is submitted by dragging a finger across the screen." +
//	            		"If the equation is valid, then you earn points cumutatively. You can form new " +
//	            		"equations by moving tiles around the board.");
//	 
//	            CharSequence ok = "OK";
//	            // set a positive/yes button and create a listener
//	            alertbox.setPositiveButton(ok, new DialogInterface.OnClickListener() {
//	 
//	                // close the help when the button is clicked
//	                public void onClick(DialogInterface arg0, int arg1) {
//	                	arg0.dismiss();
//	                }
//	            });
//	 
//	            // display box
//	            alertbox.show();
//	   	    	break;
//	   }
//    }};
}