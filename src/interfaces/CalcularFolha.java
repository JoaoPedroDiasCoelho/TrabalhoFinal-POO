package interfaces;

import entidades.Funcionario;

public interface CalcularFolha {
	public double calcularInss(Funcionario funcionario);
	public double calcularImpostoDeRenda(Funcionario funcionario);
	
 
}
