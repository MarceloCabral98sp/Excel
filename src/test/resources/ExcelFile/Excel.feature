Feature: Escrever no arquivo Excel

  Scenario: Escrever uma lista de nomes 
    Given que eu abro um arquivo excel
    When eu escrever os nomes "Ana", "Paula", "Maria"
    Then Os nomes estarão salvos no arquivo