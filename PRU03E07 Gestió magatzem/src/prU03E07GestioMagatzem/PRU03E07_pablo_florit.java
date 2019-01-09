package prU03E07GestioMagatzem;
import java.util.ArrayList;
import java.util.Scanner;
public class PRU03E07_pablo_florit{

	public static void main(String[]args) {
		int opc;
		String codi;
		String descripcio;
		double preuCompra;
		double preuVenda;
		int stock;
		    //cream un arrayList a on guardarem els diferents articles
		ArrayList<Article>articles=new ArrayList();
	    	//mostram el menu principal
		do {
		Scanner sc=new Scanner(System.in);
		System.out.println("|-------------------------|");
		System.out.println("| 1. Llistat              |");
		System.out.println("| 2. Alta                 |");
		System.out.println("| 3. Baixa                |");
		System.out.println("| 4. Modificació          |");
		System.out.println("| 5. Entrada de mercaderia|");
		System.out.println("| 6. Sortida de mercaderia|");
		System.out.println("| 7. Sortir               |");
		System.out.println("|-------------------------|");
		System.out.print("Selecciona una opccio: ");
		opc=sc.nextInt();
	    	//tractam les diferents opcions
		switch (opc) {
		case 1:
			//si no hi ha articles ho deim,aixo ho repetirem a altres opcions
			if (articles.size()<=0) {
				System.out.println("No hi ha articles al magatzem.");
			}
			//mostram els articles
			else {
				System.out.println(articles);
			}
			break;
		case 2:
			//cream la variable aux, on guardarem l'informacio que ens doni l'usuari
			Article aux;
			System.out.println("....afegir article....");
			//demanm els diferents valors requerits per al article
			sc.nextLine();
			System.out.print("Codi: ");
			 codi=sc.nextLine();
			System.out.print("Descripció: ");
			 descripcio=sc.nextLine();
			System.out.print("Preu de compra: ");
			 preuCompra=sc.nextDouble();
			System.out.print("Preu de venda: ");
			 preuVenda=sc.nextDouble();
			System.out.print("Stock actual: ");
			 stock=sc.nextInt();
			 //cream un nou article amb tot el que ha inserit l'usuari
			 aux=new Article();
			 aux.setCodi(codi);
			 aux.setDescripcio(descripcio);
			 aux.setPreuDeCompra(preuCompra);
			 aux.setPreuDeVenda(preuVenda);
			 aux.setStock(stock);
			 articles.add(aux);
			break;
		case 3:
			if (articles.size()<=0) {
				System.out.println("No hi ha cap article al magatzem.");
			}
			else {
			//mostram tots el articles que hi ha
			for (int i=0; i<articles.size();i++) {
				System.out.println("....article "+i+"....");
				System.out.println(articles.get(i));
			}
			//demanam l'article a eliminar i ho esborram
				System.out.print("Insereix el numero de l'article que vols eliminar: ");
					int numarticle=sc.nextInt();
					articles.remove(numarticle);
				System.out.println("Article eliminat.");
			}
			break;
		case 4:
			if (articles.size()<=0) {
				System.out.println("No hi ha cap article al magatzem.");
			}
			//primer mostram tots els articles
			else {
			for (int i=0; i<articles.size();i++) {
				System.out.println("....article "+i+"....");
				System.out.println(articles.get(i));
			}
			//demanam quin article es vol modificar
				System.out.print("Insereix el numero de l'article que vols modificar: ");
					int numarticle=sc.nextInt();
			//menu amb cada un dels parametres que es poden modificar
				System.out.println("..............................................................................");
				System.out.println("1. modificar codi.");
				System.out.println("2. modificar descripcio.");
				System.out.println("3. modificar preu de compra.");
				System.out.println("4. modificar preu de venda.");
				System.out.println("\"la modificacio d'estoc es fará al apartat d'entrada/sortida de mercaderia\" ");
				System.out.println("..............................................................................");
					System.out.println("Seleccioni opcio: ");
						int mod=sc.nextInt();
						System.out.println(" ");
					switch (mod) {
					//feim la modificacio que vol l'usuari
					case 1:
						sc.nextLine();
						System.out.println("insereix el nou codi: ");
						codi=sc.nextLine();
					articles.get(numarticle).setCodi(codi);
					break;
					case 2:
						sc.nextLine();
						System.out.println("insereix la nova descripcio: ");
						descripcio=sc.nextLine();
					articles.get(numarticle).setDescripcio(descripcio);
					break;
					case 3:
						System.out.println("insereix el nou preu de compra: ");
						preuCompra=sc.nextDouble();
					articles.get(numarticle).setPreuDeCompra(preuCompra);
					break;
					case 4:
						System.out.println("insereix el nou preu de venda: ");
						preuVenda=sc.nextDouble();
					articles.get(numarticle).setPreuDeVenda(preuVenda);
					break;
					default:
						System.out.println("Seleccioni una aopcio del menu.");
					}
			}
			break;
			//al 5 i 6, entrada/sortida, es demana una quantitat i es suma o resta a l'estoc segons el cas
		case 5:
			if (articles.size()<=0) {
				System.out.println("No hi ha cap article al magatzem.");
			}
			else {
			for (int i=0; i<articles.size();i++) {
				System.out.println("....article "+i+"....");
				System.out.println(articles.get(i));
			}
			System.out.println("Seleccioni el numero d'article: ");
			 	int numarticle=sc.nextInt();
			 	System.out.println("Insereix la quantitat: ");
			 		int entrada=sc.nextInt();
			 		int stockfinal=articles.get(numarticle).getStock()+entrada;
			 		articles.get(numarticle).setStock(stockfinal);
			}
			break;
		case 6:
			if (articles.size()<=0) {
				System.out.println("No hi ha cap article al magatzem.");
			}
			else {
			for (int i=0; i<articles.size();i++) {
				System.out.println("....article "+i+"....");
				System.out.println(articles.get(i));
			}
			System.out.println("Seleccioni el numero d'article: ");
			 	int numarticle=sc.nextInt();
			 	System.out.println("Insereix la quantitat: ");
			 		int sortida=sc.nextInt();
			 		int stockfinal=articles.get(numarticle).getStock()-sortida;
			 		articles.get(numarticle).setStock(stockfinal);
			}
			break;
		case 7:
			System.out.println("Adeu!");
			break;
		default:
			System.out.println("Has de inserir el numero corresponent a l'opcio del menu que desitgis.");
		}
		}while (opc !=7);

	}
}
