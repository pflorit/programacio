import java.util.Scanner;
public class PREX1AVAEX05_pablo_florit {
	public static String cadenaAXifrar;
	public static String xifratCesar(String cadenaAXifrar) {
		//mientras i sea menor que el largo de la cadena:
		for (int i=0;i<cadenaAXifrar.length();i++) {
			//cogemos el caracter de la posicion i
			char b=cadenaAXifrar.charAt(i);
			//si es mayor que 'z' :
				if ((b+3)>'z') {
					//sumaremos 3, las posiciones que queremos mover y le tenemos que restar 26, para volver a empezar el abecedario.
					b=(char) (b+3-26);
					System.out.print(b);
				}
				//movemos 3 posiciones
				else {
					b=(char)(b+3);
					System.out.print(b);
				}
		}
		return cadenaAXifrar;
	}
	public static void main(String[]args) {
		//se pide al usuario la palabra o frase que qiera cifrar.
		Scanner sc=new Scanner(System.in);
		System.out.print("Inserta una palabra/frase y la cifraremos: ");
		cadenaAXifrar=sc.nextLine();
		//ciframos la palabra introducida con el metodo que hemos implementado.
		cadenaAXifrar=xifratCesar(cadenaAXifrar);
	}
	

}
