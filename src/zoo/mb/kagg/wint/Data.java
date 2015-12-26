package zoo.mb.kagg.wint;

public class Data {

	protected int id;
	protected Double[] feature = new Double[25];
	protected Double ret_MinusTwo;
	protected Double ret_MinusOne;
	protected Double[] ret = new Double[179]; /* ret2 to ret180 */
	protected Double ret_PlusOne;
	protected Double ret_PlusTwo;
	protected Double weight_Intraday;
	protected Double weight_Daily;
	
	public Data(String line) {
		String[] s = line.split(",");
		id = Integer.parseInt(s[0]);
		for (int i = 0; i < 25; i++) {
			String x = s[i+1];
			if (x != null && x.length() > 0) {
				feature[i] = Double.parseDouble(x);
			}
		}
		ret_MinusTwo = s[26].length() == 0 ? null : Double.parseDouble(s[26]);
		ret_MinusOne = s[27].length() == 0 ? null : Double.parseDouble(s[27]);
		
		for (int i = 0; i < 179 && i < s.length - 28; i++) {
			String x = s[i + 28];
			if (x != null && x.length() > 0) {
				ret[i] = Double.parseDouble(x);
			}
		}
		
		if (s.length > 200) {
			ret_PlusOne = s[207].length() == 0 ? null : Double.parseDouble(s[207]);
			ret_PlusTwo = s[208].length() == 0 ? null : Double.parseDouble(s[208]);
			weight_Intraday = s[209].length() == 0 ? null : Double.parseDouble(s[209]);
			weight_Daily = s[210].length() == 0 ? null : Double.parseDouble(s[210]);
		}
	}
	
	public String toString() { 
		return "id=" + id + " feature1=" + feature[0] + " feature25=" + feature[24]
				+ " ret_MinusTwo=" + ret_MinusTwo + " ret_MinusOne=" + ret_MinusOne
				+ " ret2=" + ret[0] + " ret180=" + ret[178] 
				+ " ret_PlusOne=" + ret_PlusOne + " ret_PlusTwo=" + ret_PlusTwo
				+ " weight_Intraday=" + weight_Intraday + " weight_Daily=" + weight_Daily;
	}
}
