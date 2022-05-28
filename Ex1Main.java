/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercava.Ex1;
    import java.util.*;
/**
 *
 * @author Madalena Makiesse
 */
public class Ex1Main {
    //TENTAR UMA NOVA FORMA( SÓ FIXA  O CÓDIGO DA ÚLTIMA OCORRÊNCIA E UM CÓDIGO)
    //Matriz galpão, percorrer até encontrar a última ocorrência, enquanto isso somar a quantidade de elementos com mesmo código;
    //Criar class menu para o galpão
    static Scanner ler = new Scanner(System.in);
    static Informacao[][] galpao = new Informacao[10][10];
    static float[] quant=new float[10];
    static int elemL=0;
    static int[] elemC= new int[10];
    static int veriExistencis(Informacao[][] gp,int cod){
        int veri=-1;
        for(int i=0;i<gp.length;i++){
                if(gp[i][0]!=null && gp[i][0].codigoDoMaterial==cod) veri=i;
        }
        return veri;
    }
    static int vagaC(Informacao[][] gp, int linha){
        int i=0;
        while(i<10 && gp[linha][i]==null){
            i++;
        }
        return i;
    }
    static void preencher(Informacao[][] gp, float[] qg){
        int i,auxL;
        Informacao g = new Informacao();
        g=reg(g);
        for(i=0;i<gp.length;i++){
            auxL=veriExistencis(gp,g.codigoDoMaterial);
            if(auxL==-1){
                gp[elemL][0]=g;
                qg[elemL]=qg[elemL]+gp[elemL][0].quantidade;
                elemC[elemL]=1;
                elemL++;
            }
            else{
                if( elemC[auxL]==10){
                    System.out.println("Sem espaço para mais elementos.");
                }
                else{
                    gp[auxL][elemC[auxL]]=g;
                    qg[auxL]=qg[auxL]+g.quantidade;
                }
            }
        }
    }
    static Informacao reg(Informacao gp){
        gp.descricao = new String(); 
        System.out.print("Código do material: ");
        gp.codigoDoMaterial=ler.nextInt();
        ler.nextLine();
        do{
           System.out.print("Quantidade: ");
            gp.quantidade=ler.nextFloat();
        }while(gp.quantidade<0);
        System.out.print("Descrição: ");
        String s=ler.nextLine();ler.nextLine();
        if(s.length()+1<21){
            gp.descricao=s.substring(0, s.length());
        }
        else gp.descricao=s.substring(0, 21);
        
        return gp;
        
    }
    static void impressao(Informacao[][] gp,float[] q){
        for(int i=0; i<gp.length && gp[i][0]!=null;i++){
            System.out.println(gp[i][0].codigoDoMaterial+" : "+q[i]/10+" disponívei(s)");
        }
    }
    static void menu(){
        int op=1;
        while(op==1 || op==2 ){
            System.out.println("\n  1-Preencher a lista de materiais\n  2-Apresentar a lista de materias e suas respectivas quantidades\n Prima qualquer número diferente de '1' e '2' para sair");
            op= ler.nextInt();
            if(op==1){
                if(elemL<10){
                    preencher(galpao,quant); 
                }
                else System.out.println("Não existe mais espaço na lista.");
            }
            else if(elemL==0){ 
                menu();
                }
           else{
               if(op==2){
                  impressao(galpao,quant);
                }
           }
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        menu();
    }
    
}
