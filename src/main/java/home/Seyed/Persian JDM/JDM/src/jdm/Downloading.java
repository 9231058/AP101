package jdm;

import java.io.File;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import jdlib.Download;

/**
 *This is Downloading Manager. 
 * @author SMA74/9031806
 */
public class Downloading implements Observer{
     JTable jt;
     Download d;
     private URL url;
     int rowCount;
     boolean firstUpdate=true;
     Progress progress=null;
     static String dir=System.getProperty("user.home") + File.separator + "Desktop";
     
    public Downloading(URL url) { 
        this.url=url;
        progress=new Progress();
        TableModel model=JDM.frame.getTable().getModel();
        JDM.frame.getTable().setRowHeight((int) progress.getPreferredSize().getHeight());
        try{
            File f=new File(dir);           
            d=new Download(url,dir);
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(JDM.frame,"لطفا آدرس صحیح وارد کنید","خطا",JOptionPane.ERROR_MESSAGE);
        }
        
        ((DownloadTable)JDM.frame.getTable().getModel()).addDownload(d);
        d.start();
        rowCount=JDM.frame.getTable().getModel().getRowCount();
        jt=JDM.frame.getTable();
        progress.setStringPainted(true);
        JDM.frame.getTable().setDefaultRenderer(JProgressBar.class, progress);
        d.addObserver(Downloading.this);
    }
    
private  void Run()
{   
    
}
    
    @Override
    public void update(Observable o, Object arg) 
    {
        Run();
    }
}