package presentation;

import business.PublisherBU;
import pojo.PublisherPOJO;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Comparator;
import java.util.List;

public class DisabledPublisherListFrame extends JFrame {

    public interface PublisherEnabled {

        public void publisherEnabled(int id, String name, String country);
    }
    private PublisherEnabled callback;

    private DefaultTableModel model;
    private JTable table;
    private JScrollPane scroll;

    private JTextField searchField;
    private JButton searchButton;
    private JPanel searchPanel;

    private JButton enable_Button;
    private JPanel enable_Panel;

    private JPanel actionPanel;
    private String headers[] = {"ID", "Name", "Country"};
    private PublisherBU business;
    private List<PublisherPOJO> publishers;

    public DisabledPublisherListFrame(PublisherEnabled callback) {
        // Set the title of the frame
        super("List of Disabled Publishers");

        this.callback = callback;

        // Set layout of the frame
        setLayout(new BorderLayout());

        // Initialize components
        // Override isCellEditable method
        model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Always false to prevent from editing
                return false;
            }
        };
        table = new JTable();

        business = new PublisherBU();
        publishers = business.getAllDisabledPublisher();

        searchField = new JTextField(); // Search by name
        searchField.setPreferredSize(new Dimension(200, 30));
        searchButton = new JButton("Search");

        enable_Button = new JButton("Enable/Show");

        // Config components
        // Table model
        model.setColumnIdentifiers(headers);
        table.setModel(model);

        // Table
        TableColumn id_column = table.getColumnModel().getColumn(0); // id
        id_column.setPreferredWidth(30);
        id_column.setResizable(false);

        TableColumn name_column = table.getColumnModel().getColumn(1); // name
        name_column.setPreferredWidth(500);
        name_column.setResizable(false);

        TableColumn country_column = table.getColumnModel().getColumn(2);
        country_column.setPreferredWidth(300);
        country_column.setResizable(false);

        table.getTableHeader().setReorderingAllowed(false);

        // Create a TableRowSorter for the table
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());

        // Set the Comparator for the id column
        sorter.setComparator(0, new Comparator<Integer>() {
            @Override
            public int compare(Integer id1, Integer id2) {
                // Compare the id values as integers
                return id1.compareTo(id2);
            }
        });

        // Set the sorter as the RowSorter for the table
        table.setRowSorter(sorter);

        // Add a mouse listener to the table header to trigger sorting when the user clicks a column title
        table.getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int column = table.columnAtPoint(e.getPoint());
                if (column >= 0) {
                    sorter.sort();
                }
            }
        });

        // Scroll bar
        scroll = new JScrollPane(table);
        scroll.setPreferredSize(new Dimension(480, 300));

        // Add data to table
        for (PublisherPOJO publisher : publishers) {
            model.addRow(new Object[]{
                publisher.getId(),
                publisher.getName(),
                publisher.getCountry()
            });
        }

        // Create a panel for the search field and button
        searchPanel = new JPanel();
        searchPanel.add(new JLabel("Search by name:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        // Create a panel for the disable button and view disabled list
        enable_Panel = new JPanel();
        enable_Panel.add(new JLabel("Enable/Show publisher:"));
        enable_Panel.add(enable_Button);

        // Create a panel for action buttons
        actionPanel = new JPanel();
        actionPanel.setLayout(new BoxLayout(actionPanel, BoxLayout.Y_AXIS));
        actionPanel.add(searchPanel);
        actionPanel.add(enable_Panel);

        // Add components to frame
        add(scroll, BorderLayout.CENTER);
        add(actionPanel, BorderLayout.SOUTH);

        // Set the size and location of the frame
        setSize(800, 400);
        setLocation(650, 200);

        // Exit the application when the frame is closed
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Action listener for Search button (Search by name)
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the name from the search field
                String name = searchField.getText();

                // Search for publisher
                List<PublisherPOJO> searchResults = business.searchDisabledPublisher(name);

                // Clear the table model
                model.setRowCount(0);

                // Add the search results to the table model
                for (PublisherPOJO result : searchResults) {
                    model.addRow(new Object[]{
                        result.getId(),
                        result.getName(),
                        result.getCountry()
                    });
                }
            }
        });

        // Action listener for disable a publisher button
        enable_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Check if a row is selected
                if (table.getSelectedRow() == -1) {
                    // Display a message telling the user to select a publisher
                    JOptionPane.showMessageDialog(null, "Please select a publisher to enable", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Get the Id of the selected publisher
                    int id = (int) table.getValueAt(table.getSelectedRow(), 0);
                    String name = (String) table.getValueAt(table.getSelectedRow(), 1);
                    String country = (String) table.getValueAt(table.getSelectedRow(), 2);

                    System.out.println("id:" + id);

                    // Enable publisher
                    int statusCode = business.enablePublisher(id);

                    String status = "";
                    if (statusCode == -1) {
                        status = "SQL Exception";

                        // Notification
                        JOptionPane.showMessageDialog(null, status);
                    } else if (statusCode == -2) {
                        status = "Publisher not found or already be enabled";

                        // Notification
                        JOptionPane.showMessageDialog(null, status);
                    } else if (statusCode == -3) {
                        status = "Enabled publisher fail!";

                        // Notification
                        JOptionPane.showMessageDialog(null, status);
                    } else {
                        status = "Enabled publisher successfully!";

                        // Remove the enabled publisher from the table
                        int columnIndex = 0; // id
                        Object valueToSearch = Integer.valueOf(id);

                        for (int i = 0; i < model.getRowCount(); i++) {
                            // Get the value at the specified column of the current row
                            Object cellValue = model.getValueAt(i, columnIndex);

                            // Check if the value of the cell is equal to the value you are looking for
                            if (valueToSearch.equals(cellValue)) {
                                // If the value is found, remove the row from the model
                                model.removeRow(i);
                                break;
                            }
                        }

                        // Callback to the previous frame to update the table
                        callback.publisherEnabled(id, name, country);

                        // Notification
                        JOptionPane.showMessageDialog(null, status);
                    }
                }
            }
        });
    }
}
