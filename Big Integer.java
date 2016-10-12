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
