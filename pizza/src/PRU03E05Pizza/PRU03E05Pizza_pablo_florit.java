package PRU03E05Pizza;
	public class PRU03E05Pizza_pablo_florit {
	  public String tamany;
	  public String tipus;
	  public String estat;
	  public static int demanades = 0;
	  public static int servides = 0;
	  public PRU03E05Pizza_pablo_florit(String tipus, String tamany) {
		  //agafam el tipus i tamany de la pizza i afegim l'estat (demanada per defecte)
			this.tipus = tipus;
			this.tamany = tamany;
			this.estat = "demanada";
			//sumam 1  la quantitat de pizzes demanades
		    PRU03E05Pizza_pablo_florit.demanades++;
	  }
	  //mostram la pizza, pero nomes si aquesta compleix les condicions de tipus i tamany del titol
	  public String toString() {
		  if ((tipus=="margarita"|tipus=="quatre formatges" |tipus=="funghi")&&(tamany=="mitjana"|tamany=="familiar")) {
			  String pizzaCompleta="pizza " + this.tipus + " " + this.tamany + ", " + this.estat;
			  return pizzaCompleta;
		  }
			else {
				String error="O la pizza o el tamany no son valids";
				return error;
			}
	  }
	  //mostram totes les demanades
	  /**
	   * metodo para obtener cuantas pizzas han sido pedidas 
	   * @return total pedidas (int)
	   */
	  public static int getTotalDemanades() {
	    return PRU03E05Pizza_pablo_florit.demanades;
	  }
	  //mostram totes les servides
	  public static int getTotalServides() {
	    return PRU03E05Pizza_pablo_florit.servides;
	  }
	  /**
	   * sirve la pizza
	   */
	  public void sirve() {
		//si esta demanada canviam l'estat a servida,...
	    if (this.estat.equals("demanada")) {
	      this.estat = "servida";
	      System.out.println("servint pizza!");
	      //afegim 1 al total de servides
	      PRU03E05Pizza_pablo_florit.servides++;
	    } else {
	    	//...si ja s'ha servit ho deim
	      System.out.println("aquesta pizza ja s'ha servit");
	    }
	  }
	}
