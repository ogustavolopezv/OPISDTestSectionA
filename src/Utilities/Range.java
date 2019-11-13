package utilities;

import java.io.Serializable;

public class Range implements Serializable {

    private Number min;
    private Number max;

    public Range(Number min, Number max){

        this.min = min;
        this.max = max;

    }

    public Number getMin() {
        return min;
    }

    public Number getMax() {
        return max;
    }

}
