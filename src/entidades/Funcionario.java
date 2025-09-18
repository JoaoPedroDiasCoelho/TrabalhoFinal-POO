package entidades;

import java.time.LocalDate;
import java.util.List;

public class Funcionario extends Pessoa {
	private double salarioBruto;
	private double descontoInss;
	private double decontoIr;
	private List<Dependente> dependentes;

	public Funcionario(String nome, String cpf, LocalDate dataNascimento, double salarioBruto, double descontoInss,
			double decontoIr) {
		super(nome, cpf, dataNascimento);
		this.salarioBruto = salarioBruto;
		this.descontoInss = descontoInss;
		this.decontoIr = decontoIr;
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
		return decontoIr;
	}

	public void setDecontoIr(double decontoIr) {
		this.decontoIr = decontoIr;
	}

	public List<Dependente> getDependentes() {
		return dependentes;
	}

	public double CalcularInss() {
		double SalarioBruto = getSalarioBruto();
		if(SalarioBruto <= 1518) {
			return SalarioBruto * 0.075;
			
		}
		else if(SalarioBruto > 1518 && SalarioBruto <= 2794) {
			return (SalarioBruto * 0.09) - 22.77;
		}
		else if (SalarioBruto > 2794 && SalarioBruto <= 4190) {
			return (SalarioBruto * 0.12) - 107;
		}
		else if (SalarioBruto > 4190 && SalarioBruto <= 8160) {
			return (SalarioBruto * 0.14) - 191;
		}
		else {
			return (8161 * 0.14);
		}
	}
	
	public double CalcularIr() {
		double calculoBase = getSalarioBruto() - CalcularInss();
		double AbatimentoDependente = getDependentes().size() * 189.59;
		if(calculoBase <= 2259) {
			return calculoBase - AbatimentoDependente;	
		}
		else if(calculoBase > 2260 && calculoBase <= 2827) {
			return ((calculoBase - AbatimentoDependente) * 0.075) - 169;
			}
		else if (calculoBase > 2827 && calculoBase <= 3752 ) {
			return ((calculoBase - AbatimentoDependente) * 0.225) - 382;
		}
		else if (calculoBase > 3752 && calculoBase <= 4665) {
			return ((calculoBase - AbatimentoDependente) * 0.275) - 663;
		}
		else {
			return (4665 * 0.275);
		}
	}
	 public double calcularSalarioLiquido() {
	        return this.salarioBruto - CalcularIr() - CalcularInss();
	    }
}
