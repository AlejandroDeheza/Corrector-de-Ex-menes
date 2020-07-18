package main.java.metodosCorreciones;

public class ConDescuento implements MetodoCorreccion
{
	private int descuento;
	
	public ConDescuento(int descuento)
	{
		this.descuento = descuento;
	}
	
	public int corregir(int puntaje_total_resolucion, int puntaje_total_parcial) {
		return puntaje_total_resolucion - this.descuento;
	}
}