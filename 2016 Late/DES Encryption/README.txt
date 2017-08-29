TO COMPILE THE CODE:
The only .java file present is encrypt.java. This is the main class and the only one that needs to be compiled.
Make sure plaintext.txt and key.txt are in the same directory as encrypt.java, and compile with javax.


The functionality is mostly there, although I couldn't figure out S-boxes in time. Given some tweaks to make the S-boxes code work correctly, the rest of the program should fall into place as well. Decryption wasn't covered either, although again with some more time, it should be easy to implement, simply by reversing the order of the 16 cycles and using the cipher text as input. Also, Expansion Permutation needs to be tweaked, which I caught as I was finishing up and unfortunately didn't have time to fix as well.