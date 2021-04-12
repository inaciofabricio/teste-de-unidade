package br.com.caelum.leilao.teste;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.servico.Avaliador;

public class TesteDoAvaliador {

	@Test
	public void deveEntenderLancesEmOrdemCrescente() {
		
		// parte 1: cenario
		Usuario joao = new Usuario("João");
		Usuario maria = new Usuario("Maria");
		Usuario jose = new Usuario("José");
		
		Leilao leilao = new Leilao("Carro usado");
		
		leilao.propoe(new Lance(joao, 250));
		leilao.propoe(new Lance(maria, 350));
		leilao.propoe(new Lance(jose, 400));
		
		// parte 2: ação
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
		
		// parte 3: validação
		assertEquals(400, leiloeiro.getMaiorLance(), 0.00001);
		assertEquals(250, leiloeiro.getMenorLance(), 0.00001);
		
		
	}
	
	@Test
	public void deveEntenderLeilaoComApenasUmLance() {
		// parte 1: cenario
		Usuario joao = new Usuario("João");
		Leilao leilao = new Leilao("Outro Carro usado");
		
		leilao.propoe(new Lance(joao, 1000.0));
		
		// parte 2: ação
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
		
		// parte 3: validação
		assertEquals(1000.0, leiloeiro.getMaiorLance(), 0.00001);
		assertEquals(1000.0, leiloeiro.getMenorLance(), 0.00001);
	}
	
	@Test
	public void deveEncontrarOsTresMaioresLances() {
		// parte 1: cenario
		Usuario joao = new Usuario("João");
		Usuario maria = new Usuario("Maria");
		
		Leilao leilao = new Leilao("Outro Carro usado");
		
		leilao.propoe(new Lance(joao, 100));
		leilao.propoe(new Lance(maria, 200));
		leilao.propoe(new Lance(joao, 300));
		leilao.propoe(new Lance(maria, 400));
		
		// parte 2: ação
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
		
		List<Lance> maiores = leiloeiro.getTresMaiores();
		
		// parte 3: validação
		assertEquals(3, maiores.size());
		assertEquals(400, maiores.get(0).getValor(), 0.0001);
		assertEquals(300, maiores.get(1).getValor(), 0.0001);
		assertEquals(200, maiores.get(2).getValor(), 0.0001);
	}

}
