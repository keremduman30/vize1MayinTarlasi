
import javax.swing.JFrame;


public class ekran {
    public static void main(String[] args) {
        JFrame ekran=new JFrame();
        ekran.setBounds(240,100,1400,800);
        
        ekran.setTitle("mayın tarlası");
        ekran.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ekran.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ekran.setResizable(false);
        mayın mayın=new mayın();
        ekran.add(mayın);
        ekran.setVisible(true);
    }
    
}
