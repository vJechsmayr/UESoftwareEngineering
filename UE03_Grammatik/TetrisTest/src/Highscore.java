

public class Highscore {
	private static int maxSpielPunkte;
	private static int maxSpielDauer = 0;
	private static int spielDauer = 0;
	private static int spiele = 0;
	private static int verbauteTetrisSteine = 0;
	private static String maxSpielerName = "";
	
	public static int getMaxSpielPunkte() {
		return maxSpielPunkte;
	}
	
	 public static void setMaxSpiel(int maxSpielPunkteParam, String maxSpielerNameParam, int maxSpielDauerParam) {
		if(maxSpielDauer < maxSpielDauerParam){
			maxSpielPunkte = maxSpielPunkteParam;
			maxSpielerName = maxSpielerNameParam;
			maxSpielDauer = maxSpielDauerParam;
		}
	}
	
	public static String getMaxSpielerName(){
		return maxSpielerName;
	}
		
	
	public static int getMaxSpielDauer(){
		return maxSpielDauer;
	}
	
	public static int getVerbauteTetrisSteine() {
		return verbauteTetrisSteine;
	}
	public static void addVerbauteTetrisSteine(int verbauteTetrisSteineParam) {

		verbauteTetrisSteine += verbauteTetrisSteineParam;
	}
	
	public static int getSpiele(){
		return spiele;
	}
	
	public static void addSpiele(){
		spiele++;
	}
	
	public static int getSpielDauer(){
		return spielDauer;
	}
	
	public static void setSpielDauer(int spielDauerParam){
		spielDauer+= spielDauerParam;
	}
	
	
	public static String getHighscore(){
		if(getSpiele() == 0){
			return "Noch keine Spiele";
		}
		return "\nDurchschnittliche Anzahl verbaute Tetrissteine: " + getVerbauteTetrisSteine()/getSpiele() + "\nBester Spieler: " + getMaxSpielerName() + " Spieldauer:  " + getMaxSpielDauer() + " Punkteanzahl: " + getMaxSpielPunkte();
		
	}
}

