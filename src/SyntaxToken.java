

public class SyntaxToken {
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
