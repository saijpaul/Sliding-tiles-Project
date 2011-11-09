package com.softengg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import android.content.Context;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

public class TileView extends View {
                
        public static final int MOVE_UP = 0;
        public static final int MOVE_DOWN = 1;
        public static final int MOVE_LEFT = 2;
        public static final int MOVE_RIGHT = 3;

        private static final int SHADOW_RADIUS = 1;
        private HashMap hequationvalue = new HashMap();
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
//        int startIndex[] = new int[2];
//        int recx=0;
//        int recy=0;
//        int recx1=0;
//        int recy1=0;
//        int header = 80;
//        int footer = 400;
        
        Paint tPaint;
        
//       String colorChange = null;
        
    public TileView(Context context, AttributeSet attrs) {
        super(context, attrs);
        
        init();
    }
   
        public TileView(Context context) {
                super(context);
                
                init();
        }
        
        private void init() {
            setFocusable(true);
            Context context = getContext();
              
            tPaint = new Paint();
            tPaint.setTextAlign(Paint.Align.CENTER);
//            startIndex[0]=0;
//            startIndex[1]=0;
                
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
 
private void fillValue(int value)
{
	Random r = new Random();
	int rand3 = r.nextInt(tSizeSqr-1);
	while (tTiles[rand3].mNumber != 0)
	{
		rand3 = r.nextInt(tSizeSqr-1);
	}
	tTiles[rand3].mNumber = value;	
}
 
private void fillAddEquation()
{
	Random r = new Random();
	
	int rand1 = r.nextInt(7) + 1;
	int rand2 = r.nextInt((8-rand1))+1;
		
	// Equation rand1 + rand2 = sum
	fillValue(rand1);
	fillValue(rand2);
	fillValue(40); // + operator
	fillValue(20); // = operator
	fillValue((rand1+rand2));
	
	System.out.println("Eq is"+ rand1 + "+" + rand2 + "="+ (rand1+rand2));
}
	

private void fillSubEquation()
{
	Random r = new Random();
	
	int rand1 = r.nextInt(5) + 5;
	int rand2 = r.nextInt(5)+1;
		
	// Equation rand1 - rand2 = sub
	fillValue(rand1);
	fillValue(rand2);
	fillValue(50); // - operator
	fillValue(20); // = operator
	fillValue((rand1-rand2));
	
	System.out.println("Eq is"+ rand1 + "-" + rand2 + "="+ (rand1-rand2));
}
private void fillMulEquation()
{
	Random r = new Random();
	
	int rand1 = r.nextInt(3)+1;
	int rand2=0;
	
	if (rand1 == 1)
	{
		rand2 = r.nextInt(9)+1; 
	}
	else if (rand1 == 2)
	{
		rand2 = r.nextInt(4)+1;
	}
	else if (rand1 == 3)
	{
		rand2 = r.nextInt(3)+1;
	}
	 		
	// Equation rand1 * rand2 = mul
	fillValue(rand1);
	fillValue(rand2);
	fillValue(60); // * operator
	fillValue(20); // = operator
	fillValue((rand1*rand2));
	
	System.out.println("Eq is"+ rand1 + "*" + rand2 + "="+ (rand1*rand2));
	
}

private void fillRandomVals(int op, int count)
{
	Random r = new Random();
	int a=0;
	int rq=0;
	if (op == 1)
	{
		for (a=0;a<count;a++)
		{
			rq = r.nextInt(8)+1;
			fillValue(rq);
		}
	}
	else if (op == 2)
	{
		for (a=0;a<count;a++)
		{
			// Filling value 40, 50, or 60
			rq = ((r.nextInt(3)+1)*10)+30;
			fillValue(rq);
		}
	}
	else 
	{
		// Equal to "=" value:20
		for (a=0;a<count;a++)
		{
			fillValue(20);
		}
	}
}
 public void newGame(Tile[] tiles,int gridsize) {
       
        isSelected = -1; //nothing selected to start
        tSize = gridsize;
        tSizeSqr = tSize * tSize;
        int eq_size = (tSizeSqr / tSize)- 1 ;
        

        // Init array of tiles
        Random random = new Random();
        
        tTiles = new Tile[tSizeSqr];
        for (int i = 0; i < tSizeSqr; ++i) {
 //tTiles[i] = new Tile(i, random.nextInt() | 0xff000000);
                    tTiles[i] = new Tile();
        }
        
        int randFunc = random.nextInt(3);
        
    	for (int k=0;k<(randFunc+1);k++)
    	{
           	int radVal = random.nextInt(3)+1;
        	
        	switch(radVal)
        	{
	        	case 1: { fillAddEquation();break;}
	        	case 2: { fillSubEquation(); break;}
	        	case 3: { fillMulEquation();break;}
        	}
       	}
    	fillRandomVals(1,(9-(2*randFunc))); // number
    	fillRandomVals(2,(6-(2*randFunc))); // operator
    	fillRandomVals(3,(3-randFunc)); // equal to
        
        emptyIndex = tSizeSqr -1 ;

        tTiles[emptyIndex] = null;
        
        // Mix up puzzle with valid moves only
        for (int i = 0; i < 100*tSize; ++i) {
            move(random.nextInt(5));
        }
 
        

    } 
        
        
        
            
        private float getTileWidth() {
                return getWidth() / tSize;
        }
        
        private float getTileHeight() {
                //return getHeight() / tSize;
                  return getWidth()/tSize;
        }
            

    @Override
    protected void onDraw(Canvas canvas) {
        
        float tileWidth = getTileWidth();
        float tileHeight = getTileHeight();
        
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
                        
                tPaint.setColor(Color.CYAN);
                canvas.drawRect(x, y, x + tileWidth, y + tileHeight, tPaint);
                tPaint.setColor(Color.BLACK);	
                
                tPaint.setTextSize(20);
                tPaint.setTypeface(Typeface.MONOSPACE);
                
                switch (tTiles[index].mNumber)
                {
	                case 1 :
	                case 2 :
	                case 3 :
	                case 4 :
	                case 5 :
	                case 6 :
	                case 7 :
	                case 8 :
	                case 9 :
	                		  {canvas.drawText(String.valueOf(tTiles[index].mNumber), x+30, y+40, tPaint);
	                			break;}
	                case 20 : {canvas.drawText("=" , x+30, y+40, tPaint); break;}
	                case 40 : {canvas.drawText("+" , x+30, y+40, tPaint); break;}
	                case 50 : {canvas.drawText("-" , x+30, y+40, tPaint); break;}
	                case 60 : {canvas.drawText("*" , x+30, y+40, tPaint); break;}
	                default : {
	                		canvas.drawText("1" , x+30, y+40, tPaint); 
	                		tTiles[index].mNumber= 1; break;} 
                }
                                
            //Drop shadow to make numbers and borders stand out
            tPaint.setShadowLayer(SHADOW_RADIUS, 1, 1, 0xff000000);
            
          
            //Draw the outline
            //if (mShowOutlines) {
                float x2 = x + tileWidth-1;
                float y2 = y + tileHeight-1;
                float lines[] = {
                    x, y, x2, y,
                    x, y, x, y2,
                    x2, y, x2, y2,
                    x, y2, x2, y2
                };
          //      tPaint.setColor(mOutlineColor);
                canvas.drawLines(lines, tPaint);
            //}
            
            // remove shadow layer for perfomance
            tPaint.setShadowLayer(0, 0, 0, 0);
        }
    
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
        
//        startIndex[0] = index;
//        startIndex[1] = index;
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
    
    public HashMap createValueMap(float x, float y,int i) {
    	
//    	System.out.println("key in hashmap"+i);
//    	System.out.println("value in hashmap"+getCellIndex(x, y));
    		int index = getCellIndex(x,y);
    		//hequationvalue.put(i,getCellIndex(x, y));
    		hequationvalue.put(i,index);
  	
//    		colorChange = "YES";
//    		
//    		if (startIndex[0] > index)
//    		{
//    			startIndex[0] = index;
//    		}
//    		if (startIndex[1] <= index)
//    		{
//    			startIndex[1] = index;
//    		}
    		
    		//redrawRow();
    		//redrawColumn();
    		
    		
    	//System.out.println("here size of hashmap"+hequationvalue.size());
    	return hequationvalue;
    }
    
    public boolean validatEquation(Set set) {
    	
    //	TreeSet ts = new TreeSet(new DescendingComparator());
		// validation
    	Iterator it = set.iterator();
    	
		while(it.hasNext()){
			
			Integer v = (Integer) it.next();
			System.out.println("value of v" + v);
            
			System.out.println("value of value" + tTiles[v.intValue()].mNumber);
           } //while
		List<Integer> list = new ArrayList<Integer>(set);
		
		int a = list.get(0);
		int b = list.get(1);
		int c = list.get(2);
		int d = list.get(3);
		int e = list.get(4);
	    
		
//		tTiles[a].setmColor(Color.GREEN);
//		tTiles[b].setmColor(Color.GREEN);
//		tTiles[c].setmColor(Color.GREEN);
//		tTiles[d].setmColor(Color.GREEN);
//		tTiles[e].setmColor(Color.GREEN);
		
		//if (bool == true)
//		{
//			int x= ((startIndex[0] % 5)*64);
//			int y = header + (( startIndex[0]/ 5)*64);
//			int x1 = ((startIndex[1] % 5)+1) *64;
//			int y1 = header+ ((startIndex[1]/5)+1)*64;
//			System.out.println("[X,Y]"+x+y+"[x1,y1]"+x1+y1);
//			recx = x;
//			recy=y;
//			recx1=x1;
//			recy1=y1;
//			invalidate(x,y,x1,y1);
//		}
		
		System.out.println("value of value A::::" + tTiles[a].mNumber);
		System.out.println("value of value B::::" + tTiles[b].mNumber);
		System.out.println("value of value C::::" + tTiles[c].mNumber);
		System.out.println("value of value D::::" + tTiles[d].mNumber);
		System.out.println("value of value E::::" + tTiles[e].mNumber);
		boolean bool = validateEquation(a,b,c,d,e);
		System.out.println("bool:::::"+bool);
		
		
		
		return bool;

    }
    public boolean validateEquation(int a,int b,int c,int d,int e)  {

    	
		String[] arrayOfIndx = {Integer.toString(tTiles[a].mNumber),Integer.toString(tTiles[b].mNumber),Integer.toString(tTiles[c].mNumber)
				,Integer.toString(tTiles[d].mNumber),Integer.toString(tTiles[e].mNumber)};
        int operValue =0;
        String regexp1 = "[0-9]";
        String regexp2 = "[60502040]";

        if (arrayOfIndx[3].equals("40") || arrayOfIndx[1].equals("40"))
        		operValue =1;
        else if (arrayOfIndx[3].equals("60") || arrayOfIndx[1].equals("60"))
        		operValue =2;
        else if (arrayOfIndx[3].equals("50") || arrayOfIndx[1].equals("50")) 
        		operValue =3;
        
    	System.out.println("operValue " + operValue);
    	
    	System.out.println("eq 1 " + arrayOfIndx[1].equals("20"));
    	System.out.println("eq 2 " + arrayOfIndx[3].equals("20"));
    	System.out.println("eq 1 ==" + arrayOfIndx[1] == "20");
    	System.out.println("eq 2 ==" + arrayOfIndx[3] == "20");
    	System.out.println("eq 3 " + arrayOfIndx[0].matches(regexp1));
    	System.out.println("eq 4 " + arrayOfIndx[2].matches(regexp1));
    	System.out.println("eq 5 " + arrayOfIndx[4].matches(regexp1));
    	System.out.println("eq 6 " + arrayOfIndx[3].matches(regexp2));
    	System.out.println("eq 7 " + arrayOfIndx[1].matches(regexp2));
    	
        if ( arrayOfIndx[1].equals("20") && (arrayOfIndx[3].equals("40") || arrayOfIndx[3].equals("50") || arrayOfIndx[3].equals("60")) 
        		&& arrayOfIndx[0].matches(regexp1) && arrayOfIndx[2].matches(regexp1) && arrayOfIndx[4].matches(regexp1))
        {
        	System.out.println("inside 1 if ");
        	switch(operValue){

      			case 1: {

      						if (Integer.parseInt(arrayOfIndx[0]) == Integer.parseInt(arrayOfIndx[2]) + Integer.parseInt(arrayOfIndx[4]))

      					    	System.out.println("1 Eq is valid");
      							
      							return true;

      			}
      			case 2: {

  							if (Integer.parseInt(arrayOfIndx[0]) == Integer.parseInt(arrayOfIndx[2]) * Integer.parseInt(arrayOfIndx[4]))

      							System.out.println("2 Eq is valid");

  								return true;

      			}
      			case 3: {

      						if (Integer.parseInt(arrayOfIndx[0]) == Integer.parseInt(arrayOfIndx[2]) - Integer.parseInt(arrayOfIndx[4]))

  								System.out.println("3 Eq is valid");

      							return true;

      			}

      		}

      	}
        
       else if ( arrayOfIndx[3].equals("20") && (arrayOfIndx[1].equals("40") || arrayOfIndx[1].equals("50") || arrayOfIndx[1].equals("60"))
    		   && arrayOfIndx[0].matches(regexp1) && arrayOfIndx[2].matches(regexp1) && arrayOfIndx[4].matches(regexp1))
  	{
       	System.out.println("inside 2 if ");

      	switch(operValue)
  		{
  			case 1: {

  						if (Integer.parseInt(arrayOfIndx[4]) == Integer.parseInt(arrayOfIndx[0]) + Integer.parseInt(arrayOfIndx[2]))

  					    	System.out.println("Eq is valid");

  							return true;

  						}
  			case 2: {

  						if (Integer.parseInt(arrayOfIndx[4]) == Integer.parseInt(arrayOfIndx[0]) * Integer.parseInt(arrayOfIndx[2]))

  							System.out.println("Eq is valid");

  							return true;




  						}
  			case 3: {

  						if (Integer.parseInt(arrayOfIndx[4]) == Integer.parseInt(arrayOfIndx[0]) - Integer.parseInt(arrayOfIndx[2]))

  							System.out.println("Eq is valid");
  							return true;
  						}

  		}

  	}    

       else 

  			System.out.println("Eq is invalid");

       		return false;




      }
 

    @SuppressWarnings("unchecked")
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
