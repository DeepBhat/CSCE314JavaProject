// This file gives access to the underlying datafile and stores the data in the Workout class.
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.Scanner;

public class FileAccess {
  
  public static boolean loadPrimes(Primes primes, String filename) throws FileNotFoundException {
    File file = new File(Config.DATAPATH + filename); 
    Scanner scanner = new Scanner(file); 
    while (scanner.hasNextLine()) {
      primes.addPrime(new BigInteger(scanner.nextLine().strip()));
    }
    scanner.close();
		return true;
  }
  
  public static boolean loadCrosses(Primes primes, String filename) throws FileNotFoundException {
    File file = new File(Config.DATAPATH + filename);
    Scanner scanner = new Scanner(file);
    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();
      BigInteger crossOne = new BigInteger(line.substring(0,line.indexOf(",")));
      BigInteger crossTwo = new BigInteger(line.substring(line.indexOf(", ") + 1));
      Pair<BigInteger> firstPair = new Pair<BigInteger>(crossOne.subtract(BigInteger.ONE), crossOne.add(BigInteger.ONE));
      Pair<BigInteger> secondPair = new Pair<BigInteger>(crossTwo.subtract(BigInteger.ONE), crossTwo.add(BigInteger.ONE));
      primes.addCross(new Pair<Pair<BigInteger>>(firstPair, secondPair));
    }
    scanner.close();
    return true;
	}
  
  public static boolean savePrimes(Primes primes, String filename) throws IOException {
    File file = new File(Config.DATAPATH + filename);
    if(!file.exists()) {
      file.createNewFile();
    }
    FileWriter writer = new FileWriter(file);

    for (BigInteger prime : primes.iteratePrimes()) {
      String writeLine = prime.toString() + "\n";
      writer.write(writeLine);
    }
    writer.close();
  	return true;
  }
  

  public static boolean saveCrosses(Primes primes, String filename) throws IOException {
    File file = new File(Config.DATAPATH + filename);
    FileWriter writer = new FileWriter(file);
    
    for (Pair<BigInteger> middlePair : primes.iterateCrosses()) {
      String writeLine = middlePair.toString() + "/n";
      writer.write(writeLine);
    }
    writer.close();
  	return true;
  }
}