package de.peterspace.cardano.javalib;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import de.peterspace.cardano.javalib.Bech32.Bech32Data;
import de.peterspace.cardano.javalib.Bech32.Encoding;
import lombok.experimental.UtilityClass;

//@UtilityClass
public class CardanoUtils {

	public static void main(String[] args) {
		String addr = "addr1qx2k7ynqllfqv3uvnureg70n37h5kuavqryf20mvcm0v9kxahea9slntm5n8f06nlsynyf4m6sa0qd05agra0qgk09nqjm5fxn";
		Bech32Data decoded = Bech32.decode(addr);
		byte[] base8Data = Bech32.decode5to8(decoded.data);
		System.out.println(Hex.encodeHexString(base8Data));
	}

	public static String stakeToHash(String stakeAddress) {
		Bech32Data decode = Bech32.decode(stakeAddress);
		return Hex.encodeHexString(Bech32.decode5to8(decode.data));
	}

	public static String hashToStake(String stakeAddressHash) {
		try {
			byte[] data = Bech32.encode8to5(Hex.decodeHex(stakeAddressHash));
			return Bech32.encode(Encoding.BECH32, "stake", data);
		} catch (DecoderException e) {
			throw new RuntimeException(e);
		}
	}

	public static String extractPaymentHash(String shellyAddress) {

		if (determineAddressType(shellyAddress) != AddressType.SHELLY_ADDRESS) {
			return shellyAddress;
		}

		Bech32Data decoded = Bech32.decode(shellyAddress);
		byte[] base8Data = Bech32.decode5to8(decoded.data);

		int keyLength = 28;
		byte[] keyBytes = new byte[keyLength];
		System.arraycopy(base8Data, 1, keyBytes, 0, keyLength);

		return Hex.encodeHexString(keyBytes);
	}

	public static String extractStakePart(String shellyAddress) {

		if (determineAddressType(shellyAddress) != AddressType.SHELLY_ADDRESS) {
			return shellyAddress;
		}

		Bech32Data decoded = Bech32.decode(shellyAddress);
		byte[] base8Data = Bech32.decode5to8(decoded.data);

		int stakeKeyLength = 28;
		int stakeKeyStartIndex = base8Data.length - stakeKeyLength;

		// we need one more byte to put e1 aka 225 on the first position
		// https://cardano.stackexchange.com/questions/4046/bech32-encoding-stake-address-from-shelley-address-in-javascript-returning-wrong/
		byte[] stakeKeyBytes = new byte[stakeKeyLength + 1];

		System.arraycopy(base8Data, stakeKeyStartIndex, stakeKeyBytes, 1, stakeKeyLength);
		stakeKeyBytes[0] = (byte) 225;

		String stakeAddress = Bech32.encode(decoded.encoding, "stake", Bech32.encode8to5(stakeKeyBytes));
		return stakeAddress;
	}

	public static long currentSlot() {
		return System.currentTimeMillis() / 1000 - 1596491091 + 4924800;
	}

	public static enum AddressType {
		STAKE_ADDRESS, SERVICE_ADDRESS, SHELLY_ADDRESS
	}

	public static AddressType determineAddressType(String address) {
		address = address.replace("_test", "");
		if (address.startsWith("stake")) {
			return AddressType.STAKE_ADDRESS;
		} else if (address.startsWith("addr") && address.length() == 103) {
			return AddressType.SHELLY_ADDRESS;
		} else if (address.startsWith("addr") && address.length() == 58) {
			return AddressType.SERVICE_ADDRESS;
		} else {
			throw new IllegalArgumentException(String.format("Cannot determin addresstype of %s", address));
		}
	}

	public static List<String> splitStringInto64ByteChunks(String input) {
		List<String> chunks = new ArrayList<>();

		int byteCount = 0;
		StringBuilder currentChunk = new StringBuilder();

		for (int i = 0; i < input.length(); i++) {
			char currentChar = input.charAt(i);
			int charByteCount = String.valueOf(currentChar).getBytes(StandardCharsets.UTF_8).length;

			if (byteCount + charByteCount > 64) {
				chunks.add(currentChunk.toString());
				currentChunk = new StringBuilder();
				byteCount = 0;
			}

			currentChunk.append(currentChar);
			byteCount += charByteCount;
		}

		if (currentChunk.length() > 0) {
			chunks.add(currentChunk.toString());
		}

		return chunks;
	}

}
