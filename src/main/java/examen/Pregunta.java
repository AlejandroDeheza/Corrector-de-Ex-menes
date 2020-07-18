package main.java.examen;

public class Pregunta 
{
	private String enunciado;
	private int peso_especifico;
	private String respuesta_correcta;	
	
	public int get_peso_especifico() {
		return this.peso_especifico;
	}
	
	public Boolean laRespuestaEsCorrecta(String respuesta)
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
