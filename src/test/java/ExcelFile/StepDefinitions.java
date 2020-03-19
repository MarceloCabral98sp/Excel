package ExcelFile;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitions extends Excel{
	
	public StepDefinitions(String string, String string2, String string3) {
		super(string, string2, string3);
	}

	Excel excel = new Excel();
	
	@Given("que eu abro um arquivo excel")
	public void que_eu_abro_um_arquivo_excel() {
	    System.out.println("Teste1");
		excel.lerExcel();
	}

	@When("eu escrever os nomes {string}, {string}, {string}")
	public void eu_escrever_os_nomes(String string, String string2, String string3) {
	    Excel excel = new Excel(string, string2, string3);
	    excel.escreverExcel();
	}
	
	@Then("Os nomes estarão salvos no arquivo")
	public void os_nomes_estarão_salvos_no_arquivo() {
	    excel.lerExcel();
	    System.out.print("Teste3");
	}
}