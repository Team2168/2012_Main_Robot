package us.zan.FRCCamera;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author James, Jun, Zack
 */
public class FRCCamera{

   /**
    * @param args the command line arguments
    */
	/*
     * thresholds:
     * 1 is hueMin
     * 2 is hueMax
     * 3 is satMin
     * 4 is satMax
     * 5 is valMin
     * 6 is valMax
     */
    static JSlider[] thresholdSliders = new JSlider[6];
    static JFrame imageFrame;
    static JLabel imageLabel;
    static BufferedImage srcImage;
    static int[] white = { 255, 255, 255 };
    static int[] black = {0,0,0};
    static float[] thresholds = new float[6];
    static float[] RGB = new float[3];
    static float[] HSV = new float[3];
    
	
   static final int CHECKED_FLAG = 2;
   static WritableRaster pixelData;
   static int[][] binaryPixelData;// ={{1,1,1,1,0},{0,0,0,0,0},{0,1,1,0,0},{0,1,1,0,1},{0,0,0,0,1}};
   static int[][] blobPixelData;
   static int imageWidth;
   static int imageHeight;
   static ArrayList<Blob> blobs = new ArrayList<Blob>();
   static int numberOfLabels = 0;
   static ArrayList<Integer> labelTable = new ArrayList<Integer>();
   static HashMap<Integer, ArrayList<Integer>> blobLabelTable = new HashMap<Integer, ArrayList<Integer>>();

   public static void main(String[] args){
       try{
           srcImage = ImageIO.read(new File("/home/zack/Downloads/blueimage_close.jpeg"));
           pixelData = srcImage.getRaster();
           binaryPixelData = new int[pixelData.getWidth()][pixelData.getHeight()];
           imageWidth = binaryPixelData.length;
           imageHeight = binaryPixelData[0].length;
           blobPixelData = new int[pixelData.getWidth()][pixelData.getHeight()];
           /*
            * <!---------------------------------------------------------------------------------------------------------!>
            *          INITIALIZED BLOB LABEL ARRAY TO -1 SO NO RUN INTO ERRORS WITH INDEXING ZERO-BASED ARRAYS
            * <!---------------------------------------------------------------------------------------------------------!>
            */
           for(int y=0; y<imageHeight; y++){
               for(int x=0; x<imageWidth; x++){
            	   blobPixelData[x][y] = -1;
               }
           }
           
           imageLabel = new JLabel(new ImageIcon(srcImage));
           refreshThreshold();
           BufferedImage newImage = new BufferedImage(srcImage.getColorModel(), pixelData, true, null);
           ImageIO.write(newImage, "jpeg", new File("/home/zack/Downloads/_blueimage_close.jpeg"));//"C:\\Documents and Settings\\user\\Desktop\\thresholded.jpeg"
           imageLabel.setIcon(new ImageIcon(newImage));
           
           JButton refreshImageButton = new JButton("New Image");
           JPanel imagePanel = new JPanel();
           JPanel topPanel = new JPanel();
           topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
           topPanel.add("imageButton", refreshImageButton);
           for(int i=0; i<6; i++){
               thresholdSliders[i] = new JSlider();
               thresholdSliders[i].setValue(0);
               thresholdSliders[i].setMinimum(0);
               thresholdSliders[i].setMaximum(100);
               final int x=i;
               thresholdSliders[i].addChangeListener(new ChangeListener(){
                   @Override
                   public void stateChanged(ChangeEvent ce) {
                       thresholds[x] = (float)thresholdSliders[x].getValue()/100;
                       //System.out.println(Float.toString(thresholds[x]));
                       refreshThreshold();
                   }
               });
               topPanel.add("threshold "+i, thresholdSliders[i]);
           }
           topPanel.add("image", imageLabel);            
           imageFrame = new JFrame("A Pretty Image GUI");
           imageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           refreshImageButton.addActionListener(new ActionListener(){
               @Override
               public void actionPerformed(ActionEvent ae) {
                   try {
                       srcImage = ImageIO.read(new File("/home/zack/Downloads/blueimage_close.jpeg"));
                       refreshThreshold();
                   } catch (IOException ex) {
                	   System.out.println(ex.toString());
                   }
               }
               
           });
           imageFrame.getContentPane().add(imagePanel);
           imageFrame.getContentPane().add(topPanel);
           imageFrame.pack();
           imageFrame.setLocation(200,200);
           imageFrame.setVisible(true);
           
//           blobDetection();
       }
       catch (IOException ex) {
           System.out.println(ex.toString());
       }
   }
   
   private static void refreshThreshold(){
       pixelData = srcImage.getRaster();
       for(int i=0; i<imageWidth; i++){
           for(int j=0; j<imageHeight; j++){
               pixelData.getPixel(i,j,RGB);
               Color.RGBtoHSB( (srcImage.getRGB(i,j) >> 16) & 0xFF , (srcImage.getRGB(i,j) >> 8) & 0xFF , srcImage.getRGB(i,j) & 0xFF, HSV);
               if(HSV[0] > thresholds[0] && HSV[0] <  thresholds[1] && HSV[1]>thresholds[2] && HSV[2] > thresholds[5]){
                   pixelData.setPixel(i,j,white);
                   binaryPixelData[i][j] = 1;
               }
               else{
                   pixelData.setPixel(i,j,black);
                   binaryPixelData[i][j] = 0;
               }
            }   
       }
       BufferedImage newImage = new BufferedImage(srcImage.getColorModel(), pixelData, true, null);
       //ImageIO.write(newImage, "jpeg", new File("C:\\Documents and Settings\\user\\Desktop\\thresholded.jpeg"));
       imageLabel.setIcon(new ImageIcon(newImage));
       blobDetection();
   }

   private static void blobDetection(){
	   /*
	    * STEP 1:
	    * loop through negative one-initialized picture 2d-array to label all blobs with kernel
	    */
       for(int y=0; y<imageHeight; y++){
           for(int x=0; x<imageWidth; x++){
               if(binaryPixelData[x][y]==1){//only need to call kernel if there is a blob at x,y 
                   kernel(x, y);
               }
           }
       }
       /*
        * STEP 2:
        * build blob to label data structure now 
        */
       int numberOfBlobs = 0;
       for(int i=0; i<numberOfLabels; i++){
    	   if(i==labelTable.get(i)){//if label is same as index, create new blob
    		   ArrayList<Integer> newBlob = new ArrayList<Integer>();
    		   newBlob.add(i);
    		   blobLabelTable.put(numberOfBlobs, newBlob);
    		   numberOfBlobs++;
    	   }
    	   if(i>labelTable.get(i)){//if index greater than label, then part of pre-existing blob
    		   //search for existing blob containing the same label already
    		   for(int j=0; j<numberOfBlobs; j++){//loop through all blobs to search
    			   if(blobLabelTable.get(j).contains(i)){//check if that blob contains label already, if does then add index to it
    				   blobLabelTable.get(j).add(i);
    			   }
    		   }
    	   }
       }
       /*
        * STEP 3:
        * construct blobs from blob to label table
        */
       for(int i=0; i<numberOfBlobs; i++){//loop through all blobs in blob to label table and make/add new blob to master arrayList of blobs
    	   Blob blob = new Blob();
    	   blobs.add(blob);
       }
       //loop through all pixels in 2d-array of labeled blobs to assign coordinates to proper blob
       for(int y=0; y<imageHeight; y++){
           for(int x=0; x<imageWidth; x++){
        	   /*
        	    * get the blob from blobs arraylist whose index is the value of this pixel
        	    * and add this pixel to that blob
        	    */
        	   if(blobPixelData[x][y]!=-1){//if a blob
        		   blobs.get(blobPixelData[x][y]).addPixelToBlob(x, y);
        	   }
           }
       }
       /*
        * PRINT
        */
       System.out.println("# of blobs: "+numberOfBlobs);
       for(int i=0; i<numberOfBlobs; i++){
//           if(blobs.get(i).getBlobSize()<3){
//               blobs.remove(blobs.get(i));
//           }
//           else{
               System.out.println("coordinates: "+blobs.get(i).printGetBlobCoordinates());
//           }
       }
   }

   private static void kernel(int x, int y){
       final int TOP = y-1;
       final int LEFT = x-1;
       final int RIGHT = x+1;
       int[] kernel = new int[4];
       if(LEFT>=0&&TOP>=0){
    	   kernel[0] = blobPixelData[LEFT][TOP];
       }
       if(TOP>=0){
    	   kernel[1] = blobPixelData[x][TOP];
       }
       if(RIGHT>imageWidth&&TOP>=0){
    	   kernel[2] = blobPixelData[RIGHT][TOP];
       }
       if(LEFT>=0){
    	   kernel[3] = blobPixelData[LEFT][y];
       }
       boolean newBlob = false;
       for(int i=0; i<4; i++){
           if(kernel[i]!=0){
               break;
           }
           if(i==4){
               newBlob = true;
           }
       }
       if(newBlob){
           blobPixelData[x][y] = numberOfLabels;
           labelTable.add(numberOfLabels, numberOfLabels);//add new blob to numberOfLabels index of arraylist
           numberOfLabels++;
       }
       else{
           int smallest = kernel[0];
           int largest = kernel[0];
           for(int i=1; i<4; i++){
               if(kernel[i]<smallest){
                   smallest = kernel[i];
               }
               if(kernel[i]>largest){
                   smallest = kernel[i];
               }
           }
           if(smallest==-1){
               smallest = largest;
           }
           if(smallest>0&&largest>0){//if this is not true then all not blobs in kernel
        	   blobPixelData[x][y] = smallest;
        	   labelTable.set(largest, labelTable.get(smallest));//set the larger label to the value of the smaller label
           }
       }
   }
   
   private static void colorBlobs(){
	   //float[][] colors = {{0,1,0.5},{90,1,0.5},{180,1,0.5},{270,1,0.5}};//4 colors, 3 vales per
   }
}