package PRU03E06;

public class Zona{
	private int entradesPerVendre;
	public Zona(int n) {
		entradesPerVendre=n;
	}
	public int getEntradesPerVendre() {
		return entradesPerVendre;
	}
	public void vendre(int n) {
		if (this.entradesPerVendre==0) {
			System.out.println("Ho sentim, le entrdes per la zona "+" triada estan exhaurides.");
		}else if (this.entradesPerVendre<n) {
			System.out.println("Nomes queden "+this.entradesPerVendre+" entrades per aquesta zona.");
		}
		if (this.entradesPerVendre >=n) {
			entradesPerVendre -= n;
			System.out.println("Aquí té les seves "+n+" entrades, gràcies.");
		}
		
	}
}

