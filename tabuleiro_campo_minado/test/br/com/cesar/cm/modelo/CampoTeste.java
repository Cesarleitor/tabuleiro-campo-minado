package br.com.cesar.cm.modelo;

import static org.junit.jupiter.api.Assertions.*;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.cesar.cm.excecao.ExplosaoException;

public class CampoTeste {

	private Campo campo;

	@BeforeEach
	void iniciarCampo() {
		campo = new Campo(3, 3);
	}

	@Test
	void testeVizinhoDistancia1Esquerda() {
		Campo vizinho = new Campo(3, 2);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);

	}

	@Test
	void testeVizinhoDistancia1Direita() {
		Campo vizinho = new Campo(3, 4);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);

	}

	@Test
	void testeVizinhoDistancia1EmCima() {
		Campo vizinho = new Campo(2, 3);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);

	}

	@Test
	void testeVizinhoDistancia1EmBaixo() {
		Campo vizinho = new Campo(4, 3);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);

	}

	@Test
	void testeVizinhoDistancia2() {
		Campo vizinho = new Campo(2, 2);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);

	}

	@Test
	void testeNaoVizinho() {
		Campo vizinho = new Campo(1, 1);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertFalse(resultado);

	}

	@Test
	void testeVaalorPadraoAtributoMarcado() {
		assertFalse(campo.isMarcado());
	}

	@Test
	void testeAlternarMarcacao() {
		campo.alternarMarcacao();
		assertTrue(campo.isMarcado());
	}

	@Test
	void testeAlternarMarcacaoDuasChamadas() {
		campo.alternarMarcacao();
		campo.alternarMarcacao();
		assertFalse(campo.isMarcado());
	}

	@Test
	void testeAbrirNaoMinadoNaoMarcado() {
		assertTrue(campo.abrir());
	}

	@Test
	void testeAbrirNaoMinadoMarcado() {
		campo.alternarMarcacao();
		assertFalse(campo.abrir());
	}

	@Test
	void testeAbrirMinadoMarcado() {
		campo.alternarMarcacao();
		campo.minar();
		assertFalse(campo.abrir());
	}

	@Test
	void testeAbrirMinadoNaoMarcado() {
		campo.minar();
		assertThrows(ExplosaoException.class, () -> {
			campo.abrir();
		});

	}

	@Test
	void testeAbrirComVizinhos1() {
		Campo campo11 = new Campo(1, 1);

		Campo campo22 = new Campo(2, 2);
		campo22.adicionarVizinho(campo11);

		campo.adicionarVizinho(campo22);
		campo.abrir();
		assertTrue(campo22.isAberto() && campo11.isAberto());
	}

	@Test
	void testeAbrirComVizinhos2() {
		Campo campo11 = new Campo(1, 1);
		Campo campo12 = new Campo(1, 1);
		campo12.minar();

		Campo campo22 = new Campo(2, 2);
		campo22.adicionarVizinho(campo11);
		campo22.adicionarVizinho(campo12);

		campo.adicionarVizinho(campo22);
		campo.abrir();
		assertTrue(campo22.isAberto() && campo11.isFechado());
	}
	

	@Test
	void testeReiniciar_deveResetarCampos() {
		campo.aberto = true;
		campo.minado = true;
		campo.marcado = true;

		campo.reiniciar();

		assertFalse(campo.aberto);
		assertFalse(campo.minado);
		assertFalse(campo.marcado);

	}
	
	  @Test
	    void testVizinhancaSeguraSemMinas() {
	        Campo campo = new Campo(1, 1);
	        Campo vizinho1 = new Campo(1, 2);
	        Campo vizinho2 = new Campo(2, 1);

	        campo.adicionarVizinho(vizinho1);
	        campo.adicionarVizinho(vizinho2);

	        assertTrue(campo.vizinhancaSegura());
	    }

	    @Test
	    void testVizinhancaInseguraComMina() {
	        Campo campo = new Campo(1, 1);
	        Campo vizinho = new Campo(1, 2);
	        vizinho.minar();

	        campo.adicionarVizinho(vizinho);

	        assertFalse(campo.vizinhancaSegura());
	    }

	    @Test
	    void testMinasNaVizinhanca() {
	        Campo campo = new Campo(1, 1);
	        Campo v1 = new Campo(1, 2);
	        Campo v2 = new Campo(2, 2);
	        Campo v3 = new Campo(2, 1);

	        v1.minar();
	        v3.minar();

	        campo.adicionarVizinho(v1);
	        campo.adicionarVizinho(v2);
	        campo.adicionarVizinho(v3);

	        assertEquals(2, campo.minasNaVizinhanca());
	    }

	    @Test
	    void testObjetivoAlcancadoSemMinaAberto() {
	        Campo campo = new Campo(1, 1);
	        // simulando campo aberto e sem mina
	        try {
	            var abertoField = Campo.class.getDeclaredField("aberto");
	            abertoField.setAccessible(true);
	            abertoField.set(campo, true);
	        } catch (Exception e) {
	            fail(e);
	        }

	        assertTrue(campo.objetivoAlcancado());
	    }

	    @Test
	    void testObjetivoAlcancadoComMinaMarcado() {
	        Campo campo = new Campo(1, 1);
	        campo.minar();
	        try {
	            var marcadoField = Campo.class.getDeclaredField("marcado");
	            marcadoField.setAccessible(true);
	            marcadoField.set(campo, true);
	        } catch (Exception e) {
	            fail(e);
	        }

	        assertTrue(campo.objetivoAlcancado());
	    }

	    @Test
	    void testObjetivoNaoAlcancado() {
	        Campo campo = new Campo(1, 1);
	        campo.minar();
	        assertFalse(campo.objetivoAlcancado());
	    }
	
	@Test
	
	void toString_MostrandoSimbolosCorretos() {
		//campo marcado
		campo.marcado = true;
		assertEquals("x", campo.toString());
		
		//campo aberto com mina
		campo.marcado = false;
		campo.aberto = true;
		campo.minado = true;
		assertEquals("*", campo.toString());
		
		//campo aberto com minas vizinhas
		campo = new Campo(1, 1);
		Campo vizinho = new Campo(1, 2);
		vizinho.minado = true;
		campo.adicionarVizinho(vizinho);
		campo.aberto = true;
		assertEquals("1", campo.toString() );
		
		//campo aberto sem minas
		campo = new Campo(2, 2);
		campo.aberto = true;
		assertEquals(" ", campo.toString());
		
		//campo fechado
		campo = new Campo(0, 0);
		assertEquals("?", campo.toString());
		
		
		
	}

}

