import java.util.ArrayList; 
import java.math.BigInteger;

/*
 *  Desc: This class generates primes, twin primes, and hexagon crosses using BigInteger data types.
 */
public class Primes {
	
	ArrayList<BigInteger> primeList = new ArrayList<BigInteger>();
	ArrayList<Pair<BigInteger>> twinList = new ArrayList<Pair<BigInteger>>();
	ArrayList<Pair<Pair<BigInteger>>> hexList = new ArrayList<Pair<Pair<BigInteger>>>();


	// Pair class implementation. Holds two values and nothing more.
	private class Pair<T> {
		T first;
		T second;
		
		public Pair (T firstElement, T secondElement) {
			first = firstElement;
			second = secondElement;
		}
		

		public T getFirst() { return first; }
		
		public T getSecond() { return second; }
		
		
		public void setFirst(T value) { first = value; }
		
		public void setSecond(T value) { second = value; }
		
		public void newValues (ArrayList<T> values) { 
			if (values.size() != 2) 
				System.err.println("Error: Please provide an array of size 2");
			first = values.get(0);
			second = values.get(1);
		}
		
		public String toString() {
			return ("" + first + ", " + second);
		}
		
	}
	
	// Member variables for containing out lists of integers, twin primes, hexagon crosses, and the pairs of twin primes that make up the hex crosses.
	
	// Add a prime to the prime list if and only iff it is not already in the list. Assuming x is prime.
	public void addPrime(BigInteger x)
	{
		//check if the element is already in the list. If not, add it.
		for (int i = 0; i < primeList.size(); ++i)
			if (x.compareTo(primeList.get(i)) == 0)
				return;
		
		primeList.add(x);
	}
	
	// Output the prime list. Each prime should be on a separate line and the total number of primes should be on the following line.
	public void printPrimes()
	{
		for (int i = 0; i < primeList.size(); ++i)
			System.out.println(primeList.get(i));
		System.out.println("Total primes: " + primeList.size());
	}
		
	// Output the twin prime list. Each twin prime should be on a separate line with a comma separating them, and the total number of twin primes should be on the following line.
	public void printTwins()
	{
		for(int i = 0; i < twinList.size(); ++i) 
			System.out.println(twinList.get(i));
		System.out.println("Total twins: " + twinList.size());
	}
		
	// Output the hexagon cross list. Each should be on a separate line listing the two twin primes and the corresponding hexagon cross, with the total number on the following line.
	public void printHexes()
	{
		for (int i = 0; i < hexList.size(); ++i)
			System.out.println("Primes Pair: " + hexList.get(i).getFirst() + " and " + hexList.get(i).getSecond() +
					" separated by " + hexList.get(i).getFirst().getFirst().add(BigInteger.ONE) +
					", " + hexList.get(i).getSecond().getFirst().add(BigInteger.ONE));
		System.out.println("Total Hexes: " + hexList.size());
	}
		
	// Generate and store a list of primes.
	public void generatePrimes(int count)
	{
		int candidatePrime = 2;
		addPrime(new BigInteger("2"));
		candidatePrime = 3;
		
		while (count > 1) {
			if (isPrime(BigInteger.valueOf(candidatePrime))) {
				addPrime(BigInteger.valueOf(candidatePrime));
				count --;
			}
			candidatePrime += 2;
		}
	}
	
	// Generate and store a list of twin primes.
	public void generateTwinPrimes()
	{
		for (int i = 0; i < primeList.size() - 1; ++i) {
			if (primeList.get(i).intValue() == primeList.get(i + 1).intValue() - 2) {
				Pair<BigInteger> pair = new Pair<BigInteger>(primeList.get(i), primeList.get(i + 1));
				twinList.add(pair);
			}
		}
	}
	
	// Generate and store the hexagon crosses, along with the two twin primes that generate the hexagon cross.
	public void generateHexPrimes()
	{
		//First store all the middle numbers of twin primes in a list
		ArrayList <BigInteger> middleNumbers = new ArrayList <BigInteger> (twinList.size());
		for (int i = 0; i < twinList.size(); ++i)
			middleNumbers.add(twinList.get(i).getFirst().add(BigInteger.ONE));
		
		// Now we iterate through the middle numbers. Since the middle numbers have a 1-1 mapping with their
		// twin primes counterparts, we can find pairs of middle numbers s.t. one is 2 * the other.
		// Then for the corresponding indices, we add the pairs to the hexagon cross list.
		for (int i = 0; i < middleNumbers.size(); ++i) {
			for (int j = i; j < middleNumbers.size(); ++j) {
				if (middleNumbers.get(j).compareTo(middleNumbers.get(i).multiply(BigInteger.TWO)) == 0) {
					Pair<BigInteger> firstPair = new Pair<BigInteger>(twinList.get(i).getFirst(), twinList.get(i).getSecond());
					Pair<BigInteger> secondPair = new Pair<BigInteger>(twinList.get(j).getFirst(), twinList.get(j).getSecond());
					Pair<Pair<BigInteger>> HexPrimes = new Pair<Pair<BigInteger>>(firstPair, secondPair);
					hexList.add(HexPrimes);
				}
				else if(middleNumbers.get(j).compareTo(middleNumbers.get(i).multiply(BigInteger.TWO)) == 1)
					break; //the middle number at j is over two times the value of middle number at i, we need to stop checking
			}
		}
		
	}

	public boolean isPrime(BigInteger x){
		for (int i = 2; i <= x.sqrt().intValue(); ++i)
			if (x.remainder(BigInteger.valueOf(i)).compareTo(new BigInteger("0")) == 0)
				return false;
		return true;
	}
}
