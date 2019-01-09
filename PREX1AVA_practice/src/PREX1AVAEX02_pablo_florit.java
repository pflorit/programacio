import java.util.Scanner;
public class PREX1AVAEX02_pablo_florit {
	    public double number;
	    //obtener la parte entera
    int getPartSencera (double number) {
    	//convertimos el numero a string
		String numerostr = String.valueOf(number);
		//localizamos la posicion en la que se encuentra la coma
		int i=numerostr.indexOf('.');
		char b=0;
		//en este bucle se van guardando en b todos los caracteres hasta llegar a la coma
		for (int x=0; x<i; x++){
			b=numerostr.charAt(x);
			//mostramos el contenido de b
			System.out.print(b);
		}
		System.out.println(" ");//esto solo es un espacio para una mejor visualizacion del menu.
		return b;
	}
    	//obtener la parte decimal
	int getPartDecimal (double number) {
		//convertimos el numero a string
		String numerostr = String.valueOf(number);
		//localizamos la posicion el la que esta la coma
		int i=numerostr.indexOf('.');
		//con el substring lo que hacemos es eliminar los caracteres que encuentre hasta llegar a donde definamos
		//por eso lo de i+1, la posicion de la coma y una mas
		String b=numerostr.substring(i+1);
		//convertimos el string a integro
		int c = Integer.parseInt(b);
		//lo motramos
		System.out.println(c);
		return c;
	}
	//main
	public static void main(String[]args) {
		PREX1AVAEX02_pablo_florit n;
		n = new PREX1AVAEX02_pablo_florit();
		Scanner sc=new Scanner(System.in);
		int opcio=0;
		//bucle que hasta que no pida salir seguira ejecutando el programa
		do {
		System.out.println("Insereix un numero: ");
		n.number=sc.nextDouble();
		System.out.println("Vols la part sencera (1), la decimal(2) o sortir(3): ");
		opcio=sc.nextInt();
		//segun la opcion que elija el usuario definios lo que pasara
		switch (opcio) {
		case 1:
			n.getPartSencera(n.number);
			break;
		case 2:
			n.getPartDecimal(n.number);
			break;
		case 3:
			System.out.println("Adeu");
			break;
		default:
			System.out.println("Aixo no es una opcio!");
		}
	}while (opcio != 3);
}
}