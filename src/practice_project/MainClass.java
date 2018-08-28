package practice_project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.junit.Test;

public class MainClass {

	//1. Fibonacci problem with limit --Start
	//@Test
	@SuppressWarnings("resource")
	public void problem1() {
		System.out.println("1. Fibonacci problem ");
		System.out.print("Input the starting number for the sequence: ");
		int startNum = new Scanner(System.in).nextInt();
		int nextNum = 1;
		int limit = 100;
		
		System.out.print("Fibonacci sequence: " + nextNum);
		outputSequence(startNum, nextNum, limit);
		System.out.println("\n");
	}

	private void outputSequence(int startNum, int nextNum, int limit) {
		int sum = startNum + nextNum;
		startNum = nextNum;
		nextNum = sum;
		
		if(sum <= limit){
			System.out.print(", " + sum);
			outputSequence(startNum, nextNum, limit);
		}		
	}
	//1. Fibonacci problem --End
	
	//2. String reversal --Start
//	@Test
	@SuppressWarnings("resource")
	public void problem2(){
		System.out.println("2. String reversal problem");
		System.out.print("Input the string to be reversed: ");
		String str = new Scanner(System.in).nextLine();
		
		//a. by StringBuilder.reverse()
		StringBuilder strB = new StringBuilder(str);
		System.out.println("a. Reverse by StringBuilder reverse(): " + strB.reverse());
		
		//b. by iterate
		reverseIterately(str);
	}
	
	private void reverseIterately(String str) {
		StringBuffer strB = new StringBuffer();
		char[] strChar = str.toCharArray();
		
		for(int i = (strChar.length - 1); i >=0; i--){
			strB.append(strChar[i]);
		}
		
		System.out.println("b. Reverse by iterate: " + strB.toString());
	}
	//2. --End
	
	//3. Swapping numbers w/o temp or third variable problem --Start
	//@Test
	@SuppressWarnings("resource")
	public void problem3(){
		System.out.println("3. Swapping numbers w/o temp or third variable problem");
		System.out.print("Please input the first number: ");
		int firstNUm = new Scanner(System.in).nextInt();
		System.out.print("Please input the second number: ");
		int secondNUm = new Scanner(System.in).nextInt();
		
		System.out.println("Before swapping, a. "+firstNUm+", b. "+secondNUm);
		
		firstNUm = firstNUm + secondNUm;
		secondNUm = firstNUm - secondNUm;
		firstNUm = firstNUm - secondNUm;
		
		System.out.println("After swapping, a. "+firstNUm+", b. "+secondNUm);
		
	}
	//3. --End
	
	//4. Checking which value would be displayed --Start
//	@Test
	public void problem4(){
		System.out.println("4. Checking which value would be displayed.");
		System.out.println("Double.MIN_VALUE: "+Double.MIN_VALUE);
		System.out.println("0.0d: "+0.0d);
		System.out.println(Math.min(Double.MIN_VALUE, 0.0d));
	}
	//4. --End
	
	//5. Find all equilibrium indices --Start
//	@Test
	public void problem5(){
		System.out.println("5. Find all equilibrium indices");
		int[] inputElems = {-1, 3, -4, 5, 1, -6, 2, 1};
		System.out.print("The input elements are: ");
		for(int i=0; i<inputElems.length; i++){
			System.out.print(inputElems[i] + ", ");
		}
		System.out.println(" ");
		
		List<Integer> equiIndices = findAllEquilibriumIndices(inputElems);
		if(equiIndices != null && !equiIndices.isEmpty()){
			System.out.print("The equilibrium indices are: ");
			for(Integer indices : equiIndices){
				System.out.print(indices + ", ");
			}
		}
		
	}
	
	private List<Integer> findAllEquilibriumIndices(int[] inputElems) {
		List<Integer> equiIndices = new ArrayList<Integer>();
		int sum1 = 0;
		int sum2 = 0;
		
		for(int i=0; i<inputElems.length; i++){
			for(int j=0; j<i; j++){
				sum1 += inputElems[j];
			}
			for(int k=i+1; k<inputElems.length; k++){
				sum2 += inputElems[k];
			}
			
			if(sum1 == sum2){
				equiIndices.add(i);
			}
			
			sum1 = 0;
			sum2 = 0;
		}
		
		return equiIndices;
	}
	//5. --End
	
	//6. Find all possible combinations for nesting and hunting --Start
//	@Test
	public void problem6(){
		System.out.println("6. Find all possible combinations for nesting and hunting ");
		int[] nestHeights = { 13, 2, 5 }; //{4, 6, 2, 1, 5};
		System.out.print("Here are the heights of the nests: ");
		for(int i=0; i<nestHeights.length; i++){
			System.out.print(nestHeights[i] + ", ");
		}
		System.out.println(" ");
		
		int allPossibleCombinations = findAllPossibleCombinations(nestHeights);
		System.out.println("This is all the possible combinations for nesting and hunting: "+allPossibleCombinations);
	}
	
	private int findAllPossibleCombinations(int[] nestHeights) {
		int result = 0;
		int heighestNest = 0;
		int nest = 0;
		int outerCount = 0;
		int tempNest = 0;
		List<Integer> side1List;
		List<Integer> side2List;
		Set<String> allPossibleCombinations = new HashSet<String>();
		StringBuffer combination = new StringBuffer();
		
		for(int i=0; i<nestHeights.length; i++){
			outerCount = 0;
			tempNest = 0;
			nest = nestHeights[i];
			heighestNest = nest;
			side1List = new ArrayList<Integer>();
			side2List = new ArrayList<Integer>();
			combination.append(heighestNest);
			allPossibleCombinations.add(combination.toString());
			
			//get all side1 
			for(int ii=0; ii<i; ii++){
				side1List.add(nestHeights[ii]);
			}			
			//get all side2
			for(int jj=i+1; jj<nestHeights.length; jj++){
				side2List.add(nestHeights[jj]);
			}
			
			//start over again here
			boolean side1Match = false;
			boolean side2Match = false;			
			while(outerCount < nestHeights.length){
				if(outerCount == i){
					outerCount++;
				} else {
					tempNest = nestHeights[outerCount];
					if(tempNest > heighestNest){
						heighestNest = tempNest;
						combination.append(heighestNest);
						allPossibleCombinations.add(combination.toString());
						
						if(outerCount < i){
							side1Match = true;
						} else if(outerCount > i){
							side2Match = true;
						}
					}
					
					while(side1Match || side2Match){
						//Get the heights on the left side of the nest
						if(side2Match){
							side2Match = false;
							if(!side1List.isEmpty()){
								for(int j=0; j<side1List.size(); j++){
									int side1Nest = side1List.get(j);
									if(side1Nest > heighestNest){
										heighestNest = side1Nest;
										combination.append(heighestNest);
										allPossibleCombinations.add(combination.toString());
										side1Match = true;
										break;
									}
								}
							}					
						}				
						//Get the heights on the right side of the nest
						if(side1Match){
							side1Match = false;
							if(!side2List.isEmpty()){
								for(int k=0; k<side2List.size(); k++){
									int side2Nest = side2List.get(k);
									if(side2Nest > heighestNest){
										heighestNest = side2Nest;
										combination.append(heighestNest);
										allPossibleCombinations.add(combination.toString());
										side2Match = true;
										break;
									}
								}
							}
						}
					}
					
					//If no match for both sides, reset the heighest nest to default nest
					if(!side1Match && !side2Match){
						heighestNest = nest;
						outerCount++;
					}
				}	
			}			
		}
		
		if(allPossibleCombinations != null && !allPossibleCombinations.isEmpty()){
			result = allPossibleCombinations.size();
		}		
		return result;
	}
	//6. --End

	//7. Find the first non-repeated character in a String --Start
//	@Test
	@SuppressWarnings("resource")
	public void problem7(){
		System.out.println("7. Find the first non-repeated character in a String");
		System.out.print("Input a string: ");
		String str = new Scanner(System.in).nextLine();
		
		String c = findTheFirstNonRepeatedCharacter(str);
		System.out.print("The first non-repeated character is ");
		if(c != null){
			System.out.println(c + ". ");
		} else {
			System.out.println("none. ");
		}		
	}
	//7. --End

	//8. Check nested loops and break --Start
	//@Test
	public void problem8(){
		int i = 0;
		int j = 0;
		for(i = 0; i < 2; i++){
			for(j = i; j < 3; j++){
				break;
			}
			System.out.println("i = "+i+"; j = "+j);
		}
			
	}
	
	private String findTheFirstNonRepeatedCharacter(String str) {
		String c = null;
		char[] strArr = str.toCharArray();
		HashMap<Character, Integer> charCount = new HashMap<Character, Integer>();
		
		for(int i=0; i<strArr.length; i++){
			char cTemp = strArr[i];
			
			if(charCount.containsKey(cTemp)){
				charCount.put(cTemp, (charCount.get(cTemp) + 1));
			} else {
				charCount.put(cTemp, 1);
			}
		}
		
		for(int j=0; j<strArr.length; j++){
			char cTemp = strArr[j];
			if(charCount.get(cTemp).toString().equals("1")){
				c = new StringBuilder().append(cTemp).toString();
				break;
			}
		}
		
		
		return c;
	}
	//8. --End
	

	//Capco problem --Start
//	@Test
	@SuppressWarnings("resource")
	public void capcoProblem(){
		System.out.println("Capco problem");
		System.out.print("Input the number of rows: ");
		int numRows = new Scanner(System.in).nextInt();
		int numSeatsToBeTaken = 3;
		String[] allocSeats = {"1A", "1E", "1J", "2C", "2E", "2H", "3B", "3D", "3I", 
								"4G", "4H", "5B", "5C", "5E", "5F", "5I", "5J"};
		List<String> allocSeatsList = Arrays.asList(allocSeats);
		System.out.println("contains 5B?? "+ allocSeatsList.contains("5B"));
		System.out.print("Here are the allocated seats: " + allocSeats[0]);		
		for(int i=1; i<allocSeats.length; i++){
			System.out.print(", "+allocSeats[i]);
		}
		System.out.println(" ");
		
		//Get first the free seats
		List<String> freeSeats = getAllFreeSeat(allocSeats, numRows);
		
		//Get all possible combinations for the given number of seats
		List<String> allPossibleCombinationsS1 = getPossibleCombinationsByRangeOfChar('A', 'C', numSeatsToBeTaken);
		List<String> allPossibleCombinationsS2 = getPossibleCombinationsByRangeOfChar('D', 'G', numSeatsToBeTaken);
		List<String> allPossibleCombinationsS3 = getPossibleCombinationsByRangeOfChar('H', 'J', numSeatsToBeTaken);		
		
		//Get the total number of combinations
		int totalNumCombinations = 0;
		if(freeSeats != null && !freeSeats.isEmpty()){
			System.out.print("Here are the free seats: ");
			for(String s : freeSeats){
				System.out.print(s + ", ");
			}
			System.out.println(" ");
			
			for(int i=1; i<=numRows; i++){
				//number of combinations per sections
				StringBuilder section1 = new StringBuilder();				
				StringBuilder section2 = new StringBuilder();
				StringBuilder section3 = new StringBuilder();				
				
				//Get the free columns per row
				for(int j=0; j<freeSeats.size(); j++){
					//Get the allocated seat
					String allocSeat = freeSeats.get(j);
					String allocSeatRow = allocSeat.substring(0, (allocSeat.length()-1)); //allocated seat row
					String allocSeatCol = allocSeat.substring((allocSeat.length()-1), allocSeat.length()); //allocated seat column
					
					if(Integer.parseInt(allocSeatRow) == i){
						if(allocSeatCol.equals("A") || allocSeatCol.equals("B") || allocSeatCol.equals("C")){
							section1.append(allocSeatCol);
						} else if(allocSeatCol.equals("D") || allocSeatCol.equals("E") 
									|| allocSeatCol.equals("F") || allocSeatCol.equals("G")){
							section2.append(allocSeatCol);
						} else if(allocSeatCol.equals("H") || allocSeatCol.equals("I") || allocSeatCol.equals("J")){
							section3.append(allocSeatCol);
						}
					}
				}
				
				//Now check if the combinations of the free seats exist in the possible combinations
				//Section1
				if(section1.toString().length() > 0){
					totalNumCombinations += countTotalPossibleCombinationPerSection(section1.toString(), allPossibleCombinationsS1, numSeatsToBeTaken);														
				}
				//Section2
				if(section2.toString().length() > 0){
					totalNumCombinations += countTotalPossibleCombinationPerSection(section2.toString(), allPossibleCombinationsS2, numSeatsToBeTaken);				
				}
				//Section3
				if(section3.toString().length() > 0){
					totalNumCombinations += countTotalPossibleCombinationPerSection(section3.toString(), allPossibleCombinationsS3, numSeatsToBeTaken);							
				}
			}
		}
		
		
		System.out.println("Here is the total possible combinations for " + numSeatsToBeTaken + " seats: " +totalNumCombinations );
	}
	
	private List<String> getAllFreeSeat(String[] allocSeats, int numRows){
		List<String> freeSeats = new ArrayList<String>();
		for(int i=1; i<=numRows; i++){
			for(char j='A'; j<='J'; j++){
				StringBuffer freeSeat = new StringBuffer();
				freeSeat.append(i);
				freeSeat.append(j);
				boolean isFree = true;
				for(int k=0; k<allocSeats.length; k++){
					if(allocSeats[k].equals(freeSeat.toString())){
						isFree = false;
						break;
					}
				}
				
				if (isFree) {
					freeSeats.add(freeSeat.toString());
				}
			}
		}
		
		return freeSeats;
	}
	
	private List<String> getPossibleCombinationsByRangeOfChar(char start, char end, int numSeatsToBeTaken){
		List<String> allPossibleCombinations = new ArrayList<String>();
		for(char s=start; s<=end; s++){
			StringBuilder sb = new StringBuilder();
			for(char sub=s; sub<=end; sub++){				
				sb.append(sub);
				
				if(sb.toString().length() == numSeatsToBeTaken){
					allPossibleCombinations.add(sb.toString());
					break;
				}				
			}
		}
		
		return allPossibleCombinations;
	}	
	
	private int countTotalPossibleCombinationPerSection(String freeSeatCol, List<String> allPossibleCombinations, int numSeatsToBeTaken){
		int totalNumCombinations = 0;
		char[] freeSeatColChar = freeSeatCol.toCharArray();
		
		for(int s=0; s<freeSeatColChar.length; s++){
			StringBuilder sb = new StringBuilder();
			for(int sub=s; sub<freeSeatColChar.length; sub++){
				sb.append(freeSeatColChar[sub]);
				
				if(sb.toString().length() == numSeatsToBeTaken){
					for(int ii=0; ii<allPossibleCombinations.size(); ii++){
						if(sb.toString().equals(allPossibleCombinations.get(ii))){
							totalNumCombinations++;
							break;
						}
					}
					break;
				}				
			}
		}
		
		return totalNumCombinations;
	}
	
//	@Test
	public void convertCharToInt() {
		char c = '9';
		char b = 'c';
		
		System.out.println("is c a digit?? "+ Character.isDigit(c));
		System.out.println("is b a digit?? "+ Character.isDigit(b)); 
		System.out.println("numeric value c: "+Character.getNumericValue(c));
		System.out.println("numeric value b: "+Character.getNumericValue(b));
	}
	
	@Test
	public void sampleTest() {
		System.out.println("testing..");
		
	}
	
	//MFOS Attachment
//	@SuppressWarnings("deprecation")
//	@Test
//	public void mfosDownload(){
//		try {
//			HttpResponse<String> response = Unirest.post("http://10.62.174.51/mfos_backend/api/icc/geticctoken")
//					  .header("Content-Type", "application/json")
//					  .header("Cache-Control", "no-cache")
//					  .body("{\"username\":\"icc\", \"password\" : \"P@ssw0td\"}")
//					  .asString();
//			System.out.println("response: "+response);
//			System.out.println("response body: "+response.getBody());
//			System.out.println("response raw body: "+response.getRawBody());
//		} catch (UnirestException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	//--End
	

	

}
