package entidade;

import java.time.LocalDate;
import java.util.List;

public class Funcionario extends Pessoa {
	private double salarioBruto;
	private double descontoInss;
	private double descontoIr;
	private List<Dependente> dependentes;

	public Funcionario(String nome, String cpf, LocalDate dataNascimento, double salarioBruto, double descontoInss,
			double decontoIr) {
		super(nome, cpf, dataNascimento);
		this.salarioBruto = salarioBruto;
		this.descontoInss = descontoInss;
		this.descontoIr = decontoIr;
	}

	public double getSalarioBruto() {
		return salarioBruto;
	}

	public void setSalarioBruto(double salarioBruto) {
		this.salarioBruto = salarioBruto;
	}

	public double getDescontoInss() {
		return descontoInss;
	}

	public void setDescontoInss(double descontoInss) {
		this.descontoInss = descontoInss;
	}

	public double getDecontoIr() {
		return descontoIr;
	}

	public void setDecontoIr(double decontoIr) {
		this.descontoIr = decontoIr;
	}

	public List<Dependente> getDependentes() {
		return dependentes;
	}

	public double CalcularInss() {
		double SalarioBruto = getSalarioBruto();
		if (SalarioBruto <= 1518.00) {
			return SalarioBruto * 0.075;

		} else if (SalarioBruto > 1518.01 && SalarioBruto <= 2793.88) {
			return (SalarioBruto * 0.09) - 22.77;
		} else if (SalarioBruto > 2793.88 && SalarioBruto <= 4190.83) {
			return (SalarioBruto * 0.12) - 106.59;
		} else if (SalarioBruto > 4190.84 && SalarioBruto <= 8167.41) {
			return (SalarioBruto * 0.14) - 190.40;
		} else {
			return (8157.41 * 0.14);

		}
	}

	public double CalcularIr() {
		double CalculoBase = getSalarioBruto() - CalcularInss();
		double AbatimentoDependente = getDependentes().size() * 189.59;

		if (CalculoBase <= 2259.00) {
			return CalculoBase - AbatimentoDependente;
		} else if (CalculoBase > 2259.21 && CalculoBase <= 2826.65) {
			return ((CalculoBase - AbatimentoDependente) * 0.075) - 169.44;
		} else if (CalculoBase > 2826.66 && CalculoBase <= 3751.05) {
			return ((CalculoBase - AbatimentoDependente) * 0.225) - 381.44;
		} else if (CalculoBase > 3751.06 && CalculoBase <= 4664.68) {
			return ((CalculoBase - AbatimentoDependente) * 0.275) - 662.77;
		} else {
			return ((CalculoBase - AbatimentoDependente) * 0.275) - 896.00;
		}
	}

	public double calcularSalarioLiquido() {
		return this.salarioBruto - CalcularIr() - CalcularInss();
	}
}
