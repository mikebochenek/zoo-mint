package zoo.mb.crypto1;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

/**
 * Week 1 – Programming Assignment [optional: extra credit] 
 * 
 * Let us see what goes wrong when a stream cipher key is used more than once.
 * Below are eleven hex-encoded ciphertexts that are the result of encrypting
 * eleven plaintexts with a stream cipher, all with the same stream cipher key.
 * Your goal is to decrypt the last ciphertext, and submit the secret message
 * within it as solution.
 * 
 * Hint: XOR the ciphertexts together, and consider what happens when a space is
 * XORed with a character in [a-zA-Z].
 * 
 * For completeness, here is the python script used to generate the ciphertexts.
 * (it doesn’t matter if you can’t read this)
 * 
 * 
 * import sys
 * 
 * MSGS = ( —  11 secret messages  — )
 *
 * def strxor(a, b):     # xor two strings of different lengths
 *     if len(a) > len(b):
 *         return “”.join([chr(ord(x) ^ ord(y)) for (x, y) in zip(a[:len(b)], b)])
 *     else:
 *         return “”.join([chr(ord(x) ^ ord(y)) for (x, y) in zip(a, b[:len(a)])])
 * 
 * def random(size=16):
 *     return open(“/dev/urandom”).read(size)
 * 
 * def encrypt(key, msg):
 *     c = strxor(key, msg)
 *     print
 *     print c.encode(‘hex’)
 *     return c
 * 
 * def main():
 *     key = random(1024)
 *     ciphertexts = [encrypt(key, msg) for msg in MSGS]
 *     
 *     
 *     
 * cut and paste from "Applied Cryptography"
 *  
 * There's no real security here. This kind of encryption is trivial to break, even
 * without computers [587, 1475]. It will only take a few seconds with a computer. 
 * Assume the plaintext is English. Furthermore, assume the key length is any small
 * number of bytes. Here's how to break it:
 * 
 * 1.  Discover the length of the key by a procedure known as counting coincidences [577]. 
 * XOR the ciphertext against itself shifted various numbers of bytes, and count those 
 * bytes that are equal. If the displacement is a multiple of the key length, then 
 * something over 6 percent of the bytes will be equal. If it is not, then less than 0.4 
 * percent will be equal (assuming a random key encrypting normal ASCII text; other 
 * plaintext will have different numbers). This is called the index of coincidence. 
 * The smallest displacement that indicates a multiple of the key length is the length of the key.
 * 
 * 2.  Shift the ciphertext by that length and XOR it with itself. This removes the 
 * key and leaves you with plaintext XORed with the plaintext shifted the length of 
 * the key. Since English has 1.3 bits of real information per byte (see Section 11.1), 
 * there is plenty of redundancy for determining a unique decryption.
 * 
 * Despite this, the list of software vendors that tout this toy algorithm as being 
 * “almost as secure as DES” is staggering [1387]. It is the algorithm (with a 160-bit 
 * repeated “key”) that the NSA finally allowed the U.S. digital cellular phone industry 
 * to use for voice privacy. An XOR might keep your kid sister from reading your files, 
 * but it won't stop a cryptanalyst for more than a few minutes.
 * 
 * 
 * 
 * You have submitted this homework on Thu 30 Aug 2012 2:30:09 PM PDT. You achieved a score of 1.00 out of 1.00.
 * 
 * the secret message is: When using a stream cipher, never use the key more than once
 * 100%
 * we can factor the number 15 with quantum computers. We can also factor the number 1 + 25b8cc57ee59418ce7dc6bc41556bdb36bbca3e8774301fbcaa3b83b220809560987815f65286764703de0f3d524400a19b159610b11ef3e
 * euler would probably enjoy that now his theorem becomes a corner stone of crypto -  + 51f6d551f4480c82b2cb24cc5b028aa76eb7b4ab24171ab3cdadb8356f
 * the nice thing about Keeyloq is now we cryptographers can drive a lot of fancy cars + 30b59b7afb5f41afa8d661cb
 * the ciphertext produced by a weak encryption algorithm looks as good as ciphertext  + 60ead45aef520489e7da7d835402bca670bda8eb775200b8dabbba246b130f040d8ec6447e2c767f3d30ed81ea2e4c1404e1315a1010e7229be6636aaa
 * you don't want to buy a set of car keys from a guy who specializes in stealing cars + 30b59b73fb4302cd95d770c65b40aaa065f2a5e33a5a0bb5dcaba43722130f042f8ec85b7c2070
 * there are two types of cryptography - that which will keep secrets safe from your l + 79eccf52ff111284b4cc61d11902aebc66f2b2e436434eacc0aba938220b084800c2ca4e693522643573b2c4ce35050b0cf774201f0fe52ac9f26d71b6cf61a711cc229f77ace7aa88a2f19983122b11be87a59c355d25f8e4
 * there are two types of cyptography: one that allows the Government to use brute for + 73fd9b4af511039fa2d96f83414aaaf261bda2e97b170fb5cce2a53e675c154c0d9681596934777e2275b381ce2e40582afe67650b13e72287ff2270abcf73bb028932836fbdecfecee0a3b894473c1bbeb6b4913a536ce4f9b13f1efff71ea313c8661dd9a4ce
 * we can see the point where the chip is unhappy if a wrong bit is sent and consumes  + 7df7c95bba410e9aa2ca24c5474da2f276baa3ac325918b2daada43d6712150441c2e04f6565517f317da9d3
 * a (private-key)  encryption scheme states 3 algorithms, namely a procedure for gene + 62f9cf57f4564186a2c1778f1543efa270bda5e933421cbe88a4a52222190f471e9bd15f652b653b7071aec59a2705081ffe72651d08f822c9ed6d76e48b63ab15d0208573a7eef027
 *
 */
public class Test1 {

	private static String key = "46396E89C9DBD8CC9874352ACD6395102EAFCE78AA7FED28A07F6BC98D29C50B69B0339A19F8AA401A9C6D708F80C066C763FEF0123148CDD8E802d05BA98777335D"
			+ "AEFCECD59C433A6B268B60BF4EF03C9A61";
	//private static String key = "46396E89C9DBD8CC9874352ACD6395102EAFCE78AA7FED28A07F6BC98D29C50B69B0339A19F8AA401A9C6D708F80C066C763FEF01231"; // 44 is wrong
	//private static String key = "46396E89C9DBD8CC9874352ACD63"; // 9F142ABF is wrong
	//private static String key = "46396E89C9DBD8CC";
	//private static String key = "46396E89C9DB";  C389 is wrong
	//private static String key = "46396E";
	
    /**
     * Hint: XOR the ciphertexts together, and consider what happens when a space is
     * XORed with a character in [a-zA-Z].
     */
    public static void main(String[] args) {
        Test1 t = new Test1();

        System.out.println (t.convertHexToString(t.xor(key, t.target)));
        System.out.println ((key.length() * 100 / t.target.length() ) + "%");
        
        
        for (int i = 0; i < t.sample.length - 1; i++) {
        	t.print(t.sample[i], key);
        }

        for (int i = 0; i < t.sample.length; i++) {
            for (int j = 0; j < t.sample.length; j++) {
                if (i != j) {
                    //System.out.println ("trying xor for i=" + i + " j=" + j);
                    //t.print (t.sample[0], t.sample[1]);
                }
            }
        }
    }
    
    /**
     * http://en.wikipedia.org/wiki/Stream_cipher_attack
     * http://en.wikipedia.org/wiki/Lorenz_cipher
     */
    
    
    private void print(String s1, String s2) {
        //System.out.println (s1);
        //System.out.println (s2);
        //System.out.println ("——————-");
        String s = xor(s1,s2);
        //System.out.println (s);
        System.out.println (new String(hexToBytes(s)) + " + " + s1.substring(s2.length()));
    }

    /**
     * http://stackoverflow.com/questions/5126616/xor-operation-with-two-strings-in-java
     * I would do an XOR each charAt() to create a new String. Like
     *
     * String s1, s2;  
     * StringBuilder sb = new StringBuilder(); 
     * for(int i=0; i<s1.length() && i<s2.length();i++) {     
     *    sb.append((char)(s1.charAt(i) ^ s2.charAt(i))); 
     * }
     * String result = sb.toString();   
     */  
    private String xor(String s1, String s2) {
        String s = "";
        s1 = new String(hexToBytes(s1));
        s2 = new String(hexToBytes(s2));
        for(int i=0; i<s1.length() && i<s2.length();i++) {    
            s += ((char)(s1.charAt(i) ^ s2.charAt(i))); 
        }
        return bytesToHex(s);
    }
    
    /**
     * http://stackoverflow.com/questions/140131/convert-a-string-representation-of-a-hex-dump-to-a-byte-array-using-java
     */
/*    
    private byte[] hexToBytes(String hexString) {      
        HexBinaryAdapter adapter = new HexBinaryAdapter();      
        byte[] bytes = adapter.unmarshal(hexString);      
        return bytes; 
    } 
    
    private String bytesToHex(String hexString) {      
        HexBinaryAdapter adapter = new HexBinaryAdapter();      
        return  adapter.marshal(hexString.getBytes());      
    } 
*/    

    //http://www.mkyong.com/java/how-to-convert-hex-to-ascii-in-java/
    
    public String bytesToHex(String hexString) {      
    	return convertStringToHex(hexString);
    }

    public String hexToBytes(String hexString) {      
    	return convertHexToString(hexString)
;    }

    	
    public String convertStringToHex(String str){
    	 
  	  char[] chars = str.toCharArray();
   
  	  StringBuffer hex = new StringBuffer();
  	  for(int i = 0; i < chars.length; i++){
  	    hex.append(Integer.toHexString((int)chars[i]));
  	  }
   
  	  return hex.toString();
    }
   
    public String convertHexToString(String hex){
   
  	  StringBuilder sb = new StringBuilder();
  	  StringBuilder temp = new StringBuilder();
   
  	  //49204c6f7665204a617661 split into two characters 49, 20, 4c...
  	  for( int i=0; i<hex.length()-1; i+=2 ){
   
  	      //grab the hex in pairs
  	      String output = hex.substring(i, (i + 2));
  	      //convert hex to decimal
  	      int decimal = Integer.parseInt(output, 16);
  	      //convert the decimal to character
  	      sb.append((char)decimal);
   
  	      temp.append(decimal);
  	  }
  	  //System.out.println("Decimal : " + temp.toString());
   
  	  return sb.toString();
    }
    
    //target ciphertext (decrypt this one): 
    private final String target = "32510ba9babebbbefd001547a810e67149caee11d945cd7fc81a05e9f85aac650e9052ba6a8cd8257bf14d13e6f0a803b54fde9e77472dbff89d71b57bddef121336cb85ccb8f3315f4b52e301d16e9f52f904";

    private final String[] sample = {
        //ciphertext #1:
        "315c4eeaa8b5f8aaf9174145bf43e1784b8fa00dc71d885a804e5ee9fa40b16349c146fb778cdf2d3aff021dfff5b403b510d0d0455468aeb98622b137dae857553ccd8883a7bc37520e06e515d22c954eba5025b8cc57ee59418ce7dc6bc41556bdb36bbca3e8774301fbcaa3b83b220809560987815f65286764703de0f3d524400a19b159610b11ef3e",
    
        //ciphertext #2:
        "234c02ecbbfbafa3ed18510abd11fa724fcda2018a1a8342cf064bbde548b12b07df44ba7191d9606ef4081ffde5ad46a5069d9f7f543bedb9c861bf29c7e205132eda9382b0bc2c5c4b45f919cf3a9f1cb74151f6d551f4480c82b2cb24cc5b028aa76eb7b4ab24171ab3cdadb8356f",

        //ciphertext #3:
        "32510ba9a7b2bba9b8005d43a304b5714cc0bb0c8a34884dd91304b8ad40b62b07df44ba6e9d8a2368e51d04e0e7b207b70b9b8261112bacb6c866a232dfe257527dc29398f5f3251a0d47e503c66e935de81230b59b7afb5f41afa8d661cb",
        
        //ciphertext #4:
        "32510ba9aab2a8a4fd06414fb517b5605cc0aa0dc91a8908c2064ba8ad5ea06a029056f47a8ad3306ef5021eafe1ac01a81197847a5c68a1b78769a37bc8f4575432c198ccb4ef63590256e305cd3a9544ee4160ead45aef520489e7da7d835402bca670bda8eb775200b8dabbba246b130f040d8ec6447e2c767f3d30ed81ea2e4c1404e1315a1010e7229be6636aaa",
        
        //ciphertext #5:
        "3f561ba9adb4b6ebec54424ba317b564418fac0dd35f8c08d31a1fe9e24fe56808c213f17c81d9607cee021dafe1e001b21ade877a5e68bea88d61b93ac5ee0d562e8e9582f5ef375f0a4ae20ed86e935de81230b59b73fb4302cd95d770c65b40aaa065f2a5e33a5a0bb5dcaba43722130f042f8ec85b7c2070",

        //ciphertext #6:
        "32510bfbacfbb9befd54415da243e1695ecabd58c519cd4bd2061bbde24eb76a19d84aba34d8de287be84d07e7e9a30ee714979c7e1123a8bd9822a33ecaf512472e8e8f8db3f9635c1949e640c621854eba0d79eccf52ff111284b4cc61d11902aebc66f2b2e436434eacc0aba938220b084800c2ca4e693522643573b2c4ce35050b0cf774201f0fe52ac9f26d71b6cf61a711cc229f77ace7aa88a2f19983122b11be87a59c355d25f8e4",

        //ciphertext #7:
        "32510bfbacfbb9befd54415da243e1695ecabd58c519cd4bd90f1fa6ea5ba47b01c909ba7696cf606ef40c04afe1ac0aa8148dd066592ded9f8774b529c7ea125d298e8883f5e9305f4b44f915cb2bd05af51373fd9b4af511039fa2d96f83414aaaf261bda2e97b170fb5cce2a53e675c154c0d9681596934777e2275b381ce2e40582afe67650b13e72287ff2270abcf73bb028932836fbdecfecee0a3b894473c1bbeb6b4913a536ce4f9b13f1efff71ea313c8661dd9a4ce",

        //ciphertext #8:
        "315c4eeaa8b5f8bffd11155ea506b56041c6a00c8a08854dd21a4bbde54ce56801d943ba708b8a3574f40c00fff9e00fa1439fd0654327a3bfc860b92f89ee04132ecb9298f5fd2d5e4b45e40ecc3b9d59e9417df7c95bba410e9aa2ca24c5474da2f276baa3ac325918b2daada43d6712150441c2e04f6565517f317da9d3",
        
        //ciphertext #9:
        "271946f9bbb2aeadec111841a81abc300ecaa01bd8069d5cc91005e9fe4aad6e04d513e96d99de2569bc5e50eeeca709b50a8a987f4264edb6896fb537d0a716132ddc938fb0f836480e06ed0fcd6e9759f40462f9cf57f4564186a2c1778f1543efa270bda5e933421cbe88a4a52222190f471e9bd15f652b653b7071aec59a2705081ffe72651d08f822c9ed6d76e48b63ab15d0208573a7eef027",
        
        //ciphertext #10:
        "466d06ece998b7a2fb1d464fed2ced7641ddaa3cc31c9941cf110abbf409ed39598005b3399ccfafb61d0315fca0a314be138a9f32503bedac8067f03adbf3575c3b8edc9ba7f537530541ab0f9f3cd04ff50d66f1d559ba520e89a2cb2a83"};

}