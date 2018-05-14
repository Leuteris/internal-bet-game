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
@Table(name = "ALLOWED_USERS", schema = "BET")
@DynamicInsert
@DynamicUpdate
@Data
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region = "bet.entity-cache")
public class User implements Serializable {

	private static final long serialVersionUID = -5924099885411409739L;

	/**
	 * Database primary key - No business meaning
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "NAME")
	private String name;

	public User() {
		super();
	}

	public User(int id) {
		this.id = id;
	}

	public User(String name) {
		this.name = name;
	}

}