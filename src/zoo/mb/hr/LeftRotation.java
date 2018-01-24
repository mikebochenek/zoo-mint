package zoo.mb.hr;

import java.util.*;

public class LeftRotation {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        int a[] = new int[n];
        for(int a_i=0; a_i < n; a_i++){
            a[a_i] = in.nextInt();
        }
        
        k = k % n; // avoid circular rotations
        int b[] = new int[n];
        
        for (int i = 0; i < n; i++) {
        	b[i] = a[(i+k) % n];
        }
        
        for (int s : b) {
        	System.out.print(s + " ");
        }
        System.out.println("");
    }
}
