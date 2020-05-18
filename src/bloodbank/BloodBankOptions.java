package bloodbank;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class BloodBankOptions extends Frame {

    @Override
    public synchronized void addMouseMotionListener(MouseMotionListener ml) {
        for (int i = 0; ; i++) {
            System.out.println("mouse motion"+i);
        }
  
    }
    

    Connection databaseConnection = null;
    Statement dataStatement;
    PreparedStatement databasePreparedStatement = null;
    ResultSet resultSet = null;
    DefaultTableModel defaultTableModel = new DefaultTableModel();

    private Button sellBloodSaveButton, deleteTableData, refreshTableData, searchBuiyerButton, sellBloodButton, newDonorButton, exitButton, numOfBloodAvailableButton, availableDonorListButton;
    private Panel newDonorPanel = new Panel();
    private Panel searchBuiyerPannel = new Panel();
    private Panel sellBloodPannel = new Panel();
    private Panel numOfBloodAvailablePannel = new Panel();
    private Panel availableDonorListPannel = new Panel();
    private Label availalbeDonorListsLabel;
    private JTable donorListsJtable;
    private JLabel numOfBloodneededJLabel;
     
    private TextField numberOfBloodPackWantToSell;
    private Font bigLabelFont = new Font("Varenda", Font.BOLD, 20);
    
    BloodBankOptions() {
       
        databaseConnection = BloodBankOptions.connectDatabase();

        searchBuiyerPannel.setBounds(135, 00, 615, 390);
        sellBloodPannel.setBounds(135, 00, 615, 390);
        numOfBloodAvailablePannel.setBounds(135, 00, 615, 390);
        availableDonorListPannel.setBounds(135, 00, 615, 390);
        newDonorPanel.setBounds(135, 00, 615, 390);

        setTitle("Blood Bank");
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                dispose();
            }
        });
        Image icon = Toolkit.getDefaultToolkit().getImage("mainIcon.jpg");
        setIconImage(icon);
        Font allButtonFont = new Font("Varenda", Font.BOLD, 13);
        newDonorButton = new Button("New Donor");
        newDonorButton.setBounds(16, 50, 110, 30);
        newDonorButton.setForeground(Color.BLACK);
        newDonorButton.setBackground(Color.LIGHT_GRAY);
        newDonorButton.setFont(allButtonFont);
        add(newDonorButton);
        newDonorButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                if (ae.getSource() == newDonorButton) {
                    newDonorPanel.setVisible(true);
                    NewDoner();
                }

            }
        });

        availableDonorListButton = new Button("Donor's List");
        availableDonorListButton.setBounds(16, 100, 110, 30);
        availableDonorListButton.setBackground(Color.LIGHT_GRAY);
        availableDonorListButton.setFont(allButtonFont);
        add(availableDonorListButton);
        availableDonorListButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {

                availableDonorList();
               
                Object col[] = {"Phone", "Name", "Age", "Blood_Group", "Gender", "Birthdate", "City"};
                defaultTableModel.setColumnIdentifiers(col);
                donorListsJtable.setModel(defaultTableModel);
               // donorListsJtable.repaint();
                updateTableForDonorsList();
            }
        });

        sellBloodButton = new Button("Sell Blood");
        sellBloodButton.setBounds(16, 150, 110, 30);
        // sellBloodButton.setForeground(Color.red);
        sellBloodButton.setBackground(Color.LIGHT_GRAY);
        sellBloodButton.setFont(allButtonFont);
        add(sellBloodButton);
        sellBloodButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                sellBloodPannel.setVisible(true);
                sellBlood();

            }
        });
        //searchBuiyerButton.setBounds(16, 200, 110, 30);

      

       
       // numOfBloodAvailableButton.setBounds(16, 250, 110, 30);
       

        exitButton = new Button("Exit");
        exitButton.setBounds(16, 300, 110, 30);
        exitButton.setBackground(Color.GRAY);
        exitButton.setForeground(Color.BLACK);
        exitButton.setFont(allButtonFont);
        add(exitButton);
        exitButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
        });

        setSize(760, 400);
        setLayout(null);
        setLocation(300, 200);
        setVisible(true);

    }
    BufferedImage backgroundImage;

    public static Connection connectDatabase() {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection("jdbc:sqlite:BloodDonorsList.db");
            return connection;
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception);
            return null;
        }

    }

    public void backgroundImage() {
        try {
            //backgroundImage=ImageIO.read(new File("C:\\Users\\rbnho\\Documents\\NetBeansProjects\\BloodBank\\src\\image\\BloodBank1.jpeg"));
            backgroundImage = ImageIO.read(new File("backGroundImage.jpeg"));
        } catch (IOException ioException) {
        }
        setSize(760, 400);
        setVisible(true);
        setResizable(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
    }

    public void NumOfBloodAvailable() {

    }

    public int NewDoner() {
        
        //newDonorButton.setEnabled(false);
        
        //newDonorButton.setSize(115, 35);
        //newDonorButton.setBackground(Color.GRAY);

        remove(availableDonorListPannel);
        remove(numOfBloodAvailablePannel);
        remove(searchBuiyerPannel);
        remove(sellBloodPannel);
        // remove(newDonorPanel);
        newDonorPanel.setBackground(Color.LIGHT_GRAY);
        newDonorPanel.setLayout(null);
        add(newDonorPanel);

        Button singlebarButtonNoUse = new Button();
        singlebarButtonNoUse.setBounds(00, 00, 5, 390);
        singlebarButtonNoUse.setEnabled(false);
        //glebarButtonNoUse.setBackground(Color.BLUE);
        newDonorPanel.add(singlebarButtonNoUse);
        Label bloodDonorInfoLabel = new Label("Blood Donor Info: ");
        bloodDonorInfoLabel.setBounds(20, 35, 170, 50);
        bloodDonorInfoLabel.setBackground(Color.lightGray);
        bloodDonorInfoLabel.setForeground(Color.BLACK);

        bloodDonorInfoLabel.setFont(bigLabelFont);
        newDonorPanel.add(bloodDonorInfoLabel);

        TextField firstNameTextfield = new TextField("First name");
        firstNameTextfield.setBounds(20, 90, 150, 20);
        firstNameTextfield.setEnabled(true);
        newDonorPanel.add(firstNameTextfield);
        firstNameTextfield.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                firstNameTextfield.setText(null);
            }

        });
        firstNameTextfield.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent keyPressed) {
                firstNameTextfield.setText(null);

            }

        });

        TextField lastNameTextfield = new TextField("Last name");
        lastNameTextfield.setBounds(190, 90, 150, 20);
        lastNameTextfield.setEnabled(true);
        newDonorPanel.add(lastNameTextfield);
        lastNameTextfield.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                lastNameTextfield.setText(null);
            }

        });
        lastNameTextfield.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent keyPressed) {
                lastNameTextfield.setText(null);

            }

        });

        TextField ageTextfield = new TextField("Age");
        ageTextfield.setBounds(360, 90, 70, 20);
        ageTextfield.setEnabled(true);
        newDonorPanel.add(ageTextfield);
        ageTextfield.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                ageTextfield.setText(null);
            }

        });
        ageTextfield.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent keyPressed) {
                ageTextfield.setText(null);

            }

        });
        //-----------------------------------------------
        Label bloodGroupeLabel = new Label("Blood Groupe");
        bloodGroupeLabel.setBounds(20, 120, 150, 19);
        bloodGroupeLabel.setBackground(Color.lightGray);
        bloodGroupeLabel.setForeground(Color.BLACK);
        Font allLabelFont = new Font("Varenda", Font.BOLD, 14);
        bloodGroupeLabel.setFont(allLabelFont);
        newDonorPanel.add(bloodGroupeLabel);

        Choice selectBloodGroupeCoice = new Choice();
        selectBloodGroupeCoice.setBounds(20, 145, 70, 20);
        selectBloodGroupeCoice.add("A");
        selectBloodGroupeCoice.add("B");
        selectBloodGroupeCoice.add("AB");
        selectBloodGroupeCoice.add("O");
        newDonorPanel.add(selectBloodGroupeCoice);

        Choice selectBloodGroupePositiveOrNegaticveCoice = new Choice();
        selectBloodGroupePositiveOrNegaticveCoice.setBounds(100, 145, 70, 20);
        selectBloodGroupePositiveOrNegaticveCoice.add("+(ve)");
        selectBloodGroupePositiveOrNegaticveCoice.add("-(ve)");
        newDonorPanel.add(selectBloodGroupePositiveOrNegaticveCoice);

        Label genderLabel = new Label("Gender");
        genderLabel.setBounds(190, 120, 150, 19);
        genderLabel.setBackground(Color.lightGray);
        genderLabel.setForeground(Color.BLACK);
        genderLabel.setFont(allLabelFont);
        newDonorPanel.add(genderLabel);

        Font genderSelectFont = new Font("Varenda", Font.BOLD, 12);

        CheckboxGroup genderCheckBoxGroupe = new CheckboxGroup();

        Label genderSelectLabel = new Label();
        genderSelectLabel.setVisible(false);
        Checkbox maleCheckBox = new Checkbox("Male", genderCheckBoxGroupe, false);
        maleCheckBox.setBounds(190, 145, 70, 20);
        maleCheckBox.setFont(genderSelectFont);
        newDonorPanel.add(maleCheckBox);
        maleCheckBox.addItemListener((ItemEvent itemEvent) -> {
            if (itemEvent.getStateChange() == 1) {
                genderSelectLabel.setText("Male");
            }
        });
        Checkbox femaleCheckBox = new Checkbox("Female", genderCheckBoxGroupe, false);
        femaleCheckBox.setBounds(265, 145, 70, 20);
        femaleCheckBox.setFont(genderSelectFont);
        newDonorPanel.add(femaleCheckBox);
        femaleCheckBox.addItemListener((ItemEvent itemEvent) -> {
            if (itemEvent.getStateChange() == 1) {
                genderSelectLabel.setText("Female");
            }
        });
        Checkbox customGenderCheckBox = new Checkbox("Custom", genderCheckBoxGroupe, false);
        customGenderCheckBox.setBounds(340, 145, 70, 20);
        customGenderCheckBox.setFont(genderSelectFont);
        newDonorPanel.add(customGenderCheckBox);

        customGenderCheckBox.addItemListener((ItemEvent itemEvent) -> {
            if (itemEvent.getStateChange() == 1) {
                genderSelectLabel.setText("Custom");
            }
        });

        //----------------------------------------------------
        Label birthdayLabel = new Label("Birthday");
        birthdayLabel.setBounds(20, 175, 150, 19);
        birthdayLabel.setBackground(Color.lightGray);
        birthdayLabel.setForeground(Color.BLACK);
        birthdayLabel.setFont(allLabelFont);
        newDonorPanel.add(birthdayLabel);

        Choice dayCoice = new Choice();
        dayCoice.setBounds(20, 200, 70, 20);
        dayCoice.add("Day");
        dayCoice.add("01");
        dayCoice.add("02");
        dayCoice.add("03");
        dayCoice.add("04");
        dayCoice.add("05");
        dayCoice.add("06");
        dayCoice.add("07");
        dayCoice.add("08");
        dayCoice.add("09");
        dayCoice.add("10");
        dayCoice.add("11");
        dayCoice.add("12");
        dayCoice.add("13");
        dayCoice.add("14");
        dayCoice.add("15");
        dayCoice.add("16");
        dayCoice.add("17");
        dayCoice.add("18");
        dayCoice.add("19");
        dayCoice.add("20");
        dayCoice.add("21");
        dayCoice.add("22");
        dayCoice.add("23");
        dayCoice.add("24");
        dayCoice.add("25");
        dayCoice.add("26");
        dayCoice.add("27");
        dayCoice.add("28");
        dayCoice.add("29");
        dayCoice.add("30");
        dayCoice.add("31");
        newDonorPanel.add(dayCoice);

        Choice monthCoice = new Choice();
        monthCoice.setBounds(100, 200, 70, 20);
        monthCoice.add("Month");
        monthCoice.add("Jan");
        monthCoice.add("Feb");
        monthCoice.add("Mar");
        monthCoice.add("Apr");
        monthCoice.add("May");
        monthCoice.add("Jun");
        monthCoice.add("Jul");
        monthCoice.add("Aug");
        monthCoice.add("Sept");
        monthCoice.add("Oct");
        monthCoice.add("Nov");
        monthCoice.add("Dec");
        newDonorPanel.add(monthCoice);

        Choice yearCoice = new Choice();
        yearCoice.setBounds(180, 200, 70, 20);
        yearCoice.add("Year");
        yearCoice.add("2006");
        yearCoice.add("2005");
        yearCoice.add("2004");
        yearCoice.add("2003");
        yearCoice.add("2002");
        yearCoice.add("2001");
        yearCoice.add("2000");
        yearCoice.add("1999");
        yearCoice.add("1998");
        yearCoice.add("1997");
        yearCoice.add("1996");
        yearCoice.add("1995");
        yearCoice.add("1994");
        yearCoice.add("1993");
        yearCoice.add("1992");
        yearCoice.add("1991");
        yearCoice.add("1990");
        yearCoice.add("1989");
        yearCoice.add("1988");
        yearCoice.add("1987");
        yearCoice.add("1986");
        yearCoice.add("1985");
        yearCoice.add("1984");
        yearCoice.add("1983");
        yearCoice.add("1982");
        yearCoice.add("1981");
        yearCoice.add("1980");
        yearCoice.add("1979");
        yearCoice.add("1978");
        yearCoice.add("1977");
        yearCoice.add("1976");
        yearCoice.add("1975");
        newDonorPanel.add(yearCoice);
        //------------------------------------------------------
        Label cityLabel = new Label("City");
        cityLabel.setBounds(20, 230, 150, 19);
        cityLabel.setBackground(Color.lightGray);
        cityLabel.setForeground(Color.BLACK);
        cityLabel.setFont(allLabelFont);
        newDonorPanel.add(cityLabel);

        TextField insertCityTextfield = new TextField("Name of your city");
        insertCityTextfield.setBounds(20, 255, 150, 20);
        insertCityTextfield.setEnabled(true);
        newDonorPanel.add(insertCityTextfield);
        insertCityTextfield.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                insertCityTextfield.setText(null);
            }

        });
        insertCityTextfield.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent keyPressed) {
                insertCityTextfield.setText(null);

            }

        });

        Label phoneNumLabel = new Label("Phone number");
        phoneNumLabel.setBounds(190, 230, 150, 19);
        phoneNumLabel.setBackground(Color.lightGray);
        phoneNumLabel.setForeground(Color.BLACK);
        phoneNumLabel.setFont(allLabelFont);
        newDonorPanel.add(phoneNumLabel);

        TextField insertPhoneNumTextfield = new TextField("Mobile/telephone number");
        insertPhoneNumTextfield.setBounds(190, 255, 150, 20);
        insertPhoneNumTextfield.setEnabled(true);
        newDonorPanel.add(insertPhoneNumTextfield);
        insertPhoneNumTextfield.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                insertPhoneNumTextfield.setText(null);
            }

        });
        insertPhoneNumTextfield.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent keyPressed) {
                insertPhoneNumTextfield.setText(null);

            }

        });

        //----------------------------------------------------
       
                //int statemnetUpdateCheak = dataStatement.executeUpdate(sqlSaveInfoOnSavebuttonClick);
        Button saveButton = new Button("Save");
        saveButton.setBounds(20, 305, 150, 30);
        Font saveCancelButtonFont = new Font("Varenda", Font.BOLD, 14);
        saveButton.setFont(saveCancelButtonFont);
        newDonorPanel.add(saveButton);
         int  statementReturn = 0;
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                if (ae.getSource() == saveButton) {
                    String Name = firstNameTextfield.getText() + " " + lastNameTextfield.getText();
                    String age = ageTextfield.getText();
                    String bloodGroupe = selectBloodGroupeCoice.getSelectedItem() + " " + selectBloodGroupePositiveOrNegaticveCoice.getSelectedItem();
                    String gender = genderSelectLabel.getText();
                    String birthdate = dayCoice.getSelectedItem() + " " + monthCoice.getSelectedItem() + " " + yearCoice.getSelectedItem();
                    String city = insertCityTextfield.getText();
                    String phoneNumber = insertPhoneNumTextfield.getText();
                    String sqlSaveInfoOnSavebuttonClick = "INSERT INTO BloodDonorsList(Phone,Name,Age,Blood_Group,Gender,Birthdate,City)VALUES(?,?,?,?,?,?,?)";
                    
                     try {
                        int statemnetUpdateCheak = dataStatement.executeUpdate(sqlSaveInfoOnSavebuttonClick);
                        
                        databasePreparedStatement = databaseConnection.prepareStatement(sqlSaveInfoOnSavebuttonClick);
                        databasePreparedStatement.setString(1, phoneNumber);
                        databasePreparedStatement.setString(2, Name);
                        databasePreparedStatement.setString(3, age);
                        databasePreparedStatement.setString(4, bloodGroupe);
                        databasePreparedStatement.setString(5, gender);
                        databasePreparedStatement.setString(6, birthdate);
                        databasePreparedStatement.setString(7, city);

                        databasePreparedStatement.execute();
                        JOptionPane.showMessageDialog(null, "Saved");
                        resultSet.close();
                        databasePreparedStatement.close();

                    } catch (Exception exception) {
                        JOptionPane.showMessageDialog(null, exception);

                    }

                }

            }
        });

        Button cancelButton = new Button("Cancel");
        cancelButton.setBounds(190, 305, 150, 30);
        cancelButton.setFont(saveCancelButtonFont);
        newDonorPanel.add(cancelButton);
        cancelButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                if (ae.getSource() == cancelButton) {
                    newDonorButton.setEnabled(true);
                    newDonorButton.setSize(110, 30);
                    newDonorButton.setBackground(Color.LIGHT_GRAY);
                    newDonorPanel.setVisible(false);
                }
            }
        });

        cancelButton.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() == 10) {
                    newDonorButton.setEnabled(true);
                    newDonorButton.setSize(110, 30);
                    newDonorButton.setBackground(Color.LIGHT_GRAY);
                    newDonorPanel.setVisible(false);
                }
            }

        });
        return statementReturn;
    }

    public void updateTableForDonorsList() {
         
        String sqlupdateTable = "Select Phone,Name,Age,Blood_Group,Gender,Birthdate,City from BloodDonorsList";
        try {
            //int statemnetUpdateCheak = dataStatement.executeUpdate(NewDoner().sqlSaveInfoOnSavebuttonClick);
            
            dataStatement = databaseConnection.createStatement();
            
            databasePreparedStatement = databaseConnection.prepareStatement(sqlupdateTable);
            resultSet = databasePreparedStatement.executeQuery();
            Object[] coloumnOfSavedData = new Object[7];
            while (resultSet.next()) {
                coloumnOfSavedData[0] = resultSet.getString("Phone");
                coloumnOfSavedData[1] = resultSet.getString("Name");
                coloumnOfSavedData[2] = resultSet.getString("Age");
                coloumnOfSavedData[3] = resultSet.getString("Blood_Group");
                coloumnOfSavedData[4] = resultSet.getString("Gender");
                coloumnOfSavedData[5] = resultSet.getString("Birthdate");
                coloumnOfSavedData[6] = resultSet.getString("City");
                defaultTableModel.addRow(coloumnOfSavedData);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    public void availableDonorList() {
        newDonorButton.setEnabled(true);
        // remove(availableDonorListPannel);
        remove(numOfBloodAvailablePannel);
        remove(searchBuiyerPannel);
        remove(sellBloodPannel);
        remove(newDonorPanel);

        availableDonorListPannel.setBounds(135, 00, 615, 390);
        availableDonorListPannel.setBackground(Color.LIGHT_GRAY);
        availableDonorListPannel.setLayout(null);
        add(availableDonorListPannel);
        Button singlebarButtonNoUse = new Button();
        singlebarButtonNoUse.setBounds(00, 00, 5, 390);
        singlebarButtonNoUse.setEnabled(false);
        availableDonorListPannel.add(singlebarButtonNoUse);

        availalbeDonorListsLabel = new Label("Available Donors List:");
        availalbeDonorListsLabel.setFont(bigLabelFont);
        availalbeDonorListsLabel.setBounds(20, 40, 250, 50);
        availableDonorListPannel.add(availalbeDonorListsLabel);

        String donorListsColumns[] = {"Phone", "Name", "Age", "Blood_Group", "Gender", "Birthdate", "City"};
        String donorListsRows[][] = {{"0171555555", "Rahim", "20", "B+(ve)", "Male", "23 Jan 1999", "Bogura"}};

        donorListsJtable = new JTable(donorListsRows, donorListsColumns);
        donorListsJtable.setBounds(7, 100, 603, 190);
        donorListsJtable.repaint();
        JScrollPane donorListJscrollPane = new JScrollPane(donorListsJtable);
        donorListJscrollPane.setBounds(7, 100, 603, 190);
        add(donorListJscrollPane);
        availableDonorListPannel.add(donorListJscrollPane);

        refreshTableData = new Button("Refresh");
        refreshTableData.setBounds(7, 298, 50, 30);
        refreshTableData.setBackground(Color.PINK);
        availableDonorListPannel.add(refreshTableData);
        refreshTableData.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                
                donorListsJtable.repaint();//not worked..
            }
        });
        deleteTableData = new Button("Delete A Row");
        deleteTableData.setBounds(526, 298, 85, 30);
        deleteTableData.setBackground(Color.orange);
        availableDonorListPannel.add(deleteTableData);
        deleteTableData.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                donorListsJtable.removeAll();//not worked..
            }
        });
    }

    public void sellBlood() {
        remove(availableDonorListPannel);
        remove(numOfBloodAvailablePannel);
        remove(searchBuiyerPannel);
        //remove(sellBloodPannel);
        remove(newDonorPanel);

        sellBloodPannel.setBounds(135, 00, 615, 390);
        sellBloodPannel.setBackground(Color.LIGHT_GRAY);
        sellBloodPannel.setLayout(null);
        add(sellBloodPannel);
        Button singlebarButtonNoUse = new Button();
        singlebarButtonNoUse.setBounds(00, 00, 5, 390);
        singlebarButtonNoUse.setEnabled(false);
        sellBloodPannel.add(singlebarButtonNoUse);
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        Label bloodGroupeLabel = new Label("Blood Groupe:");
        bloodGroupeLabel.setBounds(20, 50, 300, 30);
        bloodGroupeLabel.setBackground(Color.lightGray);
        bloodGroupeLabel.setForeground(Color.BLACK);
        Font labelFont = new Font("Varenda", Font.BOLD, 30);
        bloodGroupeLabel.setFont(labelFont);
        sellBloodPannel.add(bloodGroupeLabel);

        Choice selectBloodGroupeCoice = new Choice();
        selectBloodGroupeCoice.setBounds(20, 100, 90, 40);
         Font optionFont = new Font("Varenda", Font.BOLD, 14);
        selectBloodGroupeCoice.setFont(optionFont);
        selectBloodGroupeCoice.add("A");
        selectBloodGroupeCoice.add("B");
        selectBloodGroupeCoice.add("AB");
        selectBloodGroupeCoice.add("O");
        sellBloodPannel.add(selectBloodGroupeCoice);

        Choice selectBloodGroupePositiveOrNegaticveCoice = new Choice();
        selectBloodGroupePositiveOrNegaticveCoice.setBounds(130, 100, 90, 40);
        selectBloodGroupePositiveOrNegaticveCoice.setFont(optionFont);
        selectBloodGroupePositiveOrNegaticveCoice.add("+(ve)");
        selectBloodGroupePositiveOrNegaticveCoice.add("-(ve)");
        sellBloodPannel.add(selectBloodGroupePositiveOrNegaticveCoice);

        numOfBloodneededJLabel = new JLabel("Number of Blood needed:");

        numOfBloodneededJLabel.setBounds(20, 140, 400, 40);
        numOfBloodneededJLabel.setFont(labelFont);
        sellBloodPannel.add(numOfBloodneededJLabel);
        
        
         numberOfBloodPackWantToSell = new TextField();
         numberOfBloodPackWantToSell.setBounds(20,190,90,20);
         sellBloodPannel.add(numberOfBloodPackWantToSell);
         sellBloodSaveButton = new Button("sell");
         sellBloodSaveButton.setBounds(20, 230, 50, 30);
         sellBloodPannel.add(sellBloodSaveButton);
         sellBloodSaveButton.setVisible(true);
        
         
         
        

    }

    
}
