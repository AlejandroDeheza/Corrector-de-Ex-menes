package main.java.examen;

import main.java.primerIntento.Alumno;

public class Resolucion 
{
	private List<int> puntajes_por_respuestas = new ArrayList<>();
	
	private Parcial parcial_asociado;
	
	private Alumno alumno;
	
	private int nota_final;
	
	private Boolean aprobo;
	
	public Resolucion(List<int> puntajes_por_respuestas, Parcial parcial_asociado, Alumno alumno)
	{
		this.puntajes_por_respuestas = puntajes_por_respuestas;
		this.parcial_asociado = parcial_asociado;
		this.alumno = alumno;
	}
	
	public int puntaje_total_resolucion()
	{
		return this.puntajes_por_respuestas.sum();
	}
	
	public void corregir()
	{
		this.nota_final = this.parcial_asociado.obtener_nota_final(this.puntaje_total_resolucion());
		
		if(this.nota_final >= this.parcial_asociado.get_nota_necesaria_para_aprobar()) {
			this.aprobo = true;
		}else {
			this.aprobo = false;
		}
	}
}
