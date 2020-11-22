import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

class Token {
	enum TokenType {
		GPR,
		FPR,
		KEYWORD,
		ERROR
	}
	
	public String identifyToken(int val) {
		
		if (val >= 3 && val <= 7) {
			return TokenType.GPR.toString(); 
		} else if (val >= 9 && val <= 13) {
			return TokenType.FPR.toString();
		} else if (val == 23 || val == 22) {
			return TokenType.KEYWORD.toString();
		} else if (val == 0){
			return "";
		}else {
			return TokenType.ERROR.toString();
		}
	}
}


public class mipsScanner {
	
	// start state
	private static final int q0 = 0;
	private static final int q1 = 1; 
	
	// GPR
	private static final int g1 = 2;
	private static final int g2 = 3;
	private static final int g3 = 4;
	private static final int g4 = 5;
	private static final int g5 = 6;
	private static final int g6 = 7;
	
	// FPR
	private static final int f1 = 8;
	private static final int f2 = 9;
	private static final int f3 = 10;
	private static final int f4 = 11;
	private static final int f5 = 12;
	private static final int f6 = 13;
	
	// KEYWORD
	private static final int k1 = 14;
	private static final int k2 = 15;
	private static final int k3 = 16;
	private static final int k4 = 17;
	private static final int k5 = 18;
	private static final int k6 = 19;
	private static final int k7 = 20;
	private static final int k8 = 21;
	private static final int k9 = 22;
	private static final int k10 = 23;
	
	private static final int e = 24;
	
	private static int dfa (int s, char c) {
		switch (s) {
			case q0: switch(c) {
				case 'R': return g1;
				case '$': return q1;
				case 'F': return f1;
				case 'D': return k1;
				default: return e;
			}
			case q1: switch(c) {
				case '0': return g2;
				case '4': return g2;
				case '5': return g2;
				case '6': return g2;
				case '7': return g2;
				case '8': return g2;
				case '9': return g2;
				case '1': return g3;
				case '2': return g3;
				case '3': return g4;
				case 'F': return f1;
				default: return e;
			}
			
			case f1: switch(c) {
				case '0': return f2;
				case '4': return f2;
				case '5': return f2;
				case '6': return f2;
				case '7': return f2;
				case '8': return f2;
				case '9': return f2;
				case '1': return f3;
				case '2': return f3;
				case '3': return f4;
				default: return e;
			}
			case f2: switch(c) {
				default: return e;
			}
			case f3: switch(c) {
				case '0': return f5;
				case '1': return f5;
				case '2': return f5;
				case '3': return f5;
				case '4': return f5;
				case '5': return f5;
				case '6': return f5;
				case '7': return f5;
				case '8': return f5;
				case '9': return f5;
				default: return e;
			}
			case f4: switch(c) {
				case '0': return f6;
				case '1': return f6;
				default: return e;
			}
			case f5: switch(c) {
				default: return e;
			}
			case f6: switch(c) {
				default: return e;
			}
			
			case g1: switch(c) {
				case '0': return g2;
				case '4': return g2;
				case '5': return g2;
				case '6': return g2;
				case '7': return g2;
				case '8': return g2;
				case '9': return g2;
				case '1': return g3;
				case '2': return g3;
				case '3': return g4;
				default: return e;
			}
			case g2: switch(c) {
				default: return e;
			}
			case g3: switch(c) {
				case '0': return g5;
				case '1': return g5;
				case '2': return g5;
				case '3': return g5;
				case '4': return g5;
				case '5': return g5;
				case '6': return g5;
				case '7': return g5;
				case '8': return g5;
				case '9': return g5;
				default: return e;
			}
			case g4: switch(c) {
				case '0': return g6;
				case '1': return g6;
				default: return e;
			}
			case g5: switch(c) {
				default: return e;
			}
			case g6: switch(c) {
				default: return e;
			}
			case k1: switch(c) {
				case 'A': return k2;
				case 'M': return k6;
				default: return e;
			}
			case k2: switch(c) {
				case 'D': return k3;
				default: return e;
			}
			case k3: switch(c) {
				case 'D': return k4;
				default: return e;
			}
			case k4: switch(c) {
				case 'I': return k5;
				case 'U': return k10;
				default: return e;
			}
			case k5: switch(c) {
				case 'U': return k10;
				default: return e;
			}
			case k6: switch(c) {
				case 'U': return k7;
				default: return e;
			}
			case k7: switch(c) {
				case 'L': return k8;
				default: return e;
			}
			case k8: switch(c) {
				case 'T': return k9;
				default: return e;
			}
			case k9: switch(c) {
				case 'U': return k10;
				default: return e;
			}
			// keyword end state
			case k10: switch(c) {
				default: return e;
			}
			default: return e;
		}
	}
	
	
	public static int state;
	
	public static void main (String[] args) throws IOException {
		ArrayList<String[]> tokenLine = new ArrayList<>();
		BufferedWriter bfw = null;
		Token token = new Token();
		
		try {
			
			BufferedReader bfr = new BufferedReader(new FileReader("C:\\Users\\Raeanne\\Downloads\\input.txt"));
			bfw = new BufferedWriter(new FileWriter("C:\\Users\\Raeanne\\Downloads\\output.txt"));
			
			String line;

			while ((line = bfr.readLine()) != null) {
				String[] tokens = line.replaceAll(", ", " ").split("\\s");
				tokenLine.add(tokens);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		for(int i=0; i<tokenLine.size(); i++) {

			int arrLength = tokenLine.get(i).length;
			
			for(int j=0; j<arrLength; j++) {
				String word = tokenLine.get(i)[j];
				
				for(char letter: word.toCharArray()) {
					state = dfa(state, letter);
				}
				
				// identify the token type of the final state
				String tokenType = token.identifyToken(state);
				
				// writes the output to text file and restart the state
				bfw.write(tokenType + ((j == arrLength-1) ? "\n" : " "));
				state = 0;
			}
		}
		
		bfw.close();
	}
}