package opidstest.data;


import java.io.Serializable;
import java.util.List;

public class DataSet implements Serializable {
    private List<List<? extends Object>> data;
    private List<Integer> labels;
    private int nSamples;
    private int dSamples;
    private int k;

    public int getK() {
        return k;
    }

    protected void setK(int k) {
        this.k = k;
    }

    public List<List<? extends Object>> getData() {
        return data;
    }

    public void setData(List<List<? extends Object>> data) {
        this.data = data;
    }

    public List<Integer> getLabels() {
        return labels;
    }

    protected void setLabels(List<Integer> labels) {
        this.labels = labels;
    }

    public int getNSamples() {
        return nSamples;
    }

    protected void setNSamples(int nSamples) {
        this.nSamples = nSamples;
    }

    public int getDSamples() {
        return dSamples;
    }

    protected void setDSamples(int dSamples) {
        this.dSamples = dSamples;
    }

    public static DataSet getDataSet(String dataset){
        if (dataset.equals("PGJFiles")) return new PGJFiles();
        return null;
    }

}
