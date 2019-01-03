package com.example.qqw;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

//회원가입 요청을 보내주는 클래스
public class RegisterRequest extends StringRequest {

    final static private String URL = "http://bbagazy.cafe24.com/UserRegister.php";
    private Map<String, String> parameters;

    public RegisterRequest(String userID, String userPassword, String userGender, String userLocate, String userEmail, Response.Listener<String> listener) {
        //해당 URL에 파라메터를 POST 방식으로 숨겨서 보내라는 뜻.
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("userID", userID);
        parameters.put("userPassword", userPassword);
        parameters.put("userGender", userGender);
        parameters.put("userLocate", userLocate);
        parameters.put("userEmail", userEmail);
    }

    @Override
    public Map<String, String> getParams(){
        return parameters;
    }
}

