package zoo.mb.kagg.wint;

public class ColumnValue implements Comparable<ColumnValue>{

	protected Double value = Double.NaN; //TODO but null is not really the same as zero...
	protected Data reverseLink;
	
	public ColumnValue(Double v, Data d) {
		value = v == null ? value : v;
		reverseLink = d;
	}
	
    @Override
    public int compareTo(ColumnValue o) {
        return value.compareTo(o.value);
    }
    @Override
    public String toString() {
        return String.valueOf("id=" + reverseLink.id + " value=" + value);
    }
}
