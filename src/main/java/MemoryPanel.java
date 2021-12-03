import oshi.hardware.GlobalMemory;

import javax.swing.*;
import javax.swing.plaf.basic.BasicProgressBarUI;
import java.awt.*;

public class MemoryPanel extends JPanel{
    private GlobalMemory memory;
    private GridBagConstraints c = new GridBagConstraints();
    private JLabel memoryLabel = new JLabel();
    private JLabel memoryAvailableLabel = new JLabel();
    private JLabel memoryAvailableValue = new JLabel();
    private JProgressBar memoryAvailable = new JProgressBar();
    private JLabel memoryPageLabel = new JLabel();
    private JLabel memoryPage = new JLabel();
    private JLabel memoryVirtualLabel = new JLabel();
    private JLabel memoryVirtual = new JLabel();
    private JLabel memoryTableLabel = new JLabel();
    private JTable memoryTable;
    private JScrollPane memoryTableScroll;

    public MemoryPanel(GlobalMemory memory){
        this.memory = memory;
        this.setLayout(new GridBagLayout());
        this.setBackground(Color.WHITE);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        c.ipadx = 0;
        c.ipady = 40;
        c.gridwidth = 3;
        c.anchor = GridBagConstraints.NORTH;
        memoryLabel.setText("RAM");
        memoryLabel.setFont(new Font("Plain", Font.PLAIN, 40));
        memoryLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(memoryLabel, c);

        c.fill = GridBagConstraints.NONE;
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 1;
        c.weighty = 1;
        c.ipadx = 0;
        c.ipady = 0;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.WEST;
        memoryAvailableLabel.setText("Memory Used/Total: ");
        memoryAvailableLabel.setFont(new Font("Plain", Font.PLAIN, 24));
        this.add(memoryAvailableLabel, c);

        c.fill = GridBagConstraints.NONE;
        c.gridx = 1;
        c.gridy = 1;
        c.weightx = 1;
        c.weighty = 1;
        c.ipadx = 0;
        c.ipady = 0;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.WEST;
        memoryAvailableValue.setText("/");
        memoryAvailableValue.setFont(new Font("Plain", Font.PLAIN, 24));
        this.add(memoryAvailableValue, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 1;
        c.weightx = 1000000;
        c.weighty = 1;
        c.ipadx = 0;
        c.ipady = 18;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.EAST;
        memoryAvailable.setMinimum(0);
        memoryAvailable.setMaximum(1000);
        memoryAvailable.setStringPainted(true);
        memoryAvailable.setUI(new BasicProgressBarUI(){
            protected Color getSelectionBackground() { return Color.black; }
            protected Color getSelectionForeground() { return Color.black; }
        });
        memoryAvailable.setValue(0);
        this.add(memoryAvailable, c);

        c.fill = GridBagConstraints.NONE;
        c.gridx = 0;
        c.gridy = 2;
        c.weightx = 1;
        c.weighty = 1;
        c.ipadx = 0;
        c.ipady = 0;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.WEST;
        memoryPageLabel.setText("Memory Page Size: ");
        memoryPageLabel.setFont(new Font("Plain", Font.PLAIN, 24));
        this.add(memoryPageLabel, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 2;
        c.weightx = 1;
        c.weighty = 1;
        c.ipadx = 0;
        c.ipady = 0;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.WEST;
        memoryPage.setText(memory.getPageSize() + " Bytes");
        memoryPage.setFont(new Font("Plain", Font.PLAIN, 24));
        this.add(memoryPage, c);

        c.fill = GridBagConstraints.NONE;
        c.gridx = 0;
        c.gridy = 3;
        c.weightx = 1;
        c.weighty = 1;
        c.ipadx = 0;
        c.ipady = 0;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.WEST;
        memoryVirtualLabel.setText("Virtual memory size: ");
        memoryVirtualLabel.setFont(new Font("Plain", Font.PLAIN, 24));
        this.add(memoryVirtualLabel, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 3;
        c.weightx = 1;
        c.weighty = 1;
        c.ipadx = 0;
        c.ipady = 0;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.WEST;
        memoryVirtual.setText(String.format("%.2f", memory.getVirtualMemory().getSwapTotal() / 1e9) + "GB");
        memoryVirtual.setFont(new Font("Plain", Font.PLAIN, 24));
        this.add(memoryVirtual, c);

        c.fill = GridBagConstraints.NONE;
        c.gridx = 0;
        c.gridy = 4;
        c.weightx = 1;
        c.weighty = 1;
        c.ipadx = 0;
        c.ipady = 0;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.WEST;
        memoryTableLabel.setText("Memory Sticks: ");
        memoryTableLabel.setFont(new Font("Plain", Font.PLAIN, 24));
        this.add(memoryTableLabel, c);

        String[][] physicalMemoryData = new String[memory.getPhysicalMemory().size()][6];
        for(int i = 0; i < memory.getPhysicalMemory().size(); i++){
            physicalMemoryData[i][0] = String.valueOf(i);
            physicalMemoryData[i][1] = memory.getPhysicalMemory().get(i).getBankLabel();
            physicalMemoryData[i][2] = String.format("%.2f GB", (memory.getPhysicalMemory().get(i).getCapacity()) / (1024.0 * 1024.0 * 1024.0));
            physicalMemoryData[i][3] = String.format("%d MHz", Math.round(memory.getPhysicalMemory().get(i).getClockSpeed() / 1e6));
            physicalMemoryData[i][4] = memory.getPhysicalMemory().get(i).getManufacturer();
            physicalMemoryData[i][5] = memory.getPhysicalMemory().get(i).getMemoryType();
        }
        String[] columnNames = {"#", "Bank Label", "Capacity", "Clock Speed", "Manufacturer", "Memory type"};
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 4;
        c.weightx = 1;
        c.weighty = 2;
        c.ipadx = 0;
        c.ipady = 80;
        c.gridwidth = 3;
        memoryTable = new JTable(physicalMemoryData, columnNames);
        memoryTable.getColumn("#").setPreferredWidth(2);
        memoryTable.getColumn("Bank Label").setPreferredWidth(120);
        memoryTable.setRowHeight(20);
        memoryTable.setFont(new Font("Plain", Font.PLAIN, 16));
        memoryTableScroll = new JScrollPane(memoryTable);
        this.add(memoryTableScroll, c);
    }

    public void update(){
        long available, total;
        double ratio, r, g;
        available = memory.getAvailable();
        total = memory.getTotal();
        ratio = ((float) total - available) / total;
        r = ratio * 255;
        g = 255 - ratio * 255;
        if(r < g){
            r = r / g * 255;
            g = 255;
        }
        else{
            g = g / r * 255;
            r = 255;
        }
        memoryAvailable.setValue((int) (ratio * 1000));
        memoryAvailable.setForeground(new Color((int) r, (int) g, 0));
        memoryAvailableValue.setText(String.format("%.2f/%.2fGB ", (total - available) / (1024.0 * 1024.0 * 1024.0), total / (1024.0 * 1024.0 * 1024.0)));
    }

    public JProgressBar getMemoryAvailable() {
        return memoryAvailable;
    }
}
