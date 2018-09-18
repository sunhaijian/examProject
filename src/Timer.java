import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.TimerTask;

/**
 * 这是一个倒计时
 * 输入框输入秒，倒计时结束后会播放音乐，停止播放音乐请按esc
 */
public class Timer extends JFrame {
    //输入倒计时区域
    private JTextField showTimeField;
    //开始按钮，暂停按钮，重置按钮
    private JButton startBtn, pauseBtn, resetBtn;
    //倒计时时间
    private int time = 30;
    //定时器
    private static java.util.Timer timer = null;

    public Timer() {
        super("倒计时器");
        this.setLayout(null);
        this.setSize(378, 349);
        this.setResizable(false);
        JPanel jp = new JPanel(null);
        jp.setBounds(10, 15, getWidth() - 24, getHeight());
        //设置背景色
        jp.setBackground(new Color(217, 228, 241));
        this.getContentPane().setBackground(new Color(217, 228, 241));

        showTimeField = new JTextField(time + "");
        showTimeField.setEnabled(true);
        showTimeField.setBounds(30, 30, 300, 50);
        showTimeField.setHorizontalAlignment(JLabel.CENTER);
        showTimeField.setFont(new Font("宋体", Font.PLAIN, 20));
        showTimeField.setDisabledTextColor(Color.BLACK);
        showTimeField.setBorder(new LineBorder(new Color(0, 0, 0)));
        showTimeField.setBackground(new Color(217, 228, 241));
        showTimeField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                String old = showTimeField.getText();
                try {
                    int n = Integer.parseInt(old);
                    time = n;
                } catch (NumberFormatException e) {

                }
                showTimeField.setText(time + "");
            }
        });

        startBtn = new JButton("Start");
        startBtn.setMargin(new java.awt.Insets(0, 0, 0, 0));
        startBtn.setBounds(50, 99, 50, 64);
        startBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (time > 0) {
                    if (timer != null) {
                        timer.cancel();
                    }
                    timer = new java.util.Timer();
                    timer.schedule(new TimerTask() {
                        public void run() {
                            time--;
                            showTimeField.setText(time + "");
                            if (time == 0) {
                                System.out.println("我的工作是播放音乐");
                                timer.cancel();
                            }
                        }
                    }, 0, 1000);
                }
            }
        });

        pauseBtn = new JButton("Pause");
        pauseBtn.setMargin(new java.awt.Insets(0, 0, 0, 0));
        pauseBtn.setBounds(150, 99, 50, 64);
        pauseBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                timer.cancel();
            }
        });

        resetBtn = new JButton("Reset");
        resetBtn.setMargin(new java.awt.Insets(0, 0, 0, 0));
        resetBtn.setBounds(250, 99, 50, 64);
        resetBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                timer.cancel();
                time = 0;
                showTimeField.setText("0");
            }
        });


        jp.add(startBtn);
        jp.add(pauseBtn);
        jp.add(resetBtn);
        jp.add(showTimeField);
        this.add(jp);
        this.setVisible(true);

        //得到当前键盘事件的管理器
        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventPostProcessor(new KeyEventPostProcessor() {
            @Override
            public boolean postProcessKeyEvent(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    System.out.println("我的工作是停止播放音乐");
                }
                return true;
            }
        });
        //关闭窗口时退出程序
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }


    public static void main(String[] args) {
        new Timer();
    }
}
