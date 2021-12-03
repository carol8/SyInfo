import oshi.hardware.CentralProcessor;

import javax.swing.*;
import javax.swing.plaf.basic.BasicProgressBarUI;
import java.awt.*;

public class CpuPanel extends JPanel{
    private long[] prevTicks = new long[CentralProcessor.TickType.values().length];
    private CentralProcessor cpu;
    private GridBagConstraints c = new GridBagConstraints();
    private JLabel cpuLabel = new JLabel();
    private JLabel cpuProgressBarLabel = new JLabel();
    private JProgressBar cpuProgressBar = new JProgressBar();
    private JLabel cpuNameLabel = new JLabel();
    private JLabel cpuName = new JLabel();
    private JLabel cpu64Label = new JLabel();
    private JLabel cpu64 = new JLabel();
    private JLabel cpuPhysicalCoreCountLabel = new JLabel();
    private JLabel cpuPhysicalCoreCount = new JLabel();
    private JLabel cpuLogicalCoreCountLabel = new JLabel();
    private JLabel cpuLogicalCoreCount = new JLabel();
    private JLabel cpuMaxFreqLabel = new JLabel();
    private JLabel cpuMaxFreq = new JLabel();
    public CpuPanel(CentralProcessor cpu){
        this.cpu = cpu;
        this.setLayout(new GridBagLayout());
        this.setBackground(Color.WHITE);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        c.ipadx = 0;
        c.ipady = 40;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.NORTH;
        cpuLabel.setText("CPU");
        cpuLabel.setFont(new Font("Plain", Font.PLAIN, 40));
        cpuLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(cpuLabel, c);

        c.fill = GridBagConstraints.NONE;
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 1;
        c.weighty = 1;
        c.ipadx = 0;
        c.ipady = 0;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.WEST;
        cpuProgressBarLabel.setText("CPU Load: ");
        cpuProgressBarLabel.setFont(new Font("Plain", Font.PLAIN, 24));
        this.add(cpuProgressBarLabel, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 1;
        c.weightx = 1000000;
        c.weighty = 1;
        c.ipadx = 0;
        c.ipady = 18;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.EAST;
        cpuProgressBar.setMinimum(0);
        cpuProgressBar.setMaximum(1000);
        cpuProgressBar.setStringPainted(true);
        cpuProgressBar.setUI(new BasicProgressBarUI(){
            protected Color getSelectionBackground() { return Color.black; }
            protected Color getSelectionForeground() { return Color.black; }
        });
        this.add(cpuProgressBar, c);

        c.fill = GridBagConstraints.NONE;
        c.gridx = 0;
        c.gridy = 2;
        c.weightx = 1;
        c.weighty = 1;
        c.ipadx = 0;
        c.ipady = 0;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.WEST;
        cpuNameLabel.setText("CPU name: ");
        cpuNameLabel.setFont(new Font("Plain", Font.PLAIN, 24));
        this.add(cpuNameLabel, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 2;
        c.weightx = 1;
        c.weighty = 1;
        c.ipadx = 0;
        c.ipady = 0;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.WEST;
        cpuName.setText(cpu.getProcessorIdentifier().getName());
        cpuName.setFont(new Font("Plain", Font.PLAIN, 24));
        this.add(cpuName, c);

        c.fill = GridBagConstraints.NONE;
        c.gridx = 0;
        c.gridy = 3;
        c.weightx = 1;
        c.weighty = 1;
        c.ipadx = 0;
        c.ipady = 0;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.WEST;
        cpu64Label.setText("CPU type: ");
        cpu64Label.setFont(new Font("Plain", Font.PLAIN, 24));
        this.add(cpu64Label, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 3;
        c.weightx = 1;
        c.weighty = 1;
        c.ipadx = 0;
        c.ipady = 0;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.WEST;
        cpu64.setText(cpu.getProcessorIdentifier().isCpu64bit() ? "x64" : "x86");
        cpu64.setFont(new Font("Plain", Font.PLAIN, 24));
        this.add(cpu64, c);

        c.fill = GridBagConstraints.NONE;
        c.gridx = 0;
        c.gridy = 4;
        c.weightx = 1;
        c.weighty = 1;
        c.ipadx = 0;
        c.ipady = 0;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.WEST;
        cpuPhysicalCoreCountLabel.setText("CPU Physical Cores: ");
        cpuPhysicalCoreCountLabel.setFont(new Font("Plain", Font.PLAIN, 24));
        this.add(cpuPhysicalCoreCountLabel, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 4;
        c.weightx = 1;
        c.weighty = 1;
        c.ipadx = 0;
        c.ipady = 0;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.WEST;
        cpuPhysicalCoreCount.setText(String.valueOf(cpu.getPhysicalProcessorCount()));
        cpuPhysicalCoreCount.setFont(new Font("Plain", Font.PLAIN, 24));
        this.add(cpuPhysicalCoreCount, c);

        c.fill = GridBagConstraints.NONE;
        c.gridx = 0;
        c.gridy = 5;
        c.weightx = 1;
        c.weighty = 1;
        c.ipadx = 0;
        c.ipady = 0;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.WEST;
        cpuLogicalCoreCountLabel.setText("CPU Logical Cores: ");
        cpuLogicalCoreCountLabel.setFont(new Font("Plain", Font.PLAIN, 24));
        this.add(cpuLogicalCoreCountLabel, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 5;
        c.weightx = 1;
        c.weighty = 1;
        c.ipadx = 0;
        c.ipady = 0;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.WEST;
        cpuLogicalCoreCount.setText(String.valueOf(cpu.getLogicalProcessorCount()));
        cpuLogicalCoreCount.setFont(new Font("Plain", Font.PLAIN, 24));
        this.add(cpuLogicalCoreCount, c);

        c.fill = GridBagConstraints.NONE;
        c.gridx = 0;
        c.gridy = 6;
        c.weightx = 1;
        c.weighty = 1;
        c.ipadx = 0;
        c.ipady = 0;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.WEST;
        cpuMaxFreqLabel.setText("CPU Base Frequency: ");
        cpuMaxFreqLabel.setFont(new Font("Plain", Font.PLAIN, 24));
        this.add(cpuMaxFreqLabel, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 6;
        c.weightx = 1;
        c.weighty = 1;
        c.ipadx = 0;
        c.ipady = 0;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.WEST;
        cpuMaxFreq.setText(String.valueOf(cpu.getMaxFreq()/1000000000.0) + "GHz");
        cpuMaxFreq.setFont(new Font("Plain", Font.PLAIN, 24));
        this.add(cpuMaxFreq, c);
    }

    public void update(){
        double cpuLoad = cpu.getSystemCpuLoadBetweenTicks( prevTicks ) * 255;
        double r = cpuLoad;
        double g = 255 - cpuLoad;
        if(r < g){
            r = r / g * 255;
            g = 255;
        }
        else{
            g = g / r * 255;
            r = 255;
        }
        prevTicks = cpu.getSystemCpuLoadTicks();
        cpuProgressBar.setValue((int)(cpuLoad / 255 * 1000));
        cpuProgressBar.setForeground(new Color((int) r, (int) g, 0));
    }

    public JProgressBar getCpuProgressBar() {
        return cpuProgressBar;
    }
}
