/********************************************************************************
* Authors: Seema Saijpaul, Pratibha Natani, Neeraja Budamagunta, Maxwell A. Garvey
*********************************************************************************/

package com.softengg;

import android.app.Activity	;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnKeyListener;
//import android.content.SharedPreferences;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.os.Bundle;
//import android.preference.PreferenceManager;
import android.widget.ImageView;

public class EquationBoardActivity extends Activity   {
       
	private ImageView imageHome = null;
    private TileView puzzleTileView;

  /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.equationboard);
    
        //generates tiles    
        puzzleTileView = (TileView) findViewById(R.id.tile_view);
        puzzleTileView.requestFocus();
        
        if (bundle == null) {
        	int gridsize=5;
            puzzleTileView.newGame(null,gridsize);
         } 
        //shows home icon on puzzle page to go back
        this.imageHome = (ImageView) this.findViewById(R.id.imageHome);
        imageHome.setOnClickListener(myListener);
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
    //move tiles using touch
    @Override public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);

        int action = event.getAction();

        switch (action) {
            case MotionEvent.ACTION_DOWN: {
                puzzleTileView.pickTile(event.getX(), event.getY());
                return true;
            }
            case MotionEvent.ACTION_MOVE: {
                puzzleTileView.dragTile(event.getX(), event.getY());
                return true;
            }
            case MotionEvent.ACTION_UP: {
                boolean moved = puzzleTileView.dropTile(event.getX(), event.getY());
                
                return true;
            }
        }
        
        return false;
    }    
}