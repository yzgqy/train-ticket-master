package security.tars.security.service;

import edu.fudan.common.util.Response;
import org.springframework.http.HttpHeaders;
import security.entity.SecurityConfig;
import security.tars.security.SecurityConfigTars;

import java.util.List;

/**
 * @Auther: yaya
 * @Date: 2019/7/19 10:45
 * @Description:
 */
public interface SecurityTarsService {
    Response<List<SecurityConfigTars>> findAllSecurityConfig();

    Response<SecurityConfigTars> addNewSecurityConfig(SecurityConfigTars info);

    Response<SecurityConfigTars> modifySecurityConfig(SecurityConfigTars info);

    Response<String> deleteSecurityConfig(String id);

    Response<String> check(String accountId);
}
