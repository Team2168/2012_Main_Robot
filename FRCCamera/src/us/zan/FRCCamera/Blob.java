/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package us.zan.FRCCamera;

import java.util.ArrayList;

/**
 *
 * @author user
 */
public class Blob {
    private int[] mostLeft = {0,0};
    private int[] mostRight= {0,0};
    private int[] mostTop= {0,0};
    private int[] mostBottom= {0,0};//[0] is x value, [1] is y value
    private int blobSize = 0;
    private int averageX = 0;
    private int averageY = 0;
    //instead use hashmap??
    private ArrayList<int[]> blobPixelCoordinates = new ArrayList<int[]>();
    
    public final int X = 0;
    public final int Y = 1;
    
    public int[] getMostLeft(){
        return this.mostLeft;
    }
    
    public int[] getMostRight(){
        return this.mostRight;
    }
    
    public int[] getMostTop(){
        return this.mostTop;
    }
    
    public int[] getMostBottom(){
        return this.mostBottom;
    }
    
    public int getBlobSize(){
        return this.blobSize;
    }
    
    public int getAverageX(){
        return this.averageX;
    }
    
    public int getAverageY(){
        return this.averageY;
    }
    
    public ArrayList<int[]> getBlobCoordinates(){
        return this.blobPixelCoordinates;
    }
    
    public String printGetBlobCoordinates(){
        String coordinates = "";
        int blobLength = blobPixelCoordinates.size();
        for(int l=0; l<blobLength; l++){
            coordinates += Integer.toString(blobPixelCoordinates.get(l)[0])+",";
            coordinates += Integer.toString(blobPixelCoordinates.get(l)[1])+" + ";
        }
        return coordinates;
    }
    
    public boolean isPixelInBlob(int x, int y){
        int[] pixelCoordinates = new int[2];
        pixelCoordinates[0] = x;
        pixelCoordinates[1] = y;
        int blobLength = blobPixelCoordinates.size();
        for(int i=0; i<blobLength; i++){
            if(blobPixelCoordinates.get(i)==pixelCoordinates){
                return true;//pixel in there
            }
        }
        return false;
    }
    
    public void addPixelToBlob(int x, int y){//0,0 is top most, left most pixel
        int[] pixel = {x, y};
        blobPixelCoordinates.add(pixel);
        this.blobSize = blobPixelCoordinates.size();
        averageX = ((blobSize-1)*averageX+x)/blobSize;
        averageY = ((blobSize-1)*averageY+y)/blobSize;
        if(pixel[0]<this.mostLeft[0]){
        	this.mostLeft=pixel;
        }
        if(pixel[0]>this.mostRight[0]){
        	this.mostRight=pixel;
        }
        if(pixel[0]<this.mostTop[0]){
        	this.mostTop=pixel;
        }
        if(pixel[0]>this.mostBottom[0]){
        	this.mostBottom=pixel;
        }
    }
    
    public void sortCoordinates(int axis){
        if(axis==X){
            
        }
        if(axis==Y){
            
        }
    }
}

