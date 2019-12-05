import java.util.ArrayList;
import java.util.Iterator;
import java.math.BigInteger;

/*
 *  Desc: This class generates primes, twin primes, and hexagon crosses using BigInteger data types.
 */
public class Primes {

	ArrayList<BigInteger> primeList = new ArrayList<BigInteger>();
	ArrayList<Pair<BigInteger>> twinList = new ArrayList<Pair<BigInteger>>();
	ArrayList<Pair<Pair<BigInteger>>> hexList = new ArrayList<Pair<Pair<BigInteger>>>();


	// Member variables for containing out lists of integers, twin primes, hexagon
	// crosses, and the pairs of twin primes that make up the hex crosses.

	// Add a prime to the prime list if and only iff it is not already in the list.
	// Assuming x is prime.
	public void addPrime(BigInteger x) {
		// check if the element is already in the list. If not, add it.
		for (int i = 0; i < primeList.size(); ++i) {
			if (x.compareTo(primeList.get(i)) == 0) {
				return;
			}
		}

		primeList.add(x);
	}

	//Add a pair to the pair list
	public void addPair(Pair<BigInteger> pair) {
		twinList.add(pair);
	}

	// Adds a pair of BigIntegers that represent a Hexagonal Cross.
	public void addCross(Pair<Pair<BigInteger>> pair) {
		hexList.add(pair);
	} 

	public void clearPrimes() {
		primeList.clear();
	}

	public void clearTwins() {
		twinList.clear();
	}

	public void clearCrosses() {
		hexList.clear();
	}

	// Output the prime list. Each prime should be on a separate line and the total
	// number of primes should be on the following line.
	public void printPrimes() {
		for (BigInteger prime : primeList) {
			System.out.println(prime);
		}
		System.out.println("Total primes: " + primeList.size());
	}

	// Output the twin prime list. Each twin prime should be on a separate line with
	// a comma separating them, and the total number of twin primes should be on the
	// following line.
	public void printTwins() {
		for (Pair<BigInteger> primePair : twinList) {
			System.out.println(primePair);
		}
		System.out.println("Total twins: " + twinList.size());
	}

	// Output the hexagon cross list. Each should be on a separate line listing the
	// two twin primes and the corresponding hexagon cross, with the total number on
	// the following line.
	public void printHexes() {
		for (Pair<Pair<BigInteger>> hexPair : hexList) {
			System.out.println("Primes Pair: " + hexPair.getFirst() + " and " + hexPair.getSecond()
					+ " separated by " + hexPair.getFirst().getFirst().add(BigInteger.ONE) + ", "
					+ hexPair.getSecond().getFirst().add(BigInteger.ONE));
		}
		System.out.println("Total Hexes: " + hexList.size());
	}

	// Generate and store a list of primes.
	public void generatePrimes(int count) {
		addPrime(new BigInteger("2"));
		BigInteger candidatePrime = new BigInteger("3");

		while (count >= 1) {
			if (isPrime(candidatePrime)) {
				addPrime(candidatePrime);
				count--;
			}
			candidatePrime = candidatePrime.add(BigInteger.TWO);
		}
	}

	// Generate primes from a given starting point
	public void generatePrimes(BigInteger start, int count)	 {
		BigInteger candidatePrime = start;
		while (count >= 1) {
			if (isPrime(candidatePrime)) {
				addPrime(candidatePrime);
				count--;
			}
			candidatePrime = candidatePrime.add(BigInteger.ONE);
		}
	}

	// Generate and store a list of twin primes.
	public void generateTwinPrimes() {
		for (int i = 0; i < primeList.size() - 1; ++i) {
			if (primeList.get(i).intValue() == primeList.get(i + 1).intValue() - 2) {
				Pair<BigInteger> pair = new Pair<BigInteger>(primeList.get(i), primeList.get(i + 1));
				twinList.add(pair);
			}
		}
	}

	// Generate and store the hexagon crosses, along with the two twin primes that
	// generate the hexagon cross.
	public void generateHexPrimes() {
		// First store all the middle numbers of twin primes in a list
		ArrayList<BigInteger> middleNumbers = new ArrayList<BigInteger>(twinList.size());
		for (int i = 0; i < twinList.size(); ++i) {
			middleNumbers.add(twinList.get(i).getFirst().add(BigInteger.ONE));
		}

		// Now we iterate through the middle numbers. Since the middle numbers have a
		// 1-1 mapping with their
		// twin primes counterparts, we can find pairs of middle numbers s.t. one is 2 *
		// the other.
		// Then for the corresponding indices, we add the pairs to the hexagon cross
		// list.
		for (int i = 0; i < middleNumbers.size(); ++i) {
			for (int j = i; j < middleNumbers.size(); ++j) {
				if (middleNumbers.get(j).compareTo(middleNumbers.get(i).multiply(BigInteger.TWO)) == 0) {
					Pair<BigInteger> firstPair = new Pair<BigInteger>(twinList.get(i).getFirst(),
							twinList.get(i).getSecond());
					Pair<BigInteger> secondPair = new Pair<BigInteger>(twinList.get(j).getFirst(),
							twinList.get(j).getSecond());
					Pair<Pair<BigInteger>> HexPrimes = new Pair<Pair<BigInteger>>(firstPair, secondPair);
					hexList.add(HexPrimes);
				} else if (middleNumbers.get(j).compareTo(middleNumbers.get(i).multiply(BigInteger.TWO)) == 1)
					break; // the middle number at j is over two times the value of middle number at i, we
							// need to stop checking
			}
		}
	}

	public boolean isPrime(BigInteger x) {
		for (BigInteger i = BigInteger.valueOf(2); i.compareTo(x.sqrt()) != 1; i = i.add(BigInteger.ONE)) {
			if (x.remainder(i).compareTo(BigInteger.ZERO) == 0) {
				return false;
			}
		}
		return true;
	}

	// Count the number of digits in the last (and thus largest) prime.
	public int sizeofLastPrime() {
		if (primeList.size() > 0) {
			return primeList.get(primeList.size() - 1).toString().length();
		}
		else {
			return 0;
		}

	}
	
	// Count the number of digits in the two entries in the last (and thus largest) hexagon cross
	public Pair<Integer> sizeofLastCross() {
		if (hexList.size() > 0) {
			Integer first = hexList.get(hexList.size() - 1).getFirst().getFirst().add(BigInteger.ONE).toString().length();
			Integer second = hexList.get(hexList.size() - 1).getSecond().getFirst().add(BigInteger.ONE).toString().length();
			return new Pair<Integer>(first, second);
		}
		else {
			return new Pair<Integer>(0, 0);
		}
	}
	
	// Return the number of primes
	public int primeCount() {
		return primeList.size();
	}
	
	// Return the number of crosses.
	public int crossesCount() {
		return hexList.size();
	}
	
	public class IterablePrimes implements Iterable<BigInteger>{		
		@Override
		public Iterator<BigInteger> iterator() {
			return new PrimesIterator();
		}
	}

	private class PrimesIterator implements Iterator<BigInteger> {
		int currentIndex = 0;

		@Override
		public boolean hasNext() {
			return currentIndex <  primeList.size();
		}

		@Override
		public BigInteger next() {
			BigInteger element = primeList.get(currentIndex);
			currentIndex ++;
			return element;
		}

	}
	
	public IterablePrimes iteratePrimes() { return new IterablePrimes();}

	public class IterableCrosses implements Iterable<Pair<BigInteger>>{

		@Override
		public Iterator<Pair<BigInteger>> iterator() {
			return new CrossIterator();
		}	
	}

	private class CrossIterator implements Iterator<Pair<BigInteger>> {
		int currentIndex = 0;

		@Override
		public boolean hasNext() {
			return currentIndex < hexList.size();
		}

		@Override
		public Pair<BigInteger> next() {
			BigInteger firstMiddle = hexList.get(currentIndex).getFirst().getFirst().add(BigInteger.ONE);
			BigInteger secondMiddle = hexList.get(currentIndex).getSecond().getFirst().add(BigInteger.ONE);
			currentIndex++;
			return new Pair<BigInteger>(firstMiddle, secondMiddle);
		}

	}
	
	public IterableCrosses iterateCrosses() { return new IterableCrosses();}
}

