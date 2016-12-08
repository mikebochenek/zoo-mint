package zoo.mb.hr;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class DiagonalDifference {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int a[][] = new int[n][n];
        for(int a_i=0; a_i < n; a_i++){
            for(int a_j=0; a_j < n; a_j++){
                a[a_i][a_j] = in.nextInt();
            }
        }
        
        int primaryDiagonal = 0;
        int secondaryDiagonal = 0;
        for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				if (i == j) {
					primaryDiagonal += a[i][j];
				}
				if (i + j == (n - 1)) {
					secondaryDiagonal += a[i][j];
				}
			}
		}
        
        System.out.println(Math.abs(primaryDiagonal - secondaryDiagonal));
    }
}
