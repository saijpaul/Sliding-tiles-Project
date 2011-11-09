package com.softengg;

public class Tile 
 
{
        public int getmColor() {
		return mColor;
	}

	public void setmColor(int mColor) {
		this.mColor = mColor;
	}

	public int getmNumber() {
		return mNumber;
	}

	public void setmNumber(int mNumber) {
		this.mNumber = mNumber;
	}

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