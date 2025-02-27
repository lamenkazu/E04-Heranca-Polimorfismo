public class Conta {
    private Cliente dono = new Cliente();
    private int numConta;
    private double saldo;
    private double limite;

    private String tipo;

    public static int totalContas;

    public Operacao[] operacoes = new Operacao[1000];



    //Metodos
    public Conta() {
        this.numConta = -1;
        this.saldo = 0;
        this.limite = 0;



        totalContas++;
    }

    public void setNumConta(int val){
        this.numConta = val;
    }

    public int getNumConta() {
        return this.numConta;
    }

    public double getSaldo() {
        return this.saldo;
    }

    public Cliente getDono() {
        return this.dono;
    }

    public void setDono(Cliente dono) {
        this.dono = dono;
    }
    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    public boolean sacar(double valor) {
        if (!(valor > this.saldo) && !(valor < 0.0)) {
            this.operacoes[Operacao.totalOperacoes] = new OperacaoSaque(valor);

            this.saldo -= valor;
            return true;
        } else {
            return false;
        }
    }

    public boolean depositar(double valor) {
        if (valor < 0.0) {
            System.out.println("Impossível depositar esse valor");
            return false;
        } else {
            this.operacoes[Operacao.totalOperacoes] = new OperacaoDeposito(valor);
            this.saldo += valor;
            System.out.println("Depósito realizado com sucesso!");
            return true;
        }
    }

    public boolean transferir(double valor, Conta destino) {
        if (this.sacar(valor) && destino.depositar(valor)) {
            this.operacoes[Operacao.totalOperacoes] = new Operacao(valor);

            System.out.println("Transferência realizada com sucesso!\n");
            return true;
        } else {
            System.out.println("Transferência mal sucedida.");
            return false;
        }
    }

    public void imprimeExtrato(){
        System.out.println("===Extrato conta " + getNumConta() + "===" + "\nDono: " + getDono().getNome());
        for(int i = 0; i < Operacao.totalOperacoes; i++){
            if(this.operacoes[i] != null){
                System.out.println(this.operacoes[i].getData() + " " + this.operacoes[i].getTipo() + "      " + this.operacoes[i].getValor() );
            }
        }
    }

    public void imprimeDados() {
        System.out.println("=== Conta: " + this.numConta + " ===");
        System.out.println("Dono: " + this.dono.getNome());
        System.out.println("Limite: " + this.limite);
        this.imprimeSaldo();
    }

    private void imprimeSaldo() {
        System.out.println("Saldo: " + this.saldo);
    }




}
