

public class Parser {
	public static final int _EOF = 0;
	public static final int _zahl = 1;
	public static final int _wort = 2;
	public static final int _ergebnisTrenner = 3;
	public static final int maxT = 8;

	static final boolean T = true;
	static final boolean x = false;
	static final int minErrDist = 2;

	public Token t;    // last recognized token
	public Token la;   // lookahead token
	int errDist = minErrDist;
	
	public Scanner scanner;
	public Errors errors;

	

	public Parser(Scanner scanner) {
		this.scanner = scanner;
		errors = new Errors();
	}

	void SynErr (int n) {
		if (errDist >= minErrDist) errors.SynErr(la.line, la.col, n);
		errDist = 0;
	}

	public void SemErr (String msg) {
		if (errDist >= minErrDist) errors.SemErr(t.line, t.col, msg);
		errDist = 0;
	}
	
	void Get () {
		for (;;) {
			t = la;
			la = scanner.Scan();
			if (la.kind <= maxT) {
				++errDist;
				break;
			}

			la = t;
		}
	}
	
	void Expect (int n) {
		if (la.kind==n) Get(); else { SynErr(n); }
	}
	
	boolean StartOf (int s) {
		return set[s][la.kind];
	}
	
	void ExpectWeak (int n, int follow) {
		if (la.kind == n) Get();
		else {
			SynErr(n);
			while (!StartOf(follow)) Get();
		}
	}
	
	boolean WeakSeparator (int n, int syFol, int repFol) {
		int kind = la.kind;
		if (kind == n) { Get(); return true; }
		else if (StartOf(repFol)) return false;
		else {
			SynErr(n);
			while (!(set[syFol][kind] || set[repFol][kind] || set[0][kind])) {
				Get();
				kind = la.kind;
			}
			return StartOf(syFol);
		}
	}
	
	void Logdatei() {
		Datensatz();
		while (la.kind == 4) {
			Datensatz();
		}
		System.out.println(Highscore.getHighscore()); 
	}

	void Datensatz() {
		Spielergebnis result; String mail; int maxPoints = 0;
		mail = Account();
		result = Spielergebnis();
		if (result.isDatumValid() && result.isUhrzeitValid())  { 
		   if(result.getErreichtePunkte() > maxPoints){
		       maxPoints = result.getErreichtePunkte();                                            
		   }
		   Highscore.addSpiele();
		Highscore.addVerbauteTetrisSteine(result.getVerbauteTetrisSteine());
		   Highscore.setMaxSpiel(result.getErreichtePunkte(), mail,result.getSpielDauer());
		} 
		
		while (la.kind == 1) {
			result = Spielergebnis();
			if (result.isDatumValid() && result.isUhrzeitValid())  { 
			   if(result.getErreichtePunkte() > maxPoints){
			       maxPoints = result.getErreichtePunkte();                                           
			   }
			   Highscore.addSpiele();
			   Highscore.addVerbauteTetrisSteine(result.getVerbauteTetrisSteine());
			   Highscore.setMaxSpiel(result.getErreichtePunkte(), mail,result.getSpielDauer());
			} 
			
		}
		System.out.println("Spieler " + mail + " hat maximal folgende Punktezahl erreicht: " + maxPoints); 
	}

	String  Account() {
		String  mailAddress;
		Expect(4);
		mailAddress = mail();
		return mailAddress;
	}

	Spielergebnis  Spielergebnis() {
		Spielergebnis  result;
		result = new Spielergebnis();
		boolean datumValid = Datum();
		result.setDatumValid(datumValid); 
		boolean uhrzeitValid = Uhrzeit();
		result.setUhrzeitValid(uhrzeitValid); 
		Expect(1);
		int spieldauer = Spieldauer();
		result.setSpielDauer(spieldauer); 
		int erreichtePunkte = ErreichtePunkte();
		result.setErreichtePunkte(erreichtePunkte); 
		int verbauteTetrisSteine = VerbauteTetrisSteine();
		result.setVerbauteTetrisSteine(verbauteTetrisSteine); 
		Expect(1);
		Expect(3);
		return result;
	}

	boolean  Datum() {
		boolean  datumValid;
		datumValid = true; int tag = -1; int monat = -1; int jahr = -1;
		Tag();
		tag = Integer.parseInt(t.val); 
		Expect(6);
		Monat();
		monat = Integer.parseInt(t.val); 
		Expect(6);
		Jahr();
		jahr = Integer.parseInt(t.val); 
		datumValid = Spielergebnis.isDateValid(tag + "." + monat + "." + jahr); 
		return datumValid;
	}

	boolean  Uhrzeit() {
		boolean  uhrzeitValid;
		uhrzeitValid = true; int stunden = -1; int minuten = -1; int sekunden = -1;
		Stunden();
		stunden = Integer.parseInt(t.val); 
		Expect(7);
		Minuten();
		minuten = Integer.parseInt(t.val); 
		Expect(7);
		Sekunden();
		sekunden = Integer.parseInt(t.val); 
		uhrzeitValid = Spielergebnis.isUhrValid(stunden + ":" + minuten + ":" + sekunden); 
		return uhrzeitValid;
	}

	int  Spieldauer() {
		int  spieldauer;
		Expect(1);
		spieldauer = Integer.parseInt(t.val); 
		return spieldauer;
	}

	int  ErreichtePunkte() {
		int  erreichtePunkte;
		Expect(1);
		erreichtePunkte = Integer.parseInt(t.val); 
		return erreichtePunkte;
	}

	int  VerbauteTetrisSteine() {
		int  verbauteTetrisSteine;
		Expect(1);
		verbauteTetrisSteine = Integer.parseInt(t.val); 
		return verbauteTetrisSteine;
	}

	String  mail() {
		String  mail;
		Username();
		mail = t.val + "@"; 
		Expect(5);
		Mailserver();
		mail += t.val; 
		return mail;
	}

	void Username() {
		Expect(2);
	}

	void Mailserver() {
		Expect(2);
	}

	void Tag() {
		Expect(1);
	}

	void Monat() {
		Expect(1);
	}

	void Jahr() {
		Expect(1);
	}

	void Stunden() {
		Expect(1);
	}

	void Minuten() {
		Expect(1);
	}

	void Sekunden() {
		Expect(1);
	}



	public void Parse() {
		la = new Token();
		la.val = "";		
		Get();
		Logdatei();
		Expect(0);

	}

	private static final boolean[][] set = {
		{T,x,x,x, x,x,x,x, x,x}

	};


	public String ParseErrors(){
			java.io.PrintStream oldStream = System.out;
		
		java.io.OutputStream out = new java.io.ByteArrayOutputStream();
		java.io.PrintStream newStream = new java.io.PrintStream(out);
		
		errors.errorStream = newStream;
		
		Parse();

		String errorStream = out.toString();
		errors.errorStream = oldStream;

		return errorStream;


	}
} // end Parser


class Errors {
	public int count = 0;                                    // number of errors detected
	public java.io.PrintStream errorStream = System.out;     // error messages go to this stream
	public String errMsgFormat = "-- line {0} col {1}: {2}"; // 0=line, 1=column, 2=text
	
	protected void printMsg(int line, int column, String msg) {
		StringBuffer b = new StringBuffer(errMsgFormat);
		int pos = b.indexOf("{0}");
		if (pos >= 0) { b.delete(pos, pos+3); b.insert(pos, line); }
		pos = b.indexOf("{1}");
		if (pos >= 0) { b.delete(pos, pos+3); b.insert(pos, column); }
		pos = b.indexOf("{2}");
		if (pos >= 0) b.replace(pos, pos+3, msg);
		errorStream.println(b.toString());
	}
	
	public void SynErr (int line, int col, int n) {
		String s;
		switch (n) {
			case 0: s = "EOF expected"; break;
			case 1: s = "zahl expected"; break;
			case 2: s = "wort expected"; break;
			case 3: s = "ergebnisTrenner expected"; break;
			case 4: s = "\"Account:\" expected"; break;
			case 5: s = "\"@\" expected"; break;
			case 6: s = "\".\" expected"; break;
			case 7: s = "\":\" expected"; break;
			case 8: s = "??? expected"; break;
			default: s = "error " + n; break;
		}
		printMsg(line, col, s);
		count++;
	}

	public void SemErr (int line, int col, String s) {	
		printMsg(line, col, s);
		count++;
	}
	
	public void SemErr (String s) {
		errorStream.println(s);
		count++;
	}
	
	public void Warning (int line, int col, String s) {	
		printMsg(line, col, s);
	}
	
	public void Warning (String s) {
		errorStream.println(s);
	}
} // Errors


class FatalError extends RuntimeException {
	public static final long serialVersionUID = 1L;
	public FatalError(String s) { super(s); }
}
