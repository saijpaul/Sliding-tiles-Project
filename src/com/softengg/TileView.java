/********************************************************************************
* Authors: Seema Saijpaul, Pratibha Natani, Neeraja Budamagunta, Maxwell A. Garvey
*********************************************************************************/

package com.softengg;

import java.util.Random;

import android.content.Context;
//import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
//import android.preference.PreferenceManager;
import android.util.AttributeSet;
import android.view.View;

public class TileView extends View {
                
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
        
        Paint tPaint;
        
       
        
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
 
        public void newGame(Tile[] tiles, int gridsize) {
       
        isSelected = -1; //nothing selected to start
       
        //hardcoding for grid size = 5*5
        	tSize = gridsize;
        	//for 25 tiles
            tSizeSqr = tSize * tSize;

            // Init array of tiles
            Random random = new Random();
            
            tTiles = new Tile[tSizeSqr];
            for (int i = 0; i < tSizeSqr; ++i) {
                tTiles[i] = new Tile(i, random.nextInt() | 0xff000000);
            	//tTiles[i] = new Tile(i, 0xffffffff);
            }

            emptyIndex = tSizeSqr -1 ;
            tTiles[emptyIndex] = null;
   
        } 
        
         
            
        private float getTileWidth() {
                return getWidth() / tSize;
        }
        
        private float getTileHeight() {
                return getHeight() / tSize;
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
                                        y += offsetX;
                                }
                                if (j >= minX && j <= maxX && i == minY) {
                                        x += offsetX;
                                }
                        }
                        
                 tPaint.setColor(Color.CYAN);
                
                canvas.drawRect(x, y, x + tileWidth, y + tileHeight, tPaint);
                        
            //Drop shadow to make numbers and borders stand out
            tPaint.setShadowLayer(SHADOW_RADIUS, 1, 1, 0xff000000);
            
            //Draw the outline
                float x2 = x + tileWidth-1;
                float y2 = y + tileHeight-1;
                float lines[] = {
                    x, y, x2, y,
                    x, y, x, y2,
                    x2, y, x2, y2,
                    x, y2, x2, y2
                };
                //tPaint.setColor(Color.RED);
                canvas.drawLines(lines, tPaint);
        
            
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
        int index = getCellIndex(x, y);
        isSelected = isSelectable(index) ? index : -1;
        
        //set coordinates to the upper left corner of the selected tile
        mX = x;
        mY = y;
        offsetX = 0;
        offsetY = 0;
        
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
