package main.java.metodosCorreciones;

import java.util.ArrayList;
import java.util.List;

public class NotaMasAltaEntreVariosCriterios implements MetodoCorreccion
{
	private List<MetodoCorreccion> metodos_correccion = new ArrayList<>();
	
	public int corregir(int puntaje_total_resolucion, int puntaje_total_parcialn) {
		return this.metodos_correccion.max(metodo_correccion -> metodo_correccion.corregir(puntaje_total_resolucion, puntaje_total_parcialn));
	}
}
