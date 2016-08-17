package kr.domaindriven.util;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by jerry on 2016-08-17.
 */
public class FacebookAuth {
    private String authInfo;

    public FacebookAuth(String param1, String param2){
        String readyParam = param1+param2;
        encryption(readyParam);
    }

    /**
     * AuthInfo 암호화
     * @param authInfo
     *            사용자 facebookSSO ID + Email
     * @return 암호화 된 사용자 패스워드
     *         암호화 방식 : SHA-512
     */
    public boolean encryption(String userPassword) {
        MessageDigest md;
        boolean isSuccess;
        String tempPassword = "";

        try {
            md = MessageDigest.getInstance("SHA-512");

            md.update(userPassword.getBytes());
            byte[] mb = md.digest();
            for (int i = 0; i < mb.length; i++) {
                byte temp = mb[i];
                String s = Integer.toHexString(new Byte(temp));
                while (s.length() < 2) {
                    s = "0" + s;
                }
                s = s.substring(s.length() - 2);
                tempPassword += s;
            }
            setAuthInfo(tempPassword);
            isSuccess = true;
        } catch (NoSuchAlgorithmException e) {
            isSuccess = false;
            return isSuccess;
        }
        return isSuccess;
    }

    private void setAuthInfo(String temppassword) {
        this.authInfo = temppassword;
    }

    public String getAuthInfo() {
        return authInfo;
    }
}
