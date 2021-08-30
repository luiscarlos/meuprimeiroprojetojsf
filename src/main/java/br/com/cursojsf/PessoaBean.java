package br.com.cursojsf;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlCommandButton;
import javax.faces.event.NamedEvent;
import javax.faces.view.facelets.FaceletContext;

import com.sun.faces.taglib.html_basic.CommandButtonTag;

import br.com.dao.DaoGeneric;
import br.com.entidade.Pessoa;

//@RequestScoped apenas para cadastro, depois se perdem e apaga tudo na tela que foi cadastrado  //
//@ViewScoped deixa gravado as informação tipo uma lista, apaga apenas quando a URL e redireionada enquanto o navegado estiver aberto ele mantem as informaçoes//
//@SessionScoped // cada usuario que logo ou cada navegador q for aberto ex 4 usuarios trabalhando vamos ter 4 sessoes , ele pode abriri mais de uma tela em casa sessao 
//@ApplicationScoped// MANTEM OS DADOS EX: se um usuario colocar 5 nomes o segundo usuario vai ver o que o usuario anterior colocou, compartilha os dados com todos na aplicação 
@ViewScoped
@ManagedBean(name = "pessoaBean")
public class PessoaBean{

	private Pessoa pessoa = new Pessoa();
	private DaoGeneric<Pessoa> daoGeneric = new DaoGeneric<Pessoa>();
	private List<Pessoa> listaPessoa = new ArrayList<Pessoa>();
	
	
	public String salvar() {
		pessoa = daoGeneric.merge(pessoa);
		carregarPessoa();
		return "";
	}

	public String remove() {
		daoGeneric.deletePorId(pessoa);
		carregarPessoa();
		return "";
	}

	public String novo() {
		pessoa = new Pessoa();
		return "";
	}
	
	@PostConstruct
	public void carregarPessoa() {
		listaPessoa = daoGeneric.getListEntity(Pessoa.class);
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<Pessoa> getListaPessoa() {
		return listaPessoa;
	}

	public void setListaPessoa(List<Pessoa> listaPesso) {
		this.listaPessoa = listaPesso;
	}

	public String logar() {
		
		
		System.out.println(pessoa.getLogin() + " - " + pessoa.getSenha());
		return "index.jsf";
	}

}
