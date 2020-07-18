package main.java.primerIntento;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import main.java.examen.Parcial;
import main.java.examen.Pregunta;
import main.java.examen.Resolucion;
import main.java.metodosCorreciones.MetodoCorreccion;

import java.util.List;

public class Docente 
{
	private List<Alumno> alumnos = new ArrayList<>();
	
	private List<Resolucion> resoluciones = new ArrayList<>();
	
	private List<Resolucion> resolucionesCorregidas = new ArrayList<>();
	
	public Parcial armar_parcial(int nota_necesaria, MetodoCorreccion metodo, List<Pregunta> preguntas)
	{
		return Parcial(nota_necesaria, metodo, preguntas);
	}
	
	public void dar_parcial_a_alumnos(Parcial parcial)
	{
		this.alumnos.stream().forEach(alumno -> alumno.recibir_parcial(parcial));
	}
	
	public void recibir_resolucion(Resolucion resolucion)
	{
		this.resoluciones.add(resolucion);
	}
	
	public void corregir_resoluciones(Parcial parcial)
	{
		this.resoluciones.stream().forEach(resolucion -> resolucion.corregir());
		this.resolucionesCorregidas.addAll(this.resoluciones);
		this.resoluciones.clear();
	}
}