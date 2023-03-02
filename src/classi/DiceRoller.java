package classi;

import java.util.Arrays;
import java.util.Random;

public class DiceRoller {
	public static int roll(int faces) {
		Random random = new Random();
	    return random.nextInt(faces) + 1;
	}
	
	public static int[] chooseArrayWithHighestScore() {
		int[][] a = new int[3][6];
		
		for(int i=0; i<a.length; i++)
			a[i] = rollArrayOfCharacteristics();
		
		int[] sums = {Arrays.stream(a[0]).sum(), Arrays.stream(a[1]).sum(), Arrays.stream(a[2]).sum()};
		
		int index = getIndexOfLargestElement(sums);
//		List<Integer> list = Arrays.asList(sums);
//		int max = Arrays.stream(sums).max().getAsInt();
//		int index  = Arrays.asList(sums).indexOf(max);
		
		return a[index];
	}
	
	public static int[] rollArrayOfCharacteristics() {
		int[] caratteristiche = new int[6];
		
		for(int i=0; i<caratteristiche.length; i++) {
			caratteristiche[i] = roll4SumHighest3();
		}
		
		return caratteristiche;
	}
	
	
	private static int roll4SumHighest3() {
		int[] roll = new int[4];
		
		for(int i=0; i<roll.length; i++) {
			roll[i] = roll(6);
		}
		
		return Arrays.stream(roll).sorted().skip(1).sum();
	}
	
	private static int getIndexOfLargestElement( int[] array )
	{
	  if ( array == null || array.length == 0 ) return -1;

	  int indexOfLargestElement = 0;
	  for ( int i = 1; i < array.length; i++ )
	  {
	      if ( array[i] > array[indexOfLargestElement] ) indexOfLargestElement = i;
	  }
	  return indexOfLargestElement;
	}
}
