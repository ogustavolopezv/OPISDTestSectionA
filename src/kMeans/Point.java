package kMeans;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
 
public class Point {
 
    private float x;
    private float y;
    private float z;

    private int cluster_number = 0;    
    
    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }
 
    public Point(float x, float y)
    {
        this.setX(x);
        this.setY(y);
    }
    
    public Point(float x, float y, float z)
    {
        this.setX(x);
        this.setY(y);
        this.setZ(z);
    }
    
    public void setX(float x) {
        this.x = x;
    }
    
    public float getX()  {
        return this.x;
    }
    
    public void setY(float y) {
        this.y = y;
    }
    
    public float getY() {
        return this.y;
    }
    
    public void setCluster(int n) {
        this.cluster_number = n;
    }
    
    public int getCluster() {
        return this.cluster_number;
    }
    
    protected static double distance(Point p, Point centroid) {
        return Math.sqrt(Math.pow((centroid.getY() - p.getY()), 2) + Math.pow((centroid.getX() - p.getX()), 2) + Math.pow((centroid.getZ() - p.getZ()), 2));        
    }
    
    //Creates random point
    protected static Point createRandomPoint(int min, int max) {
    	Random r = new Random();
    	float x = min + (max - min) * r.nextFloat();
    	float y = min + (max - min) * r.nextFloat();
        float z = min + (max - min) * r.nextFloat();
    	return new Point(x,y,z);
    }
    
    protected static List createRandomPoints(int min, int max, int number) {
    	List points = new ArrayList(number);
    	for(int i = 0; i < number; i++) {
    		points.add(createRandomPoint(min,max));
    	}
    	return points;
    }
    
    public String toString() {
    	return ""+x+"\t"+y+"\t"+z+"";
    }
}