package pru03.E02;

/**
 * @author xavier
 *
 */
public interface InterfaceCotxe {
	/**
	 * El metode arrancarMotor llan�ara una excepcio si s'intenta
	 * arrancar el motor pero el motor ja esta ences
	 * @throws Exception
	 */
	public void arrancarMotor() throws Exception;
	/**
	 * El metode comprovaMotor retornara l'estat del motor
	 * segons els possibles valors de l'enum type EstatsMotorCotxe
	 * @return
	 */
	public EstatsMotorCotxe comprovaMotor();
	/**
	 * El metode getRevolucions() retornara un sencer corresponent
	 * a les revolucions que te el motor. Si el motor esta aturat retornara 0
	 * Si el motor est� en marxa retornar� un numero aleatori entre 1 i 6500
	 * @return 
	 */
	public int getRevolucions();
	/**
	 * El metode aturarMotor() llan�ara una excepcio si s'intenta
	 * aturar el motor pero el motor est� aturat.
	 * @throws Exception
	 */
	public void aturarMotor() throws Exception;
	public void pujarMarxa() throws Exception;
	public void devallarMarxa() throws Exception;
}