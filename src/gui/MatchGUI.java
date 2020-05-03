/*
 * @author: Phạm Trung Hiếu
 * You can change this file's content at will
 * but please give credit to the original author.
 */
package gui;

import duelgame.Action;
import duelgame.DuelGame;
import duelgame.Match;
import duelgame.Player;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 */
public class MatchGUI implements Initializable {

	//<editor-fold defaultstate="collapsed" desc="FXML Declarations">
	@FXML
	private AnchorPane rootAnchorPane;
	@FXML
	private AnchorPane p1Pane;
	@FXML
	private AnchorPane p2Pane;
	@FXML
	private Label p1NameLabel;
	@FXML
	private Label p1ActionLabel;
	@FXML
	private Label p1LeftHpLabel;
	@FXML
	private Label p1RightHpLabel;
	@FXML
	private ChoiceBox<Action> p1LeftChoiceBox;
	@FXML
	private ChoiceBox<Action> p1RightChoiceBox;
	@FXML
	private Label p1LeftAtkLabel;
	@FXML
	private Label p1RightAtkLabel;
	@FXML
	private Label p1LeftDefLabel;
	@FXML
	private Label p1RightDefLabel;
	@FXML
	private Label p1WeaponLabel;
	@FXML
	private Label p1MountLabel;
	@FXML
	private ToggleButton p1WeaponToggleButton;
	@FXML
	private Label roundLabel;
	@FXML
	private Button roundFixButton;
	@FXML
	private Button previousRoundButton;
	@FXML
	private Button roundConfirmButton;
	@FXML
	private Button nextRoundButton;
	@FXML
	private Button matchInfoButton;
	@FXML
	private Button newMatchButton;
	@FXML
	private Label p2NameLabel;
	@FXML
	private Label p2ActionLabel;
	@FXML
	private Label p2LeftHpLabel;
	@FXML
	private Label p2RightHpLabel;
	@FXML
	private ChoiceBox<Action> p2LeftChoiceBox;
	@FXML
	private ChoiceBox<Action> p2RightChoiceBox;
	@FXML
	private Label p2LeftAtkLabel;
	@FXML
	private Label p2RightAtkLabel;
	@FXML
	private Label p2LeftDefLabel;
	@FXML
	private Label p2RightDefLabel;
	@FXML
	private Label p2WeaponLabel;
	@FXML
	private ToggleButton p2WeaponToggleButton;
	@FXML
	private Label p1BodyHpLabel;
	@FXML
	private ChoiceBox<Action> p1BodyChoiceBox;
	@FXML
	private Label p2BodyHpLabel;
	@FXML
	private ChoiceBox<Action> p2BodyChoiceBox;
	@FXML
	private ToggleButton p1MountToggleButton;
	@FXML
	private Label matchResultLabel;
	@FXML
	private Label p2MountLabel;
	@FXML
	private ToggleButton p2MountToggleButton;
	//</editor-fold>

	private Match match() {
		return DuelGame.getMatch();
	}
	
	private int currentRound;
	
	public void openNewMatch() throws IOException {
		DuelGame.setTempNewMatch(new Match());
		
		Stage stage = new Stage();
		URL resource = getClass().getResource("MatchCreation.fxml");
		Parent root = FXMLLoader.load(resource);	
		Scene scene = new Scene(root, 600, 352);
		
		stage.setTitle("Trận mới");
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setX(newMatchButton.getScene().getX() + 200);
		stage.setY(newMatchButton.getScene().getY() + 200);
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(newMatchButton.getScene().getWindow());
		
		stage.show();
		stage.setOnHidden((e) -> {
			if (DuelGame.isNewMatchReady()) {
				showMatch();
				DuelGame.setNewMatchReady(false);
			}
		});
	}
	
	public void showMatch() {
		p1NameLabel.setText(match().getPlayer1().getName());
		p1WeaponLabel.setText(match().getPlayer1().getWeapon().getNAME());
		p1MountLabel.setText(match().getPlayer1().getMount().getNAME());
		p2NameLabel.setText(match().getPlayer2().getName());
		p2WeaponLabel.setText(match().getPlayer2().getWeapon().getNAME());
		p2MountLabel.setText(match().getPlayer2().getMount().getNAME());
		
		match().addNewRound();
		match().toNextRound(new Action[] {Action.DO_NOTHING, Action.DO_NOTHING, Action.DO_NOTHING}, new Action[] {Action.DO_NOTHING, Action.DO_NOTHING, Action.DO_NOTHING});
		showRound(1, false);
	}
	
	public void showRound(int roundIndex, boolean isFinished) {
		currentRound = roundIndex;		
		setInput(!isFinished);
		roundConfirmButton.setDisable(match().isFinished() ? true : isFinished);
		roundFixButton.setDisable(roundIndex < match().getRounds().size() - 1 ? roundIndex == 0 : !isFinished);
		previousRoundButton.setDisable(roundIndex == 0);
		nextRoundButton.setDisable(roundIndex == match().getRounds().size() - 1 ? (!isFinished || match().isFinished()) : false);
		
		Player player1, player2;
		if (!isFinished) {
			player1 = match().getRounds().get(roundIndex).getPlayer1();
			player2 = match().getRounds().get(roundIndex).getPlayer2();
			
			p1LeftChoiceBox.getItems().clear();
			if (!player1.isDisabledLeft()) {
				p1LeftChoiceBox.getItems().addAll(Arrays.asList(Action.values()));
				if (!Action.DO_NOTHING.equals(player1.getActions()[0]))
					p1LeftChoiceBox.getItems().remove(player1.getActions()[0]);
				p1LeftChoiceBox.setValue(Action.DO_NOTHING);
			} else {
				p1LeftChoiceBox.setDisable(true);
				p1LeftChoiceBox.getItems().add(Action.DO_NOTHING);
				p1LeftChoiceBox.setValue(Action.DO_NOTHING);
			}
			
			p1BodyChoiceBox.getItems().clear();
			p1BodyChoiceBox.getItems().addAll(Action.DO_NOTHING, Action.DODGE);
			if (!Action.DO_NOTHING.equals(player1.getActions()[1]))
				p1BodyChoiceBox.getItems().remove(player1.getActions()[1]);
			p1BodyChoiceBox.setValue(Action.DO_NOTHING);
			
			p1RightChoiceBox.getItems().clear();
			if (!player1.isDisabledRight()) {
				p1RightChoiceBox.getItems().addAll(Arrays.asList(Action.values()));
				if (!Action.DO_NOTHING.equals(player1.getActions()[2]))
					p1RightChoiceBox.getItems().remove(player1.getActions()[2]);
				p1RightChoiceBox.setValue(Action.DO_NOTHING);
			} else {
				p1RightChoiceBox.setDisable(true);
				p1RightChoiceBox.getItems().add(Action.DO_NOTHING);
				p1RightChoiceBox.setValue(Action.DO_NOTHING);
			}
			
			p2LeftChoiceBox.getItems().clear();
			if (!player2.isDisabledLeft()) {
				p2LeftChoiceBox.getItems().addAll(Arrays.asList(Action.values()));
				if (!Action.DO_NOTHING.equals(player2.getActions()[0]))
					p2LeftChoiceBox.getItems().remove(player2.getActions()[0]);
				p2LeftChoiceBox.setValue(Action.DO_NOTHING);
			} else {
				p2LeftChoiceBox.setDisable(true);
				p2LeftChoiceBox.getItems().add(Action.DO_NOTHING);
				p2LeftChoiceBox.setValue(Action.DO_NOTHING);
			}
			
			p2BodyChoiceBox.getItems().clear();
			p2BodyChoiceBox.getItems().addAll(Action.DO_NOTHING, Action.DODGE);
			if (!Action.DO_NOTHING.equals(player2.getActions()[1]))
				p2BodyChoiceBox.getItems().remove(player2.getActions()[1]);
			p2BodyChoiceBox.setValue(Action.DO_NOTHING);
			
			p2RightChoiceBox.getItems().clear();
			if (!player2.isDisabledRight()) {
				p2RightChoiceBox.getItems().addAll(Arrays.asList(Action.values()));
				if (!Action.DO_NOTHING.equals(player2.getActions()[2]))
					p2RightChoiceBox.getItems().remove(player2.getActions()[2]);
				p2RightChoiceBox.setValue(Action.DO_NOTHING);
			} else {
				p2RightChoiceBox.setDisable(true);
				p2RightChoiceBox.getItems().add(Action.DO_NOTHING);
				p2RightChoiceBox.setValue(Action.DO_NOTHING);
			}
		} else {
			player1 = match().getRounds().get(roundIndex).newRoundPlayers()[0];
			player2 = match().getRounds().get(roundIndex).newRoundPlayers()[1];
			p1LeftChoiceBox.setValue(player1.getActions()[0]);
			p1BodyChoiceBox.setValue(player1.getActions()[1]);
			p1RightChoiceBox.setValue(player1.getActions()[2]);
		}
		
		roundLabel.setText(String.format("Hiệp %d", roundIndex));
		p1LeftHpLabel.setText(String.format("%d", player1.getLeftHp()));
		p1BodyHpLabel.setText(String.format("%d", player1.getBodyHp()));
		p1RightHpLabel.setText(String.format("%d", player1.getRightHp()));
		p1ActionLabel.setText(String.format("%d", player1.getActionCount()));
		p1LeftAtkLabel.setText(String.format("%d", player1.getLeftAtk()));
		p1LeftDefLabel.setText(String.format("%d", player1.getLeftDef()));
		p1RightAtkLabel.setText(String.format("%d", player1.getRightAtk()));
		p1RightDefLabel.setText(String.format("%d", player1.getRightDef()));
		p2LeftHpLabel.setText(String.format("%d", player2.getLeftHp()));
		p2BodyHpLabel.setText(String.format("%d", player2.getBodyHp()));
		p2RightHpLabel.setText(String.format("%d", player2.getRightHp()));
		p2ActionLabel.setText(String.format("%d", player2.getActionCount()));
		p2LeftAtkLabel.setText(String.format("%d", player2.getLeftAtk()));
		p2LeftDefLabel.setText(String.format("%d", player2.getLeftDef()));
		p2RightAtkLabel.setText(String.format("%d", player2.getRightAtk()));
		p2RightDefLabel.setText(String.format("%d", player2.getRightDef()));		
	}
	
	public void showPreviousRound() {
		showRound(currentRound - 1, true);
	}
	
	public void showNextRound() {
		if (currentRound < match().getRounds().size() - 2) {
			showRound(currentRound + 1, true);
		} else {
			showRound(currentRound + 1, match().isFinished());
		}
	}
	
	public void fixRound() {
		match().fixOldRound(currentRound);
		showRound(currentRound, false);
		matchResultLabel.setText("");
	}
	
	public void confirmRound() {
		match().toNextRound(
				new Action[] {p1LeftChoiceBox.getValue(), p1BodyChoiceBox.getValue(), p1RightChoiceBox.getValue()},
				new Action[] {p2LeftChoiceBox.getValue(), p2BodyChoiceBox.getValue(), p2RightChoiceBox.getValue()});
		checkMatchResult();
		showRound(currentRound, true);
	}
	
	public void checkMatchResult() {
		switch (match().result()) {
			case 0:
				matchResultLabel.setText(String.format(""));
				setInput(true);
				break;
			case 1:
				matchResultLabel.setText(String.format("%s thắng!", match().getPlayer1().getName()));
				setInput(false);
				break;
			case 2:
				matchResultLabel.setText(String.format("%s thắng!", match().getPlayer2().getName()));
				setInput(false);
				break;
			case -1:
				matchResultLabel.setText(String.format("Hoà!"));
				setInput(false);
				break;
		}
	}
	
	public void setInput(boolean bln) {
		p1LeftChoiceBox.setDisable(!bln);
		p1BodyChoiceBox.setDisable(!bln);
		p1RightChoiceBox.setDisable(!bln);
		p1WeaponToggleButton.setDisable(!bln);
		p1MountToggleButton.setDisable(!bln);
		p2LeftChoiceBox.setDisable(!bln);
		p2BodyChoiceBox.setDisable(!bln);
		p2RightChoiceBox.setDisable(!bln);
		p2WeaponToggleButton.setDisable(!bln);
		p2MountToggleButton.setDisable(!bln);
	}
	
	public void checkP1LeftAction() {
		Action a = p1LeftChoiceBox.getValue();
		if (match().getPlayer1().getActions()[0].equals(a) && !Action.DO_NOTHING.equals(a)) {
			p1LeftChoiceBox.setStyle("-fx-text-fill: red");
		} else
			p1LeftChoiceBox.setStyle("");
		if (Action.DODGE.equals(a)) {
			if (Action.DODGE.equals(p1BodyChoiceBox.getValue()))
				p1BodyChoiceBox.setValue(Action.DO_NOTHING);
			if (Action.DODGE.equals(p1RightChoiceBox.getValue()))
				p1RightChoiceBox.setValue(Action.DO_NOTHING);
		}
	}
	
	public void checkP1BodyAction() {
		Action a = p1BodyChoiceBox.getValue();
		if (match().getPlayer1().getActions()[1].equals(a) && !Action.DO_NOTHING.equals(a)) {
			p1BodyChoiceBox.setStyle("-fx-text-fill: red");
		} else
			p1BodyChoiceBox.setStyle("");
		if (Action.DODGE.equals(a)) {
			if (Action.DODGE.equals(p1LeftChoiceBox.getValue()))
				p1LeftChoiceBox.setValue(Action.DO_NOTHING);
			if (Action.DODGE.equals(p1RightChoiceBox.getValue()))
				p1RightChoiceBox.setValue(Action.DO_NOTHING);
		}
	}
	
	public void checkP1RightAction() {
		Action a = p1RightChoiceBox.getValue();
		if (match().getPlayer1().getActions()[2].equals(a) && !Action.DO_NOTHING.equals(a)) {
			p1RightChoiceBox.setStyle("-fx-text-fill: red");
		} else
			p1RightChoiceBox.setStyle("");
		if (Action.DODGE.equals(a)) {
			if (Action.DODGE.equals(p1BodyChoiceBox.getValue()))
				p1BodyChoiceBox.setValue(Action.DO_NOTHING);
			if (Action.DODGE.equals(p1LeftChoiceBox.getValue()))
				p1LeftChoiceBox.setValue(Action.DO_NOTHING);
		}
	}
	
	public void checkP2LeftAction() {
		Action a = p2LeftChoiceBox.getValue();
		if (match().getPlayer2().getActions()[0].equals(a) && !Action.DO_NOTHING.equals(a)) {
			p2LeftChoiceBox.setStyle("-fx-text-fill: red");
		} else
			p2LeftChoiceBox.setStyle("");
		if (Action.DODGE.equals(a)) {
			if (Action.DODGE.equals(p2BodyChoiceBox.getValue()))
				p2BodyChoiceBox.setValue(Action.DO_NOTHING);
			if (Action.DODGE.equals(p2RightChoiceBox.getValue()))
				p2RightChoiceBox.setValue(Action.DO_NOTHING);
		}
	}
	
	public void checkP2BodyAction() {
		Action a = p2BodyChoiceBox.getValue();
		if (match().getPlayer2().getActions()[1].equals(a) && !Action.DO_NOTHING.equals(a)) {
			p2BodyChoiceBox.setStyle("-fx-text-fill: red");
		} else
			p2BodyChoiceBox.setStyle("");
		if (Action.DODGE.equals(a)) {
			if (Action.DODGE.equals(p2LeftChoiceBox.getValue()))
				p2LeftChoiceBox.setValue(Action.DO_NOTHING);
			if (Action.DODGE.equals(p2RightChoiceBox.getValue()))
				p2RightChoiceBox.setValue(Action.DO_NOTHING);
		}
	}
	
	public void checkP2RightAction() {
		Action a = p2RightChoiceBox.getValue();
		if (match().getPlayer2().getActions()[2].equals(a) && !Action.DO_NOTHING.equals(a)) {
			p2RightChoiceBox.setStyle("-fx-text-fill: red");
		} else
			p2RightChoiceBox.setStyle("");
		if (Action.DODGE.equals(a)) {
			if (Action.DODGE.equals(p2BodyChoiceBox.getValue()))
				p2BodyChoiceBox.setValue(Action.DO_NOTHING);
			if (Action.DODGE.equals(p2LeftChoiceBox.getValue()))
				p2LeftChoiceBox.setValue(Action.DO_NOTHING);
		}
	}
	
	public void checkActionsConfirmable() {
		roundConfirmButton.setDisable(
				(match().getPlayer1().getActions()[0].equals(p1LeftChoiceBox.getValue()) && !Action.DO_NOTHING.equals(p1LeftChoiceBox.getValue()))
			||	(match().getPlayer1().getActions()[1].equals(p1BodyChoiceBox.getValue()) && !Action.DO_NOTHING.equals(p1BodyChoiceBox.getValue()))
			||	(match().getPlayer1().getActions()[2].equals(p1RightChoiceBox.getValue()) && !Action.DO_NOTHING.equals(p1RightChoiceBox.getValue()))
			||	(match().getPlayer2().getActions()[0].equals(p2LeftChoiceBox.getValue()) && !Action.DO_NOTHING.equals(p2LeftChoiceBox.getValue()))
			||	(match().getPlayer2().getActions()[1].equals(p2BodyChoiceBox.getValue()) && !Action.DO_NOTHING.equals(p2BodyChoiceBox.getValue()))
			||	(match().getPlayer2().getActions()[2].equals(p2RightChoiceBox.getValue()) && !Action.DO_NOTHING.equals(p2RightChoiceBox.getValue()))	
		);
	}
	
	/**
	 * Initializes the controller class.
	 * @param url
	 * @param rb
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		setInput(false);
		roundConfirmButton.setDisable(true);
		
		newMatchButton.setOnAction((e) -> {
			try {
				openNewMatch();
			} catch (IOException ex) {
				Logger.getLogger(MatchGUI.class.getName()).log(Level.SEVERE, null, ex);
			}
		});
		
		p1LeftChoiceBox.valueProperty().addListener((v, o, n) -> { checkP1LeftAction(); });
		p1BodyChoiceBox.valueProperty().addListener((v, o, n) -> { checkP1BodyAction(); });
		p1RightChoiceBox.valueProperty().addListener((v, o, n) -> { checkP1RightAction(); });
		p2LeftChoiceBox.valueProperty().addListener((v, o, n) -> { checkP2LeftAction(); });
		p2BodyChoiceBox.valueProperty().addListener((v, o, n) -> { checkP2BodyAction(); });
		p2RightChoiceBox.valueProperty().addListener((v, o, n) -> { checkP2RightAction(); });
		
		roundConfirmButton.setOnAction((e) -> confirmRound());
		roundFixButton.setOnAction((e) -> fixRound());
		previousRoundButton.setOnAction((e) -> showPreviousRound());
		nextRoundButton.setOnAction((e) -> showNextRound());
	}
	
}
