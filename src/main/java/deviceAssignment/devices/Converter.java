package deviceAssignment.devices;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.persistence.AttributeConverter;
import java.security.Key;
import java.util.Base64;

@Component
public class Converter implements AttributeConverter<String, String>{
		
	private static final String ALGORTITHM = "AES/ECB/PLCS5Padding";
	@Value("${ecryption.secret}")
	private String secret;
	
	
	@Override
	public String convertToDatabaseColumn(String deviceModel) {
		try {
			Key key = new SecretKeySpec(secret.getBytes(), "AES");
			Cipher c = Cipher.getInstance(ALGORTITHM);
			
			c.init(Cipher.DECRYPT_MODE, key);
			
			return Base64.getEncoder().encodeToString(c.doFinal(deviceModel.getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	@Override
	public String convertToEntityAttribute(String dbData) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
