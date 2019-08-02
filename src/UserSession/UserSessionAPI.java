package UserSession;

import java.util.List;

public interface UserSessionAPI {

    List<List<Object>> runCode(String code);
    void updateSession();




}
