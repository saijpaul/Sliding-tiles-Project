package com.softengg;


import java.util.Random;

import android.content.Context;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

public class NumberGameView extends View{

    
    public static final int MOVE_UP = 0;
    public static final int MOVE_DOWN = 1;
    public static final int MOVE_LEFT = 2;
    public static final int MOVE_RIGHT = 3;

    private static final int SHADOW_RADIUS = 1;
    //Offset of tile from top left corner of cell
    float offsetX;
    float offsetY;
    
    //Current position on screen, used for drag events
    float mX;
    float mY;
    
    int emptyIndex;
    int isSelected;
    int tSize;
    int tSizeSqr;
    Tile tTiles[];
    Tile tTiless[];
    Paint tPaint;
    
   
public NumberGameView(Context context, AttributeSet attrs) {
    super(context, attrs);
    
    init();
}

    public NumberGameView(Context context) {
            super(context);
            
            init();
    }
    
    private void init() {
        setFocusable(true);
        Context context = getContext();
          
        tPaint = new Paint();
        tPaint.setTextAlign(Paint.Align.CENTER);
            
    }
    
@Override 
public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    
    int w = getMeasuredWidth();
    int h = getMeasuredHeight();
    Context context = getContext();

    if (w <= 0 || h <= 0) {
            return;
    }

}

public void newGame(Tile[] tiles,int gridsize) {
   
    isSelected = -1; //nothing selected to start
    tSize = gridsize;
    tSizeSqr = tSize * tSize;
    

    // Init array of tiles
    Random random = new Random();
 //   int numberStore[] = new int[gridsize*gridsize];
    int i,k,temp=0;

    tTiles = new Tile[tSizeSqr];
    tTiless = new Tile[tSizeSqr];
  //  tTiles[0]=new Tile();
//    for (int l=0;l<tSizeSqr;l++)
//   {numberStore[l]=0;}
    
//    numberStore[g]=random.nextInt(gridsize*gridsize);
//    tTiles[0].mNumber=numberStore[0];
    
 /*   //start
     for(i=1;i<tSizeSqr;i++) 
     {
    	 temp=random.nextInt(gridsize*gridsize);
         System.out.println("value of i and temp is " + i + " " + temp);
    	 for(k=1;k<i;k++)
    	 {
	         if (tTiles[k].mNumber == temp)
	    	 {
	        	 temp=random.nextInt(gridsize*gridsize);
	             System.out.println("inside if and new temp is " + temp);
	             k =0;
	    	 }
	         System.out.println("value of k " + k);
    	 }
         tTiles[i] = new Tile();   
    	 tTiles[i].mNumber=temp; 
         System.out.println("final value of i is " +i+ "and temp is " +temp);
     }
   
    //end
      */
     

     //start 1
     emptyIndex = tSizeSqr -1 ;
     tTiles[emptyIndex] = new Tile(); 
     tTiles[emptyIndex] = null;

     tTiless[emptyIndex] = new Tile(); 
     tTiless[emptyIndex] = null;

     System.out.println("empty tile " + tTiles[emptyIndex]);
     for(i=0;i<(tSizeSqr-1);i++)
     {
    	   tTiles[i] = new Tile();   
      	   tTiles[i].mNumber=i+1;
      	   tTiless[i] = new Tile();   
    	   tTiless[i].mNumber=i+1;
           System.out.println("value of " +i + "element is " + tTiles[i].mNumber);
     }
     
     // Mix up puzzle with valid moves only
     for (int g= 0; g < 100*tSize; ++g) {
         move(random.nextInt(5));
     }
     //end 1
} 
    
    
    
        
    private float getTileWidth() {
            return getWidth() / tSize;
    }
    
    private float getTileHeight() {
//            return (getHeight() / tSize)/2;
              return getWidth()/tSize;
    }
        
    public boolean chkWinstate() {
  	  boolean winState=false;
  	  int i=0,j=0,k=0;
  	  System.out.println("inside chkWinstate");
  	  if (tTiles[0]!=null&&tTiles[tTiles.length-1]!=null)
  	  {
  		  winState = false;
  	  }else{
  		  if (!(tTiles[0]!=null)){
  			  i=1;
  		      j=tTiles.length-1;}
  		  else if(!(tTiles[tTiles.length-1]!=null)){
  			  i=0;
  		  j=tTiles.length-2;
  		  }
  	  for(k=i;k<j;k++)
  	  {
  		  System.out.println("inside loop chking k=" + k);
  		  
  		  
  		  if (tTiles[k].mNumber == (k+1))
  		  {	  
  			  winState= true;
  			  System.out.println("inside if for element "+ k+1 + " value is "+ tTiles[k].mNumber);
  		   }
  		  else
  		  {	  
  			  winState=false;
  			  System.out.println("inside else for element "+ k+1 + " value is "+ tTiles[k].mNumber);
  			  break;
  			  }
  	  }
  	  }
    	 return winState;
  }

@Override
protected void onDraw(Canvas canvas) {
    
    float tileWidth = getTileWidth();
    //float tileWidthHalf = tileWidth/2;
    float tileHeight = getTileHeight();
    //float tileHeightHalf = tileHeight/2;
    
	        for (int index = 0; index < tSizeSqr; ++index) {
                int i = index / tSize;
                int j = index % tSize;
                float x = tileWidth * j;
                float y = tileHeight * i;

                // if this is the empty cell do nothing                 
                if (tTiles[index] == null) {
                        continue;
                }
                                
                if(isSelected != -1) {
                        int min = Math.min(isSelected, emptyIndex);
                        int max = Math.max(isSelected, emptyIndex);
                        int minX = min % tSize;
                        int minY = min / tSize;
                        int maxX = max % tSize;
                        int maxY = max / tSize;
                        
                        if (i >= minY && i <= maxY && j == minX) {
                                y += offsetY;
                        }
                        if (j >= minX && j <= maxX && i == minY) {
                                x += offsetX;
                        }
                    }
                    
            tPaint.setColor(Color.BLACK);
            
            canvas.drawRect(x, y, x + tileWidth, y + tileHeight, tPaint);
            //tPaint.setColor(Color.RED);
            
            if(index % 2 == 0 )
            {
                    tPaint.setColor(0xFFF5DEB3);
                    canvas.drawRect(x, y, x + tileWidth, y + tileHeight, tPaint);
                //tPaint.setColor(0xFF527A7A);
                      tPaint.setColor(0xFF53537C);
            }
            else
            {
                    //tPaint.setColor(0xFFD2B48C);
                    tPaint.setColor(0xFF9B5C3D);
                    canvas.drawRect(x, y, x + tileWidth, y + tileHeight, tPaint);
               // tPaint.setColor(0xFFBFE843);
                     tPaint.setColor(0xFFFF9900);
            }
            tPaint.setTextSize(35);
            
            tPaint.setTypeface(Typeface.SANS_SERIF.DEFAULT_BOLD); // added bold font to sans_serif
          //Drop shadow to make numbers and borders stand out
            tPaint.setShadowLayer(SHADOW_RADIUS, 2, 2, 0xff000000);
            switch (tTiles[index].mNumber)
            {
              /*  case 1 :
                case 2 :
                case 3 :
                case 4 :
                case 5 :
                case 6 :
                case 7 :
                case 8 :
                case 9 :
                case 10:
                case 11:
                case 12: */
                default:		  {canvas.drawText(String.valueOf(tTiles[index].mNumber), x+40, y+60, tPaint);
                		  System.out.println("tile value " + String.valueOf(tTiles[index].mNumber));
                			break;}
//                default: System.out.println("do nothing");
            }
            
            float x2 = x + tileWidth-1;
            float y2 = y + tileHeight-1;
            float lines[] = {
                x, y, x2, y,
                x, y, x, y2,
                x2, y, x2, y2,
                x, y2, x2, y2
            };
            tPaint.setColor(0xffcd8539);
            canvas.drawLines(lines, tPaint);
        //}
        
        // remove shadow layer for perfomance
        tPaint.setShadowLayer(0, 0, 0, 0);
    }
	
//	for (int index = 0; index < tSizeSqr; ++index) {
//                int i = index / tSize;
//                int j = index % tSize;
//                float xx = tileWidth * j;
//                float x = xx;
//                float yy = tileHeight * i;
//                float y = yy+ 200;
//
//                // if this is the empty cell do nothing                 
//                if (tTiless[index] == null) {
//                        continue;
//                }
//                                
//                if(isSelected != -1) {
//                        int min = Math.min(isSelected, emptyIndex);
//                        int max = Math.max(isSelected, emptyIndex);
//                        int minX = min % tSize;
//                        int minY = min / tSize;
//                        int maxX = max % tSize;
//                        int maxY = max / tSize;
//                        
//                        if (i >= minY && i <= maxY && j == minX) {
//                                y += offsetY;
//                        }
//                        if (j >= minX && j <= maxX && i == minY) {
//                                x += offsetX;
//                        }
//                    }
//                    
//            tPaint.setColor(Color.BLACK);
//            
//            canvas.drawRect(x, y, x + tileWidth, y + tileHeight, tPaint);
//            tPaint.setColor(Color.RED);
//            
//            if(index % 2 == 0 )
//            {
//                    tPaint.setColor(0xFFF5DEB3);
//                    canvas.drawRect(x, y, x + tileWidth, y + tileHeight, tPaint);
//                //tPaint.setColor(0xFF527A7A);
//                      tPaint.setColor(0xFF53537C);
//            }
//            else
//            {
//                    //tPaint.setColor(0xFFD2B48C);
//                    tPaint.setColor(0xFF9B5C3D);
//                    canvas.drawRect(x, y, x + tileWidth, y + tileHeight, tPaint);
//               // tPaint.setColor(0xFFBFE843);
//                     tPaint.setColor(0xFFFF9900);
//            }
//            tPaint.setTextSize(35); 
//            
//            tPaint.setTypeface(Typeface.SANS_SERIF.DEFAULT_BOLD); // added bold font to sans_serif
//          //Drop shadow to make numbers and borders stand out
//            tPaint.setShadowLayer(SHADOW_RADIUS, 2, 2, 0xff000000);
//            switch (tTiless[index].mNumber)
//            {
//              /*  case 1 :
//                case 2 :
//                case 3 :
//                case 4 :
//                case 5 :
//                case 6 :
//                case 7 :
//                case 8 :
//                case 9 :
//                case 10:
//                case 11:
//                case 12: */
//                default:		  {canvas.drawText(String.valueOf(tTiless[index].mNumber), x+30, y+40, tPaint);
//                		  System.out.println("tile value " + String.valueOf(tTiless[index].mNumber));
//                			break;}
////                default: System.out.println("do nothing");
//            }
//            
//            float x2 = x + tileWidth-1;
//            float y2 = y + tileHeight-1;
//            float lines[] = {
//                x, y, x2, y,
//                x, y, x, y2,
//                x2, y, x2, y2,
//                x, y2, x2, y2
//            };
//            tPaint.setColor(Color.RED);
//            canvas.drawLines(lines, tPaint);
//        //}
//        
//        // remove shadow layer for perfomance
//        tPaint.setShadowLayer(0, 0, 0, 0);
//    }

}
        
private int getCellIndex(float x, float y) {
    float tileWidth = getTileWidth();
            float tileHeight = getTileHeight();
            
            int loc[] = new int[2];
            getLocationOnScreen(loc);


            
    int xIndex = (int)((x - loc[0]) / tileWidth);
            int yIndex = (int)((y - loc[1]) / tileHeight);

            //clamp selection to edges of puzzle
            if (xIndex >= tSize) {
                xIndex = tSize-1;
            }
            else if (xIndex < 0) {
                xIndex = 0;
            }
            
    if (yIndex >= tSize) {
        yIndex = tSize-1;
    }
    else if (yIndex < 0) {
        yIndex = 0;
    }
            
                
            return tSize * yIndex + xIndex;
}
    
private boolean isSelectable(int index) {
    return (index / tSize == emptyIndex / tSize || index % tSize == emptyIndex % tSize) &&
            index != emptyIndex;
}

public boolean move(int dir) {
    
    if (isSelected >= 0) {
        return false;
    }
    
    int index;
    switch(dir) {
            case MOVE_UP:
                    index = emptyIndex + tSize;
                    if ((index) < tSizeSqr) {
                            update(index);
                            return true;
                    }
                    return false;
            case MOVE_DOWN:
                    index = emptyIndex - tSize;
                    if ((index) >= 0) {
                            update(index);
                            return true;
                    }
                    return false;
            case MOVE_LEFT:
                    index = emptyIndex + 1;
                    if ((index % tSize) != 0 ) {
                            update(index);
                            return true;
                    }
                    return false;
            case MOVE_RIGHT:
                    index = emptyIndex - 1;
                    if ((emptyIndex % tSize) != 0) {
                            update(index);
                            return true;
                    }
                    return false;
    }
    return false;
}

private void redrawRow() {
    int h = (int)getTileHeight();
    int tileY = h * (emptyIndex / tSize);
    invalidate(0, tileY - SHADOW_RADIUS, getRight(), tileY + h + SHADOW_RADIUS);        
}

private void redrawColumn() {
    int w = (int)getTileWidth();
    int tileX =  w * (emptyIndex % tSize);
    invalidate(tileX  - SHADOW_RADIUS, 0, tileX + w + SHADOW_RADIUS, getBottom());
}

private void update(int index) {
    if (index / tSize == emptyIndex / tSize) {
            //Moving a row
            if (emptyIndex < index) {
                while (emptyIndex < index ) {
                    tTiles = (Tile[]) ArrayUtil.swap(tTiles, emptyIndex, emptyIndex + 1);
                    ++emptyIndex;
                }           
            } else {
            while (emptyIndex > index ) {
                tTiles = (Tile[]) ArrayUtil.swap(tTiles, emptyIndex, emptyIndex - 1);
                --emptyIndex;
            }
            }
        redrawRow();
    } else if (index % tSize == emptyIndex % tSize){
            //Moving a column
            if (emptyIndex < index) {
            while (emptyIndex < index ) {
                tTiles = (Tile[]) ArrayUtil.swap(tTiles, emptyIndex, emptyIndex + tSize);
                emptyIndex += tSize;
            }                                       
            } else {
            while (emptyIndex > index ) {
                tTiles = (Tile[]) ArrayUtil.swap(tTiles, emptyIndex, emptyIndex - tSize);
                emptyIndex -= tSize;
            }                       
            }
        redrawColumn();
    }       
}

public void pickTile(float x, float y) {   
	System.out.println("here reached again"+x+y);
    int index = getCellIndex(x, y);
//    System.out.println("here reached again index number"+tTiles[index].mNumber);
    isSelected = isSelectable(index) ? index : -1;
    
    //set coordinates to the upper left corner of the selected tile
    mX = x;
    mY = y;
    offsetX = 0;
    offsetY = 0;
    
//    startIndex[0] = index;
//    startIndex[1] = index;
//    
    
}

public boolean dropTile(float x, float y) {
    
    if (isSelected != -1 && (Math.abs(offsetX) > getTileWidth()/2 ||
                    Math.abs(offsetY) > getTileHeight()/2)) {   
            update(isSelected);
            isSelected = -1;
            return true;
    } else if (isSelected % tSize == emptyIndex % tSize) {
        redrawColumn();
    } else if (isSelected / tSize == emptyIndex / tSize) {
        redrawRow();
    }
    isSelected = -1;
    return false;
}


public void dragTile(float x, float y) {
	
	
    if (isSelected < 0)
        return;
    
    int w = (int)getTileWidth();
    int h = (int)getTileHeight();
            
    //Only drag in a single plane, either x or y depending on location of empty cell
    //prevent tiles from being dragged onto other tiles
    if (isSelected % tSize == emptyIndex % tSize) {
            if (isSelected > emptyIndex) {
                offsetY += y - mY;
                if (offsetY > 0) {
                    offsetY = 0;
                } else if (Math.abs(offsetY) > h) {
                    offsetY = -h;
                }
                mY = y;

            } else {
                offsetY += y - mY;
                if (offsetY < 0) {
                    offsetY = 0;
                } else if (offsetY > h) {
                    offsetY = h;
                }
                mY = y;
            }
            redrawColumn();
    } else if (isSelected / tSize == emptyIndex / tSize) {
            if (isSelected > emptyIndex) {
                offsetX += x - mX;
                if (offsetX > 0) {
                    offsetX = 0;
                } else if (Math.abs(offsetX) > w) {
                    offsetX = -w;
                }
                mX = x;
            } else {
                offsetX += x - mX;
                if (offsetX < 0) {
                    offsetX = 0;
                } else if (offsetX > w) {
                    offsetX = w;
                }
                mX = x;
            }
            redrawRow();
    }        
}  

  public Tile[] getTiles() {
    return tTiles;
}


}
