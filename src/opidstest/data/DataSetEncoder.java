package opidstest.data;

import opidstest.data.DataSet;

public interface DataSetEncoder {
    void encodeDataSet(DataSet dataSet);
    DataSet getEncodedDataSet();
}
