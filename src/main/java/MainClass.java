import com.github.weisj.darklaf.LafManager;
import com.github.weisj.darklaf.theme.DarculaTheme;
import com.github.weisj.darklaf.theme.HighContrastDarkTheme;
import com.github.weisj.darklaf.theme.IntelliJTheme;
import oshi.SystemInfo;

import javax.swing.*;
import javax.swing.plaf.basic.BasicProgressBarUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainClass {
    public static void main(String[] args) throws InterruptedException {
        LafManager.install();

        JFrame window = new JFrame("SyInfo");
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setSize(new Dimension(853, 480));
        window.setMinimumSize(new Dimension(640, 320));
        window.setLayout(new BorderLayout());

        JTabbedPane tabs = new JTabbedPane();
        tabs.setBounds(0, 0, 853, 480);

        CpuPanel cpuPanel = new CpuPanel(new SystemInfo().getHardware().getProcessor());
        MemoryPanel memoryPanel = new MemoryPanel(new SystemInfo().getHardware().getMemory());
        HDDPanel hddPanel = new HDDPanel(new SystemInfo().getHardware().getDiskStores());

        tabs.addTab("CPU", cpuPanel);
        tabs.addTab("RAM", memoryPanel);
        tabs.addTab("Hard Disks", hddPanel);
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Options");
        JCheckBoxMenuItem darkMode = new JCheckBoxMenuItem("Dark Mode");
        darkMode.addActionListener(ae -> {
            if (darkMode.getState()) {
                LafManager.installTheme(new DarculaTheme());
                cpuPanel.setBackground(new Color(43, 43, 43));
                memoryPanel.setBackground(new Color(43, 43, 43));
                hddPanel.setBackground(new Color(43, 43, 43));
                cpuPanel.getCpuProgressBar().setUI(new BasicProgressBarUI());
                memoryPanel.getMemoryAvailable().setUI(new BasicProgressBarUI());
            } else {
                LafManager.installTheme(new IntelliJTheme());
                cpuPanel.setBackground(Color.WHITE);
                memoryPanel.setBackground(Color.WHITE);
                hddPanel.setBackground(Color.WHITE);
                cpuPanel.getCpuProgressBar().setUI(new BasicProgressBarUI());
                memoryPanel.getMemoryAvailable().setUI(new BasicProgressBarUI());
            }
        });
        menu.add(darkMode);
        menuBar.add(menu);
        window.setJMenuBar(menuBar);

        window.add(tabs);
        window.setVisible(true);//making the frame visible
        while (true) {
            cpuPanel.update();
            memoryPanel.update();
            hddPanel.update();
            Thread.sleep(300);
        }
    }
}
