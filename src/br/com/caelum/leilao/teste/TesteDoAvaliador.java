package br.com.caelum.leilao.teste;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.servico.Avaliador;

public class TesteDoAvaliador {

	@Test
	public void deveEntenderLancesEmOrdemCrescente() {
		
		// parte 1: cenario
		Usuario joao = new Usuario("Jo�o");
		Usuario maria = new Usuario("Maria");
		Usuario jose = new Usuario("Jos�");
		
		Leilao leilao = new Leilao("Carro usado");
		
		leilao.propoe(new Lance(joao, 250));
		leilao.propoe(new Lance(maria, 350));
		leilao.propoe(new Lance(jose, 400));
		
		// parte 2: a��o
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
		
		// parte 3: valida��o
		double maiorEsperado = 400;
		double menorEsperado = 250;
		
		Assert.assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.00001);
		
		Assert.assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.00001);
		
		
	}

}