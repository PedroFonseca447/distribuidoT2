
/**
 *  Processo agência
 * solicita abertura de conta,autenticação de conta
 * fechamento de conta, deposito,saque e consulta de saldo
 * idepo= apaga conta e solicita saldo
 * n-idepo= saque,deposito,cria conta
 */

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.RemoteObject;
import java.util.Scanner;

public class ProcessoAgencia {
    public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
        int contadorOperacoes = 0;

        // Procura pelo servico da calculadora no IP e porta definidos
        Banco c = (Banco) Naming.lookup("rmi://localhost:1099/CalcService");
      
        Scanner in = new Scanner(System.in);
        System.out.println("digite 1 para iniciar");
        int escolha = Integer.parseInt(in.nextLine());
  
        while (escolha == 1) {
            System.out.println("1 - cria conta");
            System.out.println("2 - consulta saldo");
            System.out.println("3 - saque");
            System.out.println("4 - deposito");
            System.out.println("5 - fechamento de conta");
            System.out.println("6 - confirma conta");
            System.out.println("0 - sair");
            int key = Integer.parseInt(in.nextLine());
            switch (key) {
                case 1:
                    contadorOperacoes++;
                    //implementa o tratamento da idempotencia em operações de cadastro
                    try {
                        if (c.getOperacoesCadastro() == c.getOperacoesCadastro() + 1) {
                            System.out.println("operação perdida");
                            continue;
                        }
                    } catch (RemoteException remoException) {
                        System.out.println("erro de conexão");
                    }

                    System.out.println("Digite o numero da nova conta");
                    int idC = Integer.parseInt(in.nextLine());
                    System.out.println("Digite o saldo inicial da nova conta");
                    double saldoC = Double.parseDouble(in.nextLine());
                    System.out.println("Digite o nome vinculado a conta");
                    String nome = in.nextLine();
                    try {
                        if (c.conta(idC) == true) {
                            System.out.println("CONTA COM ID JA EXISTENTE TENTE NOVAMENTE COM OUTRO ID");
                            continue;
                        }
                    } catch (RemoteException remoException) {
                        System.out.println("erro de conexao");
                    }

                    try {
                        c.cadastraConta(idC, saldoC, nome);
                    } catch (RemoteException remoException) {
                        System.out.println("erro de conexao");

                    }

                    try {
                        System.out.println("Operacao é de número: \n" + c.getOperacoesCadastro());
                    } catch (RemoteException remoException) {
                        System.out.println("erro de conexao");
                    }

                    break;
                case 2:
                    // if(contadorOperacoes!=contadorOperacoes){
                    // throw new ILLEGAL_ARGUMENT_EXCEPTION("operação ja realizada");
                    // }
                    System.out.println("Digite seu id para identificarmos sua conta");
                    int sc = Integer.parseInt(in.nextLine());
                    try {
                        if (c.conta(sc) == false) {
                            System.out.println("CONTA COM ID Invalido");
                            continue;
                        }
                    } catch (RemoteException remoException) {
                        System.out.println("erro de conexao");
                    }
                   
                    try {
                        System.out.printf("R$:\n", c.getSaldoS(sc));
                    } catch (RemoteException remoException) {
                        System.out.println("erro de conexao");
                    }

                    break;
                case 3:
                    try {
                        if (c.getOperacaoSaque() == c.getOperacaoSaque() + 1) {
                            System.out.println("operação perdida");
                            continue;
                        }
                    } catch (RemoteException remoException) {
                        System.out.println("erro de conexão");
                    }
                    System.out.println("Digite sua id");
                    int idS = Integer.parseInt(in.nextLine());
                    try {
                        if (c.conta(idS) == false) {
                            System.out.println("CONTA INEXISTENTE");
                            continue;
                        }
                    } catch (RemoteException remoException) {
                        System.out.println("erro de conexao");
                    }
                    System.out.println("Digite o valor a ser sacado");
                    double valorSaque = Double.parseDouble(in.nextLine());
                    try {
                        if (valorSaque < 0) {
                            System.out.println("VALOR SACADO INVALIDO");
                            continue;
                        }
                    } catch (Exception remoException) {
                        System.out.println("erro de conexao");
                    }
                    try {
                        if (valorSaque > c.getSaldo(idS)) {
                            System.out.println("FUNDOS INSUFICIENTES ");
                        }
                    } catch (RemoteException remoException) {
                        System.out.println("erro de conexao");
                    }
                    try {
                        c.saque(idS, valorSaque);
                    } catch (RemoteException remoException) {
                        System.out.println("erro de conexao");
                    }
                    try {
                        System.out.println("Saldo em conta pos saque R$: " + c.getSaldoS(idS));
                    } catch (RemoteException remoException) {
                        System.out.println("erro de conexao");
                    }
                    try {
                        System.out.println("Numero da operacao: " + c.getOperacaoSaque());
                    } catch (RemoteException remoException) {
                        System.out.println("erro de conexao");
                    }

                    break;
                case 4:
                    // trata a idempotencia
                    try {
                        if (c.getContadorOperacaoDeposito() == c.getContadorOperacaoDeposito() + 1) {
                            System.out.println("operação perdida");
                            continue;
                        }
                    } catch (RemoteException remoException) {
                        System.out.println("erro de conexão");
                    }
                    System.out.println("Digite sua id");
                    int id = Integer.parseInt(in.nextLine());
                    try {
                        if (c.conta(id) == false) {
                            System.out.println("CONTA INEXISTENTE");
                            continue;
                        }
                    } catch (RemoteException remoException) {
                        System.out.println("erro de conexao");

                    }
                    System.out.println("Digite o valor a ser depositado");
                    double valorDeposito = Double.parseDouble(in.nextLine());
                    if (valorDeposito < 0) {
                        System.out.println("VALOR INSERIDO INVALIDO");
                        continue;
                    }
                    try {
                        c.deposito(id, valorDeposito);// numero de exceção de rodar 2 vezes o mesmo codigo e
                        // verificar o erro
                    } catch (RemoteException remoException) {// cria um metodo
                        System.out.println("erro de conexao");
                    }

                    try {
                        System.out.println("Saldo em conta pos deposito R$: " + c.getSaldoS(id));
                    } catch (RemoteException remoException) {
                        System.out.println("erro de conexao");
                    }

                    break;

                case 5:
                    System.out.println("digite o id da conta que deseja apagar");
                    int r = Integer.parseInt(in.nextLine());
                    try {
                        if (c.conta(r) == false) {
                            System.out.println("CONTA INEXISTENTE");
                            continue;
                        }
                    } catch (RemoteException remoException) {
                        System.out.println("erro de conexao");
                    }
                    try {
                        c.apagaConta(r);
                    } catch (RemoteException remoException) {
                        System.out.println("erro de conexao");
                   
                    }
                

                    System.out.println(" conta foi apagada");
                 break;

                case 6:
                
                    System.out.printf("Digite sua id\n");
                    int idG = Integer.parseInt(in.nextLine());
                    try {
                        System.out.println(c.nome(idG) + ":" + c.getSaldoS(idG) + ":" + c.id(idG));
                    } catch (RemoteException remoException) {
                        System.out.println("erro de conexao");
                    }

                    break;
                case 0:
                    escolha = 0;
                    System.out.println("encerrando");
                default:
                    System.out.println("Digitou opcao invalida");
                    break;

            }

            System.out.println("Deseja novamente realizar a operacao? digite[1] para continuar");
            escolha = Integer.parseInt(in.nextLine());
            if (escolha != 1) {
                System.out.println("encerrando");
            }

        }

    }
}
