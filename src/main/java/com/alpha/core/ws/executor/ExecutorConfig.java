package com.alpha.core.ws.executor;

import com.alpha.core.ws.utils.enums.ProtocolType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jzhou237 on 2016-09-30.
 */
@Configuration
public class ExecutorConfig {

    @Resource(name = "caseExecutor")
    private ICaseExecutor caseExecutor;

    @Bean(name = "executorMap")
    public Map<ProtocolType, ICaseExecutor> getExecutor() {
        Map<ProtocolType, ICaseExecutor> executorMap = new HashMap<ProtocolType, ICaseExecutor>();
        executorMap.put(ProtocolType.WEB_SERVICE, this.caseExecutor);
        return executorMap;
    }
}
