import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

  public static void main(String[] args) throws IOException {
    int state = 0;
    ArrayList<String[]> tokenLine = new ArrayList<>();
    BufferedReader bfr = null;
    BufferedWriter bfw = null;

    try {
      bfr =
        new BufferedReader(
          new FileReader("C:\\Users\\Raeanne\\Downloads\\input.txt")
        );

      bfw =
        new BufferedWriter(
          new FileWriter("C:\\Users\\Raeanne\\Downloads\\output.txt")
        );

      String line;

      while ((line = bfr.readLine()) != null) {
        String[] tokens = line.replaceAll(", ", " ").split("\\s");
        tokenLine.add(tokens);
      }

      bfr.close();
    } catch (Exception e) {
      e.printStackTrace();
    }

    SyntaxToken token = new SyntaxToken();
    MipsScanner dfaScanner = new MipsScanner();

    for (int i = 0; i < tokenLine.size(); i++) {
      int arrLength = tokenLine.get(i).length;

      for (int j = 0; j < arrLength; j++) {
        String word = tokenLine.get(i)[j];

        for (char letter : word.toCharArray()) {
          state = dfaScanner.dfa(state, letter);
        }

        // identify the token type of the final state
        String tokenType = token.identifyToken(state);

        // writes the output to text file and restart the state
        bfw.write(tokenType + ((j == arrLength - 1) ? "\n" : " "));
        state = 0;
      }
    }

    bfw.close();
  }
}
