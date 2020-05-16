
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class mayın extends JPanel implements ActionListener,MouseListener{
private int count[][]=new int[20][20];//sayım
private JButton reset   =new JButton("reset");
private  JButton [][]buton=new JButton[20][20];
private  Container grid=new Container();////butona tıkladıgımızda metini tutar
 final int mine=10;
    public mayın() {
        setLayout(new BorderLayout());
        add(reset,BorderLayout.NORTH);//kuzeyde tam reset olsun
        rastgeleMayınYarat();
        reset.addActionListener(this);
        grid.setLayout(new GridLayout(20,20));
        for (int i = 0; i < buton.length; i++) {
            for (int j = 0; j < buton[0].length; j++) {
                buton[i][j]=new JButton();
                buton[i][j].addActionListener(this);
              grid.add(buton[i][j]);
            }
        }
       add(grid);
       
    
    
    }
    public void rastgeleMayınYarat(){
        //rastgele sayı
        ArrayList<Integer> list=new ArrayList<Integer>();
        for (int i = 0; i < count.length; i++) {
            for (int j = 0; j < count[0].length; j++) {
                list.add(i*100+j);
            }
        }
        //resetleme 
        count=new int[20][20];
        for (int i = 0; i < 30; i++) {
            int choice=(int)(Math.random()*list.size());
            count[list.get(choice)/100][list.get(choice) % 100]=mine;
            list.remove(choice);
        }
        
        //
        for (int i = 0; i < count.length; i++) {
            for (int j = 0; j < count[0].length; j++) {
                if(count[i][j] !=mine ){
                int neighborcount=0;              
                if(i>0 && j>0 && count[i-1][j-1]==mine){//upleft
                    neighborcount++;
                
                }
                if(j>0 && count[i][j-1]==mine){//up
                    neighborcount++;
                
                }
                if(i<count.length -1  &&  j<count[0].length -1 && count[i+1][j+1]==mine){ //down right
                
                neighborcount++;
                }
                
                count[i][j]=neighborcount;
                }
            }
        }
           
    
    }

    
    public void lostGame(){
        for (int i = 0; i < buton.length; i++) {
            for (int j = 0; j < buton[0].length; j++) {
                if(buton[i][j].isEnabled()){
                    if(count[i][j] !=mine){
                    buton[i][j].setText(count[i][j]+"");
                    buton[i][j].setEnabled(false);
                    }
                    else{
                    buton[i][j].setText("BOM");
                    
                    buton[i][j].setEnabled(false);
                    }
                
                }
            }
        }
    
    }
      public  void checkwin() { 
          boolean won=true;
          for (int i = 0; i < count.length; i++) {
              for (int j = 0; j < count[0].length; j++) {
                  if(count[i][j]!=mine && buton[i][j].isEnabled()==true){
                  won=false;
                  }
              }
          }
          if(won==true){
              JOptionPane.showMessageDialog(this, "Tebrikler kazandınız");
              
              
          }
        }   
        public void clearZero(ArrayList<Integer> a){
     if(a.size()==0){
        return;
        }
        else{
        int x=a.get(0)/100;
        int y=a.get(0) % 100;
             a.remove(0);
                if(count[x][y]==0){
                if(x>0 && y>0 && buton[x-1][y-1].isEnabled() ){//up left
                 buton[x-1][y-1].setText(count[x-1][y-1]+"");
                  buton[x-1][y-1].setEnabled(false);
                  if(count[x-1][y-1]==0){
                  a.add((x-1) *100+(y-1));
                  }
                 }
                     if(y>0 && buton[x][y-1].isEnabled()){//up
                    buton[x][y-1].setText(count[x][y-1]+"");
                    buton[x][y-1].setEnabled(false);
                     if(count[x][y-1]==0){
                  a.add(x*100+(y-1));
                  }
                 }
                     if(x<count.length-1 && y>0 && buton[x+1][y-1].isEnabled()){//upright
                         buton[x+1][y-1].setText(count[x+1][y-1]+"");
                         buton[x+1][y-1].setEnabled(false);
                          if(count[x+1][y-1]==0){
                               a.add((x+1)*100+(y-1));
                          }


                     }
                 
                }
            
          clearZero(a);
               }
          }
        
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(reset)){
            for (int i = 0; i < buton.length; i++) {
                for (int j = 0; j < buton[0].length; j++) {
                    buton[i][j].setEnabled(true);
                    buton[i][j].setText("");
                }
            }
        rastgeleMayınYarat();
        }
        else{
            for (int i = 0; i < buton.length; i++) {
                for (int j = 0; j <buton[0].length; j++) {
                    if(e.getSource().equals(buton[i][j])){
                        if(count[i][j]==mine){
                        buton[i][j].setForeground(Color.red);
                        buton[i][j].setText("BOM");
                    //    buton[i][j].setForeground(Color.black);
                        lostGame();
                      
                        }
                        else if(count[i][j]==0){
                          buton[i][j].setText(count[i][j]+"");
                        buton[i][j].setEnabled(false);
                        ArrayList<Integer> b=new ArrayList<Integer>();
                        b.add(i*100+j);
                        clearZero(b );
                         checkwin();
                        
                        }
                        else{
                        buton[i][j].setText(count[i][j]+"");
                        buton[i][j].setEnabled(false);
                              checkwin();
                      
                        }
                    }
                }
            }
        }
        
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    private void add(GridLayout gridLayout) {
    }

  

  


    
}
