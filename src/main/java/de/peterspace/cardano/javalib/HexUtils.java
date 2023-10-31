package de.peterspace.cardano.javalib;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import lombok.experimental.UtilityClass;

@UtilityClass
public class HexUtils {

	public String hexStringToString(String hexString) {
		try {
			return new String(Hex.decodeHex(hexString));
		} catch (DecoderException e) {
			throw new RuntimeException("Cannot decode " + hexString, e);
		}
	}

}
