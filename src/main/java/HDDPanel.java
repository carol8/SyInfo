import oshi.hardware.HWDiskStore;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class HDDPanel extends JPanel {
    private ArrayList<HWDiskStore> diskStore;
    private GridBagConstraints c = new GridBagConstraints();
    private JLabel hddLabel = new JLabel();
    private JScrollPane hddScrollPane;
    private JTable hddTable;
    private String[][] hddData;
    public HDDPanel(List<HWDiskStore> diskStore){
        this.diskStore = new ArrayList<>(diskStore);
        this.setLayout(new GridBagLayout());
        this.setBackground(Color.WHITE);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        c.ipadx = 0;
        c.ipady = 40;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.NORTH;
        hddLabel.setText("Hard Disks");
        hddLabel.setFont(new Font("Plain", Font.PLAIN, 40));
        hddLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(hddLabel, c);

        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 1;
        c.weighty = 1000000;
        c.ipadx = 0;
        c.ipady = 0;
        c.gridwidth = 1;
        String[] hddColumnNames = {"HDD Model", "HDD Size", "HDD Reads", "HDD Writes", "HDD Total Transfer Time"};
        hddData = new String[diskStore.size()][5];
        for(int i = 0; i < diskStore.size(); i++){
            hddData[i][0] = diskStore.get(i).getModel();
            hddData[i][1] = String.format("%.2f GB", diskStore.get(i).getSize() / (1024.0 * 1024.0 * 1024.0));
            hddData[i][2] = String.format("%d (%.2f GB)", diskStore.get(i).getReads(), diskStore.get(i).getReadBytes() / (1024.0 * 1024.0 * 1024.0));
            hddData[i][3] = String.format("%d (%.2f GB)", diskStore.get(i).getWrites(), diskStore.get(i).getWriteBytes() / (1024.0 * 1024.0 * 1024.0));
            hddData[i][4] = String.format("%dh:%dmin:%ds:%dms",
                    diskStore.get(i).getTransferTime() / 3600000,
                    diskStore.get(i).getTransferTime() % 3600000 / 60000,
                    diskStore.get(i).getTransferTime() % 60000 / 1000,
                    diskStore.get(i).getTransferTime() % 1000);
        }
        hddTable = new JTable(hddData, hddColumnNames);
        hddTable.setFont(new Font("Plain", Font.PLAIN, 16));
        hddTable.getColumn("HDD Size").setPreferredWidth(10);
        hddTable.setRowHeight(20);
        hddScrollPane = new JScrollPane(hddTable);
        this.add(hddScrollPane, c);
    }

    public void update(){
        for(int i = 0; i < diskStore.size(); i++){
            diskStore.get(i).updateAttributes();
            hddData[i][2] = String.format("%d (%.2f GB)", diskStore.get(i).getReads(), diskStore.get(i).getReadBytes() / (1024.0 * 1024.0 * 1024.0));
            hddData[i][3] = String.format("%d (%.2f GB)", diskStore.get(i).getWrites(), diskStore.get(i).getWriteBytes() / (1024.0 * 1024.0 * 1024.0));
            hddData[i][4] = String.format("%dh:%dmin:%ds:%dms",
                    diskStore.get(i).getTransferTime() / 3600000,
                    diskStore.get(i).getTransferTime() % 3600000 / 60000,
                    diskStore.get(i).getTransferTime() % 60000 / 1000,
                    diskStore.get(i).getTransferTime() % 1000);
        }
        hddTable.repaint();
    }
}
