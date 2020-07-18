package main.java.metodosCorreciones;

import java.util.ArrayList;
import java.util.List;

public class PromedioEntreVariosCriterios implements MetodoCorreccion
{
	private List<MetodoCorreccion> metodos_correccion = new ArrayList<>();
	
	public int corregir(int puntaje_total_resolucion, int puntaje_total_parcial) {
		return (this.metodos_correccion.sum(metodo_correccion -> metodos_correccion.corregir(puntaje_total_resolucion, puntaje_total_parcialn))) / this.metodos_correccion.length();
	}
}