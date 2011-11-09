/********************************************************************************
* Authors: Seema Saijpaul, Pratibha Natani, Neeraja Budamagunta, Maxwell A. Garvey
*********************************************************************************/

package com.softengg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import android.app.Activity	;

import android.view.MotionEvent;
import android.view.View;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class EquationBoardActivity extends Activity   {
       
	private ImageView imageHome = null;
    private TileView puzzleTileView;
    private TextView textViewToChange;
    //private int i=0;

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
    private int i=0;
    private String mode = null;
    private HashMap hmm = new HashMap();
    private Set s = new LinkedHashSet();
    private int score = 0;
   // private List l = new ArrayList();
    //move tiles using touch
    @Override public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);

        int action = event.getAction();
         
        switch (action) {
            case MotionEvent.ACTION_DOWN: {
            	System.out.println("here reached pick");
                puzzleTileView.pickTile(event.getX(), event.getY());
                break;
            }
            case MotionEvent.ACTION_MOVE: {
   //         	System.out.println("here reached value of i"+i);
            	hmm = puzzleTileView.createValueMap(event.getX(), event.getY(),i);
//            	puzzleTileView.dragTile(event.getX(), event.getY());
 //           	System.out.println("size of hashmap"+hmm.size());
            	i = i+1;
//            	for(int i=0; i<hmm.size();i++){
//                    if(!s.add(hmm.get(i))){
//                      System.out.println("Duplicate detected first : " + hmm.get(i));
//                    	}
//                 }
//            	System.out.println("size of set"+s.size());
//            	if(s.size()==5){
                mode = "nowdo";
//            	}
//            	else{
            		//puzzleTileView.dragTile(event.getX(), event.getY());
            		//s.clear();
            		//hmm.clear();
            	//}
                break;
            }
            case MotionEvent.ACTION_UP: {
            	if(mode=="nowdo"){
            		
            		
//            		System.out.println("SIZE: " + hmm.size());
//            		System.out.println("SIZE: " + hmm.values().size());
            		for(int i=0; i<hmm.size();i++){
//            			System.out.println(hmm.get(i));
//            			System.out.println(s.add(hmm.get(i)));
            			if(hmm.get(i)!=null){
            			s.add(hmm.get(i));
            		}
                        //if(s.add(hmm.get(i))){
     //                     System.out.println("Duplicate detected : " + hmm.get(i));}
                        //}
                     }
            		

                	//System.out.println("here reached size one"+s.size());
                	if(s.size()<5){
                		puzzleTileView.dragTile(event.getX(), event.getY());
                	}
         
                	else{
//                		Iterator it = s.iterator();
//                		
//                		while(it.hasNext()){
//                			
//                			
//                			Integer v = (Integer) it.next();
//       //                    System.out.println("non Duplicate : " + v);
//                           } //while

                		boolean bool = puzzleTileView.validatEquation(s);
                		
                		if(bool == true){
                		score = score +5;
                		Toast.makeText(this, "HURRAY! Your equation is correct", Toast.LENGTH_SHORT).show();
                		textViewToChange = (TextView) findViewById(R.id.scoreLabel);
                		textViewToChange.setText("Score:"+score);
                		}
                		else{ // jus added for checking need to remove later
                			score = score +0;
                			Toast.makeText(this, "OOPS! Try Again", Toast.LENGTH_SHORT).show();
                			textViewToChange = (TextView) findViewById(R.id.scoreLabel);
                    		textViewToChange.setText("Score: "+score);
                		}
                		System.out.println("scoring"+score);
                		
                		
                       } // else
                		
            	}
        

            		boolean moved = puzzleTileView.dropTile(event.getX(), event.getY());
            		s.clear();
                    hmm.clear();
                    i=0;
            	
                break;
            }
            
        }
       
        return true;
    }    
}