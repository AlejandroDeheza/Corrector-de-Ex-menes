package main.java.examen;

import java.util.ArrayList;
import java.util.List;

import main.java.metodosCorreciones.MetodoCorreccion;

public class Parcial 
{
	private int nota_necesaria_para_aprobar;
	
	private MetodoCorreccion metodo_correccion;
	
	private List<Pregunta> preguntas = new ArrayList<>();
	
	public Parcial(int nota_necesaria_para_aprobar, MetodoCorreccion metodo_correccion, List<Pregunta> preguntas)
	{
		this.nota_necesaria_para_aprobar = nota_necesaria_para_aprobar;
		this.metodo_correccion = metodo_correccion;
		this.preguntas = preguntas;
	}
	
	public int get_nota_necesaria_para_aprobar() {
		return this.nota_necesaria_para_aprobar;
	}
	
	public int puntaje_total_parcial()
	{
		return this.preguntas.stream().sum(pregunta -> pregunta.get_peso_especifico());
	}
	
	public int obtener_nota_final(int puntaje_total_resolucion)
	{
		return this.metodo_correccion.corregir(puntaje_total_resolucion, this.puntaje_total_parcial());
	}
}
