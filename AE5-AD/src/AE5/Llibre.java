package AE5;

public class Llibre {

	public Llibre( String titol, String autor, String any_naixement, String any_publicacio,
			String editorial, String num_pagines) {
		super();
		Titol = titol;
		Autor = autor;
		Any_naixement = any_naixement;
		Any_publicacio = any_publicacio;
		Editorial = editorial;
		Num_pagines = num_pagines;
	}

	private int Identificador; 
	private String Titol; 
	private String Autor;
	private String Any_naixement;
	private String Any_publicacio;
	private String Editorial;
    private String Num_pagines;
    
    public Llibre() {    }

	public int getIdentificador() {
		return Identificador;
	}

	public void setIdentificador(int identificador) {
		Identificador = identificador;
	}

	public String getTitol() {
		return Titol;
	}

	public void setTitol(String titol) {
		Titol = titol;
	}

	public String getAutor() {
		return Autor;
	}

	public void setAutor(String autor) {
		Autor = autor;
	}

	public String getAny_naixement() {
		return Any_naixement;
	}

	public void setAny_naixement(String any_naixement) {
		Any_naixement = any_naixement;
	}

	public String getAny_publicacio() {
		return Any_publicacio;
	}

	public void setAny_publicacio(String any_publicacio) {
		Any_publicacio = any_publicacio;
	}

	public String getEditorial() {
		return Editorial;
	}

	public void setEditorial(String editorial) {
		Editorial = editorial;
	}

	public String getNum_pagines() {
		return Num_pagines;
	}

	public void setNum_pagines(String num_pagines) {
		Num_pagines = num_pagines;
	}
    
}
