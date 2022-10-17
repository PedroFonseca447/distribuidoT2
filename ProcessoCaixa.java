
    // Processo caixa
    // garante operações de saque , deposito e consulta de saldo
    // sendo nao idempotentes saque e deposito
    import java.rmi.Naming;
    import java.util.Scanner;

public class ProcessoCaixa {
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        try {
            //Procura pelo servico da calculadora no IP e porta definidos
            Banco c = (Banco) Naming.lookup("rmi://localhost:1099/CalcService");
   
            System.out.println("1 - saque");
            System.out.println("2 - deposito");
            System.out.println("3 - consulta de saldo");
           
            boolean exec = true;
            double result;
            int result1;
      
            while (exec) {

                int key = in.nextInt();
                switch (key) {
                    case 1:
                    System.out.println("Digite sua id");
                    int idS=in.nextInt();
                    System.out.println("Digite o valor a ser sacado");
                    double valorSaque=in.nextDouble();
                    c.saque(idS, valorSaque);
                   
                    System.out.println("Saldo em conta pos saque: "+c.getSaldo(idS));
                        break;
                    case 2:
                    System.out.println("Digite sua id");
                    int id=in.nextInt();
                    System.out.println("Digite o valor a ser depositado");
                    double valorDeposito=in.nextDouble();
                    c.deposito(id, valorDeposito);
                    
                    System.out.println("Saldo em conta pos saque: "+c.getSaldo(id));
                        break;
                    case 3:
                    System.out.println("Digite seu id para identificarmos sua conta");
                    System.out.printf("Result: %.2f\n",c.getSaldo(in.nextInt()) );
                        break;
                   
                    case 0:
                        exec = false;
                    default:
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
