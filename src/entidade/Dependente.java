package entidade;

import java.time.LocalDate;
import enuns.Parentesco;
import exception.DependenteException;

public class Dependente extends Pessoa {
	private Parentesco parentesco;

	public Dependente(String nome, String cpf, LocalDate dataNascimento, Parentesco parentesco)
		throws DependenteException {
		super(nome, cpf, dataNascimento);
		this.parentesco = parentesco;
		validarIdade();
	}

	public void validarIdade() throws DependenteException {
		if (getIdade() >= 18) {
			throw new DependenteException("O dependente deve ser menor que 18 anos: " + getNome());
		}
	}

	public Parentesco getParentesco() {
		return parentesco;
	}
}
