package com.softengg;

public class Tile 
 
{
        public int mColor;
        public int mNumber;
        
     
    public Tile ()
    {
    	mNumber =0;
    	mColor = 0xffffffff;
    }

    public Tile(int number, int color) {                
                mNumber = number;
                mColor = color;
        }
        

}