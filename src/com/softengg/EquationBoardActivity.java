/********************************************************************************
* Authors: Seema Saijpaul, Pratibha Natani, Neeraja Budamagunta, Maxwell A. Garvey
*********************************************************************************/

package com.softengg;

import android.app.Activity;
import android.view.View;
import android.os.Bundle;
import android.widget.ImageView;


public class EquationBoardActivity extends Activity {
       
	private ImageView imageHome = null;
	
  /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.equationboard);
        
        this.imageHome = (ImageView) this.findViewById(R.id.imageHome);
        
        imageHome.setOnClickListener(myListener);
     }
    
    View.OnClickListener myListener = new View.OnClickListener(){
        public void  onClick  (View  v){
        	switch(v.getId()){
        	
	    	case R.id.imageHome:
	    		finish();
	    	break;
	   
	   }
    }};
     
}