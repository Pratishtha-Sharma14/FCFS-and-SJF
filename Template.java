package lab_7_1;
//author Pratishtha
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Template extends JFrame implements ActionListener
{
    JTextArea Text= new JTextArea();
    String [] processors= new String[100];
    int[] time= new int[100];
    JFrame Frame= new JFrame();
    JCheckBox B1= new JCheckBox("FCFS");
    JCheckBox B2= new JCheckBox("SJF");
    boolean fcfs;
    boolean sjf;
    Template()
    {
        Frame.setLayout(new GridLayout(2,0));
        JPanel Panel1= new JPanel();
        Panel1.setLayout(new GridLayout(4,1));
        JButton Button= new JButton("Compute");
        JPanel Panel2= new JPanel();
        Panel2.setLayout(new GridLayout(1,0));
        Panel1.add(Text);
        Panel1.add(B1);
        Panel1.add(B2);
        Panel1.add(Button);
        Frame.add(Panel1);
        Frame.add(Panel2);
        B1.addActionListener(this);
        B2.addActionListener(this);
        Button.addActionListener(this);
        Frame.setSize(500, 500);
        Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Frame.setVisible(true);
    }

    public static void main(String[] args)
    {
        Template T1= new Template();
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if(e.getActionCommand()=="Compute")
        {
            String text= Text.getText();
            fcfs=B1.isSelected();
            sjf= B2.isSelected();
            if(B1.isSelected()==true)
            {
                System.out.println(text);
                text=text.replaceAll(" ", "");
                FCFS(text);
            }
            if(B2.isSelected()==true)
            {
                System.out.println(text);
                text=text.replaceAll(" ", "");
                SJF(text);
            }
        }
    }
    public void FCFS(String S)
    {
        int i=0;
        int elements=0;
        int start=0;
        int end=0;
        int j=0;
        while(S.charAt(i)!='.')
        {
            if(S.charAt(i)==';')
            {
                elements++;
            }
            i++;
        }
        elements+=1;
        String Temp;
        String Temp2= S;
        for(i=0;i<elements;i++)
        {
            end= Temp2.indexOf(',');
            Temp= Temp2.substring(start, end);
            processors[i]= Temp;
            Temp2=Temp2.substring(end+1);
            start= Temp2.indexOf(';')+1;
        }
        String Temp3= S;
        String Temp4;
        start=S.indexOf(',')+1;
        for(i=0;i<elements-1;i++)
        {
                Temp4="";
                end= Temp3.indexOf(';');
                Temp= Temp3.substring(start, end);
                Temp4+=Temp;
                int t= Integer.parseInt(Temp4);
                time[i]= t;
                Temp3=Temp3.substring(end+1);
                start= Temp3.indexOf(',')+1;           
        }
        start= Temp3.indexOf(',')+1;
        end= Temp3.indexOf('.');
        Temp=Temp3.substring(start, end);
        int t= Integer.parseInt(Temp);
        time[elements-1]= t;
        for(i=0;i<elements; i++)
        {
            System.out.println(processors[i]+"\t"+time[i]);
        }
        
    }
    public void SJF(String S)
    {
        int i=0;
        int elements=0;
        int start=0;
        int end=0;
        int j=0;
        while(S.charAt(i)!='.')
        {
            if(S.charAt(i)==';')
            {
                elements++;
            }
            i++;
        }
        elements+=1;
        String Temp;
        String Temp2= S;
        for(i=0;i<elements;i++)
        {
            end= Temp2.indexOf(',');
            Temp= Temp2.substring(start, end);
            processors[i]= Temp;
            Temp2=Temp2.substring(end+1);
            start= Temp2.indexOf(';')+1;
        }
        String Temp3= S;
        String Temp4;
        start=S.indexOf(',')+1;
        for(i=0;i<elements-1;i++)
        {
                Temp4="";
                end= Temp3.indexOf(';');
                Temp= Temp3.substring(start, end);
                Temp4+=Temp;
                int t= Integer.parseInt(Temp4);
                time[i]= t;
                Temp3=Temp3.substring(end+1);
                start= Temp3.indexOf(',')+1;           
        }
        start= Temp3.indexOf(',')+1;
        end= Temp3.indexOf('.');
        Temp=Temp3.substring(start, end);
        int t= Integer.parseInt(Temp);
        time[elements-1]= t;
        
        int n = time.length; 
        for (i = 0; i < n-1; i++) 
            for ( j = 0; j < n-i-1; j++) 
                if (time[j] > time[j+1]) 
                { 
                    // swap arr[j+1] and arr[i] 
                    int temp = time[j]; 
                    String St= processors[j];
                    time[j] = time[j+1];
                    processors[j]= processors[j+1];
                    time[j+1] = temp; 
                    processors[j+1]=St;
                } 
        for(i=0;i<elements; i++)
        {
            System.out.println(processors[i]+"\t"+time[i]);
        }
    } 
    /*public static class Canvas extends JPanel 
    {
        public void paintComponent(Graphics g) 
    {
            super.paintComponent(g);
            if(sjf)
            {
                g.setFont(new Font("Arial",Font.PLAIN,16));
                g.drawString("Shortest job First",190,190);
                int cnt=0;
                int sum=0;
                for (int i=0;i<times_percSJF.size();++i)
                {
                    g.setFont(new Font("Arial",Font.PLAIN,14));
                    int red= 30;
                    int green= 100;
                    int blue= 200;
                    Color color = new Color(red, green, blue);
                    g.setColor(color);
                    g.fillRect(cnt,100,times_percSJF.get(i),75);
                    g.setColor(Color.BLACK);
                    g.drawString(Integer.toString(sum+timeSJF.get(i)),cnt+times_percSJF.get(i)-25,170);
                    g.setFont(new Font("Arial",Font.BOLD,16));
                    g.drawString(pSJF.get(i),cnt+(times_percSJF.get(i))/2-5,140);
                    cnt+=times_percSJF.get(i);
                    sum+=timeSJF.get(i);
                }
            }
            if(fcfs)
            {
                g.setFont(new Font("Arial",Font.PLAIN,16));
                g.drawString("First Come First Serve",180,90);
                int cnt=0;
                int sum=0;
                for (int i=0;i<times_percFCFS.size();++i)
                {
                    g.setFont(new Font("Arial",Font.PLAIN,14));
                    int red = (int)(Math.random()*256.0);
                    int green = (int)(Math.random()*256.0);
                    int blue = (int)(Math.random()*256.0);
                    Color color = new Color(red, green, blue);
                    g.setColor(color);
                    
                    g.fillRect(cnt,0,times_percFCFS.get(i),75);
                    g.setColor(Color.BLACK);
                    g.drawString(Integer.toString(sum+timeFCFS.get(i)),cnt+times_percFCFS.get(i)-25,70);
                    g.setFont(new Font("Arial",Font.BOLD,16));
                    g.drawString(pFCFS.get(i),cnt+(times_percFCFS.get(i))/2-5,40);
                    cnt+=times_percFCFS.get(i);
                    sum+=timeFCFS.get(i);
                }
            }
            if(!fcfs&&!sjf)
            {
                g.setFont(new Font("Times New Roman",Font.BOLD,30));
                g.drawString("GANTT CHART",850,250);
            }
        }
    }*/
}
