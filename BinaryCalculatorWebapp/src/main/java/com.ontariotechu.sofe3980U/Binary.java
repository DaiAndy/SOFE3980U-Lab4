package com.ontariotechu.sofe3980U;

/**
 * Unsigned integer Binary variable
 *
 */
public class Binary
{
	private String number="0";  // string containing the binary value '0' or '1'
	/**
	* A constructor that generates a binary object.
	*
	* @param number a String of the binary values. It should conatins only zeros or ones with any length and order. otherwise, the value of "0" will be stored.   Trailing zeros will be excluded and empty string will be considered as zero.
	*/
    public Binary(String number) {
		for (int i = 0; i < number.length(); i++) {
			// check each character if it's not 0 or 1
			char ch=number.charAt(i);
			if(ch!='0' && ch!='1') {
				number="0"; // if not store "0" and end the function
				return;
			}
		}
		// remove any trailing zeros
		int beg;
		for (beg = 0; beg < number.length(); beg++) {
			if (number.charAt(beg)!='0')
				break;
		}
		//beg has the index of the first non zero digit in the number
		this.number=number.substring(beg); // exclude the trailing zeros if any
		// uncomment the following code
		
		if(this.number=="") { // replace empty strings with a single zero
			this.number="0";
		}
		
    }
	/**
	* Return the binary value of the variable
	*
	* @return the binary value in a string format.
	*/
	public String getValue()
	{
		return this.number;
	}
	/**
	* Adding two binary variables. For more information, visit <a href="https://www.wikihow.com/Add-Binary-Numbers"> Add-Binary-Numbers </a>.
	*
	* @param num1 The first addend object
	* @param num2 The second addend object
	* @return A binary variable with a value of <i>num1+num2</i>.
	*/
	public static Binary add(Binary num1,Binary num2)
	{
		// the index of the first digit of each number
		int ind1=num1.number.length()-1;
		int ind2=num2.number.length()-1;
		//initial variable
		int carry=0;
		String num3="";  // the binary value of the sum
		while(ind1>=0 ||  ind2>=0 || carry!=0) // loop until all digits are processed
		{
			int sum=carry; // previous carry
			if(ind1>=0){ // if num1 has a digit to add
				sum += (num1.number.charAt(ind1)=='1')? 1:0; // convert the digit to int and add it to sum
				ind1--; // update ind1
			}
			if(ind2>=0){ // if num2 has a digit to add
				sum += (num2.number.charAt(ind2)=='1')? 1:0; // convert the digit to int and add it to sum
				ind2--; //update ind2
			}
			carry=sum/2; // the new carry
			sum=sum%2;  // the resultant digit
			num3 =( (sum==0)? "0":"1")+num3; //convert sum to string and append it to num3
		}
		Binary result=new Binary(num3);  // create a binary object with the calculated value.
		return result;
		
	}
	public static Binary multiply(Binary num1, Binary num2) {

		// initializes strings for the parameters entered
		String binary1 = num1.number;
		String binary2 = num2.number;
		// intitilzes the result variable
		Binary result = new Binary("0");

		// for loop where it goes through each bit of the first binary, if = 1 shift the second binary number by i with 0's
		for (int i = binary1.length()-1 ; i >=0; i--) {
			if (binary1.charAt(i) == '1') {
				String shiftbin2 = binary2;
				// subtract i to the length of the binary to get the position of the least significant bit
				for (int j = 0; j < binary1.length() - 1 - i; j++) {
					shiftbin2 += "0";
				}
				// this will then add the current result with the binary shifted to get a new result
				result = add(result, new Binary(shiftbin2));
			}
		}
		return result;
	}
	public static Binary and(Binary num1, Binary num2){

		// take the parameters in as strings
		String binary1 = num1.number;
		String binary2 = num2.number;

		// get the lenght of the binary numbers
		int ind1=num1.number.length()-1;
		int ind2=num2.number.length()-1;

		// this will represent the result string
		String num3 = "";

		// loop until the both binary numbers are covered
		while(ind1>=0 ||  ind2>=0)
		{
			// intializes chars where it will hold the current character in that index of each binary
			char x1, x2;

			// gets the bit if within the range of the binary number, otherwise assign a 0 to it
			if (ind1 >= 0) {
				x1 = binary1.charAt(ind1);
			} else {
				x1 = '0';
			}

			// same thing but with the second binary number
			if (ind2 >= 0) {
				x2 = binary2.charAt(ind2);
			} else {
				x2 = '0';
			}

			// does the and comparsion, if both numbers are 1, add a 1 to the result, else add a 0
			if (x1 == '1' && x2 == '1') {
				num3 = "1" + num3;
			} else {
				num3 = "0" + num3;
			}
			// move to the next index of both the binary numbers
			ind1--;
			ind2--;
		}
		// creates a binary object with the calculated value
		Binary result = new Binary(num3);
		return result;
	}
	public static Binary or(Binary num1, Binary num2){

		// take the parameters in as strings
		String binary1 = num1.number;
		String binary2 = num2.number;

		// get the lenght of the binary numbers
		int ind1=num1.number.length()-1;
		int ind2=num2.number.length()-1;

		// this will represent the result string
		String num3 = "";

		// loop until the both binary numbers are covered
		while(ind1>=0 ||  ind2>=0) // loop until all digits are processed
		{
			char x1, x2;

			// gets the bit if within the range of the binary number, otherwise assign a 0 to it
			if (ind1 >= 0) {
				x1 = binary1.charAt(ind1);
			} else {
				x1 = '0';
			}

			// same thing but with the second binary number
			if (ind2 >= 0) {
				x2 = binary2.charAt(ind2);
			} else {
				x2 = '0';
			}

			// does the or comparsion, if any number has a 1, it will add a 1 to the result binary, else it adds a 0
			if (x1 == '1' || x2 == '1') {
				num3 = "1" + num3;
			} else {
				num3 = "0" + num3;
			}

			// move to the next index of both the binary numbers
			ind1--;
			ind2--;
		}

		// creates a binary object with the calculated value
		Binary result = new Binary(num3);
		return result;
	}
}	
