package practice_project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import javax.xml.soap.Node;

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
	
	// Make a class not extendable and can't be initialized
	//  - Making final a class will make it non-extendable
	//  - Making all constructors of a class to private, other class cannot initiliaze it
	public final class SampleClass{
		private SampleClass() {}
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
		SampleClass ss = new SampleClass();
//		Node sampleN = new Node();
		
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
