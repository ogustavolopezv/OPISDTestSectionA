package opidstest.data;

import utilities.Range;
import utilities.Utilities;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Normalization extends DataSet implements DataSetEncoder, Serializable {
    private final Range range;

    public Normalization(Range Range) {

        this.range = Range;

    }
    
    public Normalization() {

        this.range = new Range(0, 1);

    }    

    private void calculateNormalization(DataSet dataSet) {
        List<List<? extends Number>> data = new ArrayList<>();
        for (List l : dataSet.getData()) {
            List<Number> internList = new ArrayList<>();
            for (Object o : l) {
                Number n = (Number)o;
                internList.add(n);
            }
            data.add(internList);
        }

        if (!data.isEmpty()) {
            final int n = data.size();
            final int d = data.get(0).size();

            List<List<Float>> floatEncodedData = new ArrayList<>();
            for (int i = 0; i < n; i++) {floatEncodedData.add(new ArrayList<>());}
            List<List<? extends Number>> dataT = Utilities.transpose(data);

            // For each dimension
            for (int i = 0; i < d; i++) {
                Float min = Utilities.getMin(dataT.get(i)); // 0f;
                Float max = Utilities.getMax(dataT.get(i)); //255f;
                Float a = this.range.getMin().floatValue();
                Float b = this.range.getMax().floatValue();
                Float range = max - min;

                // For each vector
                for (int x = 0; x < n; x++) {
                    float y = (((data.get(x).get(i).floatValue() - min) / (range)) * (b - a)) + (a);
                    floatEncodedData.get(x).add(y);
                }
            }
            setData(new ArrayList<>(floatEncodedData));
            setLabels(dataSet.getLabels());
            setDSamples(d);
            setK(dataSet.getK());
        }
    }

    @Override
    public void encodeDataSet(DataSet dataSet) {
        setNSamples(dataSet.getLabels().size());
        calculateNormalization(dataSet);
    }

    @Override
    public DataSet getEncodedDataSet() {
        return this;
    }
}
