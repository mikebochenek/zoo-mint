package zoo.mb.hr;


public class AtoZ {

	static class Node {
		Node left;
		Node right;
		int value = -1;
		public Node() { }
		public Node(Node l, Node r, int v) {
			left = l;
			right = r;
			value = v;
		}
	}

	public static Node insert(Node n, int v) {
		if (n == null || n.value == -1) {
			n = new Node(null, null, v);
			return n;
		} else if (v < n.value) {
			n.left =  insert(n.left, v);
		} else {
			n.right = insert(n.right, v);
		}
		return n;
	}

	public static int badGlobalCounter = 0;
	public static Node search(Node n, int v) {
		badGlobalCounter++;
		if (n == null) {
			return null;
		} else if (n.value == v) {
			return n;
		} else if (v < n.value) {
			return search(n.left, v);
		} else if (v > n.value) {
			return search(n.right, v);
		} else {
			return null;
		}
	}
	
	public static int bstDistance(int[] values, int n, int node1, int node2) {
		Node root = new Node();
		for (int i = 0; i < values.length && i < n; i++) {
			root = insert(root, values[i]);
		}
		
		Node start = search(root, node1);
		if (start == null) { 
			return -1;
		} else {
			badGlobalCounter = 0;
			Node end = search(start, node2);
			if (end != null) {
				return badGlobalCounter;
			}
		}
		
//		for (int i = 0; i < values.length && i < n; i++) {
//		   System.out.println(search(root, values[i]));
//		}
		return -1;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] input = {"5", "-2", "4", "Z", "X", "9", "+", "+"};
		String[] input2 = {"1", "2", "+", "Z"};
		// System.out.println(total(input, input.length));
		
		int[] i = {5, 6, 3, 1, 2, 4};
		System.out.println(bstDistance(i, 6, 2, 4));
	}

	public static int total(String[] blocks, int n) {
		int total = 0;
		if (n != blocks.length) {
			//TODO error handling - n is actually redundant
		}
		
		java.util.List<Integer> s = new java.util.ArrayList<Integer>();
		
		for (int i = 0; i < blocks.length && i < n; i++) {
			if ("X".equals(blocks[i])) {
				if (s.size() > 0) {
					s.add(s.get(s.size() - 1) * 2);
				} //TODO else do some error handling
			} else if ("Z".equals(blocks[i])) {
				if (s.size() > 0) {
					s.remove(s.size() - 1);
				}
			} else if ("+".equals(blocks[i])) {
				if (s.size() > 1) {
					s.add(s.get(s.size() - 1) + s.get(s.size() - 2));
				} //TODO else do some error handling
			} else {
				try {
					int symbol = Integer.parseInt(blocks[i]);
					s.add(symbol);
				} catch (NumberFormatException nfe) {
					//TODO error handling
					System.out.println(nfe);
				}
			}
			
			//System.out.println(" " + i + " : "  + s.toString());
		}
		
		for (int i = 0; i < s.size(); i++) {
			total += s.get(i);
		}
		
		return total;
	}
}
