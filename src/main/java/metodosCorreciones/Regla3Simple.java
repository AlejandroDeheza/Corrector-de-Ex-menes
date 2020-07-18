package main.java.metodosCorreciones;

public class Regla3Simple implements MetodoCorreccion
{
	public int corregir(int puntaje_total_resolucion, int puntaje_total_parcial) {
		return (puntaje_total_resolucion * 10) /puntaje_total_parcial;
	}
}
