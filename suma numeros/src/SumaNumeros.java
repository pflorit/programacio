import java.util.Scanner;
public class SumaNumeros {
	public static void main(String[]args) {
		Scanner sc=new Scanner(System.in);
		int numero;
		int total=0;
		do {
		numero=sc.nextInt();
		if (numero<0) {
			}else {
			for (int i=0; i<String.valueOf(numero).length();i++) {
				String c = String.valueOf(Integer.toString(numero).charAt(i));
				 total+=Integer.parseInt(c);
				if(i==String.valueOf(numero).length()-1) {
					System.out.println(c+" = "+total);
				}else {
					System.out.print(c+" + ");
			}
		}
			total=0;
	}}while(numero>=0);
}}
