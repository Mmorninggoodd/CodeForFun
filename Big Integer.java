/*
* class BigInt, to represent non-negative integers of arbitrary size
* constructor accept a String, representing this non-neg int (e.g. "50"), assume valid input    
* needs to be able to add to another BigInt, and return their sum as a new BigInt object
* immutable.
* new BigInt("20").add(new BigInt("30")) --> BigInt("50")
*/

public class BigInt {
    private byte[] digits;
    private boolean isNegative;
    /*
        Can only accept string representation of number without any space.
        Can accept negative sign at the first index
        "-11" "231"

        "01" "-01" "00" "13 32" "- 23" "+11" are not valid inputs
     */
    public BigInt(String str) {
        if(str == null || str.length() == 0) throw new IllegalArgumentException("Invalid input");
        this.isNegative = str.charAt(0) == '-';
        this.digits = new byte[str.length() - (this.isNegative ? 1 : 0)];
        for(int i = 0; i < this.digits.length; i++) {
            this.digits[i] = (byte) (str.charAt(str.length() - 1 - i) - '0');
        }
    }
    public BigInt(byte[] bytes, boolean isNegative) {
        this.isNegative = isNegative;
        int len = bytes.length;
        while(len > 0 && bytes[len - 1] == 0) len--;
        if(len == 0) {
            this.isNegative = false;
            this.digits = new byte[]{0};
        }
        else {
            this.digits = new byte[len];
            System.arraycopy(bytes, 0, this.digits, 0, len);
        }
    }
    public BigInt add(BigInt otherBigInt) {
        if(this.isNegative == otherBigInt.isNegative) return addAbs(this, otherBigInt, this.isNegative);
        boolean cmp = compareAbs(this, otherBigInt) > 0;
        if(cmp) return subtractAbs(this, otherBigInt, this.isNegative);
        return subtractAbs(otherBigInt, this, otherBigInt.isNegative);

    }
    public BigInt subtract(BigInt otherBigInt) {
        if(this.isNegative && !otherBigInt.isNegative) return addAbs(this, otherBigInt, true);
        else if(!this.isNegative && otherBigInt.isNegative) return addAbs(this, otherBigInt, false);
        boolean cmp = compareAbs(this, otherBigInt) > 0, isNegative = this.isNegative == cmp;
        if(cmp) return subtractAbs(this, otherBigInt, isNegative);
        return subtractAbs(otherBigInt, this, isNegative);
    }

    public int getDigit(int index) {
        if(index >= this.digits.length) return 0;
        return this.digits[index];
    }

    private static int compareAbs(BigInt bigInt1, BigInt bigInt2) {
        if(bigInt1.digits.length > bigInt2.digits.length) return 1;
        else if(bigInt1.digits.length < bigInt2.digits.length) return -1;
        for(int i = bigInt1.digits.length - 1; i >= 0; i--) {
            if(bigInt1.digits[i] > bigInt2.digits[i]) return 1;
            else if(bigInt1.digits[i] < bigInt2.digits[i]) return -1;
        }
        return 0;
    }

    private static BigInt addAbs(BigInt bigInt1, BigInt bigInt2, boolean isNegative) {
        int carry = 0;
        int maxLen = Math.max(bigInt1.digits.length, bigInt2.digits.length);
        byte[] newDigits = new byte[maxLen + 1];
        for(int i = 0; i < maxLen || carry != 0; i++) {
            int digit = carry + bigInt1.getDigit(i) + bigInt2.getDigit(i);
            carry = digit / 10;
            newDigits[i] = (byte) (digit % 10);
        }
        return new BigInt(newDigits, isNegative);
    }

    private static BigInt subtractAbs(BigInt big, BigInt small, boolean isNegative) {
        byte[] digits = new byte[big.digits.length];
        int carry = 0;
        for(int i = 0; i < big.digits.length; i++) {
            int digit = carry + big.getDigit(i) - small.getDigit(i);
            carry = digit < 0 ? -1 : 0;
            digits[i] = (byte) (digit % 10);
        }
        return new BigInt(digits, isNegative);
    }

    public static void main(String[] args) {
        BigInt b1 = new BigInt("888888");
        BigInt b2 = new BigInt("-13");
        b1.subtract(b2);
        b1.add(b2);
    }
}



/*
	Version 2:
	Use string representation
*/
class BigInt {
    private String str;
    private boolean isNegative;
    public BigInt(String str) { // big endian
        this.isNegative = str.charAt(0) == '-';
        if(this.isNegative) str = str.substring(1);
        this.str = str;
    }
    private BigInt(String str, boolean isNegative) {
        this.str = str;
        this.isNegative = isNegative;
    }
    public static BigInt add(BigInt num1, BigInt num2) {
        if(num1.isNegative ^ num2.isNegative) {   // different sign
            int cmp = absCompare(num1, num2);
            if(cmp == 0) return new BigInt("0", false);
            if(cmp < 0) {   // swap two numbers such that absolute value of num1 > num2
                BigInt tmp = num1;
                num1 = num2;
                num2 = tmp;
            }
            return new BigInt(absSubtract(num1, num2), num1.isNegative);
        }
        StringBuilder newStr = new StringBuilder();
        int n1 = num1.str.length(), n2 = num2.str.length(), carry = 0;
        for(int i = 1; i <= n1 || i <= n2; i++) {
            int cur = (i <= n1 ? num1.str.charAt(n1 - i) - '0' : 0) + (i <= n2 ? num2.str.charAt(n2 - i) - '0' : 0) + carry;
            carry = cur / 10;
            newStr.append(cur % 10);
        }
        return new BigInt(newStr.toString(), num1.isNegative);
    }
    public static BigInt substract(BigInt num1, BigInt num2) {
        num2.isNegative = !num2.isNegative;
        BigInt res = add(num1, num2);
        num2.isNegative = !num2.isNegative;
        return res;
    }
    public static BigInt multiple(BigInt num1, BigInt num2) {
        if(num1.str.equals("0") || num2.str.equals("0")) return new BigInt("0");
        int n1 = num1.str.length(), n2 = num2.str.length();
        int[] digits = new int[n1 + n2];
        for(int i = n1 - 1; i >= 0; i--) {
            for(int j = n2 - 1; j >= 0; j--) {
                digits[i + j + 1] += (num1.str.charAt(i) - '0') * (num2.str.charAt(j) - '0');
                digits[i + j] += (digits[i + j + 1] / 10);
                digits[i + j + 1] %= 10;
            }
        }
        StringBuilder res = new StringBuilder();
        for(int digit : digits) {
            if(res.length() != 0 || digit != 0) res.append(digit);
        }
        return new BigInt(res.toString(), num1.isNegative ^ num2.isNegative);
    }
    
    private static String absSubtract(BigInt num1, BigInt num2) {
        // here num1 is larger
        StringBuilder newStr = new StringBuilder();
        int n1 = num1.str.length(), n2 = num2.str.length(), carry = 0;
        for(int i = 1; i <= n1 || i <= n2; i++) {
            int cur = (i <= n1 ? num1.str.charAt(n1 - i) - '0' : 0) - (i <= n2 ? num2.str.charAt(n2 - i) - '0' : 0) + carry;
            carry = cur < 0 ? -1 : 0;
            newStr.append((10 + cur) % 10);
        }
        while(newStr.length() > 1 && newStr.charAt(newStr.length() - 1) == '0') {   // remove leading zeros
            newStr.deleteCharAt(newStr.length() - 1);
        }
        return newStr.reverse().toString();
    }
    private static int absCompare(BigInt num1, BigInt num2) {
        if(num1.str.length() != num2.str.length()) {
            return Integer.compare(num1.str.length(), num2.str.length());
        }
        return num1.str.compareTo(num2.str);
    }

    public static void main(String[] args) {
        BigInt b1 = new BigInt("12"), b2 = new BigInt("1"), b3 = new BigInt("-5"), b4 = new BigInt("-3");
        BigInt res = multiple(b1,b2);
        System.out.print(res.str + ", isNegative: " + res.isNegative);
    }
}
