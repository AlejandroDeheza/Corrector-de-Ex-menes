package main.java.primerIntento;

import main.java.examen.Parcial;
import main.java.examen.Resolucion;

public class Alumno 
{
	private Docente docente;
	
	private Parcial parcial_actual;
	
	public void recibir_parcial(Parcial parcial)
	{
		this.parcial_actual = parcial;
	}
	
	public Resolucion realizar_examen()
	{
		List<int> puntajes_por_respuestas = new ArrayList<>();
		
		// esto lo hace el alumno hasta que responde todas las preguntas
		int puntaje = pregunta.puntaje_respuesta(respuesta);
		puntajes_por_respuestas.add(puntaje);

		
		
		return Resolucion(puntajes_por_respuestas, parcial_actual, this);
	}
	
	public void entregar_resolucion(Resolucion resolucion)
	{
		this.docente.recibir_resolucion(resolucion);
	}
}
