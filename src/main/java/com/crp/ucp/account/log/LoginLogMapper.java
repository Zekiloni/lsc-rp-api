package com.crp.ucp.account.log;

import com.crp.ucp.server.model.LoginLogAudit;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface LoginLogMapper {

    LoginLogAudit mapTo(LoginLogEntity loginLog);

    List<LoginLogAudit> mapTo(List<LoginLogEntity> loginLogs);
}
