/*
 * @author: Phạm Trung Hiếu
 * You can change this file's content at will
 * but please give credit to the original author.
 */
package gui;

import duelgame.mount.*;
import duelgame.weapon.*;
import duelgame.*;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 */
public class MatchCreation implements Initializable {

	//<editor-fold defaultstate="collapsed" desc="FXML Declarations">
	@FXML
	private AnchorPane player1Pane;
	@FXML
	private TextField p1NameField;
	@FXML
	private Label p1LeftAtkLabel;
	@FXML
	private Label p1RightAtkLabel;
	@FXML
	private Label p1RightDefLabel;
	@FXML
	private Label p1LeftDefLabel;
	@FXML
	private Slider p1AtkSlider;
	@FXML
	private Slider p1DefSlider;
	@FXML
	private ChoiceBox<Weapons> p1WeaponCB;
	@FXML
	private ChoiceBox<Mounts> p1MountCB;
	@FXML
	private Button p1WeaponButton;
	@FXML
	private Button p1MountButton;
	@FXML
	private AnchorPane matchPane;
	@FXML
	private Spinner<Integer> roundNumberSpinner;
	@FXML
	private Spinner<Integer> roundAddedSpinner;
	@FXML
	private ToggleButton hpCheckTB;
	@FXML
	private ToggleButton bodyHpCheckTB;
	@FXML
	private ToggleButton itemsLevelCheckTB;
	@FXML
	private ToggleButton actionsCheckTB;
	@FXML
	private ToggleButton weaponAllowedTB;
	@FXML
	private ToggleButton mountAllowedTB;
	@FXML
	private AnchorPane player2Pane;
	@FXML
	private TextField p2NameField;
	@FXML
	private Label p2LeftAtkLabel;
	@FXML
	private Label p2RightAtkLabel;
	@FXML
	private Label p2RightDefLabel;
	@FXML
	private Label p2LeftDefLabel;
	@FXML
	private Slider p2AtkSlider;
	@FXML
	private Slider p2DefSlider;
	@FXML
	private ChoiceBox<Weapons> p2WeaponCB;
	@FXML
	private ChoiceBox<Mounts> p2MountCB;
	@FXML
	private Button p2MountButton;
	@FXML
	private Button p2WeaponButton;
	@FXML
	private Button confirmButton;
	@FXML
	private Button closeButton;
	//</editor-fold>
	
	private Match match() {
		return DuelGame.getMatch();
	}
	
	private Match newMatch() {
		return DuelGame.getTempNewMatch();
	}
	
	private void setMatch() {
		try {
			Player p1 = new Player();
			Player p2 = new Player();
			
			p1.initiate(p1NameField.getText(), (int) p1AtkSlider.getValue(), (int) p1DefSlider.getValue(), p1WeaponCB.getValue().create(), p1MountCB.getValue().create());
			p2.initiate(p2NameField.getText(), (int) p2AtkSlider.getValue(), (int) p2DefSlider.getValue(), p2WeaponCB.getValue().create(), p2MountCB.getValue().create());
			
			newMatch().setPlayer1(p1);
			newMatch().setPlayer2(p2);
			
			DuelGame.setMatch(newMatch());
		} catch (NoSuchMethodException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
			Logger.getLogger(MatchCreation.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public void updateRoundNumber() {
		newMatch().setRoundNumber(roundNumberSpinner.getValue());
	}
	
	public void updateRoundAdded() {
		newMatch().setRoundAdded(roundAddedSpinner.getValue());
	}
	
	public void updateAtkDef(Slider slider) {		
		if (p1AtkSlider.equals(slider)) {
			p1RightAtkLabel.setText(String.format("%d", (int) slider.getValue()));
			p1LeftAtkLabel.setText(String.format("%d", (int) (slider.getMax() + slider.getMin() - slider.getValue())));
		}
		if (p1DefSlider.equals(slider)) {
			p1RightDefLabel.setText(String.format("%d", (int) slider.getValue()));
			p1LeftDefLabel.setText(String.format("%d", (int) (slider.getMax() + slider.getMin() - slider.getValue())));
		}
		if (p2AtkSlider.equals(slider)) {
			p2RightAtkLabel.setText(String.format("%d", (int) slider.getValue()));
			p2LeftAtkLabel.setText(String.format("%d", (int) (slider.getMax() + slider.getMin() - slider.getValue())));
		}
		if (p2DefSlider.equals(slider)) {
			p2RightDefLabel.setText(String.format("%d", (int) slider.getValue()));
			p2LeftDefLabel.setText(String.format("%d", (int) (slider.getMax() + slider.getMin() - slider.getValue())));
		}
	}
	
	public void updateIfWeaponAllowed() {
		if (newMatch().isWeaponsAllowed()) {
			p1WeaponCB.setDisable(false);
			p1WeaponButton.setDisable(false);
			p1WeaponCB.getItems().addAll(Arrays.asList(Weapons.values()));
			p1WeaponCB.setValue(p1WeaponCB.getItems().get(0));
			p2WeaponCB.setDisable(false);
			p2WeaponButton.setDisable(false);
			p2WeaponCB.getItems().addAll(Arrays.asList(Weapons.values()));
			p2WeaponCB.setValue(p2WeaponCB.getItems().get(0));
		} else {
			p1WeaponCB.setDisable(true);
			p1WeaponButton.setDisable(true);
			p2WeaponCB.setDisable(true);
			p2WeaponButton.setDisable(true);
			p1WeaponCB.getItems().clear();
			p2WeaponCB.getItems().clear();
			p1WeaponCB.setValue(Weapons.NO_WEAPON);
			p2WeaponCB.setValue(Weapons.NO_WEAPON);
		}
	}
	
	public void updateIfMountAllowed() {
		if (newMatch().isMountsAllowed()) {
			p1MountCB.setDisable(false);
			p1MountButton.setDisable(false);
			p1MountCB.getItems().addAll(Arrays.asList(Mounts.values()));
			p1MountCB.setValue(p1MountCB.getItems().get(0));
			p2MountCB.setDisable(false);
			p2MountButton.setDisable(false);
			p2MountCB.getItems().addAll(Arrays.asList(Mounts.values()));
			p2MountCB.setValue(p2MountCB.getItems().get(0));
		} else {
			p1MountCB.setDisable(true);
			p1MountButton.setDisable(true);
			p2MountCB.setDisable(true);
			p2MountButton.setDisable(true);
			p1MountCB.getItems().clear();
			p2MountCB.getItems().clear();
			p1MountCB.setValue(Mounts.NO_MOUNT);
			p2MountCB.setValue(Mounts.NO_MOUNT);
		}
	}

	/**
	 * Initializes the controller class.
	 * @param url
	 * @param rb
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		roundNumberSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(Match.getMIN_ROUNDS_NUM(), Match.getMAX_ROUNDS_NUM(), Match.getDEFAULT_ROUNDS_NUM()));
		roundNumberSpinner.getValueFactory().setWrapAround(true);
		roundNumberSpinner.requestFocus();
		roundAddedSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(Match.getMIN_ROUNDS_ADDED(), Match.getMAX_ROUNDS_ADDED(), Match.getDEFAULT_ROUNDS_ADDED()));
		roundAddedSpinner.getValueFactory().setWrapAround(true);
		weaponAllowedTB.setSelected(newMatch().isWeaponsAllowed());
		mountAllowedTB.setSelected(newMatch().isMountsAllowed());
		hpCheckTB.setSelected(newMatch().getMatchDecisionCriteria()[0]);
		bodyHpCheckTB.setSelected(newMatch().getMatchDecisionCriteria()[1]);
		itemsLevelCheckTB.setSelected(newMatch().getMatchDecisionCriteria()[2]);
		actionsCheckTB.setSelected(newMatch().getMatchDecisionCriteria()[3]);
		
		weaponAllowedTB.setOnAction((e) -> { newMatch().setWeaponsAllowed(weaponAllowedTB.isSelected()); updateIfWeaponAllowed(); });
		mountAllowedTB.setOnAction((e) -> { newMatch().setMountsAllowed(mountAllowedTB.isSelected()); updateIfMountAllowed(); });
		
		p1AtkSlider.setMin(Player.getMIN_ATK_DEF());
		p1AtkSlider.setMax(Player.getBASIC_ATK() - Player.getMIN_ATK_DEF());
		p1AtkSlider.setValue((int) (Player.getBASIC_ATK() / 2));
		p1RightAtkLabel.setText(String.format("%d", (int) p1AtkSlider.getValue()));
		p1LeftAtkLabel.setText(String.format("%d", (int) (Player.getBASIC_ATK() - p1AtkSlider.getValue())));
		p1DefSlider.setMin(Player.getMIN_ATK_DEF());
		p1DefSlider.setMax(Player.getBASIC_DEF() - Player.getMIN_ATK_DEF());
		p1DefSlider.setValue((int) (Player.getBASIC_DEF() / 2));
		p1RightDefLabel.setText(String.format("%d", (int) p1DefSlider.getValue()));
		p1LeftDefLabel.setText(String.format("%d", (int) (Player.getBASIC_DEF() - p1DefSlider.getValue())));
		p2AtkSlider.setMin(Player.getMIN_ATK_DEF());
		p2AtkSlider.setMax(Player.getBASIC_ATK() - Player.getMIN_ATK_DEF());
		p2AtkSlider.setValue((int) (Player.getBASIC_ATK() / 2));
		p2RightAtkLabel.setText(String.format("%d", (int) p2AtkSlider.getValue()));
		p2LeftAtkLabel.setText(String.format("%d", (int) (Player.getBASIC_ATK() - p2AtkSlider.getValue())));
		p2DefSlider.setMin(Player.getMIN_ATK_DEF());
		p2DefSlider.setMax(Player.getBASIC_DEF() - Player.getMIN_ATK_DEF());
		p2DefSlider.setValue((int) (Player.getBASIC_DEF() / 2));
		p2RightDefLabel.setText(String.format("%d", (int) p2DefSlider.getValue()));
		p2LeftDefLabel.setText(String.format("%d", (int) (Player.getBASIC_DEF() - p2DefSlider.getValue())));
		
		updateIfWeaponAllowed();
		updateIfMountAllowed();
		
		confirmButton.setOnAction((e) -> { setMatch(); DuelGame.setNewMatchReady(true); confirmButton.getScene().getWindow().hide(); });
		closeButton.setOnAction((e) -> { DuelGame.setNewMatchReady(false); closeButton.getScene().getWindow().hide(); });
		roundNumberSpinner.getValueFactory().valueProperty().addListener((v, o, n) -> { updateRoundNumber(); });
		roundAddedSpinner.getValueFactory().valueProperty().addListener((v, o, n) -> { updateRoundAdded(); });
		p1AtkSlider.valueProperty().addListener((v, o, n) -> { p1AtkSlider.setValue(n.intValue()); updateAtkDef(p1AtkSlider); });
		p1DefSlider.valueProperty().addListener((v, o, n) -> { p1DefSlider.setValue(n.intValue()); updateAtkDef(p1DefSlider); });
		p2AtkSlider.valueProperty().addListener((v, o, n) -> { p2AtkSlider.setValue(n.intValue()); updateAtkDef(p2AtkSlider); });
		p2DefSlider.valueProperty().addListener((v, o, n) -> { p2DefSlider.setValue(n.intValue()); updateAtkDef(p2DefSlider); });
	}
}
