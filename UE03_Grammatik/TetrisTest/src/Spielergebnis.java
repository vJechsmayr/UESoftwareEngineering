import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class Spielergebnis {
	private boolean uhrzeitValid;
	private boolean datumValid;
	private int verbauteTetrisSteine;
	private int erreichtePunkte;	
	private int spielDauer;
	
	public int getSpielDauer() {
		return spielDauer;
	}
	
	public void setSpielDauer(int dauer) {
		spielDauer = dauer;
	}
	
	public boolean isUhrzeitValid() {
		return uhrzeitValid;
	}
	public void setUhrzeitValid(boolean uhrzeitValid) {
		this.uhrzeitValid = uhrzeitValid;
	}
	public boolean isDatumValid(){
		return datumValid;
	}
	public void setDatumValid(boolean datumValid) {
		this.datumValid = datumValid;
	}

	public int getVerbauteTetrisSteine() {
		return verbauteTetrisSteine;
	}
	public void setVerbauteTetrisSteine(int verbauteTetrisSteine) {
		
		this.verbauteTetrisSteine = verbauteTetrisSteine;
	}
	public int getErreichtePunkte() {
		return erreichtePunkte;
	}
	public void setErreichtePunkte(int erreichtePunkte) {
		this.erreichtePunkte = erreichtePunkte;
	}
	
	final static String DATE_FORMAT = "dd.MM.yyyy";
	final static String UHR_FORMAT = "HH:mm:ss";

	public static boolean isDateValid(String date) 
	{
	        try {
	            DateFormat df = new SimpleDateFormat(DATE_FORMAT);
	            df.setLenient(false);
	            df.parse(date);
	            return true;
	        } catch (ParseException e) {
	            return false;
	        }
	}
	public static boolean isUhrValid(String uhr) 
	{
	        try {
	            DateFormat uf = new SimpleDateFormat(UHR_FORMAT);
	            uf.setLenient(false);
	            uf.parse(uhr);
	            return true;
	        } catch (ParseException e) {
	        	System.out.println(e.getMessage());
	            return false;
	        }
	}
}
