package main.java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Primera 
{

}

public class Docente 
{
	private list<Alumno> alumnos = new ArrayList<>();
	
	private list<Resolucion> resoluciones = new ArrayList<>();
	
	private list<Resolucion> resolucionesCorregidas = new ArrayList<>();
	
	public Parcial armar_parcial(int nota_necesaria, MetodoCorreccion metodo, list<Pregunta> preguntas)
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
		this.resoluciones.clean();
	}
}

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

public interface MetodoCorreccion
{
	public int corregir(int puntaje_total_resolucion, int puntaje_total_parcial);
}

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

public class Regla3Simple implements MetodoCorreccion
{
	public int corregir(int puntaje_total_resolucion, int puntaje_total_parcial) {
		return (puntaje_total_resolucion * 10) /puntaje_total_parcial;
	}
}

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

public class NotaMasAltaEntreVariosCriterios implements MetodoCorreccion
{
	private List<MetodoCorreccion> metodos_correccion = new ArrayList<>();
	
	public int corregir(int puntaje_total_resolucion, int puntaje_total_parcialn) {
		return this.metodos_correccion.max(metodo_correccion -> metodos_correccion.corregir(puntaje_total_resolucion, puntaje_total_parcialn));
	}
}

public class PromedioEntreVariosCriterios implements MetodoCorreccion
{
	private List<MetodoCorreccion> metodos_correccion = new ArrayList<>();
	
	public int corregir(int puntaje_total_resolucion, int puntaje_total_parcial) {
		return (this.metodos_correccion.sum(metodo_correccion -> metodos_correccion.corregir(puntaje_total_resolucion, puntaje_total_parcialn))) / this.metodos_correccion.length();
	}
}

public class Parcial 
{
	private int nota_necesaria_para_aprobar;
	
	private MetodoCorreccion metodo_correccion;
	
	private list<Pregunta> preguntas = new ArrayList<>();
	
	public Parcial(int nota_necesaria_para_aprobar, MetodoCorreccion metodo_correccion, list<Pregunta> preguntas)
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

public class Pregunta 
{
	private String enunciado;
	private int peso_especifico;
	private String respuesta_correcta;	
	
	public int get_peso_especifico() {
		return this.peso_especifico;
	}
	
	public bool laRespuestaEsCorrecta(String respuesta)
	{
		return this.respuesta_correcta.equals(respuesta);
	}
	
	public int puntaje_respuesta(String respuesta)
	{
		if(this.laRespuestaEsCorrecta(respuesta)) {
			return this.peso_especifico;
		}else {
			return 0;
		}
	}
}


public class Resolucion 
{
	private List<int> puntajes_por_respuestas = new ArrayList<>();
	
	private Parcial parcial_asociado;
	
	private Alumno alumno;
	
	private int nota_final;
	
	private Bool aprobo;
	
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
