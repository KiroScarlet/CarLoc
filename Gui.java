import java.awt.Button;

import java.awt.event.WindowAdapter;

import java.awt.event.WindowEvent;

import java.awt.*;
import java.awt.event.*;

import java.awt.FlowLayout;

import javax.swing.JFrame;

import javax.swing.*;
import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class GuiDemo {



    public static void main(String[] args)

    {

       //创建frame



       JFrame frame = new JFrame("实时位置更新");

       //调整frame的大小和初始位置

       frame.setSize(250, 250);

       frame.setLocation(100, 100);

       //采用流布局
	//Container c = getContentPane();
	//c.setBackground(Color.BLUE);
	frame.setLayout(new BorderLayout(10,10));
	JTextArea jt1 = new JTextArea("请输入车辆编号和位置：",1,5);
	jt1.setEditable(false);
	JTextArea jt2 = new JTextArea("",2,5);
       //新建Button

       Button button = new Button("实时更新");

       //将Button添加到frame中
	 frame.add(jt1,BorderLayout.NORTH);


       frame.add(jt2);
	frame.add(button,BorderLayout.SOUTH);
	button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
		  try {
	Connection con = null;
         Class.forName("org.postgresql.Driver");
         con = DriverManager
            .getConnection("jdbc:postgresql://localhost:5432/chinaosmgisdb",
            "think8848", "111111");
	   Statement st = con.createStatement();
		  String s = jt2.getText().toString();//获取文本框的内容
		  String []a = s.split("\n");
		  String id,x1,x2;
		  id = a[0];
		  x1 = a[1];
               x2 = a[2];
		  String sql = " update t_gps set geom=st_geomfromtext('Point("+x1+" "+x2+")',4326) where id='"+id+"'";
               st.executeUpdate(sql);
		  System.out.println(id);
		  System.out.println(x1);
               System.out.println(x2);
		  jt2.setText("");

	} catch (Exception ex) {
         ex.printStackTrace();
         System.err.println(ex.getClass().getName()+": "+ex.getMessage());
         System.exit(0);
      }

            }
        });





        //增加窗口监听事件，使用内部类方法，并用监听器的默认适配器

       frame.addWindowListener(new WindowAdapter(){



           //重写窗口关闭事件

           @Override

           public void windowClosing(WindowEvent arg0) {

              System.exit(0);

           }





       });



       //显示窗体

       frame.setVisible(true);


    }

}