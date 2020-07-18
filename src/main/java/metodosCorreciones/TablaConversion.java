package main.java.metodosCorreciones;

public class TablaConversion implements MetodoCorreccion
{
	private Map<int, int> tabla_conversiones = new HashMap<int, int>();

	public TablaConversion(Map<int, int> tabla_conversiones)
	{
		this.tabla_conversiones = tabla_conversiones;
	}
	
	public int corregir(int puntaje_total_resolucion, int puntaje_total_parcial) {
		return this.tabla_conversiones.get(puntaje_total_resolucion);
	}
}
