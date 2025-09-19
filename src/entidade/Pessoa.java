package entidade;

import java.time.LocalDate;

public abstract class Pessoa {
	protected String nome;
	protected String cpf;
	protected LocalDate dataNascimento;

	public Pessoa(String nome, String cpf, LocalDate dataNascimento) {
		if (nome == null || cpf == null || dataNascimento == null) {
			throw new IllegalArgumentException("Campos obrigatórios.");
		}
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
	if (cpf.length() != 11) {
        throw new IllegalArgumentException("O CPF deve ter exatamente 11 dígitos: " + cpf);
	}
	return cpf;
	}
	
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public int getIdade() {
		int anoAtual = LocalDate.now().getYear();
		int idade = anoAtual - this.dataNascimento.getYear();

		if (LocalDate.now().getDayOfYear() < this.dataNascimento.getDayOfYear()) {
			idade--;
		}
		return idade;
	}
}