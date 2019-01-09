package PRU03E06;
import java.util.Scanner;
public class PRU03E06_Pablo_Florit {
	
	public static void main(String[]args) {
		Scanner sc= new Scanner(System.in);
		int opc;
		//cream cada una de les zones amb la quantitat d'entrades que hi ha per  cada una
		Zona platea= new Zona (200);
		Zona amfiteatre= new Zona (1000);
		Zona VIP= new Zona (25);
		
		do {
		//menu prinipal del programa
		System.out.println("1. Mostrar número d'entrades lliures.");
		System.out.println("2. Comprar entrades.");
		System.out.println("3. Sortir.");
		System.out.print("Seleccioni el numero de l'operacio a realitzar: ");
		opc=sc.nextInt();
		//tractam les diferents opcions
		switch (opc){
		case 1:
			//mostram les entrades disponibles per a cada zona
			System.out.println("platea: "+platea.getEntradesPerVendre());
			System.out.println("amfiteatre: "+amfiteatre.getEntradesPerVendre());
			System.out.println("VIP: "+VIP.getEntradesPerVendre());
		break;
		case 2:
			//l'usuari selecciona la zona i la cuantitat d'entrades que vol
			System.out.print("Seleccioni la zona (1=platea, 2=amfiteatre, 3=VIP): ");
			 int zona=sc.nextInt();
			System.out.print("Insereixi numero d'entrades: ");
				int entrades=sc.nextInt();
				switch (zona) {
				case 1:
					platea.vendre(entrades);
				break;
				case 2:
					amfiteatre.vendre(entrades);
				break;
				case 3:
					VIP.vendre(entrades);
				break;
				default:
					System.out.println("Opcio incorrecta.");
				}
		break;
		case 3:
			//sortir del programa
			System.out.println("Adeu!");
		break;
		default:
			System.out.println(opc +" no es una opcio valida.");
		}
		}while(opc!=3);
	}
}
