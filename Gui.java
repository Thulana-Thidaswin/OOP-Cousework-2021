import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Gui extends JFrame {

        public Gui(Forumala1ChampionshipManager fcm) {
                // fcm is a instance of Forumala1ChampionshipManager

                String[] columnNames = { "Driver Name", "Driver Team", "No. of 1st", "No. of 2nd", "No. of 3rd",
                                "Points", "No. of Races" };
                String[] columnNames2 = { "Date", "Driver Name", "Position" };
                String[] columnNames3 = { "Driver Name", "Date", "Position" };
                String[] columnNames4 = { "Driver Name", "Date", "Position" };
                Object[][] G = fcm.StringRace();

                Object[][] values = new Object[Forumala1ChampionshipManager.raceDriver.size()][7];
                fcm.sortForTable();
                GridLayout Layout = new GridLayout(9, 10);

                /** 
                 * 4 table models for 4 tables, tableModel1 for the defualt 
                */

                DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
                DefaultTableModel tableModel2 = new DefaultTableModel(columnNames2, 0);
                DefaultTableModel tableModel3 = new DefaultTableModel(columnNames3, 0);
                DefaultTableModel tableModel4 = new DefaultTableModel(columnNames4, 0);

                JTable table = new JTable(tableModel);
                JTable table2 = new JTable(tableModel2);
                JTable table3 = new JTable(tableModel3);
                JTable table4 = new JTable(tableModel4);

                for (Formula1Driver i : Forumala1ChampionshipManager.raceDriver) {
                        Object[] driver = { i.getname(), i.getteam(), i.getfpos(), i.getspos(), i.gettpos(),
                                        i.getpoints(), i.getnRaces(), };
                        tableModel.addRow(driver);
                }

                add(new JScrollPane(table));
                add(new JScrollPane(table2));
                add(new JScrollPane(table3));
                add(new JScrollPane(table4));

                setLayout(Layout);

                JButton randButton = new JButton("Generate random Positions");
                JButton raceButton = new JButton("Generate a random race");
                JButton dateButton = new JButton("Sort races with date");
                JButton driverSearch = new JButton("Search for a driver");
                randButton.setBounds(0, 100, 95, 30);
                raceButton.setBounds(0, 110, 95, 30);
                dateButton.setBounds(0, 120, 95, 30);
                driverSearch.setBounds(0, 130, 95, 30);
                Dimension size = new Dimension(100, 50);
                randButton.setMaximumSize(size);
                JTextField Search = new JTextField();
                Search.setText("Enter what you seek here");
                add(randButton);
                add(raceButton);
                add(dateButton);
                add(Search);
                add(driverSearch);

                TableRowSorter<TableModel> s = new TableRowSorter<TableModel>(table.getModel());
                table.setRowSorter(s);
                java.util.List<RowSorter.SortKey> sortList = new ArrayList<>(5);
                sortList.add(new RowSorter.SortKey(5, SortOrder.ASCENDING));
                s.setSortKeys(sortList);

                randButton.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {

                                fcm.randomrPos();
                                tableModel.setRowCount(0);
                                // updating table
                                for (Formula1Driver i : Forumala1ChampionshipManager.raceDriver) {
                                        Object[] driver = { i.getname(), i.getteam(), i.getfpos(), i.getspos(),
                                                        i.gettpos(), i.getpoints(), i.getnRaces(), };
                                        tableModel.addRow(driver);
                                }
                                for (Race j : Forumala1ChampionshipManager.RandomDriverList) {
                                        if (j.getPosition() == -1) {
                                                continue;
                                        }
                                        Object[] driver = { j.getDate(), j.getDriverName(), j.getPosition() };
                                        tableModel2.addRow(driver);
                                }

                        }

                });
                raceButton.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                                System.out.println("Working");

                        }
                });
                dateButton.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                                fcm.sortDate();
                                for (Race j : Forumala1ChampionshipManager.RandomDriverList) {
                                        if ((j.getPosition() != -1)) {
                                                Object[] driver = { j.getDriverName(), j.getDate(),
                                                                j.getPosition() };
                                                tableModel3.addRow(driver);
                                        }
                                }
                        }
                });
                driverSearch.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                                String t = Search.getText();
                                for (Formula1Driver i : Forumala1ChampionshipManager.raceDriver) {
                                        if (t.equalsIgnoreCase(i.getname())) {
                                                for (Race j : Forumala1ChampionshipManager.RandomDriverList) {
                                                        if (t.equalsIgnoreCase(j.getDriverName())
                                                                        && (j.getPosition() != -1)) {
                                                                Object[] driver = { j.getDriverName(), j.getDate(),
                                                                                j.getPosition() };
                                                                tableModel3.addRow(driver);
                                                        }
                                                }

                                        }
                                }

                        }
                });

        }

}