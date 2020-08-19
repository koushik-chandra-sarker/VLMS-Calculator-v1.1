package VLSM;

import com.gembox.spreadsheet.ExcelFile;
import com.gembox.spreadsheet.ExcelWorksheet;
import com.gembox.spreadsheet.SpreadsheetInfo;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.*;
import javafx.fxml.FXML;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.fxml.Initializable;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.imageio.IIOException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;
import static java.lang.Integer.parseInt;

public class VlsmFXMLController implements Initializable {

    @FXML
    private TableColumn subnetName;

    @FXML
    private TableColumn needSize;

    @FXML
    private TableColumn allocatedSize;

    @FXML
    private TableColumn address;

    @FXML
    private TableColumn mask;

    @FXML
    private TableColumn assignableRange;

    @FXML
    private TableColumn broadcast;

    @FXML
    private Button subnetbtn;

    @FXML
    private TextField subnetFIeld;

    @FXML
    private Button hostbtn;

    @FXML
    private TextField hostfield;

    @FXML
    private Button ipbtn;

    @FXML
    private TextField maskField;

    @FXML
    private TextField ipField;

    @FXML
    private Button submitbtn;

    @FXML
    private Label hostLabel;

    @FXML
    private Label massageLabel;

    @FXML
    private TableView mytable;

    @FXML
    private MaterialDesignIconView Close;

    @FXML
    private MaterialDesignIconView minimize;

    @FXML
    private Button clearbtn;

    @FXML
    private Button resetbtn;

    @FXML
    private Button save;

    private String ip;

    private int subnetMask;

    private int needSubnet;

    private ArrayList<Integer> RequiredHost = new ArrayList();

    int index = 0;

    int a = 2;

    String initialBinary;

    ObservableList<Person> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL event, ResourceBundle rb) {
        ipbtn.setDisable(true);
        subnetbtn.setDisable(true);
        hostbtn.setDisable(true);
        subnetFIeld.setDisable(true);
        submitbtn.setDisable(true);
        hostfield.setDisable(true);
        Collections.sort(RequiredHost);
    }

    @FXML
    private void ipbuttonAction(ActionEvent event) {
        String dot = ipField.getText();
        int countDot = 0;
        for (int i = 0; i < dot.length(); i++) {
            if (dot.charAt(i) == '.') {
                countDot++;
            }
        }
        if (countDot > 3 || countDot < 3) {
            massageLabel.setText("Invalid Ip Address. Enter a valid Ip Address.");
        } else {
            String s = ipField.getText();
            String[] s1 = s.split("\\.");
            int a = Integer.parseInt(s1[0]);
            int b = Integer.parseInt(s1[1]);
            int c = Integer.parseInt(s1[2]);
            int d = Integer.parseInt(s1[3]);
            int m = Integer.parseInt(maskField.getText());
            if (s.length() > 15 || a > 255 || a < 0 || b > 255 || b < 0 || c > 255 || c < 0 || d > 255 || d < 0) {
                massageLabel.setText("Invalid Ip Address. Enter a valid Ip Address.");
            } else if (m > 32 || m < 0) {
                massageLabel.setText("Invalid Subnet Mask. Enter a valid Mask");
            } else {
                ip = ipField.getText();
                subnetMask = Integer.parseInt(maskField.getText());
                subnetFIeld.setDisable(false);
                ipField.setDisable(true);
                maskField.setDisable(true);
                ipbtn.setDisable(true);
            }
        }
        massageLabel.setText("How Many Subnets You Need To Create? ");
    }

    @FXML
    private void FieldKeyReleased(KeyEvent event) {
        boolean ipbtnDisable, subnetbtnDisable, hostBtnDisable;
        ipbtnDisable = ipField.getText().isEmpty() || maskField.getText().isEmpty();
        ipbtn.setDisable(ipbtnDisable);
        subnetbtnDisable = subnetFIeld.getText().isEmpty();
        subnetbtn.setDisable(subnetbtnDisable);
        if (subnetbtnDisable == false) {
            ipbtn.setDisable(true);
        }
        hostBtnDisable = hostfield.getText().isEmpty();
        hostbtn.setDisable(hostBtnDisable);
        if (hostBtnDisable == false) {
            subnetbtn.setDisable(true);
        }
    }

    @FXML
    private void subnetButtonAction(ActionEvent event) {
        needSubnet = Integer.parseInt(subnetFIeld.getText());
        hostfield.setDisable(false);
        subnetbtn.setDisable(true);
        subnetFIeld.setDisable(true);
    }

    @FXML
    private void hostButtonAction(ActionEvent event) {
        int n = Integer.parseInt(hostfield.getText());
        RequiredHost.add(n);
        hostfield.clear();
        hostLabel.setText("How many Host need for Subnet number " + a + " :");
        if ((a - 1) == needSubnet) {
            hostfield.setDisable(true);
            hostbtn.setDisable(true);
            massageLabel.setText("Click Submit Button");
            submitbtn.setDisable(false);
        }
        a++;
    }

    @FXML
    private void submitAction(ActionEvent event) {
        subnetName.setCellValueFactory(new PropertyValueFactory<Person, String>("subnetName"));
        needSize.setCellValueFactory(new PropertyValueFactory<Person, Integer>("needSize"));
        allocatedSize.setCellValueFactory(new PropertyValueFactory<Person, Integer>("allocatedSize"));
        address.setCellValueFactory(new PropertyValueFactory<Person, String>("address"));
        mask.setCellValueFactory(new PropertyValueFactory<Person, String>("mask"));
        assignableRange.setCellValueFactory(new PropertyValueFactory<Person, String>("assignableRange"));
        broadcast.setCellValueFactory(new PropertyValueFactory<Person, String>("broadcast"));
        mytable.setItems(list);
        /*Person p = new Person("A",15,32,"17.3.5","52","subnetMask","155");
        list.add(p);*/
        char c = 'A';
        for (int i = 1; i <= needSubnet; i++) {
            int host = RequiredHost.get(0);
            // SubnetCalculator s = new SubnetCalculator();
            SubnetCalculator(subnetMask, ip, host);
            /* int new_mask = s.getNew_mask();
            String new_ip = s.getNew_ip();
            int allocatedSize = s.getAllocatedSize();
            int needSize = s.getNeedSize();
            String First_valid_Host = s.getFirst_valid_Host();
            String Last_valid_Host = s.getLast_valid_Host();
            String BroadcastAddress = s.getBroadcastAddress();*/
            String s1 = Character.toString(c);
            Person p = new Person(s1, NeedSize, AllocatedSize, new_ip, "/" + new_mask, First_valid_Host + " to " + Last_valid_Host, BroadcastAddress + "/" + new_mask);
            list.add(p);
            RequiredHost.remove(0);
            c++;
            massageLabel.setText("Subneting Successful.");
            boolean t = true;
            int x = 1;
            while (t != false) {
                subnetMask = this.SubnetMaskList.get(this.SubnetMaskList.size() - x);
                int y = (int) Math.pow(2, (32 - subnetMask));
                if (y < host) {
                    x++;
                    t = true;
                } else {
                    t = false;
                }
            }
            String Ip_binary = this.AvailableSubnet.get(this.AvailableSubnet.size() - x);
            Conversion con = new Conversion();
            con.binaryToIpConversion(Ip_binary);
            ip = con.getOutIP();
            if (i < needSubnet) {
                this.SubnetMaskList.remove(this.SubnetMaskList.size() - x);
                this.AvailableSubnet.remove(this.AvailableSubnet.size() - x);
            }
        }
        MakeFile m = new MakeFile();
        m.makeFile(AvailableSubnet, SubnetMaskList);
        submitbtn.setDisable(true);
        clearbtn.setDisable(true);
    }

    private int new_mask;

    private String new_ip;

    private int AllocatedSize;

    private int NeedSize;

    private String First_valid_Host;

    private String Last_valid_Host;

    private String BroadcastAddress;

    public ArrayList<String> AvailableSubnet = new ArrayList<String>();

    public ArrayList<Integer> SubnetMaskList = new ArrayList<Integer>();

    private void SubnetCalculator(int SubnetMask, String ip_in, int host) {
        String ip_In = ip_in;
        this.NeedSize = host;
        int subnetMask = SubnetMask;
        Conversion c = new Conversion();
        c.IpToBinaryConversion(ip_in);
        String initialBinary = c.getOutBinary();
        binaryGenerators b = new binaryGenerators(subnetMask, initialBinary, NeedSize);
        b.Generator();
        // Set Subnet to Database
        this.AvailableSubnet.addAll(b.subnetlist);
        // get subnet for required host
        String A = this.AvailableSubnet.get(this.AvailableSubnet.size() - 1);
        c.binaryToIpConversion(A);
        this.new_ip = c.getOutIP();
        this.AvailableSubnet.remove(this.AvailableSubnet.size() - 1);
        // Set Subnet mask to database
        this.SubnetMaskList.addAll(b.subnetMasklist);
        this.new_mask = this.SubnetMaskList.get(this.SubnetMaskList.size() - 1);
        this.SubnetMaskList.remove(this.SubnetMaskList.size() - 1);
        this.AllocatedSize = b.allocatedSize;
        HostCalculator h = new HostCalculator(new_ip, new_mask);
        this.First_valid_Host = h.firstAssignableHost;
        this.Last_valid_Host = h.lastAssignableHost;
        this.BroadcastAddress = h.BroadCast;
    /*System.out.println("subnet = "+new_ip);
        System.out.println("Subnet mask = "+new_mask);
        System.out.println("Required Size = "+host);
        System.out.println("Allocated Size = "+allocatedSize);
        System.out.println("First_valid_Host = "+First_valid_Host);
        System.out.println("Last_valid_Host = "+Last_valid_Host);
        System.out.println("BroadcastAddress = "+BroadcastAddress);*/
    // System.out.println(this.AvailableSubnet.size());
    }

    @FXML
    private void OtherSubnetViewAction(ActionEvent event) throws IOException {
        Parent OtherSubnetView = FXMLLoader.load(getClass().getResource("OtherSubnetViewFXML.fxml"));
        Scene scene2 = new Scene(OtherSubnetView);
        Stage stage2 = new Stage();
        stage2.setScene(scene2);
        stage2.initStyle(StageStyle.TRANSPARENT);
        stage2.show();
        // stage2.centerOnScreen();
        OtherSubnetViewFXMLController c = new OtherSubnetViewFXMLController();
    }

    public void setTable() {
        for (String s : AvailableSubnet) {
            System.out.println(s);
        }
    }

    @FXML
    private void MouseClicked(MouseEvent event) {
        // Windows Close
        if (event.getSource() == Close) {
            Stage stage = (Stage) ((MaterialDesignIconView) event.getSource()).getScene().getWindow();
            stage.close();
        }
        // Windows Minimize
        if (event.getSource() == minimize) {
            Stage stage = (Stage) ((MaterialDesignIconView) event.getSource()).getScene().getWindow();
            // stage.toBack();
            // or
            stage.setIconified(true);
        }
    }

    @FXML
    private void ClearAction(ActionEvent event) {
        ipField.setText("");
        ipField.setDisable(false);
        maskField.setText("");
        maskField.setDisable(false);
        subnetFIeld.setText("");
        subnetFIeld.setDisable(true);
        hostfield.setText("");
        hostfield.setDisable(true);
        subnetMask = 0;
        needSubnet = 0;
        RequiredHost.clear();
        hostLabel.setText("How many Host need for Subnet number " + 1 + " :");
    }

    @FXML
    private void ResetAction(ActionEvent event) {
        ipField.setText("");
        ipField.setDisable(false);
        maskField.setText("");
        maskField.setDisable(false);
        subnetFIeld.setText("");
        subnetFIeld.setDisable(true);
        hostfield.setText("");
        hostfield.setDisable(true);
        massageLabel.setText("Clear all Content.");
        subnetMask = 0;
        needSubnet = 0;
        RequiredHost.clear();
        AvailableSubnet.clear();
        SubnetMaskList.clear();
        ip = "";
        a = 2;
        hostLabel.setText("How many Host need for Subnet number " + 1 + " :");
        for (int i = 0; i < mytable.getItems().size(); i++) {
            mytable.getItems().clear();
        }
        // for clear file
        MakeFile m1 = new MakeFile();
        m1.makeFile(AvailableSubnet, SubnetMaskList);
    }

    static {
        SpreadsheetInfo.setLicense("FREE-LIMITED-KEY");
    }

    @FXML
    public void saveAction(ActionEvent event) throws IOException {
        subnetName.setCellValueFactory(new PropertyValueFactory<Person, String>("subnetName"));
        needSize.setCellValueFactory(new PropertyValueFactory<Person, Integer>("needSize"));
        allocatedSize.setCellValueFactory(new PropertyValueFactory<Person, Integer>("allocatedSize"));
        address.setCellValueFactory(new PropertyValueFactory<Person, String>("address"));
        mask.setCellValueFactory(new PropertyValueFactory<Person, String>("mask"));
        assignableRange.setCellValueFactory(new PropertyValueFactory<Person, String>("assignableRange"));
        broadcast.setCellValueFactory(new PropertyValueFactory<Person, String>("broadcast"));
        ExcelFile file = new ExcelFile();
        ExcelWorksheet worksheet = file.addWorksheet("sheet");
        for (int row = 0; row < mytable.getItems().size(); row++) {
            ObservableList cells = (ObservableList) mytable.getItems().get(row);
            for (int column = 0; column < cells.size(); column++) {
                if (cells.get(column) != null) {
                    worksheet.getCell(row, column).setValue(cells.get(column).toString());
                }
            }
        }
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("XLSX files(*.xlsx", "*.xlsx"), new FileChooser.ExtensionFilter("XLS files(*.xls", "*.xls"), new FileChooser.ExtensionFilter("ODS files(*.ods", "*.ods"), new FileChooser.ExtensionFilter("CSV files(*.csv", "*.csv"), new FileChooser.ExtensionFilter("HTML files(*.html", "*.html"));
        File saveFile = fileChooser.showSaveDialog(mytable.getScene().getWindow());
        file.save(saveFile.getAbsolutePath());
    }
}
