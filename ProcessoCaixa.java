
    // Processo caixa
    // garante operações de saque , deposito e consulta de saldo
    // sendo nao idempotentes saque e deposito
    import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class ProcessoCaixa {
    public static void main(String args[]) throws MalformedURLException, RemoteException, NotBoundException{
        Scanner in = new Scanner(System.in);
        System.out.println("digite 1 para iniciar");
         int escolha=Integer.parseInt(in.nextLine());
         while(escolha==1) {
            //Procura pelo servico da calculadora no IP e porta definidos
            Banco c = (Banco) Naming.lookup("rmi://localhost:1099/CalcService");
   
            System.out.println("1 - saque");
            System.out.println("2 - deposito");
            System.out.println("3 - consulta de saldo");
      

            int key = Integer.parseInt(in.nextLine());
                switch (key) {
                    case 1:
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
                        System.out.println("Saldo em conta pos saque: " + c.getSaldoS(idS));
                    } catch (RemoteException remoException) {
                        System.out.println("erro de conexao");
                    }
                    try {
                        System.out.println("Numero da operacao: " + c.getOperacaoSaque());
                    } catch (RemoteException remoException) {
                        System.out.println("erro de conexao");
                    }

                    break;
                    case 2:
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
                        System.out.println("Saldo em conta pos deposito: " + c.getSaldoS(id));
                    } catch (RemoteException remoException) {
                        System.out.println("erro de conexao");
                    }

                    break;
                    case 3:
                    System.out.printf("Digite sua id\n");
                    int idG=Integer.parseInt(in.nextLine());
                    System.out.println(c.nome(idG)+":"+ c.getSaldo(idG)+":"+c.id(idG));
                    break;
                    case 0:
                    escolha=0;
                    System.out.println("encerrando");
                    default:
                    System.out.println("Digitou opcao invalida");
                        break;
                }
                System.out.println("Deseja novamente realizar a operacao? digite[1] para continuar");
                escolha=Integer.parseInt(in.nextLine());
                if(escolha!=1){
                 System.out.println("encerrando");
                }
                
            }
        } 
    }
