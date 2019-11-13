/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;
import java.util.List;
import kMeans.KMeans;
import kMeans.Point;
import opidstest.data.DataSet;
import opidstest.data.DataSetProvider;
import opidstest.data.DataSetEncoder;
import opidstest.data.Normalization;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import utilities.Range;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.StructType;

/**
 *
 * @author Oko
 */
public class test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        sectionB();
    }
    
    public static void sectionA() {
        System.out.println("--- Section A ---");
        System.out.println("Loading dataset...");
        DataSetProvider dataSetProvider = (DataSetProvider) DataSet.getDataSet("PGJFiles");
        dataSetProvider.loadDataSet();
        
        System.out.println("Normalizing dataset...");
        DataSetEncoder dataSetEncoder = new Normalization(new Range(0.01, 100));
        dataSetEncoder.encodeDataSet(dataSetProvider.getDataSet());        
        
        System.out.println("Getting dataset information...");
        List<Point> categoryAndGeopoint = new ArrayList<>();
        for (List<? extends Object> o : dataSetEncoder.getEncodedDataSet().getData()) {
            Point p = new Point((Float) o.get(0), (Float) o.get(1), (Float) o.get(2));
            categoryAndGeopoint.add(p);
        }        

        System.out.println("kMeans clusterization process...");
    	KMeans kmeans = new KMeans(4, categoryAndGeopoint.size());
    	kmeans.init(categoryAndGeopoint);
    	kmeans.calculate();
        kmeans.plotClusters(); 
        
        System.out.println("Process succesfully finished.");
    }
    
    
    public static void sectionB() {

        SparkSession spark = SparkSession
            .builder()
            .appName("Java Spark SQL Example")
            .getOrCreate();
            
        StructType schema = new StructType()
        .add("department", "string")
        .add("designation", "string")
        .add("ctc", "long")
        .add("state", "string");

        Dataset<Row> df = spark.read()
        .option("mode", "DROPMALFORMED")
        .schema(schema)
        .csv("hdfs://path/test.csv");
        
        df.createOrReplaceTempView("employee");
        
        Dataset<Row> sqlResult = spark.sql(
            "SELECT department, designation, state, SUM(ctc), COUNT(department)" 
            + " FROM employee GROUP BY department, designation, state");

        sqlResult.show(); //for testing
    }
    
}
