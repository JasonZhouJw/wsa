package com.alpha.core.ws.client;

import com.alpha.core.ws.executor.RequestExecutor;
import com.alpha.core.ws.model.WsdlInfo;
import org.junit.Test;

import java.util.List;

/**
 * Created by jzhou237 on 9/8/2016.
 */

public class DynamicClientTest {

    @Test
    public void testRequest() throws Exception {
        List<WsdlInfo> wsdlInfoList = RequestExecutor.request("http://localhost:8080/services/");
        wsdlInfoList.forEach(wsdlInfo -> {
            DynamicClient.request(wsdlInfo);
        });
    }
}
