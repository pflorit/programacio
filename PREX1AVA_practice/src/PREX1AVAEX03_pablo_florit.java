import java.util.Scanner;
public class PREX1AVAEX03_pablo_florit {
	//creamos las varibles
	public static int N;
	public static int K;
	public static int result; 
	//funcion factorial, calcula el factorial de un numero
	public static int factorial (int n) {
		int i=n-1;
		//si n es 0, el factorial es 1
		if (n==0) {
			n=1;
		}
		else {
			//calculo del factorial de un numero
			while (i>0 ) {
				n=n*i;
				i=i-1;
			}
		}
		return n;
	}
	//funcion del calculo
	public static int binomialCoefficient(int N, int K) {
		int kfact=factorial(K);
		int nfact=factorial(N);
		int nk=N-K;
		int nkfact=factorial(nk);
		result=nfact/(nkfact*kfact);
		System.out.print(N+"! / (("+N+"-"+K+")! * "+K+"!)= ");
		System.out.println(" "+nfact+" /("+nkfact+"*"+kfact+")="+result);
		return result;
		
	}
	//main
	public static void main(String[] args) {
		//el usuario insera el valor de n y k
		Scanner sc=new Scanner(System.in);
		System.out.print("Inserta el valor de n: ");
		N=sc.nextInt();
		System.out.print("Inserta el valor de k: ");
		K=sc.nextInt();
		result=binomialCoefficient(N, K);
		
	}

}
