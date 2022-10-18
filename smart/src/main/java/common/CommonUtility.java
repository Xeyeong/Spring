package common;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import org.apache.commons.mail.HtmlEmail;
import org.springframework.stereotype.Service;

import member.MemberVO;

@Service
public class CommonUtility {

	// 임시비밀번호 이메일전송처리
	public void sendPassword(MemberVO vo, String pw) {
		HtmlEmail email = new HtmlEmail();
		email.setHostName("smtp.naver.com"); // 이메일 전송: 관리자
		email.setDebug(true);
		email.setCharset("utf-8");

		email.setAuthentication("darkdino1@naver.com", "as245168##"); // 관리자 이메일 로그인
		email.setSSLOnConnect(true); // 로그인버튼 클릭

		try {
			email.setFrom("darkdino1@naver.com", "스마트 웹&앱 관리자");
			email.addTo(vo.getEmail(), vo.getName()); // 받는사람
			email.setSubject("임시 비밀번호 발급");

			StringBuffer content = new StringBuffer();
			content.append("<html>");
			content.append("<body>");
			content.append("<h3>").append(vo.getName()).append("님 임시 비밀번호 발급</h3>");
			content.append("<div>임시 비밀번호: <strong>").append(pw).append("</strong></div>");
			content.append("</body>");
			content.append("</html>");

			email.setHtmlMsg(content.toString());
			email.send(); // 보내기버튼클릭

		} catch (Exception e) {
		}

	}

	// 비밀번호를 salt를 사용해 암호화하는 처리(암호화 : encrypt, 복호화 : decrypt)
	public String getEncrypt(String pw, String salt) {
		pw += salt; // 암호화 처리 메소드를 갖는 클래스 : MessageDigest
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");// 암호화방식지정
			md.update(pw.getBytes());
			byte[] bytes = md.digest();

			// 16진수로 변환하여 하나의 문자열로 만든다
			StringBuffer encrypt = new StringBuffer();
			for (byte b : bytes) {
				encrypt.append(String.format("%02x", b));
			}
			pw = encrypt.toString();
		} catch (NoSuchAlgorithmException e) {
		}
		return pw;
	}

	// 암호화에 사용할 salt
	public String generateSalt() {
		SecureRandom random = new SecureRandom();
		byte[] bytes = new byte[24];
		random.nextBytes(bytes);

		// 16진수로 변환 한 24개의 바이트 정보를 하나의 문자열로 만든다
		StringBuffer salt = new StringBuffer();
		for (byte b : bytes) {
			salt.append(String.format("%02x", b)); // 16진수 : %x 8진수 : %o 0214
		}
		return salt.toString();
	}

	public String requestAPI(StringBuffer apiURL) {
		String response = "";
		try {
			URL url = new URL(apiURL.toString());
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			int responseCode = con.getResponseCode();
			BufferedReader br;
			System.out.print("responseCode=" + responseCode);
			if (responseCode == 200) { // 정상 호출
				br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
			} else { // 에러 발생
				br = new BufferedReader(new InputStreamReader(con.getErrorStream(), "utf-8"));
			}
			String inputLine;
			StringBuffer res = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {
				res.append(inputLine);
			}
			br.close();
			response = res.toString();
		} catch (Exception e) {
			System.out.println(e);
		}
		return response;
	}

	public String requestAPI(StringBuffer apiURL, String header) {
		String response = "";
		try {
			URL url = new URL(apiURL.toString());
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Authorization", header);

			int responseCode = con.getResponseCode();
			BufferedReader br;
			System.out.print("responseCode=" + responseCode);
			if (responseCode == 200) { // 정상 호출
				br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
			} else { // 에러 발생
				br = new BufferedReader(new InputStreamReader(con.getErrorStream(), "utf-8"));
			}
			String inputLine;
			StringBuffer res = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {
				res.append(inputLine);
			}
			br.close();
			response = res.toString();
		} catch (Exception e) {
			System.out.println(e);
		}
		return response;
	}
}
