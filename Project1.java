import java.math.BigInteger;

public class Project1 {
	public static void main(String[] args) 
	{
		Primes primes = new Primes();
		primes.generatePrimes(10000);
		primes.generateTwinPrimes();
		primes.generateHexPrimes();
		for(Pair<BigInteger> crossPair : primes.iterateCrosses()) {
			System.out.println(crossPair);
		}
		
	}
}
