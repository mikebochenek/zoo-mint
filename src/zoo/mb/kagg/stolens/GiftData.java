package zoo.mb.kagg.stolens;

public class GiftData implements Comparable<GiftData>{
	int id;
	double longitude;
	double latitude;
	double weight;
	
	int tripID;

	public GiftData(String line) {
		String[] s = line.split(",");
		id = Integer.parseInt(s[0]);
		longitude = Double.parseDouble(s[1]);
		latitude = Double.parseDouble(s[2]);
		weight = Double.parseDouble(s[3]);
	}

	public int getTripID() {
		return tripID;
	}

	public void setTripID(int tripID) {
		this.tripID = tripID;
	}
	
	public String toLine() {
		return "" + id + "," + getTripID();
	}

    @Override
    public int compareTo(GiftData o) {
    	
    	/*
    	if (timezone(latitude) == timezone(o.latitude)) {
    		// return new Double(Haversine.haversine(latitude, longitude, o.latitude, o.longitude)).intValue();
    		return new Double((longitude+180) - (o.longitude+180)).intValue();
    	} else {
    		return timezone(latitude) - timezone(o.latitude);
    	}
    	*/
    	
    	return id - o.id;
    }
    
    private int timezone(double d) {
    	return new Double((d+180) / 10).intValue();
    }
    
	@Override
	public String toString() {
		return "GiftData [id=" + id + ", longitude=" + longitude + ", latitude=" + latitude + ", weight=" + weight
				+ "]";
	}
}
