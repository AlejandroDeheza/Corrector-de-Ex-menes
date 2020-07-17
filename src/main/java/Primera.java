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
	
	Parcial armar_parcial(int nota_necesaria, MetodoCorreccion metodo, list<Pregunta> preguntas)
	{
		return Parcial(nota_necesaria, metodo, preguntas);
	}
	
	void dar_parcial_a_alumnos(Parcial parcial)
	{
		this.alumnos.stream().forEach(alumno -> alumno.recibir_parcial(parcial));
	}
	
	void corregir_resoluciones(Parcial parcial)
	{
		parcial.corregir_resoluciones();
	}
	
}

public class Alumno 
{
	Parcial parcial_actual;
	
	void recibir_parcial(Parcial parcial)
	{
		this.parcial_actual = parcial;
	}
	
	void realizar_examen()
	{
		//las "respuestas" se hacen en la interfaz grafica
		List<String> respuestas;
		
		Resolucion resolucion = Resolucion(respuestas, this);
		
		parcial_actual.resoluciones.add(resolucion);
	}
}

public interface MetodoCorreccion
{
    int corregir(Resolucion resolucion);
}

public class ConDescuento implements MetodoCorreccion
{
	int descuento;
	
	ConDescuento(int descuento)
	{
		this.descuento = descuento;
	}
	
	int corregir(Resolucion resolucion) {
		return puntaje_total_respuestas() - descuento;
	}
}

public class Regla3Simple implements MetodoCorreccion
{
	int corregir(Resolucion resolucion) {
		return (puntaje_total_respuestas() * 10) /puntaje_total();
	}
}

public class TablaConversion implements MetodoCorreccion
{
	private Map<int, int> tabla_conversion = new HashMap<int, int>();

	TablaConversion(Map<int, int> tabla_conversion)
	{
		this.tabla_conversion = tabla_conversion;
	}
	
	int corregir(Resolucion resolucion) {
		return tabla_conversion.get(puntaje_total_respuestas());
	}
}

public class NotaMasAltaEntreVariosCriterios implements MetodoCorreccion
{
	List<MetodoCorreccion> metodos_correccion = new ArrayList<>();
	
	int corregir(Resolucion resolucion) {
		return this.metodos_correccion.max(metodo_correccion -> metodos_correccion.corregir(resolucion));
	}
}

public class PromedioEntreVariosCriterios implements MetodoCorreccion
{
	List<MetodoCorreccion> metodos_correccion = new ArrayList<>();
	
	int corregir(Resolucion resolucion) {
		return (this.metodos_correccion.sum(metodo_correccion -> metodos_correccion.corregir(resolucion))) / this.metodos_correccion.length();
	}
}

public class Parcial 
{
	int nota_necesaria_para_aprobar;
	
	MetodoCorreccion metodo_correccion;
	
	private list<Pregunta> preguntas = new ArrayList<>();
	
	private list<Resolucion> resoluciones = new ArrayList<>();
	
	Parcial(int nota_necesaria_para_aprobar, MetodoCorreccion metodo_correccion, list<Pregunta> preguntas)
	{
		this.nota_necesaria_para_aprobar = nota_necesaria_para_aprobar;
		this.metodo_correccion = metodo_correccion;
		this.preguntas = preguntas;
	}
	
	int puntaje_total()
	{
		return preguntas.stream().sum(pregunta -> pregunta.peso_especifico);
	}
	
	void corregir_resoluciones()
	{
		this.resoluciones.stream().forEach(
				resolucion -> resolucion.corregir(this.metodo_correccion, this.nota_necesaria_para_aprobar)
				);
	}

}

public class Pregunta 
{
	String enunciado; // el tipo de la pregunta podria estar incluido en elñ enunciado
	
	int peso_especifico;
	
	//TipoPregunta tipo_pregunta;
	
}

public class Resolucion 
{
	List<String> respuestas = new ArrayList<>();
	
	Alumno alumno;
	
	int nota_final;
	
	Bool aprobo;
	
	Resolucion(List<String> respuestas, Alumno alumno)
	{
		this.respuestas = respuestas;
		this.alumno = alumno;
	}
	
	void corregir(MetodoCorreccion metodo_correccion, int nota_necesaria_para_aprobar)
	{
		nota_final = metodo_correccion.corregir(this);
		
		if(nota_final >= nota_necesaria_para_aprobar) {
			this.aprobo = true;
		}else {
			this.aprobo = false;
		}
	}
}
