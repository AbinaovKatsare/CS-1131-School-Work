/*This program has the user enter in two 3x3 arrays and then tells whether they are strictly identical or not.

Date last modified 9/19/2019
@author Murali Abinaov

CS1131 Fall 2019
Lab Section LO2
*/
public class Prog3Cipher {
    // INSTANCE VARIABLES
    char[] keyList; // Key string turned into a char array
    char[][] cipherTable; // The two dimensional array with a shifted alphabet used to encode
    char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};   //This array is used to find the index of where in the alphabet the code is

    // METHODS

    /**
     * METHOD DESCRIPTION COMMENT
     *  This method converts the given message into a secret code.
     * @param message This is the given string that needs to be encoded
     * @return The encoded string is returned
     */
    String encode(String message) {
        String result = "";
        // Write your code here!
        char keychar;               //Keeps track of the char from the key to be used in encoding
        char messchar;             //Keeps track of the char from the message to be used in encoding
        int row = 0;              //Keeps track of the row of the Cipher table needed
        int column = 0;          //Keeps track of the column of the Cipher table needed
        message = message.toUpperCase();
        int keycounter = 0;    //Tracks the index of the keyList
        for (int i = 0; i < message.length(); i++) {
            keychar = keyList[keycounter];
            keycounter++;
            if (keycounter == keyList.length) {
                keycounter = 0;
            }
            messchar = message.charAt(i);
            for (int j = 0; j < alphabet.length; j++) {
                if (keychar == alphabet[j]) {
                    row = j;
                }
            }
            for (int k = 0; k < alphabet.length; k++) {
                if (messchar == ' ') {
                    result = result + " ";
                    break;
                }
                if (messchar == alphabet[k]) {
                    column = k;
                    result = result + cipherTable[row][column];
                }
            }
        }
        return result;
    }

    /**
     * METHOD DESCRIPTION COMMENT
     *  This method translates the given message string back into english.
     * @param message the given string that is to be decoded.
     * @return the decoded string of the message.
     */
    String decode(String message) {
        String result = "";
        // Write your code here!
        char keychar;               //Keeps track of the char from the key to be used in decoding
        char messchar;             //Keeps track of the char from the message to be used in decoding
        int row = 0;              //Keeps track of the row of the Cipher table needed
        int column = 0;          //Keeps track of the column of the Cipher table which is then the needed index of the alphabet
        int keycounter = 0;    //Tracks the index of the keyList
        for (int i = 0; i < message.length(); i++) {
            messchar = message.charAt(i);
            keychar = keyList[keycounter];
            keycounter++;
            if (keycounter == keyList.length) {
                keycounter = 0;
            }
            for (int j = 0; j < alphabet.length; j++) {
                if (keychar == alphabet[j]) {
                    row = j;
                }
            }
            for (int k = 0; k < alphabet.length; k++) {
                if (messchar == ' ') {
                    result = result + " ";
                    break;
                }
                if (messchar == cipherTable[row][k]) {
                    column = k;
                    result = result + alphabet[column];
                }
            }
        }
        return result;
    }

    // CONSTRUCTORS

    /**
     * CONSTRUCTOR DESCRIPTION COMMENT
     *  This constructor creates a cipher table and a key list for the purpose of encoding or decoding
     * @param code The code is the inputted char that decides where the Cipher table begins
     * @param key  The key is the inputted string that is then turned into a character array
     */
    public Prog3Cipher(char code, String key) {
        // Write your code here!
        cipherTable = new char[26][26];
        int index = 0;       //This variable saves the position of the code in the alphabet
        int col = 0;        //Short for column, this variable decides which column the cipher table is in
        key = key.replaceAll("\\s", "");
        keyList = key.toCharArray();
        for (int i = 0; i < alphabet.length; i++) {
            if (code == alphabet[i]) {
                index = i;
            }
        }
        int j;                          //This variable is a counter for the inner loop
        int k = index;                 //This variable is a counter for the outer loop
        int rindex = index + index;   //This variable acts like the index except each iteration through the first do while loop shifts it over by one
        int f =0;                    //This variable is just to ensure the rindex does not exceed 26
        if (index>=13){
            rindex=index;
            do {
                if (rindex==25){
                    rindex=-1;
                }
                rindex++;
                f++;
            }while (f<index);
        }
        do {
            j = rindex;
            do {
                cipherTable[k][col] = alphabet[j];
                col++;
                if (j == 25) {
                    j = -1;
                }
                j++;
            } while (j != rindex);
            if (rindex == 25) {
                rindex = -1;
            }
            rindex++;
            if (k == 25) {
                k = -1;
            }
            col = 0;
            k++;
        } while (k != index);
    }

    // MAIN (TEST) Method

    /**
     * METHOD DESCRIPTION COMMENT
     *  The main method that automatically runs, used for testing whether the program works
     * @param args
     */
    public static void main(String[] args) {
        // Testing only works if using VM argument -ea
        /*
        Prog3Cipher self = new Prog3Cipher('H', "BABBAGE");
        assert "PHXXF MQYBPKNJ".equals(self.encode("Happy Birthday"));
        assert "HAPPY BIRTHDAY".equals(self.decode("PHXXF MQYBPKNJ"));/*
      for (int m=0;m<26;m++){
         for (int n=0;n<26;n++){
            System.out.print(self.cipherTable[m][n]);
         }
         System.out.println(" ");
      }
      for (int q=0;q<self.keyList.length;q++){
         System.out.print(self.keyList[q]);
      }
        System.out.println(self.encode("Happy Birthday"));
        System.out.println(self.decode("PHXXF MQYBPKNJ"));*/
        Prog3Cipher ob = new Prog3Cipher('Q', "A");
        System.out.println(ob.encode("ATTACK AT DAWN"));
        System.out.println(ob.decode("QJJQSA QJ TQMD"));
    }
} // END OF CLASS --------------------------------------------------------