package PRU03E05Pizza;

public class ComandesPizza {

	public static void main(String[] args) {
		PRU03E05Pizza_pablo_florit p1 = new PRU03E05Pizza_pablo_florit("margarita","mitjana");
		PRU03E05Pizza_pablo_florit p2 = new PRU03E05Pizza_pablo_florit("funghi","familiar");
		p2.sirve();
		PRU03E05Pizza_pablo_florit p3 = new PRU03E05Pizza_pablo_florit ("quatre formatges","mitjana");
		System.out.println(p1);
		System.out.println(p2);
		System.out.println(p3);
		p2.sirve();
		System.out.println("demanades: "+ PRU03E05Pizza_pablo_florit.getTotalDemanades());
		System.out.println("servides: "+ PRU03E05Pizza_pablo_florit.getTotalServides());	
	}

}
