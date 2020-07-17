package main.java;

import java.util.ArrayList;

import model.prenda.Categoria;

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

public enum MetodoCorreccion {
    CON_DESCUENTO(),

    REGLA_3_SIMPLE,

    TABLA_CONVERSION,

    NOTA_MAS_ALTA_ENTRE_VARIOS_CRITERIOS,

    PROMEDIO_ENTRE_VARIOS_CRITERIOS(Categoria.PARTE_SUPERIOR),
    
    private Categoria categoria;

	MetodoCorreccion(Categoria categoria) {
        this.categoria = categoria;
    }

    public Categoria getCategoria() {
        return categoria;
    }
    
    void corregir(Resolucion resolucion) {
    	
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
	
	void corregir_resoluciones()
	{
		this.resoluciones.stream().forEach(resolucion -> resolucion.corregir(this.metodo_correccion, this.nota_necesaria_para_aprobar));
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
			aprobo = true;
		}else {
			aprobo = false;
		}
	}
}
