package com.example.qqw;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

//회원가입 아이디를 체크해주는 클래스(가능여부)
public class ValidateRequest extends StringRequest {

    final static private String URL = "http://bbagazy.cafe24.com/UserValidate.php";
    private Map<String, String> parameters;

    public ValidateRequest(String userID, Response.Listener<String> listener) {
        //해당 URL에 파라메터를 POST 방식으로 숨겨서 보내라는 뜻.
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("userID", userID);
    }

    @Override
    public Map<String, String> getParams() {
        return parameters;
    }
}

