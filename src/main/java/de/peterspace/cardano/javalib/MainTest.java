package de.peterspace.cardano.javalib;

import org.apache.commons.codec.binary.Hex;

import de.peterspace.cardano.javalib.Bech32.Bech32Data;

public class MainTest {
	public static void main(String[] args) {
		Bech32Data decode = Bech32.decode("stake1uxyt389wtpccs6t26f248d9qszgxmya2qc6a3k06jw2el9g42aktg");
		System.out.println(Hex.encodeHexString(Bech32.decode5to8(decode.data)));

		System.out.println(CardanoUtils.extractStakePart("addr1q9fqa7u2cjjf4p8pqdrte50j46a409u0t6e6qvd8v4qy876lg2cmpfw2jhgdasfgs8f9edxg3s52grrcz3klqwdqv7ysx3cv64"));
		System.out.println(CardanoUtils.extractStakePart("addr1q8xe0njqzeawr40ryjq5frruy7auw353dff267hge5elxvx3x8upc88rylduekqkc03z9exzrcfklqlqlqtmuzf62zjqlhjrsc"));
		System.out.println(CardanoUtils.extractStakePart("addr1q9kk37h6wnnkfe4asu5lvcg6vkw7qvxsu7xqz944xxla6zw3x8upc88rylduekqkc03z9exzrcfklqlqlqtmuzf62zjqqnecuk"));
		System.out.println(CardanoUtils.extractStakePart("addr1q8xe0njqzeawr40ryjq5frruy7auw353dff267hge5elxvx3x8upc88rylduekqkc03z9exzrcfklqlqlqtmuzf62zjqlhjrsc"));
	}

}
