package br.com.caelum.leilao.teste;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Assert;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import org.junit.Before;
import org.junit.Test;

import br.com.caelum.leilao.builder.CriadorDeLeilao;
import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.servico.Avaliador;

public class TesteDoAvaliador {

	private Avaliador leiloeiro;
	private Usuario joao;
	private Usuario maria;
	private Usuario jose;

	@Before
	public void criaAvaliador() {
		this.leiloeiro = new Avaliador();
		this.joao = new Usuario("João");
		this.maria = new Usuario("Maria");
		this.jose = new Usuario("José");
	}
	
	@Test(expected = RuntimeException.class)
	public void NaoDeveAvaliarLeiloesSemNenhumLance() {
		Leilao leilao = new CriadorDeLeilao().para("Carreta").constroi();
		leiloeiro.avalia(leilao);
	}
	
	
	@Test
	public void deveEntenderLancesEmOrdemCrescente() {
	
		Leilao leilao = new CriadorDeLeilao().para("Fusca Velho")
				.lance(joao, 250)
				.lance(maria, 350)
				.lance(jose, 400)
				.constroi();

		leiloeiro.avalia(leilao);

		assertEquals(400, leiloeiro.getMaiorLance(), 0.00001);
		assertEquals(250, leiloeiro.getMenorLance(), 0.00001);
//		assertThat(leiloeiro.getMaiorLance(), equalTo(400));
//		assertThat(leiloeiro.getMenorLance(), equalTo(250));
		
		
	}
	
	@Test
	public void deveEntenderLeilaoComApenasUmLance() {

		Leilao leilao = new CriadorDeLeilao().para("Outro Carro usado")
				.lance(joao, 1000)
				.constroi();
		
     	leiloeiro.avalia(leilao);
		
     	assertEquals(1000.0, leiloeiro.getMaiorLance(), 0.00001);
		assertEquals(1000.0, leiloeiro.getMenorLance(), 0.00001);
	}
	
	@Test
	public void deveEncontrarOsTresMaioresLances() {
		
		Leilao leilao = new CriadorDeLeilao().para("Fusca Velho")
				.lance(joao, 100)
				.lance(maria, 200)
				.lance(joao, 300)
				.lance(maria, 400)
				.constroi();
		
		// parte 2: ação
		leiloeiro.avalia(leilao);
		
		List<Lance> maiores = leiloeiro.getTresMaiores();
		
		// parte 3: validação
		assertEquals(3, maiores.size());
		assertEquals(400, maiores.get(0).getValor(), 0.0001);
		assertEquals(300, maiores.get(1).getValor(), 0.0001);
		assertEquals(200, maiores.get(2).getValor(), 0.0001);
	}

}
