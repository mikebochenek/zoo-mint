package zoo.mb.mint.kata;

public class CodiliPracticeWednesday {

	public static void main(String[] args) {
		System.out.println("it's Wednesday, not Thursday");
		System.out.println(nestingSolution("))))"));
		System.out.println(bracketsSolution("{[()()]}"));
		System.out.println(bracketsSolution("([)()]"));
		System.out.println(bracketsSolution("{()(){}[]}"));
		System.out.println(nestingSolution("(()(())())"));
		System.out.println(nestingSolution("())"));
		System.out.println(nestingSolution(")()"));
		System.out.println(nestingSolution(")()"));
	}
	
	/**
	 * 7-stacks_and_queues/nesting/
	 * Nesting
	 * 
	 * A string S consisting of N characters is called properly nested if:
	 *   S is empty;
	 *   S has the form "(U)" where U is a properly nested string;
	 *   S has the form "VW" where V and W are properly nested strings.
	 * For example, string "(()(())())" is properly nested but string "())" isn't.
	 * 
	 * Write a function: that, given a string S consisting of N characters, returns 1 if string S is properly nested and 0 otherwise.
	 * For example, given S = "(()(())())", the function should return 1 and given S = "())", the function should return 0, as explained above.
	 * Assume that:
	 *   N is an integer within the range [0..1,000,000];
	 *   string S consists only of the characters "(" and/or ")".
	 * Complexity:
	 *   expected worst-case time complexity is O(N);
	 *   expected worst-case space complexity is O(1) (not counting the storage required for input arguments).
	 */
	public static int nestingSolution(String S) {
		int x = 0;
		for (int i = 0; i < S.length(); i++) {
			char c = S.charAt(i);
			if (')' == c && x == 0) return 0;
			if ('(' == c) x++;
			if (')' == c && x != 0) x--;
		}
		return (x == 0 ? 1 : 0);
	}

	
	/**
	 * lessons/7-stacks_and_queues/brackets/
	 * a string S consisting of N characters is considered to be properly nested if any of the following conditions is true:
	 * 
	 * S is empty;
	 * S has the form "(U)" or "[U]" or "{U}" where U is a properly nested string;
	 * S has the form "VW" where V and W are properly nested strings.
	 * 
	 * Assume that:
	 *   N is an integer within the range [0..200,000];
	 *   string S consists only of the following characters: "(", "{", "[", "]", "}" and/or ")".
	 * Complexity:
	 *   expected worst-case time complexity is O(N);
	 *   expected worst-case space complexity is O(N) (not counting the storage required for input arguments).
	 */
	public static int bracketsSolution(String S) {
		char[] stack = new char[S.length()];
		short sIdx = 0;
		
		for (int i = 0; i < S.length(); i++) {
			char c = S.charAt(i);
			if (c == '{' || c == '(' || c == '[') {
				stack[sIdx++] = c;
			}
			if (c == '}') {
				if (sIdx > 0 && stack[sIdx - 1] == '{') {
					stack[sIdx--] = ' ';
				} else {
					return 0;
				}
			}
			if (c == ')') {
				if (sIdx > 0 && stack[sIdx - 1] == '(') {
					stack[sIdx--] = ' ';
				} else {
					return 0;
				}
			}
			if (c == ']') {
				if (sIdx > 0 && stack[sIdx - 1] == '[') {
					stack[sIdx--] = ' ';
				} else {
					return 0;
				}
			}
		}
			
		return (sIdx == 0 ? 1 : 0);
	}
	
	public static int bracketsNaiveSolution(String S) {
		short x = 0; // {}
		short y = 0; // ()
		short z = 0; // []
		char lastChar = ' ';
		for (int i = 0; i < S.length(); i++) {
			char c = S.charAt(i);
			if (c == '{') x++;
			if (c == '}' && (lastChar == '[' || lastChar == '(')) return 0; // error because of unmatched }
			if (c == '}') x--;
			
			if (c == '(') y++;
			if (c == ')' && (lastChar == '[' || lastChar == '{')) return 0; // error because of unmatched )
			if (c == ')') y--;
			
			if (c == '[') z++;
			if (c == ']' && (lastChar == '{' || lastChar == '(')) return 0;
			if (c == ']') z--;
			
			lastChar = c;
		}
		return (x == 0 && y == 0 && z == 0 ? 1 : 0);
	}
	
	public static int bracketsWrongSolution(String s) {
		short x = 0; // {}
		short y = 0; // ()
		short z = 0; // []
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == '{') x++;
			if (c == '}' && x == 0) return 0; // error because of unmatched }
			if (c == '}') x--;
			
			if (c == '(') y++;
			if (c == ')' && y == 0) return 0; // error because of unmatched )
			if (c == ')') y--;
			
			if (c == '[') z++;
			if (c == ']' && z == 0) return 0;
			if (c == ']') z--;
		}
		return (x == 0 && y == 0 && z == 0 ? 1 : 0);
	}
}
