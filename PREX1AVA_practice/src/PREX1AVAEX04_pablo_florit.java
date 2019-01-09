import java.util.Scanner;
public class PREX1AVAEX04_pablo_florit {
	public static void main(String[]args) {
		int n;
		Scanner sc=new Scanner(System.in);
		//pedimos que inserte un numero
		System.out.print("Input a number: ");
		n=sc.nextInt();
		System.out.print(n+" ");
		do { 
		//si es par
		if (n%2==0) {//n%2==0 comprueva si es par
			 //divide el numero entre 2
			 n=n/2;
			 System.out.print(n+" ");
			 //si no es par
			 }else{
			//multipica el numero por 3 y le suma 1
			 n=(n*3)+1;
			 System.out.print(n+" ");
		}
	//entrara en el bucle hasta que el resultado sea 1
	}while (n>1);
 }
}
