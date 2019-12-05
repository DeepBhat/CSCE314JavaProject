import java.math.BigInteger;

public class NaiveTest
{
	public static boolean isPrime(BigInteger candidate)
	{
		// Source: https://stackoverflow.com/questions/2385909/what-would-be-the-fastest-method-to-test-for-primality-in-java
		if (candidate.compareTo(BigInteger.TWO) == -1) return false;
		if (candidate.equals(BigInteger.TWO) || candidate.equals(new BigInteger("3"))) return true;
		if (candidate.remainder(BigInteger.TWO).equals(BigInteger.ZERO) || candidate.remainder(new BigInteger("3")).equals(BigInteger.ZERO)) return false;
		for(BigInteger i = new BigInteger("6"); i.compareTo(candidate.sqrt()) == -1; i.add(new BigInteger("6"))) {
			if (candidate.remainder(i.subtract(BigInteger.ONE)).equals(BigInteger.ZERO) || candidate.remainder(i.add(BigInteger.ONE)).equals(BigInteger.ZERO)) 
				return false;
		}
		return true;
	}
}
