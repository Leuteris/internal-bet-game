package bet.model;

import bet.api.constants.OverResult;
import bet.api.constants.ScoreResult;
import lombok.Data;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ODD", schema = "BET")
@DynamicInsert
@DynamicUpdate
@Data
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region = "bet.entity-cache")
public class Odd implements Serializable {

	private static final long serialVersionUID = -5924099885411409739L;

	/**
	 * Database primary key - No business meaning
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@OneToOne
	@JoinColumn(name = "GAME_ID", referencedColumnName = "ID", nullable = false)
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region = "dev.entity-cache")
	private Game game;

	@Column(name = "ODDS_HOME")
	private float oddsHome;

	@Column(name = "ODDS_AWAY")
	private float oddsAway;

	@Column(name = "ODDS_TIE")
	private float oddsTie;

	@Column(name = "ODDS_OVER")
	private float oddsOver;

	@Column(name = "ODDS_UNDER")
	private float oddsUnder;

	public Odd() {
		super();
	}

	public Odd(int id, int gameId, float oddsHome, float oddsAway, float oddsTie, float oddsOver, float oddsUnder) {
		this.id = id;
		this.game = new Game(gameId);
		this.oddsHome = oddsHome;
		this.oddsAway = oddsAway;
		this.oddsTie = oddsTie;
		this.oddsOver = oddsOver;
		this.oddsUnder = oddsUnder;
	}

	@Transient
	public float getOddForScore(ScoreResult result) {
		switch (result) {
		case SCORE_1:
			return oddsHome;
		case SCORE_2:
			return oddsAway;
		case SCORE_X:
			return oddsTie;
		default:
			throw new RuntimeException("Invalid score result:" + result);
		}
	}

	@Transient
	public float getOddForOver(OverResult result) {
		switch (result) {
		case OVER:
			return oddsOver;
		case UNDER:
			return oddsUnder;
		default:
			throw new RuntimeException("Invalid over result:" + result);
		}
	}

	@Transient
	public int getMultiplier() {
		switch (game.getMatchDay()) {
		case 1:
		case 2:
		case 3:
			return 100;
		case 4:
		case 5:
		case 6:
			return 200;
		case 7:
		case 8:
			return 300;
		default:
			throw new RuntimeException("Invalida match day:" + game.getMatchDay());
		}
	}
}