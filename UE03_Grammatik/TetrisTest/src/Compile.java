
public class Compile {
	
	public static void main (String [] args) {
		Scanner scanner = new Scanner("src/TetrisLog.txt");
		Parser parser = new Parser(scanner);
		parser.Parse();
		System.out.println(parser.errors.count + " errors detected");
		
	}

}
