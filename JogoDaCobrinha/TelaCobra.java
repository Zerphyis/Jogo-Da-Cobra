package JogoDaCobrinha;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.lang.invoke.WrongMethodTypeException;
import java.util.Random;

public class TelaCobra extends JPanel implements ActionListener {

    private static final int LARGURA_DA_TELA=1360;
    private static final int AlTURA_DA_TELA=750;
    private  static final int TAMANHO_BLOCO=50;
    private  static final int UNIDADES=LARGURA_DA_TELA * AlTURA_DA_TELA/(TAMANHO_BLOCO * TAMANHO_BLOCO);
    private  static final int INTERVALO=400;
    private  static final String NOME ="Ink Free";

    private final int[] eixoX=new int[UNIDADES];
    private final int[] eixoY=new int[UNIDADES];

    private int corpoDaCobra=6;
    private int eatBlocks;
    private int Blockx;
    private int Blocky;
    private char direction='D';//D-Direita E-Esquerda C-Cima  B-Baixo
    private  boolean rodando=false;
 Timer tempo;
 Random random;

 TelaCobra(){
     random=new Random();
     setPreferredSize(new Dimension(LARGURA_DA_TELA,AlTURA_DA_TELA));
     setBackground(Color.darkGray);
     setFocusable(true);
     addKeyListener(new LeitorDeTeclas());
     iniciarJogo();
 }

 public  void iniciarJogo(){
    CriarBlock();
    rodando=true;
    tempo=new Timer(INTERVALO,this);
    tempo.start();
 }
@Override
 public void paintComponent(Graphics g){
     super.paintComponent(g);
     desenharTela(g);

 }

 public void desenharTela(Graphics g){
     if(rodando){
         g.setColor(Color.red);
         g.fillOval(Blockx,Blocky,TAMANHO_BLOCO,TAMANHO_BLOCO);
         for (int i = 0; i <corpoDaCobra ; i++) {
             if(i == 0){
                 g.setColor(Color.green);
                 g.fillRect(eixoX[0],eixoY[0],TAMANHO_BLOCO,TAMANHO_BLOCO);
             }else {
                 g.setColor(new Color(45,180,0));
                 g.fillRect(eixoX[i],eixoY[i],TAMANHO_BLOCO,TAMANHO_BLOCO);
             }
             
         }
         g.setColor(Color.red);
         g.setFont(new Font(NOME,Font.BOLD,40));
         FontMetrics metrics = getFontMetrics(g.getFont());
         g.drawString("Pontos: " + eatBlocks, (LARGURA_DA_TELA - metrics.stringWidth("Pontos: " + eatBlocks)) / 2,g.getFont().getSize());
     }
     else {
               fimDoJogo(g);
     }
 }
     private void CriarBlock() {
         Blockx = random.nextInt(LARGURA_DA_TELA/ TAMANHO_BLOCO) * TAMANHO_BLOCO;
         Blocky = random.nextInt(AlTURA_DA_TELA / TAMANHO_BLOCO) * TAMANHO_BLOCO;
     }
    public void fimDoJogo(Graphics g){
         g.setColor(Color.red);
         g.setFont(new Font(NOME,Font.BOLD,40));
         FontMetrics fontePontuacao=getFontMetrics(g.getFont());
         g.drawString("Fim do Jogo", (LARGURA_DA_TELA - fontePontuacao.stringWidth("Game Over")) / 2,AlTURA_DA_TELA/2);
     }

    @Override
    public void actionPerformed(ActionEvent e) {
   if(rodando){
       andar();
       alcancarBlock();
       valiarLimite();
   }
      repaint();

    }
    public  void  andar(){
        for (int i =corpoDaCobra;i > 0 ; i--) {
            eixoX[i]=eixoX[i - 1];
            eixoY[i]=eixoY[i - 1];

        }
        switch (direction){
            case 'C':
                eixoY[0]=eixoY[0] - TAMANHO_BLOCO;
                break;
            case 'B':
                eixoY[0]=eixoY[0] - TAMANHO_BLOCO;
                break;
            case 'E':
                eixoX[0]=eixoX[0] - TAMANHO_BLOCO;
                break;
            case 'D':
                eixoX[0]=eixoX[0] - TAMANHO_BLOCO;
                break;
            default:
                break;
        }
 }


private  void valiarLimite(){
    for (int i = corpoDaCobra; i > 0 ; i--) {
        if (eixoX[0] == eixoX[i] && eixoY[0] == eixoY[i]) {
            rodando=false;
            break;
        }
    }
    //A cabeça tocou uma das bordas Direita ou esquerda?
    if (eixoX[0] < 0 || eixoX[0] > LARGURA_DA_TELA) {
        rodando= false;
    }

    //A cabeça tocou o piso ou o teto?
    if (eixoY[0] < 0 || eixoY[0] > AlTURA_DA_TELA) {
        rodando= false;
    }

    if (!rodando) {
        tempo.stop();
    }
}
private  void alcancarBlock() {
    if (eixoX[0] == Blockx && eixoY[0] == Blocky) {
        corpoDaCobra++;
        eatBlocks++;
        CriarBlock();

    }
}
public  class  LeitorDeTeclas extends KeyAdapter {
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_LEFT:
                if (direction!= 'D') {
                    direction = 'E';
                }
                break;
            case  KeyEvent.VK_RIGHT:
                if(direction!='E'){
                    direction='D';
                }
                break;
            case  KeyEvent.VK_UP:
                if(direction!='B'){
                    direction='C';
                }
                break;

            case  KeyEvent.VK_DOWN:
                if(direction!='C'){
                    direction='B';
                }
                break;
            default:
                break;
        }
    }
}

    }


























