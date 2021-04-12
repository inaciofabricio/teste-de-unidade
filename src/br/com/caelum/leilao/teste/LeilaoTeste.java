package br.com.caelum.leilao.teste;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;

public class LeilaoTeste {
	
	@Test
	public void deveReceberUmLance() {
		Leilao leilao = new Leilao("Macbook Pro 15");
		assertEquals(0, leilao.getLances().size());
		
		leilao.propoe(new Lance(new Usuario("Jose"), 2000));
		
		assertEquals(1, leilao.getLances().size());
		assertEquals(2000, leilao.getLances().get(0).getValor(), 0.00001);
	}
	
	@Test
	public void deveReceberVariosLance() {
		Leilao leilao = new Leilao("Macbook Pro 15");
		
		leilao.propoe(new Lance(new Usuario("Jose"), 2000));
		leilao.propoe(new Lance(new Usuario("Chico"), 3000));
						
		assertEquals(2, leilao.getLances().size());
		assertEquals(2000, leilao.getLances().get(0).getValor(), 0.00001);
		assertEquals(3000, leilao.getLances().get(1).getValor(), 0.00001);
	}

	@Test
	public void deveDeveAceitarDoisLancesSeguidosDoMesmoUsuario() {
		Leilao leilao = new Leilao("Macbook Pro 15");
		Usuario jose = new Usuario("Jose");
		
		leilao.propoe(new Lance(jose, 2000));
		leilao.propoe(new Lance(jose, 3000));
						
		assertEquals(1, leilao.getLances().size());
		assertEquals(2000, leilao.getLances().get(0).getValor(), 0.00001);
	}
	
	@Test
	public void deveDeveAceitarMaisDoQueCincoLancesDeUmMesmoUsuario() {
		Leilao leilao = new Leilao("Macbook Pro 15");
		Usuario jose = new Usuario("Jose");
		Usuario chico = new Usuario("Chico");
		
		leilao.propoe(new Lance(jose, 1000));
		leilao.propoe(new Lance(chico, 2000));
		
		leilao.propoe(new Lance(jose, 3000));
		leilao.propoe(new Lance(chico, 4000));
		
		leilao.propoe(new Lance(jose, 5000));
		leilao.propoe(new Lance(chico, 6000));
		
		leilao.propoe(new Lance(jose, 7000));
		leilao.propoe(new Lance(chico, 8000));
		
		leilao.propoe(new Lance(jose, 9000));
		leilao.propoe(new Lance(chico, 10000));
		
		leilao.propoe(new Lance(chico, 11000));
						
		assertEquals(10, leilao.getLances().size());
		assertEquals(10000, leilao.getLances().get(leilao.getLances().size()-1).getValor(), 0.00001);
	}
}
