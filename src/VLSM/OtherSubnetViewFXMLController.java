package VLSM;

import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.*;
import javafx.fxml.FXML;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

public class OtherSubnetViewFXMLController extends VlsmFXMLController implements Initializable {

    @FXML
    private TableColumn broadcast;

    @FXML
    private TableColumn assignableRange;

    @FXML
    private TableColumn mask;

    @FXML
    private TableColumn address;

    @FXML
    private TableView mytable;

    @FXML
    private TableColumn needSize;

    @FXML
    private TableColumn allocatedSize;

    @FXML
    private TableColumn SubnetNumber;

    @FXML
    private MaterialDesignIconView Close;

    @FXML
    private MaterialDesignIconView minimize;

    private ArrayList<String> SubnetList = new ArrayList<String>();

    private ArrayList<Integer> SubnetMaskList = new ArrayList<Integer>();

    private String First_valid_Host;

    private String Last_valid_Host;

    private String BroadcastAddress;

    private int AllocatedSize;

    ObservableList<Person> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL event, ResourceBundle rb) {
        getSubnetList();
        getSubnetMaskList();
        SubnetNumber.setCellValueFactory(new PropertyValueFactory<Person, String>("SubnetName"));
        allocatedSize.setCellValueFactory(new PropertyValueFactory<Person, Integer>("allocatedSize"));
        address.setCellValueFactory(new PropertyValueFactory<Person, String>("address"));
        mask.setCellValueFactory(new PropertyValueFactory<Person, String>("mask"));
        assignableRange.setCellValueFactory(new PropertyValueFactory<Person, String>("assignableRange"));
        broadcast.setCellValueFactory(new PropertyValueFactory<Person, String>("broadcast"));
        mytable.setItems(list);
        for (int i = 0; i <= SubnetList.size() - 1; i++) {
            String Ip_binary = SubnetList.get(i);
            int subnetMask = SubnetMaskList.get(i);
            Conversion c1 = new Conversion();
            c1.binaryToIpConversion(Ip_binary);
            String Ip = c1.getOutIP();
            HostCalculator h = new HostCalculator(Ip, subnetMask);
            this.First_valid_Host = h.firstAssignableHost;
            this.Last_valid_Host = h.lastAssignableHost;
            this.BroadcastAddress = h.BroadCast;
            this.AllocatedSize = (int) Math.pow(2, (32 - subnetMask));
            String st = String.valueOf((i + 1));
            Person p = new Person(st, 0, AllocatedSize, Ip, "/" + subnetMask, First_valid_Host + " to " + Last_valid_Host, BroadcastAddress + "/" + subnetMask);
            list.add(p);
        }
    }

    private void getSubnetList() {
        Scanner s = null;
        try {
            s = new Scanner(new File("totalSubnet.txt"));
            while (s.hasNext()) {
                SubnetList.add(s.next());
            }
            s.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void getSubnetMaskList() {
        Scanner sc = null;
        try {
            sc = new Scanner(new File("totalSubnetMask.txt"));
            while (sc.hasNext()) {
                SubnetMaskList.add(sc.nextInt());
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void MouseClicked(MouseEvent event) {
        if (event.getSource() == Close) {
            Stage stage = (Stage) ((MaterialDesignIconView) event.getSource()).getScene().getWindow();
            stage.close();
        }
        if (event.getSource() == minimize) {
            Stage stage = (Stage) ((MaterialDesignIconView) event.getSource()).getScene().getWindow();
            stage.toBack();
        }
    }
}
