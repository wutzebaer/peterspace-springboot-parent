package de.peterspace.cardano.javalib;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CardanoUtilsTest {

	@Test
	void stakeToHash() {
		assertThat(CardanoUtils.stakeToHash("stake1u8wmu7jc0e4a6fn5haflczfjy6aagwhsxh6w5p7hsyt8jeshhy0rn"))
				.isEqualTo("e1ddbe7a587e6bdd2674bf53fc093226bbd43af035f4ea07d781167966");
	}

	@Test
	void hashToStake() {
		assertThat(CardanoUtils.hashToStake("e1ddbe7a587e6bdd2674bf53fc093226bbd43af035f4ea07d781167966"))
				.isEqualTo("stake1u8wmu7jc0e4a6fn5haflczfjy6aagwhsxh6w5p7hsyt8jeshhy0rn");
	}

	@Test
	void extractStakePart() {
		assertThat(CardanoUtils.extractStakePart("addr1qx2k7ynqllfqv3uvnureg70n37h5kuavqryf20mvcm0v9kxahea9slntm5n8f06nlsynyf4m6sa0qd05agra0qgk09nqjm5fxn"))
				.isEqualTo("stake1u8wmu7jc0e4a6fn5haflczfjy6aagwhsxh6w5p7hsyt8jeshhy0rn");
	}

}
