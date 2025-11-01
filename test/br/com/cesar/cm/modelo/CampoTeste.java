package br.com.cesar.cm.modelo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CampoTeste {
	
	private Campo campo;
	
	@BeforeEach
	void iniciarCampo() {
		campo = new Campo(3, 3);
		
	}
	
	@Test
	void testeVizinhoDistancia1() {
		CampoTeste vizinhoEsquerdaCampoTeste = new Campo(3, 2);
		boolean resultadoEsquerda = campo.adicionarVizinho(vizinho);
		assert
	}

}
